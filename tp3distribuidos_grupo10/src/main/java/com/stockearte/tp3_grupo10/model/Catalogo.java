package com.stockearte.tp3_grupo10.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "catalogo")
public class Catalogo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "tienda_id", nullable = false)
	private Tienda tienda;

	@ManyToMany
	@JoinTable(name = "Catalogo_Producto", joinColumns = @JoinColumn(name = "catalogo_id"), inverseJoinColumns = @JoinColumn(name = "producto_codigo"))
	private List<Producto> productos;

	public Catalogo(Tienda tienda, List<Producto> productos) {
		super();
		this.tienda = tienda;
		this.productos = productos;
	}

	public Catalogo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public Tienda getTienda() {
		return tienda;
	}

	public void setTienda(Tienda tienda) {
		this.tienda = tienda;
	}

	public List<Producto> getProducto() {
		return productos;
	}

	public void setProducto(List<Producto> productos) {
		this.productos = productos;
	}
}
