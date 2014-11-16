<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


 <!-- Layout -->
<c:import url="../layout/cabecalho.jsp" />

<!-- Scripts Padrões -->
<script type="text/javascript" src="/safetycar/resources/javaScript/scriptPadrao.js"></script>

<!-- Scripts de  paginação e liberação de tags -->
<%@taglib tagdir="/WEB-INF/tags" prefix="t" %>
<script type="text/javascript" src="/safetycar/resources/javaScript/formulario.js"></script>
<script type="text/javascript" src="/safetycar/resources/javaScript/util.js"></script>


<div align="center">
	<font size="5" face="Times, serif" color="black"><b><i>Pesquisa Fornecedor</i></b></font>	
</div>

<br/>
		
<form id="pesquisaFornecedor" name="pesquisaFornecedor" method="get">	

<!-- Hiddens de  paginação -->
<input type="hidden" id="relatorio" name="relatorio" value="0">
<input type="hidden" id="metodo" name="metodo" value="paginar" />
<input type="hidden" id="pagina" name="pagina" value="" />
<input type="hidden" id="tabela" name="tabela" value="" />

<!-- Hiddens para manter dados -->
<input type="hidden" id="placa" name="placa" value="${veiculo.placa}" />
<input type="hidden" id="cidade" name="cidade" value="${veiculo.cidade}" />
<input type="hidden" id="estado" name="estado" value="${veiculo.estado}" />
<input type="hidden" id="marca" name="marca" value="${veiculo.marca}" />
<input type="hidden" id="modelo" name="modelo" value="${veiculo.modelo}" />
<input type="hidden" id="versao" name="versao" value="${veiculo.versao}" />
<input type="hidden" id="anoFab" name="anoFab" value="${veiculo.anoFab}" />
<input type="hidden" id="anoMod" name="anoMod" value="${veiculo.anoMod}" />
<input type="hidden" id="renavan" name="renavan" value="${veiculo.renavan}" />
<input type="hidden" id="chassi" name="chassi" value="${veiculo.chassi}" />
<input type="hidden" id="km" name="km" value="${veiculo.km}" />
<input type="hidden" id="combustivel" name="combustivel" value="${veiculo.combustivel}" />
<input type="hidden" id="cor" name="cor" value="${veiculo.cor}" />
<input type="hidden" id="dataEntrada" name="dataEntrada" value="<fmt:formatDate value="${veiculo.dataCadastro.time}" pattern="dd/MM/yyyy"/>" />
<input type="hidden" id="valorCompra" name="valorCompra" value="${compraVeiculo.valorCompra}" />
<input type="hidden" id="consignado" name="consignado" value="${compraVeiculo.consignado}" />
<input type="hidden" id="infDocNome" name="infDocNome" value="${veiculo.infDocNome}" />
<input type="hidden" id="infDocEndereco" name="infDocEndereco" value="${veiculo.infDocEndereco}" />
<input type="hidden" id="opCompleto" name="opCompleto" value="${veiculosOpcionais.opCompleto}" />
<input type="hidden" id="opArQuente" name="opArQuente" value="${veiculosOpcionais.opArQuente}" />
<input type="hidden" id="opFarolXenon" name="opFarolXenon" value="${veiculosOpcionais.opFarolXenon}" />
<input type="hidden" id="opBancoRegAltura" name="opBancoRegAltura" value="${veiculosOpcionais.opBancoRegAltura}" />
<input type="hidden" id="opGps" name="opGps" value="${veiculosOpcionais.opGps}" />
<input type="hidden" id="opLimpTraseiro" name="opLimpTraseiro" value="${veiculosOpcionais.opLimpTraseiro}" />
<input type="hidden" id="opTravaEletrica" name="opTravaEletrica" value="${veiculosOpcionais.opTravaEletrica}" />
<input type="hidden" id="opRadio" name="opRadio" value="${veiculosOpcionais.opRadio}" />
<input type="hidden" id="opCapotaMaritima" name="opCapotaMaritima" value="${veiculosOpcionais.opCapotaMaritima}" />
<input type="hidden" id="opRadioTocaFita" name="opRadioTocaFita" value="${veiculosOpcionais.opRadioTocaFita}" />
<input type="hidden" id="opCdMp3" name="opCdMp3" value="${veiculosOpcionais.opCdMp3}" />
<input type="hidden" id="opCompBordo" name="opCompBordo" value="${veiculosOpcionais.opCompBordo}" />
<input type="hidden" id="opRetroFotocromico" name="opRetroFotocromico" value="${veiculosOpcionais.opRetroFotocromico}" />
<input type="hidden" id="opRetrovEletrico" name="opRetrovEletrico" value="${veiculosOpcionais.opRetrovEletrico}" />
<input type="hidden" id="opRodLigaLeve" name="opRodLigaLeve" value="${veiculosOpcionais.opRodLigaLeve}" />
<input type="hidden" id="opSensorEstacionamento" name="opSensorEstacionamento" value="${veiculosOpcionais.opSensorEstacionamento}" />
<input type="hidden" id="opDVD" name="opDVD" value="${veiculosOpcionais.opDVD}" />
<input type="hidden" id="opTetoSolar" name="opTetoSolar" value="${veiculosOpcionais.opTetoSolar}" />
<input type="hidden" id="opDesembTraseito" name="opDesembTraseito" value="${veiculosOpcionais.opDesembTraseito}" />
<input type="hidden" id="opTracao4x4" name="opTracao4x4" value="${veiculosOpcionais.opTracao4x4}" />
<input type="hidden" id="opAirBag" name="opAirBag" value="${veiculosOpcionais.opAirBag}" />
<input type="hidden" id="opVolRegAltura" name="opVolRegAltura" value="${veiculosOpcionais.opVolRegAltura}" />
<input type="hidden" id="opABS" name="opABS" value="${veiculosOpcionais.opABS}" />
<input type="hidden" id="opAlarme" name="opAlarme" value="${veiculosOpcionais.opAlarme}" />
<input type="hidden" id="opDirecaoHidraulica" name="opDirecaoHidraulica" value="${veiculosOpcionais.opDirecaoHidraulica}" />
<input type="hidden" id="opBancoCouro" name="opBancoCouro" value="${veiculosOpcionais.opBancoCouro}" />
<input type="hidden" id="opArCondicionado" name="opArCondicionado" value="${veiculosOpcionais.opArCondicionado}" />
<input type="hidden" id="opVidroEletrico" name="opVidroEletrico" value="${veiculosOpcionais.opVidroEletrico}" />
<input type="hidden" id="opChaveReserva" name="opChaveReserva" value="${veiculosOpcionais.opChaveReserva}" />
<input type="hidden" id="opManual" name="opManual" value="${veiculosOpcionais.opManual}" />


