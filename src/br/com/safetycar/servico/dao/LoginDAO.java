package br.com.safetycar.servico.dao;

import br.com.safetycar.modelos.Usuarios;

public interface LoginDAO {
	
	public boolean existeUsuario(String loginUsuario);
	
	public boolean verificaSenhaLogin(String loginUsuario, String senhaUsuario);
	
	public Long getErroLogin(String loginUsuario);
	
	public void somaErroLogin(String loginUsuario);
	
	public boolean getPrimeiroLogin(String loginUsuario);
	
	public Usuarios pesquisaUsuario(String login);
	
	public void usuarioLogado(String loginUsuario);
	
	public void atualizaSenha(Usuarios usuario);

}
