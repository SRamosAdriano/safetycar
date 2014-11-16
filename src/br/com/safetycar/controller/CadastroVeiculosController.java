package br.com.safetycar.controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.safetycar.connection.ConnectionFactory;
import br.com.safetycar.modelos.ClienteFornecedor;
import br.com.safetycar.modelos.CompraVeiculo;
import br.com.safetycar.modelos.Veiculos;
import br.com.safetycar.modelos.VeiculosOpcionais;
import br.com.safetycar.paginacao.ListaPaginacao;
import br.com.safetycar.paginacao.Paginador;
import br.com.safetycar.servico.dao.CadastroVeiculoDAO;
import br.com.safetycar.servico.dao.impl.CadastroVeiculoDAOImpl;

@Controller
public class CadastroVeiculosController {
	
	private static Logger LOG = Logger.getLogger(CadastroVeiculosController.class.getName());
	
	public static final String PARAMETRO_PAGINA = "pagina";
	public static final String PARAMETRO_ID = "id";
	
	@RequestMapping("/cadastroVeiculo")
	public String cadastroVeiculo(Model model) {
		
		Connection connectionBD = new ConnectionFactory().getConnection();
		
		CadastroVeiculoDAO dao = new CadastroVeiculoDAOImpl(connectionBD);
		
		model.addAttribute("marcasVeiculos", dao.getMarcasVeiculos());
		model.addAttribute("estados", dao.getSiglaEstados());					
		model.addAttribute("combustivelVeiculos", dao.getCombustivelVeiculos());
		
		Veiculos veiculo = new Veiculos();
		model.addAttribute("veiculo", veiculo);		
						
		model.addAttribute("radioConsignado", "1");
		
		VeiculosOpcionais veiculosOpcionais = new VeiculosOpcionais();
		veiculosOpcionais.setOpCompleto(false);
		veiculosOpcionais.setOpArQuente(false);
		veiculosOpcionais.setOpGps(false);
		veiculosOpcionais.setOpRadio(false);
		veiculosOpcionais.setOpCdMp3(false);
		veiculosOpcionais.setOpRetrovEletrico(false);
		veiculosOpcionais.setOpDVD(false);
		veiculosOpcionais.setOpTracao4x4(false);
		veiculosOpcionais.setOpABS(false);
		veiculosOpcionais.setOpBancoCouro(false);
		veiculosOpcionais.setOpFarolXenon(false);
		veiculosOpcionais.setOpLimpTraseiro(false);
		veiculosOpcionais.setOpCapotaMaritima(false);
		veiculosOpcionais.setOpCompBordo(false);
		veiculosOpcionais.setOpRodLigaLeve(false);
		veiculosOpcionais.setOpTetoSolar(false);
		veiculosOpcionais.setOpAirBag(false);
		veiculosOpcionais.setOpAlarme(false);
		veiculosOpcionais.setOpArCondicionado(false);
		veiculosOpcionais.setOpBancoRegAltura(false);
		veiculosOpcionais.setOpTravaEletrica(false);
		veiculosOpcionais.setOpRadioTocaFita(false);
		veiculosOpcionais.setOpRetroFotocromico(false);
		veiculosOpcionais.setOpSensorEstacionamento(false);
		veiculosOpcionais.setOpDesembTraseito(false);
		veiculosOpcionais.setOpVolRegAltura(false);
		veiculosOpcionais.setOpDirecaoHidraulica(false);
		veiculosOpcionais.setOpVidroEletrico(false);
		veiculosOpcionais.setOpChaveReserva(false);
		veiculosOpcionais.setOpManual(false);
		model.addAttribute("veiculosOpcionais", veiculosOpcionais);
		
		CompraVeiculo veiculoCompra = new CompraVeiculo();
		veiculoCompra.setValorCompra(0.0);
		model.addAttribute("compraVeiculo", veiculoCompra);
		
		model.addAttribute("exibeCliFor", false);
		
		try {
			connectionBD.close();				
		} catch (SQLException e) {
			LOG.error(e.getMessage(), e.getCause());
			throw new RuntimeException(e);
		}
				
		return "cadastroVeiculos/cadastroVeiculo";
	}
	
	
	@RequestMapping("/pesquisaFornecedor")
	public String pesquisaFornecedor(HttpServletRequest request, Model model) {
		
		String pesqNome = request.getParameter("pesqNome");
		String pesqCpf = request.getParameter("pesqCpf");
		String pesqRg = request.getParameter("pesqRg");
		
		Connection connectionBD = new ConnectionFactory().getConnection();	
		
		CadastroVeiculoDAO dao = new CadastroVeiculoDAOImpl(connectionBD);
				
		int numeroPaginaAtual = ServletRequestUtils.getIntParameter(request, PARAMETRO_PAGINA, 1);
		int qtdePorPagina = 10;
		Paginador paginador = new Paginador();
		List<ClienteFornecedor> clisFors = dao.pesquisaFornecedor(pesqNome, pesqCpf, pesqRg, "ativo", numeroPaginaAtual, qtdePorPagina);
		ListaPaginacao report = paginador.criarListaPaginacao(clisFors, dao.countFornecedor(pesqNome, pesqCpf, pesqRg, "ativo"), numeroPaginaAtual);		
		model.addAttribute("itensRelatorio", report);
		
		model.addAttribute("pesqNome", pesqNome);
		model.addAttribute("pesqCpf", pesqCpf);
		model.addAttribute("pesqRg", pesqRg);
		
		//Mantem dados da Tela anterior
		Veiculos veiculo = getDadosVeiculo(request);
		VeiculosOpcionais veiculosOpcionais =  getOpcionaisVeiculo(request);
		CompraVeiculo compraVeiculo =  getVeiculoCompra(request);
		model.addAttribute("veiculo", veiculo);
		model.addAttribute("veiculosOpcionais", veiculosOpcionais);
		model.addAttribute("compraVeiculo", compraVeiculo);
		
		try {
			connectionBD.close();				
		} catch (SQLException e) {	
			LOG.error(e.getMessage(), e.getCause());
			throw new RuntimeException(e);
		}
		
		return "cadastroVeiculos/pesquisaFornecedor";
	}
	
	
	@RequestMapping("/fornecedorSelecionado")
	public String fornecedorSelecionado(Long id, HttpSession session,  Model model, HttpServletRequest request){
		
		Connection connectionBD = new ConnectionFactory().getConnection();
		
		CadastroVeiculoDAO dao = new CadastroVeiculoDAOImpl(connectionBD);		
		
		model.addAttribute("marcasVeiculos", dao.getMarcasVeiculos());		
		model.addAttribute("estados", dao.getSiglaEstados());			
		model.addAttribute("combustivelVeiculos", dao.getCombustivelVeiculos());

		ClienteFornecedor clifor = dao.pesquisaFornecedor(id);				
		model.addAttribute("cliFor", clifor);
		model.addAttribute("exibeCliFor", true);
		
		
		//Mantem dados da Tela anterior
		Veiculos veiculo = getDadosVeiculo(request);
		VeiculosOpcionais veiculosOpcionais =  getOpcionaisVeiculo(request);
		CompraVeiculo compraVeiculo =  getVeiculoCompra(request);
		model.addAttribute("veiculo", veiculo);
		model.addAttribute("veiculosOpcionais", veiculosOpcionais);
		model.addAttribute("compraVeiculo", compraVeiculo);
		
		if(compraVeiculo.isConsignado()){
			model.addAttribute("radioConsignado", "2");
		}else{
			model.addAttribute("radioConsignado", "1");
		}	
								
		try {
			connectionBD.close();				
		} catch (SQLException e) {
			LOG.error(e.getMessage(), e.getCause());
			throw new RuntimeException(e);
		}
		return "cadastroVeiculos/cadastroVeiculo";
	}	
	
		
	// Popula Veiculos
	private static Veiculos getDadosVeiculo (HttpServletRequest request){
		
		Veiculos veiculo = new Veiculos();
		
		String id = request.getParameter("id");
		if(id != null && id.replaceAll(" ", "").length() > 0){
			veiculo.setId(Long.valueOf(id));						
		}		

		veiculo.setPlaca(request.getParameter("placa"));

		veiculo.setCidade(request.getParameter("cidade"));

		veiculo.setEstado(request.getParameter("estado"));

		veiculo.setMarca(request.getParameter("marca"));
		
		veiculo.setModelo(request.getParameter("modelo"));

		veiculo.setVersao(request.getParameter("versao"));
		
		String anoFab = request.getParameter("anoFab");
		if(anoFab != null && anoFab.replaceAll(" ", "").length() > 0){
			veiculo.setAnoFab(Integer.parseInt(anoFab));			
		}
		
		String anoMod = request.getParameter("anoMod");
		if(anoMod != null && anoMod.replaceAll(" ", "").length() > 0){
			veiculo.setAnoMod(Integer.parseInt(anoMod));			
		}
		
		String renavan = request.getParameter("renavan");
		if(renavan != null && renavan.replaceAll(" ", "").length() > 0){
			veiculo.setRenavan(Integer.parseInt(renavan));			
		}

		veiculo.setChassi(request.getParameter("chassi"));
		
		String km = request.getParameter("km");
		if(km != null && km.replaceAll(" ", "").length() > 0){
			veiculo.setKm(Long.parseLong(km));						
		}

		veiculo.setCombustivel(request.getParameter("combustivel"));

		veiculo.setCor(request.getParameter("cor"));
		
		
		try {
			String dataEntrada = request.getParameter("dataEntrada");
			if(dataEntrada != null && !dataEntrada.equals("") && dataEntrada.length() <= 10 ){
				SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
				formatoData.parse(dataEntrada);
				
				Calendar calendar = Calendar.getInstance();  
				
				calendar.setTime(formatoData.parse(dataEntrada));
				veiculo.setDataCadastro(calendar);
				
			}					
		} catch (ParseException e) {
			LOG.error(e.getMessage(), e.getCause());
			throw new RuntimeException(e);
		}
		
		
		veiculo.setInfDocNome(request.getParameter("infDocNome"));

		veiculo.setInfDocEndereco(request.getParameter("infDocEndereco"));
			
		return veiculo;
	}
	
