package com.stockearte.tp3_grupo10.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.stockearte.tp3_grupo10.model.Producto;
import com.stockearte.tp3_grupo10.service.ProductoService;

import java.util.List;

@RestController
@RequestMapping("/api/producto")
public class ProductoController {

	@Autowired
	private ProductoService productoService;

	@PostMapping("/add")
	public ResponseEntity<Producto> addProducto(@RequestParam Long codigoProducto, @RequestParam String nombre,
			@RequestParam String talle, @RequestParam String foto, @RequestParam String color) {
		Producto producto = productoService.add(codigoProducto, nombre, talle, foto, color);
		return ResponseEntity.ok(producto);
	}

	@GetMapping("/getById")
	public ResponseEntity<Producto> getProductoById(@RequestParam Long codigoProducto) {
		Producto producto = productoService.getOneById(codigoProducto);
		return ResponseEntity.ok(producto);
	}

	@GetMapping("/getAll")
	public ResponseEntity<List<Producto>> getAllProductos() {
		List<Producto> productos = productoService.getAll();
		return ResponseEntity.ok(productos);
	}

	@PostMapping("/update")
	public ResponseEntity<Producto> updateProducto(@RequestParam Long codigoProducto, @RequestParam String nombre,
			@RequestParam String talle, @RequestParam String foto, @RequestParam String color) {
		Producto producto = productoService.update(codigoProducto, nombre, talle, foto, color);
		return ResponseEntity.ok(producto);
	}

	@GetMapping("/buscar")
	public ResponseEntity<List<Producto>> buscarProducto(@RequestParam(required = false) String nombre,
			@RequestParam(required = false) Long codigoProducto, @RequestParam(required = false) String talle,
			@RequestParam(required = false) String color) {
		List<Producto> productos = productoService.buscarProducto(nombre, codigoProducto, talle, color);
		return ResponseEntity.ok(productos);
	}
}