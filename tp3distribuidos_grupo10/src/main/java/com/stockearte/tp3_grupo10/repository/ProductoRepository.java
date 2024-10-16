package com.stockearte.tp3_grupo10.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stockearte.tp3_grupo10.model.Producto;

@Repository("productoRepository")
public interface ProductoRepository extends JpaRepository<Producto, Long> {
	List<Producto> findByNombreContainingAndCodigoAndTalleAndColor(String nombre, Long codigo, String talle, String color);

}
