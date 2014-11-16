package br.com.safetycar.servico.dao;

import java.util.List;

import br.com.safetycar.modelos.Grupos;
import br.com.safetycar.modelos.Usuarios;

public interface CadastroUsuariosDAO {

	public boolean existeLogin(String login);	
	
	public List<Grupos> getGrupos();
	
	public void adicionaUsuario(Usuarios usuario);
}
