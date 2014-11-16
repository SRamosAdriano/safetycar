package br.com.safetycar.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.log4j.Logger;

import br.com.safetycar.dao.ClienteFornecedorDAO;
import br.com.safetycar.modelos.ClienteFornecedor;
import br.com.safetycar.utils.PaginacaoUtils;

public class ClienteFornecedorDAOImpl extends PaginacaoUtils implements ClienteFornecedorDAO{
	
	private static Logger LOG = Logger.getLogger(ClienteFornecedorDAOImpl.class.getName());
	
	private Connection cnn;
	
	public ClienteFornecedorDAOImpl(Connection connection) {
		this.cnn = connection;
	}
		
	
	public void adicionaClienteFornecedor(ClienteFornecedor cliFor) {

		try {
			String strSql = "insert into clifor (tipoCliFor, nomeCompleto, rg, cpf, "
					+ "                    email, dataCadastro, status) "
					+ "     values( ?, ?, ?, ?, ?, ?, ?) ";

			PreparedStatement stmt = (PreparedStatement) cnn.prepareStatement(strSql);
			stmt.setString(1, cliFor.getTipoCliFor());
			stmt.setString(2, cliFor.getNomeCompleto());
			stmt.setString(3, cliFor.getRg());
			stmt.setString(4, cliFor.getCpf());
			stmt.setString(5, cliFor.getEmail());
			stmt.setDate(6, new java.sql.Date(cliFor.getDataCadastro()
					.getTimeInMillis()));
			stmt.setString(7, cliFor.getStatus());

			stmt.execute();

			strSql = "select id from clifor " 
				    + "  where nomeCompleto = ? "
					+ "    and rg = ? " + "    and cpf = ? "
					+ "    and status = ? ";

			stmt = (PreparedStatement) cnn.prepareStatement(strSql);
			stmt.setString(1, cliFor.getNomeCompleto());
			stmt.setString(2, cliFor.getRg());
			stmt.setString(3, cliFor.getCpf());
			stmt.setString(4, cliFor.getStatus());

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				cliFor.setId(rs.getLong("id"));
			}

			strSql = "insert into telefoneclifor (telefone, tipo, clifor_id) "
					+ "      values(?, ?, ?) ";

			if (cliFor.getTelefone() != null
					|| cliFor.getTelefone().replaceAll(" ", "").length() > 7) {
				stmt = (PreparedStatement) cnn.prepareStatement(strSql);
				stmt.setString(1, cliFor.getTelefone());
				stmt.setString(2, "telefone");
				stmt.setLong(3, cliFor.getId());

				stmt.execute();
			}

			if (cliFor.getCelular() != null
					|| cliFor.getCelular().replaceAll(" ", "").length() > 7) {
				stmt = (PreparedStatement) cnn.prepareStatement(strSql);
				stmt.setString(1, cliFor.getCelular());
				stmt.setString(2, "celular");
				stmt.setLong(3, cliFor.getId());

				stmt.execute();
			}

			if (cliFor.getRadio() != null) {
				if (!cliFor.getRadio().equals("")) {
					stmt = (PreparedStatement) cnn.prepareStatement(strSql);
					stmt.setString(1, cliFor.getRadio());
					stmt.setString(2, "radio");
					stmt.setLong(3, cliFor.getId());

					stmt.execute();
				}
			}

			strSql = " insert into enderecoclifor (endereco, bairro, cep, cidade, estado, clifor_id) "
					+ "values(?, ?, ?, ?, ?, ?) ";

			stmt = (PreparedStatement) cnn.prepareStatement(strSql);
			stmt.setString(1, cliFor.getEndereco());
			stmt.setString(2, cliFor.getBairro());
			stmt.setString(3, cliFor.getCep());
			stmt.setString(4, cliFor.getCidade());
			stmt.setString(5, cliFor.getEstado());
			stmt.setLong(6, cliFor.getId());

			stmt.execute();

			stmt.close();
		} catch (SQLException e) {
			LOG.error(e.getMessage(), e.getCause());
			throw new RuntimeException(e);
		}
	}
	
	
	public void atualizaClienteFornecedor(ClienteFornecedor cliFor) {		

		try {			
			String strSql ="update clifor set tipoCliFor = ?, nomeCompleto = ? " +
					"                   , rg = ?, cpf = ?, email = ?, status = ? " +
					"       where id = ?";
				
			PreparedStatement stmt = (PreparedStatement) cnn.prepareStatement(strSql);
			stmt.setString(1, cliFor.getTipoCliFor());
			stmt.setString(2, cliFor.getNomeCompleto());
			stmt.setString(3, cliFor.getRg());
			stmt.setString(4, cliFor.getCpf());
			stmt.setString(5, cliFor.getEmail());
			stmt.setString(6, cliFor.getStatus());
			stmt.setLong(7, cliFor.getId());

			stmt.executeUpdate();
			
			strSql ="update enderecoclifor set endereco = ?, bairro = ? " +
					"                          , cep = ?, cidade = ?, estado = ? " +
					" where clifor_id = ? ";
			
			stmt = (PreparedStatement) cnn.prepareStatement(strSql);
			stmt.setString(1, cliFor.getEndereco());
			stmt.setString(2, cliFor.getBairro());
			stmt.setString(3, cliFor.getCep());
			stmt.setString(4, cliFor.getCidade());
			stmt.setString(5, cliFor.getEstado());
			stmt.setLong(6, cliFor.getId());
			
			stmt.executeUpdate();
		
			
			strSql ="select id, telefone, tipo from telefoneclifor " +
					" where clifor_id = ? ";
			
			stmt = (PreparedStatement) cnn.prepareStatement(strSql);
			stmt.setLong(1, cliFor.getId());
			
			ResultSet rs = stmt.executeQuery();

			String telefone = null;
			String celular = null;
			String radio = null;
			
			while (rs.next()) {
				String tipo = rs.getString("tipo");				
				
				if(tipo != null){
					if(!tipo.equals("")){
						if(tipo.equals("telefone")){
							telefone = rs.getString("telefone");
						}else if(tipo.equals("celular")){
							celular = rs.getString("telefone");
						}else if(tipo.equals("radio")){
							radio = rs.getString("telefone");
						}						
					}
				}				
			}
			

			if(telefone != null){				
				strSql = "update telefoneclifor set telefone = ? " +
						" where clifor_id = ? " +
						"	and tipo = ? ";
				
				stmt = (PreparedStatement) cnn.prepareStatement(strSql);
				stmt.setString(1, cliFor.getTelefone());
				stmt.setLong(2, cliFor.getId());
				stmt.setString(3, "telefone");
				stmt.execute();				
			}
			
			if(celular != null){
				strSql = "update telefoneclifor set telefone = ? " +
				         " where clifor_id = ? " +
				         "	 and tipo = ? ";
				
				stmt = (PreparedStatement) cnn.prepareStatement(strSql);
				stmt.setString(1, cliFor.getCelular());
				stmt.setLong(2, cliFor.getId());
				stmt.setString(3, "celular");
				stmt.execute();				
			}
			
			if(radio != null){
				strSql = "update telefoneclifor set telefone = ? " +
						 " where clifor_id = ? " +
						 "   and tipo = ? ";
				
				stmt = (PreparedStatement) cnn.prepareStatement(strSql);
				stmt.setString(1, cliFor.getRadio());
				stmt.setLong(2, cliFor.getId());
				stmt.setString(3, "radio");				
				stmt.execute();
				
			}else{
				strSql = "insert into telefoneclifor (telefone, tipo, clifor_id) " +
					     "      values(?, ?, ?) ";
				stmt = (PreparedStatement) cnn.prepareStatement(strSql);
				stmt.setString(1, cliFor.getRadio());
				stmt.setString(2, "radio");
				stmt.setLong(3, cliFor.getId());
				stmt.execute();
			}
						
			stmt.close();
			
		} catch (SQLException e) {
			LOG.error(e.getMessage(), e.getCause());
			throw new RuntimeException(e);
		}
	}
	
	public boolean existeClienteFornecedor(String cpf, long idClienteFornecedor) {
		
		if(cpf != null){
			if(cpf.equals("")){
				cpf = null;
			}
		}	
		
		String strSql = "select cpf " + "  from clifor " + " where 1 = 1 ";
		
		if(cpf != null){
			strSql = strSql + " and cpf = \"" + cpf.trim() + "\"";
		}
		
		if(idClienteFornecedor > 0){
			strSql = strSql + " and id <>" + idClienteFornecedor;
		}
		
		try {
			PreparedStatement stmt = (PreparedStatement) cnn.prepareStatement(strSql);
			
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				stmt.close();
				return true;
			}

			stmt.close();
			return false;

		} catch (SQLException e) {
			LOG.error(e.getMessage(), e.getCause());
			throw new RuntimeException(e);
		}
	}
	
	public int countClienteFornecedor(long id, String nome, String cpf, String rg, String status, List<String> tipoCliFor) {
		if (nome != null && nome.equals("")) {
			nome = null;			
		}
		if (cpf != null && cpf.equals("")) {
			cpf = null;			
		}
		if (rg != null && rg.equals("")) {
			rg = null;			
		}
		if (status != null && status.equals("")) {
			status = null;			
		}		
		
		String strSql = "select count(1) as totalPaginas from clifor where 1 = 1 ";
		
		if(id > 0){
			strSql = strSql +  " and id = " + id;
		}		
		if(nome != null){
			strSql = strSql +  " and nomeCompleto like ('%" + nome.trim() + "%') ";
		}
		if(cpf != null){
			strSql = strSql +  " and cpf = '" + cpf.trim() + "' ";
		}			
		if(rg != null){
			strSql = strSql +  " and rg = '" + rg.trim() + "' ";
		}
		if(status != null){
			strSql = strSql + "   and status = '" + status.trim() + "' ";
		}		 
		if(tipoCliFor != null){
			String inTiposCliFor = "";
			for (String tipo : tipoCliFor) {
				inTiposCliFor += "'" + tipo + "', ";
			}
			if(inTiposCliFor.length() > 0){
				inTiposCliFor = inTiposCliFor.substring(0, inTiposCliFor.length()-2);
				strSql = strSql + "   and tipoCliFor in (" + inTiposCliFor + ") ";			
			}
		}		
		
		try {
		PreparedStatement stmt = (PreparedStatement) cnn.prepareStatement(strSql);
		ResultSet rs = stmt.executeQuery();
		
		int totalPaginas = 0;
		while (rs.next()) {
			totalPaginas = rs.getInt("totalPaginas");
		}		
		
		stmt.close();

		return totalPaginas;
		
		} catch (SQLException e) {
			LOG.error(e.getMessage(), e.getCause());
			throw new RuntimeException(e);
		}		
	}
	
	public List<ClienteFornecedor> pesquisaClienteFornecedor(long id, String nome, String cpf, String rg, String status, List<String> tipoCliFor) {
		return pesqClienteFornecedor(id, nome, cpf, rg, status, tipoCliFor, 0, 0);
	}
	
	public List<ClienteFornecedor> pesquisaClienteFornecedor(long id, String nome, String cpf, String rg, String status, List<String> tipoCliFor, int paginaAtual,int qtdePorPagina) {
		return pesqClienteFornecedor(id, nome, cpf, rg, status, tipoCliFor, paginaAtual, qtdePorPagina);
	}
	
	private List<ClienteFornecedor> pesqClienteFornecedor(long id, String nome, String cpf, String rg, String status, List<String> tipoCliFor, int paginaAtual,int qtdePorPagina) {
		if (nome != null && nome.equals("")) {
			nome = null;			
		}
		if (cpf != null && cpf.equals("")) {
			cpf = null;			
		}
		if (rg != null && rg.equals("")) {
			rg = null;			
		}
		if (status != null && status.equals("")) {
			status = null;			
		}		
		
		List<ClienteFornecedor> clisFors = new ArrayList<ClienteFornecedor>();
		
		String strSql = "select c.id, c.tipoCliFor, c.nomeCompleto, c.rg, c.cpf, c.email, c.dataCadastro, c.status "
					  + "        , e.endereco, e.bairro, e.cep, e.cidade, e.estado "
					  + "  from clifor c "
					  + "        , enderecoclifor e "
					  + " where c.id = e.clifor_id ";
		
		if(id > 0){
			strSql = strSql +  " and c.id = " + id;
		}		
		if(nome != null){
			strSql = strSql +  " and nomeCompleto like ('%" + nome.trim() + "%') ";
		}
		if(cpf != null){
			strSql = strSql +  " and cpf = '" + cpf.trim() + "' ";
		}			
		if(rg != null){
			strSql = strSql +  " and rg = '" + rg.trim() + "' ";
		}
		if(status != null){
			strSql = strSql + "   and c.status = '" + status.trim() + "' ";
		}		 
		if(tipoCliFor != null){
			String inTiposCliFor = "";
			for (String tipo : tipoCliFor) {
				inTiposCliFor += "'" + tipo + "', ";
			}
			if(inTiposCliFor.length() > 0){
				inTiposCliFor = inTiposCliFor.substring(0, inTiposCliFor.length()-2);
				strSql = strSql + "   and c.tipoCliFor in (" + inTiposCliFor + ") ";			
			}
		}				
		
		strSql = strSql + " order by c.nomeCompleto";
		
		if(paginaAtual > 0 && qtdePorPagina > 0){
			strSql = strSql + " LIMIT "+ parametroLimit(paginaAtual, qtdePorPagina) +", "+ qtdePorPagina;
		}
		
		
		try {
		PreparedStatement stmt = (PreparedStatement) cnn.prepareStatement(strSql);
		ResultSet rs = stmt.executeQuery();
		
		while (rs.next()) {
			ClienteFornecedor cliFor = new ClienteFornecedor();
			cliFor.setId(rs.getLong("id"));
			cliFor.setTipoCliFor(rs.getString("tipoCliFor"));
			cliFor.setNomeCompleto(rs.getString("nomeCompleto"));
			cliFor.setRg(rs.getString("rg"));
			cliFor.setCpf(rs.getString("cpf"));
			cliFor.setEmail(rs.getString("email"));
			cliFor.setStatus(rs.getString("status"));
			cliFor.setEndereco(rs.getString("endereco"));
			cliFor.setBairro(rs.getString("bairro"));
			cliFor.setCep(rs.getString("cep"));
			cliFor.setCidade(rs.getString("cidade"));
			cliFor.setEstado(rs.getString("estado"));

			Date data = rs.getDate("dataCadastro");
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(data);
			cliFor.setDataCadastro(calendar);

			strSql = "select telefone, tipo " + "   from telefoneclifor "
					+ "  where clifor_id = ?";
			stmt = (PreparedStatement) cnn.prepareStatement(strSql);
			stmt.setLong(1, cliFor.getId());
			ResultSet rs2 = stmt.executeQuery();

			while (rs2.next()) {
				String tipo = rs2.getString("tipo");

				if (tipo.equals("telefone")) {
					cliFor.setTelefone(rs2.getString("telefone"));
				} else if (tipo.equals("celular")) {
					cliFor.setCelular(rs2.getString("telefone"));
				} else if (tipo.equals("radio")) {
					cliFor.setRadio(rs2.getString("telefone"));
				}
				
			}
			clisFors.add(cliFor);
		}		
		
		stmt.close();

		return clisFors;
		
		} catch (SQLException e) {
			LOG.error(e.getMessage(), e.getCause());
			throw new RuntimeException(e);
		}		
	}

}
