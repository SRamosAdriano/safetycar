package br.com.safetycar.controller.adm;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.safetycar.connection.ConnectionFactory;
import br.com.safetycar.modelos.Grupos;
import br.com.safetycar.modelos.Paginas;
import br.com.safetycar.paginacao.ListaPaginacao;
import br.com.safetycar.paginacao.Paginador;
import br.com.safetycar.servico.dao.AlteracaoCadastroGrupoDAO;
import br.com.safetycar.servico.dao.impl.AlteracaoCadastroGrupoDAOImpl;

@Controller
public class AlteracaoCadastroGrupoController {
	
	private static Logger LOG = Logger.getLogger(AlteracaoCadastroGrupoController.class.getName());

	public static final String PARAMETRO_PAGINA = "pagina";
	public static final String PARAMETRO_ID = "id";
	
	
	@RequestMapping("/alteracaoCadastroGrupo")
	public String alteracaoGrupo(Model model, HttpServletRequest request) {
				
		String pesqNome = request.getParameter("pesqNome");		
		String pesqDataCadastro = request.getParameter("pesqDataCadastro");		
		
		if(pesqNome != null && pesqNome.replaceAll(" ", "").length() == 0){
			pesqNome = null;			
		}
		if(pesqDataCadastro != null && pesqDataCadastro.replaceAll(" ", "").length() == 0){
			pesqDataCadastro = null;			
		}
		
		Calendar pesqDtCadastro = null;
		if(pesqDataCadastro != null){			
			try {
				pesqDtCadastro = Calendar.getInstance();
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				Date dt = sdf.parse(pesqDataCadastro);				
				pesqDtCadastro.setTime(dt);
			} catch (ParseException e) {
				LOG.error(e.getMessage(), e.getCause());
				e.printStackTrace();
			}						
		}
		
		Connection connectionBD = new ConnectionFactory().getConnection();
		
		AlteracaoCadastroGrupoDAO dao = new AlteracaoCadastroGrupoDAOImpl(connectionBD);		
		
		int numeroPaginaAtual = ServletRequestUtils.getIntParameter(request, PARAMETRO_PAGINA, 1);
		int qtdePorPagina = 10;
		Paginador paginador = new Paginador();
		List<Grupos> grupos = dao.pesquisaGrupos(pesqNome, pesqDtCadastro, numeroPaginaAtual, qtdePorPagina);
		ListaPaginacao report = paginador.criarListaPaginacao(grupos, dao.countGrupos(pesqNome, pesqDtCadastro), numeroPaginaAtual);		
		model.addAttribute("itensRelatorio", report);
				
		mantemDados(model, request);				
		
		try {
			connectionBD.close();				
		} catch (SQLException e) {	
			LOG.error(e.getMessage(), e.getCause());
			throw new RuntimeException(e);
		}				
		return "alteracaoCadastroGrupo/alteracaoCadastroGrupoLista";
	}
	
	
	@RequestMapping("/alteracaoCadastroGrupoSelecionadoGrupo")
	public String alteracaoGrupoSelecionadoGrupo(Long id, Model model) {
		
		Connection connectionBD = new ConnectionFactory().getConnection();
		
		AlteracaoCadastroGrupoDAO dao = new AlteracaoCadastroGrupoDAOImpl(connectionBD);
		
		Grupos grupo = dao.pesquisaGrupos(id);
		Collections.sort(grupo.getPaginas(), Paginas.POR_NOME);
		
		List<Paginas> paginasDisponiveis = dao.getPaginasDisponiveis();
				
		removeDeAPaginaExistenteEmB(paginasDisponiveis, grupo.getPaginas());
		
		model.addAttribute("grupo",grupo);
		model.addAttribute("paginasDisponiveis",paginasDisponiveis);
		model.addAttribute("paginasLiberadas",grupo.getPaginas());				
		
		try {
			connectionBD.close();				
		} catch (SQLException e) {	
			LOG.error(e.getMessage(), e.getCause());
			throw new RuntimeException(e);
		}
		
		return "alteracaoCadastroGrupo/alteracaoCadastroGrupoDados";
	}
	
	@RequestMapping("/alteracaoCadastroGrupoExcluirGrupo")
	public String excluirGrupo(Long idGrupo, Model model, HttpServletRequest request) {
		
		Connection connectionBD = new ConnectionFactory().getConnection();
		
		AlteracaoCadastroGrupoDAO dao = new AlteracaoCadastroGrupoDAOImpl(connectionBD);
		
		dao.removeGrupo(idGrupo);
		
		try {
			connectionBD.close();				
		} catch (SQLException e) {	
			LOG.error(e.getMessage(), e.getCause());
			throw new RuntimeException(e);
		}
		
		return "redirect:alteracaoCadastroGrupo";
	}
	
	private void mantemDados(Model model, HttpServletRequest request){		
		model.addAttribute("pesqNome", request.getParameter("pesqNome"));		
		model.addAttribute("pesqDataCadastro", request.getParameter("pesqDataCadastro"));	
	}
	
	private void removeDeAPaginaExistenteEmB(List<Paginas> a, List<Paginas> b){	
		if(a != null && b != null && !a.isEmpty() && !b.isEmpty() ){			
			for(int i = 0 ; i < b.size() ; i++){
				lacoInterno:
				for(int z = 0 ; z < a.size() ; z++){
					if(b.get(i).getId() == a.get(z).getId()){
						a.remove(z);						
						break lacoInterno;
					}
				}
			}
		}	
	}
	
}
