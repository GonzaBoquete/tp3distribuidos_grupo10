package com.stockearte.tp3_grupo10.model;

import java.time.LocalDate;
import java.util.List;

import com.stockearte.tp3_grupo10.enumerators.EstadoOrden;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "orden_de_compra")
public class OrdenDeCompra {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name = "fecha")
	private LocalDate fecha;

	@Enumerated(EnumType.STRING)
	@Column(name = "estado")
	private EstadoOrden estado;

	@ManyToOne
	@JoinColumn(name = "tienda_codigod", nullable = false)
	private Tienda tienda;

	@OneToMany(mappedBy = "OrdenDeCompra", cascade = CascadeType.ALL)
	private List<ItemOrdenDeCompra> itemsOrdenCompra;

	public OrdenDeCompra(Long id, LocalDate fecha, EstadoOrden estado, Tienda tienda,
			List<ItemOrdenDeCompra> itemsOrdenCompra) {
		super();
		this.id = id;
		this.fecha = fecha;
		this.estado = estado;
		this.tienda = tienda;
		this.itemsOrdenCompra = itemsOrdenCompra;
	}

	public OrdenDeCompra() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public EstadoOrden getEstado() {
		return estado;
	}

	public void setEstado(EstadoOrden estado) {
		this.estado = estado;
	}

	public Tienda getTienda() {
		return tienda;
	}

	public void setTienda(Tienda tienda) {
		this.tienda = tienda;
	}

	public List<ItemOrdenDeCompra> getItemsOrdenCompra() {
		return itemsOrdenCompra;
	}

	public void setItemsOrdenCompra(List<ItemOrdenDeCompra> itemsOrdenCompra) {
		this.itemsOrdenCompra = itemsOrdenCompra;
	}

}
