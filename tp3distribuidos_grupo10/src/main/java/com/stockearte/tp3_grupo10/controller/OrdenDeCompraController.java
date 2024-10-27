package com.stockearte.tp3_grupo10.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.stockearte.tp3_grupo10.model.OrdenDeCompra;
import com.stockearte.tp3_grupo10.service.OrdenDeCompraService;

import java.util.List;

@RestController
@RequestMapping("/api/ordenDeCompra")
public class OrdenDeCompraController {

	@Autowired
	private OrdenDeCompraService ordenDeCompraService;

	@PostMapping("/add")
	public ResponseEntity<OrdenDeCompra> addOrdenDeCompra(@RequestParam Long codigoTienda) {
		OrdenDeCompra ordenDeCompra = ordenDeCompraService.add(codigoTienda);
		return ResponseEntity.ok(ordenDeCompra);
	}

	@PostMapping("/agregarItem")
	public ResponseEntity<OrdenDeCompra> agregarItemOrdenDeCompra(@RequestParam Long idOrdenDeCompra,
			@RequestParam int cantidad, @RequestParam Long codigoProducto) {
		OrdenDeCompra ordenDeCompra = ordenDeCompraService.agregarItemOrdenDeCompra(idOrdenDeCompra, cantidad,
				codigoProducto);
		return ResponseEntity.ok(ordenDeCompra);
	}

	@PostMapping("/eliminarItem")
	public ResponseEntity<OrdenDeCompra> eliminarItemOrdenDeCompra(@RequestParam Long idOrdenDeCompra,
			@RequestParam Long idItemOrdenDeCompra) {
		OrdenDeCompra ordenDeCompra = ordenDeCompraService.eliminarItemOrdenDeCompra(idOrdenDeCompra,
				idItemOrdenDeCompra);
		return ResponseEntity.ok(ordenDeCompra);
	}

	@GetMapping("/getById")
	public ResponseEntity<OrdenDeCompra> getOrdenDeCompraById(@RequestParam Long idOrdenDeCompra) {
		OrdenDeCompra ordenDeCompra = ordenDeCompraService.getOneById(idOrdenDeCompra);
		return ResponseEntity.ok(ordenDeCompra);
	}

	@GetMapping("/getAll")
	public ResponseEntity<List<OrdenDeCompra>> getAllOrdenesDeCompra() {
		List<OrdenDeCompra> ordenesDeCompra = ordenDeCompraService.getAll();
		return ResponseEntity.ok(ordenesDeCompra);
	}

	@PostMapping("/delete")
	public ResponseEntity<Void> deleteOrdenDeCompra(@RequestParam Long idOrdenDeCompra) {
		ordenDeCompraService.delete(idOrdenDeCompra);
		return ResponseEntity.noContent().build();
	}
}