package com.stockearte.tp3_grupo10.service;

import java.util.List;

import com.stockearte.tp3_grupo10.model.Tienda;

public interface TiendaService {

	Tienda add(Long codigoTienda, String direccion, String ciudad, String provincia, boolean habilitada);

	Tienda getOneById(Long codigoTienda);

	List<Tienda> getAll();

	Tienda update(Long codigoTienda, String direccion, String ciudad, String provincia, boolean habilitada);
	
	List<Tienda> buscarTienda(Long codigoTienda, boolean habilitada);

}
