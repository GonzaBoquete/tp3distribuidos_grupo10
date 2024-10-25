package com.stockearte.tp3_grupo10.service;

import java.util.List;

import com.stockearte.tp3_grupo10.enumerators.Rol;
import com.stockearte.tp3_grupo10.model.Usuario;

public interface UsuarioService {

	Usuario add(String nombreUsuario, String contrasena, String nombre, String apellido,
			Rol rol, boolean habilitado, Long codigoTienda);

	Usuario getOneById(Long id);

	List<Usuario> getAll();

	Usuario update(Long idUsuario, String nombreUsuario, String contrasena, String nombre, String apellido,
			Rol rol, boolean habilitado, Long codigoTienda);
	
	Usuario login(String nombreUsuario, String contrasena);
	
	List<Usuario> buscarUsuario(String nombre, Long codigoTienda);
	
	List<Usuario> buscarUsuario(String nombre);

}
