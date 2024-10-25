package com.stockearte.tp3_grupo10.service;

import java.util.List;

import com.stockearte.tp3_grupo10.model.OrdenDeCompra;

public interface OrdenDeCompraService {
	
	OrdenDeCompra add(Long codigoTienda);
	
	OrdenDeCompra agregarItemOrdenDeCompra(Long idOrdenDeCompra, int cantidad, Long codigoProducto);
	
	OrdenDeCompra eliminarItemOrdenDeCompra(Long idOrdenDeCompra, Long idItemOrdenDeCompra);

	OrdenDeCompra getOneById(Long idOrdenDeCompra);

	List<OrdenDeCompra> getAll();
	
	void delete(Long idOrdenDeCompra);
	
}
