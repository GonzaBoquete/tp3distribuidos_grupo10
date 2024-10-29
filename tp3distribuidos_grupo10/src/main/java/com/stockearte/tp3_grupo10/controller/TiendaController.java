package com.stockearte.tp3_grupo10.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.stockearte.tp3_grupo10.converter.TiendaConverter;
import com.stockearte.tp3_grupo10.model.Tienda;
import com.stockearte.tp3_grupo10.soap.endpoint.TiendaEndpoint;
import com.stockearte.tp3_grupo10.soap.interfaces.AddTiendaRequest;
import com.stockearte.tp3_grupo10.soap.interfaces.BuscarTiendaRequest;
import com.stockearte.tp3_grupo10.soap.interfaces.GetAllTiendasRequest;
import com.stockearte.tp3_grupo10.soap.interfaces.GetOneTiendaByCodeRequest;
import com.stockearte.tp3_grupo10.soap.interfaces.UpdateTiendaRequest;

@RestController
@RequestMapping("/api/tienda")
public class TiendaController {

	@Autowired
	private TiendaEndpoint tiendaEndpoint;

	@Autowired
	private TiendaConverter tiendaConverter;

	@PostMapping("/add")
	public ResponseEntity<Tienda> addTienda(@RequestParam Long codigoTienda, @RequestParam String direccion,
			@RequestParam String ciudad, @RequestParam String provincia, @RequestParam boolean habilitada) {
		AddTiendaRequest addTiendaRequest = new AddTiendaRequest();
		addTiendaRequest.setCodigoTienda(codigoTienda);
		addTiendaRequest.setDireccion(direccion);
		addTiendaRequest.setCiudad(ciudad);
		addTiendaRequest.setProvincia(provincia);
		addTiendaRequest.setHabilitada(habilitada);
		Tienda tienda = tiendaConverter
				.convertInfoToTienda(tiendaEndpoint.addTienda(addTiendaRequest).getTiendaServiceStatus().getTienda());
		return ResponseEntity.ok(tienda);
	}

	@GetMapping("/getById")
	public ResponseEntity<Tienda> getTiendaById(@RequestParam Long codigoTienda) {
		GetOneTiendaByCodeRequest getOneTiendaByCodeRequest = new GetOneTiendaByCodeRequest();
		getOneTiendaByCodeRequest.setCodigo(codigoTienda);
		Tienda tienda = tiendaConverter
				.convertInfoToTienda(tiendaEndpoint.GetOneTiendaByCode(getOneTiendaByCodeRequest).getTienda());
		return ResponseEntity.ok(tienda);
	}

	@GetMapping("/getAll")
	public ResponseEntity<List<Tienda>> getAllTiendas() {
		GetAllTiendasRequest getAllTiendasRequest = new GetAllTiendasRequest();
		List<Tienda> tiendas = tiendaConverter
				.convertInfosToTiendas(tiendaEndpoint.getAllTiendas(getAllTiendasRequest).getTiendas());
		return ResponseEntity.ok(tiendas);
	}

	@PostMapping("/update")
	public ResponseEntity<Tienda> updateTienda(@RequestParam Long codigoTienda, @RequestParam String direccion,
			@RequestParam String ciudad, @RequestParam String provincia, @RequestParam boolean habilitada) {
		UpdateTiendaRequest updateTiendaRequest = new UpdateTiendaRequest();
		updateTiendaRequest.setCodigo(codigoTienda);
		updateTiendaRequest.setDireccion(direccion);
		updateTiendaRequest.setCiudad(ciudad);
		updateTiendaRequest.setProvincia(provincia);
		updateTiendaRequest.setHabilitada(habilitada);
		Tienda tienda = tiendaConverter.convertInfoToTienda(
				tiendaEndpoint.updateTienda(updateTiendaRequest).getTiendaServiceStatus().getTienda());
		return ResponseEntity.ok(tienda);
	}

	@GetMapping("/buscar")
	public ResponseEntity<List<Tienda>> buscarTienda(@RequestParam(required = false) Long codigoTienda,
			@RequestParam(required = false) boolean habilitada) {
		BuscarTiendaRequest buscarTiendaRequest = new BuscarTiendaRequest();
		buscarTiendaRequest.setCodigo(codigoTienda);
		buscarTiendaRequest.setHabilitada(habilitada);
		List<Tienda> tiendas = tiendaConverter
				.convertInfosToTiendas(tiendaEndpoint.buscarTienda(buscarTiendaRequest).getTiendas());
		return ResponseEntity.ok(tiendas);
	}
}
