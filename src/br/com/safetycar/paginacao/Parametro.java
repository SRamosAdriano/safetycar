package br.com.safetycar.paginacao;

import java.io.*;
import java.util.*;

/**
 * Representa um parâmetro gerérico.
 */
public class Parametro implements Serializable, Comparable<Parametro> {
	
	private static final long serialVersionUID = -78777785727687863L;
	
	private String nome;
	
	private Object valor;

	public Parametro() {
		this(null, null);
	}
	
	public Parametro(String nome, Object valor) {
		setNome(nome);
		setValor(valor);
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public Object getValor() {
		return valor;
	}
	
	public void setValor(Object valor) {
		this.valor = valor;
	}
	
	public static Parametro getParametroRequerido(String nome, List<Parametro> parametros) {
		return getParametroRequerido(nome, parametros.toArray(new Parametro[parametros.size()]));
	}

	/**
	 * Busca um parâmetro com o nome passado e levanta uma exception se não encontrar.
	 * 
	 * @param nome
	 * @param parametros
	 * @return
	 */
	public static Parametro getParametroRequerido(String nome, Parametro... parametros) {
		Parametro parametro = null;
		for (Parametro p : parametros) {
			if (p.getNome().equals(nome)) {
				parametro = p;
				break;
			}
		}
		if (parametro == null) {
			throw new IllegalArgumentException("Parametro '" + nome + "' nao encontrado");
		}
		return parametro;
	}

	@Override
	public int hashCode() {
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + ((nome == null) ? 0 : nome.hashCode());
		result = PRIME * result + ((valor == null) ? 0 : valor.hashCode());
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
		final Parametro other = (Parametro) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (valor == null) {
			if (other.valor != null)
				return false;
		} else if (!valor.equals(other.valor))
			return false;
		return true;
	}

	public int compareTo(Parametro obj) {
		return getNome().compareTo(obj.getNome());
	}

	@Override
	public String toString() {
		return getNome() + ":" + getValor();
	}

}