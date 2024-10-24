package com.stockearte.tp3_grupo10.service;

import java.util.List;
import java.util.Optional;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.stockearte.tp3_grupo10.model.Catalogo;
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
	public Catalogo add(Catalogo catalogo, Long idTienda) {
		Tienda tienda = getTiendaService().getOneById(idTienda);
		if (tienda != null) {
			catalogo.setTienda(tienda);
		} else {
			throw new ServiceException("No se encontro la tienda");
		}
		return catalogoRepository.save(catalogo);
	}

	@Override
	public Catalogo update(Catalogo catalogo, Long id) {
		Optional<Catalogo> foundCatalogo = catalogoRepository.findById(id);
		if (!foundCatalogo.isEmpty()) {
			foundCatalogo.get().setTienda(catalogo.getTienda());
			foundCatalogo.get().setProducto(catalogo.getProducto());
			return catalogoRepository.save(foundCatalogo.get());
		}
		return null;
	}
	
	@Override
	public void delete(Long id) {
		Optional<Catalogo> foundCatalogo = catalogoRepository.findById(id);
		if (!foundCatalogo.isEmpty()) {
			catalogoRepository.delete(foundCatalogo.get());
		}
	}
	
	@Override
	public Catalogo getOneById(Long id) {
		Optional<Catalogo> catalogo = catalogoRepository.findById(id);
		return catalogo.isEmpty() ? null : catalogo.get();
	}	
	
	@Override
	public List<Catalogo> getAll() {
		return (List<Catalogo>) catalogoRepository.findAll();
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

}
