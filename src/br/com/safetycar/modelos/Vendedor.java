package br.com.safetycar.modelos;

import java.util.Calendar;

import org.springframework.format.annotation.DateTimeFormat;

public class Vendedor {
	
	private long id;
	
	private String nome;
	
	private String sobreNome;
	
	@DateTimeFormat(pattern="dd/MM/yyyy")
	private Calendar dataCadastro;

	
	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setSobreNome(String sobreNome) {
		this.sobreNome = sobreNome;
	}

	public String getSobreNome() {
		return sobreNome;
	}

	public void setDataCadastro(Calendar dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Calendar getDataCadastro() {
		return dataCadastro;
	}

}
