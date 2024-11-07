package com.stockearte.tp3_grupo10.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.stockearte.tp3_grupo10.model.Tienda;
import com.stockearte.tp3_grupo10.repository.TiendaRepository;

import jakarta.persistence.criteria.Predicate;

@Service("tiendaService")
public class TiendaServiceImpl implements TiendaService {

	@Autowired
	@Qualifier("tiendaRepository")
	private TiendaRepository tiendaRepository;

	@Override
	public Tienda add(Long codigoTienda, String direccion, String ciudad, String provincia, boolean habilitada) {
		Tienda tienda = this.getOneById(codigoTienda);
		if (tienda != null) {
			throw new ServiceException("Ya existe una tienda con ese codigo");
		} else {
			tienda = new Tienda();
			tienda.setCodigo(codigoTienda);
			tienda.setDireccion(direccion);
			tienda.setCiudad(ciudad);
			tienda.setProvincia(provincia);
			tienda.setHabilitada(habilitada);
			return tiendaRepository.save(tienda);
		}
	}

	@Override
	public Tienda getOneById(Long codigoTienda) {
		Optional<Tienda> tienda = tiendaRepository.findById(codigoTienda);
		return tienda.isEmpty() ? null : tienda.get();
	}

	@Override
	public List<Tienda> getAll() {
		return (List<Tienda>) tiendaRepository.findAll();
	}

	@Override
	public Tienda update(Long codigoTienda, String direccion, String ciudad, String provincia, boolean habilitada) {
		Tienda tienda = this.getOneById(codigoTienda);
		if (tienda != null) {
			tienda.setDireccion(direccion);
			tienda.setCiudad(ciudad);
			tienda.setProvincia(provincia);
			tienda.setHabilitada(habilitada);
			return tiendaRepository.save(tienda);
		} else {
			throw new ServiceException("No se encontro ninguna tienda con ese codigo.");
		}
	}

	@Override
	public List<Tienda> buscarTienda(Long codigoTienda, boolean habilitada) {
		return tiendaRepository.findAll((Specification<Tienda>) (root, query, criteriaBuilder) -> {
			List<Predicate> predicates = new ArrayList<>();

			if (codigoTienda != null) {
				predicates.add(criteriaBuilder.equal(root.get("codigo"), codigoTienda));
			}
			predicates.add(criteriaBuilder.equal(root.get("habilitada"), habilitada));

			return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
		});
	}

}
