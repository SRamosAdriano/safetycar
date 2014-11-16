package br.com.safetycar.controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.safetycar.connection.ConnectionFactory;
import br.com.safetycar.modelos.ClienteFornecedor;
import br.com.safetycar.modelos.Veiculos;
import br.com.safetycar.modelos.VendaVeiculo;
import br.com.safetycar.modelos.Vendedor;
import br.com.safetycar.paginacao.ListaPaginacao;
import br.com.safetycar.paginacao.Paginador;
import br.com.safetycar.servico.dao.AlteracaoCadastroVendaVeiculoDAO;
import br.com.safetycar.servico.dao.impl.AlteracaoCadastroVendaVeiculoDAOImpl;

@Controller
public class AlteracaoCadastroVendaVeiculoController {
	
	private static Logger LOG = Logger.getLogger(AlteracaoCadastroVendaVeiculoController.class.getName());
	
	public static final String PARAMETRO_PAGINA = "pagina";
	public static final String PARAMETRO_ID = "id";
	
	@RequestMapping("/alteracaoVendaVeiculo")
	public String vendaVeiculo(Model model, HttpServletRequest request) {
		
		String pesqPlaca = request.getParameter("pesqPlaca");		
		String pesqMarca = request.getParameter("pesqMarca");
		String pesqModelo = request.getParameter("pesqModelo");
		
		if(pesqPlaca != null && pesqPlaca.replaceAll(" ", "").length() == 0){
			pesqPlaca = null;			
		}
		if(pesqMarca != null && pesqMarca.equals("Selecione")){
			pesqMarca = null;			
		}
		if(pesqModelo != null && pesqModelo.replaceAll(" ", "").length() == 0){
			pesqModelo = null;			
		}
		Connection connectionBD = new ConnectionFactory().getConnection();
		
		AlteracaoCadastroVendaVeiculoDAO dao = new AlteracaoCadastroVendaVeiculoDAOImpl(connectionBD);
				
		int numeroPaginaAtual = ServletRequestUtils.getIntParameter(request, PARAMETRO_PAGINA, 1);
		int qtdePorPagina = 10;
		Paginador paginador = new Paginador();
		List<Veiculos> veiculos = dao.pesquisaVeiculo(pesqPlaca, pesqMarca, pesqModelo, numeroPaginaAtual, qtdePorPagina);	
		ListaPaginacao report = paginador.criarListaPaginacao(veiculos, dao.countVeiculos(pesqPlaca, pesqMarca, pesqModelo), numeroPaginaAtual);		
		model.addAttribute("itensRelatorio", report);
				
		
		mantemDadosPesquisaVendedor(model, request);
		
		model.addAttribute("marcasVeiculos", dao.getMarcasVeiculos());
		
		try {
			connectionBD.close();				
		} catch (SQLException e) {	
			LOG.error(e.getMessage(), e.getCause());
			throw new RuntimeException(e);
		}
		
		return "alteracaoCadastroVendaVeiculo/alteracaoCadastroVendaVeiculoLista";
	}
	
	@RequestMapping("/alteracaoVendaVeiculoSelecionadoVeiculo")
	public String vendaVeiculoSelecionadoVeiculo(Long id, Model model) {
			
		Connection connectionBD = new ConnectionFactory().getConnection();
		
		AlteracaoCadastroVendaVeiculoDAO dao = new AlteracaoCadastroVendaVeiculoDAOImpl(connectionBD);
				
		Veiculos veiculo = dao.pesquisaVeiculo(id);		
		
		String veiculoOpcionais = dao.buscaOpcionaisVeiculo(id);		
				
		VendaVeiculo vendaVeiculo = dao.getVendaVeiculo(id);				
		
		List<Vendedor> vendedores = dao.getVendedores();
		
		ClienteFornecedor clifor = dao.pesquisaCliente(vendaVeiculo.getCliente_id());
		
		model.addAttribute("veiculo",veiculo);
		model.addAttribute("veiculoOpcionais",veiculoOpcionais);
		model.addAttribute("valorVenda",vendaVeiculo.getValorVenda());
		model.addAttribute("vendedor",vendaVeiculo.getVendedor_id());
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		model.addAttribute("dataVenda", sdf.format(vendaVeiculo.getDataVenda().getTime()));
		model.addAttribute("vendedores",vendedores);		
		model.addAttribute("cliFor", clifor);
		model.addAttribute("exibeCliente", true);
		
		try {
			connectionBD.close();				
		} catch (SQLException e) {
			LOG.error(e.getMessage(), e.getCause());
			throw new RuntimeException(e);
		}
		
		return "alteracaoCadastroVendaVeiculo/alteracaoCadastroVendaVeiculoDados";
	}
	
