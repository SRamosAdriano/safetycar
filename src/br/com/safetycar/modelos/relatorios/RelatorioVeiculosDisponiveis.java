package br.com.safetycar.modelos.relatorios;


public class RelatorioVeiculosDisponiveis {
	
	private long veiculo_id;
	
	private String placa;	
	
	private String marca;
	
	private String modelo;
	
	private String versao;	
	
	private int anoFab;
	
	private int anoMod;
	
	private long km;
	
	private String combustivel;
	
	private String cor;
	
	private String opcionais;
	
	private String valorVenda;
	

	public void setVeiculo_id(long veiculo_id) {
		this.veiculo_id = veiculo_id;
	}

	public long getVeiculo_id() {
		return veiculo_id;
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

	public void setAnoFab(int anoFab) {
		this.anoFab = anoFab;
	}

	public int getAnoFab() {
		return anoFab;
	}

	public void setAnoMod(int anoMod) {
		this.anoMod = anoMod;
	}

	public int getAnoMod() {
		return anoMod;
	}

	public void setKm(long km) {
		this.km = km;
	}

	public long getKm() {
		return km;
	}

	public void setCombustivel(String combustivel) {
		this.combustivel = combustivel;
	}

	public String getCombustivel() {
		return combustivel;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public String getCor() {
		return cor;
	}

	public void setOpcionais(String opcionais) {
		this.opcionais = opcionais;
	}

	public String getOpcionais() {
		return opcionais;
	}

	public void setValorVenda(String valorVenda) {
		this.valorVenda = valorVenda;
	}

	public String getValorVenda() {
		return valorVenda;
	}	
}
