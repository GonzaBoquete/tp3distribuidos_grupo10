package com.stockearte.tp3_grupo10.controller;

import java.util.List;
import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.stockearte.tp3_grupo10.converter.UsuarioConverter;
import com.stockearte.tp3_grupo10.enumerators.Rol;
import com.stockearte.tp3_grupo10.model.Usuario;
import com.stockearte.tp3_grupo10.soap.endpoint.UsuarioEndpoint;
import com.stockearte.tp3_grupo10.soap.interfaces.AddUsuarioRequest;
import com.stockearte.tp3_grupo10.soap.interfaces.BuscarUsuarioRequest;
import com.stockearte.tp3_grupo10.soap.interfaces.GetAllUsuariosRequest;
import com.stockearte.tp3_grupo10.soap.interfaces.GetOneUsuarioByIdRequest;
import com.stockearte.tp3_grupo10.soap.interfaces.LoginRequest;
import com.stockearte.tp3_grupo10.soap.interfaces.UpdateUsuarioRequest;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioEndpoint usuarioEndpoint;

	@Autowired
	private UsuarioConverter usuarioConverter;

	@PostMapping("/add")
	public ResponseEntity<Usuario> addUsuario(@RequestParam String nombreUsuario, @RequestParam String contrasena,
			@RequestParam String nombre, @RequestParam String apellido, @RequestParam Rol rol,
			@RequestParam boolean habilitado, @RequestParam Long codigoTienda) {
		AddUsuarioRequest addUsuarioRequest = new AddUsuarioRequest();
		addUsuarioRequest.setNombreUsuario(nombreUsuario);
		addUsuarioRequest.setContrasena(contrasena);
		addUsuarioRequest.setNombre(nombre);
		addUsuarioRequest.setApellido(apellido);
		addUsuarioRequest.setRol(rol.toString());
		addUsuarioRequest.setHabilitado(habilitado);
		addUsuarioRequest.setCodigoTienda(codigoTienda);
		Usuario usuario = usuarioConverter.convertInfoToUsuario(
				usuarioEndpoint.addUsuario(addUsuarioRequest).getUsuarioServiceStatus().getUsuario());
		return ResponseEntity.ok(usuario);
	}

	@GetMapping("/getById")
	public ResponseEntity<Usuario> getUsuarioById(@RequestParam Long id) {
		GetOneUsuarioByIdRequest getOneUsuarioByCodeRequest = new GetOneUsuarioByIdRequest();
		getOneUsuarioByCodeRequest.setId(id);
		Usuario usuario = usuarioConverter
				.convertInfoToUsuario(usuarioEndpoint.GetOneUsuarioByCode(getOneUsuarioByCodeRequest).getUsuario());
		return ResponseEntity.ok(usuario);
	}

	@GetMapping("/getAll")
	public ResponseEntity<List<Usuario>> getAllUsuarios() {
		GetAllUsuariosRequest getAllUsuariosRequest = new GetAllUsuariosRequest();
		List<Usuario> usuarios = usuarioConverter
				.convertInfosToUsuarios(usuarioEndpoint.getAllUsuarios(getAllUsuariosRequest).getUsuarios());
		return ResponseEntity.ok(usuarios);
	}

	@PostMapping("/update")
	public ResponseEntity<Usuario> updateUsuario(@RequestParam Long idUsuario, @RequestParam String nombreUsuario,
			@RequestParam String contrasena, @RequestParam String nombre, @RequestParam String apellido,
			@RequestParam Rol rol, @RequestParam boolean habilitado, @RequestParam Long codigoTienda) {
		UpdateUsuarioRequest updateUsuarioRequest = new UpdateUsuarioRequest();
		updateUsuarioRequest.setId(idUsuario);
		updateUsuarioRequest.setNombreUsuario(nombreUsuario);
		updateUsuarioRequest.setContrasena(contrasena);
		updateUsuarioRequest.setNombre(nombre);
		updateUsuarioRequest.setApellido(apellido);
		updateUsuarioRequest.setRol(rol.toString());
		updateUsuarioRequest.setHabilitado(habilitado);
		updateUsuarioRequest.setCodigoTienda(codigoTienda);
		Usuario usuario = usuarioConverter.convertInfoToUsuario(
				usuarioEndpoint.updateUsuario(updateUsuarioRequest).getUsuarioServiceStatus().getUsuario());
		return ResponseEntity.ok(usuario);
	}

	@PostMapping("/login")
	public ResponseEntity<Usuario> login(
	    @RequestParam String nombreUsuario, 
	    @RequestParam String contrasena,
	    HttpSession session) {

	    LoginRequest loginRequest = new LoginRequest();
	    loginRequest.setNombreUsuario(nombreUsuario);
	    loginRequest.setContrasena(contrasena);

	    Usuario usuario = usuarioConverter
	            .convertInfoToUsuario(usuarioEndpoint.login(loginRequest).getUsuarioServiceStatus().getUsuario());

	    // Guardar el usuario en la sesión si el login es exitoso
	    if (usuario != null) {
	        session.setAttribute("usuario", usuario);
	        return ResponseEntity.ok(usuario);
	    } else {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build(); // Retorna un error 401 si no es exitoso
	    }
	}

	@PostMapping("/logout")
	public ResponseEntity<String> logout(HttpSession session) {
	    session.invalidate();
	    return ResponseEntity.ok("Sesión cerrada correctamente");
	}

	@GetMapping("/buscar")
	public ResponseEntity<List<Usuario>> buscarUsuario(@RequestParam(required = false) String nombre,
			@RequestParam(required = false) Long codigoTienda) {
		BuscarUsuarioRequest buscarUsuarioRequest = new BuscarUsuarioRequest();
		buscarUsuarioRequest.setNombreUsuario(nombre);
		buscarUsuarioRequest.setCodigoTienda(codigoTienda);
		List<Usuario> usuarios = usuarioConverter
				.convertInfosToUsuarios(usuarioEndpoint.buscarUsuario(buscarUsuarioRequest).getUsuario());
		return ResponseEntity.ok(usuarios);
	}
}