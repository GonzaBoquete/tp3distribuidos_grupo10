package com.stockearte.tp3_grupo10.controller;


import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class WebController {
    
	
	/*@GetMapping("/home")
    public String showHomePage(HttpSession session, RedirectAttributes redirectAttributes) {
        if (session.getAttribute("usuario") == null) {
            return "redirect:/login";
        }
        return "home"; 
    }*/ // activar cuando funcione login
	
	@GetMapping("/home")
    public String homeProveedor() {
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
}