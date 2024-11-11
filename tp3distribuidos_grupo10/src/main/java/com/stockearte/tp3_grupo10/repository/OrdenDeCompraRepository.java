package com.stockearte.tp3_grupo10.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.stockearte.tp3_grupo10.model.OrdenDeCompra;

@Repository("ordenDeCompraRepository")
public interface OrdenDeCompraRepository
		extends JpaRepository<OrdenDeCompra, Long>, JpaSpecificationExecutor<OrdenDeCompra> {
}
