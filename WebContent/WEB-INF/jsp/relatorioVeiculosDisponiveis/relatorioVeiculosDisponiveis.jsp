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

<style>
#pop{
	display:none;
	position:absolute;
	top:50%;
	left:50%;
	margin-left:-150px;
	margin-top:-100px;
	padding:10px;
	width:450px;
	border:1px solid #d0d0d0
}
</style>		
		
<script type="text/javascript">
	function detalhes(idVeiculo){
		relatorioVeiculosDisponiveisAjaxDwr.consultaDetVeiculoDisp(idVeiculo, {
			callback : function(veiculosDispVenda) {	 			

			var c = document.getElementById("divShow");
			
			var conteudo = "<table align='center'>";
			conteudo += "<tr><td align='center' colspan='4'><font size='4' face='Times, serif' color='black'>Detalhe Veiculo</font></td></tr>";
			conteudo += "<tr>";
			conteudo += "<td nowrap><font size='4' face='Times, serif' color='black'>Placa:</font></td>";
			conteudo += "<td><font size='3' face='Times, serif' color='black'>" + veiculosDispVenda.placa + "</font></td>";
			conteudo += "<td nowrap><font size='4' face='Times, serif' color='black'>Marca:</font></td>";
			conteudo += "<td><font size='3' face='Times, serif' color='black'>" + veiculosDispVenda.marca + "</font></td>";
			conteudo += "</tr>";
			conteudo += "<tr>";
			conteudo += "<td nowrap><font size='4' face='Times, serif' color='black'>Modelo:</font></td>";
			conteudo += "<td colspan='3'><font size='3' face='Times, serif' color='black'>" + veiculosDispVenda.modelo + "</font></td>";
			conteudo += "</tr>";
			conteudo += "<tr>";
			conteudo += "<td nowrap><font size='4' face='Times, serif' color='black'>Vers&atilde;o:</font></td>";
			conteudo += "<td colspan='3'><font size='3' face='Times, serif' color='black'>" + veiculosDispVenda.versao + "</font></td>";
			conteudo += "</tr>";
			conteudo += "<tr>";
			conteudo += "<td nowrap><font size='4' face='Times, serif' color='black'>Ano Fab/Mod:</font></td>";
			conteudo += "<td><font size='3' face='Times, serif' color='black'>" + veiculosDispVenda.anoFab + "/" + veiculosDispVenda.anoMod + "</font></td>";
			conteudo += "<td nowrap><font size='4' face='Times, serif' color='black'>Cor:</font></td>";
			conteudo += "<td><font size='3' face='Times, serif' color='black'>" + veiculosDispVenda.cor + "</font></td>";
			conteudo += "</tr>";
			conteudo += "<tr>";
			conteudo += "<td nowrap><font size='4' face='Times, serif' color='black'>Km:</font></td>";
			conteudo += "<td><font size='3' face='Times, serif' color='black'>" + veiculosDispVenda.km + "</font></td>";
			conteudo += "<td nowrap><font size='4' face='Times, serif' color='black'>Combustivel:</font></td>";
			conteudo += "<td><font size='3' face='Times, serif' color='black'>" + veiculosDispVenda.combustivel + "</font></td>";
			conteudo += "</tr>";
			conteudo += "<tr>";
			conteudo += "<td nowrap><font size='4' face='Times, serif' color='black'>Valor Venda:</font></td>";
			conteudo += "<td colspan='3'><font size='3' face='Times, serif' color='black'>" + veiculosDispVenda.valorVenda + "</font></td>";
			conteudo += "</tr>";
			conteudo += "<tr><td align='center' colspan='4'><font size='4' face='Times, serif' color='black'>Opcionais:</font></td></tr>";
			conteudo += "<tr><td align='left' colspan='4'><font size='3' face='Times, serif' color='black'>" + veiculosDispVenda.opcionais + "</font></td></tr>";
			conteudo += "</table>";			
			c.innerHTML = conteudo;				 					
			}
		});	
	}
</script>


