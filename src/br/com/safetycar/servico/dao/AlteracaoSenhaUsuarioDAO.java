package br.com.safetycar.servico.dao;

import br.com.safetycar.modelos.Usuarios;

public interface AlteracaoSenhaUsuarioDAO {
	
	public boolean verificaSenhaLogin(String loginUsuario, String senhaUsuario);
	
	public void atualizaSenha(Usuarios usuario);
	
	public void somaErroLogin(String loginUsuario);
	
	public Long getErroLogin(String loginUsuario);
	
	public Usuarios pesquisaUsuario(String login);

}
