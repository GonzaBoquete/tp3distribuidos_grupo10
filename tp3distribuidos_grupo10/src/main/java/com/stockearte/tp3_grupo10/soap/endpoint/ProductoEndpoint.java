package com.stockearte.tp3_grupo10.soap.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;

import com.stockearte.tp3_grupo10.service.ProductoService;

@Endpoint
public class ProductoEndpoint {
	private static final String NAMESPACE_URI = "http://www.stockearte.com/tp3_grupo10/soap/interfaces";

    @Autowired
    private ProductoService productoService;
    
    

	public ProductoService getProductoService() {
		return productoService;
	}

	public void setProductoService(ProductoService productoService) {
		this.productoService = productoService;
	}
}
