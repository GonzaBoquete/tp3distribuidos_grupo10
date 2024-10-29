package com.stockearte.tp3_grupo10.soap.endpoint;

import java.util.List;

import javax.xml.datatype.DatatypeConfigurationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.stockearte.tp3_grupo10.converter.OrdenDeCompraConverter;
import com.stockearte.tp3_grupo10.model.OrdenDeCompra;
import com.stockearte.tp3_grupo10.service.OrdenDeCompraService;
import com.stockearte.tp3_grupo10.soap.interfaces.AddOrdenDeCompraRequest;
import com.stockearte.tp3_grupo10.soap.interfaces.AddOrdenDeCompraResponse;
import com.stockearte.tp3_grupo10.soap.interfaces.AgregarItemOrdenDeCompraRequest;
import com.stockearte.tp3_grupo10.soap.interfaces.AgregarItemOrdenDeCompraResponse;
import com.stockearte.tp3_grupo10.soap.interfaces.DeleteOrdenDeCompraRequest;
import com.stockearte.tp3_grupo10.soap.interfaces.DeleteOrdenDeCompraResponse;
import com.stockearte.tp3_grupo10.soap.interfaces.EliminarItemOrdenDeCompraRequest;
import com.stockearte.tp3_grupo10.soap.interfaces.EliminarItemOrdenDeCompraResponse;
import com.stockearte.tp3_grupo10.soap.interfaces.GetAllOrdenesDeCompraRequest;
import com.stockearte.tp3_grupo10.soap.interfaces.GetAllOrdenesDeCompraResponse;
import com.stockearte.tp3_grupo10.soap.interfaces.GetOneOrdenDeCompraByIdRequest;
import com.stockearte.tp3_grupo10.soap.interfaces.GetOneOrdenDeCompraByIdResponse;
import com.stockearte.tp3_grupo10.soap.interfaces.OrdenDeCompraInfo;

@Endpoint
public class OrdenDeCompraEndpoint {
	private static final String NAMESPACE_URI = "http://www.stockearte.com/tp3_grupo10/soap/interfaces";

	@Autowired
	private OrdenDeCompraService ordenDeCompraService;

	@Autowired
	private OrdenDeCompraConverter ordenDeCompraConverter;

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getOneOrdenDeCompraByIdRequest")
	@ResponsePayload
	public GetOneOrdenDeCompraByIdResponse GetOneOrdenDeCompraById(
			@RequestPayload GetOneOrdenDeCompraByIdRequest request) throws DatatypeConfigurationException {
		GetOneOrdenDeCompraByIdResponse response = new GetOneOrdenDeCompraByIdResponse();
		OrdenDeCompra ordenDeCompra = this.getOrdenDeCompraService().getOneById(request.getId());
		response.setOrdenDeCompra(this.getOrdenDeCompraConverter().convertOrdenDeCompraToInfo(ordenDeCompra));
		return response;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllOrdenesDeCompraRequest")
	@ResponsePayload
	public GetAllOrdenesDeCompraResponse getAllOrdenesDeCompra(@RequestPayload GetAllOrdenesDeCompraRequest request)
			throws DatatypeConfigurationException {
		GetAllOrdenesDeCompraResponse response = new GetAllOrdenesDeCompraResponse();
		List<OrdenDeCompraInfo> ordenesDeCompra = this.getOrdenDeCompraConverter()
				.convertOrdenesDeCompraToInfoList(this.getOrdenDeCompraService().getAll());
		for (OrdenDeCompraInfo ordenDeCompra : ordenesDeCompra) {
			response.getOrdenesDeCompra().add(ordenDeCompra);
		}
		return response;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "addOrdenDeCompraRequest")
	@ResponsePayload
	public AddOrdenDeCompraResponse addOrdenDeCompra(@RequestPayload AddOrdenDeCompraRequest request)
			throws DatatypeConfigurationException {
		AddOrdenDeCompraResponse response = new AddOrdenDeCompraResponse();
		response.getOrdenDeCompraServiceStatus().setOrdenDeCompra(this.getOrdenDeCompraConverter()
				.convertOrdenDeCompraToInfo(this.getOrdenDeCompraService().add(request.getCodigoTienda())));
		return response;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteOrdenDeCompraRequest")
	@ResponsePayload
	public DeleteOrdenDeCompraResponse deleteOrdenDeCompra(@RequestPayload DeleteOrdenDeCompraRequest request) {
		DeleteOrdenDeCompraResponse response = new DeleteOrdenDeCompraResponse();
		try {
			this.getOrdenDeCompraService().delete(request.getId());
			response.getOrdenDeCompraServiceStatus().setStatus("OK");
			response.getOrdenDeCompraServiceStatus()
					.setMessage("Se elimino el ordenDeCompra con id :" + request.getId());
		} catch (Exception e) {
			response.getOrdenDeCompraServiceStatus().setStatus("ERROR");
			response.getOrdenDeCompraServiceStatus().setMessage(e.getLocalizedMessage());
		}
		return response;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "agregarItemOrdenDeCompraRequest")
	@ResponsePayload
	public AgregarItemOrdenDeCompraResponse agregarItemOrdenDeCompra(
			@RequestPayload AgregarItemOrdenDeCompraRequest request) {
		AgregarItemOrdenDeCompraResponse response = new AgregarItemOrdenDeCompraResponse();
		try {
			OrdenDeCompra ordenDeCompra = this.getOrdenDeCompraService().agregarItemOrdenDeCompra(request.getId(),
					request.getCantidad(), request.getCodigoProducto());
			response.getOrdenDeCompraServiceStatus().setStatus("OK");
			response.getOrdenDeCompraServiceStatus()
					.setOrdenDeCompra(this.getOrdenDeCompraConverter().convertOrdenDeCompraToInfo(ordenDeCompra));
		} catch (Exception e) {
			response.getOrdenDeCompraServiceStatus().setStatus("ERROR");
			response.getOrdenDeCompraServiceStatus().setMessage(e.getLocalizedMessage());
		}
		return response;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "eliminarItemOrdenDeCompraRequest")
	@ResponsePayload
	public EliminarItemOrdenDeCompraResponse eliminarItemOrdenDeCompra(
			@RequestPayload EliminarItemOrdenDeCompraRequest request) {
		EliminarItemOrdenDeCompraResponse response = new EliminarItemOrdenDeCompraResponse();
		try {
			OrdenDeCompra ordenDeCompra = this.getOrdenDeCompraService().eliminarItemOrdenDeCompra(request.getId(),
					request.getIdItemOrdenDeCompra());
			response.getOrdenDeCompraServiceStatus().setStatus("OK");
			response.getOrdenDeCompraServiceStatus()
					.setOrdenDeCompra(this.getOrdenDeCompraConverter().convertOrdenDeCompraToInfo(ordenDeCompra));
		} catch (Exception e) {
			response.getOrdenDeCompraServiceStatus().setStatus("ERROR");
			response.getOrdenDeCompraServiceStatus().setMessage(e.getLocalizedMessage());
		}
		return response;
	}

	public void setOrdenDeCompraService(OrdenDeCompraService ordenDeCompraService) {
		this.ordenDeCompraService = ordenDeCompraService;
	}

	public OrdenDeCompraConverter getOrdenDeCompraConverter() {
		return ordenDeCompraConverter;
	}

	public void setOrdenDeCompraConverter(OrdenDeCompraConverter ordenDeCompraConverter) {
		this.ordenDeCompraConverter = ordenDeCompraConverter;
	}

	public OrdenDeCompraService getOrdenDeCompraService() {
		return ordenDeCompraService;
	}
}
