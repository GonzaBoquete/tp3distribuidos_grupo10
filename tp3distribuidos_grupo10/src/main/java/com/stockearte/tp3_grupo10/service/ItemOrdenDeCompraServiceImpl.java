package com.stockearte.tp3_grupo10.service;

import java.util.List;
import java.util.Optional;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.stockearte.tp3_grupo10.model.ItemOrdenDeCompra;
import com.stockearte.tp3_grupo10.model.OrdenDeCompra;
import com.stockearte.tp3_grupo10.model.Producto;
import com.stockearte.tp3_grupo10.repository.ItemOrdenDeCompraRepository;

@Service("itemOrdenDeCompraService")
public class ItemOrdenDeCompraServiceImpl implements ItemOrdenDeCompraService {

	@Autowired
	@Qualifier("itemOrdenDeCompraRepository")
	private ItemOrdenDeCompraRepository itemOrdenDeCompraRepository;

	@Autowired
	private ProductoService productoService;
	
	@Autowired
	private OrdenDeCompraService ordenDeCompraService;

	@Override
	public ItemOrdenDeCompra add(int cantidad, Long codigoProducto, Long idOrdenDeCompra) {
		ItemOrdenDeCompra itemOrdenDeCompra = new ItemOrdenDeCompra();
		itemOrdenDeCompra.setCantidad(cantidad);
		Producto producto = getProductoService().getOneById(codigoProducto);
		if (producto != null) {
			itemOrdenDeCompra.setProducto(producto);
		} else {
			throw new ServiceException("No se encontro el producto");
		}
		OrdenDeCompra ordenDeCompra = getOrdenDeCompraService().getOneById(idOrdenDeCompra);
		if (ordenDeCompra != null) {
			itemOrdenDeCompra.setOrdenDeCompra(ordenDeCompra);
		} else {
			throw new ServiceException("No se encontro la orden de compra");
		}
		return itemOrdenDeCompraRepository.save(itemOrdenDeCompra);
	}

	@Override
	public ItemOrdenDeCompra update(ItemOrdenDeCompra itemOrdenDeCompra, Long id) {
		Optional<ItemOrdenDeCompra> foundItemOrdenDeCompra = itemOrdenDeCompraRepository.findById(id);
		if (!foundItemOrdenDeCompra.isEmpty()) {
			foundItemOrdenDeCompra.get().setCantidad(itemOrdenDeCompra.getCantidad());
			foundItemOrdenDeCompra.get().setProducto(itemOrdenDeCompra.getProducto());
			foundItemOrdenDeCompra.get().setOrdenDeCompra(itemOrdenDeCompra.getOrdenDeCompra());
			return itemOrdenDeCompraRepository.save(foundItemOrdenDeCompra.get());
		} else {
			throw new ServiceException("No se encontro el item de orden de compra");
		}
	}

	@Override
	public void delete(Long id) {
		Optional<ItemOrdenDeCompra> foundItemOrdenDeCompra = itemOrdenDeCompraRepository.findById(id);
		if (!foundItemOrdenDeCompra.isEmpty()) {
			itemOrdenDeCompraRepository.delete(foundItemOrdenDeCompra.get());
		} else {
			throw new ServiceException("No se encontro el item de orden de compra");
		}
	}

	@Override
	public ItemOrdenDeCompra getOneById(Long id) {
		Optional<ItemOrdenDeCompra> itemOrdenDeCompra = itemOrdenDeCompraRepository.findById(id);
		return itemOrdenDeCompra.isEmpty() ? null : itemOrdenDeCompra.get();
	}

	@Override
	public List<ItemOrdenDeCompra> getAll() {
		return (List<ItemOrdenDeCompra>) itemOrdenDeCompraRepository.findAll();
	}

	public ProductoService getProductoService() {
		return productoService;
	}

	public void setProductoService(ProductoService productoService) {
		this.productoService = productoService;
	}

	public OrdenDeCompraService getOrdenDeCompraService() {
		return ordenDeCompraService;
	}

	public void setOrdenDeCompraService(OrdenDeCompraService ordenDeCompraService) {
		this.ordenDeCompraService = ordenDeCompraService;
	}

}
