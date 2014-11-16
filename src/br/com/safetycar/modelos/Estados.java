package br.com.safetycar.modelos;

public class Estados {
	
	private long id;
	
	private String sigla;
	
	private String descricao;
	
	
	/*
	 * Gets and Sets
	 */
	

	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public String getSigla() {
		return sigla;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

}
