package com.stockearte.tp3_grupo10.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.stockearte.tp3_grupo10.model.Filtro;
import com.stockearte.tp3_grupo10.service.FiltroService;

import java.util.List;

@RestController
@RequestMapping("/api/filtro")
public class FiltroController {

	@Autowired
	private FiltroService filtroService;

	@PostMapping("/add")
	public ResponseEntity<Filtro> addFiltro(@RequestParam String codigoFiltro, @RequestParam Long idUsuario,
			@RequestParam Long codigoTienda, @RequestParam Long codigoProducto) {
		Filtro filtro = filtroService.add(codigoFiltro, idUsuario, codigoTienda, codigoProducto);
		return ResponseEntity.ok(filtro);
	}
	
	@CrossOrigin(origins = "http://localhost:5000")
	@GetMapping("/getByCode")
	public ResponseEntity<Filtro> getFiltroByCode(@RequestParam String codigo) {
		Filtro filtro = filtroService.getOneByCode(codigo);
		return ResponseEntity.ok(filtro);
	}

	@CrossOrigin(origins = "http://localhost:5000")
	@GetMapping("/getById")
	public ResponseEntity<Filtro> getFiltroById(@RequestParam Long id) {
		Filtro filtro = filtroService.getOneById(id);
		return ResponseEntity.ok(filtro);
	}
	
	@CrossOrigin(origins = "http://localhost:5000")
	@GetMapping("/getAll")
	public ResponseEntity<List<Filtro>> getAllFiltros() {
		List<Filtro> filtros = filtroService.getAll();
		return ResponseEntity.ok(filtros);
	}

	@PostMapping("/updateProducto")
	public ResponseEntity<Filtro> updateProducto(@RequestParam String codigoFiltro, @RequestParam Long codigoProducto) {
		Filtro filtro = filtroService.updateProducto(codigoFiltro, codigoProducto);
		return ResponseEntity.ok(filtro);
	}

	@PostMapping("/updateTienda")
	public ResponseEntity<Filtro> updateTienda(@RequestParam String codigoFiltro, @RequestParam Long codigoTienda) {
		Filtro filtro = filtroService.updateTienda(codigoFiltro, codigoTienda);
		return ResponseEntity.ok(filtro);
	}

	@PostMapping("/delete")
	public ResponseEntity<Void> deleteFiltro(@RequestParam String codigo) {
		filtroService.delete(codigo);
		return ResponseEntity.noContent().build();
	}
}