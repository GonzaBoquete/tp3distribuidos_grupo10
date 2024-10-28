package com.stockearte.tp3_grupo10.converter;

import org.springframework.stereotype.Component;

import com.stockearte.tp3_grupo10.enumerators.EstadoOrden;
import com.stockearte.tp3_grupo10.model.ItemOrdenDeCompra;
import com.stockearte.tp3_grupo10.model.OrdenDeCompra;
import com.stockearte.tp3_grupo10.model.Tienda;
import com.stockearte.tp3_grupo10.soap.interfaces.EstadoOrdenInfo;
import com.stockearte.tp3_grupo10.soap.interfaces.OrdenDeCompraInfo;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

@Component
public class OrdenDeCompraConverter {

	// Convertir de OrdenDeCompra a ordenDeCompraInfo
	public OrdenDeCompraInfo convertOrdenDeCompraToInfo(OrdenDeCompra ordenDeCompra)
			throws DatatypeConfigurationException {
		OrdenDeCompraInfo info = new OrdenDeCompraInfo();
		info.setId(ordenDeCompra.getId());
		GregorianCalendar gcal = GregorianCalendar.from(ordenDeCompra.getFecha().atStartOfDay(ZoneId.systemDefault()));
		XMLGregorianCalendar xcal = DatatypeFactory.newInstance().newXMLGregorianCalendar(gcal);
		info.setFecha(xcal);

		// Convertir el estado de la orden
		if (ordenDeCompra.getEstado() != null) {
			info.setEstado(EstadoOrdenInfo.valueOf(ordenDeCompra.getEstado().name()));
		}

		// Establecer idTienda
		if (ordenDeCompra.getTienda() != null) {
			info.setIdTienda(ordenDeCompra.getTienda().getCodigo());
		}

		// Convertir itemsOrdenCompra
		if (ordenDeCompra.getItemsOrdenCompra() != null) {
			for (ItemOrdenDeCompra item : ordenDeCompra.getItemsOrdenCompra()) {
				OrdenDeCompraInfo.ItemsOrdenCompra itemInfo = new OrdenDeCompraInfo.ItemsOrdenCompra();
				itemInfo.setItemOrdenDeCompraId(item.getId());
				info.getItemsOrdenCompra().add(itemInfo);
			}
		}

		return info;
	}

	// Convertir de ordenDeCompraInfo a OrdenDeCompra
	public OrdenDeCompra convertInfoToOrdenDeCompra(OrdenDeCompraInfo info, Tienda tienda,
			List<ItemOrdenDeCompra> items) throws DatatypeConfigurationException {
		OrdenDeCompra ordenDeCompra = new OrdenDeCompra();
		ordenDeCompra.setId(info.getId());
		GregorianCalendar gcal = GregorianCalendar.from(ordenDeCompra.getFecha().atStartOfDay(ZoneId.systemDefault()));
		XMLGregorianCalendar xcal = DatatypeFactory.newInstance().newXMLGregorianCalendar(gcal);
		ordenDeCompra.setFecha(xcal.toGregorianCalendar().toZonedDateTime().toLocalDate());

		// Establecer estado
		if (info.getEstado() != null) {
			ordenDeCompra.setEstado(EstadoOrden.valueOf(info.getEstado().toString()));
		}

		// Establecer tienda
		ordenDeCompra.setTienda(tienda);

		// Establecer itemsOrdenCompra
		if (items != null) {
			ordenDeCompra.setItemsOrdenCompra(items);
		}

		return ordenDeCompra;
	}

	// Convertir lista de OrdenDeCompra a lista de ordenDeCompraInfo
	public List<OrdenDeCompraInfo> convertOrdenesDeCompraToInfoList(List<OrdenDeCompra> ordenes)
			throws DatatypeConfigurationException {
		List<OrdenDeCompraInfo> infoList = new ArrayList<>();
		for (OrdenDeCompra orden : ordenes) {
			infoList.add(convertOrdenDeCompraToInfo(orden));
		}
		return infoList;
	}

	// Convertir lista de ordenDeCompraInfo a lista de OrdenDeCompra
	public List<OrdenDeCompra> convertInfoListToOrdenesDeCompra(List<OrdenDeCompraInfo> infoList, Tienda tienda,
			List<ItemOrdenDeCompra> items) throws DatatypeConfigurationException {
		List<OrdenDeCompra> ordenes = new ArrayList<>();
		for (OrdenDeCompraInfo info : infoList) {
			ordenes.add(convertInfoToOrdenDeCompra(info, tienda, items));
		}
		return ordenes;
	}
}
