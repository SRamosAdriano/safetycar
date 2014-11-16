<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

 <!-- Layout -->
<c:import url="../layout/cabecalho.jsp" />

<!-- Scripts Padrões -->
<script type="text/javascript" src="/safetycar/resources/javaScript/scriptPadrao.js"></script>

<link rel="stylesheet" href="/safetycar/resources/jquery/css/jquery-ui-1.8.20.custom.css" />

<script type="text/javascript" src="/safetycar/resources/jquery/js/jquery-1.7.2.js"></script>
<script type="text/javascript" src="/safetycar/resources/jquery/js/jquery.mim.js"></script>
<script type="text/javascript" src="/safetycar/resources/jquery/js/jquery.ui.datepicker.js"></script>
<script type="text/javascript" src="/safetycar/resources/jquery/js/jquery.ui.core.js"></script>
<script type="text/javascript" src="/safetycar/resources/jquery/js/jquery.ui.datepicker-pt-BR.js"></script>


<script>
	$(function() {
		$( "#dataEntrada" ).datepicker();
	});
</script>

<script type='text/javascript' src='/safetycar/dwr/engine.js'></script>
<script type='text/javascript' src='/safetycar/dwr/interface/alteraCadastroVeiculosAjaxDwr.js'></script>
<script type='text/javascript' src='/safetycar/dwr/util.js'></script>

