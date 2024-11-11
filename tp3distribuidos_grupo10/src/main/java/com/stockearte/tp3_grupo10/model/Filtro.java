package com.stockearte.tp3_grupo10.model;

import java.time.LocalDate;

import com.stockearte.tp3_grupo10.enumerators.EstadoOrden;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "filtro")
public class Filtro {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name = "nombre", nullable = false, unique = true)
	private String nombre;

	@ManyToOne
	@JoinColumn(name = "usuario_id", nullable = false)
	private Usuario usuario;

	@ManyToOne
	@JoinColumn(name = "tienda_codigo", nullable = false)
	private Tienda tienda;

	@ManyToOne
	@JoinColumn(name = "producto_codigo", nullable = false)
	private Producto producto;
	
	@Column(name = "fecha_desde")
	private LocalDate fechaDesde;
	
	@Column(name = "fecha_hasta")
	private LocalDate fechaHasta;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "estado")
	private EstadoOrden estado;

	public Filtro(String nombre, Usuario usuario, Tienda tienda, Producto producto, LocalDate fechaDesde,
			LocalDate fechaHasta, EstadoOrden estado) {
		super();
		this.nombre = nombre;
		this.usuario = usuario;
		this.tienda = tienda;
		this.producto = producto;
		this.fechaDesde = fechaDesde;
		this.fechaHasta = fechaHasta;
		this.estado = estado;
	}

	public Filtro() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Tienda getTienda() {
		return tienda;
	}

	public void setTienda(Tienda tienda) {
		this.tienda = tienda;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public LocalDate getFechaDesde() {
		return fechaDesde;
	}

	public void setFechaDesde(LocalDate fechaDesde) {
		this.fechaDesde = fechaDesde;
	}

	public LocalDate getFechaHasta() {
		return fechaHasta;
	}

	public void setFechaHasta(LocalDate fechaHasta) {
		this.fechaHasta = fechaHasta;
	}

	public EstadoOrden getEstado() {
		return estado;
	}

	public void setEstado(EstadoOrden estado) {
		this.estado = estado;
	}

}
