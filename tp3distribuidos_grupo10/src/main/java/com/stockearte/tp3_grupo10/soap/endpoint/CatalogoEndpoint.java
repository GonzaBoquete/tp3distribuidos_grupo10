package com.stockearte.tp3_grupo10.soap.endpoint;

import java.util.List;

import javax.xml.datatype.DatatypeConfigurationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.stockearte.tp3_grupo10.converter.CatalogoConverter;
import com.stockearte.tp3_grupo10.model.Catalogo;
import com.stockearte.tp3_grupo10.service.CatalogoService;
import com.stockearte.tp3_grupo10.soap.interfaces.AddCatalogoRequest;
import com.stockearte.tp3_grupo10.soap.interfaces.AddCatalogoResponse;
import com.stockearte.tp3_grupo10.soap.interfaces.AgregarProductoACatalogoRequest;
import com.stockearte.tp3_grupo10.soap.interfaces.AgregarProductoACatalogoResponse;
import com.stockearte.tp3_grupo10.soap.interfaces.CatalogoInfo;
import com.stockearte.tp3_grupo10.soap.interfaces.CatalogoServiceStatus;
import com.stockearte.tp3_grupo10.soap.interfaces.DeleteCatalogoRequest;
import com.stockearte.tp3_grupo10.soap.interfaces.DeleteCatalogoResponse;
import com.stockearte.tp3_grupo10.soap.interfaces.EliminarProductoDeCatalogoRequest;
import com.stockearte.tp3_grupo10.soap.interfaces.EliminarProductoDeCatalogoResponse;
import com.stockearte.tp3_grupo10.soap.interfaces.GetAllCatalogosRequest;
import com.stockearte.tp3_grupo10.soap.interfaces.GetAllCatalogosResponse;
import com.stockearte.tp3_grupo10.soap.interfaces.GetOneCatalogoByIdRequest;
import com.stockearte.tp3_grupo10.soap.interfaces.GetOneCatalogoByIdResponse;
import com.stockearte.tp3_grupo10.soap.interfaces.UpdateTiendaDeCatalogoRequest;
import com.stockearte.tp3_grupo10.soap.interfaces.UpdateTiendaDeCatalogoResponse;

@Endpoint
public class CatalogoEndpoint {
	private static final String NAMESPACE_URI = "http://www.stockearte.com/tp3_grupo10/soap/interfaces";

	@Autowired
	private CatalogoService catalogoService;

