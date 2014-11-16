package br.com.safetycar.controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.safetycar.connection.ConnectionFactory;
import br.com.safetycar.modelos.FornecedorDespesas;
import br.com.safetycar.paginacao.ListaPaginacao;
import br.com.safetycar.paginacao.Paginador;
import br.com.safetycar.servico.dao.AlteracaoCadastroFornecedorDespesasDAO;
import br.com.safetycar.servico.dao.impl.AlteracaoCadastroFornecedorDespesasDAOImpl;

@Controller
public class AlteracaoCadastroFornecedorDespesasController {
	
	private static Logger LOG = Logger.getLogger(AlteracaoCadastroFornecedorDespesasController.class.getName());
	
	public static final String PARAMETRO_PAGINA = "pagina";
	public static final String PARAMETRO_ID = "id";
	
	@RequestMapping("/alteracaoCadForDesp")
	public String alteracaoForDesp(Model model, HttpServletRequest request) {		
			
		String pesqNome = request.getParameter("pesqNome");		
		String pesqContato = request.getParameter("pesqContato");
		String pesqTelefone = request.getParameter("pesqTelefone");
		
		if(pesqNome != null && pesqNome.replaceAll(" ", "").length() == 0){
			pesqNome = null;		
		}
		if(pesqContato != null && pesqContato.equals("Selecione")){
			pesqContato = null;			
		}
		if(pesqTelefone != null && pesqTelefone.replaceAll(" ", "").length() == 0){
			pesqTelefone = null;			
		}
		
		Connection connectionBD = new ConnectionFactory().getConnection();
		
		AlteracaoCadastroFornecedorDespesasDAO dao = new AlteracaoCadastroFornecedorDespesasDAOImpl(connectionBD);
				
		int numeroPaginaAtual = ServletRequestUtils.getIntParameter(request, PARAMETRO_PAGINA, 1);
		int qtdePorPagina = 10;
		Paginador paginador = new Paginador();
		List<FornecedorDespesas> fornecedorDespesas = dao.pesquisaFornecedorDespesas(pesqNome, pesqContato, pesqTelefone, numeroPaginaAtual, qtdePorPagina);	
		ListaPaginacao report = paginador.criarListaPaginacao(fornecedorDespesas, dao.countFornecedorDespesas(pesqNome, pesqContato, pesqTelefone), numeroPaginaAtual);		
		model.addAttribute("itensRelatorio", report);
		
		mantemDados(model, request);
		
		try {
			connectionBD.close();				
		} catch (SQLException e) {				
			LOG.error(e.getMessage(), e.getCause());
			throw new RuntimeException(e);
		}
		
		return "alteracaoCadastroFornecedorDespesas/alteracaoCadastroFornecedorDespesasLista";
	}
	
	@RequestMapping("/alteracaoCadForDespSelecionadoFordesp")
	public String salvaAlteracaoForDesp(Long id, Model model) {
		
		Connection connectionBD = new ConnectionFactory().getConnection();
		
		AlteracaoCadastroFornecedorDespesasDAO dao = new AlteracaoCadastroFornecedorDespesasDAOImpl(connectionBD);
		
		model.addAttribute("forDesp", dao.pesquisaFornecedorDespesas(id));		
		
		try {
			connectionBD.close();				
		} catch (SQLException e) {				
			LOG.error(e.getMessage(), e.getCause());
			throw new RuntimeException(e);
		}
		
		return "alteracaoCadastroFornecedorDespesas/alteracaoCadastroFornecedorDespesasDados";
	}
	
	@RequestMapping("/alteracaoCadForDespExcluir")
	public String excluiForDesp(Long idForDesp, Model model, HttpServletRequest request) {
		
		Connection connectionBD = new ConnectionFactory().getConnection();
		
		AlteracaoCadastroFornecedorDespesasDAO dao = new AlteracaoCadastroFornecedorDespesasDAOImpl(connectionBD);
		
		dao.removeFornecedorDespesas(idForDesp);
		
		try {
			connectionBD.close();				
		} catch (SQLException e) {				
			LOG.error(e.getMessage(), e.getCause());
			throw new RuntimeException(e);
		}
		
		return "redirect:alteracaoCadForDesp";		
	}
	
	private void mantemDados(Model model, HttpServletRequest request){		
		
		model.addAttribute("pesqNome", request.getParameter("pesqNome"));		
		model.addAttribute("pesqContato", request.getParameter("pesqContato"));		
		model.addAttribute("pesqTelefone", request.getParameter("pesqTelefone"));		
	}
}
