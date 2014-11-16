package br.com.safetycar.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import br.com.safetycar.dao.VeiculosOpcionaisDAO;
import br.com.safetycar.modelos.VeiculosOpcionais;

public class VeiculosOpcionaisDAOImpl implements VeiculosOpcionaisDAO{
	
	private static Logger LOG = Logger.getLogger(VeiculosOpcionaisDAOImpl.class.getName());
	
	private Connection cnn;
	
	
	public VeiculosOpcionaisDAOImpl(Connection connection) {
		this.cnn = connection;
	}
	
	
	public void adicionaOpcionaisVeiculos(VeiculosOpcionais veiculosOpcionais){
		try {
			String strSql = "insert into opcionaisVeiculo (veiculo_id, opCompleto, opArQuente, opFarolXenon " +
					"                              , opBancoRegAltura, opGps, opLimpTraseiro, opTravaEletrica " +
					"                              , opRadio, opCapotaMaritima, opRadioTocaFita, opCdMp3 " +
					"                              , opCompBordo, opRetroFotocromico, opRetrovEletrico, opRodLigaLeve " +
					"                              , opSensorEstacionamento, opDVD, opTetoSolar, opDesembTraseito " +
					"                              , opTracao4x4, opAirBag, opVolRegAltura, opABS, opAlarme " +
					"                              , opDirecaoHidraulica, opBancoCouro, opArCondicionado, opVidroEletrico " +
					"                              , opChaveReserva, opManual)" +
					"                      values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " +
					"                              ?, ?, ?, ?, ?,?,?) ";
			
			PreparedStatement stmt = (PreparedStatement) cnn.prepareStatement(strSql);
			stmt.setLong(1, veiculosOpcionais.getVeiculo_id());
			stmt.setBoolean(2, veiculosOpcionais.isOpCompleto());
			stmt.setBoolean(3, veiculosOpcionais.isOpArQuente());
			stmt.setBoolean(4, veiculosOpcionais.isOpFarolXenon());
			stmt.setBoolean(5, veiculosOpcionais.isOpBancoRegAltura());
			stmt.setBoolean(6, veiculosOpcionais.isOpGps());
			stmt.setBoolean(7, veiculosOpcionais.isOpLimpTraseiro());
			stmt.setBoolean(8, veiculosOpcionais.isOpTravaEletrica());
			stmt.setBoolean(9, veiculosOpcionais.isOpRadio());
			stmt.setBoolean(10, veiculosOpcionais.isOpCapotaMaritima());
			stmt.setBoolean(11, veiculosOpcionais.isOpRadioTocaFita());
			stmt.setBoolean(12, veiculosOpcionais.isOpCdMp3());
			stmt.setBoolean(13, veiculosOpcionais.isOpCompBordo());
			stmt.setBoolean(14, veiculosOpcionais.isOpRetroFotocromico());
			stmt.setBoolean(15, veiculosOpcionais.isOpRetrovEletrico());
			stmt.setBoolean(16, veiculosOpcionais.isOpRodLigaLeve());
			stmt.setBoolean(17, veiculosOpcionais.isOpSensorEstacionamento());
			stmt.setBoolean(18, veiculosOpcionais.isOpDVD());
			stmt.setBoolean(19, veiculosOpcionais.isOpTetoSolar());
			stmt.setBoolean(20, veiculosOpcionais.isOpDesembTraseito());
			stmt.setBoolean(21, veiculosOpcionais.isOpTracao4x4());
			stmt.setBoolean(22, veiculosOpcionais.isOpAirBag());
			stmt.setBoolean(23, veiculosOpcionais.isOpVolRegAltura());
			stmt.setBoolean(24, veiculosOpcionais.isOpABS());
			stmt.setBoolean(25, veiculosOpcionais.isOpAlarme());
			stmt.setBoolean(26, veiculosOpcionais.isOpDirecaoHidraulica());
			stmt.setBoolean(27, veiculosOpcionais.isOpBancoCouro());
			stmt.setBoolean(28, veiculosOpcionais.isOpArCondicionado());
			stmt.setBoolean(29, veiculosOpcionais.isOpVidroEletrico());
			stmt.setBoolean(30, veiculosOpcionais.isOpChaveReserva());
			stmt.setBoolean(31, veiculosOpcionais.isOpManual());
			
			stmt.execute();
			
			stmt.close();
		} catch (SQLException e) {
			LOG.error(e.getMessage(), e.getCause());
			throw new RuntimeException(e);
		}
	}
	
	
	public void atualizaOpcionaisVeiculos(VeiculosOpcionais veiculosOpcionais){		
		
		try {			
			String strSql = "update opcionaisVeiculo set veiculo_id = ?, opCompleto = ?, opArQuente = ?, opFarolXenon = ? " +
							"                              , opBancoRegAltura = ?, opGps = ?, opLimpTraseiro = ?, opTravaEletrica = ? " +
							"                              , opRadio = ?, opCapotaMaritima = ?, opRadioTocaFita = ?, opCdMp3 = ? " +
							"                              , opCompBordo = ?, opRetroFotocromico = ?, opRetrovEletrico = ?, opRodLigaLeve = ? " +
							"                              , opSensorEstacionamento = ?, opDVD = ?, opTetoSolar = ?, opDesembTraseito = ? " +
							"                              , opTracao4x4 = ?, opAirBag = ?, opVolRegAltura = ?, opABS = ?, opAlarme = ? " +
							"                              , opDirecaoHidraulica = ?, opBancoCouro = ?, opArCondicionado = ?, opVidroEletrico = ? " +
							"                              , opChaveReserva = ?, opManual = ? " +
							"       where veiculo_id = ? ";
	
			PreparedStatement stmt = (PreparedStatement) cnn.prepareStatement(strSql);
			stmt.setLong(1, veiculosOpcionais.getVeiculo_id());
			stmt.setBoolean(2, veiculosOpcionais.isOpCompleto());
			stmt.setBoolean(3, veiculosOpcionais.isOpArQuente());
			stmt.setBoolean(4, veiculosOpcionais.isOpFarolXenon());
			stmt.setBoolean(5, veiculosOpcionais.isOpBancoRegAltura());
			stmt.setBoolean(6, veiculosOpcionais.isOpGps());
			stmt.setBoolean(7, veiculosOpcionais.isOpLimpTraseiro());
			stmt.setBoolean(8, veiculosOpcionais.isOpTravaEletrica());
			stmt.setBoolean(9, veiculosOpcionais.isOpRadio());
			stmt.setBoolean(10, veiculosOpcionais.isOpCapotaMaritima());
			stmt.setBoolean(11, veiculosOpcionais.isOpRadioTocaFita());
			stmt.setBoolean(12, veiculosOpcionais.isOpCdMp3());
			stmt.setBoolean(13, veiculosOpcionais.isOpCompBordo());
			stmt.setBoolean(14, veiculosOpcionais.isOpRetroFotocromico());
			stmt.setBoolean(15, veiculosOpcionais.isOpRetrovEletrico());
			stmt.setBoolean(16, veiculosOpcionais.isOpRodLigaLeve());
			stmt.setBoolean(17, veiculosOpcionais.isOpSensorEstacionamento());
			stmt.setBoolean(18, veiculosOpcionais.isOpDVD());
			stmt.setBoolean(19, veiculosOpcionais.isOpTetoSolar());
			stmt.setBoolean(20, veiculosOpcionais.isOpDesembTraseito());
			stmt.setBoolean(21, veiculosOpcionais.isOpTracao4x4());
			stmt.setBoolean(22, veiculosOpcionais.isOpAirBag());
			stmt.setBoolean(23, veiculosOpcionais.isOpVolRegAltura());
			stmt.setBoolean(24, veiculosOpcionais.isOpABS());
			stmt.setBoolean(25, veiculosOpcionais.isOpAlarme());
			stmt.setBoolean(26, veiculosOpcionais.isOpDirecaoHidraulica());
			stmt.setBoolean(27, veiculosOpcionais.isOpBancoCouro());
			stmt.setBoolean(28, veiculosOpcionais.isOpArCondicionado());
			stmt.setBoolean(29, veiculosOpcionais.isOpVidroEletrico());
			stmt.setBoolean(30, veiculosOpcionais.isOpChaveReserva());
			stmt.setBoolean(31, veiculosOpcionais.isOpManual());
			stmt.setLong(32, veiculosOpcionais.getVeiculo_id());
			
					
			stmt.executeUpdate();
			
			stmt.close();
		} catch (SQLException e) {
			LOG.error(e.getMessage(), e.getCause());
			throw new RuntimeException(e);
		}
	}
	
