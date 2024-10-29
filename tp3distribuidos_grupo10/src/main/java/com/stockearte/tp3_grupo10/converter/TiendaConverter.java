package com.stockearte.tp3_grupo10.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.stockearte.tp3_grupo10.model.Tienda;
import com.stockearte.tp3_grupo10.soap.interfaces.TiendaInfo;

@Component
public class TiendaConverter {

	// Convertir de Tienda a TiendaInfo
	public TiendaInfo convertTiendaToInfo(Tienda tienda) {
		TiendaInfo info = new TiendaInfo();
		info.setCodigo(tienda.getCodigo());
		info.setDireccion(tienda.getDireccion());
		info.setCiudad(tienda.getCiudad());
		info.setProvincia(tienda.getProvincia());
		info.setHabilitada(tienda.isHabilitada());
		return info;
	}

	// Convertir de TiendaInfo a Tienda
	public Tienda convertInfoToTienda(TiendaInfo info) {
		Tienda tienda = new Tienda();
		tienda.setCodigo(info.getCodigo());
		tienda.setDireccion(info.getDireccion());
		tienda.setCiudad(info.getCiudad());
		tienda.setProvincia(info.getProvincia());
		tienda.setHabilitada(info.isHabilitada());
		return tienda;
	}

	// Convertir lista de Tienda a lista de TiendaInfo
	public List<TiendaInfo> convertTiendasToInfos(List<Tienda> tiendas) {
		List<TiendaInfo> infoList = new ArrayList<>();
		for (Tienda tienda : tiendas) {
			infoList.add(convertTiendaToInfo(tienda));
		}
		return infoList;
	}

	// Convertir lista de TiendaInfo a lista de Tienda
	public List<Tienda> convertInfosToTiendas(List<TiendaInfo> infoList) {
		List<Tienda> tiendas = new ArrayList<>();
		for (TiendaInfo info : infoList) {
			tiendas.add(convertInfoToTienda(info));
		}
		return tiendas;
	}
}
