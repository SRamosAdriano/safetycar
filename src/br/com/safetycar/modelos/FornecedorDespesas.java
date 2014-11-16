package br.com.safetycar.modelos;

import java.util.Calendar;

import org.springframework.format.annotation.DateTimeFormat;

public class FornecedorDespesas {
	
	private long id;

	private String nome;
	
	private String contato;
	
	private String telefone;
	
	@DateTimeFormat(pattern="dd/MM/yyyy")
	private Calendar dtCadastro;

	
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getContato() {
		return contato;
	}

	public void setContato(String contato) {
		this.contato = contato;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Calendar getDtCadastro() {
		return dtCadastro;
	}

	public void setDtCadastro(Calendar dtCadastro) {
		this.dtCadastro = dtCadastro;
	}
}
