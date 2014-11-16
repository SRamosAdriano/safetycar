package br.com.safetycar.modelos;

public class VlComissaoEVendaVeiculo {
	
	private long id;
	
	private Double valorComissao;
	
	private Double valorBonus;
	
	private Double valorVenda;
	
	private long veiculo_id;
	
	
	/*
	 * Gets and Sets
	 */

	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public void setValorComissao(Double valorComissao) {
		this.valorComissao = valorComissao;
	}

	public Double getValorComissao() {
		return valorComissao;
	}

	public void setValorBonus(Double valorBonus) {
		this.valorBonus = valorBonus;
	}

	public Double getValorBonus() {
		return valorBonus;
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

}
