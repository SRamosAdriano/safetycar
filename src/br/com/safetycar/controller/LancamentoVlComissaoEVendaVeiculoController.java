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
import br.com.safetycar.modelos.GastosVeiculos;
import br.com.safetycar.modelos.Veiculos;
import br.com.safetycar.modelos.VlComissaoEVendaVeiculo;
import br.com.safetycar.paginacao.ListaPaginacao;
import br.com.safetycar.paginacao.Paginador;
import br.com.safetycar.servico.dao.LancamentoVlComissaoEVendaVeiculoDAO;
import br.com.safetycar.servico.dao.impl.LancamentoVlComissaoEVendaVeiculoDAOImpl;

@Controller
public class LancamentoVlComissaoEVendaVeiculoController {
	
	private static Logger LOG = Logger.getLogger(LancamentoVlComissaoEVendaVeiculoController.class.getName());
	
	public static final String PARAMETRO_PAGINA = "pagina";
	public static final String PARAMETRO_ID = "id";
			
	@RequestMapping("/lancamentoVlComissaoEVendaVeiculo")
	public String lancamentoVlComissaoEVendaVeiculo(Model model, HttpServletRequest request) {
		
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
		
		LancamentoVlComissaoEVendaVeiculoDAO dao = new LancamentoVlComissaoEVendaVeiculoDAOImpl(connectionBD);		
				
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
		return "lancamentoVlComissaoEVendaVeiculo/lancamentoVlComissaoEVendaVeiculoVeiculoLista";
	}
	
	@RequestMapping("/lancamentoVlComissaoEVendaVeiculoSelecionadoVeiculo")
	public String selecionadoVeiculo(long idVeiculo, Model model, HttpServletRequest request) {	
		
		Connection connectionBD = new ConnectionFactory().getConnection();
		
		LancamentoVlComissaoEVendaVeiculoDAO dao = new LancamentoVlComissaoEVendaVeiculoDAOImpl(connectionBD);
		
		Veiculos veiculoSelecionado = dao.pesquisaVeiculo(idVeiculo);
		
		List<GastosVeiculos> gastosVeiculo = dao.buscaGastosVeiculo(idVeiculo);
		double totalGastos = 0;
		if(gastosVeiculo.size() > 0){			
			for(int i = 0 ; i < gastosVeiculo.size() ; i++){				
				totalGastos = totalGastos + gastosVeiculo.get(i).getValor();
			}
		}
		
		VlComissaoEVendaVeiculo vlComissaoEVendaVeiculo =  dao.pesquisaVlComissaoEVendaVeiculo(idVeiculo);
		if(vlComissaoEVendaVeiculo != null){
			model.addAttribute("bonusComissaoVenda",vlComissaoEVendaVeiculo);
		}
		
		model.addAttribute("veiculo", veiculoSelecionado);		
		model.addAttribute("vlCompra", dao.buscaCompraVeiculo(idVeiculo).getValorCompra());
		model.addAttribute("totalGastos", totalGastos);
		
		try {
			connectionBD.close();				
		} catch (SQLException e) {	
			LOG.error(e.getMessage(), e.getCause());
			throw new RuntimeException(e);
		}
		
		return "lancamentoVlComissaoEVendaVeiculo/lancamentoVlComissaoEVendaVeiculoDados";
	}
	
	
	private void mantemDadosPesqVeiculo(Model model, HttpServletRequest request){		
		model.addAttribute("pesqPlaca", request.getParameter("pesqPlaca"));		
		model.addAttribute("pesqMarca", request.getParameter("pesqMarca"));		
		model.addAttribute("pesqModelo", request.getParameter("pesqModelo"));		
	}
}
