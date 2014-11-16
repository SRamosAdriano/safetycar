package br.com.safetycar.controller.adm;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.safetycar.connection.ConnectionFactory;
import br.com.safetycar.servico.dao.CadastroGrupoDAO;
import br.com.safetycar.servico.dao.impl.CadastroGrupoDAOImpl;

@Controller
public class CadastroGrupoController {
	
	private static Logger LOG = Logger.getLogger(CadastroGrupoController.class.getName());
	
	@RequestMapping("/cadastroGrupo")
	public String cadastroGrupo(Model model, HttpServletRequest request) {
				
		Connection connectionBD = new ConnectionFactory().getConnection();
		CadastroGrupoDAO dao = new CadastroGrupoDAOImpl(connectionBD);
				
		model.addAttribute("paginasDisponiveis",dao.getPaginasDisponiveis());			
		
		try {
			connectionBD.close();				
		} catch (SQLException e) {				
			LOG.error(e.getMessage(), e.getCause());
			throw new RuntimeException(e);
		}
		
		return "cadastroGrupo/cadastroGrupo";
	}

}
