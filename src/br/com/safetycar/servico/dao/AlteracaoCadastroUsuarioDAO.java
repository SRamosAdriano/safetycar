package br.com.safetycar.servico.dao;

import java.util.Calendar;
import java.util.List;

import br.com.safetycar.modelos.Grupos;
import br.com.safetycar.modelos.Usuarios;

public interface AlteracaoCadastroUsuarioDAO {
	
	public Usuarios pesquisaUsuario(long id);
	
	public List<Usuarios> pesquisaUsuarios(String nomeUsuario, String loginUsuario, Calendar dataCadastro);
	
	public List<Usuarios> pesquisaUsuarios(String nomeUsuario, String loginUsuario, Calendar dataCadastro, int numeroPaginaAtual, int qtdePorPagina);
	
	public int countUsuarios(String nomeUsuario, String loginUsuario, Calendar dataCadastro);
	
	public List<Grupos> getGruposDisponiveis();
	
	public void removeUsuario(long idUsuario);
	
	public void atualizaUsuario(Usuarios usuario);
	
	public boolean existeLogin(long idUsuario, String login);

}
