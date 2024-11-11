package com.stockearte.tp3_grupo10.soap.endpoint;

import java.util.List;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.XMLGregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.stockearte.tp3_grupo10.converter.FiltroConverter;
import com.stockearte.tp3_grupo10.enumerators.EstadoOrden;
import com.stockearte.tp3_grupo10.model.Filtro;
import com.stockearte.tp3_grupo10.service.FiltroService;
import com.stockearte.tp3_grupo10.soap.interfaces.AddFiltroRequest;
import com.stockearte.tp3_grupo10.soap.interfaces.AddFiltroResponse;
import com.stockearte.tp3_grupo10.soap.interfaces.DeleteFiltroRequest;
import com.stockearte.tp3_grupo10.soap.interfaces.DeleteFiltroResponse;
import com.stockearte.tp3_grupo10.soap.interfaces.FiltroInfo;
import com.stockearte.tp3_grupo10.soap.interfaces.FiltroServiceStatus;
import com.stockearte.tp3_grupo10.soap.interfaces.GetAllFiltrosRequest;
import com.stockearte.tp3_grupo10.soap.interfaces.GetAllFiltrosResponse;
import com.stockearte.tp3_grupo10.soap.interfaces.GetOneFiltroByCodeRequest;
import com.stockearte.tp3_grupo10.soap.interfaces.GetOneFiltroByCodeResponse;
import com.stockearte.tp3_grupo10.soap.interfaces.GetOneFiltroByIdRequest;
import com.stockearte.tp3_grupo10.soap.interfaces.GetOneFiltroByIdResponse;
import com.stockearte.tp3_grupo10.soap.interfaces.UpdateFiltroRequest;
import com.stockearte.tp3_grupo10.soap.interfaces.UpdateFiltroResponse;

@Endpoint
public class FiltroEndpoint {
	private static final String NAMESPACE_URI = "http://www.stockearte.com/tp3_grupo10/soap/interfaces";

	@Autowired
	private FiltroService filtroService;

	@Autowired
	private FiltroConverter filtroConverter;

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getOneFiltroByCodeRequest")
	@ResponsePayload
	public GetOneFiltroByCodeResponse GetOneFiltroByCode(@RequestPayload GetOneFiltroByCodeRequest request)
			throws DatatypeConfigurationException {
		GetOneFiltroByCodeResponse response = new GetOneFiltroByCodeResponse();
		Filtro filtro = this.getFiltroService().getOneByCode(request.getCodigo());
		response.setFiltro(this.getFiltroConverter().convertFiltroToFiltroInfo(filtro));
		return response;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getOneFiltroByIdResponse")
	@ResponsePayload
	public GetOneFiltroByIdResponse GetOneFiltroById(@RequestPayload GetOneFiltroByIdRequest request)
			throws DatatypeConfigurationException {
		GetOneFiltroByIdResponse response = new GetOneFiltroByIdResponse();
		Filtro filtro = this.getFiltroService().getOneById(request.getId());
		response.setFiltro(this.getFiltroConverter().convertFiltroToFiltroInfo(filtro));
		return response;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllFiltrosRequest")
	@ResponsePayload
	public GetAllFiltrosResponse getAllFiltros(@RequestPayload GetAllFiltrosRequest request)
			throws DatatypeConfigurationException {
		GetAllFiltrosResponse response = new GetAllFiltrosResponse();
		List<FiltroInfo> filtros = this.getFiltroConverter()
				.convertFiltrosToFiltroInfos(this.getFiltroService().getAll());
		for (FiltroInfo filtro : filtros) {
			response.getFiltros().add(filtro);
		}
		return response;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "addFiltroRequest")
	@ResponsePayload
	public AddFiltroResponse addFiltro(@RequestPayload AddFiltroRequest request) throws DatatypeConfigurationException {
		AddFiltroResponse response = new AddFiltroResponse();
		XMLGregorianCalendar xmlGregorianCalendar1 = request.getFechaDesde();
		XMLGregorianCalendar xmlGregorianCalendar2 = request.getFechaHasta();

		response.setFiltro(this.getFiltroConverter()
				.convertFiltroToFiltroInfo(this.getFiltroService().add(request.getCodigoFiltro(),
						request.getIdUsuario(), request.getCodigoTienda(), request.getCodigoProducto(),
						xmlGregorianCalendar1.toGregorianCalendar().toZonedDateTime().toLocalDate(),
						xmlGregorianCalendar2.toGregorianCalendar().toZonedDateTime().toLocalDate(),
						EstadoOrden.valueOf(request.getEstado().toString()))));
		return response;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateFiltroRequest")
	@ResponsePayload
	public UpdateFiltroResponse updateFiltro(@RequestPayload UpdateFiltroRequest request)
			throws DatatypeConfigurationException {
		UpdateFiltroResponse response = new UpdateFiltroResponse();
		response.setFiltroServiceStatus(new FiltroServiceStatus());
		XMLGregorianCalendar xmlGregorianCalendar1 = request.getFechaDesde();
		XMLGregorianCalendar xmlGregorianCalendar2 = request.getFechaHasta();

		response.getFiltroServiceStatus()
				.setFiltro(this.getFiltroConverter()
						.convertFiltroToFiltroInfo(this.getFiltroService().updateFiltro(request.getNombre(),
								request.getCodigoTienda(), request.getCodigoProducto(),
								xmlGregorianCalendar1.toGregorianCalendar().toZonedDateTime().toLocalDate(),
								xmlGregorianCalendar2.toGregorianCalendar().toZonedDateTime().toLocalDate(),
								EstadoOrden.valueOf(request.getEstado().toString()))));
		return response;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteFiltroRequest")
	@ResponsePayload
	public DeleteFiltroResponse deleteFiltro(@RequestPayload DeleteFiltroRequest request) {
		DeleteFiltroResponse response = new DeleteFiltroResponse();
		response.setFiltroServiceStatus(new FiltroServiceStatus());
		try {
			this.getFiltroService().delete(request.getCodigoFiltro());
			response.getFiltroServiceStatus().setStatus("OK");
			response.getFiltroServiceStatus()
					.setMessage("Se elimino el filtro con codigo :" + request.getCodigoFiltro());
		} catch (Exception e) {
			response.getFiltroServiceStatus().setStatus("ERROR");
			response.getFiltroServiceStatus().setMessage(e.getLocalizedMessage());
		}
		return response;
	}

	public FiltroService getFiltroService() {
		return filtroService;
	}

	public void setFiltroService(FiltroService filtroService) {
		this.filtroService = filtroService;
	}

	public FiltroConverter getFiltroConverter() {
		return filtroConverter;
	}

	public void setFiltroConverter(FiltroConverter filtroConverter) {
		this.filtroConverter = filtroConverter;
	}
}
