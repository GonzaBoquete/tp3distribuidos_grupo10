package com.stockearte.tp3_grupo10.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.stockearte.tp3_grupo10.model.Catalogo;
import com.stockearte.tp3_grupo10.model.Producto;
import com.stockearte.tp3_grupo10.service.ProductoService;
import com.stockearte.tp3_grupo10.service.TiendaService;
import com.stockearte.tp3_grupo10.soap.interfaces.CatalogoInfo;
import com.stockearte.tp3_grupo10.soap.interfaces.CatalogoInfo.Productos;

@Component
public class CatalogoConverter {

	@Autowired
	private ProductoService productoService;

	@Autowired
	private TiendaService tiendaService;

	// Convertir de Catalogo a CatalogoInfo
	public CatalogoInfo convertCatalogoToCatalogoInfo(Catalogo catalogo) {
		CatalogoInfo catalogoInfo = new CatalogoInfo();
		if (catalogo != null) {
			catalogoInfo.setIdCatalogo(catalogo.getId());
			if (catalogo.getTienda() != null) {
				catalogoInfo.setIdTienda(catalogo.getTienda().getCodigo());
			}
			if (catalogo.getProducto() != null) {
				for (Producto producto : catalogo.getProducto()) {
					CatalogoInfo.Productos productoInfo = new CatalogoInfo.Productos();
					productoInfo.setProductoCodigo(String.valueOf(producto.getCodigo()));
					catalogoInfo.getProductos().add(productoInfo);
				}
			}
		}
		return catalogoInfo;
	}

	// Convertir de CatalogoInfo a Catalogo
	public Catalogo convertCatalogoInfoToCatalogo(CatalogoInfo catalogoInfo) {
		Catalogo catalogo = new Catalogo();
		if (catalogoInfo != null) {
			catalogo.setId(catalogoInfo.getIdCatalogo());
			catalogo.setTienda(this.getTiendaService().getOneById(catalogoInfo.getIdTienda()));
			if (catalogoInfo.getProductos() != null) {
				for (Productos producto : catalogoInfo.getProductos()) {
					catalogo.getProducto()
							.add(this.getProductoService().getOneById(Long.parseLong(producto.getProductoCodigo())));
				}
			}
		}
		return catalogo;
	}

	// Convertir lista de Catalogo a lista de CatalogoInfo
	public List<CatalogoInfo> convertCatalogosToCatalogoInfos(List<Catalogo> catalogos) {
		List<CatalogoInfo> catalogoInfos = new ArrayList<>();
		for (Catalogo catalogo : catalogos) {
			catalogoInfos.add(convertCatalogoToCatalogoInfo(catalogo));
		}
		return catalogoInfos;
	}

	// Convertir lista de CatalogoInfo a lista de Catalogo
	public List<Catalogo> convertCatalogoInfosToCatalogos(List<CatalogoInfo> catalogoInfos) {
		List<Catalogo> catalogos = new ArrayList<>();
		for (CatalogoInfo catalogoInfo : catalogoInfos) {
			catalogos.add(convertCatalogoInfoToCatalogo(catalogoInfo));
		}
		return catalogos;
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
}
