package com.stockearte.tp3_grupo10.service;

import java.util.List;
import java.util.Optional;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.stockearte.tp3_grupo10.enumerators.Rol;
import com.stockearte.tp3_grupo10.model.Tienda;
import com.stockearte.tp3_grupo10.model.Usuario;
import com.stockearte.tp3_grupo10.repository.UsuarioRepository;

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
			Tienda tienda = getTiendaService().getOneById(codigoTienda);
			if (tienda != null) {
				foundUsuario.get().setTienda(tienda);
			} else {
				throw new ServiceException("No se encontro la tienda");
			}
			foundUsuario.get().setNombreUsuario(nombreUsuario);
			foundUsuario.get().setContrasena(contrasena);
			foundUsuario.get().setNombre(nombre);
			foundUsuario.get().setApellido(apellido);
			foundUsuario.get().setRol(rol);
			foundUsuario.get().setHabilitado(habilitado);
			return usuarioRepository.save(foundUsuario.get());
		}
	}

	@Override
	public Usuario getOneById(Long id) {
		Optional<Usuario> usuario = usuarioRepository.findById(id);
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
		if (optionalUser.isPresent()) {
			Usuario user = optionalUser.get();
			if (user.getContrasena().equals(contrasena)) {
				return user;
			} else {
				throw new RuntimeException("Contraseña incorrecta");
			}
		} else {
			throw new RuntimeException("Usuario no encontrado");
		}
	}

	@Override
	public List<Usuario> buscarUsuario(String nombre, Long codigoTienda) {
		Tienda tienda = getTiendaService().getOneById(codigoTienda);
		if (tienda != null) {
			return usuarioRepository.findByNombreAndTienda(nombre, tienda);
		} else {
			return usuarioRepository.findByNombre(nombre);
		}
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
