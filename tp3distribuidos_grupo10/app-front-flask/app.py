from flask import Flask, render_template, request, redirect, url_for, flash
from config import Config
from models import db, UserFilter
from forms import OrderFilterForm, SaveFilterForm
import requests, os, csv
import pandas as pd

app = Flask(__name__)
app.config.from_object(Config)
db.init_app(app)

UPLOAD_FOLDER = 'uploads'
app.config['UPLOAD_FOLDER'] = UPLOAD_FOLDER
os.makedirs(UPLOAD_FOLDER, exist_ok=True)

#Pagina home  - INDEX
@app.route('/')
def index():
    return render_template('index.html')

# Ruta para el informe de órdenes de compra
@app.route("/orders", methods=["GET", "POST"])
def orders():
    form = OrderFilterForm()
    if form.validate_on_submit():
        # Capturar los datos de filtro
        params = {
            "product_code": form.product_code.data,
            "start_date": form.start_date.data,
            "end_date": form.end_date.data,
            "status": form.status.data,
            "store_code": form.store_code.data
        }
        # Realizar solicitud a la API de Spring Boot
        response = requests.get("http://localhost:8080/api/ordenDeCompra/getAll", params=params)
        orders = response.json() if response.status_code == 200 else []
        
        # Agrupación de resultados
        df = pd.DataFrame(orders)
        grouped_orders = df.groupby(["product_code", "status", "store_code"])["quantity"].sum().reset_index()

        return render_template("orders.html", form=form, orders=grouped_orders)
    return render_template("orders.html", form=form)

# Ruta para guardar filtros
@app.route("/save_filter", methods=["POST"])
def save_filter():
    form = SaveFilterForm()
    if form.validate_on_submit():
        new_filter = UserFilter(
            name=form.name.data,
            product_code=form.product_code.data,
            date_range=f"{form.start_date.data} - {form.end_date.data}",
            status=form.status.data,
            store_code=form.store_code.data,
            user_id=1  # ID de usuario de ejemplo
        )
        db.session.add(new_filter)
        db.session.commit()
        flash("Filter saved successfully")
    return redirect(url_for("orders"))

# Ruta para ver y aplicar filtros guardados
@app.route("/load_filter/<int:filter_id>")
def load_filter(filter_id):
    saved_filter = UserFilter.query.get(filter_id)
    if saved_filter:
        # Cargar filtro seleccionado
        form = OrderFilterForm(
            product_code=saved_filter.product_code,
            status=saved_filter.status,
            store_code=saved_filter.store_code
        )
        # Lógica de aplicar el filtro (similar a la ruta `/orders`)
        response = requests.get("http://localhost:8080/api/ordenDeCompra/getAll", params=form.data)
        orders = response.json() if response.status_code == 200 else []
        return render_template("orders.html", form=form, orders=orders)
    flash("Filter not found")
    return redirect(url_for("orders"))

# Ruta para catálogo de productos
@app.route("/catalog", methods=["GET", "POST"])
def catalog():
    # Lógica de acceso a catálogo solo para usuarios de tienda
    response = requests.get("http://localhost:8080/api/catalogo/getAll")
    catalogs = response.json() if response.status_code == 200 else []
    return render_template("catalog.html", catalogs=catalogs)

# Ruta para carga masiva de usuarios
@app.route("/users_upload", methods=['GET', 'POST'])
def users_upload():
    if request.method == 'POST':
        archivo = request.files.get('archivo')
        errores = []
        # Verifica que se haya subido un archivo y que sea un CSV
        if archivo and archivo.filename.endswith('.csv'):
            ruta_archivo = os.path.join(app.config['UPLOAD_FOLDER'], archivo.filename)
            archivo.save(ruta_archivo) # Procesa el archivo CSV
   
            with open(ruta_archivo, newline='', encoding='utf-8') as csvfile:
                csv_reader = csv.reader(csvfile, delimiter=';')

            for linea_num, linea in enumerate(csv_reader, start=1):
                try:
                    user = {
                            "nombreUsuario": linea["usuario"],
                            "contrasena": linea["contraseña"],
                            "nombre": linea["nombre"],
                            "apellido": linea["apellido"],
                            "codigoTienda": linea["codigo de tienda"]
                            }
                    if not "usuario" or not "contraseña" or not "nombre" or not "apellido" or not "codigo_tienda":
                            errores.append(f"Línea {linea_num}: Faltan campos.")
                            continue
                    response = requests.post("http://localhost:8080/api/usuario/add", data=user)
                except Exception as e:
                        errores.append(f"Línea {linea_num}: Error al procesar - {e}")
            if errores:
                flash("Se encontraron errores en el archivo CSV: " + ", ".join(errores))
            else:
                flash("Carga masiva completada exitosamente.")
            return redirect(url_for('index'))
        flash("No se subió un archivo o el archivo no es válido.")
        return redirect(url_for('users_upload'))
                
    return render_template("users_upload.html")

if __name__ == "__main__":
    app.run(debug=True)