<script language="JavaScript" type="text/JavaScript">
	function direcionaTelaPesquisa(){
		
		var parametros = "";
		
		parametros += "?idVeiculo=" + document.getElementById("idVeiculo").value;
		parametros += "&placa=" + document.getElementById("placa").value;		
		parametros += "&cidade=" + document.getElementById("cidade").value;
		parametros += "&estado=" + document.getElementById("estado").value;
		parametros += "&marca=" + document.getElementById("marca").value;
		parametros += "&modelo=" + document.getElementById("modelo").value;
		parametros += "&versao=" + document.getElementById("versao").value;
		parametros += "&anoFab=" + document.getElementById("anoFab").value;
		parametros += "&anoMod=" + document.getElementById("anoMod").value;
		parametros += "&renavan=" + document.getElementById("renavan").value;
		parametros += "&chassi=" + document.getElementById("chassi").value;
		parametros += "&km=" + document.getElementById("km").value;
		parametros += "&combustivel=" + document.getElementById("combustivel").value;
		parametros += "&cor=" + document.getElementById("cor").value;
		parametros += "&dataEntrada=" + document.getElementById("dataEntrada").value;
		parametros += "&valorCompra=" + document.getElementById("valorCompra").value;
		parametros += "&status=" + document.getElementById("status").value;
		
		var objRadio = document.forms[0].elements["consignado"];
	    for(var i=0; i < objRadio.length; i++ ) {
	        if (objRadio[i].checked == true) 
	        	parametros += "&consignado=" + objRadio[i].value;
	    }		
		
		if(document.getElementById("opCompleto").checked){
			parametros += "&opCompleto=" + document.getElementById("opCompleto").value;
		}else{
			parametros += "&opCompleto=";
		}
		if(document.getElementById("opArQuente").checked){
			parametros += "&opArQuente=" + document.getElementById("opArQuente").value;
		}else{
			parametros += "&opArQuente=";
		}
		if(document.getElementById("opFarolXenon").checked){
			parametros += "&opFarolXenon=" + document.getElementById("opFarolXenon").value;
		}else{
			parametros += "&opFarolXenon=";
		}
		if(document.getElementById("opBancoRegAltura").checked){
			parametros += "&opBancoRegAltura=" + document.getElementById("opBancoRegAltura").value;
		}else{
			parametros += "&opBancoRegAltura=";
		}
		if(document.getElementById("opGps").checked){
			parametros += "&opGps=" + document.getElementById("opGps").value;
		}else{
			parametros += "&opGps=";
		}
		if(document.getElementById("opLimpTraseiro").checked){
			parametros += "&opLimpTraseiro=" + document.getElementById("opLimpTraseiro").value;
		}else{
			parametros += "&opLimpTraseiro=";
		}
		if(document.getElementById("opTravaEletrica").checked){
			parametros += "&opTravaEletrica=" + document.getElementById("opTravaEletrica").value;
		}else{
			parametros += "&opTravaEletrica=";
		}	
		if(document.getElementById("opRadio").checked){
			parametros += "&opRadio=" + document.getElementById("opRadio").value;
		}else{
			parametros += "&opRadio=";
		}
		if(document.getElementById("opCapotaMaritima").checked){
			parametros += "&opCapotaMaritima=" + document.getElementById("opCapotaMaritima").value;
		}else{
			parametros += "&opCapotaMaritima=";
		}
		if(document.getElementById("opRadioTocaFita").checked){
			parametros += "&opRadioTocaFita=" + document.getElementById("opRadioTocaFita").value;
		}else{
			parametros += "&opRadioTocaFita=";
		}
		if(document.getElementById("opCdMp3").checked){
			parametros += "&opCdMp3=" + document.getElementById("opCdMp3").value;
		}else{
			parametros += "&opCdMp3=";
		}
		if(document.getElementById("opCompBordo").checked){
			parametros += "&opCompBordo=" + document.getElementById("opCompBordo").value;
		}else{
			parametros += "&opCompBordo=";
		}
		if(document.getElementById("opRetroFotocromico").checked){
			parametros += "&opRetroFotocromico=" + document.getElementById("opRetroFotocromico").value;
		}else{
			parametros += "&opRetroFotocromico=";
		}
		if(document.getElementById("opRetrovEletrico").checked){
			parametros += "&opRetrovEletrico=" + document.getElementById("opRetrovEletrico").value;
		}else{
			parametros += "&opRetrovEletrico=";
		}	
		if(document.getElementById("opRodLigaLeve").checked){
			parametros += "&opRodLigaLeve=" + document.getElementById("opRodLigaLeve").value;
		}else{
			parametros += "&opRodLigaLeve=";
		}
		if(document.getElementById("opSensorEstacionamento").checked){
			parametros += "&opSensorEstacionamento=" + document.getElementById("opSensorEstacionamento").value;
		}else{
			parametros += "&opSensorEstacionamento=";
		}
		if(document.getElementById("opDVD").checked){
			parametros += "&opDVD=" + document.getElementById("opDVD").value;
		}else{
			parametros += "&opDVD=";
		}
		if(document.getElementById("opTetoSolar").checked){
			parametros += "&opTetoSolar=" + document.getElementById("opTetoSolar").value;
		}else{
			parametros += "&opTetoSolar=";
		}
		if(document.getElementById("opDesembTraseito").checked){
			parametros += "&opDesembTraseito=" + document.getElementById("opDesembTraseito").value;
		}else{
			parametros += "&opDesembTraseito=";
		}
		if(document.getElementById("opTracao4x4").checked){
			parametros += "&opTracao4x4=" + document.getElementById("opTracao4x4").value;
		}else{
			parametros += "&opTracao4x4=";
		}
		if(document.getElementById("opAirBag").checked){
			parametros += "&opAirBag=" + document.getElementById("opAirBag").value;
		}else{
			parametros += "&opAirBag=";
		}
		if(document.getElementById("opVolRegAltura").checked){
			parametros += "&opVolRegAltura=" + document.getElementById("opVolRegAltura").value;
		}else{
			parametros += "&opVolRegAltura=";
		}
		if(document.getElementById("opABS").checked){
			parametros += "&opABS=" + document.getElementById("opABS").value;
		}else{
			parametros += "&opABS=";
		}
		if(document.getElementById("opAlarme").checked){
			parametros += "&opAlarme=" + document.getElementById("opAlarme").value;
		}else{
			parametros += "&opAlarme=";
		}
		if(document.getElementById("opDirecaoHidraulica").checked){
			parametros += "&opDirecaoHidraulica=" + document.getElementById("opDirecaoHidraulica").value;
		}else{
			parametros += "&opDirecaoHidraulica=";
		}
		if(document.getElementById("opBancoCouro").checked){
			parametros += "&opBancoCouro=" + document.getElementById("opBancoCouro").value;
		}else{
			parametros += "&opBancoCouro=";
		}
		if(document.getElementById("opArCondicionado").checked){
			parametros += "&opArCondicionado=" + document.getElementById("opArCondicionado").value;
		}else{
			parametros += "&opArCondicionado=";
		}
		if(document.getElementById("opVidroEletrico").checked){
			parametros += "&opVidroEletrico=" + document.getElementById("opVidroEletrico").value;
		}else{
			parametros += "&opVidroEletrico=";
		}
		if(document.getElementById("opChaveReserva").checked){
			parametros += "&opChaveReserva=" + document.getElementById("opChaveReserva").value;
		}else{
			parametros += "&opChaveReserva=";
		}
		if(document.getElementById("opManual").checked){
			parametros += "&opManual=" + document.getElementById("opManual").value;
		}else{
			parametros += "&opManual=";
		}
		
				

		parametros += "&infDocNome=" + document.getElementById("infDocNome").value;
		parametros += "&infDocEndereco=" + document.getElementById("infDocEndereco").value;		

		window.location="alteracaoVeiculoPesquisaFornecedor" + parametros;
	}
	
	function adicionar(){
		var veiculo = {
				id:dwr.util.getValue("idVeiculo"), 
				placa:dwr.util.getValue("placa"),	
				cidade:dwr.util.getValue("cidade"),
				estado:dwr.util.getValue("estado"),
				marca:dwr.util.getValue("marca"),
				modelo:dwr.util.getValue("modelo"),
				versao:dwr.util.getValue("versao"),
				anoFab:dwr.util.getValue("anoFab"),
				anoMod:dwr.util.getValue("anoMod"),
				renavan:dwr.util.getValue("renavan"),				
				chassi:dwr.util.getValue("chassi"),
				km:dwr.util.getValue("km"),
				combustivel:dwr.util.getValue("combustivel"),
				cor:dwr.util.getValue("cor"),									
				infDocNome:dwr.util.getValue("infDocNome"),
				infDocEndereco:dwr.util.getValue("infDocEndereco"),
				status:dwr.util.getValue("status")
		};
		
		
		var objRadio = document.forms[0].elements["consignado"];
		var vlConsignado="";
	    for(var i=0; i < objRadio.length; i++ ) {
	        if (objRadio[i].checked == true) 
	        	vlConsignado = objRadio[i].value;
	    }		
		var compraVeiculo = {				
				consignado:vlConsignado,
				fornecedor_id:dwr.util.getValue("id")
		};
		var valorCompra = document.getElementById("valorCompra").value;
		
		
		var opCompleto = false;
		if(document.getElementById("opCompleto").checked){
			opCompleto = document.getElementById("opCompleto").value;
		}
		var opArQuente = false;
		if(document.getElementById("opArQuente").checked){
			opArQuente = document.getElementById("opArQuente").value;
		}
		var opFarolXenon = false;
		if(document.getElementById("opFarolXenon").checked){
			opFarolXenon = document.getElementById("opFarolXenon").value;
		}
		var opBancoRegAltura = false;
		if(document.getElementById("opBancoRegAltura").checked){
			opBancoRegAltura = document.getElementById("opBancoRegAltura").value;
		}
		var opGps = false;
		if(document.getElementById("opGps").checked){
			opGps = document.getElementById("opGps").value;
		}
		var opLimpTraseiro = false;
		if(document.getElementById("opLimpTraseiro").checked){
			opLimpTraseiro = document.getElementById("opLimpTraseiro").value;
		}
		var opTravaEletrica = false;
		if(document.getElementById("opTravaEletrica").checked){
			opTravaEletrica = document.getElementById("opTravaEletrica").value;
		}
		var opRadio = false;
		if(document.getElementById("opRadio").checked){
			opRadio = document.getElementById("opRadio").value;
		}
		var opCapotaMaritima = false;
		if(document.getElementById("opCapotaMaritima").checked){
			opCapotaMaritima = document.getElementById("opCapotaMaritima").value;
		}
		var opRadioTocaFita = false;
		if(document.getElementById("opRadioTocaFita").checked){
			opRadioTocaFita = document.getElementById("opRadioTocaFita").value;
		}
		var opCdMp3 = false;
		if(document.getElementById("opCdMp3").checked){
			opCdMp3 = document.getElementById("opCdMp3").value;
		}
		var opCompBordo = false;
		if(document.getElementById("opCompBordo").checked){
			opCompBordo = document.getElementById("opCompBordo").value;
		}
		var opRetroFotocromico = false;
		if(document.getElementById("opRetroFotocromico").checked){
			opRetroFotocromico = document.getElementById("opRetroFotocromico").value;
		}
		var opRetrovEletrico = false;
		if(document.getElementById("opRetrovEletrico").checked){
			opRetrovEletrico = document.getElementById("opRetrovEletrico").value;
		}
		var opRodLigaLeve = false;
		if(document.getElementById("opRodLigaLeve").checked){
			opRodLigaLeve = document.getElementById("opRodLigaLeve").value;
		}
		var opSensorEstacionamento = false;
		if(document.getElementById("opSensorEstacionamento").checked){
			opSensorEstacionamento = document.getElementById("opSensorEstacionamento").value;
		}
		var opDVD = false;
		if(document.getElementById("opDVD").checked){
			opDVD = document.getElementById("opDVD").value;
		}
		var opTetoSolar = false;
		if(document.getElementById("opTetoSolar").checked){
			opTetoSolar = document.getElementById("opTetoSolar").value;
		}
		var opDesembTraseito = false;
		if(document.getElementById("opDesembTraseito").checked){
			opDesembTraseito = document.getElementById("opDesembTraseito").value;
		}
		var opTracao4x4 = false;
		if(document.getElementById("opTracao4x4").checked){
			opTracao4x4 = document.getElementById("opTracao4x4").value;
		}
		var opAirBag = false;
		if(document.getElementById("opAirBag").checked){
			opAirBag = document.getElementById("opAirBag").value;
		}
		var opVolRegAltura = false;
		if(document.getElementById("opVolRegAltura").checked){
			opVolRegAltura = document.getElementById("opVolRegAltura").value;
		}
		var opABS = false;
		if(document.getElementById("opABS").checked){
			opABS = document.getElementById("opABS").value;
		}
		var opAlarme = false;
		if(document.getElementById("opAlarme").checked){
			opAlarme = document.getElementById("opAlarme").value;
		}
		var opDirecaoHidraulica = false;
		if(document.getElementById("opDirecaoHidraulica").checked){
			opDirecaoHidraulica = document.getElementById("opDirecaoHidraulica").value;
		}
		var opBancoCouro = false;
		if(document.getElementById("opBancoCouro").checked){
			opBancoCouro = document.getElementById("opBancoCouro").value;
		}
		var opArCondicionado = false;
		if(document.getElementById("opArCondicionado").checked){
			opArCondicionado = document.getElementById("opArCondicionado").value;
		}
		var opVidroEletrico = false;
		if(document.getElementById("opVidroEletrico").checked){
			opVidroEletrico = document.getElementById("opVidroEletrico").value;
		}
		var opChaveReserva = false;
		if(document.getElementById("opChaveReserva").checked){
			opChaveReserva = document.getElementById("opChaveReserva").value;
		}
		var opManual = false;
		if(document.getElementById("opManual").checked){
			opManual = document.getElementById("opManual").value;
		}
		var veiculosOpcionais = {
				opCompleto:opCompleto,
				opArQuente:opArQuente,
				opFarolXenon:opFarolXenon,
				opBancoRegAltura:opBancoRegAltura,
				opGps:opGps,
				opLimpTraseiro:opLimpTraseiro,
				opTravaEletrica:opTravaEletrica,
				opRadio:opRadio,
				opCapotaMaritima:opCapotaMaritima,
				opRadioTocaFita:opRadioTocaFita,
				opCdMp3:opCdMp3,
				opCompBordo:opCompBordo,
				opRetroFotocromico:opRetroFotocromico,
				opRetrovEletrico:opRetrovEletrico,
				opRodLigaLeve:opRodLigaLeve,
				opSensorEstacionamento:opSensorEstacionamento,
				opDVD:opDVD,
				opTetoSolar:opTetoSolar,
				opDesembTraseito:opDesembTraseito,
				opTracao4x4:opTracao4x4,
				opAirBag:opAirBag,
				opVolRegAltura:opVolRegAltura,
				opABS:opABS,
				opAlarme:opAlarme,
				opDirecaoHidraulica:opDirecaoHidraulica,
				opBancoCouro:opBancoCouro,
				opArCondicionado:opArCondicionado,
				opVidroEletrico:opVidroEletrico,
				opChaveReserva:opChaveReserva,
				opManual:opManual				
		};		

		/* Data calendar */
		var dataEntrada = dwr.util.getValue("dataEntrada");

		alteraCadastroVeiculosAjaxDwr.altVeiculo(veiculo, veiculosOpcionais,compraVeiculo,dataEntrada, valorCompra, {
			callback : function(retorno) {	 			

				if(retorno[0] == 'ERRO'){
					var c = document.getElementById("divShow");
					var table = "<table border='0'align='center' >";
					
					table += "<tr>";
					table += "<td colspan='2' align='center'> <font size='5' face='Times, serif' color='red'><b><i> Aten&ccedil;&atilde;o </i></b></font> </td>";					
					table += "</tr>";
					
					table += "<tr>";
					table += "<td> <img src='/safetycar/resources/imagens/atencao.png' width='60' height='60' border='0'/> </td>";
					table += "<td> <font size='4' face='Times, serif' color='black'>" + retorno[1] + "</font> </td>";
					table += "</tr>";

					table += "</table>";				
		 			c.innerHTML = table;
				}else{
					/*Caso a alteração seja efetuada retorna para pagina de busca*/
					window.location="alteracaoVeiculo";					
				}	 					
			}
		});	
	    
		}  