	@Autowired
	private CatalogoConverter catalogoConverter;

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getOneCatalogoByIdRequest")
	@ResponsePayload
	public GetOneCatalogoByIdResponse GetOneCatalogoByCode(@RequestPayload GetOneCatalogoByIdRequest request)
			throws DatatypeConfigurationException {
		GetOneCatalogoByIdResponse response = new GetOneCatalogoByIdResponse();
		Catalogo catalogo = this.getCatalogoService().getOneById(request.getIdCatalogo());
		response.setCatalogo(this.getCatalogoConverter().convertCatalogoToCatalogoInfo(catalogo));
		return response;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllCatalogosRequest")
	@ResponsePayload
	public GetAllCatalogosResponse getAllCatalogos(@RequestPayload GetAllCatalogosRequest request)
			throws DatatypeConfigurationException {
		GetAllCatalogosResponse response = new GetAllCatalogosResponse();
		List<CatalogoInfo> catalogos = this.getCatalogoConverter()
				.convertCatalogosToCatalogoInfos(this.getCatalogoService().getAll());
		for (CatalogoInfo catalogo : catalogos) {
			response.getCatalogos().add(catalogo);
		}
		return response;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "addCatalogoRequest")
	@ResponsePayload
	public AddCatalogoResponse addCatalogo(@RequestPayload AddCatalogoRequest request)
			throws DatatypeConfigurationException {
		AddCatalogoResponse response = new AddCatalogoResponse();
		response.setCatalogo(this.getCatalogoConverter()
				.convertCatalogoToCatalogoInfo(this.getCatalogoService().add(request.getIdTienda())));
		return response;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteCatalogoRequest")
	@ResponsePayload
	public DeleteCatalogoResponse deleteCatalogo(@RequestPayload DeleteCatalogoRequest request) {
		DeleteCatalogoResponse response = new DeleteCatalogoResponse();
		response.setCatalogoServiceStatus(new CatalogoServiceStatus());
		try {
			this.getCatalogoService().delete(request.getIdCatalogo());
			response.getCatalogoServiceStatus().setStatus("OK");
			response.getCatalogoServiceStatus().setMessage("Se elimino el catalogo con id :" + request.getIdCatalogo());
		} catch (Exception e) {
			response.getCatalogoServiceStatus().setStatus("ERROR");
			response.getCatalogoServiceStatus().setMessage(e.getLocalizedMessage());
		}
		return response;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateTiendaDeCatalogoRequest")
	@ResponsePayload
	public UpdateTiendaDeCatalogoResponse updateTiendaDeCatalogo(
			@RequestPayload UpdateTiendaDeCatalogoRequest request) {
		UpdateTiendaDeCatalogoResponse response = new UpdateTiendaDeCatalogoResponse();
		response.setCatalogoServiceStatus(new CatalogoServiceStatus());
		try {
			this.getCatalogoService().updateTienda(request.getIdCatalogo(), request.getCodigoTienda());
			response.getCatalogoServiceStatus().setStatus("OK");
			response.getCatalogoServiceStatus().setMessage("Se elimino el catalogo con id :" + request.getIdCatalogo());
		} catch (Exception e) {
			response.getCatalogoServiceStatus().setStatus("ERROR");
			response.getCatalogoServiceStatus().setMessage(e.getLocalizedMessage());
		}
		return response;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "agregarProductoACatalogoRequest")
	@ResponsePayload
	public AgregarProductoACatalogoResponse agregarProductoACatalogo(
			@RequestPayload AgregarProductoACatalogoRequest request) {
		AgregarProductoACatalogoResponse response = new AgregarProductoACatalogoResponse();
		response.setCatalogoServiceStatus(new CatalogoServiceStatus());
		try {
			Catalogo catalogo = this.getCatalogoService().agregarProducto(request.getCodigoCatalogo(),
					request.getCodigoProducto());
			response.getCatalogoServiceStatus().setStatus("OK");
			response.getCatalogoServiceStatus()
					.setCatalogo(this.getCatalogoConverter().convertCatalogoToCatalogoInfo(catalogo));
		} catch (Exception e) {
			response.getCatalogoServiceStatus().setStatus("ERROR");
			response.getCatalogoServiceStatus().setMessage(e.getLocalizedMessage());
		}
		return response;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "eliminarProductoDeCatalogoRequest")
	@ResponsePayload
	public EliminarProductoDeCatalogoResponse eliminarProductoDeCatalogo(
			@RequestPayload EliminarProductoDeCatalogoRequest request) {
		EliminarProductoDeCatalogoResponse response = new EliminarProductoDeCatalogoResponse();
		response.setCatalogoServiceStatus(new CatalogoServiceStatus());
		try {
			Catalogo catalogo = this.getCatalogoService().eliminarProducto(request.getCodigoCatalogo(),
					request.getCodigoProducto());
			response.getCatalogoServiceStatus().setStatus("OK");
			response.getCatalogoServiceStatus()
					.setCatalogo(this.getCatalogoConverter().convertCatalogoToCatalogoInfo(catalogo));
		} catch (Exception e) {
			response.getCatalogoServiceStatus().setStatus("ERROR");
			response.getCatalogoServiceStatus().setMessage(e.getLocalizedMessage());
		}
		return response;
	}


	public void setCatalogoService(CatalogoService catalogoService) {
		this.catalogoService = catalogoService;
	}

	public CatalogoConverter getCatalogoConverter() {
		return catalogoConverter;
	}

	public void setCatalogoConverter(CatalogoConverter catalogoConverter) {
		this.catalogoConverter = catalogoConverter;
	}

	public CatalogoService getCatalogoService() {
		return catalogoService;
	}
}
