package com.stockearte.tp3_grupo10.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.stockearte.tp3_grupo10.enumerators.EstadoOrden;
import com.stockearte.tp3_grupo10.model.OrdenDeCompra;
import com.stockearte.tp3_grupo10.model.Tienda;
import com.stockearte.tp3_grupo10.repository.OrdenDeCompraRepository;

@Service("ordenDeCompraService")
public class OrdenDeCompraServiceImpl implements OrdenDeCompraService {

	@Autowired
	@Qualifier("ordenDeCompraRepository")
	private OrdenDeCompraRepository ordenDeCompraRepository;

	@Autowired
	private ProductoService productoService;

	@Autowired
	private TiendaService tiendaService;

	@Autowired
	private ItemOrdenDeCompraService itemOrdenDeCompraService;

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
		return ordenDeCompraRepository.save(ordenDeCompra);
	}

	@Override
	public OrdenDeCompra agregarItemOrdenDeCompra(Long idOrdenDeCompra, int cantidad, Long codigoProducto) {
		Optional<OrdenDeCompra> foundOrdenDeCompra = ordenDeCompraRepository.findById(idOrdenDeCompra);
		if (!foundOrdenDeCompra.isEmpty()) {
			foundOrdenDeCompra.get().getItemsOrdenCompra()
					.add(getItemOrdenDeCompraService().add(cantidad, codigoProducto, idOrdenDeCompra));
			return ordenDeCompraRepository.save(foundOrdenDeCompra.get());
		} else {
			throw new ServiceException("No se encontro la orden de compra");
		}
	}

	@Override
	public OrdenDeCompra eliminarItemOrdenDeCompra(Long idOrdenDeCompra, Long idItemOrdenDeCompra) {
		Optional<OrdenDeCompra> foundOrdenDeCompra = ordenDeCompraRepository.findById(idOrdenDeCompra);
		if (!foundOrdenDeCompra.isEmpty()) {
			foundOrdenDeCompra.get().getItemsOrdenCompra()
					.remove(getItemOrdenDeCompraService().getOneById(idItemOrdenDeCompra));
			getItemOrdenDeCompraService().delete(idItemOrdenDeCompra);
			return ordenDeCompraRepository.save(foundOrdenDeCompra.get());
		} else {
			throw new ServiceException("No se encontro la orden de compra");
		}
	}

	@Override
	public void delete(Long id) {
		Optional<OrdenDeCompra> foundOrdenDeCompra = ordenDeCompraRepository.findById(id);
		if (!foundOrdenDeCompra.isEmpty()) {
			ordenDeCompraRepository.delete(foundOrdenDeCompra.get());
		} else {
			throw new ServiceException("No se encontro la orden de compra");
		}
	}

	@Override
	public OrdenDeCompra getOneById(Long id) {
		Optional<OrdenDeCompra> ordenDeCompra = ordenDeCompraRepository.findById(id);
		return ordenDeCompra.isEmpty() ? null : ordenDeCompra.get();
	}

	@Override
	public List<OrdenDeCompra> getAll() {
		return (List<OrdenDeCompra>) ordenDeCompraRepository.findAll();
	}

	public ProductoService getProductoService() {
		return productoService;
	}

	public void setProductoService(ProductoService productoService) {
		this.productoService = productoService;
	}

	public ItemOrdenDeCompraService getItemOrdenDeCompraService() {
		return itemOrdenDeCompraService;
	}

	public void setItemOrdenDeCompraService(ItemOrdenDeCompraService itemOrdenDeCompraService) {
		this.itemOrdenDeCompraService = itemOrdenDeCompraService;
	}

	public TiendaService getTiendaService() {
		return tiendaService;
	}

	public void setTiendaService(TiendaService tiendaService) {
		this.tiendaService = tiendaService;
	}

}
