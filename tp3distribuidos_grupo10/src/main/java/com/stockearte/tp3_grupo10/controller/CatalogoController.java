package com.stockearte.tp3_grupo10.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.stockearte.tp3_grupo10.model.Catalogo;
import com.stockearte.tp3_grupo10.service.CatalogoService;

import java.util.List;

@RestController
@RequestMapping("/api/catalogo")
public class CatalogoController {

	@Autowired
	private CatalogoService catalogoService;

	@PostMapping("/add")
	public ResponseEntity<Catalogo> addCatalogo(@RequestParam Long idTienda) {
		Catalogo catalogo = catalogoService.add(idTienda);
		return ResponseEntity.ok(catalogo);
	}

	@GetMapping("/getById")
	public ResponseEntity<Catalogo> getCatalogoById(@RequestParam Long idCatalogo) {
		Catalogo catalogo = catalogoService.getOneById(idCatalogo);
		return ResponseEntity.ok(catalogo);
	}

	@GetMapping("/getAll")
	public ResponseEntity<List<Catalogo>> getAllCatalogos() {
		List<Catalogo> catalogos = catalogoService.getAll();
		return ResponseEntity.ok(catalogos);
	}

	@PostMapping("/updateTienda")
	public ResponseEntity<Catalogo> updateTienda(@RequestParam Long idCatalogo, @RequestParam Long codigoTienda) {
		Catalogo catalogo = catalogoService.updateTienda(idCatalogo, codigoTienda);
		return ResponseEntity.ok(catalogo);
	}

	@PostMapping("/delete")
	public ResponseEntity<Void> deleteCatalogo(@RequestParam Long idCatalogo) {
		catalogoService.delete(idCatalogo);
		return ResponseEntity.noContent().build();
	}

	@PostMapping("/agregarProducto")
	public ResponseEntity<Catalogo> agregarProducto(@RequestParam Long codigoCatalogo,
			@RequestParam Long codigoProducto) {
		Catalogo catalogo = catalogoService.agregarProducto(codigoCatalogo, codigoProducto);
		return ResponseEntity.ok(catalogo);
	}

	@PostMapping("/eliminarProducto")
	public ResponseEntity<Catalogo> eliminarProducto(@RequestParam Long codigoCatalogo,
			@RequestParam Long codigoProducto) {
		Catalogo catalogo = catalogoService.eliminarProducto(codigoCatalogo, codigoProducto);
		return ResponseEntity.ok(catalogo);
	}
}