	public void removeOpcionaisVeiculos (long id, long veiculoId){
		try {
			String strSql = "delete from opcionaisVeiculo " +
			"                      where 1 = 1 ";
			
			if(id > 0){
				strSql = strSql + " and id = " + id;
			}
			if(veiculoId > 0){
				strSql = strSql + " and veiculo_id = " + veiculoId;
			}
			
			PreparedStatement stmt = (PreparedStatement) cnn.prepareStatement(strSql);
			
			stmt.executeUpdate();
			
			stmt.close();		

		} catch (SQLException e) {
			LOG.error(e.getMessage(), e.getCause());
			throw new RuntimeException(e);
		}		
	}
	
	public VeiculosOpcionais buscaOpcionaisVeiculos(long idVeiculo) {		
		
		VeiculosOpcionais veiculosOpcionais = new VeiculosOpcionais();
		
		if(idVeiculo > 0){
			try {
				String strSql = "select id, veiculo_id, opCompleto, opArQuente, opFarolXenon, opBancoRegAltura, opGps" +
						"       , opLimpTraseiro, opTravaEletrica, opRadio, opCapotaMaritima, opRadioTocaFita, opCdMp3" +
						"       , opCompBordo, opRetroFotocromico, opRetrovEletrico, opRodLigaLeve, opSensorEstacionamento" +
						"       , opDVD, opTetoSolar, opDesembTraseito, opTracao4x4, opAirBag, opVolRegAltura, opABS" +
						"       , opAlarme, opDirecaoHidraulica, opBancoCouro, opArCondicionado, opVidroEletrico" +
						"       , opChaveReserva, opManual" +
						" from opcionaisveiculo " +
						" where veiculo_id = ?";

				PreparedStatement stmt = (PreparedStatement) cnn.prepareStatement(strSql);
				stmt.setLong(1, idVeiculo);
				
				ResultSet rs = stmt.executeQuery();
									
				while (rs.next()) {	
					veiculosOpcionais.setId(rs.getLong("id"));
					veiculosOpcionais.setVeiculo_id(rs.getLong("veiculo_id"));
					veiculosOpcionais.setOpCompleto(rs.getBoolean("opCompleto"));					
					veiculosOpcionais.setOpArQuente(rs.getBoolean("opArQuente"));
					veiculosOpcionais.setOpFarolXenon(rs.getBoolean("opFarolXenon"));
					veiculosOpcionais.setOpBancoRegAltura(rs.getBoolean("opBancoRegAltura"));
					veiculosOpcionais.setOpGps(rs.getBoolean("opGps"));
					veiculosOpcionais.setOpLimpTraseiro(rs.getBoolean("opLimpTraseiro"));
					veiculosOpcionais.setOpTravaEletrica(rs.getBoolean("opTravaEletrica"));
					veiculosOpcionais.setOpRadio(rs.getBoolean("opRadio"));
					veiculosOpcionais.setOpCapotaMaritima(rs.getBoolean("opCapotaMaritima"));
					veiculosOpcionais.setOpRadioTocaFita(rs.getBoolean("opRadioTocaFita"));
					veiculosOpcionais.setOpCdMp3(rs.getBoolean("opCdMp3"));
					veiculosOpcionais.setOpCompBordo(rs.getBoolean("opCompBordo"));
					veiculosOpcionais.setOpRetroFotocromico(rs.getBoolean("opRetroFotocromico"));
					veiculosOpcionais.setOpRetrovEletrico(rs.getBoolean("opRetrovEletrico"));
					veiculosOpcionais.setOpRodLigaLeve(rs.getBoolean("opRodLigaLeve"));
					veiculosOpcionais.setOpSensorEstacionamento(rs.getBoolean("opSensorEstacionamento"));
					veiculosOpcionais.setOpDVD(rs.getBoolean("opDVD"));
					veiculosOpcionais.setOpTetoSolar(rs.getBoolean("opTetoSolar"));
					veiculosOpcionais.setOpDesembTraseito(rs.getBoolean("opDesembTraseito"));
					veiculosOpcionais.setOpTracao4x4(rs.getBoolean("opTracao4x4"));
					veiculosOpcionais.setOpAirBag(rs.getBoolean("opAirBag"));
					veiculosOpcionais.setOpVolRegAltura(rs.getBoolean("opVolRegAltura"));
					veiculosOpcionais.setOpABS(rs.getBoolean("opABS"));
					veiculosOpcionais.setOpAlarme(rs.getBoolean("opAlarme"));
					veiculosOpcionais.setOpDirecaoHidraulica(rs.getBoolean("opDirecaoHidraulica"));
					veiculosOpcionais.setOpBancoCouro(rs.getBoolean("opBancoCouro"));
					veiculosOpcionais.setOpArCondicionado(rs.getBoolean("opArCondicionado"));
					veiculosOpcionais.setOpVidroEletrico(rs.getBoolean("opVidroEletrico"));
					veiculosOpcionais.setOpChaveReserva(rs.getBoolean("opChaveReserva"));
					veiculosOpcionais.setOpManual(rs.getBoolean("opManual"));
				}				
				stmt.close();

			} catch (SQLException e) {
				LOG.error(e.getMessage(), e.getCause());
				throw new RuntimeException(e);
			}			
		}		
		return veiculosOpcionais;
	}
	
	public boolean existeOpcionaisVeiculos (long id , long veiculoId){
		try {			
			String strSql = " select id " +
					        "   from opcionaisveiculo" +
					        "  where 1 = 1 ";		

			if(id > 0){
				strSql = strSql + " and id = " + id;
			}
			if(veiculoId > 0){
				strSql = strSql + " and veiculo_id = " + veiculoId;
			}
			
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
	

}
