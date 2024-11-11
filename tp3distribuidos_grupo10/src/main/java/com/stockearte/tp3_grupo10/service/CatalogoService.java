package com.stockearte.tp3_grupo10.service;

import java.util.List;

import com.stockearte.tp3_grupo10.model.Catalogo;

public interface CatalogoService {
	
	Catalogo add(Long idTienda);

	Catalogo getOneById(Long idCatalogo);

	List<Catalogo> getAll();

	Catalogo updateTienda(Long idCatalogo, Long codigoTienda);
	
	void delete(Long idCatalogo);
	
	Catalogo agregarProducto(Long codigoCatalogo, Long codigoProducto);
	
	Catalogo eliminarProducto(Long codigoCatalogo, Long codigoProducto);
	
	byte[] exportCatalogosToPdf();
	
	
}
