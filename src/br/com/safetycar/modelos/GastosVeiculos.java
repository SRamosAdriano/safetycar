package br.com.safetycar.modelos;

import java.util.Calendar;

import org.springframework.format.annotation.DateTimeFormat;

public class GastosVeiculos {
	
	private long id;
	
	private String descricao;
	
	private Double valor;
	
	@DateTimeFormat(pattern="dd/MM/yyyy")
	private Calendar dtLancamento;
		
	private long veiculo_id;
	
	private long fornecedordespesas_id;
	
	private String nDocumento;

	
		
	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Double getValor() {
		return valor;
	}

	public void setDtLancamento(Calendar dtLancamento) {
		this.dtLancamento = dtLancamento;
	}

	public Calendar getDtLancamento() {
		return dtLancamento;
	}	

	public void setVeiculo_id(long veiculo_id) {
		this.veiculo_id = veiculo_id;
	}

	public long getVeiculo_id() {
		return veiculo_id;
	}

	public void setFornecedordespesas_id(long fornecedordespesas_id) {
		this.fornecedordespesas_id = fornecedordespesas_id;
	}

	public long getFornecedordespesas_id() {
		return fornecedordespesas_id;
	}

	public void setNDocumento(String nDocumento) {
		this.nDocumento = nDocumento;
	}

	public String getNDocumento() {
		return nDocumento;
	}

	

}
