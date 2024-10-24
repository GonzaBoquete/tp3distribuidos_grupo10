package com.stockearte.tp3_grupo10.service;

import java.util.List;

import com.stockearte.tp3_grupo10.model.Filtro;

public interface FiltroService {
	
	Filtro add(String codigoFiltro, Long idUsuario, Long codigoTienda, Long codigoProducto);

	Filtro getOneByCode(String codigo);
	
	Filtro getOneById(Long id);

	List<Filtro> getAll();
	
	Filtro updateProducto(String codigoFiltro, Long codigoProducto);
	
	Filtro updateTienda(String codigoFiltro, Long codigoTienda);
	
	void delete(String codigo);
	
}
