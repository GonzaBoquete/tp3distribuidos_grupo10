package com.stockearte.tp3_grupo10.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stockearte.tp3_grupo10.model.Catalogo;

@Repository("catalogoRepository")
public interface CatalogoRepository extends JpaRepository<Catalogo, Long> {
}