	// Popula VeiculosOpcionais
	private static VeiculosOpcionais getOpcionaisVeiculo(HttpServletRequest request){
		
		VeiculosOpcionais veiculosOpcionais = new VeiculosOpcionais();
		
		String opCompleto = request.getParameter("opCompleto");
		if(opCompleto != null && opCompleto.replaceAll(" ", "").length() > 0){
			veiculosOpcionais.setOpCompleto(Boolean.valueOf(opCompleto));
			
		}
		
		String opArQuente = request.getParameter("opArQuente");
		if(opArQuente != null && opArQuente.replaceAll(" ", "").length() > 0){
			veiculosOpcionais.setOpArQuente(Boolean.valueOf(opArQuente));			
		}
		
		String opGps = request.getParameter("opGps");
		if(opGps != null && opGps.replaceAll(" ", "").length() > 0){
			veiculosOpcionais.setOpGps(Boolean.valueOf(opGps));			
		}
		
		String opRadio = request.getParameter("opRadio");
		if(opRadio != null && opRadio.replaceAll(" ", "").length() > 0){
			veiculosOpcionais.setOpRadio(Boolean.valueOf(opRadio));			
		}
		
		String opCdMp3 = request.getParameter("opCdMp3");
		if(opCdMp3 != null && opCdMp3.replaceAll(" ", "").length() > 0){
			veiculosOpcionais.setOpCdMp3(Boolean.valueOf(opCdMp3));			
		}
		
		String opRetrovEletrico = request.getParameter("opRetrovEletrico");
		if(opRetrovEletrico != null && opRetrovEletrico.replaceAll(" ", "").length() > 0){
			veiculosOpcionais.setOpRetrovEletrico(Boolean.valueOf(opRetrovEletrico));			
		}
		
		String opDVD = request.getParameter("opDVD");
		if(opDVD != null && opDVD.replaceAll(" ", "").length() > 0){
			veiculosOpcionais.setOpDVD(Boolean.valueOf(opDVD));			
		}
		
		String opTracao4x4 = request.getParameter("opTracao4x4");
		if(opTracao4x4 != null && opTracao4x4.replaceAll(" ", "").length() > 0){
			veiculosOpcionais.setOpTracao4x4(Boolean.valueOf(opTracao4x4));			
		}
		
		String opBancoCouro = request.getParameter("opBancoCouro");
		if(opBancoCouro != null && opBancoCouro.replaceAll(" ", "").length() > 0){
			veiculosOpcionais.setOpBancoCouro(Boolean.valueOf(opBancoCouro));			
		}
		
		String opFarolXenon = request.getParameter("opFarolXenon");
		if(opFarolXenon != null && opFarolXenon.replaceAll(" ", "").length() > 0){
			veiculosOpcionais.setOpFarolXenon(Boolean.valueOf(opFarolXenon));			
		}
		
		String opLimpTraseiro = request.getParameter("opLimpTraseiro");
		if(opLimpTraseiro != null && opLimpTraseiro.replaceAll(" ", "").length() > 0){
			veiculosOpcionais.setOpLimpTraseiro(Boolean.valueOf(opLimpTraseiro));			
		}
		
		String opCapotaMaritima = request.getParameter("opCapotaMaritima");
		if(opCapotaMaritima != null && opCapotaMaritima.replaceAll(" ", "").length() > 0){
			veiculosOpcionais.setOpCapotaMaritima(Boolean.valueOf(opCapotaMaritima));			
		}
		
		String opCompBordo = request.getParameter("opCompBordo");
		if(opCompBordo != null && opCompBordo.replaceAll(" ", "").length() > 0){
			veiculosOpcionais.setOpCompBordo(Boolean.valueOf(opCompBordo));			
		}
		
		String opRodLigaLeve = request.getParameter("opRodLigaLeve");
		if(opRodLigaLeve != null && opRodLigaLeve.replaceAll(" ", "").length() > 0){
			veiculosOpcionais.setOpRodLigaLeve(Boolean.valueOf(opRodLigaLeve));			
		}
		
		String opTetoSolar = request.getParameter("opTetoSolar");
		if(opTetoSolar != null && opTetoSolar.replaceAll(" ", "").length() > 0){
			veiculosOpcionais.setOpTetoSolar(Boolean.valueOf(opTetoSolar));			
		}
		
		String opAirBag = request.getParameter("opAirBag");
		if(opAirBag != null && opAirBag.replaceAll(" ", "").length() > 0){
			veiculosOpcionais.setOpAirBag(Boolean.valueOf(opAirBag));			
		}
		
		String opAlarme = request.getParameter("opAlarme");
		if(opAlarme != null && opAlarme.replaceAll(" ", "").length() > 0){
			veiculosOpcionais.setOpAlarme(Boolean.valueOf(opAlarme));			
		}
		
		String opArCondicionado = request.getParameter("opArCondicionado");
		if(opArCondicionado != null && opArCondicionado.replaceAll(" ", "").length() > 0){
			veiculosOpcionais.setOpArCondicionado(Boolean.valueOf(opArCondicionado));			
		}
		
		String opBancoRegAltura = request.getParameter("opBancoRegAltura");
		if(opBancoRegAltura != null && opBancoRegAltura.replaceAll(" ", "").length() > 0){
			veiculosOpcionais.setOpBancoRegAltura(Boolean.valueOf(opBancoRegAltura));			
		}
		
		String opTravaEletrica = request.getParameter("opTravaEletrica");
		if(opTravaEletrica != null && opTravaEletrica.replaceAll(" ", "").length() > 0){
			veiculosOpcionais.setOpTravaEletrica(Boolean.valueOf(opTravaEletrica));			
		}
		
		String opRadioTocaFita = request.getParameter("opRadioTocaFita");
		if(opRadioTocaFita != null && opRadioTocaFita.replaceAll(" ", "").length() > 0){
			veiculosOpcionais.setOpRadioTocaFita(Boolean.valueOf(opRadioTocaFita));			
		}
		
		String opRetroFotocromico = request.getParameter("opRetroFotocromico");
		if(opRetroFotocromico != null && opRetroFotocromico.replaceAll(" ", "").length() > 0){
			veiculosOpcionais.setOpRetroFotocromico(Boolean.valueOf(opRetroFotocromico));			
		}
		
		String opSensorEstacionamento = request.getParameter("opSensorEstacionamento");
		if(opSensorEstacionamento != null && opSensorEstacionamento.replaceAll(" ", "").length() > 0){
			veiculosOpcionais.setOpSensorEstacionamento(Boolean.valueOf(opSensorEstacionamento));			
		}
		
		String opDesembTraseito= request.getParameter("opDesembTraseito");
		if(opDesembTraseito != null && opDesembTraseito.replaceAll(" ", "").length() > 0){
			veiculosOpcionais.setOpDesembTraseito(Boolean.valueOf(opDesembTraseito));			
		}
		
		String opVolRegAltura = request.getParameter("opVolRegAltura");
		if(opVolRegAltura != null && opVolRegAltura.replaceAll(" ", "").length() > 0){
			veiculosOpcionais.setOpVolRegAltura(Boolean.valueOf(opVolRegAltura));			
		}
		
		String opDirecaoHidraulica = request.getParameter("opDirecaoHidraulica");
		if(opDirecaoHidraulica != null && opDirecaoHidraulica.replaceAll(" ", "").length() > 0){
			veiculosOpcionais.setOpDirecaoHidraulica(Boolean.valueOf(opDirecaoHidraulica));			
		}
		
		String opABS = request.getParameter("opABS");
		if(opABS != null && opABS.replaceAll(" ", "").length() > 0){
			veiculosOpcionais.setOpABS(Boolean.valueOf(opABS));			
		}
		
		String opVidroEletrico = request.getParameter("opVidroEletrico");
		if(opVidroEletrico != null && opVidroEletrico.replaceAll(" ", "").length() > 0){
			veiculosOpcionais.setOpVidroEletrico(Boolean.valueOf(opVidroEletrico));			
		}
		
		String opChaveReserva = request.getParameter("opChaveReserva");
		if(opChaveReserva != null && opChaveReserva.replaceAll(" ", "").length() > 0){
			veiculosOpcionais.setOpChaveReserva(Boolean.valueOf(opChaveReserva));			
		}
		
		String opManual = request.getParameter("opManual");
		if(opManual != null && opManual.replaceAll(" ", "").length() > 0){
			veiculosOpcionais.setOpManual(Boolean.valueOf(opManual));			
		}
				
		return veiculosOpcionais;
	}
	
