package br.com.safetycar.modelos.relatorios;

import java.util.Calendar;

public class RelatorioVeiculosVendidos {
	
	private long veiculo_id;
	
	private long cliente_id;
	
	private String placa;	
	
	private String marca;
	
	private String modelo;
	
	private String versao;
	
	private String nomeCliente;
	
	private String nomeVendedor;
	
	private String valorVenda;
	
	private Calendar dataVenda;
	
	private Boolean consignado;

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

	public void setVersao(String versao) {
		this.versao = versao;
	}

	public String getVersao() {
		return versao;
	}

	public void setValorVenda(String valorVenda) {
		this.valorVenda = valorVenda;
	}

	public String getValorVenda() {
		return valorVenda;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeVendedor(String nomeVendedor) {
		this.nomeVendedor = nomeVendedor;
	}

	public String getNomeVendedor() {
		return nomeVendedor;
	}

	public void setDataVenda(Calendar dataVenda) {
		this.dataVenda = dataVenda;
	}

	public Calendar getDataVenda() {
		return dataVenda;
	}

	public void setConsignado(Boolean consignado) {
		this.consignado = consignado;
	}

	public Boolean getConsignado() {
		return consignado;
	}

}
