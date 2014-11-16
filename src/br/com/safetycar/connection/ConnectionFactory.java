package br.com.safetycar.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import br.com.safetycar.properties.PropertiesLoaderImpl;

public class ConnectionFactory {
	
	private static Logger LOG = Logger.getLogger(ConnectionFactory.class.getName());

	public Connection getConnection() {
		Connection conexao = null;
		try 
		{
			LOG.info("Conectando ao BD ...");
			
			String user         = PropertiesLoaderImpl.getValor("prop.connecrionBDUser");
		   	String senha        = PropertiesLoaderImpl.getValor("prop.connecrionBDSenha");
		   	String bd           = PropertiesLoaderImpl.getValor("prop.connecrionBDSchema");
		   	String server     	= PropertiesLoaderImpl.getValor("prop.connecrionBDServer");  
		   	String driver 		= PropertiesLoaderImpl.getValor("prop.connecrionBDDriver"); 
		   	String porta     	= PropertiesLoaderImpl.getValor("prop.connecrionBDPorta");  
		   	String iniURL 		= PropertiesLoaderImpl.getValor("prop.connecrionBDIniURL");	   
		   	
		   	String url			= iniURL + "://" + server + ":" + porta + "/" + bd;		   	
		   	
		   	Class.forName(driver).newInstance();		   	
						
			conexao = DriverManager.getConnection(url, user, senha);				
			
			LOG.info("Conexao BD OK");
			
			return conexao;		
		}
		catch (ClassNotFoundException e){
			LOG.error(e.getMessage(), e.getCause());
			return null;
		}
		catch (SQLException e) {
			LOG.error(e.getMessage(), e.getCause());
			return null;
		}
		catch (Exception e) {	
			LOG.error(e.getMessage(), e.getCause());			
			return null;
		}	
	}
}

