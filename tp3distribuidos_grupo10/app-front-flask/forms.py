from flask_wtf import FlaskForm
from wtforms import StringField, SelectField, DateField, IntegerField, BooleanField
from wtforms.validators import DataRequired

class OrderFilterForm(FlaskForm):
    product_code = StringField("Product Code")
    start_date = DateField("Start Date")
    end_date = DateField("End Date")
    status = SelectField("Status", choices=[("pending", "Pending"), ("completed", "Completed")])
    store_code = StringField("Store Code")

class SaveFilterForm(FlaskForm):
    name = StringField("Filter Name", validators=[DataRequired()])
    product_code = StringField("Product Code")
    start_date = DateField("Start Date")
    end_date = DateField("End Date")
    status = SelectField("Status", choices=[("pending", "Pending"), ("completed", "Completed")])
    store_code = StringField("Store Code")
