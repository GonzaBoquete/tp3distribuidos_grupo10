package com.stockearte.tp3_grupo10.soap.endpoint;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.stockearte.tp3_grupo10.converter.TiendaConverter;
import com.stockearte.tp3_grupo10.model.Tienda;
import com.stockearte.tp3_grupo10.service.TiendaService;
import com.stockearte.tp3_grupo10.soap.interfaces.AddTiendaRequest;
import com.stockearte.tp3_grupo10.soap.interfaces.AddTiendaResponse;
import com.stockearte.tp3_grupo10.soap.interfaces.BuscarTiendaRequest;
import com.stockearte.tp3_grupo10.soap.interfaces.BuscarTiendaResponse;
import com.stockearte.tp3_grupo10.soap.interfaces.GetAllTiendasRequest;
import com.stockearte.tp3_grupo10.soap.interfaces.GetAllTiendasResponse;
import com.stockearte.tp3_grupo10.soap.interfaces.GetOneTiendaByCodeRequest;
import com.stockearte.tp3_grupo10.soap.interfaces.GetOneTiendaByCodeResponse;
import com.stockearte.tp3_grupo10.soap.interfaces.TiendaInfo;
import com.stockearte.tp3_grupo10.soap.interfaces.UpdateTiendaRequest;
import com.stockearte.tp3_grupo10.soap.interfaces.UpdateTiendaResponse;

@Endpoint
public class TiendaEndpoint {
	private static final String NAMESPACE_URI = "http://www.stockearte.com/tp3_grupo10/soap/interfaces";

	@Autowired
	private TiendaService tiendaService;

	@Autowired
	private TiendaConverter tiendaConverter;

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getOneTiendaByCodeRequest")
	@ResponsePayload
	public GetOneTiendaByCodeResponse GetOneTiendaByCode(@RequestPayload GetOneTiendaByCodeRequest request) {
		GetOneTiendaByCodeResponse response = new GetOneTiendaByCodeResponse();
		Tienda tienda = this.getTiendaService().getOneById(request.getCodigo());
		response.setTienda(this.getTiendaConverter().convertTiendaToInfo(tienda));
		return response;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllTiendasRequest")
	@ResponsePayload
	public GetAllTiendasResponse getAllTiendas(@RequestPayload GetAllTiendasRequest request) {
		GetAllTiendasResponse response = new GetAllTiendasResponse();
		List<TiendaInfo> tiendas = this.getTiendaConverter().convertTiendasToInfos(this.getTiendaService().getAll());
		for (TiendaInfo tienda : tiendas) {
			response.getTiendas().add(tienda);
		}
		return response;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "buscarTiendaRequest")
	@ResponsePayload
	public BuscarTiendaResponse buscarProducto(@RequestPayload BuscarTiendaRequest request) {
		BuscarTiendaResponse response = new BuscarTiendaResponse();
		List<TiendaInfo> tiendas = this.getTiendaConverter().convertTiendasToInfos(
				this.getTiendaService().buscarTienda(request.getCodigo(), request.isHabilitada()));
		for (TiendaInfo tienda : tiendas) {
			response.getTiendas().add(tienda);
		}
		return response;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "addTiendaRequest")
	@ResponsePayload
	public AddTiendaResponse addTienda(@RequestPayload AddTiendaRequest request) {
		AddTiendaResponse response = new AddTiendaResponse();
		response.setTienda(this.getTiendaConverter()
				.convertTiendaToInfo(this.getTiendaService().add(request.getTienda().getCodigo(),
						request.getTienda().getDireccion(), request.getTienda().getCiudad(),
						request.getTienda().getProvincia(), request.getTienda().isHabilitada())));
		return response;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateTiendaRequest")
	@ResponsePayload
	public UpdateTiendaResponse updateTienda(@RequestPayload UpdateTiendaRequest request) {
		UpdateTiendaResponse response = new UpdateTiendaResponse();
		try {
			Tienda tienda = this.getTiendaService().update(request.getCodigo(), request.getDireccion(),
					request.getCiudad(), request.getProvincia(), request.isHabilitada());
			response.getTiendaServiceStatus().setStatus("OK");
			response.getTiendaServiceStatus().setTienda(this.getTiendaConverter().convertTiendaToInfo(tienda));
		} catch (Exception e) {
			response.getTiendaServiceStatus().setStatus("ERROR");
			response.getTiendaServiceStatus().setMessage(e.getLocalizedMessage());
		}
		return response;
	}

	public TiendaService getTiendaService() {
		return tiendaService;
	}

	public void setTiendaService(TiendaService tiendaService) {
		this.tiendaService = tiendaService;
	}

	public TiendaConverter getTiendaConverter() {
		return tiendaConverter;
	}

	public void setTiendaConverter(TiendaConverter tiendaConverter) {
		this.tiendaConverter = tiendaConverter;
	}
}
