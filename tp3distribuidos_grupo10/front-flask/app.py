from flask import Flask, render_template, request, jsonify, redirect, url_for, flash
import requests
import csv

app = Flask(__name__)
app.secret_key = 'supersecretkey'  # Para habilitar mensajes flash

# Ruta para la pantalla de informe de órdenes de compra
@app.route('/informe', methods=['GET', 'POST'])
def informe():
    if request.method == 'POST':
        product_code = request.form.get('product_code', '')
        start_date = request.form.get('start_date', '')
        end_date = request.form.get('end_date', '')
        status = request.form.get('status', '')
        store_code = request.form.get('store_code', '')

        api_url = 'http://localhost:8080/api/purchase-orders'
        params = {
            'productCode': product_code,
            'startDate': start_date,
            'endDate': end_date,
            'status': status,
            'storeCode': store_code
        }

        response = requests.get(api_url, params=params)
        if response.status_code == 200:
            orders = response.json()
            return render_template('report.html', orders=orders)
        else:
            flash(f"Error al consultar la API: {response.status_code}", 'error')
            return render_template('report.html')
    
    return render_template('report.html')

# Ruta para los filtros personalizados
@app.route('/filtros', methods=['GET', 'POST'])
def filtros():
    if request.method == 'POST':
        filter_name = request.form.get('filter_name')
        product_code = request.form.get('product_code', '')
        start_date = request.form.get('start_date', '')
        end_date = request.form.get('end_date', '')
        status = request.form.get('status', '')
        store_code = request.form.get('store_code', '')

        api_url = 'http://localhost:8080/api/filters'
        filter_data = {
            'name': filter_name,
            'productCode': product_code,
            'startDate': start_date,
            'endDate': end_date,
            'status': status,
            'storeCode': store_code
        }

        response = requests.post(api_url, json=filter_data)
        if response.status_code == 201:
            flash("Filtro guardado correctamente", 'success')
        else:
            flash(f"Error al guardar el filtro: {response.status_code}", 'error')

    return render_template('filter.html')

# Ruta para el catálogo de productos
@app.route('/catalogo', methods=['GET', 'POST'])
def catalogo():
    if request.method == 'POST':
        catalog_name = request.form.get('catalog_name', '')
        product_data = request.form.get('product_data', '')

        api_url = 'http://localhost:8080/api/catalogs'
        catalog_data = {
            'name': catalog_name,
            'products': product_data  # Suponiendo que se envía como un string o array
        }

        response = requests.post(api_url, json=catalog_data)
        if response.status_code == 201:
            flash("Catálogo creado correctamente", 'success')
        else:
            flash(f"Error al crear el catálogo: {response.status_code}", 'error')

    return render_template('catalog.html')

# Ruta para la carga masiva de usuarios
@app.route('/carga_masiva', methods=['GET', 'POST'])
def carga_masiva():
    if request.method == 'POST':
        file = request.files['csv_file']
        if file:
            file_content = file.read().decode('utf-8').splitlines()
            reader = csv.DictReader(file_content, delimiter=';')
            users = [row for row in reader]

            api_url = 'http://localhost:8080/api/users/bulk'
            response = requests.post(api_url, json=users)

            if response.status_code == 200:
                flash("Usuarios cargados exitosamente", 'success')
            else:
                flash(f"Error al cargar usuarios: {response.status_code}", 'error')

    return render_template('upload.html')

if __name__ == '__main__':
    app.run(debug=True)