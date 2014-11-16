package br.com.safetycar.paginacao;

import java.io.*;
import java.util.*;

/**
 * Representa um filtro de pesquisa que pode possuir vários parâmetros.
 * Representa o elemento "filter-def" do arquivo de configuração do Hibernate.
 */
public class Filtro implements Serializable, Comparable<Filtro> {
	
	private static final long serialVersionUID = -9103937604939492494L;

	private String nome;

	private List<Parametro> parametros;

	public Filtro() {
		this(null);
	}

	public Filtro(String nome) {
		parametros = new ArrayList<Parametro>();
		setNome(nome);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Parametro> getParametros() {
		return parametros;
	}

	public void setParametros(List<Parametro> parametros) {
		this.parametros = parametros;
	}

	public void addParametro(Parametro parametro) {
		if (parametro == null) {
			throw new IllegalArgumentException("Parametro nulo");
		}

		parametros.add(parametro);
	}

	public void addParametro(String nome, Object valor) {
		parametros.add(new Parametro(nome, valor));
	}

	@Override
	public int hashCode() {
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + ((nome == null) ? 0 : nome.hashCode());
		result = PRIME * result
				+ ((parametros == null) ? 0 : parametros.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final Filtro other = (Filtro) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (parametros == null) {
			if (other.parametros != null)
				return false;
		} else if (!parametros.equals(other.parametros))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return getNome() + " - " + getParametros().toString();
	}

	public int compareTo(Filtro obj) {
		return getNome().compareTo(obj.getNome());
	}
}