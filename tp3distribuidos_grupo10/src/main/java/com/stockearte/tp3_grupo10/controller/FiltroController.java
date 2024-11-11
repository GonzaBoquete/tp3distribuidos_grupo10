package com.stockearte.tp3_grupo10.controller;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.stockearte.tp3_grupo10.converter.FiltroConverter;
import com.stockearte.tp3_grupo10.enumerators.EstadoOrden;
import com.stockearte.tp3_grupo10.model.Filtro;
import com.stockearte.tp3_grupo10.soap.endpoint.FiltroEndpoint;
import com.stockearte.tp3_grupo10.soap.interfaces.AddFiltroRequest;
import com.stockearte.tp3_grupo10.soap.interfaces.DeleteFiltroRequest;
import com.stockearte.tp3_grupo10.soap.interfaces.EstadoOrdenInfoFilter;
import com.stockearte.tp3_grupo10.soap.interfaces.GetAllFiltrosRequest;
import com.stockearte.tp3_grupo10.soap.interfaces.GetOneFiltroByCodeRequest;
import com.stockearte.tp3_grupo10.soap.interfaces.GetOneFiltroByIdRequest;
import com.stockearte.tp3_grupo10.soap.interfaces.UpdateFiltroRequest;

@RestController
@RequestMapping("/api/filtro")
public class FiltroController {

	@Autowired
	private FiltroConverter filtroConverter;

	@Autowired
	private FiltroEndpoint filtroEndpoint;

	@PostMapping("/add")
	public ResponseEntity<Filtro> addFiltro(@RequestParam String nombreFiltro, @RequestParam Long idUsuario,
			@RequestParam Long codigoTienda, @RequestParam Long codigoProducto, @RequestParam LocalDate fechaDesde,
			@RequestParam LocalDate fechaHasta, @RequestParam EstadoOrden estado)
			throws DatatypeConfigurationException {
		AddFiltroRequest addFiltroRequest = new AddFiltroRequest();
		GregorianCalendar gcal1 = GregorianCalendar.from(fechaDesde.atStartOfDay(ZoneId.systemDefault()));
		GregorianCalendar gcal2 = GregorianCalendar.from(fechaHasta.atStartOfDay(ZoneId.systemDefault()));
		addFiltroRequest.setCodigoFiltro(nombreFiltro);
		addFiltroRequest.setIdUsuario(idUsuario);
		addFiltroRequest.setCodigoTienda(codigoTienda);
		addFiltroRequest.setCodigoProducto(codigoProducto);
		addFiltroRequest.setFechaDesde(DatatypeFactory.newInstance().newXMLGregorianCalendar(gcal1));
		addFiltroRequest.setFechaHasta(DatatypeFactory.newInstance().newXMLGregorianCalendar(gcal2));
		addFiltroRequest.setEstado(EstadoOrdenInfoFilter.valueOf(estado.name()));
		Filtro filtro = filtroConverter
				.convertFiltroInfoToFiltro(filtroEndpoint.addFiltro(addFiltroRequest).getFiltro());
		return ResponseEntity.ok(filtro);
	}

	@GetMapping("/getByCode")
	public ResponseEntity<Filtro> getFiltroByCode(@RequestParam String codigo) throws DatatypeConfigurationException {
		GetOneFiltroByCodeRequest getOneFiltroByCodeRequest = new GetOneFiltroByCodeRequest();
		getOneFiltroByCodeRequest.setCodigo(codigo);
		Filtro filtro = filtroConverter
				.convertFiltroInfoToFiltro(filtroEndpoint.GetOneFiltroByCode(getOneFiltroByCodeRequest).getFiltro());
		return ResponseEntity.ok(filtro);
	}

	@GetMapping("/getById")
	public ResponseEntity<Filtro> getFiltroById(@RequestParam Long id) throws DatatypeConfigurationException {
		GetOneFiltroByIdRequest getOneFiltroByIdRequest = new GetOneFiltroByIdRequest();
		getOneFiltroByIdRequest.setId(id);
		Filtro filtro = filtroConverter
				.convertFiltroInfoToFiltro(filtroEndpoint.GetOneFiltroById(getOneFiltroByIdRequest).getFiltro());
		return ResponseEntity.ok(filtro);
	}

	@GetMapping("/getAll")
	public ResponseEntity<List<Filtro>> getAllFiltros() throws DatatypeConfigurationException {
		GetAllFiltrosRequest getAllFiltrosRequest = new GetAllFiltrosRequest();
		List<Filtro> filtros = filtroConverter
				.convertFiltroInfosToFiltros(filtroEndpoint.getAllFiltros(getAllFiltrosRequest).getFiltros());
		return ResponseEntity.ok(filtros);
	}

	@PostMapping("/update")
	public ResponseEntity<Filtro> updateFiltro(@RequestParam String nombreFiltro, @RequestParam Long codigoTienda,
			@RequestParam Long codigoProducto, @RequestParam LocalDate fechaDesde, @RequestParam LocalDate fechaHasta,
			@RequestParam EstadoOrden estado) throws DatatypeConfigurationException {
		UpdateFiltroRequest updateFiltroRequest = new UpdateFiltroRequest();
		GregorianCalendar gcal1 = GregorianCalendar.from(fechaDesde.atStartOfDay(ZoneId.systemDefault()));
		GregorianCalendar gcal2 = GregorianCalendar.from(fechaHasta.atStartOfDay(ZoneId.systemDefault()));
		updateFiltroRequest.setNombre(nombreFiltro);
		updateFiltroRequest.setCodigoTienda(codigoTienda);
		updateFiltroRequest.setCodigoProducto(codigoProducto);
		updateFiltroRequest.setFechaDesde(DatatypeFactory.newInstance().newXMLGregorianCalendar(gcal1));
		updateFiltroRequest.setFechaHasta(DatatypeFactory.newInstance().newXMLGregorianCalendar(gcal2));
		updateFiltroRequest.setEstado(EstadoOrdenInfoFilter.valueOf(estado.name()));
		Filtro filtro = filtroConverter.convertFiltroInfoToFiltro(
				filtroEndpoint.updateFiltro(updateFiltroRequest).getFiltroServiceStatus().getFiltro());
		return ResponseEntity.ok(filtro);
	}

	@PostMapping("/delete")
	public ResponseEntity<Filtro> deleteFiltro(@RequestParam String codigo) {
		DeleteFiltroRequest deleteFiltroRequest = new DeleteFiltroRequest();
		deleteFiltroRequest.setCodigoFiltro(codigo);
		Filtro filtro = filtroConverter.convertFiltroInfoToFiltro(
				filtroEndpoint.deleteFiltro(deleteFiltroRequest).getFiltroServiceStatus().getFiltro());
		return ResponseEntity.ok(filtro);
	}
}