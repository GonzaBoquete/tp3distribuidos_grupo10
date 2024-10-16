package com.stockearte.tp3_grupo10.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.stockearte.tp3_grupo10.model.Tienda;
import com.stockearte.tp3_grupo10.repository.TiendaRepository;

@Service("tiendaService")
public class TiendaServiceImpl implements TiendaService {

	@Autowired
	@Qualifier("tiendaRepository")
	private TiendaRepository tiendaRepository;
	
	@Override
	public Tienda add(Tienda tienda) {
		return tiendaRepository.save(tienda);
	}

	@Override
	public Tienda getOneById(Long codigo) {
		Optional<Tienda> tienda = tiendaRepository.findById(codigo);
		return tienda.isEmpty() ? null : tienda.get();
	}
	
	@Override
	public List<Tienda> getAll() {
		return (List<Tienda>) tiendaRepository.findAll();
	}

	@Override
	public Tienda update(Tienda tienda, Long codigo) {
		Optional<Tienda> foundTienda = tiendaRepository.findById(codigo);
		if (!foundTienda.isEmpty()) {
			foundTienda.get().setDireccion(tienda.getDireccion());
			foundTienda.get().setCiudad(tienda.getCiudad());
			foundTienda.get().setProvincia(tienda.getProvincia());
			foundTienda.get().setHabilitada(tienda.isHabilitada());
			return tiendaRepository.save(foundTienda.get());
		}
		return null;
	}
	
	@Override
	public List<Tienda> buscarTienda(Long codigo, boolean habilitada) {
		return tiendaRepository.findByCodigoAndHabilitada(codigo, habilitada);
	}

}
