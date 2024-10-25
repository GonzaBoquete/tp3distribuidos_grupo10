package com.stockearte.tp3_grupo10.service;

import java.util.List;
import java.util.Optional;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.stockearte.tp3_grupo10.model.Catalogo;
import com.stockearte.tp3_grupo10.model.Producto;
import com.stockearte.tp3_grupo10.model.Tienda;
import com.stockearte.tp3_grupo10.repository.CatalogoRepository;

@Service("catalogoService")
public class CatalogoServiceImpl implements CatalogoService {

	@Autowired
	@Qualifier("catalogoRepository")
	private CatalogoRepository catalogoRepository;

	@Autowired
	private TiendaService tiendaService;

	@Autowired
	private ProductoService productoService;

	@Override
	public Catalogo add(Long idTienda) {
		Tienda tienda = getTiendaService().getOneById(idTienda);
		if (tienda != null) {
			Catalogo catalogo = new Catalogo();
			catalogo.setTienda(tienda);
			return getCatalogoRepository().save(catalogo);
		} else {
			throw new ServiceException("No se encontro la tienda");
		}
	}

	@Override
	public Catalogo updateTienda(Long idCatalogo, Long codigoTienda) {
		Catalogo catalogo = this.getOneById(idCatalogo);
		if (catalogo != null) {
			Tienda tienda = getTiendaService().getOneById(codigoTienda);
			if (tienda != null) {
				catalogo.setTienda(tienda);
				return getCatalogoRepository().save(catalogo);
			} else {
				throw new ServiceException("No se encontro la tienda");
			}
		} else {
			throw new ServiceException("No se encontro el catalogo");
		}
	}

	@Override
	public Catalogo agregarProducto(Long codigoCatalogo, Long codigoProducto) {
		Catalogo catalogo = this.getOneById(codigoCatalogo);
		if (catalogo != null) {
			Producto producto = getProductoService().getOneById(codigoProducto);
			if (producto == null) {
				throw new ServiceException("No se encontro el producto");
			}
			catalogo.getProducto().add(producto);
			return getCatalogoRepository().save(catalogo);
		} else {
			throw new ServiceException("No se encontro el catalogo");
		}
	}

	@Override
	public Catalogo eliminarProducto(Long codigoCatalogo, Long codigoProducto) {
		Catalogo catalogo = this.getOneById(codigoCatalogo);
		if (catalogo != null) {
			Producto producto = getProductoService().getOneById(codigoProducto);
			if (producto == null) {
				throw new ServiceException("No se encontro el producto");
			}
			catalogo.getProducto().remove(producto);
			return getCatalogoRepository().save(catalogo);
		} else {
			throw new ServiceException("No se encontro el catalogo");
		}
	}

	@Override
	public void delete(Long idCatalogo) {
		Optional<Catalogo> foundCatalogo = getCatalogoRepository().findById(idCatalogo);
		if (!foundCatalogo.isEmpty()) {
			getCatalogoRepository().delete(foundCatalogo.get());
		}
	}

	@Override
	public Catalogo getOneById(Long idCatalogo) {
		Optional<Catalogo> catalogo = getCatalogoRepository().findById(idCatalogo);
		return catalogo.isEmpty() ? null : catalogo.get();
	}

	@Override
	public List<Catalogo> getAll() {
		return (List<Catalogo>) getCatalogoRepository().findAll();
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

	public CatalogoRepository getCatalogoRepository() {
		return catalogoRepository;
	}

	public void setCatalogoRepository(CatalogoRepository catalogoRepository) {
		this.catalogoRepository = catalogoRepository;
	}

}
