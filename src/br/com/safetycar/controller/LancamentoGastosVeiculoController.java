package br.com.safetycar.controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.safetycar.connection.ConnectionFactory;
import br.com.safetycar.modelos.FornecedorDespesas;
import br.com.safetycar.modelos.GastosVeiculos;
import br.com.safetycar.modelos.Veiculos;
import br.com.safetycar.paginacao.ListaPaginacao;
import br.com.safetycar.paginacao.Paginador;
import br.com.safetycar.servico.dao.LancamentoGastosVeiculoDAO;
import br.com.safetycar.servico.dao.impl.LancamentoGastosVeiculoDAOImpl;

@Controller
public class LancamentoGastosVeiculoController {
	
	private static Logger LOG = Logger.getLogger(LancamentoGastosVeiculoController.class.getName());
	
	public static final String PARAMETRO_PAGINA = "pagina";
	public static final String PARAMETRO_ID = "id";
		
	@RequestMapping("/gastosVeiculo")
	public String lancamentoGastosVeiculo(Model model, HttpServletRequest request) {
		
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
		
		LancamentoGastosVeiculoDAO dao = new LancamentoGastosVeiculoDAOImpl(connectionBD);		
				
		int numeroPaginaAtual = ServletRequestUtils.getIntParameter(request, PARAMETRO_PAGINA, 1);
		int qtdePorPagina = 10;
		Paginador paginador = new Paginador();
		List<Veiculos> veiculos = dao.pesquisaVeiculo(pesqPlaca, pesqMarca, pesqModelo, numeroPaginaAtual, qtdePorPagina);
		ListaPaginacao report = paginador.criarListaPaginacao(veiculos, dao.countVeiculo(pesqPlaca, pesqMarca, pesqModelo), numeroPaginaAtual);		
		model.addAttribute("itensRelatorio", report);
		
		mantemDadosPesqVeiculo(model, request);
		
		model.addAttribute("marcasVeiculos", dao.getMarcasVeiculos());
		
		try {
			connectionBD.close();				
		} catch (SQLException e) {
			LOG.error(e.getMessage(), e.getCause());
			throw new RuntimeException(e);
		}
		
		return "lancamentoGastosVeiculo/lancamentoGastosVeiculoVeiculoLista";
	}
	
	@RequestMapping("/gastosVeiculoSelecionadoVeiculo")
	public String selecionadoVeiculo(long idVeiculo, Model model, HttpServletRequest request) {		
				
		Connection connectionBD = new ConnectionFactory().getConnection();
		
		LancamentoGastosVeiculoDAO dao = new LancamentoGastosVeiculoDAOImpl(connectionBD);
		
		Veiculos veiculoSelecionado = dao.pesquisaVeiculo(idVeiculo);
		
		List<GastosVeiculos> gastosVeiculo = dao.buscaGastosVeiculo(idVeiculo); 
		
		List<FornecedorDespesas> dadosForDesp =  new ArrayList<FornecedorDespesas>();
		double totalGastos = 0;
		if(gastosVeiculo.size() > 0){			
			for(int i = 0 ; i < gastosVeiculo.size() ; i++){
				dadosForDesp.add(dao.pesquisaFornecedorDespesas(gastosVeiculo.get(i).getFornecedordespesas_id()));
				
				totalGastos = totalGastos + gastosVeiculo.get(i).getValor();
			}
			
			model.addAttribute("tabelaGastosVeiculo", gastosVeiculo);
			model.addAttribute("tabelaGastosVeiculoListaNomeFor", dadosForDesp);
			model.addAttribute("totalGastos", totalGastos);
			
			model.addAttribute("existeGastoVeiculo", true);
			model.addAttribute("naoExisteGastoVeiculo", false);
		}else{
			model.addAttribute("existeGastoVeiculo", false);
			model.addAttribute("naoExisteGastoVeiculo", true);
		}
		
		model.addAttribute("veiculo", veiculoSelecionado);		
		model.addAttribute("valorVeiculo", dao.buscaCompraVeiculo(idVeiculo).getValorCompra());		
		
		model.addAttribute("idVeiculo", veiculoSelecionado.getId());
		model.addAttribute("fornededorNaoSele", true);
		model.addAttribute("fornededorSele", false);
		
		try {
			connectionBD.close();				
		} catch (SQLException e) {
			LOG.error(e.getMessage(), e.getCause());
			throw new RuntimeException(e);
		}
		
		return "lancamentoGastosVeiculo/lancamentoGastosVeiculoDados";
	}
	
	@RequestMapping("/gastosVeiculoSelecionaForDesp")
	public String selecionaForDesp(long idVeiculo, Model model, HttpServletRequest request) {		
		
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
		
		LancamentoGastosVeiculoDAO dao = new LancamentoGastosVeiculoDAOImpl(connectionBD);
				
		int numeroPaginaAtual = ServletRequestUtils.getIntParameter(request, PARAMETRO_PAGINA, 1);
		int qtdePorPagina = 10;
		Paginador paginador = new Paginador();
		List<FornecedorDespesas> fornecedorDespesas = dao.pesquisaFornecedorDespesas(pesqNome, pesqContato, pesqTelefone, numeroPaginaAtual, qtdePorPagina);
		ListaPaginacao report = paginador.criarListaPaginacao(fornecedorDespesas, dao.countFornecedorDespesas(pesqNome, pesqContato, pesqTelefone), numeroPaginaAtual);		
		model.addAttribute("itensRelatorio", report);
		
		mantemDadosPesqForDesp(model, request);
		
		try {
			connectionBD.close();				
		} catch (SQLException e) {	
			LOG.error(e.getMessage(), e.getCause());
			throw new RuntimeException(e);
		}
		
		return "lancamentoGastosVeiculo/lancamentoGastosVeiculoFornecedorDespesasLista";
	}
	
