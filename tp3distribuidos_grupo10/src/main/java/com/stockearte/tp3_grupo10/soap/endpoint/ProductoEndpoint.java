package com.stockearte.tp3_grupo10.soap.endpoint;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.stockearte.tp3_grupo10.converter.ProductoConverter;
import com.stockearte.tp3_grupo10.model.Producto;
import com.stockearte.tp3_grupo10.service.ProductoService;
import com.stockearte.tp3_grupo10.soap.interfaces.AddProductoRequest;
import com.stockearte.tp3_grupo10.soap.interfaces.AddProductoResponse;
import com.stockearte.tp3_grupo10.soap.interfaces.BuscarProductoRequest;
import com.stockearte.tp3_grupo10.soap.interfaces.BuscarProductoResponse;
import com.stockearte.tp3_grupo10.soap.interfaces.GetAllProductosRequest;
import com.stockearte.tp3_grupo10.soap.interfaces.GetAllProductosResponse;
import com.stockearte.tp3_grupo10.soap.interfaces.GetOneProductoByCodeRequest;
import com.stockearte.tp3_grupo10.soap.interfaces.GetOneProductoByCodeResponse;
import com.stockearte.tp3_grupo10.soap.interfaces.ProductoInfo;
import com.stockearte.tp3_grupo10.soap.interfaces.UpdateProductoRequest;
import com.stockearte.tp3_grupo10.soap.interfaces.UpdateProductoResponse;

@Endpoint
public class ProductoEndpoint {
	private static final String NAMESPACE_URI = "http://www.stockearte.com/tp3_grupo10/soap/interfaces";

	@Autowired
	private ProductoService productoService;

	@Autowired
	private ProductoConverter productoConverter;

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getOneProductoByCodeRequest")
	@ResponsePayload
	public GetOneProductoByCodeResponse GetOneProductoByCode(@RequestPayload GetOneProductoByCodeRequest request) {
		GetOneProductoByCodeResponse response = new GetOneProductoByCodeResponse();
		Producto producto = this.getProductoService().getOneById(request.getCodigo());
		response.setProducto(this.getProductoConverter().convertProductoToProductoInfo(producto));
		return response;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllProductosRequest")
	@ResponsePayload
	public GetAllProductosResponse getAllProductos(@RequestPayload GetAllProductosRequest request) {
		GetAllProductosResponse response = new GetAllProductosResponse();
		List<ProductoInfo> productos = this.getProductoConverter()
				.convertProductosToProductoInfos(this.getProductoService().getAll());
		for (ProductoInfo producto : productos) {
			response.getProductos().add(producto);
		}
		return response;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "buscarProductoRequest")
	@ResponsePayload
	public BuscarProductoResponse buscarProducto(@RequestPayload BuscarProductoRequest request) {
		BuscarProductoResponse response = new BuscarProductoResponse();
		List<ProductoInfo> productos = this.getProductoConverter()
				.convertProductosToProductoInfos(this.getProductoService().buscarProducto(request.getNombre(),
						request.getCodigo(), request.getTalle(), request.getColor()));
		for (ProductoInfo producto : productos) {
			response.getProductos().add(producto);
		}
		return response;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "addProductoRequest")
	@ResponsePayload
	public AddProductoResponse addProducto(@RequestPayload AddProductoRequest request) {
		AddProductoResponse response = new AddProductoResponse();
		response.setProducto(this.getProductoConverter()
				.convertProductoToProductoInfo(this.getProductoService().add(request.getProducto().getCodigo(),
						request.getProducto().getNombre(), request.getProducto().getTalle(),
						request.getProducto().getFoto(), request.getProducto().getColor())));
		return response;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateProductoRequest")
	@ResponsePayload
	public UpdateProductoResponse updateProducto(@RequestPayload UpdateProductoRequest request) {
		UpdateProductoResponse response = new UpdateProductoResponse();
		try {
			Producto producto = this.getProductoService().update(request.getCodigo(), request.getNombre(),
					request.getTalle(), request.getFoto(), request.getColor());
			response.getProductoServiceStatus().setStatus("OK");
			response.getProductoServiceStatus()
					.setProducto(this.getProductoConverter().convertProductoToProductoInfo(producto));
		} catch (Exception e) {
			response.getProductoServiceStatus().setStatus("ERROR");
			response.getProductoServiceStatus().setMessage(e.getLocalizedMessage());
		}
		return response;
	}

	public ProductoService getProductoService() {
		return productoService;
	}

	public void setProductoService(ProductoService productoService) {
		this.productoService = productoService;
	}

	public ProductoConverter getProductoConverter() {
		return productoConverter;
	}

	public void setProductoConverter(ProductoConverter productoConverter) {
		this.productoConverter = productoConverter;
	}
}