</script>


<div align="center">
	<font size="5" face="Times, serif" color="black"><b><i>Altera&ccedil;&atilde;o Cadastro de Veiculos</i></b></font>	
</div>

<form action="cadastroVeiculo" method="post">

<table width="98%" border="0" cellpadding="1" cellspacing="0" align="center">	
	<tr>
		<td>			
			<br />
				
<table border="0" cellpadding="3" cellspacing="1" align="center">	
<tr>
	<td colspan="4">
		<table border="0" cellpadding="3" cellspacing="1" align="center">
			<tr>
				<td>
					<font size="4" face="Times, serif" color="black">Placa:</font> <b><font face="Times, serif" color="#FF0000">*</font></b>
				</td>
				<td colspan="3">
					<input id="placa" name="placa" type="text" placeholder="Ex:ABC-1212" style="width:200px; height: 24px;" maxlength="8" onKeyUp="validaPlaca(this); converteMaiuscula(this); return mascara(this, '###-####', event)" value="${veiculo.placa }">
					<input type="hidden" id="idVeiculo" name="idVeiculo"  value="${veiculo.id}">
					<input type="hidden" id="status" name="status"  value="${veiculo.status}">
				</td>					
			</tr>
			
			<tr>
				<td>
					<font size="4" face="Times, serif" color="black">Cidade:</font> <b><font face="Times, serif" color="#FF0000">*</font></b>
				</td>
				<td>
					<input id="cidade" name="cidade" type="text" style="width:200px; height: 24px;" maxlength="149" onkeyup="somenteLetras(this); formataTexto(this);" value="${veiculo.cidade }"> 					
				</td>
				<td>
					<font size="4" face="Times, serif" color="black">Estado:</font> <b><font face="Times, serif" color="#FF0000">*</font></b>
				</td>
				<td align="right">
					<select size="1" id="estado" name="estado" style="width:200px; height: 24px;">
					 	<option value="Selecione">Selecione</option>
					 	<c:forEach items="${estados}" var="estados">
					 		<c:if test="${estados.sigla eq veiculo.estado}">
					 			<option selected="selected"> ${estados.sigla } </option>
					 		</c:if>
					 		<c:if test="${estados.sigla != veiculo.estado}">
					 			<option> ${estados.sigla } </option>
					 		</c:if>					 	
					 	</c:forEach>							
					</select>
				</td>	
			</tr>	
		
			<tr>
				<td>
					<font size="4" face="Times, serif" color="black">Marca:</font> <b><font face="Times, serif" color="#FF0000">*</font></b>
				</td>
				<td>
					<select style="width:200px; height: 24px;" id="marca" name="marca">	
						<option value="Selecione">Selecione</option>
						<c:forEach items="${marcasVeiculos}" var="marcasVeiculos">
							<c:if test="${veiculo.marca eq marcasVeiculos.nomeMarca}">
								<option selected="selected"> ${marcasVeiculos.nomeMarca } </option>
							</c:if>
							<c:if test="${veiculo.marca != marcasVeiculos.nomeMarca}">
								<option> ${marcasVeiculos.nomeMarca } </option>
							</c:if>				
						</c:forEach>
					 </select> 					
				</td>
				<td>
					<font size="4" face="Times, serif" color="black">Modelo	:</font> <b><font face="Times, serif" color="#FF0000">*</font></b>
				</td>
				<td align="right">
					<input id="modelo" name="modelo" type="text" style="width:200px; height: 24px;" maxlength="199" onkeyup="formataTexto(this);" value="${veiculo.modelo }">
				</td>	
			</tr>
			
			<tr>
				<td>
					<font size="4" face="Times, serif" color="black">Vers&atilde;o:</font> <b><font face="Times, serif" color="#FF0000">*</font></b>
				</td>
				<td colspan="3">
					<input id="versao" name="versao" type="text" style="width:540px; height: 24px;" maxlength="199" onkeyup="formataTexto(this);" value="${veiculo.versao }">
				</td>		
			</tr>
			
			<tr>
				<td>
					<font size="4" face="Times, serif" color="black">Ano Fabrica&ccedil;&atilde;o:</font> <b><font face="Times, serif" color="#FF0000">*</font></b>
				</td>
				<td>
					<c:if test="${veiculo.anoFab eq 0}">
						<input id="anoFab" name="anoFab" type="text" style="width:200px; height: 24px;" maxlength="4" onKeyUp="javascript:somenteNumeros(this);" value="">
					</c:if>
					<c:if test="${veiculo.anoFab > 0}">
						<input id="anoFab" name="anoFab" type="text" style="width:200px; height: 24px;" maxlength="4" onKeyUp="javascript:somenteNumeros(this);" value="${veiculo.anoFab }">
					</c:if>			
				</td>
				<td>
					<font size="4" face="Times, serif" color="black">Ano Modelo:</font> <b><font face="Times, serif" color="#FF0000">*</font></b>
				</td>
				<td align="right">
					<c:if test="${veiculo.anoMod eq 0}">
						<input id="anoMod" name="anoMod" type="text" style="width:200px; height: 24px;" maxlength="4" onKeyUp="javascript:somenteNumeros(this);" value="">
					</c:if>
					<c:if test="${veiculo.anoMod > 0}">
						<input id="anoMod" name="anoMod" type="text" style="width:200px; height: 24px;" maxlength="4" onKeyUp="javascript:somenteNumeros(this);" value="${veiculo.anoMod }">
					</c:if>
					
				</td>	
			</tr>
			
			<tr>
				<td>
					<font size="4" face="Times, serif" color="black">Renavan:</font> <b><font face="Times, serif" color="#FF0000">*</font></b>
				</td>
				<td>
					<c:if test="${veiculo.renavan eq 0}">
						<input id="renavan" name="renavan" type="text" style="width:200px; height: 24px;" maxlength="9" onKeyUp="javascript:somenteNumeros(this);" value="">
					</c:if>
					<c:if test="${veiculo.renavan > 0}">
						<input id="renavan" name="renavan" type="text" style="width:200px; height: 24px;" maxlength="9" onKeyUp="javascript:somenteNumeros(this);" value="${veiculo.renavan }">
					</c:if>			
				</td>
				<td>
					<font size="4" face="Times, serif" color="black">Chassi:</font> <b><font face="Times, serif" color="#FF0000">*</font></b>
				</td>
				<td align="right">
					<input id="chassi" name="chassi" type="text" style="width:200px; height: 24px;" maxlength="17" onkeyup="converteMaiuscula(this);" value="${veiculo.chassi }">
				</td>
			</tr>
			
			<tr>
				<td>
					<font size="4" face="Times, serif" color="black">Km:</font> <b><font face="Times, serif" color="#FF0000">*</font></b>
				</td>
				<td>
					<c:if test="${veiculo.km eq 0}">
						<input id="km" name="km" type="text" style="width:200px; height: 24px;" maxlength="10" onKeyUp="javascript:somenteNumeros(this);" value="">
					</c:if>
					<c:if test="${veiculo.km > 0}">
						<input id="km" name="km" type="text" style="width:200px; height: 24px;" maxlength="10" onKeyUp="javascript:somenteNumeros(this);" value="${veiculo.km }">
					</c:if>			
				</td>
				<td>
					<font size="4" face="Times, serif" color="black">Combustivel:</font> <b><font face="Times, serif" color="#FF0000">*</font></b>
				</td>
				<td align="right">
					<select style="width:200px; height: 24px;" id="combustivel" name="combustivel">	
						<option value="Selecione">Selecione</option>
						<c:forEach items="${combustivelVeiculos}" var="combustivel">
							<c:if test="${veiculo.combustivel eq combustivel.descricao}">
								<option selected="selected"> ${combustivel.descricao } </option>
							</c:if>
							<c:if test="${veiculo.combustivel != combustivel.descricao}">
								<option> ${combustivel.descricao } </option>
							</c:if>				
						</c:forEach>
					 </select>					
				</td>		
			</tr>
			
			<tr>
				<td>
					<font size="4" face="Times, serif" color="black">Cor:</font> <b><font face="Times, serif" color="#FF0000">*</font></b>
				</td>
				<td>
					<input id="cor" name="cor" type="text" style="width:200px; height: 24px;" maxlength="149" onkeyup="somenteLetras(this); formataTexto(this);" value="${veiculo.cor }">
				</td>
				<td>
					<font size="4" face="Times, serif" color="black">Data Entrada:</font> <b><font face="Times, serif" color="#FF0000">*</font></b>
				</td>
				<td align="right">
					<input type="text" id="dataEntrada" name="dataEntrada" style="width:200px; height: 24px;" onblur="validaData(this)" onKeyUp="bloqueiaData(this);return mascara(this, '##/##/####', event);"
					        maxlength="10" value="<fmt:formatDate value="${veiculo.dataCadastro.time}" pattern="dd/MM/yyyy"/>" />
				</td>
			</tr>
			
			<tr>
				<td>
					<font size="4" face="Times, serif" color="black">Valor Compra:</font> <b><font face="Times, serif" color="#FF0000">*</font></b>
				</td>
				<td>
					<c:if test="${compraVeiculo.valorCompra eq 0}">
						<input id="valorCompra" name="valorCompra" type="text" style="width:200px; height: 24px;" maxlength="12" onKeyUp="moeda(this);" value="">
					</c:if>
					<c:if test="${compraVeiculo.valorCompra > 0}">
						 <fmt:setLocale value="pt-BR" />
						<input id="valorCompra" name="valorCompra" type="text" style="width:200px; height: 24px;" maxlength="12" onKeyUp="moeda(this);" value="<fmt:formatNumber value="${compraVeiculo.valorCompra}" type="currency"/>">				
					</c:if>			
				</td>
				<td>
					<font size="4" face="Times, serif" color="black">Consignado:</font> <b><font face="Times, serif" color="#FF0000">*</font></b>
				</td>
				<td>
					<!-- if para deixar sempre setado o radio -->
					<c:if test="${radioConsignado eq 1}">
						<input type="radio" id="nao" name="consignado" value="nao" checked="checked"> <font size="4" face="Times, serif" color="black">N&atilde;o</font>
						&nbsp; &nbsp;
						<input type="radio" id="sim" name="consignado" value="sim"> <font size="4" face="Times, serif" color="black">Sim</font>							
					</c:if>
					<c:if test="${radioConsignado eq 2}">
						<input type="radio" id="nao" name="consignado" value="nao" > <font size="4" face="Times, serif" color="black">N&atilde;o</font>
						&nbsp; &nbsp;
						<input type="radio" id="sim" name="consignado" value="sim" checked="checked"> <font size="4" face="Times, serif" color="black">Sim</font>							
					</c:if>	
				</td>
			</tr>
		</table>
	</td>