	@RequestMapping("/alteracaoVendaVeiculoPesquisaCliente")
	public String pesquisaCliente(HttpServletRequest request, Model model) {
				
		String pesqNome = request.getParameter("pesqNome");
		String pesqCpf = request.getParameter("pesqCpf");
		String pesqRg = request.getParameter("pesqRg");
				
		Connection connectionBD = new ConnectionFactory().getConnection();
		
		AlteracaoCadastroVendaVeiculoDAO dao = new AlteracaoCadastroVendaVeiculoDAOImpl(connectionBD);
				
		int numeroPaginaAtual = ServletRequestUtils.getIntParameter(request, PARAMETRO_PAGINA, 1);
		int qtdePorPagina = 10;
		Paginador paginador = new Paginador();
		List<ClienteFornecedor> clisFors = dao.pesquisaCliente(pesqNome, pesqCpf, pesqRg, "ativo", numeroPaginaAtual, qtdePorPagina);
		ListaPaginacao report = paginador.criarListaPaginacao(clisFors, dao.countCliente(pesqNome, pesqCpf, pesqRg, "ativo"), numeroPaginaAtual);		
		model.addAttribute("itensRelatorio", report);
		
		mantemDadosPesquisaCliente(model, request);
		
		//Mantem dados da Tela anterior
		mantemDadosVendaVeiculo(model, request);
		
		try {
			connectionBD.close();				
		} catch (SQLException e) {
			LOG.error(e.getMessage(), e.getCause());
			throw new RuntimeException(e);
		}	
		return "alteracaoCadastroVendaVeiculo/alteracaoCadastroVendaVeiculoPesquisaCliente";
	}
	
	@RequestMapping("/alteracaoVendaVeiculoClienteSelecionado")
	public String clienteSelecionado(Long idVeiculo, Long idCliente, HttpSession session,  Model model, HttpServletRequest request) {
				
		Connection connectionBD = new ConnectionFactory().getConnection();
		
		AlteracaoCadastroVendaVeiculoDAO dao = new AlteracaoCadastroVendaVeiculoDAOImpl(connectionBD);
		
		Veiculos veiculo = dao.pesquisaVeiculo(idVeiculo);	
		
		String veiculoOpcionais = dao.buscaOpcionaisVeiculo(idVeiculo);
		
		List<Vendedor> vendedores = dao.getVendedores();
		
		ClienteFornecedor clifor = dao.pesquisaCliente(idCliente);	
		
		model.addAttribute("vendedores",vendedores);
		model.addAttribute("veiculo",veiculo);
		model.addAttribute("veiculoOpcionais",veiculoOpcionais);		
		model.addAttribute("cliFor", clifor);
		model.addAttribute("exibeCliente", true);
			
		
		//Mantem dados da Tela anterior
		mantemDadosVendaVeiculo(model, request);		
		
		try {
			connectionBD.close();				
		} catch (SQLException e) {
			LOG.error(e.getMessage(), e.getCause());
			throw new RuntimeException(e);
		}
		
		return "alteracaoCadastroVendaVeiculo/alteracaoCadastroVendaVeiculoDados";
	}
	
	@RequestMapping("/alteracaoVendaVeiculoExcluirVeiculo")
	public String vendaVeiculoExcluirVeiculo(Long idVeiculo, Model model, HttpServletRequest request) {
		
		Connection connectionBD = new ConnectionFactory().getConnection();
		
		AlteracaoCadastroVendaVeiculoDAO dao = new AlteracaoCadastroVendaVeiculoDAOImpl(connectionBD);
		
		dao.removeVendaVeiculo(idVeiculo);
		
		try {
			connectionBD.close();				
		} catch (SQLException e) {
			LOG.error(e.getMessage(), e.getCause());
			throw new RuntimeException(e);
		}
		
		return "redirect:alteracaoVendaVeiculo";
	}
	
	
	private void mantemDadosPesquisaVendedor(Model model, HttpServletRequest request){		
		model.addAttribute("pesqPlaca", request.getParameter("pesqPlaca"));		
		model.addAttribute("pesqMarca", request.getParameter("pesqMarca"));		
		model.addAttribute("pesqModelo", request.getParameter("pesqModelo"));		
	}
	
	private void mantemDadosPesquisaCliente(Model model, HttpServletRequest request){		
		model.addAttribute("pesqNome", request.getParameter("pesqNome"));
		model.addAttribute("pesqCpf", request.getParameter("pesqCpf"));
		model.addAttribute("pesqRg", request.getParameter("pesqRg"));		
	}	
	
	private void mantemDadosVendaVeiculo(Model model, HttpServletRequest request){		
		model.addAttribute("idVeiculo", request.getParameter("idVeiculo"));		
		model.addAttribute("vendedor", request.getParameter("vendedor"));
		model.addAttribute("dataVenda", request.getParameter("dataVenda"));
		
		String valorVenda = request.getParameter("valorVenda");
		if(valorVenda != null){
			valorVenda = valorVenda.replace("R$", "");
		}
		if(valorVenda != null && !valorVenda.equals("") && !valorVenda.equals("0") && valorVenda.length() > 1){		
			model.addAttribute("valorVenda",(new Double(valorVenda.substring(0,valorVenda.length()-3).replace(".", "") + valorVenda.substring(valorVenda.length()-3).replace(",", "."))));
		}else if (valorVenda != null && valorVenda.length() == 1){
			model.addAttribute("valorVenda",valorVenda);			
		}else{
			model.addAttribute("valorVenda",0);
		}
	}

}
