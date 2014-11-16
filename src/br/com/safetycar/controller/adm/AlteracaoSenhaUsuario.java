package br.com.safetycar.controller.adm;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.safetycar.modelos.Usuarios;

@Controller
public class AlteracaoSenhaUsuario {
	
	@RequestMapping("/alteraSenhaUsuario")
	public String alteraSenhaUsuario(Model model, HttpServletRequest request) {
		
		Usuarios usuario = (Usuarios)request.getSession().getAttribute("usrLogado");
				
		model.addAttribute("usuario", usuario);
		
		return "alteracaoSenhaUsuario/alteracaoSenhaUsuario";						
	}

}