</tr>
	
<tr>
	<td>&nbsp; </td>
	<td>&nbsp;</td>
	<td>&nbsp; </td>
	<td>&nbsp; </td>
</tr>
	
<tr>
	<td colspan="4" align="center">
		<font size="4" face="Times, serif" color="black"><i>Opcionais</i></font>
	</td>		
</tr>

<tr>
	<td colspan="4">
		<table border="0" cellpadding="3" cellspacing="1" align="center">
			<tr>
				<td align="left">
					<c:if test="${veiculosOpcionais.opCompleto eq true}">
						<input type="checkbox" id="opCompleto" name="opCompleto" value="true" checked="checked"> <font size="4" face="Times, serif" color="black">Completo</font>
					</c:if>
					<c:if test="${veiculosOpcionais.opCompleto eq false}">
						<input type="checkbox" id="opCompleto" name="opCompleto" value="true"> <font size="4" face="Times, serif" color="black">Completo</font>
					</c:if>			
				</td>
				<td align="left">
					<c:if test="${veiculosOpcionais.opChaveReserva eq true}">
						<input type="checkbox" id="opChaveReserva" name="opChaveReserva" value="true" checked="checked"> <font size="4" face="Times, serif" color="black">Chave Reserva</font>
					</c:if>
					<c:if test="${veiculosOpcionais.opChaveReserva eq false}">
						<input type="checkbox" id="opChaveReserva" name="opChaveReserva" value="true"> <font size="4" face="Times, serif" color="black">Chave Reserva</font>
					</c:if>			
				</td>
				<td align="left">
					<c:if test="${veiculosOpcionais.opManual eq true}">
						<input type="checkbox" id="opManual" name="opManual" value="true" checked="checked"> <font size="4" face="Times, serif" color="black">Manual</font>
					</c:if>
					<c:if test="${veiculosOpcionais.opManual eq false}">
						<input type="checkbox" id="opManual" name="opManual" value="true"> <font size="4" face="Times, serif" color="black">Manual</font>
					</c:if>			
				</td>		
			</tr>
	
			<tr>
				<td align="left">
					<c:if test="${veiculosOpcionais.opArQuente eq true}">
						<input type="checkbox" id="opArQuente" name="opArQuente" value="true" checked="checked"> <font size="4" face="Times, serif" color="black">Ar Quente</font>
					</c:if>
					<c:if test="${veiculosOpcionais.opArQuente eq false}">
						<input type="checkbox" id="opArQuente" name="opArQuente" value="true"> <font size="4" face="Times, serif" color="black">Ar Quente</font>
					</c:if>
				</td>
				<td align="left">
					<c:if test="${veiculosOpcionais.opFarolXenon eq true}">
						<input type="checkbox" id="opFarolXenon" name="opFarolXenon" value="true" checked="checked"> <font size="4" face="Times, serif" color="black">Farol xenônio</font>
					</c:if>
					<c:if test="${veiculosOpcionais.opFarolXenon eq false}">
						<input type="checkbox" id="opFarolXenon" name="opFarolXenon" value="true"> <font size="4" face="Times, serif" color="black">Farol xenônio</font>
					</c:if>
				</td>
				<td align="left" colspan="2">
					<c:if test="${veiculosOpcionais.opBancoRegAltura eq true}">
						<input type="checkbox" id="opBancoRegAltura" name="opBancoRegAltura" value="true" checked="checked"> <font size="4" face="Times, serif" color="black">Banco Mot. com Ajuste de altura</font>
					</c:if>
					<c:if test="${veiculosOpcionais.opBancoRegAltura eq false}">
						<input type="checkbox" id="opBancoRegAltura" name="opBancoRegAltura" value="true"> <font size="4" face="Times, serif" color="black">Banco Mot. com Ajuste de altura</font>
					</c:if>			
				</td>
			</tr>
			
			<tr>
				<td align="left">
					<c:if test="${veiculosOpcionais.opGps eq true}">
						<input type="checkbox" id="opGps" name="opGps" value="true" checked="checked"> <font size="4" face="Times, serif" color="black">GPS</font>
					</c:if>
					<c:if test="${veiculosOpcionais.opGps eq false}">
						<input type="checkbox" id="opGps" name="opGps" value="true" > <font size="4" face="Times, serif" color="black">GPS</font>
					</c:if>			
				</td>
				<td align="left">
					<c:if test="${veiculosOpcionais.opLimpTraseiro eq true}">
						<input type="checkbox" id="opLimpTraseiro" name="opLimpTraseiro" value="true" checked="checked"> <font size="4" face="Times, serif" color="black">Limpador Traseiro</font>
					</c:if>
					<c:if test="${veiculosOpcionais.opLimpTraseiro eq false}">
						<input type="checkbox" id="opLimpTraseiro" name="opLimpTraseiro" value="true"> <font size="4" face="Times, serif" color="black">Limpador Traseiro</font>
					</c:if>			
				</td>
				<td align="left" colspan="2">
					<c:if test="${veiculosOpcionais.opTravaEletrica eq true}">
						<input type="checkbox" id="opTravaEletrica" name="opTravaEletrica" value="true" checked="checked"> <font size="4" face="Times, serif" color="black">Travas Elétricas</font>
					</c:if>
					<c:if test="${veiculosOpcionais.opTravaEletrica eq false}">
						<input type="checkbox" id="opTravaEletrica" name="opTravaEletrica" value="true"> <font size="4" face="Times, serif" color="black">Travas Elétricas</font>
					</c:if>			
				</td>
			</tr>
			
			<tr>
				<td align="left">
					<c:if test="${veiculosOpcionais.opRadio eq true}">
						<input type="checkbox" id="opRadio" name="opRadio" value="true" checked="checked"> <font size="4" face="Times, serif" color="black">Rádio</font>
					</c:if>
					<c:if test="${veiculosOpcionais.opRadio eq false}">
						<input type="checkbox" id="opRadio" name="opRadio" value="true" > <font size="4" face="Times, serif" color="black">Rádio</font>
					</c:if>			
				</td>
				<td align="left">
					<c:if test="${veiculosOpcionais.opCapotaMaritima eq true}">
						<input type="checkbox" id="opCapotaMaritima" name="opCapotaMaritima" value="true" checked="checked"> <font size="4" face="Times, serif" color="black">Capota Maritima</font>
					</c:if>
					<c:if test="${veiculosOpcionais.opCapotaMaritima eq false}">
						<input type="checkbox" id="opCapotaMaritima" name="opCapotaMaritima" value="true"> <font size="4" face="Times, serif" color="black">Capota Maritima</font>
					</c:if>			
				</td>
				<td align="left" colspan="2">
					<c:if test="${veiculosOpcionais.opRadioTocaFita eq true}">
						<input type="checkbox" id="opRadioTocaFita" name="opRadioTocaFita" value="true" checked="checked"> <font size="4" face="Times, serif" color="black">Rádio e Toca Fitas</font>
					</c:if>
					<c:if test="${veiculosOpcionais.opRadioTocaFita eq false}">
						<input type="checkbox" id="opRadioTocaFita" name="opRadioTocaFita" value="true"> <font size="4" face="Times, serif" color="black">Rádio e Toca Fitas</font>
					</c:if>
				</td>
			</tr>
			
			<tr>
				<td align="left">
					<c:if test="${veiculosOpcionais.opCdMp3 eq true}">
						<input type="checkbox" id="opCdMp3" name="opCdMp3" value="true" checked="checked"> <font size="4" face="Times, serif" color="black">CD Mp3</font>
					</c:if>
					<c:if test="${veiculosOpcionais.opCdMp3 eq false}">
						<input type="checkbox" id="opCdMp3" name="opCdMp3" value="true"> <font size="4" face="Times, serif" color="black">CD Mp3</font>
					</c:if>			
				</td>
				<td align="left">
					<c:if test="${veiculosOpcionais.opCompBordo eq true}">
						<input type="checkbox" id="opCompBordo" name="opCompBordo" value="true" checked="checked"> <font size="4" face="Times, serif" color="black">Comp. Bordo</font>
					</c:if>
					<c:if test="${veiculosOpcionais.opCompBordo eq false}">
						<input type="checkbox" id="opCompBordo" name="opCompBordo" value="true"> <font size="4" face="Times, serif" color="black">Comp. Bordo</font>
					</c:if>			
				</td>
				<td align="left" colspan="2">
					<c:if test="${veiculosOpcionais.opRetroFotocromico eq true}">
						<input type="checkbox" id="opRetroFotocromico" name="opRetroFotocromico" value="true" checked="checked"> <font size="4" face="Times, serif" color="black">Retrovisor Fotocromico</font>
					</c:if>
					<c:if test="${veiculosOpcionais.opRetroFotocromico eq false}">
						<input type="checkbox" id="opRetroFotocromico" name="opRetroFotocromico" value="true"> <font size="4" face="Times, serif" color="black">Retrovisor Fotocromico</font>
					</c:if>			
				</td>
			</tr>
			
			<tr>
				<td align="left">
					<c:if test="${veiculosOpcionais.opRetrovEletrico eq true}">
						<input type="checkbox" id="opRetrovEletrico" name="opRetrovEletrico" value="true" checked="checked"> <font size="4" face="Times, serif" color="black">Retrovisor Elétrico</font>
					</c:if>
					<c:if test="${veiculosOpcionais.opRetrovEletrico eq false}">
						<input type="checkbox" id="opRetrovEletrico" name="opRetrovEletrico" value="true"> <font size="4" face="Times, serif" color="black">Retrovisor Elétrico</font>
					</c:if>
				</td>
				<td align="left">
					<c:if test="${veiculosOpcionais.opRodLigaLeve eq true}">
						<input type="checkbox" id="opRodLigaLeve" name="opRodLigaLeve" value="true" checked="checked"> <font size="4" face="Times, serif" color="black">Roda Liga Leve</font>
					</c:if>
					<c:if test="${veiculosOpcionais.opRodLigaLeve eq false}">
						<input type="checkbox" id="opRodLigaLeve" name="opRodLigaLeve" value="true"> <font size="4" face="Times, serif" color="black">Roda Liga Leve</font>
					</c:if>			
				</td>
				<td align="left" colspan="2">
					<c:if test="${veiculosOpcionais.opSensorEstacionamento eq true}">
						<input type="checkbox" id="opSensorEstacionamento" name="opSensorEstacionamento" value="true" checked="checked"> <font size="4" face="Times, serif" color="black">Sensor Estacionamento</font>
					</c:if>
					<c:if test="${veiculosOpcionais.opSensorEstacionamento eq false}">
						<input type="checkbox" id="opSensorEstacionamento" name="opSensorEstacionamento" value="true"> <font size="4" face="Times, serif" color="black">Sensor Estacionamento</font>
					</c:if>			
				</td>
			</tr>
			
			<tr>
				<td align="left">
					<c:if test="${veiculosOpcionais.opDVD eq true}">
						<input type="checkbox" id="opDVD" name="opDVD" value="true" checked="checked"> <font size="4" face="Times, serif" color="black">DVD</font>
					</c:if>
					<c:if test="${veiculosOpcionais.opDVD eq false}">
						<input type="checkbox" id="opDVD" name="opDVD" value="true"> <font size="4" face="Times, serif" color="black">DVD</font>
					</c:if>
				</td>
				<td align="left">
					<c:if test="${veiculosOpcionais.opTetoSolar eq true}">
						<input type="checkbox" id="opTetoSolar" name="opTetoSolar" value="true" checked="checked"> <font size="4" face="Times, serif" color="black">Teto Solar</font>
					</c:if>
					<c:if test="${veiculosOpcionais.opTetoSolar eq false}">
						<input type="checkbox" id="opTetoSolar" name="opTetoSolar" value="true"> <font size="4" face="Times, serif" color="black">Teto Solar</font>
					</c:if>
				</td>
				<td align="left" colspan="2">
					<c:if test="${veiculosOpcionais.opDesembTraseito eq true}">
						<input type="checkbox" id="opDesembTraseito" name="opDesembTraseito" value="true" checked="checked"> <font size="4" face="Times, serif" color="black">Desemb. Traseiro</font>
					</c:if>
					<c:if test="${veiculosOpcionais.opDesembTraseito eq false}">
						<input type="checkbox" id="opDesembTraseito" name="opDesembTraseito" value="true"> <font size="4" face="Times, serif" color="black">Desemb. Traseiro</font>
					</c:if>			
				</td>
			</tr>
			
			<tr>
				<td align="left">
					<c:if test="${veiculosOpcionais.opTracao4x4 eq true}">
						<input type="checkbox" id="opTracao4x4" name="opTracao4x4" value="true" checked="checked"> <font size="4" face="Times, serif" color="black">Tração 4X4</font>
					</c:if>
					<c:if test="${veiculosOpcionais.opTracao4x4 eq false}">
						<input type="checkbox" id="opTracao4x4" name="opTracao4x4" value="true"> <font size="4" face="Times, serif" color="black">Tração 4X4</font>
					</c:if>			 
				</td>
				<td align="left">
					<c:if test="${veiculosOpcionais.opAirBag eq true}">
						<input type="checkbox" id="opAirBag" name="opAirBag" value="true" checked="checked"> <font size="4" face="Times, serif" color="black">Air Bag</font>
					</c:if>
					<c:if test="${veiculosOpcionais.opAirBag eq false}">
						<input type="checkbox" id="opAirBag" name="opAirBag" value="true"> <font size="4" face="Times, serif" color="black">Air Bag</font>
					</c:if>			
				</td>
				<td align="left" colspan="2">
					<c:if test="${veiculosOpcionais.opVolRegAltura eq true}">
						<input type="checkbox" id="opVolRegAltura" name="opVolRegAltura" value="true" checked="checked"> <font size="4" face="Times, serif" color="black">Volante Regulagem Altura</font>
					</c:if>
					<c:if test="${veiculosOpcionais.opVolRegAltura eq false}">
						<input type="checkbox" id="opVolRegAltura" name="opVolRegAltura" value="true"> <font size="4" face="Times, serif" color="black">Volante Regulagem Altura</font>
					</c:if>						
				</td>
			</tr>
			
			<tr>
				<td align="left">
					<c:if test="${veiculosOpcionais.opABS eq true}">
						<input type="checkbox" id="opABS" name="opABS" value="true" checked="checked"> <font size="4" face="Times, serif" color="black">Freio ABS</font>
					</c:if>
					<c:if test="${veiculosOpcionais.opABS eq false}">
						<input type="checkbox" id="opABS" name="opABS" value="true"> <font size="4" face="Times, serif" color="black">Freio ABS</font>
					</c:if>			 
				</td>
				<td align="left">
					<c:if test="${veiculosOpcionais.opAlarme eq true}">
						<input type="checkbox" id="opAlarme" name="opAlarme" value="true" checked="checked"> <font size="4" face="Times, serif" color="black">Alarme</font>
					</c:if>
					<c:if test="${veiculosOpcionais.opAlarme eq false}">
						<input type="checkbox" id="opAlarme" name="opAlarme" value="true"> <font size="4" face="Times, serif" color="black">Alarme</font>
					</c:if>
				</td>
				<td align="left" colspan="2">
					<c:if test="${veiculosOpcionais.opDirecaoHidraulica eq true}">
						<input type="checkbox" id="opDirecaoHidraulica" name="opDirecaoHidraulica" value="true" checked="checked"> <font size="4" face="Times, serif" color="black">Direção Hidráulica</font>
					</c:if>
					<c:if test="${veiculosOpcionais.opDirecaoHidraulica eq false}">
						<input type="checkbox" id="opDirecaoHidraulica" name="opDirecaoHidraulica" value="true"> <font size="4" face="Times, serif" color="black">Direção Hidráulica</font>
					</c:if>			
				</td>
			</tr>
			
			<tr>
				<td align="left">
					<c:if test="${veiculosOpcionais.opBancoCouro eq true}">
						<input type="checkbox" id="opBancoCouro" name="opBancoCouro" value="true" checked="checked"> <font size="4" face="Times, serif" color="black">Banco de Couro</font>
					</c:if>
					<c:if test="${veiculosOpcionais.opBancoCouro eq false}">
						<input type="checkbox" id="opBancoCouro" name="opBancoCouro" value="true"> <font size="4" face="Times, serif" color="black">Banco de Couro</font>
					</c:if>			 
				</td>
				<td align="left">
					<c:if test="${veiculosOpcionais.opArCondicionado eq true}">
						<input type="checkbox" id="opArCondicionado" name="opArCondicionado" value="true" checked="checked"> <font size="4" face="Times, serif" color="black">Ar Condicionado</font>
					</c:if>
					<c:if test="${veiculosOpcionais.opArCondicionado eq false}">
						<input type="checkbox" id="opArCondicionado" name="opArCondicionado" value="true"> <font size="4" face="Times, serif" color="black">Ar Condicionado</font>
					</c:if>
				</td>
				<td align="left" colspan="2">
					<c:if test="${veiculosOpcionais.opVidroEletrico eq true}">
						<input type="checkbox" id="opVidroEletrico" name="opVidroEletrico" value="true" checked="checked"> <font size="4" face="Times, serif" color="black">Vidros Elétricos</font>
					</c:if>
					<c:if test="${veiculosOpcionais.opVidroEletrico eq false}">
						<input type="checkbox" id="opVidroEletrico" name="opVidroEletrico" value="true"> <font size="4" face="Times, serif" color="black">Vidros Elétricos</font>
					</c:if>			
				</td>
			</tr>
		</table>
	</td>
