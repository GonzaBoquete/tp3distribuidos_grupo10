package com.stockearte.tp3_grupo10.service;

import java.time.LocalDate;
import java.util.List;

import com.stockearte.tp3_grupo10.enumerators.EstadoOrden;
import com.stockearte.tp3_grupo10.model.Filtro;

public interface FiltroService {

	Filtro add(String codigoFiltro, Long idUsuario, Long codigoTienda, Long codigoProducto, LocalDate fechaDesde,
			LocalDate fechaHasta, EstadoOrden estado);

	Filtro updateFiltro(String codigoFiltro, Long codigoTienda, Long codigoProducto, LocalDate fechaDesde,
			LocalDate fechaHasta, EstadoOrden estado);

	Filtro getOneByCode(String codigo);

	Filtro getOneById(Long id);

	List<Filtro> getAll();

	void delete(String codigo);

}
