package br.com.safetycar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CadastroFornecedorDespesasController {
		
	@RequestMapping("/cadastroFornecedorDespesas")
	public String cadastroFornecedorDespesas(Model model) {
		
		return "cadastroFornecedorDespesas/cadastroFornecedorDespesas";
	}
}
