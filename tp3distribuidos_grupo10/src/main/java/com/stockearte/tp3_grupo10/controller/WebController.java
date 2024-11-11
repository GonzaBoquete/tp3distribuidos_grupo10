package com.stockearte.tp3_grupo10.controller;


import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.stockearte.tp3_grupo10.enumerators.Rol;
import com.stockearte.tp3_grupo10.model.Tienda;
import com.stockearte.tp3_grupo10.model.Usuario;

@Controller
public class WebController {
    
	
	@GetMapping("/home")
	public String showHomePage(HttpSession session, Model model) {
	    if (session.getAttribute("usuario") == null) {
	        return "redirect:/login";  
	    }
	    Usuario usuario = (Usuario) session.getAttribute("usuario"); 
	    String rol = usuario.getRol().name(); 
	    model.addAttribute("rol", rol);
	    Tienda tienda = usuario.getTienda();
	    model.addAttribute("tienda", tienda);

	    return "home";  
	}
	
	@GetMapping("/")
    public String redirectToHome() {
        return "redirect:/home"; 
    }
	
	@GetMapping("/login")
	public String showLoginForm() {
	    return "login"; 
	}
	
	@GetMapping("/ordenesCompra")
    public String ordenesCompra() {
        return "ordenesCompra"; 
    }
	
	@GetMapping("/catalogo")
    public String catalogo() {
        return "catalogo"; 
    }
	
	@GetMapping("/detalleCatalogo/{id}")
	public String detalleCatalogo(@PathVariable("id") Long idCatalogo, Model model) {
	    model.addAttribute("idCatalogo", idCatalogo);
	    return "detalleCatalogo";
	}
	
	@GetMapping("/crearCatalogo")
	public String crearCatalogo() {
	    return "crearCatalogo";
	}
	
	@GetMapping("/ordenCompra/{id}")
	public String detalleOrdenCompra(@PathVariable("id") Long idOrdenCompra, Model model) {
	    model.addAttribute("idOrdenCompra", idOrdenCompra);
	    return "detalleOrdenCompra";
	}

}