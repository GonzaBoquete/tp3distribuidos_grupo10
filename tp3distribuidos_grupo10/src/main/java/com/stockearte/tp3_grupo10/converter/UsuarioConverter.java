package com.stockearte.tp3_grupo10.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.stockearte.tp3_grupo10.enumerators.Rol;
import com.stockearte.tp3_grupo10.model.Usuario;
import com.stockearte.tp3_grupo10.soap.interfaces.UsuarioInfo;

@Component
public class UsuarioConverter {

	// Convertir de Usuario a UsuarioInfo
	public UsuarioInfo convertUsuarioToInfo(Usuario usuario) {
		UsuarioInfo info = new UsuarioInfo();
		info.setId(usuario.getId());
		info.setNombreUsuario(usuario.getNombreUsuario());
		info.setContrasena(usuario.getContrasena());
		info.setNombre(usuario.getNombre());
		info.setApellido(usuario.getApellido());
		info.setRol(usuario.getRol().getValue()); 
		return info;
	}

	// Convertir de UsuarioInfo a Usuario
	public Usuario convertInfoToUsuario(UsuarioInfo info) {
		Usuario usuario = new Usuario();
		usuario.setNombreUsuario(info.getNombreUsuario());
		usuario.setContrasena(info.getContrasena());
		usuario.setNombre(info.getNombre());
		usuario.setApellido(info.getApellido());
		usuario.setRol(Rol.valueOf(info.getRol()));

		return usuario;
	}

	// Convertir lista de Usuario a lista de UsuarioInfo
	public List<UsuarioInfo> convertUsuariosToInfos(List<Usuario> usuarios) {
		List<UsuarioInfo> infoList = new ArrayList<>();
		for (Usuario usuario : usuarios) {
			infoList.add(convertUsuarioToInfo(usuario));
		}
		return infoList;
	}

	// Convertir lista de UsuarioInfo a lista de Usuario
	public List<Usuario> convertInfosToUsuarios(List<UsuarioInfo> infoList) {
		List<Usuario> usuarios = new ArrayList<>();
		for (UsuarioInfo info : infoList) {
			usuarios.add(convertInfoToUsuario(info));
		}
		return usuarios;
	}
}
