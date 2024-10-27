from flask_sqlalchemy import SQLAlchemy

db = SQLAlchemy()

class UserFilter(db.Model):
    id = db.Column(db.Integer, primary_key=True)
    name = db.Column(db.String(100), nullable=False)
    product_code = db.Column(db.String(20), nullable=True)
    date_range = db.Column(db.String(50), nullable=True)
    status = db.Column(db.String(20), nullable=True)
    store_code = db.Column(db.String(20), nullable=True)
    user_id = db.Column(db.Integer, nullable=False)
