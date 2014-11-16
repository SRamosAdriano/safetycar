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
import br.com.safetycar.modelos.Usuarios;
import br.com.safetycar.paginacao.ListaPaginacao;
import br.com.safetycar.paginacao.Paginador;
import br.com.safetycar.servico.dao.AlteracaoCadastroUsuarioDAO;
import br.com.safetycar.servico.dao.impl.AlteracaoCadastroUsuarioDAOImpl;

@Controller
public class AlteracaoCadastroUsuariosController {
	
	private static Logger LOG = Logger.getLogger(AlteracaoCadastroUsuariosController.class.getName());
	
	public static final String PARAMETRO_PAGINA = "pagina";
	public static final String PARAMETRO_ID = "id";
	
	@RequestMapping("/alteracaoCadastroUsuarios")
	public String alteracaoCadastroUsuario(Model model, HttpServletRequest request) {
				
		String pesqNome = request.getParameter("pesqNome");
		String pesqLogin = request.getParameter("pesqLogin");
		String pesqDataCadastro = request.getParameter("pesqDataCadastro");		
		
		if(pesqNome != null && pesqNome.replaceAll(" ", "").length() == 0){
			pesqNome = null;			
		}
		if(pesqLogin != null && pesqLogin.replaceAll(" ", "").length() == 0){
			pesqLogin = null;			
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
		
		AlteracaoCadastroUsuarioDAO dao = new AlteracaoCadastroUsuarioDAOImpl(connectionBD);		
				
		int numeroPaginaAtual = ServletRequestUtils.getIntParameter(request, PARAMETRO_PAGINA, 1);
		int qtdePorPagina = 10;
		Paginador paginador = new Paginador();
		List<Usuarios> usuarios = dao.pesquisaUsuarios(pesqNome, pesqLogin, pesqDtCadastro, numeroPaginaAtual, qtdePorPagina);
		ListaPaginacao report = paginador.criarListaPaginacao(usuarios, dao.countUsuarios(pesqNome, pesqLogin, pesqDtCadastro), numeroPaginaAtual);		
		model.addAttribute("itensRelatorio", report);
				
		mantemDados(model, request);				
		
		try {
			connectionBD.close();				
		} catch (SQLException e) {
			LOG.error(e.getMessage(), e.getCause());
			throw new RuntimeException(e);
		}				
		return "alteracaoCadastroUsuarios/alteracaoCadastroUsuariosLista";
	}
	
	
	@RequestMapping("/alteracaoCadastroUsuariosSelecionadoUsuario")
	public String alteracaoUsuarioSelecionado(Long id, Model model) {
		
		Connection connectionBD = new ConnectionFactory().getConnection();
		
		AlteracaoCadastroUsuarioDAO dao = new AlteracaoCadastroUsuarioDAOImpl(connectionBD);
		
		Usuarios usuario = dao.pesquisaUsuario(id);
		Collections.sort(usuario.getGrupos(), Grupos.POR_NOME);
		
		List<Grupos> gruposDisponiveis = dao.getGruposDisponiveis();
				
		removeDeAPaginaExistenteEmB(gruposDisponiveis, usuario.getGrupos());
		
		model.addAttribute("usuario",usuario);
		model.addAttribute("gruposDisponiveis",gruposDisponiveis);
		model.addAttribute("gruposLiberados",usuario.getGrupos());				
		
		try {
			connectionBD.close();				
		} catch (SQLException e) {
			LOG.error(e.getMessage(), e.getCause());
			throw new RuntimeException(e);
		}
		
		return "alteracaoCadastroUsuarios/alteracaoCadastroUsuariosDados";
	}
	
	@RequestMapping("/alteracaoCadastroUsuariosExcluirUsuario")
	public String excluirUsuario(Long idUsuario, Model model, HttpServletRequest request) {
		
		Connection connectionBD = new ConnectionFactory().getConnection();
		
		AlteracaoCadastroUsuarioDAO dao = new AlteracaoCadastroUsuarioDAOImpl(connectionBD);
		
		dao.removeUsuario(idUsuario);
		
		try {
			connectionBD.close();				
		} catch (SQLException e) {
			LOG.error(e.getMessage(), e.getCause());
			throw new RuntimeException(e);
		}
		
		return "redirect:alteracaoCadastroUsuarios";
	}
	
	private void mantemDados(Model model, HttpServletRequest request){		
		model.addAttribute("pesqNome", request.getParameter("pesqNome"));
		model.addAttribute("pesqLogin", request.getParameter("pesqLogin"));
		model.addAttribute("pesqDataCadastro", request.getParameter("pesqDataCadastro"));	
	}
	
	private void removeDeAPaginaExistenteEmB(List<Grupos> a, List<Grupos> b){	
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
