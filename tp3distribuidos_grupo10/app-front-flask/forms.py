from flask_wtf import FlaskForm
from wtforms import StringField, SelectField, DateField, IntegerField, BooleanField
from wtforms.validators import DataRequired

class OrderFilterForm(FlaskForm):
    codigoProducto = StringField("Producto Codigo")
    start_date = DateField("Start Date")
    end_date = DateField("End Date")
    estado = SelectField("Estado", choices=[("pendiente", "Pendiente"), ("completada", "Completada"),("cancelada", "Cancelada")])
    codigoTienda = StringField("Tienda Codigo")

class SaveFilterForm(FlaskForm):
    name = StringField("Filter Name", validators=[DataRequired()])
    codigoProducto = StringField("Product Code")
    start_date = DateField("Start Date")
    end_date = DateField("End Date")
    estado = SelectField("Status", choices=[("pendiente", "Pendiente"), ("completada", "Completada"), ("cancelada", "Cancelada")])
    codigoTienda = StringField("Tienda Codigo")
