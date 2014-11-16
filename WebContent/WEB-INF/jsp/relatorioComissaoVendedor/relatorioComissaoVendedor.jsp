<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


 <!-- Layout -->
<c:import url="../layout/cabecalho.jsp" />

<!-- Scripts Padr�es -->
<script type="text/javascript" src="/safetycar/resources/javaScript/scriptPadrao.js"></script>

<!-- Scripts de  pagina��o e libera��o de tags -->
<%@taglib tagdir="/WEB-INF/tags" prefix="t" %>
<script type="text/javascript" src="/safetycar/resources/javaScript/formulario.js"></script>
<script type="text/javascript" src="/safetycar/resources/javaScript/util.js"></script>


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
	<font size="5" face="Times, serif" color="black"><b><i>Relat&oacute;rio Comiss&atilde;o Vendedor</i></b></font>	
</div>

<br/>

<div align="center">
	<font size="4" face="Times, serif" color="black"><i>Pesquisa</i></font>
</div>

<form id="comissaoVendedor" name="comissaoVendedor" method="post">

<!-- Hiddens de  pagina��o -->
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
				</tr>
		</table>
	</td>
</tr>

<tr>
	<td colspan="4" align="right">
		<a href="#" onClick="document.comissaoVendedor.submit();">
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
			<a href="#" onClick="abrirNovaJanela('relComissaoVendedorImpressao?pesqPeriodoDe=${pesqPeriodoDe}&pesqPeriodoAte=${pesqPeriodoAte}&pesqVendedor=${pesqVendedor}','_blank', 'height=760, width=980, top=40, left=25, scrollbars=yes, resizable=no');">
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
<div style="width: 930px" >		
<t:paginacao id="tabela_AltCF" lista="${itensRelatorio}" semPaginacao="false">
	<jsp:attribute name="cabecalho">
		<th nowrap><b>Placa</b></th>
		<th nowrap><b>Marca</b></th>
		<th nowrap><b>Modelo</b></th>
		<th nowrap><b>Vers�o</b></th>
		<th nowrap><b>Data Venda</b></th>			
		<th nowrap><b>Nome Vendedor</b></th>
		<th nowrap><b>Vl Comiss&atilde;o</b></th>
		<th nowrap><b>Vl Bonus</b></th>
		<th nowrap><b>Total</b></th>			
	</jsp:attribute>	
	<jsp:attribute name="linha">				
		<td>
			${item.placa}
		</td>
		
		<td align="center">
			${item.marca}
		</td>
		
		<td align="center">
			${item.modelo}
		</td>
		
		<td align="center">
			${item.versao}
		</td>				
		
		<td align="center">
			<fmt:formatDate value="${item.dataVenda.time }" pattern="dd/MM/yyyy"/>
		</td>	
		
		<td align="center">
			${item.nomeVendedor}
		</td>
		
		<td align="center" nowrap>
			<fmt:formatNumber value="${item.vlComissao }" type="currency"/>
		</td>			
		
		<td align="center" nowrap>	
			<fmt:formatNumber value="${item.vlBonus }" type="currency"/>
		</td>
		
		<td align="center" nowrap>	
			<fmt:formatNumber value="${item.total }" type="currency"/>
		</td>		
		
	</jsp:attribute>
	<jsp:attribute name="link">
		javascript: paginar('comissaoVendedor', '${tabela}', ${pagina});
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