<div align="center">	
<table border="0" cellpadding="0" cellspacing="0" align="center">
<tr>
	<td colspan="4">
		<table border="0" cellpadding="3" cellspacing="1" align="left">
				<tr>
					<td>
						<font size="4" face="Times, serif" color="black">Nome:</font>
					</td>
					<td colspan="3">
						<input id="pesqNome" name="pesqNome" type="text" maxlength="150" style="width:540px; height: 24px;" onKeyUp="javascript:somenteLetras(this); formataTexto(this);" value="${pesqNome }">
					</td>		
				</tr>
				
				<tr>
					<td>
						<font size="4" face="Times, serif" color="black">CPF:</font>
					</td>
					<td>
						<input id="pesqCpf" name="pesqCpf" type="text" style="width:200px; height: 24px;" maxlength="14" onKeyUp="javascript:bloqueiaCpf(this);return mascara(this, '###.###.###-##', event);" value="${pesqCpf }">
					</td>
					<td align="right">
						<font size="4" face="Times, serif" color="black">RG:</font>
					</td>
					<td>
						<input id="pesqRg" name="pesqRg" type="text" style="width:200px; height: 24px;" maxlength="10" onkeyup="somenteNumerosLetras(this); converteMaiuscula(this);" value="${pesqRg }">
					</td>				
				</tr>
		</table>
	</td>
</tr>
	
<tr>
	<td colspan="4" align="right">
		<a href="#" onClick="document.pesquisaFornecedor.submit();">
			<img src="/safetycar/resources/imagens/pesquisa01.png" width="20" height="20" border="0" title="Pesquisar" alt="Pesquisar"
						 onMouseOver="this.src='/safetycar/resources/imagens/pesquisa02.png'"  
	                     onMouseOut="this.src='/safetycar/resources/imagens/pesquisa01.png'">
		</a>
	</td>
</tr>
</table>
</div>


