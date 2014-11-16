package br.com.safetycar.modelos;

import java.util.Calendar;
import java.util.Comparator;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

public class Paginas implements Comparable<Paginas>{
	
	private long id;
	
	private String nome;
	
	private List<String> url;
	
	@DateTimeFormat(pattern="dd/MM/yyyy")
	private Calendar dataCadastro;

	
	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setUrl(List<String> url) {
		this.url = url;
	}

	public List<String> getUrl() {
		return url;
	}

	public void setDataCadastro(Calendar dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Calendar getDataCadastro() {
		return dataCadastro;
	}
	
	public static final Comparator<Paginas> POR_NOME = new Comparator<Paginas>() { 
		public int compare(Paginas p1, Paginas p2) { 
			return p1.getNome().compareTo(p2.getNome());				
			} 
		};
		
	public static final Comparator<Paginas> POR_ID = new Comparator<Paginas>() { 
		public int compare(Paginas p1, Paginas p2) { 
			return (int) (p1.getId() -  p2.getId());				
			} 
		};
		
	@Override
	public int compareTo(Paginas p) {
		// TODO Auto-generated method stub
		return 0;
	}

}
