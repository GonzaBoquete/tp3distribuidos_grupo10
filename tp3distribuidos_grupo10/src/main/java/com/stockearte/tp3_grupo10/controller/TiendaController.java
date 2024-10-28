package com.stockearte.tp3_grupo10.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.stockearte.tp3_grupo10.model.Tienda;
import com.stockearte.tp3_grupo10.service.TiendaService;

import java.util.List;

@RestController
@RequestMapping("/api/tienda")
public class TiendaController {

	@Autowired
	private TiendaService tiendaService;

	@PostMapping("/add")
	public ResponseEntity<Tienda> addTienda(@RequestParam Long codigoTienda, @RequestParam String direccion,
			@RequestParam String ciudad, @RequestParam String provincia, @RequestParam boolean habilitada) {
		Tienda tienda = tiendaService.add(codigoTienda, direccion, ciudad, provincia, habilitada);
		return ResponseEntity.ok(tienda);
	}

	@CrossOrigin(origins = "http://localhost:5000")
	@GetMapping("/getById")
	public ResponseEntity<Tienda> getTiendaById(@RequestParam Long codigoTienda) {
		Tienda tienda = tiendaService.getOneById(codigoTienda);
		return ResponseEntity.ok(tienda);
	}

	@CrossOrigin(origins = "http://localhost:5000")
	@GetMapping("/getAll")
	public ResponseEntity<List<Tienda>> getAllTiendas() {
		List<Tienda> tiendas = tiendaService.getAll();
		return ResponseEntity.ok(tiendas);
	}

	@PostMapping("/update")
	public ResponseEntity<Tienda> updateTienda(@RequestParam Long codigoTienda, @RequestParam String direccion,
			@RequestParam String ciudad, @RequestParam String provincia, @RequestParam boolean habilitada) {
		Tienda tienda = tiendaService.update(codigoTienda, direccion, ciudad, provincia, habilitada);
		return ResponseEntity.ok(tienda);
	}
	
	@CrossOrigin(origins = "http://localhost:5000")
	@GetMapping("/buscar")
	public ResponseEntity<List<Tienda>> buscarTienda(@RequestParam(required = false) Long codigoTienda,
			@RequestParam(required = false) boolean habilitada) {
		List<Tienda> tiendas = tiendaService.buscarTienda(codigoTienda, habilitada);
		return ResponseEntity.ok(tiendas);
	}
}
