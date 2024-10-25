package com.stockearte.tp3_grupo10.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "item_orden_compra")
public class ItemOrdenDeCompra {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "cantidad", nullable = false)
	private int cantidad;

	@ManyToOne
	@JoinColumn(name = "producto_codigo", referencedColumnName = "codigo", nullable = false)
	private Producto producto;

	@ManyToOne
	@JoinColumn(name = "id_orden_compra", nullable = false)
	private OrdenDeCompra OrdenDeCompra;

	public ItemOrdenDeCompra() {
		super();
	}

	public ItemOrdenDeCompra(int cantidad, Producto producto, OrdenDeCompra OrdenDeCompra) {
		super();
		this.cantidad = cantidad;
		this.producto = producto;
		this.OrdenDeCompra = OrdenDeCompra;
	}

	// Getters and Setters
	public Long getCodigo() {
		return id;
	}

	public void setCodigo(Long codigo) {
		this.id = codigo;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public OrdenDeCompra getOrdenDeCompra() {
		return OrdenDeCompra;
	}

	public void setOrdenDeCompra(OrdenDeCompra OrdenDeCompra) {
		this.OrdenDeCompra = OrdenDeCompra;
	}

}
