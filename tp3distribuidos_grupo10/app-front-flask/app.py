from flask import Flask, render_template, request, redirect, url_for, flash, jsonify
from zeep import Client
from config import config

app = Flask(__name__)
app.config.from_object(config)

# Configuración de los clientes SOAP
client_orders = Client(config.SOAP_WSDL_ORDERS)
client_filters = Client(config.SOAP_WSDL_FILTERS)
client_catalog = Client(config.SOAP_WSDL_CATALOG)
client_users = Client(config.SOAP_WSDL_USERS)

# Módulo de Informe de Órdenes de Compra
@app.route('/orders_report', methods=['GET', 'POST'])
def orders_report():
    filters = {
        "product_code": request.form.get("product_code"),
        "start_date": request.form.get("start_date"),
        "end_date": request.form.get("end_date"),
        "status": request.form.get("status"),
        "store": request.form.get("store")
    }

    # Realizar solicitud al servicio SOAP para obtener órdenes con filtros
    response = client_orders.service.getOrdersReport(**filters)
    orders = response if response else []

    return render_template('orders_report.html', orders=orders)

# Guardar Filtro Personalizado
@app.route('/save_filter', methods=['POST'])
def save_filter():
    filter_data = {
        "name": request.form.get("filter_name"),
        "product_code": request.form.get("product_code"),
        "start_date": request.form.get("start_date"),
        "end_date": request.form.get("end_date"),
        "status": request.form.get("status"),
        "store": request.form.get("store")
    }

    # Llamada al servicio SOAP para guardar el filtro
    response = client_filters.service.saveFilter(filter_data)

    if response.success:
        flash("Filtro guardado exitosamente", "success")
    else:
        flash("Error al guardar el filtro", "error")
    return redirect(url_for('orders_report'))

# Cargar Filtros Guardados
@app.route('/load_filters', methods=['GET'])
def load_filters():
    # Llamada al servicio SOAP para obtener los filtros guardados
    filters = client_filters.service.getSavedFilters()

    return render_template('filters.html', filters=filters)

# Módulo de Catálogo de Productos
@app.route('/catalog', methods=['GET', 'POST'])
def catalog():
    if request.method == 'POST':
        product_data = {
            "product_name": request.form.get("product_name"),
            "color": request.form.get("color"),
            "size": request.form.get("size")
        }
        
        # Llamada al servicio SOAP para agregar producto al catálogo
        response = client_catalog.service.addProductToCatalog(product_data)
        
        if response.success:
            flash("Producto agregado al catálogo", "success")
        else:
            flash("Error al agregar producto", "error")

    # Obtener catálogo completo desde el servicio SOAP
    catalog = client_catalog.service.getCatalog()

    return render_template('catalog.html', catalog=catalog)

# Generación de PDF de Catálogo
@app.route('/catalog/download_pdf', methods=['GET'])
def download_catalog_pdf():
    # Llamada al servicio SOAP para generar el PDF
    response = client_catalog.service.generateCatalogPDF()
    if response.success:
        pdf_content = response.file_data
        return pdf_content, 200, {'Content-Type': 'application/pdf'}
    
    flash("Error al descargar el catálogo en PDF", "error")
    return redirect(url_for('catalog'))

# Carga Masiva de Usuarios
@app.route('/bulk_upload_users', methods=['GET', 'POST'])
def bulk_upload_users():
    if request.method == 'POST':
        file = request.files['file']
        if file.filename.endswith('.csv'):
            # Convertir archivo CSV en base64 para enviarlo al servicio SOAP
            file_data = file.read().decode("utf-8")

            # Llamada al servicio SOAP para procesar carga masiva de usuarios
            response = client_users.service.uploadUsersCSV(file_data)

            if response.success:
                flash("Carga masiva de usuarios completada", "success")
                errors = response.errors
                return render_template("errors.html", errors=errors)
            else:
                flash("Error en la carga masiva de usuarios", "error")
        else:
            flash("El archivo debe ser en formato CSV", "error")

    return render_template('upload_users.html')

# Ruta de inicio
@app.route('/')
def index():
    return render_template('index.html')

if __name__ == '__main__':
    app.run(debug=True)