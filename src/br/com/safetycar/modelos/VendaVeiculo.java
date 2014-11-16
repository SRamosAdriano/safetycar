package br.com.safetycar.modelos;

import java.util.Calendar;

import org.springframework.format.annotation.DateTimeFormat;

public class VendaVeiculo {
	
	private long id;
	
	@DateTimeFormat(pattern="dd/MM/yyyy")
	private Calendar dataVenda;
	
	private Double valorVenda;
	
	private long veiculo_id;
	
	private long cliente_id;
	
	private long vendedor_id;
	
	

	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public void setDataVenda(Calendar dataVenda) {
		this.dataVenda = dataVenda;
	}

	public Calendar getDataVenda() {
		return dataVenda;
	}

	public void setValorVenda(Double valorVenda) {
		this.valorVenda = valorVenda;
	}

	public Double getValorVenda() {
		return valorVenda;
	}

	public void setVeiculo_id(long veiculo_id) {
		this.veiculo_id = veiculo_id;
	}

	public long getVeiculo_id() {
		return veiculo_id;
	}

	public void setCliente_id(long cliente_id) {
		this.cliente_id = cliente_id;
	}

	public long getCliente_id() {
		return cliente_id;
	}

	public void setVendedor_id(long vendedor_id) {
		this.vendedor_id = vendedor_id;
	}

	public long getVendedor_id() {
		return vendedor_id;
	}

}
