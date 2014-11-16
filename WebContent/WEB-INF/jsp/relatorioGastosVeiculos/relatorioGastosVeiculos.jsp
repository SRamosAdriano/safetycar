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
	<font size="5" face="Times, serif" color="black"><b><i>Relat&oacute;rio Gastos Veiculos</i></b></font>	
</div>

<br/>

<div align="center">
	<font size="4" face="Times, serif" color="black"><i>Pesquisa</i></font>
</div>

<form id="gastosVeiculos" name="gastosVeiculos" method="post">

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
						<font size="4" face="Times, serif" color="black">Periodo De:</font><font face="Times, serif" color="#FF0000"><b>*</b></font>
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
						<font size="4" face="Times, serif" color="black">Placa:</font>
					</td>
					<td>						
						<input id="pesqPlaca" name="pesqPlaca" type="text" style="width:200px; height: 24px;" maxlength="8" onKeyUp="validaPlaca(this); converteMaiuscula(this); return mascara(this, '###-####', event)" onkeyup="somenteLetras(this); formataTexto(this);" value="${pesqPlaca }">
					</td>
					<td>
						<font size="4" face="Times, serif" color="black">Marca:</font>
					</td>
					<td>						
						<select style="width:200px; height: 24px;" id="pesqMarca" name="pesqMarca">	
						<option value="Selecione">Selecione</option>
						<c:forEach items="${marcasVeiculos}" var="marcasVeiculos">
							<c:if test="${pesqMarca eq marcasVeiculos.nomeMarca}">
								<option selected="selected"> ${marcasVeiculos.nomeMarca } </option>
							</c:if>
							<c:if test="${pesqMarca != marcasVeiculos.nomeMarca}">
								<option> ${marcasVeiculos.nomeMarca } </option>
							</c:if>				
						</c:forEach>
					 </select>
					</td>									
				</tr>
				<tr>
					<td>
						<font size="4" face="Times, serif" color="black">Modelo:</font>
					</td>
					<td>
						<input id="pesqModelo" name="pesqModelo" type="text" style="width:200px; height: 24px;" maxlength="199" onkeyup="formataTexto(this);" value="${pesqModelo }">
					</td>					
				</tr>
				<tr>
					<td>
						<font size="4" face="Times, serif" color="black">Fornecedor:</font>
					</td>
					<td colspan="3">						
						<select style="width:470px; height: 24px;" id="pesqFornecedor" name="pesqFornecedor">	
						<option value="Selecione">Selecione</option>
						<c:forEach items="${fornecedores}" var="fornecedores">
							<c:if test="${pesqFornecedor eq fornecedores}">
								<option selected="selected"> ${fornecedores } </option>
							</c:if>
							<c:if test="${pesqFornecedor != fornecedores}">
								<option> ${fornecedores } </option>
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
		<a href="#" onClick="document.gastosVeiculos.submit();">
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
			<a href="#" onClick="abrirNovaJanela('relGastosVeiculosImpressao?pesqPeriodoDe=${pesqPeriodoDe}&pesqPeriodoAte=${pesqPeriodoAte}&pesqPlaca=${pesqPlaca}&pesqMarca=${pesqMarca}&pesqModelo=${pesqModelo}&pesqFornecedor=${pesqFornecedor}','_blank', 'height=760, width=980, top=40, left=25, scrollbars=yes, resizable=no');">
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
		<th nowrap><b>N&ordm; Documento</b></th>
		<th nowrap><b>Data Gasto</b></th>			
		<th nowrap><b>Nome Fornecedor</b></th>
		<th nowrap><b>Descri&ccedil;&atilde;o</b></th>
		<th nowrap><b>Valor</b></th>					
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
			${item.nDocumento}
		</td>				
		
		<td align="center">
			<fmt:formatDate value="${item.dtGasto.time }" pattern="dd/MM/yyyy"/>
		</td>	
		
		<td align="center">
			${item.nomeFornecedor}
		</td>
		
		<td align="center">
			${item.descricaoGasto}
		</td>			
				
		
		<td align="center" nowrap>	
			${item.valorGasto }
		</td>		
		
	</jsp:attribute>
	<jsp:attribute name="link">
		javascript: paginar('gastosVeiculos', '${tabela}', ${pagina});
	</jsp:attribute>
</t:paginacao>
</div>
</div>


</c:if>

</form>

<!-- Layout -->		
<c:import url="../layout/rodape.jsp" />