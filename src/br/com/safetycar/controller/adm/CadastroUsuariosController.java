package br.com.safetycar.controller.adm;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.safetycar.connection.ConnectionFactory;
import br.com.safetycar.servico.dao.CadastroUsuariosDAO;
import br.com.safetycar.servico.dao.impl.CadastroUsuariosDAOImpl;

@Controller
public class CadastroUsuariosController {
	
	@RequestMapping("/cadastroUsuarios")
	public String cadastroUsuario(Model model, HttpServletRequest request) {		
		
		Connection connectionBD = new ConnectionFactory().getConnection();
		
		CadastroUsuariosDAO dao = new CadastroUsuariosDAOImpl(connectionBD);
		
		model.addAttribute("gruposDisponiveis",dao.getGrupos());
		
		return "cadastroUsuarios/cadastroUsuarios";
	}

}
