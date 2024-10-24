package com.stockearte.tp3_grupo10.service;

import java.util.List;

import com.stockearte.tp3_grupo10.model.ItemOrdenDeCompra;

public interface ItemOrdenDeCompraService {
	
	ItemOrdenDeCompra add(int cantidad, Long codigoProducto, Long idOrdenDeCompra);

	ItemOrdenDeCompra getOneById(Long id);

	List<ItemOrdenDeCompra> getAll();

	ItemOrdenDeCompra update(ItemOrdenDeCompra itemOrdenDeCompra, Long id);
	
	void delete(Long id);
	
}
