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

<script type='text/javascript' src='/safetycar/dwr/engine.js'></script>
<script type='text/javascript' src='/safetycar/dwr/interface/relatorioVeiculosDisponiveisAjaxDwr.js'></script>
<script type='text/javascript' src='/safetycar/dwr/util.js'></script>

<link rel="stylesheet" href="/safetycar/resources/jquery/css/jquery-ui-1.8.20.custom.css" />

<script type="text/javascript" src="/safetycar/resources/jquery/js/jquery.mim.js"></script>
<script type="text/javascript" src="/safetycar/resources/jquery/js/jquery.ui.datepicker.js"></script>
<script type="text/javascript" src="/safetycar/resources/jquery/js/jquery.ui.core.js"></script>
<script type="text/javascript" src="/safetycar/resources/jquery/js/jquery.ui.datepicker-pt-BR.js"></script>

<script>
	$(function() {
		$( "#pesqPeriodoDe" ).datepicker();
	});

	$(function() {
		$( "#pesqPeriodoAte" ).datepicker();
	});
</script>

<div align="center">
	<font size="5" face="Times, serif" color="black"><b><i>Relat&oacute;rio Lucro Veiculo</i></b></font>	
</div>

<br/>

<div align="center">
	<font size="4" face="Times, serif" color="black"><i>Pesquisa</i></font>
</div>

<form id="relLucro" name="relLucro" method="post">

<!-- Hiddens de  paginação -->
<input type="hidden" id="relatorio" name="relatorio" value="0">
<input type="hidden" id="metodo" name="metodo" value="paginar" />
<input type="hidden" id="pagina" name="pagina" value="" />
<input type="hidden" id="tabela" name="tabela" value="" />


<input type="hidden" id="primExec" name="primExec" value="${primExec }" />


<table align="center">
<tr>
	<td colspan="4">
		<table border="0" cellpadding="3" cellspacing="1" align="left">
				<c:if test="${exibeErroDataInicio eq true}">
					<tr>
						<td colspan="4">
							<blink><font size="3" face="Times, serif" color="red"><i><b> ERRO!  Digite o periodo de inicio</b></i></font></blink>
						</td>
					</tr>
				</c:if>
				
				<tr>
					<td>
						<font size="4" face="Times, serif" color="black">Periodo De: <font face="Times, serif" color="#FF0000"><b>*</b></font></font>
					</td>
					<td>
						<input id="pesqPeriodoDe" name="pesqPeriodoDe" type="text" style="width:200px; height: 24px;" maxlength="10" onblur="validaData(this)" onKeyUp="bloqueiaData(this);return mascara(this, '##/##/####', event);"
							value="${pesqPeriodoDe }">
					</td>
					<td>
						<font size="4" face="Times, serif" color="black">Ate:</font>
					</td>
					<td>
						<input id="pesqPeriodoAte" name="pesqPeriodoAte" type="text" style="width:200px; height: 24px;" maxlength="10" onblur="validaData(this)" onKeyUp="bloqueiaData(this);return mascara(this, '##/##/####', event);"
							value="${pesqPeriodoAte }">
					</td>
				</tr>
				
				<tr>
					<td>
						<font size="4" face="Times, serif" color="black">Vendedor:</font>
					</td>
					<td>						
						<select style="width:200px; height: 24px;" id="pesqVendedor" name="pesqVendedor">	
						<option value="Selecione">Selecione</option>
						<c:forEach items="${vendedores}" var="vendedores">
							<c:if test="${pesqVendedor eq vendedores}">
								<option selected="selected"> ${vendedores } </option>
							</c:if>
							<c:if test="${pesqVendedor != vendedores}">
								<option> ${vendedores } </option>
							</c:if>				
						</c:forEach>
					 </select>
					</td>
					<td>
						<font size="4" face="Times, serif" color="black">Consignado:</font>
					</td>
					<td>						
						<select style="width:200px; height: 24px;" id="pesqConsignado" name="pesqConsignado">							
							<c:if test="${(pesqConsignado eq 'Selecione') || (empty pesqConsignado)}">
								<option value="Selecione" selected="selected">Selecione</option>
					 			<option value="true">Sim</option>
					 			<option value="false">N&atilde;o</option>
					 		</c:if>														
							<c:if test="${pesqConsignado eq 'true'}">
								<option value="Selecione">Selecione</option>
					 			<option value="true" selected="selected">Sim</option>
					 			<option value="false">N&atilde;o</option>
					 		</c:if>
					 		<c:if test="${pesqConsignado eq 'false'}">
					 			<option value="Selecione">Selecione</option>
					 			<option value="true">Sim</option>
					 			<option value="false" selected="selected">Não</option>
					 		</c:if>					 						 							
					 </select>
					</td>									
				</tr>
		</table>
	</td>
