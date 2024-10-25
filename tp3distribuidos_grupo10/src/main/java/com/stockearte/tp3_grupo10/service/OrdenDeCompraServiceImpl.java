package com.stockearte.tp3_grupo10.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.stockearte.tp3_grupo10.enumerators.EstadoOrden;
import com.stockearte.tp3_grupo10.model.ItemOrdenDeCompra;
import com.stockearte.tp3_grupo10.model.OrdenDeCompra;
import com.stockearte.tp3_grupo10.model.Producto;
import com.stockearte.tp3_grupo10.model.Tienda;
import com.stockearte.tp3_grupo10.repository.ItemOrdenDeCompraRepository;
import com.stockearte.tp3_grupo10.repository.OrdenDeCompraRepository;

@Service("ordenDeCompraService")
public class OrdenDeCompraServiceImpl implements OrdenDeCompraService {

	@Autowired
	@Qualifier("ordenDeCompraRepository")
	private OrdenDeCompraRepository ordenDeCompraRepository;

	@Autowired
	@Qualifier("itemOrdenDeCompraRepository")
	private ItemOrdenDeCompraRepository itemOrdenDeCompraRepository;

	@Autowired
	private ProductoService productoService;

	@Autowired
	private TiendaService tiendaService;

	@Override
	public OrdenDeCompra add(Long codigoTienda) {
		OrdenDeCompra ordenDeCompra = new OrdenDeCompra();
		ordenDeCompra.setFecha(LocalDate.now());
		ordenDeCompra.setEstado(EstadoOrden.SOLICITADA);
		Tienda tienda = getTiendaService().getOneById(codigoTienda);
		if (tienda != null) {
			ordenDeCompra.setTienda(tienda);
		} else {
			throw new ServiceException("No se encontro la tienda");
		}
		return getOrdenDeCompraRepository().save(ordenDeCompra);
	}

	@Override
	public OrdenDeCompra agregarItemOrdenDeCompra(Long idOrdenDeCompra, int cantidad, Long codigoProducto) {
		Optional<OrdenDeCompra> foundOrdenDeCompra = getOrdenDeCompraRepository().findById(idOrdenDeCompra);
		if (!foundOrdenDeCompra.isEmpty()) {
			Producto producto = getProductoService().getOneById(codigoProducto);
			if (producto == null) {
				throw new ServiceException("No se encontro la tienda");
			}
			foundOrdenDeCompra.get().getItemsOrdenCompra()
					.add(new ItemOrdenDeCompra(cantidad, producto, foundOrdenDeCompra.get()));
			return getOrdenDeCompraRepository().save(foundOrdenDeCompra.get());
		} else {
			throw new ServiceException("No se encontro la orden de compra");
		}
	}

	@Override
	public OrdenDeCompra eliminarItemOrdenDeCompra(Long idOrdenDeCompra, Long idItemOrdenDeCompra) {
		Optional<OrdenDeCompra> foundOrdenDeCompra = getOrdenDeCompraRepository().findById(idOrdenDeCompra);
		if (!foundOrdenDeCompra.isEmpty()) {
			foundOrdenDeCompra.get().getItemsOrdenCompra()
					.remove(getItemOrdenDeCompraRepository().getReferenceById(idItemOrdenDeCompra));
			return getOrdenDeCompraRepository().save(foundOrdenDeCompra.get());
		} else {
			throw new ServiceException("No se encontro la orden de compra");
		}
	}

	@Override
	public void delete(Long idOrdenDeCompra) {
		Optional<OrdenDeCompra> foundOrdenDeCompra = getOrdenDeCompraRepository().findById(idOrdenDeCompra);
		if (!foundOrdenDeCompra.isEmpty()) {
			getOrdenDeCompraRepository().delete(foundOrdenDeCompra.get());
		} else {
			throw new ServiceException("No se encontro la orden de compra");
		}
	}

	@Override
	public OrdenDeCompra getOneById(Long idOrdenDeCompra) {
		Optional<OrdenDeCompra> ordenDeCompra = getOrdenDeCompraRepository().findById(idOrdenDeCompra);
		return ordenDeCompra.isEmpty() ? null : ordenDeCompra.get();
	}

	@Override
	public List<OrdenDeCompra> getAll() {
		return (List<OrdenDeCompra>) getOrdenDeCompraRepository().findAll();
	}

	public ProductoService getProductoService() {
		return productoService;
	}

	public void setProductoService(ProductoService productoService) {
		this.productoService = productoService;
	}

	public TiendaService getTiendaService() {
		return tiendaService;
	}

	public void setTiendaService(TiendaService tiendaService) {
		this.tiendaService = tiendaService;
	}

	public OrdenDeCompraRepository getOrdenDeCompraRepository() {
		return ordenDeCompraRepository;
	}

	public void setOrdenDeCompraRepository(OrdenDeCompraRepository ordenDeCompraRepository) {
		this.ordenDeCompraRepository = ordenDeCompraRepository;
	}

	public ItemOrdenDeCompraRepository getItemOrdenDeCompraRepository() {
		return itemOrdenDeCompraRepository;
	}

	public void setItemOrdenDeCompraRepository(ItemOrdenDeCompraRepository itemOrdenDeCompraRepository) {
		this.itemOrdenDeCompraRepository = itemOrdenDeCompraRepository;
	}

}
