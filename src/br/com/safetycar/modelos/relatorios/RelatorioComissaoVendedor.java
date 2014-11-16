package br.com.safetycar.modelos.relatorios;

import java.util.Calendar;

public class RelatorioComissaoVendedor {
	
	private String placa;	
	
	private String marca;
	
	private String modelo;
	
	private String versao;
	
	private Calendar dataVenda;
	
	private String nomeVendedor;
	
	private double vlComissao;
	
	private double vlBonus;
	
	private double total;

	
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

	public void setDataVenda(Calendar dataVenda) {
		this.dataVenda = dataVenda;
	}

	public Calendar getDataVenda() {
		return dataVenda;
	}

	public void setNomeVendedor(String nomeVendedor) {
		this.nomeVendedor = nomeVendedor;
	}

	public String getNomeVendedor() {
		return nomeVendedor;
	}

	public void setVlComissao(double vlComissao) {
		this.vlComissao = vlComissao;
	}

	public double getVlComissao() {
		return vlComissao;
	}

	public void setVlBonus(double vlBonus) {
		this.vlBonus = vlBonus;
	}

	public double getVlBonus() {
		return vlBonus;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public double getTotal() {
		return total;
	}

}
