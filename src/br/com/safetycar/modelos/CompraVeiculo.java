package br.com.safetycar.modelos;

import java.util.Calendar;

import org.springframework.format.annotation.DateTimeFormat;

public class CompraVeiculo {
	
	private long id;
	
	@DateTimeFormat(pattern="dd/MM/yyyy")
	private Calendar dataCompra;
	
	private Double valorCompra;
	
	private boolean consignado;
	
	private long veiculo_id;
	
	private long fornecedor_id;

	
	/*
	 * Gets and Sets
	 */
	
	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public void setDataCompra(Calendar dataCompra) {
		this.dataCompra = dataCompra;
	}

	public Calendar getDataCompra() {
		return dataCompra;
	}

	public void setValorCompra(Double valorCompra) {
		this.valorCompra = valorCompra;
	}

	public Double getValorCompra() {
		return valorCompra;
	}

	public void setConsignado(boolean consignado) {
		this.consignado = consignado;
	}

	public boolean isConsignado() {
		return consignado;
	}

	public void setVeiculo_id(long veiculo_id) {
		this.veiculo_id = veiculo_id;
	}

	public long getVeiculo_id() {
		return veiculo_id;
	}

	public void setFornecedor_id(long fornecedor_id) {
		this.fornecedor_id = fornecedor_id;
	}

	public long getFornecedor_id() {
		return fornecedor_id;
	}
	

}
