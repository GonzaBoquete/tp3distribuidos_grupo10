package com.stockearte.tp3_grupo10.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.stockearte.tp3_grupo10.enumerators.EstadoOrden;
import com.stockearte.tp3_grupo10.model.Filtro;
import com.stockearte.tp3_grupo10.model.Producto;
import com.stockearte.tp3_grupo10.model.Tienda;
import com.stockearte.tp3_grupo10.model.Usuario;
import com.stockearte.tp3_grupo10.repository.FiltroRepository;

@Service("filtroService")
public class FiltroServiceImpl implements FiltroService {

	@Autowired
	@Qualifier("filtroRepository")
	private FiltroRepository filtroRepository;

	@Autowired
	private TiendaService tiendaService;

	@Autowired
	private ProductoService productoService;

	@Autowired
	private UsuarioService usuarioService;

	@Override
	public Filtro add(String codigoFiltro, Long idUsuario, Long codigoTienda, Long codigoProducto, LocalDate fechaDesde,
			LocalDate fechaHasta, EstadoOrden estado) {
		Filtro filtro = new Filtro();
		if (this.getOneByCode(codigoFiltro) != null) {
			throw new ServiceException("Ya existe un filtro con ese nombre");
		} else {
			filtro.setNombre(codigoFiltro);
		}
		Usuario usuario = getUsuarioService().getOneById(codigoTienda);
		if (usuario != null) {
			filtro.setUsuario(usuario);
		} else {
			throw new ServiceException("No se encontro el usuario");
		}
		Tienda tienda = getTiendaService().getOneById(codigoTienda);
		if (tienda != null) {
			filtro.setTienda(tienda);
		} else {
			throw new ServiceException("No se encontro la tienda");
		}
		Producto producto = getProductoService().getOneById(codigoProducto);
		if (producto != null) {
			filtro.setProducto(producto);
		} else {
			throw new ServiceException("No se encontro el producto");
		}
		filtro.setFechaDesde(fechaDesde);
		filtro.setFechaHasta(fechaHasta);
		filtro.setEstado(estado);
		return filtroRepository.save(filtro);
	}

	@Override
	public Filtro updateFiltro(String codigoFiltro, Long codigoTienda, Long codigoProducto,
			LocalDate fechaDesde, LocalDate fechaHasta, EstadoOrden estado) {
		Filtro foundFiltro = this.getOneByCode(codigoFiltro);
		if (foundFiltro != null) {
			Tienda tienda = getTiendaService().getOneById(codigoTienda);
			if (tienda != null) {
				foundFiltro.setTienda(tienda);
			} else {
				throw new ServiceException("No se encontro la tienda");
			}
			Producto producto = getProductoService().getOneById(codigoProducto);
			if (producto != null) {
				foundFiltro.setProducto(producto);
			} else {
				throw new ServiceException("No se encontro el producto");
			}
			foundFiltro.setFechaDesde(fechaDesde);
			foundFiltro.setFechaHasta(fechaHasta);
			foundFiltro.setEstado(estado);
			return filtroRepository.save(foundFiltro);
		} else {
			throw new ServiceException("No se encontro el filtro");
		}
	}

	@Override
	public void delete(String codigoFiltro) {
		Filtro foundFiltro = this.getOneByCode(codigoFiltro);
		if (foundFiltro != null) {
			filtroRepository.delete(foundFiltro);
		} else {
			throw new ServiceException("No se encontro el filtro");
		}
	}

	@Override
	public Filtro getOneByCode(String codigo) {
		List<Filtro> filtros = this.getAll();
		Filtro foundFiltro = null;
		for (Filtro persistedFiltro : filtros) {
			if (persistedFiltro.getNombre().compareToIgnoreCase(codigo) == 0) {
				foundFiltro = persistedFiltro;
			}
		}
		return foundFiltro;
	}

	@Override
	public Filtro getOneById(Long id) {
		Optional<Filtro> filtro = filtroRepository.findById(id);
		return filtro.isEmpty() ? null : filtro.get();
	}

	@Override
	public List<Filtro> getAll() {
		return (List<Filtro>) filtroRepository.findAll();
	}

	public TiendaService getTiendaService() {
		return tiendaService;
	}

	public void setTiendaService(TiendaService tiendaService) {
		this.tiendaService = tiendaService;
	}

	public ProductoService getProductoService() {
		return productoService;
	}

	public void setProductoService(ProductoService productoService) {
		this.productoService = productoService;
	}

	public UsuarioService getUsuarioService() {
		return usuarioService;
	}

	public void setUsuarioService(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

}
