package com.stockearte.tp3_grupo10.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.stockearte.tp3_grupo10.enumerators.Rol;
import com.stockearte.tp3_grupo10.model.Tienda;
import com.stockearte.tp3_grupo10.model.Usuario;
import com.stockearte.tp3_grupo10.repository.UsuarioRepository;

import jakarta.persistence.criteria.Predicate;

@Service("usuarioService")
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	@Qualifier("usuarioRepository")
	private UsuarioRepository usuarioRepository;

	@Autowired
	private TiendaService tiendaService;

	@Override
	public Usuario add(String nombreUsuario, String contrasena, String nombre, String apellido, Rol rol,
			boolean habilitado, Long codigoTienda) {
		Optional<Usuario> foundUsuario = getUsuarioRepository().findByNombreUsuario(nombreUsuario);
		if (!foundUsuario.isEmpty()) {
			throw new ServiceException("Ya existe un usuario con ese nombre de usuario.");
		} else {
			Usuario usuario = new Usuario();
			Tienda tienda = getTiendaService().getOneById(codigoTienda);
			if (tienda != null) {
				usuario.setTienda(tienda);
			} else {
				throw new ServiceException("No se encontro la tienda");
			}
			usuario.setNombreUsuario(nombreUsuario);
			usuario.setContrasena(contrasena);
			usuario.setNombre(nombre);
			usuario.setApellido(apellido);
			usuario.setRol(rol);
			usuario.setHabilitado(habilitado);
			return usuarioRepository.save(usuario);
		}
	}

	@Override
	public Usuario getOneById(Long id) {
		Optional<Usuario> usuario = usuarioRepository.findById(id);
		if(usuario.isEmpty()) {
			throw new ServiceException("No se encontro el usuario");
		}
		return usuario.isEmpty() ? null : usuario.get();
	}

	@Override
	public List<Usuario> getAll() {
		return (List<Usuario>) usuarioRepository.findAll();
	}

	@Override
	public Usuario update(Long idUsuario, String nombreUsuario, String contrasena, String nombre, String apellido,
			Rol rol, boolean habilitado, Long codigoTienda) {
		Optional<Usuario> foundUsuario = usuarioRepository.findById(idUsuario);
		if (!foundUsuario.isEmpty()) {
			Tienda tienda = getTiendaService().getOneById(codigoTienda);
			if (tienda != null) {
				foundUsuario.get().setTienda(tienda);
			} else {
				throw new ServiceException("No se encontro la tienda");
			}
			foundUsuario.get().setContrasena(contrasena);
			foundUsuario.get().setNombre(nombre);
			foundUsuario.get().setApellido(apellido);
			foundUsuario.get().setRol(rol);
			foundUsuario.get().setHabilitado(habilitado);
			return usuarioRepository.save(foundUsuario.get());
		} else {
			throw new ServiceException("No se encontro el usuario");
		}
	}

	@Override
	public Usuario login(String nombreUsuario, String contrasena) {
		Optional<Usuario> optionalUser = usuarioRepository.findByNombreUsuario(nombreUsuario);
		if (!optionalUser.isEmpty()) {
			Usuario user = optionalUser.get();
			if (user.getContrasena().equals(contrasena)) {
				return user;
			} else {
				throw new ServiceException("Contraseña incorrecta");
			}
		} else {
			throw new ServiceException("Usuario no encontrado");
		}
	}

	@Override
	public List<Usuario> buscarUsuario(String nombre, Long codigoTienda) {
		return usuarioRepository.findAll((Specification<Usuario>) (root, query, criteriaBuilder) -> {
			List<Predicate> predicates = new ArrayList<>();

			if (nombre != null && !nombre.isEmpty()) {
				predicates.add(criteriaBuilder.like(root.get("nombreUsuario"), "%" + nombre + "%"));
			}

			if (codigoTienda != null) {
				Tienda tienda = tiendaService.getOneById(codigoTienda);
				if (tienda != null) {
					predicates.add(criteriaBuilder.equal(root.get("tienda"), tienda));
				}
			}

			return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
		});
	}

	public TiendaService getTiendaService() {
		return tiendaService;
	}

	public void setTiendaService(TiendaService tiendaService) {
		this.tiendaService = tiendaService;
	}

	public UsuarioRepository getUsuarioRepository() {
		return usuarioRepository;
	}

	public void setUsuarioRepository(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}

}