<table align="center">
<tr>
	<td colspan="4">
		<t:paginacao id="tabela_AltCF" lista="${itensRelatorio}" semPaginacao="false">
			<jsp:attribute name="cabecalho">
				<th><b>Nome Completo</b></th>
				<th><b>RG</b></th>
				<th><b>CPF</b></th>
				<th><b>Data Cadastro</b></th>
				<th><b>Tipo</b></th>
				<th>&nbsp; &nbsp;</th>											
			</jsp:attribute>
			<jsp:attribute name="linha">	
				<td>
					${item.nomeCompleto}
				</td>
				
				<td align="center">
					${item.rg}
				</td>
				
				<td align="center">
					${item.cpf}
				</td>
				
				<td align="center">
					<fmt:formatDate value="${item.dataCadastro.time}" pattern="dd/MM/yyyy"/>
				</td>
				
				<td align="center">
					${item.tipoCliFor}
				</td>
				
				<td align="center">															 
					<a href="fornecedorSelecionado?id=${item.id}&placa=${veiculo.placa}&cidade=${veiculo.cidade}&estado=${veiculo.estado}&marca=${veiculo.marca}&modelo=${veiculo.modelo}&versao=${veiculo.versao}&anoFab=${veiculo.anoFab}&anoMod=${veiculo.anoMod}&renavan=${veiculo.renavan}&chassi=${veiculo.chassi}&km=${veiculo.km}&combustivel=${veiculo.combustivel}&cor=${veiculo.cor}&dataEntrada=<fmt:formatDate value="${veiculo.dataCadastro.time}" pattern="dd/MM/yyyy"/>&valorCompra=${compraVeiculo.valorCompra}&consignado=${compraVeiculo.consignado}&opCompleto=${veiculosOpcionais.opCompleto}&opArQuente=${veiculosOpcionais.opArQuente}&opFarolXenon=${veiculosOpcionais.opFarolXenon}&opBancoRegAltura=${veiculosOpcionais.opBancoRegAltura}&opGps=${veiculosOpcionais.opGps}&opLimpTraseiro=${veiculosOpcionais.opLimpTraseiro}&opTravaEletrica=${veiculosOpcionais.opTravaEletrica}&opRadio=${veiculosOpcionais.opRadio}&opCapotaMaritima=${veiculosOpcionais.opCapotaMaritima}&opRadioTocaFita=${veiculosOpcionais.opRadioTocaFita}&opCdMp3=${veiculosOpcionais.opCdMp3}&opCompBordo=${veiculosOpcionais.opCompBordo}&opRetroFotocromico=${veiculosOpcionais.opRetroFotocromico}&opRetrovEletrico=${veiculosOpcionais.opRetrovEletrico}&opRodLigaLeve=${veiculosOpcionais.opRodLigaLeve}&opSensorEstacionamento=${veiculosOpcionais.opSensorEstacionamento}&opDVD=${veiculosOpcionais.opDVD}&opTetoSolar=${veiculosOpcionais.opTetoSolar}&opDesembTraseito=${veiculosOpcionais.opDesembTraseito}&opTracao4x4=${veiculosOpcionais.opTracao4x4}&opAirBag=${veiculosOpcionais.opAirBag}&opVolRegAltura=${veiculosOpcionais.opVolRegAltura}&opABS=${veiculosOpcionais.opABS}&opAlarme=${veiculosOpcionais.opAlarme}&opDirecaoHidraulica=${veiculosOpcionais.opDirecaoHidraulica}&opBancoCouro=${veiculosOpcionais.opBancoCouro}&opArCondicionado=${veiculosOpcionais.opArCondicionado}&opVidroEletrico=${veiculosOpcionais.opVidroEletrico}&opChaveReserva=${veiculosOpcionais.opChaveReserva}&opManual=${veiculosOpcionais.opManual}&infDocNome=${veiculo.infDocNome}&infDocEndereco=${veiculo.infDocEndereco}">
						<img src="/safetycar/resources/imagens/selecionar01.png" width="20" height="20" border="0" title="Selecionar" alt="Selecionar"
							 onMouseOver="this.src='/safetycar/resources/imagens/selecionar02.png'"  
		                        onMouseOut="this.src='/safetycar/resources/imagens/selecionar01.png'">
					</a>
				</td>										
			</jsp:attribute>
			<jsp:attribute name="link">
				javascript: paginar('pesquisaFornecedor', '${tabela}', ${pagina});
			</jsp:attribute>
			</t:paginacao>
	</td>
</tr>
</table>

</form>

<!-- Layout -->		
<c:import url="../layout/rodape.jsp" />