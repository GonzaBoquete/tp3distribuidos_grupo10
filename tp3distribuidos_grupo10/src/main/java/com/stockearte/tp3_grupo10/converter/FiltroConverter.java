package com.stockearte.tp3_grupo10.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.stockearte.tp3_grupo10.model.Filtro;
import com.stockearte.tp3_grupo10.model.Producto;
import com.stockearte.tp3_grupo10.model.Tienda;
import com.stockearte.tp3_grupo10.model.Usuario;
import com.stockearte.tp3_grupo10.service.ProductoService;
import com.stockearte.tp3_grupo10.service.TiendaService;
import com.stockearte.tp3_grupo10.service.UsuarioService;
import com.stockearte.tp3_grupo10.soap.interfaces.FiltroInfo;

@Component
public class FiltroConverter {

	@Autowired
	private ProductoService productoService;

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private TiendaService tiendaService;

	// Convertir de Filtro a FiltroInfo
	public FiltroInfo convertFiltroToFiltroInfo(Filtro filtro) {
		FiltroInfo filtroInfo = new FiltroInfo();
		filtroInfo.setId(filtro.getId());
		filtroInfo.setNombre(filtro.getNombre());

		if (filtro.getUsuario() != null) {
			filtroInfo.setIdUsuario(filtro.getUsuario().getId());
		}
		if (filtro.getTienda() != null) {
			filtroInfo.setCodigoTienda(filtro.getTienda().getCodigo());
		}
		if (filtro.getProducto() != null) {
			filtroInfo.setCodigoProducto(filtro.getProducto().getCodigo());
		}

		return filtroInfo;
	}

	// Convertir de FiltroInfo a Filtro
	public Filtro convertFiltroInfoToFiltro(FiltroInfo filtroInfo) {
		Filtro filtro = new Filtro();
		filtro.setNombre(filtroInfo.getNombre());
		filtro.setUsuario(this.getUsuarioService().getOneById(filtroInfo.getIdUsuario()));
		filtro.setTienda(this.getTiendaService().getOneById(filtroInfo.getCodigoTienda()));
		filtro.setProducto(this.getProductoService().getOneById(filtroInfo.getCodigoProducto()));

		return filtro;
	}

	// Convertir lista de Filtro a lista de FiltroInfo
	public List<FiltroInfo> convertFiltrosToFiltroInfos(List<Filtro> filtros) {
		List<FiltroInfo> filtroInfos = new ArrayList<>();
		for (Filtro filtro : filtros) {
			filtroInfos.add(convertFiltroToFiltroInfo(filtro));
		}
		return filtroInfos;
	}

	// Convertir lista de FiltroInfo a lista de Filtro
	public List<Filtro> convertFiltroInfosToFiltros(List<FiltroInfo> filtroInfos) {
		List<Filtro> filtros = new ArrayList<>();
		for (FiltroInfo filtroInfo : filtroInfos) {
			filtros.add(convertFiltroInfoToFiltro(filtroInfo));
		}
		return filtros;
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

	public TiendaService getTiendaService() {
		return tiendaService;
	}

	public void setTiendaService(TiendaService tiendaService) {
		this.tiendaService = tiendaService;
	}
}
