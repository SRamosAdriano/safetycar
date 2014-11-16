package br.com.safetycar.modelos.relatorios;

import java.util.Calendar;

import org.springframework.format.annotation.DateTimeFormat;

public class RelatorioLucroVeiculo {
	
	private String placa;	
	
	private String marca;
	
	private String modelo;
	
	private String versao;
	
	@DateTimeFormat(pattern="dd/MM/yyyy")
	private Calendar dataCompra;
	
	@DateTimeFormat(pattern="dd/MM/yyyy")
	private Calendar dataVenda;
	
	private double vlCompra;
	
	private double vlComissao;
	
	private double vlGastos;
	
	private double vlVenda;
	
	private double vlLucro;
	
	

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

	public void setDataCompra(Calendar dataCompra) {
		this.dataCompra = dataCompra;
	}

	public Calendar getDataCompra() {
		return dataCompra;
	}

	public void setDataVenda(Calendar dataVenda) {
		this.dataVenda = dataVenda;
	}

	public Calendar getDataVenda() {
		return dataVenda;
	}

	public void setVlCompra(double vlCompra) {
		this.vlCompra = vlCompra;
	}

	public double getVlCompra() {
		return vlCompra;
	}

	public void setVlComissao(double vlComissao) {
		this.vlComissao = vlComissao;
	}

	public double getVlComissao() {
		return vlComissao;
	}

	public void setVlGastos(double vlGastos) {
		this.vlGastos = vlGastos;
	}

	public double getVlGastos() {
		return vlGastos;
	}

	public void setVlVenda(double vlVenda) {
		this.vlVenda = vlVenda;
	}

	public double getVlVenda() {
		return vlVenda;
	}

	public void setVlLucro(double vlLucro) {
		this.vlLucro = vlLucro;
	}

	public double getVlLucro() {
		return vlLucro;
	}

}