</tr>
		
<tr>
	<td>&nbsp; </td>
	<td>&nbsp;</td>
	<td>&nbsp; </td>
	<td>&nbsp; </td>
</tr>	
	
<tr>
	<td colspan="4" align="center">
		<font size="4" face="Times, serif" color="black"><i>Informa&ccedil;&otilde;es Documento</i></font>
	</td>		
</tr>

<tr>
	<td colspan="4">
		<table border="0" cellpadding="3" cellspacing="1" align="left">
			<tr>
				<td>
					<font size="4" face="Times, serif" color="black">Nome:</font> <b><font face="Times, serif" color="#FF0000">*</font></b>
				</td>
				<td colspan="3">
					<input id="infDocNome" name="infDocNome" type="text" style="width:540px; height: 24px;" maxlength="199" onkeyup="somenteLetras(this); formataTexto(this);" value="${veiculo.infDocNome }">
				</td>		
			</tr>
				
			<tr>
				<td>
					<font size="4" face="Times, serif" color="black">Endere&ccedil;o:</font> <b><font face="Times, serif" color="#FF0000">*</font></b>
				</td>
				<td colspan="3">
					<input id="infDocEndereco" name="infDocEndereco" type="text" style="width:540px; height: 24px;" maxlength="199" onkeyup="formataTexto(this);" value="${veiculo.infDocEndereco }">
				</td>		
			</tr>
		</table>
	</td>
