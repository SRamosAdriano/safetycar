package br.com.safetycar.modelos;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import sun.misc.BASE64Encoder;

public class Usuarios {

	private long id;
	
	private String nome;
	
	private String login;
	
	private String senha;
	
	boolean primeiroLogin;
	
	@DateTimeFormat(pattern="dd/MM/yyyy")
	private Calendar dataCadastro;
	
	@DateTimeFormat(pattern="dd/MM/yyyy")
	private Calendar dataUltimoLogin;
	
	private int erroLogin;
	
	private List<Grupos> grupos;

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

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getSenha() {
		return senha;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getLogin() {
		return login;
	}
	
	public void setPrimeiroLogin(boolean primeiroLogin) {
		this.primeiroLogin = primeiroLogin;
	}

	public boolean getPrimeiroLogin() {
		return primeiroLogin;
	}

	public void setDataCadastro(Calendar dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Calendar getDataCadastro() {
		return dataCadastro;
	}

	public void setDataUltimoLogin(Calendar dataUltimoLogin) {
		this.dataUltimoLogin = dataUltimoLogin;
	}

	public Calendar getDataUltimoLogin() {
		return dataUltimoLogin;
	}

	public void setErroLogin(int erroLogin) {
		this.erroLogin = erroLogin;
	}

	public int getErroLogin() {
		return erroLogin;
	}

	public void setGrupos(List<Grupos> grupos) {
		this.grupos = grupos;
	}

	public List<Grupos> getGrupos() {
		return grupos;
	}

	
	
	public static String gerarSenha(){	
		String senha = "";
		
		String alfabeto[] = {"A","B","C","D","E","F","G","H","I","J","K","L"
				             ,"M","N","O","P","Q","R","S","T","W","Y","X","Z"
				             ,"a","b","c","d","e","f","g","h","i","j","k","l"
				             ,"m","n","o","p","q","r","s","t","w","y","x","z"};
		
		String esp[] = {"!","@","#","$","%","&","*","+","-","=","?","/","\\","(",")",">","<"};		

		for (int i = 0 ; i <= 2 ; i ++){
			int num = (int) (Math.random()*2);			
			if(num == 0){
				senha = senha + alfabeto[(int) (Math.random()*48)];
				senha = senha + String.valueOf(((int) (Math.random()*9)));
				senha = senha + esp[(int) (Math.random()*17)];
			}else if(num == 1){
				senha = senha + String.valueOf(((int) (Math.random()*9)));
				senha = senha + alfabeto[(int) (Math.random()*48)];				
				senha = senha + esp[(int) (Math.random()*17)];
			}else if(num == 2){				
				senha = senha + String.valueOf(((int) (Math.random()*9)));
				senha = senha + esp[(int) (Math.random()*17)];
				senha = senha + alfabeto[(int) (Math.random()*48)];
			}			
		}
		senha = senha + alfabeto[(int) (Math.random()*48)];
		
		return senha;
	}
	
	public static String criptografarSenha(String senha){		
	    try {
	    	MessageDigest digest = MessageDigest.getInstance("MD5");  
	        digest.update(senha.getBytes());  
	        BASE64Encoder encoder = new BASE64Encoder ();  
	        
	        return encoder.encode (digest.digest ());
	        
	    } catch (NoSuchAlgorithmException ns) {
	    	ns.printStackTrace ();
	    	return senha;
	    }  
	}
}
