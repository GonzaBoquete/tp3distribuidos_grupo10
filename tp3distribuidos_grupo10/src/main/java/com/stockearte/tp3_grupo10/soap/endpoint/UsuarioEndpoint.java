package com.stockearte.tp3_grupo10.soap.endpoint;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.stockearte.tp3_grupo10.converter.UsuarioConverter;
import com.stockearte.tp3_grupo10.enumerators.Rol;
import com.stockearte.tp3_grupo10.model.Usuario;
import com.stockearte.tp3_grupo10.service.UsuarioService;
import com.stockearte.tp3_grupo10.soap.interfaces.AddUsuarioRequest;
import com.stockearte.tp3_grupo10.soap.interfaces.AddUsuarioResponse;
import com.stockearte.tp3_grupo10.soap.interfaces.BuscarUsuarioRequest;
import com.stockearte.tp3_grupo10.soap.interfaces.BuscarUsuarioResponse;
import com.stockearte.tp3_grupo10.soap.interfaces.GetAllUsuariosRequest;
import com.stockearte.tp3_grupo10.soap.interfaces.GetAllUsuariosResponse;
import com.stockearte.tp3_grupo10.soap.interfaces.GetOneUsuarioByIdRequest;
import com.stockearte.tp3_grupo10.soap.interfaces.GetOneUsuarioByIdResponse;
import com.stockearte.tp3_grupo10.soap.interfaces.LoginRequest;
import com.stockearte.tp3_grupo10.soap.interfaces.LoginResponse;
import com.stockearte.tp3_grupo10.soap.interfaces.UpdateUsuarioRequest;
import com.stockearte.tp3_grupo10.soap.interfaces.UpdateUsuarioResponse;
import com.stockearte.tp3_grupo10.soap.interfaces.UsuarioInfo;

@Endpoint
public class UsuarioEndpoint {
	private static final String NAMESPACE_URI = "http://www.stockearte.com/tp3_grupo10/soap/interfaces";

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private UsuarioConverter usuarioConverter;

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getOneUsuarioByCodeRequest")
	@ResponsePayload
	public GetOneUsuarioByIdResponse GetOneUsuarioByCode(@RequestPayload GetOneUsuarioByIdRequest request) {
		GetOneUsuarioByIdResponse response = new GetOneUsuarioByIdResponse();
		Usuario usuario = this.getUsuarioService().getOneById(request.getId());
		response.setUsuario(this.getUsuarioConverter().convertUsuarioToInfo(usuario));
		return response;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllUsuariosRequest")
	@ResponsePayload
	public GetAllUsuariosResponse getAllUsuarios(@RequestPayload GetAllUsuariosRequest request) {
		GetAllUsuariosResponse response = new GetAllUsuariosResponse();
		List<UsuarioInfo> usuarios = this.getUsuarioConverter()
				.convertUsuariosToInfos(this.getUsuarioService().getAll());
		for (UsuarioInfo usuario : usuarios) {
			response.getUsuarios().add(usuario);
		}
		return response;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "addUsuarioRequest")
	@ResponsePayload
	public AddUsuarioResponse addUsuario(@RequestPayload AddUsuarioRequest request) {
		AddUsuarioResponse response = new AddUsuarioResponse();
		response.getUsuarioServiceStatus()
				.setUsuario(this.getUsuarioConverter()
						.convertUsuarioToInfo(this.getUsuarioService().add(request.getUsuario().getNombreUsuario(),
								request.getUsuario().getContrasena(), request.getUsuario().getNombre(),
								request.getUsuario().getApellido(), Rol.valueOf(request.getUsuario().getRol()),
								request.getUsuario().isHabilitado(), request.getCodigoTienda())));
		return response;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateUsuarioRequest")
	@ResponsePayload
	public UpdateUsuarioResponse updateUsuario(@RequestPayload UpdateUsuarioRequest request) {
		UpdateUsuarioResponse response = new UpdateUsuarioResponse();
		try {
			Usuario usuario = this.getUsuarioService().update(request.getUsuario().getId(),
					request.getUsuario().getNombreUsuario(), request.getUsuario().getContrasena(),
					request.getUsuario().getNombre(), request.getUsuario().getApellido(),
					Rol.valueOf(request.getUsuario().getRol()), request.getUsuario().isHabilitado(),
					request.getCodigoTienda());
			response.getUsuarioServiceStatus().setStatus("OK");
			response.getUsuarioServiceStatus().setUsuario(this.getUsuarioConverter().convertUsuarioToInfo(usuario));
		} catch (Exception e) {
			response.getUsuarioServiceStatus().setStatus("ERROR");
			response.getUsuarioServiceStatus().setMessage(e.getLocalizedMessage());
		}
		return response;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "LoginRequest")
	@ResponsePayload
	public LoginResponse login(@RequestPayload LoginRequest request) {
		LoginResponse response = new LoginResponse();
		try {
			Usuario usuario = this.getUsuarioService().login(request.getNombreUsuario(), request.getContrasena());
			response.getUsuarioServiceStatus().setStatus("OK");
			response.getUsuarioServiceStatus().setUsuario(this.getUsuarioConverter().convertUsuarioToInfo(usuario));
		} catch (Exception e) {
			response.getUsuarioServiceStatus().setStatus("ERROR");
			response.getUsuarioServiceStatus().setMessage(e.getLocalizedMessage());
		}
		return response;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "BuscarUsuarioRequest")
	@ResponsePayload
	public BuscarUsuarioResponse buscarUsuario(@RequestPayload BuscarUsuarioRequest request) {
		BuscarUsuarioResponse response = new BuscarUsuarioResponse();
		List<UsuarioInfo> usuarios = this.getUsuarioConverter().convertUsuariosToInfos(
				this.getUsuarioService().buscarUsuario(request.getNombreUsuario(), request.getCodigoTienda()));
		for (UsuarioInfo usuario : usuarios) {
			response.getUsuarios().add(usuario);
		}
		return response;
	}

	public UsuarioService getUsuarioService() {
		return usuarioService;
	}

	public void setUsuarioService(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	public UsuarioConverter getUsuarioConverter() {
		return usuarioConverter;
	}

	public void setUsuarioConverter(UsuarioConverter usuarioConverter) {
		this.usuarioConverter = usuarioConverter;
	}
}