</tr>

<tr>
	<td>&nbsp; </td>
	<td>&nbsp;</td>
	<td>&nbsp; </td>
	<td>&nbsp; </td>
</tr>

<tr>
	<td colspan="4" align="right">	
		<a href="#" onclick="direcionaTelaPesquisa();">
			<img src="/safetycar/resources/imagens/pesquisa01.png" width="20" height="20" border="0" title="Pesquisar Fornecedor" alt="Pesquisar Fornecedor"
						 onMouseOver="this.src='/safetycar/resources/imagens/pesquisa02.png'"  
	                     onMouseOut="this.src='/safetycar/resources/imagens/pesquisa01.png'">	        
		</a>		
	</td>
</tr>

<c:if test="${exibeCliFor eq true}">
	<tr id="dadosFornecedor">
		<td colspan="4">
			<input type="hidden" id="id" name="id" value="${cliFor.id }">
		
			<table border="0" cellpadding="3" cellspacing="1" align="center">
				<tr>
					<td>
						<font size="4" face="Times, serif" color="black">Nome Completo:</font>
					</td>
					<td colspan="3">
						<font size="4" face="Times, serif" color="black"><i> ${cliFor.nomeCompleto } </i></font>
					</td>		
				</tr>
				
				<tr>
					<td>
						<font size="4" face="Times, serif" color="black">RG:</font>
					</td>
					<td>
						<font size="4" face="Times, serif" color="black"><i> ${cliFor.rg } </i></font>
					</td>
					<td>
						<font size="4" face="Times, serif" color="black">CPF:</font> 
					</td>
					<td>
						<font size="4" face="Times, serif" color="black"><i> ${cliFor.cpf } </i></font>
					</td>
				</tr>
				
				<tr>
					<td>
						<font size="4" face="Times, serif" color="black">Endere&ccedil;o:</font> 
					</td>
					<td colspan="3">
						<font size="4" face="Times, serif" color="black"><i> ${cliFor.endereco } </i></font>
					</td>		
				</tr>
				
				<tr>
					<td>
						<font size="4" face="Times, serif" color="black">Bairro:</font> 
					</td>
					<td colspan="3">
						<font size="4" face="Times, serif" color="black"><i> ${cliFor.bairro } </i></font>
					</td>	
				</tr>
				
				<tr>
					<td>
						<font size="4" face="Times, serif" color="black">Cidade:</font> 
					</td>
					<td colspan="3">
						<font size="4" face="Times, serif" color="black"><i> ${cliFor.cidade } </i></font>
					</td>	
				</tr>
				
				<tr>
					<td>
						<font size="4" face="Times, serif" color="black">Estado:</font> 
					</td>
					<td>
						<font size="4" face="Times, serif" color="black"><i> ${cliFor.estado } </i></font>
					</td>
					<td>
						<font size="4" face="Times, serif" color="black">Cep:</font> 
					</td>
					<td>
						<font size="4" face="Times, serif" color="black"><i> ${cliFor.cep } </i></font>
					</td>
				</tr>
				
				<tr>
					<td>
						<font size="4" face="Times, serif" color="black">Telefone (res):</font> 
					</td>
					<td>
						<font size="4" face="Times, serif" color="black"><i> ${cliFor.telefone } </i></font>
					</td>
					<td>
						<font size="4" face="Times, serif" color="black">Celular:</font> 
					</td>
					<td>
						<font size="4" face="Times, serif" color="black"><i> ${cliFor.celular } </i></font>
					</td>	
				</tr>
				
				<tr>
					<td>
						<font size="4" face="Times, serif" color="black">R&aacute;dio:</font>
					</td>
					<td>
						<font size="4" face="Times, serif" color="black"><i> ${cliFor.radio } </i></font>
					</td>
					<td>
						<font size="4" face="Times, serif" color="black">Status:</font> 
					</td>
					<td>
						<font size="4" face="Times, serif" color="black"><i> ${cliFor.status} </i></font>								
					</td>
				</tr>
				
				<tr>
					<td>
						<font size="4" face="Times, serif" color="black">E-mail:</font>
					</td>
					<td colspan="3">
						<font size="4" face="Times, serif" color="black"><i> ${cliFor.email } </i></font>
					</td>	
				</tr>
				
				<tr>
					<td>
						<font size="4" face="Times, serif" color="black">Data Cadastro:</font> 
					</td>
					<td>
			 			<font size="4" face="Times, serif" color="black"><i> <fmt:formatDate value="${cliFor.dataCadastro.time }" pattern="dd/MM/yyyy" /> </i></font>
					</td>	
				</tr>
			</table>
		</td>
	</tr>
	
	<tr id="btnSalvar">
		<td colspan="4" align="right">
			<a href="#dialog1" name="modal" style="text-decoration:none" onclick="$('html, body').animate({ scrollTop: 0 }, 'slow'); adicionar();" >
				<img src="/safetycar/resources/imagens/salvar01.png" width="20" height="20" border="0" title="Salvar" alt="Salvar"
							 onMouseOver="this.src='/safetycar/resources/imagens/salvar02.png'"  
	                         onMouseOut="this.src='/safetycar/resources/imagens/salvar01.png'">
			</a>
		</td>
	</tr>
	
