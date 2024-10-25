package com.stockearte.tp3_grupo10.service;

import java.util.List;
import java.util.Optional;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.stockearte.tp3_grupo10.model.Producto;
import com.stockearte.tp3_grupo10.repository.ProductoRepository;

@Service("productoService")
public class ProductoServiceImpl implements ProductoService {

	@Autowired
	@Qualifier("productoRepository")
	private ProductoRepository productoRepository;
	
	@Override
	public Producto add(Long codigoProducto, String nombre, String talle, String foto, String color) {
		Producto producto = this.getOneById(codigoProducto);
		if (producto != null) {
			throw new ServiceException("Ya existe un producto con ese codigo");
		} else {
			producto = new Producto();
			producto.setCodigo(codigoProducto);
			producto.setNombre(nombre);
			producto.setTalle(talle);
			producto.setFoto(foto);
			producto.setColor(color);
			return productoRepository.save(producto);
		}
	}

	@Override
	public Producto getOneById(Long codigo) {
		Optional<Producto> producto = productoRepository.findById(codigo);
		return producto.isEmpty() ? null : producto.get();
	}

	@Override
	public List<Producto> getAll() {
		return (List<Producto>) productoRepository.findAll();
	}

	@Override
	public Producto update(Long codigoProducto, String nombre, String talle, String foto, String color) {
		Producto producto = this.getOneById(codigoProducto);
		if (producto != null) {
			producto.setNombre(nombre);
			producto.setTalle(talle);
			producto.setFoto(foto);
			producto.setColor(color);
			return productoRepository.save(producto);
		} else {
			throw new ServiceException("No existe un producto con ese codigo");
		}
	}
	
	@Override
	public List<Producto> buscarProducto(String nombre, Long codigo, String talle, String color) {
		return productoRepository.findByNombreContainingAndCodigoAndTalleAndColor(nombre, codigo, talle, color);
	}

}
