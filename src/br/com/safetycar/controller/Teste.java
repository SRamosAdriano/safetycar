package br.com.safetycar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Teste {
	
	

		/*
		 * URL acesso http://localhost:8080/safetycar/21/
		 */

		/*
		 * Chama pagina
		 */

		@RequestMapping("/teste")
		public String teste(Model model) {			
			
			return "teste/index";
		}


		

}
