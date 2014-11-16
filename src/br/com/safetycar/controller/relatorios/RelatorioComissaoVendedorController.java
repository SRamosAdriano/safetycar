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
import br.com.safetycar.modelos.relatorios.RelatorioComissaoVendedor;
import br.com.safetycar.paginacao.ListaPaginacao;
import br.com.safetycar.paginacao.Paginador;
import br.com.safetycar.relatorios.dao.RelatorioComissaoVendedorDAO;
import br.com.safetycar.relatorios.dao.Impl.RelatorioComissaoVendedorDAOImpl;

@Controller
public class RelatorioComissaoVendedorController {
	
	private static Logger LOG = Logger.getLogger(RelatorioComissaoVendedorController.class.getName());
	
	public static final String PARAMETRO_PAGINA = "pagina";
	public static final String PARAMETRO_ID = "id";
	
	@RequestMapping("/relComissaoVendedor")
	public String relVeiculosVendidos(Model model, HttpServletRequest request) {
		
		String pesqPeriodoDe = request.getParameter("pesqPeriodoDe");		
		String pesqPeriodoAte = request.getParameter("pesqPeriodoAte");
		String pesqVendedor = request.getParameter("pesqVendedor");
		
		Connection connectionBD = new ConnectionFactory().getConnection();
		RelatorioComissaoVendedorDAO dao = new RelatorioComissaoVendedorDAOImpl(connectionBD);
		
		if(pesqPeriodoDe != null && pesqPeriodoDe.replaceAll(" ", "").length() == 0){
			pesqPeriodoDe = null;			
		}
		if(pesqPeriodoAte != null && pesqPeriodoAte.replaceAll(" ", "").length() == 0){
			pesqPeriodoAte = null;			
		}
		if(pesqVendedor != null && pesqVendedor.equals("Selecione")){
			pesqVendedor = null;			
		}
		
		
		if(pesqPeriodoDe == null && pesqPeriodoAte == null && pesqVendedor == null){
			model.addAttribute("exibeRel", false);
			
			if(request.getParameter("primExec") != null){
				model.addAttribute("primExec", true);
				model.addAttribute("exibeErroDataInicio", true);
				model.addAttribute("vendedores", dao.getVendedores());
				
			}else{
				model.addAttribute("primExec", true);
			}
			
			model.addAttribute("vendedores", dao.getVendedores());
			return "relatorioComissaoVendedor/relatorioComissaoVendedor";
		}
		if(pesqPeriodoDe == null){			
			model.addAttribute("exibeErroDataInicio", true);
			model.addAttribute("vendedores", dao.getVendedores());
			
			mantemDados(model, request);
			return "relatorioComissaoVendedor/relatorioComissaoVendedor";
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
		
		List<RelatorioComissaoVendedor> comissaoVendedor = dao.pesquisaComissaoVendedor(pesqPeriodoDe, pesqPeriodoAte, pesqVendedor);
				
		
		int numeroPaginaAtual = ServletRequestUtils.getIntParameter(request, PARAMETRO_PAGINA, 1);		
		Paginador paginador = new Paginador();
		paginador.setObjetosPorPagina(15);
		ListaPaginacao report = paginador.paginarCollection(numeroPaginaAtual, comissaoVendedor);		
		model.addAttribute("itensRelatorio", report);
		
		mantemDados(model, request);		
		
		model.addAttribute("vendedores", dao.getVendedores());
		model.addAttribute("exibeRel", true);
		
		try {
			connectionBD.close();				
		} catch (SQLException e) {	
			LOG.error(e.getMessage(), e.getCause());
			throw new RuntimeException(e);
		}				
		return "relatorioComissaoVendedor/relatorioComissaoVendedor";
	}
	
	
	@RequestMapping("/relComissaoVendedorImpressao")
	public String relVeiculosVendidosImpressao(Model model, HttpServletRequest request) {
		
		String pesqPeriodoDe = request.getParameter("pesqPeriodoDe");		
		String pesqPeriodoAte = request.getParameter("pesqPeriodoAte");
		String pesqVendedor = request.getParameter("pesqVendedor");
		
		
		if(pesqPeriodoDe != null && pesqPeriodoDe.replaceAll(" ", "").length() == 0){
			pesqPeriodoDe = null;			
		}
		if(pesqPeriodoAte != null && pesqPeriodoAte.replaceAll(" ", "").length() == 0){
			pesqPeriodoAte = null;			
		}
		if(pesqVendedor != null && pesqVendedor.equals("Selecione")){
			pesqVendedor = null;			
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
		RelatorioComissaoVendedorDAO dao = new RelatorioComissaoVendedorDAOImpl(connectionBD);
		
		List<RelatorioComissaoVendedor> comissaoVendedor = dao.pesquisaComissaoVendedor(pesqPeriodoDe, pesqPeriodoAte, pesqVendedor);
				
						
		int numeroPaginaAtual = ServletRequestUtils.getIntParameter(request, PARAMETRO_PAGINA, 1);		
		Paginador paginador = new Paginador();
		paginador.setObjetosPorPagina(1500000);
		ListaPaginacao report = paginador.paginarCollection(numeroPaginaAtual, comissaoVendedor);		
		model.addAttribute("itensRelatorio", report);		
		
		try {
			connectionBD.close();				
		} catch (SQLException e) {		
			LOG.error(e.getMessage(), e.getCause());
			throw new RuntimeException(e);
		}		
				
		return "relatorioComissaoVendedor/relatorioComissaoVendedorImpressao";
	}
	
	private void mantemDados(Model model, HttpServletRequest request){		
		model.addAttribute("pesqPeriodoDe", request.getParameter("pesqPeriodoDe"));		
		model.addAttribute("pesqPeriodoAte", request.getParameter("pesqPeriodoAte"));
		model.addAttribute("pesqVendedor", request.getParameter("pesqVendedor"));		
	}

}
