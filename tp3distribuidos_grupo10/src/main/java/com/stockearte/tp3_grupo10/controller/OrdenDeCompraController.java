package com.stockearte.tp3_grupo10.controller;

import java.util.List;

import javax.xml.datatype.DatatypeConfigurationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.stockearte.tp3_grupo10.converter.OrdenDeCompraConverter;
import com.stockearte.tp3_grupo10.model.OrdenDeCompra;
import com.stockearte.tp3_grupo10.soap.endpoint.OrdenDeCompraEndpoint;
import com.stockearte.tp3_grupo10.soap.interfaces.AddOrdenDeCompraRequest;
import com.stockearte.tp3_grupo10.soap.interfaces.AgregarItemOrdenDeCompraRequest;
import com.stockearte.tp3_grupo10.soap.interfaces.DeleteOrdenDeCompraRequest;
import com.stockearte.tp3_grupo10.soap.interfaces.EliminarItemOrdenDeCompraRequest;
import com.stockearte.tp3_grupo10.soap.interfaces.GetAllOrdenesDeCompraRequest;
import com.stockearte.tp3_grupo10.soap.interfaces.GetOneOrdenDeCompraByIdRequest;
import com.stockearte.tp3_grupo10.soap.interfaces.GetOrdenesDeCompraByFilterRequest;

@RestController
@RequestMapping("/api/ordenDeCompra")
public class OrdenDeCompraController {

	@Autowired
	private OrdenDeCompraEndpoint ordenDeCompraEndpoint;

	@Autowired
	private OrdenDeCompraConverter ordenDeCompraConverter;

	@PostMapping("/add")
	public ResponseEntity<OrdenDeCompra> addOrdenDeCompra(@RequestParam Long codigoTienda)
			throws DatatypeConfigurationException {
		AddOrdenDeCompraRequest addOrdenDeCompraRequest = new AddOrdenDeCompraRequest();
		addOrdenDeCompraRequest.setCodigoTienda(codigoTienda);
		OrdenDeCompra ordenDeCompra = ordenDeCompraConverter.convertInfoToOrdenDeCompra(ordenDeCompraEndpoint
				.addOrdenDeCompra(addOrdenDeCompraRequest).getOrdenDeCompraServiceStatus().getOrdenDeCompra());
		return ResponseEntity.ok(ordenDeCompra);
	}

	@PostMapping("/agregarItem")
	public ResponseEntity<OrdenDeCompra> agregarItemOrdenDeCompra(@RequestParam Long idOrdenDeCompra,
			@RequestParam int cantidad, @RequestParam Long codigoProducto) throws DatatypeConfigurationException {
		AgregarItemOrdenDeCompraRequest agregarItemOrdenDeCompraRequest = new AgregarItemOrdenDeCompraRequest();
		agregarItemOrdenDeCompraRequest.setId(idOrdenDeCompra);
		agregarItemOrdenDeCompraRequest.setCantidad(cantidad);
		agregarItemOrdenDeCompraRequest.setCodigoProducto(codigoProducto);
		OrdenDeCompra ordenDeCompra = ordenDeCompraConverter.convertInfoToOrdenDeCompra(
				ordenDeCompraEndpoint.agregarItemOrdenDeCompra(agregarItemOrdenDeCompraRequest)
						.getOrdenDeCompraServiceStatus().getOrdenDeCompra());
		return ResponseEntity.ok(ordenDeCompra);
	}

	@PostMapping("/eliminarItem")
	public ResponseEntity<OrdenDeCompra> eliminarItemOrdenDeCompra(@RequestParam Long idOrdenDeCompra,
			@RequestParam Long idItemOrdenDeCompra) throws DatatypeConfigurationException {
		EliminarItemOrdenDeCompraRequest eliminarItemOrdenDeCompraRequest = new EliminarItemOrdenDeCompraRequest();
		eliminarItemOrdenDeCompraRequest.setId(idOrdenDeCompra);
		eliminarItemOrdenDeCompraRequest.setIdItemOrdenDeCompra(idItemOrdenDeCompra);
		OrdenDeCompra ordenDeCompra = ordenDeCompraConverter.convertInfoToOrdenDeCompra(
				ordenDeCompraEndpoint.eliminarItemOrdenDeCompra(eliminarItemOrdenDeCompraRequest)
						.getOrdenDeCompraServiceStatus().getOrdenDeCompra());
		return ResponseEntity.ok(ordenDeCompra);
	}

	@GetMapping("/getById")
	public ResponseEntity<OrdenDeCompra> getOrdenDeCompraById(@RequestParam Long idOrdenDeCompra)
			throws DatatypeConfigurationException {
		GetOneOrdenDeCompraByIdRequest getOneOrdenDeCompraByIdRequest = new GetOneOrdenDeCompraByIdRequest();
		getOneOrdenDeCompraByIdRequest.setId(idOrdenDeCompra);
		OrdenDeCompra ordenDeCompra = ordenDeCompraConverter.convertInfoToOrdenDeCompra(
				ordenDeCompraEndpoint.GetOneOrdenDeCompraById(getOneOrdenDeCompraByIdRequest).getOrdenDeCompra());
		return ResponseEntity.ok(ordenDeCompra);
	}

	@GetMapping("/getAll")
	public ResponseEntity<List<OrdenDeCompra>> getAllOrdenesDeCompra() throws DatatypeConfigurationException {
		GetAllOrdenesDeCompraRequest getAllOrdenesDeCompraRequest = new GetAllOrdenesDeCompraRequest();
		List<OrdenDeCompra> ordenesDeCompra = ordenDeCompraConverter.convertInfoListToOrdenesDeCompra(
				ordenDeCompraEndpoint.getAllOrdenesDeCompra(getAllOrdenesDeCompraRequest).getOrdenesDeCompra());
		return ResponseEntity.ok(ordenesDeCompra);
	}

	@GetMapping("/getByFilter")
	public ResponseEntity<List<OrdenDeCompra>> getOrdenesDeCompraByFilter(@RequestParam String nombreFiltro)
			throws DatatypeConfigurationException {
		GetOrdenesDeCompraByFilterRequest getOrdenesDeCompraByFilterRequest = new GetOrdenesDeCompraByFilterRequest();
		getOrdenesDeCompraByFilterRequest.setNombreFiltro(nombreFiltro);
		List<OrdenDeCompra> ordenesDeCompra = ordenDeCompraConverter
				.convertInfoListToOrdenesDeCompra(ordenDeCompraEndpoint
						.getOrdenesDeCompraByFilter(getOrdenesDeCompraByFilterRequest).getOrdenesDeCompra());
		return ResponseEntity.ok(ordenesDeCompra);
	}

	@PostMapping("/delete")
	public ResponseEntity<OrdenDeCompra> deleteOrdenDeCompra(@RequestParam Long idOrdenDeCompra)
			throws DatatypeConfigurationException {
		DeleteOrdenDeCompraRequest deleteOrdenDeCompraRequest = new DeleteOrdenDeCompraRequest();
		deleteOrdenDeCompraRequest.setId(idOrdenDeCompra);
		OrdenDeCompra ordenDeCompra = ordenDeCompraConverter.convertInfoToOrdenDeCompra(ordenDeCompraEndpoint
				.deleteOrdenDeCompra(deleteOrdenDeCompraRequest).getOrdenDeCompraServiceStatus().getOrdenDeCompra());
		return ResponseEntity.ok(ordenDeCompra);
	}
}