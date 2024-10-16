package com.stockearte.tp3_grupo10.service;

import java.util.List;

import com.stockearte.tp3_grupo10.model.Tienda;

public interface TiendaService {

	Tienda add(Tienda tienda);

	Tienda getOneById(Long codigo);

	List<Tienda> getAll();

	Tienda update(Tienda tienda, Long codigo);
	
	List<Tienda> buscarTienda(Long codigo, boolean habilitada);

}
