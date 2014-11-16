package br.com.safetycar.properties;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

public class PropertiesLoader {
	
	private static Logger LOG =  Logger.getLogger(PropertiesLoader.class.getName());

	private Properties props;	
		
	private String caminhoProperties = "/br/com/safetycar/resources/parametros.properties";

	protected PropertiesLoader() {
		try {
			props = new Properties();			
			InputStream in = this.getClass().getResourceAsStream(caminhoProperties);
			props.load(in);
			in.close();
		} catch (IOException e) {
			LOG.error(e.getMessage(), e.getCause());
		}		
	}

	protected String getValor(String chave) {
		return (String) props.getProperty(chave);
	}

}
