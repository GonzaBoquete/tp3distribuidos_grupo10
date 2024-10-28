package com.stockearte.tp3_grupo10.converter;

import org.springframework.stereotype.Component;

import com.stockearte.tp3_grupo10.model.Filtro;
import com.stockearte.tp3_grupo10.model.Producto;
import com.stockearte.tp3_grupo10.model.Tienda;
import com.stockearte.tp3_grupo10.model.Usuario;
import com.stockearte.tp3_grupo10.soap.interfaces.FiltroInfo;

import java.util.ArrayList;
import java.util.List;

@Component
public class FiltroConverter {

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
	public Filtro convertFiltroInfoToFiltro(FiltroInfo filtroInfo, Usuario usuario, Tienda tienda, Producto producto) {
		Filtro filtro = new Filtro();
		filtro.setNombre(filtroInfo.getNombre());
		filtro.setUsuario(usuario);
		filtro.setTienda(tienda);
		filtro.setProducto(producto);

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
	public List<Filtro> convertFiltroInfosToFiltros(List<FiltroInfo> filtroInfos, List<Usuario> usuarios,
			List<Tienda> tiendas, List<Producto> productos) {
		List<Filtro> filtros = new ArrayList<>();
		for (FiltroInfo filtroInfo : filtroInfos) {
			Usuario usuario = usuarios.stream().filter(u -> u.getId() == filtroInfo.getIdUsuario()).findFirst()
					.orElse(null);

			Tienda tienda = tiendas.stream().filter(t -> t.getCodigo() == filtroInfo.getCodigoTienda()).findFirst()
					.orElse(null);

			Producto producto = productos.stream().filter(p -> p.getCodigo() == filtroInfo.getCodigoProducto())
					.findFirst().orElse(null);

			filtros.add(convertFiltroInfoToFiltro(filtroInfo, usuario, tienda, producto));
		}
		return filtros;
	}
}
