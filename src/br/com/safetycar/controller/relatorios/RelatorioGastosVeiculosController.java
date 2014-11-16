package br.com.safetycar.controller.relatorios;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.safetycar.connection.ConnectionFactory;
import br.com.safetycar.modelos.relatorios.RelatorioGastosVeiculos;
import br.com.safetycar.paginacao.ListaPaginacao;
import br.com.safetycar.paginacao.Paginador;
import br.com.safetycar.relatorios.dao.RelatorioGastosVeiculosDAO;
import br.com.safetycar.relatorios.dao.Impl.RelatorioGastosVeiculosDAOImpl;

@Controller
public class RelatorioGastosVeiculosController {
	
	private static Logger LOG = Logger.getLogger(RelatorioGastosVeiculosController.class.getName());
	
	public static final String PARAMETRO_PAGINA = "pagina";
	public static final String PARAMETRO_ID = "id";
	
	@RequestMapping("/relGastosVeiculos")
	public String relGastosVeiculos(Model model, HttpServletRequest request) {
		
		String pesqPeriodoDe = request.getParameter("pesqPeriodoDe");		
		String pesqPeriodoAte = request.getParameter("pesqPeriodoAte");
		String pesqPlaca = request.getParameter("pesqPlaca");
		String pesqMarca = request.getParameter("pesqMarca");
		String pesqModelo = request.getParameter("pesqModelo");
		String pesqFornecedor = request.getParameter("pesqFornecedor");
		
		if(pesqPeriodoDe != null && pesqPeriodoDe.replaceAll(" ", "").length() == 0){
			pesqPeriodoDe = null;			
		}
		if(pesqPeriodoAte != null && pesqPeriodoAte.replaceAll(" ", "").length() == 0){
			pesqPeriodoAte = null;			
		}
		if(pesqPlaca != null && pesqPlaca.replaceAll(" ", "").length() == 0){
			pesqPlaca = null;			
		}
		if(pesqMarca != null && pesqMarca.equals("Selecione")){
			pesqMarca = null;			
		}
		if(pesqModelo != null && pesqModelo.replaceAll(" ", "").length() == 0){
			pesqModelo = null;			
		}
		if(pesqFornecedor != null && pesqFornecedor.equals("Selecione")){
			pesqFornecedor = null;			
		}
		
		
		Connection connectionBD = new ConnectionFactory().getConnection();
		
		RelatorioGastosVeiculosDAO dao = new RelatorioGastosVeiculosDAOImpl(connectionBD);
		
		if(pesqPeriodoDe == null && pesqPeriodoAte == null 
				&& pesqPlaca == null && pesqMarca == null
				&& pesqModelo == null && pesqFornecedor == null){
			model.addAttribute("exibeRel", false);
			
			if(request.getParameter("primExec") != null){
				model.addAttribute("primExec", true);
				model.addAttribute("exibeErroDataInicio", true);				
			}else{
				model.addAttribute("primExec", true);
			}
			model.addAttribute("marcasVeiculos", dao.getMarcasVeiculos());
			model.addAttribute("fornecedores", dao.getNomeForcedores());
			
			return "relatorioGastosVeiculos/relatorioGastosVeiculos";
		}
		if(pesqPeriodoDe == null){			
			model.addAttribute("exibeErroDataInicio", true);
			
			mantemDados(model, request);
			
			model.addAttribute("marcasVeiculos", dao.getMarcasVeiculos());
			model.addAttribute("fornecedores", dao.getNomeForcedores());
			
			return "relatorioGastosVeiculos/relatorioGastosVeiculos";
		}
		
		if(pesqPeriodoDe != null){			
		    Date periodDe = null;		      
		    try{  
		    periodDe = new SimpleDateFormat("dd/MM/yyyy").parse(pesqPeriodoDe);    
		    pesqPeriodoDe = new SimpleDateFormat("yyyy-MM-dd").format(periodDe);		    
		    }catch( java.text.ParseException e ){  
		    e.printStackTrace();  
		    }  
		}
		
		if(pesqPeriodoAte != null){			
			Date periodAte = null;		      
		    try{  
		    periodAte = new SimpleDateFormat("dd/MM/yyyy").parse(pesqPeriodoAte);    
		    pesqPeriodoAte = new SimpleDateFormat("yyyy-MM-dd").format(periodAte);		    
		    }catch( java.text.ParseException e ){
		    	LOG.error(e.getMessage(), e.getCause());
		    	e.printStackTrace();  
		    }  
		}		
		
		List<RelatorioGastosVeiculos> relGastosVeiculos = null;
		relGastosVeiculos = dao.pesquisaGastosVeiculos(pesqPeriodoDe, pesqPeriodoAte, pesqPlaca, pesqMarca, pesqModelo, pesqFornecedor);
		
		int numeroPaginaAtual = ServletRequestUtils.getIntParameter(request, PARAMETRO_PAGINA, 1);		
		Paginador paginador = new Paginador();
		paginador.setObjetosPorPagina(15);		
		ListaPaginacao report = paginador.paginarCollection(numeroPaginaAtual, relGastosVeiculos);		
		model.addAttribute("itensRelatorio", report);
		
		mantemDados(model, request);		
		
		model.addAttribute("marcasVeiculos", dao.getMarcasVeiculos());
		model.addAttribute("fornecedores", dao.getNomeForcedores());
		
		try {
			connectionBD.close();				
		} catch (SQLException e) {	
			LOG.error(e.getMessage(), e.getCause());
			throw new RuntimeException(e);
		}	
		
		model.addAttribute("exibeRel", true);		
		return "relatorioGastosVeiculos/relatorioGastosVeiculos";
	}
	