</tr>

<tr>
	<td colspan="4" align="right">
		<a href="#" onClick="document.relLucro.submit();">
			<img src="/safetycar/resources/imagens/pesquisa01.png" width="20" height="20" border="0" title="Pesquisar" alt="Pesquisar"
						 onMouseOver="this.src='/safetycar/resources/imagens/pesquisa02.png'"  
	                     onMouseOut="this.src='/safetycar/resources/imagens/pesquisa01.png'">
		</a>		
	</td>
</tr>
</table>

<c:if test="${exibeRel eq true}">

<div align="center">
<div style="width: 900px" >	
<table style="width: 905px" border="0" align="center">
	<tr>
		<td align="right">
			<a href="#" onClick="abrirNovaJanela('relLucroVeiculoImpressao?pesqPeriodoDe=${pesqPeriodoDe}&pesqPeriodoAte=${pesqPeriodoAte}&pesqVendedor=${pesqVendedor}&pesqConsignado=${pesqConsignado}','_blank', 'height=760, width=980, top=40, left=25, scrollbars=yes, resizable=no');">
				<img src="/safetycar/resources/imagens/imprimir01.png" width="20" height="20" border="0" title="Imprimir" alt="Imprimir"
							 onMouseOver="this.src='/safetycar/resources/imagens/imprimir02.png'"  
		                     onMouseOut="this.src='/safetycar/resources/imagens/imprimir01.png'">
			</a>
		</td>
	</tr>
</table>
</div>
</div>

<div align="center">
<div style="overflow: auto;width: 930px" >		
<t:paginacao id="tabela_AltCF" lista="${itensRelatorio}" semPaginacao="false">
	<jsp:attribute name="cabecalho">
		<th nowrap><b>Placa</b></th>
		<th nowrap><b>Marca</b></th>
		<th nowrap><b>Modelo</b></th>
		<th nowrap><b>Versão</b></th>
		<th nowrap><b>Data Compra</b></th>			
		<th nowrap><b>Data Venda</b></th>
		<th nowrap><b>Compra</b></th>
		<th nowrap><b>Comiss&atilde;o</b></th>
		<th nowrap><b>Gastos</b></th>
		<th nowrap><b>Venda</b></th>
		<th nowrap><b>Lucro</b></th>			
	</jsp:attribute>	
	<jsp:attribute name="linha">				
		<td>
			${item.placa}
		</td>
		
		<td align="center">
			${item.marca}
		</td>
		
		<td align="center" nowrap>
			${item.modelo}
		</td>
		
		<td align="center" nowrap>
			${item.versao}
		</td>				
		
		<td align="center">
			<fmt:formatDate value="${item.dataCompra.time }" pattern="dd/MM/yyyy"/>
		</td>	
		
		<td align="center">
			<fmt:formatDate value="${item.dataVenda.time }" pattern="dd/MM/yyyy"/>
		</td>
		
		<td align="center" nowrap>
			<fmt:formatNumber value="${item.vlCompra }" type="currency"/>
		</td>			
		
		<td align="center" nowrap>
			<fmt:formatNumber value="${item.vlComissao }" type="currency"/>			
		</td>
		
		<td align="center" nowrap>	
			<fmt:formatNumber value="${item.vlGastos }" type="currency"/>
		</td>	
		
		<td align="center" nowrap>	
			<fmt:formatNumber value="${item.vlVenda }" type="currency"/>
		</td>
		
		<td align="center" nowrap>
			<c:if test="${item.vlLucro < 0 }">
				<font color="#FF0000"> 
					<fmt:formatNumber value="${item.vlLucro }" type="currency"/>
				</font>
			</c:if>
			<c:if test="${item.vlLucro >= 0 }">
				<fmt:formatNumber value="${item.vlLucro }" type="currency"/>
			</c:if>
			
		</td>	
		
	</jsp:attribute>
	<jsp:attribute name="link">
		javascript: paginar('relLucro', '${tabela}', ${pagina});
	</jsp:attribute>
</t:paginacao>
</div>
</div>

</c:if>

</form>

<div id="pop" style="background-color:#FFF">        
        <div id="divShow"></div>
</div>

<!-- Layout -->		
<c:import url="../layout/rodape.jsp" />