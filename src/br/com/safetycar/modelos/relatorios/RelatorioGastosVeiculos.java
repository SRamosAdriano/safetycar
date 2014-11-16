package br.com.safetycar.modelos.relatorios;

import java.util.Calendar;

import org.springframework.format.annotation.DateTimeFormat;

public class RelatorioGastosVeiculos {
	
	private String placa;	
	
	private String marca;
	
	private String modelo;
	
	private String nDocumento;
	
	@DateTimeFormat(pattern="dd/MM/yyyy")
	private Calendar dtGasto;
	
	private String nomeFornecedor;
	
	private String descricaoGasto;
	
	private String valorGasto;
	
	

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getPlaca() {
		return placa;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getMarca() {
		return marca;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getModelo() {
		return modelo;
	}

	public void setnDocumento(String nDocumento) {
		this.nDocumento = nDocumento;
	}

	public String getnDocumento() {
		return nDocumento;
	}

	public void setDtGasto(Calendar dtGasto) {
		this.dtGasto = dtGasto;
	}

	public Calendar getDtGasto() {
		return dtGasto;
	}

	public void setNomeFornecedor(String nomeFornecedor) {
		this.nomeFornecedor = nomeFornecedor;
	}

	public String getNomeFornecedor() {
		return nomeFornecedor;
	}

	public void setDescricaoGasto(String descricaoGasto) {
		this.descricaoGasto = descricaoGasto;
	}

	public String getDescricaoGasto() {
		return descricaoGasto;
	}

	public void setValorGasto(String valorGasto) {
		this.valorGasto = valorGasto;
	}

	public String getValorGasto() {
		return valorGasto;
	}

}
