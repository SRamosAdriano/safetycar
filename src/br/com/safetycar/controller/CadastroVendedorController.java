package br.com.safetycar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CadastroVendedorController {
	
	@RequestMapping("/cadastroVendedor")
	public String cadastroVendedor(Model model) {
		
		return "cadastroVendedor/cadastroVendedor";
	}

}
