package com.stockearte.tp3_grupo10.service;

import java.util.List;

import com.stockearte.tp3_grupo10.model.Producto;

public interface ProductoService {

	Producto add(Long codigoProducto, String nombre, String talle, String foto, String color);

	Producto getOneById(Long codigoProducto);

	List<Producto> getAll();

	Producto update(Long codigoProducto, String nombre, String talle, String foto, String color);
	
	List<Producto> buscarProducto(String nombre, Long codigoProducto, String talle, String color);
}