	@RequestMapping("/gastosVeiculoSelecionadoForDesp")
	public String selecionadoForDesp(long idVeiculo, long idForDesp, Model model, HttpServletRequest request) {	
		
		Connection connectionBD = new ConnectionFactory().getConnection();
		
		LancamentoGastosVeiculoDAO dao = new LancamentoGastosVeiculoDAOImpl(connectionBD);
		
		Veiculos veiculoSelecionado = dao.pesquisaVeiculo(idVeiculo);		
		model.addAttribute("veiculo", veiculoSelecionado);
		
		FornecedorDespesas fornecedorDespesasSelecionado = dao.pesquisaFornecedorDespesas(idForDesp);
		model.addAttribute("fornecedorDespesas", fornecedorDespesasSelecionado);
		
		List<GastosVeiculos> gastosVeiculo = dao.buscaGastosVeiculo(idVeiculo); 
		
		List<FornecedorDespesas> dadosForDesp =  new ArrayList<FornecedorDespesas>();
		double totalGastos = 0;
		if(gastosVeiculo.size() > 0){			
			for(int i = 0 ; i < gastosVeiculo.size() ; i++){
				dadosForDesp.add(dao.pesquisaFornecedorDespesas(gastosVeiculo.get(i).getFornecedordespesas_id()));
				
				totalGastos = totalGastos + gastosVeiculo.get(i).getValor();
			}
			
			model.addAttribute("tabelaGastosVeiculo", gastosVeiculo);
			model.addAttribute("tabelaGastosVeiculoListaNomeFor", dadosForDesp);
			model.addAttribute("totalGastos", totalGastos);
			
			model.addAttribute("existeGastoVeiculo", true);
			model.addAttribute("naoExisteGastoVeiculo", false);
		}else{
			model.addAttribute("existeGastoVeiculo", false);
			model.addAttribute("naoExisteGastoVeiculo", true);
		}
		
		model.addAttribute("veiculo", veiculoSelecionado);		
		model.addAttribute("valorVeiculo", dao.buscaCompraVeiculo(idVeiculo).getValorCompra());
		
		model.addAttribute("idVeiculo", request.getParameter("idVeiculo"));
		model.addAttribute("idForDesp", request.getParameter("idForDesp"));		
		model.addAttribute("fornededorNaoSele", false);
		model.addAttribute("fornededorSele", true);
		
		try {
			connectionBD.close();				
		} catch (SQLException e) {	
			LOG.error(e.getMessage(), e.getCause());
			throw new RuntimeException(e);
		}
		
		return "lancamentoGastosVeiculo/lancamentoGastosVeiculoDados";
	}
	
	@RequestMapping("/gastosVeiculoRemoveGasto")
	public String gastosVeiculoRemoveGasto(long idVeiculo, long idGastoVeiculo, Model model, HttpServletRequest request) {
		
		String idForDesp = request.getParameter("idForDesp");
		Connection connectionBD = new ConnectionFactory().getConnection();
		
		LancamentoGastosVeiculoDAO dao = new LancamentoGastosVeiculoDAOImpl(connectionBD);
		
		if(idGastoVeiculo > 0){
			dao.removeGastosVeiculo(idGastoVeiculo);
		}
		
		try {
			connectionBD.close();				
		} catch (SQLException e) {
			LOG.error(e.getMessage(), e.getCause());
			throw new RuntimeException(e);
		}
		
		if(idForDesp != null){
			if(idForDesp.equals("")){
				return "redirect:gastosVeiculoSelecionadoVeiculo?idVeiculo="+ idVeiculo;
			}else{
				return "redirect:gastosVeiculoSelecionadoForDesp?idVeiculo="+ idVeiculo + "&idForDesp=" + idForDesp;
			}			
		}else{
			return "redirect:gastosVeiculoSelecionadoVeiculo?idVeiculo="+ idVeiculo;
		}
	}
	
	
	private void mantemDadosPesqVeiculo(Model model, HttpServletRequest request){		
		model.addAttribute("pesqPlaca", request.getParameter("pesqPlaca"));		
		model.addAttribute("pesqMarca", request.getParameter("pesqMarca"));		
		model.addAttribute("pesqModelo", request.getParameter("pesqModelo"));		
	}
	
	private void mantemDadosPesqForDesp(Model model, HttpServletRequest request){		
		model.addAttribute("pesqNome", request.getParameter("pesqNome"));		
		model.addAttribute("pesqContato", request.getParameter("pesqContato"));		
		model.addAttribute("pesqTelefone", request.getParameter("pesqTelefone"));
		
		model.addAttribute("idVeiculo", request.getParameter("idVeiculo"));
	}	
}