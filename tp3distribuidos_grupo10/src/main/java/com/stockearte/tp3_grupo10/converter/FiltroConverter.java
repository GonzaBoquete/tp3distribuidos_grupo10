package com.stockearte.tp3_grupo10.converter;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.stockearte.tp3_grupo10.enumerators.EstadoOrden;
import com.stockearte.tp3_grupo10.model.Filtro;
import com.stockearte.tp3_grupo10.service.ProductoService;
import com.stockearte.tp3_grupo10.service.TiendaService;
import com.stockearte.tp3_grupo10.service.UsuarioService;
import com.stockearte.tp3_grupo10.soap.interfaces.EstadoOrdenInfoFilter;
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
	public FiltroInfo convertFiltroToFiltroInfo(Filtro filtro) throws DatatypeConfigurationException {
		FiltroInfo filtroInfo = new FiltroInfo();
		if (filtro != null) {
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
			// Casteo fechas
			GregorianCalendar gcal1 = GregorianCalendar
					.from(filtro.getFechaDesde().atStartOfDay(ZoneId.systemDefault()));
			XMLGregorianCalendar xcal1 = DatatypeFactory.newInstance().newXMLGregorianCalendar(gcal1);
			filtroInfo.setFechaDesde(xcal1);

			GregorianCalendar gcal2 = GregorianCalendar
					.from(filtro.getFechaHasta().atStartOfDay(ZoneId.systemDefault()));
			XMLGregorianCalendar xcal2 = DatatypeFactory.newInstance().newXMLGregorianCalendar(gcal2);
			filtroInfo.setFechaHasta(xcal2);

			// Convertir el estado de la orden
			if (filtro.getEstado() != null) {
				filtroInfo.setEstado(EstadoOrdenInfoFilter.valueOf(filtro.getEstado().name()));
			}
		}
		return filtroInfo;
	}

	// Convertir de FiltroInfo a Filtro
	public Filtro convertFiltroInfoToFiltro(FiltroInfo filtroInfo) {
		Filtro filtro = new Filtro();
		if (filtroInfo != null) {
			filtro.setId(filtroInfo.getId());
			filtro.setNombre(filtroInfo.getNombre());
			filtro.setUsuario(this.getUsuarioService().getOneById(filtroInfo.getIdUsuario()));
			filtro.setTienda(this.getTiendaService().getOneById(filtroInfo.getCodigoTienda()));
			filtro.setProducto(this.getProductoService().getOneById(filtroInfo.getCodigoProducto()));
			// Casteo fechas
			XMLGregorianCalendar xmlGregorianCalendar1 = filtroInfo.getFechaDesde();
			filtro.setFechaDesde(xmlGregorianCalendar1.toGregorianCalendar().toZonedDateTime().toLocalDate());
			XMLGregorianCalendar xmlGregorianCalendar2 = filtroInfo.getFechaHasta();
			filtro.setFechaHasta(xmlGregorianCalendar2.toGregorianCalendar().toZonedDateTime().toLocalDate());

			// Establecer estado
			if (filtroInfo.getEstado() != null) {
				filtro.setEstado(EstadoOrden.valueOf(filtroInfo.getEstado().toString()));
			}
		}
		return filtro;
	}

	// Convertir lista de Filtro a lista de FiltroInfo
	public List<FiltroInfo> convertFiltrosToFiltroInfos(List<Filtro> filtros) throws DatatypeConfigurationException {
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
