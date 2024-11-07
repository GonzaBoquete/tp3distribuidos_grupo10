package com.stockearte.tp3_grupo10.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.stockearte.tp3_grupo10.model.Tienda;

@Repository("tiendaRepository")
public interface TiendaRepository extends JpaRepository<Tienda, Long>, JpaSpecificationExecutor<Tienda> {
}