	// Popula CompraVeiculo
	private static CompraVeiculo getVeiculoCompra(HttpServletRequest request){
		CompraVeiculo veiculoCompra = new CompraVeiculo();
		
		try {
			String dataEntrada = request.getParameter("dataEntrada");
			if(dataEntrada != null && !dataEntrada.equals("") && dataEntrada.length() <= 10 ){
				SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
				formatoData.parse(dataEntrada);
				
				Calendar calendar = Calendar.getInstance(); 			
				calendar.setTime(formatoData.parse(dataEntrada));
					
				veiculoCompra.setDataCompra(calendar);
							
			}					
		} catch (ParseException e) {	
			LOG.error(e.getMessage(), e.getCause());
			throw new RuntimeException(e);
		}
		
		String valorCompra = request.getParameter("valorCompra");
		if(valorCompra != null){
			valorCompra = valorCompra.replace("R$", "");
		}
		if(valorCompra != null && !valorCompra.equals("") && !valorCompra.equals("0") && valorCompra.length() > 1){		
			veiculoCompra.setValorCompra(new Double(valorCompra.substring(0,valorCompra.length()-3).replace(".", "") + valorCompra.substring(valorCompra.length()-3).replace(",", ".")));			
		}else if(valorCompra != null && valorCompra.length() == 1){
			veiculoCompra.setValorCompra(Double.valueOf(valorCompra));			
		}else{
			veiculoCompra.setValorCompra(0d);
		}
		
		String consignado = request.getParameter("consignado");
		if(consignado != null){
			if(consignado.equals("sim") || consignado.equals("true")){
				veiculoCompra.setConsignado(true);
			}else{
				veiculoCompra.setConsignado(false);
			}
		}		
		return veiculoCompra;
	}

}
