package com.stockearte.tp3_grupo10.service;

import java.util.List;

import com.stockearte.tp3_grupo10.model.Catalogo;

public interface CatalogoService {
	
	Catalogo add(Catalogo catalogo, Long idTienda);

	Catalogo getOneById(Long id);

	List<Catalogo> getAll();

	Catalogo update(Catalogo catalogo, Long id);
	
	void delete(Long id);
	
}
