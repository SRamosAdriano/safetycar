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
import br.com.safetycar.modelos.ClienteFornecedor;
import br.com.safetycar.paginacao.ListaPaginacao;
import br.com.safetycar.paginacao.Paginador;
import br.com.safetycar.servico.dao.AlteracaoCadastroClienteFornecedorDAO;
import br.com.safetycar.servico.dao.impl.AlteracaoCadastroClienteFornecedorDAOImpl;

@Controller
public class AlteracaoCadastroClifForController {
	
	private static Logger LOG = Logger.getLogger(AlteracaoCadastroClifForController.class.getName());
	
	public static final String PARAMETRO_PAGINA = "pagina";
	public static final String PARAMETRO_ID = "id";	
		
	@RequestMapping("/alteracaoCliFor")
	public String alteracaoCliFor(Model model, HttpServletRequest request) {		
		
		String pesqNome = request.getParameter("pesqNome");		
		String pesqCpf = request.getParameter("pesqCpf");
		String pesqRg = request.getParameter("pesqRg");
		
		if(pesqNome != null && pesqNome.replaceAll(" ", "").length() == 0){
			pesqNome = null;			
		}
		if(pesqCpf != null && pesqCpf.replaceAll(" ", "").length() == 0){
			pesqCpf = null;			
		}
		if(pesqRg != null && pesqRg.replaceAll(" ", "").length() == 0){
			pesqRg = null;			
		}
		Connection connectionBD = new ConnectionFactory().getConnection();
		
		AlteracaoCadastroClienteFornecedorDAO dao = new AlteracaoCadastroClienteFornecedorDAOImpl(connectionBD);
		
		int numeroPaginaAtual = ServletRequestUtils.getIntParameter(request, PARAMETRO_PAGINA, 1);
		int qtdePorPagina = 10;
		Paginador paginador = new Paginador();
		List<ClienteFornecedor> clisFors = dao.pesquisaCliFor(pesqNome, pesqCpf, pesqRg, numeroPaginaAtual, qtdePorPagina);
		ListaPaginacao report = paginador.criarListaPaginacao(clisFors, dao.countClienteFornecedor(pesqNome, pesqCpf, pesqRg), numeroPaginaAtual);		
		model.addAttribute("itensRelatorio", report);
				
		
		mantemDados(model, request);
		model.addAttribute("radioStatus", "1");
		model.addAttribute("radioCliFor", "1");
				
		model.addAttribute("estados", dao.getSiglaEstados());
		
		try {
			connectionBD.close();				
		} catch (SQLException e) {			
			LOG.error(e.getMessage(), e.getCause());
			throw new RuntimeException(e);
		}				
		return "alteracaoCadastroClienteFornecedor/alteracaoCadastroCliForLista";
	}
	

	@RequestMapping("/cliForSelecionado")
	public String cliForSelecionado(Long id, Model model) {
		
		Connection connectionBD = new ConnectionFactory().getConnection();
		
		AlteracaoCadastroClienteFornecedorDAO dao = new AlteracaoCadastroClienteFornecedorDAOImpl(connectionBD);		
		
		model.addAttribute("estados", dao.getSiglaEstados());		

		ClienteFornecedor clifor = dao.pesquisaCliFor(id);		
		model.addAttribute("cliFor", clifor);			
		
		if(clifor.getStatus().equals("ativo")){
			model.addAttribute("radioStatus", "1");
		}else{
			model.addAttribute("radioStatus", "2");
		}
		
		if(clifor.getTipoCliFor().equals("cliente/Fornecedor")){
			model.addAttribute("radioCliFor", "1");
		}else if(clifor.getTipoCliFor().equals("cliente")){
				model.addAttribute("radioCliFor", "2");
			}else if(clifor.getTipoCliFor().equals("fornecedor")){
					model.addAttribute("radioCliFor", "3");
				}

		try {
			connectionBD.close();				
		} catch (SQLException e) {				
			LOG.error(e.getMessage(), e.getCause());
			throw new RuntimeException(e);
		}		
		
		return "alteracaoCadastroClienteFornecedor/alteracaoCadastroCliForDados";
	}
	
	private void mantemDados(Model model, HttpServletRequest request){		
		model.addAttribute("pesqNome", request.getParameter("pesqNome"));		
		model.addAttribute("pesqCpf", request.getParameter("pesqCpf"));		
		model.addAttribute("pesqRg", request.getParameter("pesqRg"));
		
	}	
}
