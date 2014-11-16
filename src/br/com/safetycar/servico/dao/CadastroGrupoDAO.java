package br.com.safetycar.servico.dao;

import java.util.List;

import br.com.safetycar.modelos.Grupos;
import br.com.safetycar.modelos.Paginas;

public interface CadastroGrupoDAO {
	
	public List<Paginas> getPaginasDisponiveis();
	
	public boolean existeGrupo(String nomeGrupo);
	
	public void adicionaGrupo(Grupos grupo);

}
