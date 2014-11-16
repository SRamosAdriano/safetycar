package br.com.safetycar.modelos;

import java.util.Calendar;
import java.util.Comparator;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

public class Grupos implements Comparable<Grupos>{
	
	private long id;
	
	private String nome;
	
	@DateTimeFormat(pattern="dd/MM/yyyy")
	private Calendar dataCadastro;
	
	private List<Paginas> paginas;

	
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

	public void setDataCadastro(Calendar dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Calendar getDataCadastro() {
		return dataCadastro;
	}

	public void setPaginas(List<Paginas> paginas) {
		this.paginas = paginas;
	}

	public List<Paginas> getPaginas() {
		return paginas;
	}
	
	
	public static final Comparator<Grupos> POR_NOME = new Comparator<Grupos>() { 
		public int compare(Grupos g1, Grupos g2) { 
			return g1.getNome().compareTo(g2.getNome());				
			} 
		};
		
	public static final Comparator<Grupos> POR_ID = new Comparator<Grupos>() { 
		public int compare(Grupos g1, Grupos g2) { 
			return (int) (g1.getId() -  g2.getId());				
			} 
		};
		
	@Override
	public int compareTo(Grupos g) {
		// TODO Auto-generated method stub
		return 0;
	}

}
