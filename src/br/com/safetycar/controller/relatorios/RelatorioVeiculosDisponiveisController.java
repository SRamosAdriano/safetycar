package br.com.safetycar.controller.relatorios;

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
import br.com.safetycar.modelos.relatorios.RelatorioVeiculosDisponiveis;
import br.com.safetycar.paginacao.ListaPaginacao;
import br.com.safetycar.paginacao.Paginador;
import br.com.safetycar.relatorios.dao.RelatorioVeiculosDisponiveisDAO;
import br.com.safetycar.relatorios.dao.Impl.RelatorioVeiculosDisponiveisDAOImpl;

@Controller
public class RelatorioVeiculosDisponiveisController {
	
	private static Logger LOG = Logger.getLogger(RelatorioVeiculosDisponiveisController.class.getName());
	
	public static final String PARAMETRO_PAGINA = "pagina";
	public static final String PARAMETRO_ID = "id";
	
	
	@RequestMapping("/veiculosDisponiveis")
	public String veiculosDisponiveis(Model model, HttpServletRequest request) {
		
		String pesqPlaca = request.getParameter("pesqPlaca");		
		String pesqMarca = request.getParameter("pesqMarca");
		String pesqModelo = request.getParameter("pesqModelo");

		if(pesqPlaca != null){
			if(pesqPlaca.replaceAll(" ", "").length() == 0){
				pesqPlaca = null;
			}
		}
		if(pesqMarca != null){
			if(pesqMarca.equals("Selecione")){
				pesqMarca = null;
			}
		}
		if(pesqModelo != null){
			if(pesqModelo.replaceAll(" ", "").length() == 0){
				pesqModelo = null;
			}
		}
		
		Connection connectionBD = new ConnectionFactory().getConnection();
		
		RelatorioVeiculosDisponiveisDAO dao = new RelatorioVeiculosDisponiveisDAOImpl(connectionBD);
		
		List<RelatorioVeiculosDisponiveis> veiculosDispVenda = dao.pesquisaVeiculosDisponiveisParaVenda(pesqPlaca, pesqMarca, pesqModelo);
		
		int numeroPaginaAtual = ServletRequestUtils.getIntParameter(request, PARAMETRO_PAGINA, 1);		
		Paginador paginador = new Paginador();
		paginador.setObjetosPorPagina(15);
		ListaPaginacao report = paginador.paginarCollection(numeroPaginaAtual, veiculosDispVenda);		
		model.addAttribute("itensRelatorio", report);
		
		mantemDados(model, request);
		
		model.addAttribute("marcasVeiculos", dao.getMarcasVeiculos());
		
		try {
			connectionBD.close();				
		} catch (SQLException e) {
			LOG.error(e.getMessage(), e.getCause());
			throw new RuntimeException(e);
		}	
		
		return "relatorioVeiculosDisponiveis/relatorioVeiculosDisponiveis";		
	}
	
	@RequestMapping("/veiculosDisponiveisImpressao")
	public String veiculosDisponiveisImpressao(Model model, HttpServletRequest request) {
		
		String pesqPlaca = request.getParameter("pesqPlaca");		
		String pesqMarca = request.getParameter("pesqMarca");
		String pesqModelo = request.getParameter("pesqModelo");
		
		if(pesqPlaca != null){
			if(pesqPlaca.replaceAll(" ", "").length() == 0){
				pesqPlaca = null;
			}
		}
		if(pesqMarca != null){
			if(pesqMarca.equals("Selecione") || pesqMarca.equals("")){
				pesqMarca = null;
			}
		}
		if(pesqModelo != null){
			if(pesqModelo.replaceAll(" ", "").length() == 0){
				pesqModelo = null;
			}
		}
		
		Connection connectionBD = new ConnectionFactory().getConnection();
		
		RelatorioVeiculosDisponiveisDAO dao = new RelatorioVeiculosDisponiveisDAOImpl(connectionBD);
		
		List<RelatorioVeiculosDisponiveis> veiculosDispVenda = dao.pesquisaVeiculosDisponiveisParaVenda(pesqPlaca, pesqMarca, pesqModelo);
		
		if(veiculosDispVenda != null && !veiculosDispVenda.isEmpty()){
			for(int i = 0 ; i < veiculosDispVenda.size() ; i++){
				veiculosDispVenda.set(i, dao.pesquisaVeiculo(Long.valueOf(veiculosDispVenda.get(i).getVeiculo_id())));
				veiculosDispVenda.get(i).setOpcionais(dao.buscaOpcionaisVeiculo(veiculosDispVenda.get(i).getVeiculo_id()));
				veiculosDispVenda.get(i).setValorVenda(dao.pesquisaVlVendaVeiculo(veiculosDispVenda.get(i).getVeiculo_id()));
			}
		}
		model.addAttribute("veiculosDispVenda", veiculosDispVenda);
		
		
		
		try {
			connectionBD.close();				
		} catch (SQLException e) {
			LOG.error(e.getMessage(), e.getCause());
			throw new RuntimeException(e);
		}	
		
		return "relatorioVeiculosDisponiveis/relatorioVeiculosDisponiveisImpressao";
	}
	
	private void mantemDados(Model model, HttpServletRequest request){		
		model.addAttribute("pesqPlaca", request.getParameter("pesqPlaca"));		
		model.addAttribute("pesqMarca", request.getParameter("pesqMarca"));		
		model.addAttribute("pesqModelo", request.getParameter("pesqModelo"));		
	}

}
