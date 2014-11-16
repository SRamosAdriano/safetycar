package br.com.safetycar.modelos;

import java.util.Calendar;

import org.springframework.format.annotation.DateTimeFormat;


public class Veiculos {
	
	private long id;
	
	private String placa;
	
	private String cidade;
	
	private String estado;
	
	private String marca;
	
	private String modelo;
	
	private String versao;	
	
	private int anoFab;
	
	private int anoMod;
	
	private long renavan;
	
	private String chassi;
	
	private long km;
	
	private String combustivel;
	
	private String cor;
	
	@DateTimeFormat(pattern="dd/MM/yyyy")
	private Calendar dataCadastro;
	
	private String status;
			
	private String infDocNome;
	
	private String infDocEndereco;
	
	
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getVersao() {
		return versao;
	}

	public void setVersao(String versao) {
		this.versao = versao;
	}

	public int getAnoFab() {
		return anoFab;
	}

	public void setAnoFab(int anoFab) {
		this.anoFab = anoFab;
	}

	public int getAnoMod() {
		return anoMod;
	}

	public void setAnoMod(int anoMod) {
		this.anoMod = anoMod;
	}

	public long getRenavan() {
		return renavan;
	}

	public void setRenavan(long renavan) {
		this.renavan = renavan;
	}

	public String getChassi() {
		return chassi;
	}

	public void setChassi(String chassi) {
		this.chassi = chassi;
	}

	public long getKm() {
		return km;
	}

	public void setKm(long km) {
		this.km = km;
	}

	public String getCombustivel() {
		return combustivel;
	}

	public void setCombustivel(String combustivel) {
		this.combustivel = combustivel;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public Calendar getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Calendar dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public String getInfDocNome() {
		return infDocNome;
	}

	public void setInfDocNome(String infDocNome) {
		this.infDocNome = infDocNome;
	}

	public String getInfDocEndereco() {
		return infDocEndereco;
	}

	public void setInfDocEndereco(String infDocEndereco) {
		this.infDocEndereco = infDocEndereco;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getModelo() {
		return modelo;
	}	

}
