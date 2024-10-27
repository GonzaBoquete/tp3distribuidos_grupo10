class Config:
    SOAP_WSDL_ORDERS = "http://backend-soap-url.com/orders?wsdl"    # URL del servicio SOAP para órdenes de compra
    SOAP_WSDL_FILTERS = "http://backend-soap-url.com/filters?wsdl"   # URL del servicio SOAP para filtros personalizados
    SOAP_WSDL_CATALOG = "http://backend-soap-url.com/catalog?wsdl"   # URL del servicio SOAP para catálogo de productos
    SOAP_WSDL_USERS = "http://backend-soap-url.com/users?wsdl"       # URL del servicio SOAP para carga masiva de usuarios
    SECRET_KEY = "your_secret_key"

config = Config()