package br.com.adriano.dwr;

import br.com.adriano.bean.UsuarioBean;

public class UsuarioAjax {
	
	private UsuarioBean usuario;

	public UsuarioBean getUsuario() {
		return usuario;
	}

	public void setUsuario(final UsuarioBean usuario) {
		this.usuario = usuario;
	}
	
	//Método que será invocado pela nossa chamada DWR
	public UsuarioBean find(){	
		
		getUsuario().setId(1);
		//getUsuario().setNome(nome);
		getUsuario().setEndereco("Rua santo DWR número 555");
		getUsuario().setTelefone("9999-9999");
		
		return getUsuario();
	}
}