</c:if>	

</table>
			
		</td>		
	</tr>
</table>

</form>

<div id="boxes">
  
<!-- Janela Modal -->  
<div id="dialog1" class="window">
	<div class="RoundedCorner" style="width:460px;">
		<b class="b1"></b>
		<b class="b2"></b>
		<b class="b3"></b>
		<b class="b4"></b>
		<div class="RoundedCornerContent" >
			
			<a href="#" class="close" onclick="$('html, body').animate({ scrollTop: 0 }, 'slow');">
				<img src="/safetycar/resources/imagens/fechar01.png" width="20" height="20" border="0" title="Fechar" alt="Fechar"
							 onMouseOver="this.src='/safetycar/resources/imagens/fechar02.png'"  
	                         onMouseOut="this.src='/safetycar/resources/imagens/fechar01.png'">
			</a>
			&nbsp;
			
			<div id="divShow"></div>
			
			<table align="center">
				<tr>
					<td>
						<a href="#" class="close" onclick="$('html, body').animate({ scrollTop: 0 }, 'slow');">
							<img src="/safetycar/resources/imagens/aceitarSim.png" width="30" height="30" border="0" title="OK" alt="OK">
						</a>					
					</td>
				</tr>			
			</table>			
		
		</div>
		<b class="b4"></b>
		<b class="b3"></b>
		<b class="b2"></b>
		<b class="b1"></b>
	</div>
</div>
<!-- Fim Janela Modal -->

<!-- Máscara para cobrir a tela -->
  <div id="mask"></div>

</div>

<br />
<br />

<!-- Layout -->		
<c:import url="../layout/rodape.jsp" />