<div align="center">
	<font size="5" face="Times, serif" color="black"><b><i>Relat&oacute;rio Veiculos Disponiveis para Venda</i></b></font>	
</div>

<br/>

<div align="center">
	<font size="4" face="Times, serif" color="black"><i>Pesquisa Veiculo</i></font>
</div>

<form id="veiculosDisponiveis" name="veiculosDisponiveis" method="post">

<!-- Hiddens de  paginação -->
<input type="hidden" id="relatorio" name="relatorio" value="0">
<input type="hidden" id="metodo" name="metodo" value="paginar" />
<input type="hidden" id="pagina" name="pagina" value="" />
<input type="hidden" id="tabela" name="tabela" value="" />


<table align="center">
<tr>
	<td colspan="4">
		<table border="0" cellpadding="3" cellspacing="1" align="left">
				<tr>
					<td>
						<font size="4" face="Times, serif" color="black">Placa:</font>
					</td>
					<td colspan="3">
						<input id="pesqPlaca" name="pesqPlaca" type="text" style="width:200px; height: 24px;" maxlength="8" onKeyUp="validaPlaca(this); converteMaiuscula(this); return mascara(this, '###-####', event)" onkeyup="somenteLetras(this); formataTexto(this);" value="${pesqPlaca }">
					</td>		
				</tr>
				
				<tr>
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
					<td align="right">
						<font size="4" face="Times, serif" color="black">Modelo:</font>
					</td>
					<td align="right">
						<input id="pesqModelo" name="pesqModelo" type="text" style="width:200px; height: 24px;" maxlength="199" onkeyup="formataTexto(this);" value="${pesqModelo }">
					</td>				
				</tr>
		</table>
	</td>
</tr>

<tr>
	<td colspan="4" align="right">
		<a href="#" onClick="document.veiculosDisponiveis.submit();">
			<img src="/safetycar/resources/imagens/pesquisa01.png" width="20" height="20" border="0" title="Pesquisar" alt="Pesquisar"
						 onMouseOver="this.src='/safetycar/resources/imagens/pesquisa02.png'"  
	                     onMouseOut="this.src='/safetycar/resources/imagens/pesquisa01.png'">
		</a>		
	</td>
</tr>
</table>

<div align="center">
<div style="width: 900px" >	
<table style="width: 905px" border="0" align="center">
	<tr>
		<td align="right">
			<a href="#" onClick="abrirNovaJanela('veiculosDisponiveisImpressao?pesqPlaca=${pesqPlaca}&pesqMarca=${pesqMarca}&pesqModelo=${pesqModelo}','_blank', 'height=760, width=980, top=40, left=25, scrollbars=yes, resizable=no');">
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
		<th><b>Placa</b></th>
		<th><b>Marca</b></th>
		<th><b>Modelo</b></th>
		<th><b>Versão</b></th>
		<th><b>Ano</b></th>			
		<th><b>Valor</b></th>
		<th>&nbsp; &nbsp;</th>			
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
			${item.anoFab} / ${item.anoMod}
		</td>				
		
		<td align="center">					
			${item.valorVenda }
		</td>
		
		<td>
			<a href="#" onmouseover="document.getElementById('pop').style.display='block';" 
							onmouseout="document.getElementById('pop').style.display='none';">
				<img src="/safetycar/resources/imagens/detalhes01.png" width="20" height="20" border="0" title="Detalhes" alt="Detalhes"
							 onMouseOver="this.src='/safetycar/resources/imagens/detalhes02.png'; detalhes(${item.veiculo_id})"  
			                    onMouseOut="this.src='/safetycar/resources/imagens/detalhes01.png'">
			</a>		
		</td>
	</jsp:attribute>
	<jsp:attribute name="link">
		javascript: paginar('veiculosDisponiveis', '${tabela}', ${pagina});
	</jsp:attribute>
</t:paginacao>
</div>
</div>

</form>

<div id="pop" style="background-color:#FFF">        
        <div id="divShow"></div>
</div>

<!-- Layout -->		
<c:import url="../layout/rodape.jsp" />