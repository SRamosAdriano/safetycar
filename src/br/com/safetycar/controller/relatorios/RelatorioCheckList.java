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
import br.com.safetycar.modelos.Veiculos;
import br.com.safetycar.paginacao.ListaPaginacao;
import br.com.safetycar.paginacao.Paginador;
import br.com.safetycar.relatorios.dao.RelatorioCheckListDAO;
import br.com.safetycar.relatorios.dao.Impl.RelatorioCheckListDAOImpl;

@Controller
public class RelatorioCheckList {
	
	private static Logger LOG = Logger.getLogger(RelatorioCheckList.class.getName());
	
	public static final String PARAMETRO_PAGINA = "pagina";
	public static final String PARAMETRO_ID = "id";
	
	
	@RequestMapping("/checkList")
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
		
		RelatorioCheckListDAO dao = new RelatorioCheckListDAOImpl(connectionBD);
		
		List<Veiculos> veiculosDispVenda = dao.pesquisaVeiculo(pesqPlaca, pesqMarca, pesqModelo);
		
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
		
		return "relatorioCheckList/checkListLista";
	}
	
	@RequestMapping("/checkListImpressao")
	public String checkListImpressao(Model model, long idVeiculo) {
				
		Connection connectionBD = new ConnectionFactory().getConnection();
		
		RelatorioCheckListDAO dao = new RelatorioCheckListDAOImpl(connectionBD);
		
		dao.pesquisaVeiculo(idVeiculo);
		
		model.addAttribute("veiculo", dao.pesquisaVeiculo(idVeiculo));		
		
		try {
			connectionBD.close();				
		} catch (SQLException e) {
			LOG.error(e.getMessage(), e.getCause());
			throw new RuntimeException(e);
		}	
		
		return "relatorioCheckList/checkListImpressao";
	}
	
	private void mantemDados(Model model, HttpServletRequest request){		
		model.addAttribute("pesqPlaca", request.getParameter("pesqPlaca"));		
		model.addAttribute("pesqMarca", request.getParameter("pesqMarca"));		
		model.addAttribute("pesqModelo", request.getParameter("pesqModelo"));		
	}

}
