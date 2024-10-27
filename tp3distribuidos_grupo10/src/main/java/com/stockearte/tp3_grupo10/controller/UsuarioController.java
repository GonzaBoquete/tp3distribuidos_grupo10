package com.stockearte.tp3_grupo10.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.stockearte.tp3_grupo10.enumerators.Rol;
import com.stockearte.tp3_grupo10.model.Usuario;
import com.stockearte.tp3_grupo10.service.UsuarioService;

import java.util.List;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

	@PostMapping("/add")
	public ResponseEntity<Usuario> addUsuario(@RequestParam String nombreUsuario, @RequestParam String contrasena,
			@RequestParam String nombre, @RequestParam String apellido, @RequestParam Rol rol,
			@RequestParam boolean habilitado, @RequestParam Long codigoTienda) {
		Usuario usuario = usuarioService.add(nombreUsuario, contrasena, nombre, apellido, rol, habilitado,
				codigoTienda);
		return ResponseEntity.ok(usuario);
	}

	@GetMapping("/getById")
	public ResponseEntity<Usuario> getUsuarioById(@RequestParam Long id) {
		Usuario usuario = usuarioService.getOneById(id);
		return ResponseEntity.ok(usuario);
	}

	@GetMapping("/getAll")
	public ResponseEntity<List<Usuario>> getAllUsuarios() {
		List<Usuario> usuarios = usuarioService.getAll();
		return ResponseEntity.ok(usuarios);
	}

	@PostMapping("/update")
	public ResponseEntity<Usuario> updateUsuario(@RequestParam Long idUsuario, @RequestParam String nombreUsuario,
			@RequestParam String contrasena, @RequestParam String nombre, @RequestParam String apellido,
			@RequestParam Rol rol, @RequestParam boolean habilitado, @RequestParam Long codigoTienda) {
		Usuario usuario = usuarioService.update(idUsuario, nombreUsuario, contrasena, nombre, apellido, rol, habilitado,
				codigoTienda);
		return ResponseEntity.ok(usuario);
	}

	@PostMapping("/login")
	public ResponseEntity<Usuario> login(@RequestParam String nombreUsuario, @RequestParam String contrasena) {
		Usuario usuario = usuarioService.login(nombreUsuario, contrasena);
		return ResponseEntity.ok(usuario);
	}

	@GetMapping("/buscar")
	public ResponseEntity<List<Usuario>> buscarUsuario(@RequestParam(required = false) String nombre,
			@RequestParam(required = false) Long codigoTienda) {
		List<Usuario> usuarios;
		if (codigoTienda != null) {
			usuarios = usuarioService.buscarUsuario(nombre, codigoTienda);
		} else {
			usuarios = usuarioService.buscarUsuario(nombre);
		}
		return ResponseEntity.ok(usuarios);
	}
}