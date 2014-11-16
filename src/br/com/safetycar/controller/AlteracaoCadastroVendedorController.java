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
import br.com.safetycar.modelos.Vendedor;
import br.com.safetycar.paginacao.ListaPaginacao;
import br.com.safetycar.paginacao.Paginador;
import br.com.safetycar.servico.dao.AlteracaoCadastroVendedorDAO;
import br.com.safetycar.servico.dao.impl.AlteracaoCadastroVendedorDAOImpl;

@Controller
public class AlteracaoCadastroVendedorController {
	
	private static Logger LOG = Logger.getLogger(AlteracaoCadastroVendedorController.class.getName());
	
	public static final String PARAMETRO_PAGINA = "pagina";
	public static final String PARAMETRO_ID = "id";
	
	
	@RequestMapping("/alteracaoVendedor")
	public String alteracaoVendedor(Model model, HttpServletRequest request) {
		
		String pesqNome = request.getParameter("pesqNome");		
		String pesqSobreNome = request.getParameter("pesqSobreNome");		
		
		if(pesqNome != null && pesqNome.replaceAll(" ", "").length() == 0){
			pesqNome = null;			
		}
		if(pesqSobreNome != null && pesqSobreNome.replaceAll(" ", "").length() == 0){
			pesqSobreNome = null;			
		}
		
		Connection connectionBD = new ConnectionFactory().getConnection();
		
		AlteracaoCadastroVendedorDAO dao = new AlteracaoCadastroVendedorDAOImpl(connectionBD);
				
		int numeroPaginaAtual = ServletRequestUtils.getIntParameter(request, PARAMETRO_PAGINA, 1);
		int qtdePorPagina = 10;
		Paginador paginador = new Paginador();
		List<Vendedor> vendedores = dao.pesquisaVendedor(pesqNome, pesqSobreNome, numeroPaginaAtual, qtdePorPagina);	
		ListaPaginacao report = paginador.criarListaPaginacao(vendedores, dao.countVendedor(pesqNome, pesqSobreNome), numeroPaginaAtual);		
		model.addAttribute("itensRelatorio", report);
		
		mantemDados(model, request);		
		
		try {
			connectionBD.close();				
		} catch (SQLException e) {
			LOG.error(e.getMessage(), e.getCause());
			throw new RuntimeException(e);
		}
		
		return "alteracaoCadastroVendedor/alteracaoCadastroVendedorLista";
	}
	
	@RequestMapping("/alteracaoVendedorSelecionadoVendedor")
	public String vendedorSelecionado(Long id, Model model) {
			
		Connection connectionBD = new ConnectionFactory().getConnection();
		
		AlteracaoCadastroVendedorDAO dao = new AlteracaoCadastroVendedorDAOImpl(connectionBD);
				
		Vendedor vendedor = dao.pesquisaVendedor(id);		
		
		model.addAttribute("vendedor", vendedor);	
						
		
		try {
			connectionBD.close();				
		} catch (SQLException e) {
			LOG.error(e.getMessage(), e.getCause());
			throw new RuntimeException(e);
		}
		
		return "alteracaoCadastroVendedor/alteracaoCadastroVendedorDados";			    
	}
	
	@RequestMapping("/alteracaoVendedorExcluir")
	public String excluirVendedor(Long idVendedor, Model model, HttpServletRequest request) {
		
		Connection connectionBD = new ConnectionFactory().getConnection();
		
		AlteracaoCadastroVendedorDAO dao = new AlteracaoCadastroVendedorDAOImpl(connectionBD);
		
		dao.removeVendedor(idVendedor);
		
		try {
			connectionBD.close();				
		} catch (SQLException e) {
			LOG.error(e.getMessage(), e.getCause());
			throw new RuntimeException(e);
		}
		
		return "redirect:alteracaoVendedor";
	}
	
	private void mantemDados(Model model, HttpServletRequest request){		
		model.addAttribute("pesqNome", request.getParameter("pesqNome"));		
		model.addAttribute("pesqSobreNome", request.getParameter("pesqSobreNome"));	
	}

}
