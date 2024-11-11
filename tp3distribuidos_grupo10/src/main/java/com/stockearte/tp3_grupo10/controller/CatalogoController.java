package com.stockearte.tp3_grupo10.controller;

import java.util.List;
import org.springframework.http.MediaType;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;


import javax.xml.datatype.DatatypeConfigurationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.stockearte.tp3_grupo10.converter.CatalogoConverter;
import com.stockearte.tp3_grupo10.model.Catalogo;
import com.stockearte.tp3_grupo10.service.CatalogoService;
import com.stockearte.tp3_grupo10.soap.endpoint.CatalogoEndpoint;
import com.stockearte.tp3_grupo10.soap.interfaces.AddCatalogoRequest;
import com.stockearte.tp3_grupo10.soap.interfaces.AgregarProductoACatalogoRequest;
import com.stockearte.tp3_grupo10.soap.interfaces.DeleteCatalogoRequest;
import com.stockearte.tp3_grupo10.soap.interfaces.EliminarProductoDeCatalogoRequest;
import com.stockearte.tp3_grupo10.soap.interfaces.GetAllCatalogosRequest;
import com.stockearte.tp3_grupo10.soap.interfaces.GetOneCatalogoByIdRequest;
import com.stockearte.tp3_grupo10.soap.interfaces.UpdateTiendaDeCatalogoRequest;


@RestController
@RequestMapping("/api/catalogo")
public class CatalogoController {

	@Autowired
	private CatalogoEndpoint catalogoEndpoint;

	@Autowired
	private CatalogoConverter catalogoConverter;

	@Autowired
	private CatalogoService catalogoService;

	@PostMapping("/add")
	public ResponseEntity<Catalogo> addCatalogo(@RequestParam Long idTienda) throws DatatypeConfigurationException {
		AddCatalogoRequest addCatalogoRequest = new AddCatalogoRequest();
		addCatalogoRequest.setIdTienda(idTienda);
		Catalogo catalogo = catalogoConverter
				.convertCatalogoInfoToCatalogo(catalogoEndpoint.addCatalogo(addCatalogoRequest).getCatalogo());
		return ResponseEntity.ok(catalogo);
	}

	@GetMapping("/getById")
	public ResponseEntity<Catalogo> getCatalogoById(@RequestParam Long idCatalogo)
			throws DatatypeConfigurationException {
		GetOneCatalogoByIdRequest getOneCatalogoByCodeRequest = new GetOneCatalogoByIdRequest();
		getOneCatalogoByCodeRequest.setIdCatalogo(idCatalogo);
		Catalogo catalogo = catalogoConverter.convertCatalogoInfoToCatalogo(
				catalogoEndpoint.GetOneCatalogoByCode(getOneCatalogoByCodeRequest).getCatalogo());
		return ResponseEntity.ok(catalogo);
	}

	@GetMapping("/getAll")
	public ResponseEntity<List<Catalogo>> getAllCatalogos() throws DatatypeConfigurationException {
		GetAllCatalogosRequest getAllCatalogosRequest = new GetAllCatalogosRequest();
		List<Catalogo> catalogos = catalogoConverter.convertCatalogoInfosToCatalogos(
				catalogoEndpoint.getAllCatalogos(getAllCatalogosRequest).getCatalogos());
		return ResponseEntity.ok(catalogos);
	}

	@PostMapping("/updateTienda")
	public ResponseEntity<Catalogo> updateTienda(@RequestParam Long idCatalogo, @RequestParam Long codigoTienda) {
		UpdateTiendaDeCatalogoRequest updateTiendaDeCatalogoRequest = new UpdateTiendaDeCatalogoRequest();
		updateTiendaDeCatalogoRequest.setIdCatalogo(idCatalogo);
		updateTiendaDeCatalogoRequest.setCodigoTienda(codigoTienda);
		Catalogo catalogo = catalogoConverter.convertCatalogoInfoToCatalogo(catalogoEndpoint
				.updateTiendaDeCatalogo(updateTiendaDeCatalogoRequest).getCatalogoServiceStatus().getCatalogo());
		return ResponseEntity.ok(catalogo);
	}

	@PostMapping("/delete")
	public ResponseEntity<Catalogo> deleteCatalogo(@RequestParam Long idCatalogo) {
		DeleteCatalogoRequest deleteCatalogoRequest = new DeleteCatalogoRequest();
		deleteCatalogoRequest.setIdCatalogo(idCatalogo);
		Catalogo catalogo = catalogoConverter.convertCatalogoInfoToCatalogo(
				catalogoEndpoint.deleteCatalogo(deleteCatalogoRequest).getCatalogoServiceStatus().getCatalogo());
		return ResponseEntity.ok(catalogo);
	}

	@PostMapping("/agregarProducto")
	public ResponseEntity<Catalogo> agregarProducto(@RequestParam Long codigoCatalogo,
			@RequestParam Long codigoProducto) {
		AgregarProductoACatalogoRequest agregarProductoACatalogoRequest = new AgregarProductoACatalogoRequest();
		agregarProductoACatalogoRequest.setCodigoCatalogo(codigoCatalogo);
		agregarProductoACatalogoRequest.setCodigoProducto(codigoProducto);
		Catalogo catalogo = catalogoConverter.convertCatalogoInfoToCatalogo(catalogoEndpoint
				.agregarProductoACatalogo(agregarProductoACatalogoRequest).getCatalogoServiceStatus().getCatalogo());
		return ResponseEntity.ok(catalogo);
	}

	@PostMapping("/eliminarProducto")
	public ResponseEntity<Catalogo> eliminarProducto(@RequestParam Long codigoCatalogo,
			@RequestParam Long codigoProducto) {
		EliminarProductoDeCatalogoRequest eliminarProductoDeCatalogoRequest = new EliminarProductoDeCatalogoRequest();
		eliminarProductoDeCatalogoRequest.setCodigoCatalogo(codigoCatalogo);
		eliminarProductoDeCatalogoRequest.setCodigoProducto(codigoProducto);
		Catalogo catalogo = catalogoConverter.convertCatalogoInfoToCatalogo(
				catalogoEndpoint.eliminarProductoDeCatalogo(eliminarProductoDeCatalogoRequest)
						.getCatalogoServiceStatus().getCatalogo());
		return ResponseEntity.ok(catalogo);
	}

	@GetMapping("/exportToPdf")
	public ResponseEntity<byte[]> exportToPdf() {
		byte[] pdfContent = catalogoService.exportCatalogosToPdf();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_PDF);
		headers.setContentDispositionFormData("filename", "catalogos.pdf");

		return ResponseEntity.ok().headers(headers).body(pdfContent);
	}
}