	@RequestMapping("/relGastosVeiculosImpressao")
	public String relGastosVeiculosImpressao(Model model, HttpServletRequest request) {
		
		String pesqPeriodoDe = request.getParameter("pesqPeriodoDe");		
		String pesqPeriodoAte = request.getParameter("pesqPeriodoAte");
		String pesqPlaca = request.getParameter("pesqPlaca");
		String pesqMarca = request.getParameter("pesqMarca");
		String pesqModelo = request.getParameter("pesqModelo");
		String pesqFornecedor = request.getParameter("pesqFornecedor");
		
		if(pesqPeriodoDe != null && pesqPeriodoDe.replaceAll(" ", "").length() == 0){
			pesqPeriodoDe = null;			
		}
		if(pesqPeriodoAte != null && pesqPeriodoAte.replaceAll(" ", "").length() == 0){
			pesqPeriodoAte = null;			
		}
		if(pesqPlaca != null && pesqPlaca.replaceAll(" ", "").length() == 0){
			pesqPlaca = null;			
		}
		if(pesqMarca != null && pesqMarca.equals("Selecione")){
			pesqMarca = null;			
		}
		if(pesqModelo != null && pesqModelo.replaceAll(" ", "").length() == 0){
			pesqModelo = null;			
		}
		if(pesqFornecedor != null && pesqFornecedor.equals("Selecione")){
			pesqFornecedor = null;			
		}		
				
		if(pesqPeriodoDe != null){			
		    Date periodDe = null;		      
		    try{  
		    periodDe = new SimpleDateFormat("dd/MM/yyyy").parse(pesqPeriodoDe);    
		    pesqPeriodoDe = new SimpleDateFormat("yyyy-MM-dd").format(periodDe);		    
		    }catch( java.text.ParseException e ){
		    	LOG.error(e.getMessage(), e.getCause());
		    	e.printStackTrace();  	
		    }  
		}
		
		if(pesqPeriodoAte != null){			
			Date periodAte = null;		      
		    try{  
		    periodAte = new SimpleDateFormat("dd/MM/yyyy").parse(pesqPeriodoAte);    
		    pesqPeriodoAte = new SimpleDateFormat("yyyy-MM-dd").format(periodAte);		    
		    }catch( java.text.ParseException e ){
		    	LOG.error(e.getMessage(), e.getCause());
		    	e.printStackTrace();  
		    }  
		}
		
		Connection connectionBD = new ConnectionFactory().getConnection();
		
		RelatorioGastosVeiculosDAO dao = new RelatorioGastosVeiculosDAOImpl(connectionBD);
		
		List<RelatorioGastosVeiculos> relGastosVeiculos = null;
		relGastosVeiculos = dao.pesquisaGastosVeiculos(pesqPeriodoDe, pesqPeriodoAte, pesqPlaca, pesqMarca, pesqModelo, pesqFornecedor);
		
		int numeroPaginaAtual = ServletRequestUtils.getIntParameter(request, PARAMETRO_PAGINA, 1);		
		Paginador paginador = new Paginador();
		paginador.setObjetosPorPagina(1500000);		
		ListaPaginacao report = paginador.paginarCollection(numeroPaginaAtual, relGastosVeiculos);		
		model.addAttribute("itensRelatorio", report);
		
		mantemDados(model, request);		
		
		model.addAttribute("marcasVeiculos", dao.getMarcasVeiculos());
		model.addAttribute("fornecedores", dao.getNomeForcedores());
		
		try {
			connectionBD.close();				
		} catch (SQLException e) {		
			LOG.error(e.getMessage(), e.getCause());
			throw new RuntimeException(e);
		}	
		
		model.addAttribute("exibeRel", true);		
		return "relatorioGastosVeiculos/relatorioGastosVeiculosImpressao";
	}
	
	private void mantemDados(Model model, HttpServletRequest request){		
		model.addAttribute("pesqPeriodoDe", request.getParameter("pesqPeriodoDe"));		
		model.addAttribute("pesqPeriodoAte", request.getParameter("pesqPeriodoAte"));		
		model.addAttribute("pesqPlaca", request.getParameter("pesqPlaca"));
		model.addAttribute("pesqMarca", request.getParameter("pesqMarca"));
		model.addAttribute("pesqModelo", request.getParameter("pesqModelo"));
		model.addAttribute("pesqFornecedor", request.getParameter("pesqFornecedor"));
	}

}
