package br.com.safetycar.modelos;

import java.util.Calendar;

import org.springframework.format.annotation.DateTimeFormat;

public class ClienteFornecedor {
	
	private long id;
	
	private String tipoCliFor;
	
	private String nomeCompleto;
	
	private String rg;
	
	private String cpf;
	
	private String endereco;
	
	private String bairro;
	
	private String cep;
	
	private String cidade;
	
	private String estado;
	
	private String telefone;
	
	private String celular;
	
	private String radio;
	
	private String email;
	
	@DateTimeFormat(pattern="dd/MM/yyyy")
	private Calendar dataCadastro;
	
	private String status;
	
		
	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public void setTipoCliFor(String tipoCliFor) {
		this.tipoCliFor = tipoCliFor;
	}

	public String getTipoCliFor() {
		return tipoCliFor;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getRg() {
		return rg;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getCpf() {
		return cpf;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getBairro() {
		return bairro;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getCep() {
		return cep;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getCidade() {
		return cidade;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getEstado() {
		return estado;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getCelular() {
		return celular;
	}

	public void setRadio(String radio) {
		this.radio = radio;
	}

	public String getRadio() {
		return radio;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public void setDataCadastro(Calendar dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Calendar getDataCadastro() {
		return dataCadastro;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

}
