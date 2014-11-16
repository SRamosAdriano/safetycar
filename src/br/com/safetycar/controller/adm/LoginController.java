package br.com.safetycar.controller.adm;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.safetycar.connection.ConnectionFactory;
import br.com.safetycar.modelos.Usuarios;
import br.com.safetycar.servico.dao.LoginDAO;
import br.com.safetycar.servico.dao.impl.LoginDAOImpl;

@Controller
public class LoginController {
	
	private static Logger LOG = Logger.getLogger(LoginController.class.getName());
	
	@RequestMapping("/login")
	public String login(Model model, HttpServletRequest request) {
		
		String login = request.getParameter("login");
		model.addAttribute("login",login);
		
		String erroLoginSenha = request.getParameter("erroLoginSenha");
		model.addAttribute("erroLoginSenha",erroLoginSenha);
		
		String blqLogin = request.getParameter("blqLogin");
		model.addAttribute("blqLogin",blqLogin);
				
		String alteraSenha = request.getParameter("alteraSenha");
		model.addAttribute("alteraSenha",alteraSenha);
		
		String erroSenhasDiferentes = request.getParameter("erroSenhasDiferentes");
		model.addAttribute("erroSenhasDiferentes",erroSenhasDiferentes);
		
		String erroSenhaPequena = request.getParameter("erroSenhaPequena");
		model.addAttribute("erroSenhaPequena",erroSenhaPequena);
				
		String erroSenhasIguais = request.getParameter("erroSenhasIguais");
		model.addAttribute("erroSenhasIguais",erroSenhasIguais);
		
		return "login/login";		
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:login";
	}
	
	@RequestMapping("efetuaLogin")
	public String efetuaLogin(HttpServletRequest request, HttpSession session, Model model) {
		
		String login = request.getParameter("login");
		String senha = request.getParameter("senha");
		
		boolean erro = false;
		
		if(login == null || login.equals("") || login.length() < 4){
			erro = true;
			return "redirect:login?erroLoginSenha="+ erro + "&login=" + login;
		}
		
		if(senha == null || senha.equals("") || senha.length() < 4){
			erro = true;
			return "redirect:login?erroLoginSenha="+ erro + "&login=" + login;
		}else{
			senha = Usuarios.criptografarSenha(senha);
		}
		
		Connection connectionBD = new ConnectionFactory().getConnection();
		
		LoginDAO dao = new LoginDAOImpl(connectionBD);		
		
		String retorno = "";
		
		if(dao.existeUsuario(login)) {		
			if(dao.getErroLogin(login) >= 5){					
				erro = true;
				retorno = "redirect:login?blqLogin="+ erro + "&login=" + login;
			}else if(dao.verificaSenhaLogin(login, senha)){		
				if(dao.getPrimeiroLogin(login)){					
					String senhaNova = request.getParameter("senhaNova");
					String senhaNovaConf = request.getParameter("senhaNovaConf");
					
					if (senhaNova != null && senhaNovaConf != null){						
						if(senhaNova.equals(senhaNovaConf)){
							if(senhaNova.replaceAll(" ", "").length() > 4){
								senhaNova = Usuarios.criptografarSenha(senhaNova);
								senhaNovaConf = Usuarios.criptografarSenha(senhaNovaConf);								
								
								Usuarios usuario = dao.pesquisaUsuario(login);
								usuario.setPrimeiroLogin(false);
								usuario.setErroLogin(0);
								usuario.setSenha(senhaNova);
								dao.atualizaSenha(usuario);
								usuario.setSenha(null);
								
								dao.usuarioLogado(usuario.getLogin());
								
								model.addAttribute("usuario", usuario);
								session.setAttribute("usrLogado", usuario);
																
								retorno = "redirect:loginInicio";
							}else{									
								erro = true;
								retorno = "redirect:login?alteraSenha="+ erro + "&erroSenhaPequena=" + erro + "&login=" + login;
							}						
						}else{							
							erro = true;
							retorno = "redirect:login?alteraSenha="+ erro + "&erroSenhasDiferentes=" + erro + "&login=" + login;
						}						
					}else{
						erro = true;
						retorno = "redirect:login?alteraSenha="+ erro + "&login=" + login;
					}
					
				}else{					
					dao.usuarioLogado(login);
					
					Usuarios usuario = dao.pesquisaUsuario(login);
					
					model.addAttribute("usuario", usuario);
					session.setAttribute("usrLogado", usuario);
					
					retorno = "redirect:loginInicio";						
				}
			}else{
				dao.somaErroLogin(login);				
				
				erro = true;
				retorno = "redirect:login?erroLoginSenha="+ erro + "&login=" + login;
			}			
		}else{			
			erro = true;
			retorno = "redirect:login?erroLoginSenha="+ erro + "&login=" + login;
		}
		
		try {
			connectionBD.close();				
		} catch (SQLException e) {
			LOG.error(e.getMessage(), e.getCause());
			throw new RuntimeException(e);
		}
		return retorno;		
	}
	
	@RequestMapping("/loginInicio")
	public String inicio(Model model, HttpServletRequest request) {
		
		Usuarios usuario = (Usuarios)request.getSession().getAttribute("usrLogado");
		
		if(usuario != null){			
			model.addAttribute("usuario", usuario);
			
			return "login/loginInicio";
		}else{
			return "redirect:login";
		}				
	}
	
	@RequestMapping("/erroPermissao")
	public String erroPermissao(Model model, HttpServletRequest request) {
		
		return "erros/erroPermissao";		
	}

}
