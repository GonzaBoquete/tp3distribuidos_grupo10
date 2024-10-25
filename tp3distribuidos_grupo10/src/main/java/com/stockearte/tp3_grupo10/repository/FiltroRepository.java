package com.stockearte.tp3_grupo10.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stockearte.tp3_grupo10.model.Filtro;


@Repository("filtroRepository")
public interface FiltroRepository extends JpaRepository<Filtro, Long> {
}
