package com.stockearte.tp3_grupo10.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.stockearte.tp3_grupo10.model.Catalogo;
import com.stockearte.tp3_grupo10.model.Producto;
import com.stockearte.tp3_grupo10.model.Tienda;
import com.stockearte.tp3_grupo10.soap.interfaces.CatalogoInfo;

@Component
public class CatalogoConverter {

	// Convertir de Catalogo a CatalogoInfo
		public CatalogoInfo convertCatalogoToCatalogoInfo(Catalogo catalogo) {
			CatalogoInfo catalogoInfo = new CatalogoInfo();
			catalogoInfo.setIdCatalogo(catalogo.getId());

			if (catalogo.getTienda() != null) {
				catalogoInfo.setIdTienda(catalogo.getTienda().getCodigo());
			}

			// Inicializamos la lista de productos en CatalogoInfo y añadimos cada elemento
			// uno a uno
			for (Producto producto : catalogo.getProducto()) {
				CatalogoInfo.Productos productoInfo = new CatalogoInfo.Productos();
				productoInfo.setProductoCodigo(String.valueOf(producto.getCodigo())); // Convertimos el código a String si
																						// es necesario
				catalogoInfo.getProductos().add(productoInfo);
			}

			return catalogoInfo;
		}

		// Convertir de CatalogoInfo a Catalogo
		public Catalogo convertCatalogoInfoToCatalogo(CatalogoInfo catalogoInfo, Tienda tienda, List<Producto> productos) {
			Catalogo catalogo = new Catalogo();
			catalogo.setTienda(tienda);
			catalogo.setProducto(productos); // Asignamos directamente la lista de productos existente en el sistema
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
		public List<Catalogo> convertCatalogoInfosToCatalogos(List<CatalogoInfo> catalogoInfos, List<Tienda> tiendas,
				List<Producto> productos) {
			List<Catalogo> catalogos = new ArrayList<>();
			for (CatalogoInfo catalogoInfo : catalogoInfos) {
				Tienda tienda = tiendas.stream().filter(t -> t.getCodigo() == catalogoInfo.getIdTienda()).findFirst()
						.orElse(null);

				catalogos.add(convertCatalogoInfoToCatalogo(catalogoInfo, tienda, productos));
			}
			return catalogos;
		}
	}
