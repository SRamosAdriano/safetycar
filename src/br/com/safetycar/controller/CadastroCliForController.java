package br.com.safetycar.controller;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.safetycar.connection.ConnectionFactory;
import br.com.safetycar.servico.dao.CadastroClienteFornecedorDAO;
import br.com.safetycar.servico.dao.impl.CadastroClienteFornecedorDAOImpl;

@Controller
public class CadastroCliForController {	
	
	private static Logger LOG = Logger.getLogger(CadastroCliForController.class.getName());

	@RequestMapping("/cadastroCliFor")
	public String cadastroCliFor(Model model) {
		
		Connection connectionBD = new ConnectionFactory().getConnection();
		CadastroClienteFornecedorDAO dao = new CadastroClienteFornecedorDAOImpl(connectionBD);				
		model.addAttribute("estados", dao.getSiglaEstados());		
		
		try {
			connectionBD.close();				
		} catch (SQLException e) {
			LOG.error(e.getMessage(), e.getCause());
			throw new RuntimeException(e);
		}
		
		return "cadastroClienteForncedor/cadastroCliFor";
	}
}
