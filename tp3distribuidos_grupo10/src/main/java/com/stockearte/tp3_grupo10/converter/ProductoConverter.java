package com.stockearte.tp3_grupo10.converter;

import org.springframework.stereotype.Component;

import com.stockearte.tp3_grupo10.model.Producto;
import com.stockearte.tp3_grupo10.soap.interfaces.ProductoInfo;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductoConverter {

	public ProductoInfo convertProductoToProductoInfo(Producto producto) {
		ProductoInfo productoInfo = new ProductoInfo();
		productoInfo.setCodigo(producto.getCodigo());
		productoInfo.setNombre(producto.getNombre());
		productoInfo.setTalle(producto.getTalle());
		productoInfo.setFoto(producto.getFoto());
		productoInfo.setColor(producto.getColor());
		return productoInfo;
	}

	public Producto convertProductoInfoToProducto(ProductoInfo productoInfo) {
		Producto producto = new Producto();
		producto.setCodigo(productoInfo.getCodigo());
		producto.setNombre(productoInfo.getNombre());
		producto.setTalle(productoInfo.getTalle());
		producto.setFoto(productoInfo.getFoto());
		producto.setColor(productoInfo.getColor());
		return producto;
	}

	public List<ProductoInfo> convertProductosToProductoInfos(List<Producto> productos) {
		List<ProductoInfo> productoInfos = new ArrayList<>();
		for (Producto producto : productos) {
			productoInfos.add(convertProductoToProductoInfo(producto));
		}
		return productoInfos;
	}

	public List<Producto> convertProductoInfosToProductos(List<ProductoInfo> productoInfos) {
		List<Producto> productos = new ArrayList<>();
		for (ProductoInfo productoInfo : productoInfos) {
			productos.add(convertProductoInfoToProducto(productoInfo));
		}
		return productos;
	}
}
