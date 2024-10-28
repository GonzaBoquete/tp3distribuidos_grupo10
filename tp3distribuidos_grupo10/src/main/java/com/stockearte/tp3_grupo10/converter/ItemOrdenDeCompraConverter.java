package com.stockearte.tp3_grupo10.converter;

import org.springframework.stereotype.Component;

import com.stockearte.tp3_grupo10.model.ItemOrdenDeCompra;
import com.stockearte.tp3_grupo10.model.OrdenDeCompra;
import com.stockearte.tp3_grupo10.model.Producto;
import com.stockearte.tp3_grupo10.soap.interfaces.ItemOrdenDeCompraInfo;

import java.util.ArrayList;
import java.util.List;

@Component
public class ItemOrdenDeCompraConverter {

	// Convertir de ItemOrdenDeCompra a ItemOrdenDeCompraInfo
	public ItemOrdenDeCompraInfo convertItemOrdenDeCompraToInfo(ItemOrdenDeCompra item) {
		ItemOrdenDeCompraInfo itemInfo = new ItemOrdenDeCompraInfo();
		itemInfo.setId(item.getId());
		itemInfo.setCantidad(item.getCantidad());

		if (item.getProducto() != null) {
			itemInfo.setProductoCodigo(item.getProducto().getCodigo());
		}
		if (item.getOrdenDeCompra() != null) {
			itemInfo.setOrdenDeCompraId(item.getOrdenDeCompra().getId());
		}

		return itemInfo;
	}

	// Convertir de ItemOrdenDeCompraInfo a ItemOrdenDeCompra
	public ItemOrdenDeCompra convertInfoToItemOrdenDeCompra(ItemOrdenDeCompraInfo itemInfo, Producto producto,
			OrdenDeCompra ordenDeCompra) {
		ItemOrdenDeCompra item = new ItemOrdenDeCompra();
		item.setCantidad(itemInfo.getCantidad());
		item.setProducto(producto);
		item.setOrdenDeCompra(ordenDeCompra);

		return item;
	}

	// Convertir lista de ItemOrdenDeCompra a lista de ItemOrdenDeCompraInfo
	public List<ItemOrdenDeCompraInfo> convertItemsToInfoList(List<ItemOrdenDeCompra> items) {
		List<ItemOrdenDeCompraInfo> itemInfos = new ArrayList<>();
		for (ItemOrdenDeCompra item : items) {
			itemInfos.add(convertItemOrdenDeCompraToInfo(item));
		}
		return itemInfos;
	}

	// Convertir lista de ItemOrdenDeCompraInfo a lista de ItemOrdenDeCompra
	public List<ItemOrdenDeCompra> convertInfoListToItems(List<ItemOrdenDeCompraInfo> itemInfos,
			List<Producto> productos, List<OrdenDeCompra> ordenes) {
		List<ItemOrdenDeCompra> items = new ArrayList<>();
		for (ItemOrdenDeCompraInfo itemInfo : itemInfos) {
			Producto producto = productos.stream().filter(p -> p.getCodigo() == itemInfo.getProductoCodigo())
					.findFirst().orElse(null);

			OrdenDeCompra ordenDeCompra = ordenes.stream().filter(o -> o.getId() == itemInfo.getOrdenDeCompraId())
					.findFirst().orElse(null);

			items.add(convertInfoToItemOrdenDeCompra(itemInfo, producto, ordenDeCompra));
		}
		return items;
	}
}
