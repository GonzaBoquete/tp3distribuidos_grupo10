package com.stockearte.tp3_grupo10.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.stockearte.tp3_grupo10.converter.FiltroConverter;
import com.stockearte.tp3_grupo10.model.Filtro;
import com.stockearte.tp3_grupo10.soap.endpoint.FiltroEndpoint;
import com.stockearte.tp3_grupo10.soap.interfaces.AddFiltroRequest;
import com.stockearte.tp3_grupo10.soap.interfaces.DeleteFiltroRequest;
import com.stockearte.tp3_grupo10.soap.interfaces.GetAllFiltrosRequest;
import com.stockearte.tp3_grupo10.soap.interfaces.GetOneFiltroByCodeRequest;
import com.stockearte.tp3_grupo10.soap.interfaces.GetOneFiltroByIdRequest;
import com.stockearte.tp3_grupo10.soap.interfaces.UpdateProductoDeFiltroRequest;
import com.stockearte.tp3_grupo10.soap.interfaces.UpdateTiendaDeFiltroRequest;

@RestController
@RequestMapping("/api/filtro")
public class FiltroController {

	@Autowired
	private FiltroConverter filtroConverter;

	@Autowired
	private FiltroEndpoint filtroEndpoint;

	@PostMapping("/add")
	public ResponseEntity<Filtro> addFiltro(@RequestParam String codigoFiltro, @RequestParam Long idUsuario,
			@RequestParam Long codigoTienda, @RequestParam Long codigoProducto) {
		AddFiltroRequest addFiltroRequest = new AddFiltroRequest();
		addFiltroRequest.setCodigoFiltro(codigoFiltro);
		addFiltroRequest.setIdUsuario(idUsuario);
		addFiltroRequest.setCodigoTienda(codigoTienda);
		addFiltroRequest.setCodigoProducto(codigoProducto);
		Filtro filtro = filtroConverter
				.convertFiltroInfoToFiltro(filtroEndpoint.addFiltro(addFiltroRequest).getFiltro());
		return ResponseEntity.ok(filtro);
	}

	@GetMapping("/getByCode")
	public ResponseEntity<Filtro> getFiltroByCode(@RequestParam String codigo) {
		GetOneFiltroByCodeRequest getOneFiltroByCodeRequest = new GetOneFiltroByCodeRequest();
		getOneFiltroByCodeRequest.setCodigo(codigo);
		Filtro filtro = filtroConverter
				.convertFiltroInfoToFiltro(filtroEndpoint.GetOneFiltroByCode(getOneFiltroByCodeRequest).getFiltro());
		return ResponseEntity.ok(filtro);
	}

	@GetMapping("/getById")
	public ResponseEntity<Filtro> getFiltroById(@RequestParam Long id) {
		GetOneFiltroByIdRequest getOneFiltroByIdRequest = new GetOneFiltroByIdRequest();
		getOneFiltroByIdRequest.setId(id);
		Filtro filtro = filtroConverter
				.convertFiltroInfoToFiltro(filtroEndpoint.GetOneFiltroById(getOneFiltroByIdRequest).getFiltro());
		return ResponseEntity.ok(filtro);
	}

	@GetMapping("/getAll")
	public ResponseEntity<List<Filtro>> getAllFiltros() {
		GetAllFiltrosRequest getAllFiltrosRequest = new GetAllFiltrosRequest();
		List<Filtro> filtros = filtroConverter
				.convertFiltroInfosToFiltros(filtroEndpoint.getAllFiltros(getAllFiltrosRequest).getFiltros());
		return ResponseEntity.ok(filtros);
	}

	@PostMapping("/updateProducto")
	public ResponseEntity<Filtro> updateProducto(@RequestParam String codigoFiltro, @RequestParam Long codigoProducto) {
		UpdateProductoDeFiltroRequest updateProductoDeFiltroRequest = new UpdateProductoDeFiltroRequest();
		updateProductoDeFiltroRequest.setCodigoFiltro(codigoFiltro);
		updateProductoDeFiltroRequest.setCodigoProducto(codigoProducto);
		Filtro filtro = filtroConverter.convertFiltroInfoToFiltro(filtroEndpoint
				.updateProductoDeFiltro(updateProductoDeFiltroRequest).getFiltroServiceStatus().getFiltro());
		return ResponseEntity.ok(filtro);
	}

	@PostMapping("/updateTienda")
	public ResponseEntity<Filtro> updateTienda(@RequestParam String codigoFiltro, @RequestParam Long codigoTienda) {
		UpdateTiendaDeFiltroRequest updateTiendaDeFiltroRequest = new UpdateTiendaDeFiltroRequest();
		updateTiendaDeFiltroRequest.setCodigoFiltro(codigoFiltro);
		updateTiendaDeFiltroRequest.setCodigoTienda(codigoTienda);
		Filtro filtro = filtroConverter.convertFiltroInfoToFiltro(
				filtroEndpoint.updateTiendaDeFiltro(updateTiendaDeFiltroRequest).getFiltroServiceStatus().getFiltro());
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