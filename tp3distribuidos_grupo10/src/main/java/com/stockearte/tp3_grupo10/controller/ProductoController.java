package com.stockearte.tp3_grupo10.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.stockearte.tp3_grupo10.converter.ProductoConverter;
import com.stockearte.tp3_grupo10.model.Producto;
import com.stockearte.tp3_grupo10.service.ProductoService;
import com.stockearte.tp3_grupo10.soap.endpoint.ProductoEndpoint;
import com.stockearte.tp3_grupo10.soap.interfaces.AddProductoRequest;
import com.stockearte.tp3_grupo10.soap.interfaces.GetAllProductosRequest;
import com.stockearte.tp3_grupo10.soap.interfaces.GetOneProductoByCodeRequest;

import java.util.List;

@RestController
@RequestMapping("/api/producto")
public class ProductoController {

	@Autowired
	private ProductoService productoService;

	@Autowired
	private ProductoEndpoint productoEndpoint;

	@Autowired
	private ProductoConverter productoConverter;

	@PostMapping("/add")
	public ResponseEntity<Producto> addProducto(@RequestParam Long codigoProducto, @RequestParam String nombre,
			@RequestParam String talle, @RequestParam String foto, @RequestParam String color) {
		AddProductoRequest addProductoRequest = new AddProductoRequest();
		addProductoRequest.setProducto(productoConverter
				.convertProductoToProductoInfo(new Producto(codigoProducto, nombre, talle, foto, color)));
		Producto producto = productoConverter
				.convertProductoInfoToProducto(productoEndpoint.addProducto(addProductoRequest).getProducto());
		return ResponseEntity.ok(producto);
	}

	@GetMapping("/getById")
	public ResponseEntity<Producto> getProductoById(@RequestParam Long codigoProducto) {
		GetOneProductoByCodeRequest getOneProductoByCodeRequest = new GetOneProductoByCodeRequest();
		getOneProductoByCodeRequest.setCodigo(codigoProducto);
		Producto producto = productoConverter.convertProductoInfoToProducto(
				productoEndpoint.GetOneProductoByCode(getOneProductoByCodeRequest).getProducto());
		return ResponseEntity.ok(producto);
	}

	@GetMapping("/getAll")
	public ResponseEntity<List<Producto>> getAllProductos() {
		GetAllProductosRequest getAllProductosRequest = new GetAllProductosRequest();
		List<Producto> productos = productoConverter.convertProductoInfosToProductos(
				productoEndpoint.getAllProductos(getAllProductosRequest).getProductos());
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