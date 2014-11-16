package br.com.safetycar.dao;

import java.util.Calendar;
import java.util.List;

import br.com.safetycar.modelos.Usuarios;

public interface UsuariosDAO {
	
	public Long getErroLoginUsuario(String loginUsuario);	
	
	public boolean verificaSenhaUsuario(String loginUsuario, String senhaCripUsuario);
		
	public boolean getPrimeiroLoginUsuario(String loginUsuario);
		
	public boolean existeUsuario(long id, String nome, String login);
	
	public int countUsuarios(long id, String nomeUsuario, String loginUsuario, Calendar dataCadastro);
	
	public List<Usuarios> pesquisaUsuarios(long id, String nomeUsuario, String loginUsuario, Calendar dataCadastro, int paginaAtual,int qtdePorPagina);
	
	public List<Usuarios> pesquisaUsuarios(long id, String nomeUsuario, String loginUsuario, Calendar dataCadastro);
	
	public void adicionaUsuario(Usuarios usuario);
	
	public void atualizaUsuario(Usuarios usuario);
	
	public void atualizaDtUltimoLoginEErroLoginUsuario(String loginUsuario, Calendar dataUltimoLogin, int erroLogin);
	
	public void atualizaErroLoginUsuario(String loginUsuario);
	
	public void atualizaSenhaUsuario(Usuarios usuario);
	
	public void removeUsuario(long idUsuario);

}
