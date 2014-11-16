package br.com.safetycar.controller.adm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import br.com.safetycar.modelos.Grupos;
import br.com.safetycar.modelos.Paginas;
import br.com.safetycar.modelos.Usuarios;

public class AutorizadorInterceptor extends HandlerInterceptorAdapter {

	public boolean preHandle(HttpServletRequest request,
								HttpServletResponse response,
								Object controller) throws Exception {
		
		String uri = request.getRequestURI();
		if (uri.endsWith("login") || uri.endsWith("efetuaLogin")) {
			return true;
		}
		if (request.getSession().getAttribute("usrLogado") != null) {
			/* Verifica permissao usuario */
			Usuarios usuario = (Usuarios) request.getSession().getAttribute("usrLogado");
			if(usuario.getErroLogin() >= 5){
				/* Erro Usuário bloqueado */			
				response.sendRedirect("login");
				return false;	
			}else if(usuario.getGrupos() != null){				
				/* Caso o usuario nao possua nenhum grupo associado */
				if( usuario.getGrupos().isEmpty() &&
						(uri.endsWith("loginInicio") || uri.endsWith("logout")
								|| uri.endsWith("erroPermissao") || uri.endsWith("alteraSenhaUsuario")) ){
					return true;
				}				
				for (Grupos grupo : usuario.getGrupos()) {
					if(grupo.getPaginas() != null && !grupo.getPaginas().isEmpty()){
						for (Paginas pagina : grupo.getPaginas()) {
							if(pagina.getUrl() != null && !pagina.getUrl().isEmpty()){
								for (String url : pagina.getUrl()) {
									if(uri.endsWith("loginInicio")
											|| uri.endsWith("logout")
											|| uri.endsWith("erroPermissao")
											|| uri.endsWith("alteraSenhaUsuario") 
											|| uri.endsWith(url)){
										return true;							
									}
								}
							}							
						}
					}								
				}
			}
			
			/* Erro permissão */			
			response.sendRedirect("erroPermissao");
			return false;			
		}
		response.sendRedirect("login");
		return false;
	}

}
