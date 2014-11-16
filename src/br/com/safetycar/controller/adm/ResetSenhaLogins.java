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
public class ResetSenhaLogins {
	
	private static Logger LOG = Logger.getLogger(ResetSenhaLogins.class.getName());
	
	public static final String PARAMETRO_PAGINA = "pagina";
	public static final String PARAMETRO_ID = "id";
	
	@RequestMapping("/resetSenhaLogins")
	public String resetSenhaLogins(Model model, HttpServletRequest request) {
				
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
		return "resetSenhaLogins/resetSenhaLoginsLista";
	}
	
	
	@RequestMapping("/resetSenhaLoginsSelecionadoUsuario")
	public String resetSenhaLoginsSelecionadoUsuario(Long id, Model model) {
		
		Connection connectionBD = new ConnectionFactory().getConnection();
		
		AlteracaoCadastroUsuarioDAO dao = new AlteracaoCadastroUsuarioDAOImpl(connectionBD);
		
		Usuarios usuario = dao.pesquisaUsuario(id);
		Collections.sort(usuario.getGrupos(), Grupos.POR_NOME);
						
		model.addAttribute("usuario",usuario);
		
		model.addAttribute("gruposLiberados",usuario.getGrupos());				
		
		try {
			connectionBD.close();				
		} catch (SQLException e) {
			LOG.error(e.getMessage(), e.getCause());
			throw new RuntimeException(e);
		}
		
		return "resetSenhaLogins/resetSenhaLoginsDados";
	}
	
	private void mantemDados(Model model, HttpServletRequest request){		
		model.addAttribute("pesqNome", request.getParameter("pesqNome"));
		model.addAttribute("pesqLogin", request.getParameter("pesqLogin"));
		model.addAttribute("pesqDataCadastro", request.getParameter("pesqDataCadastro"));	
	}

}
