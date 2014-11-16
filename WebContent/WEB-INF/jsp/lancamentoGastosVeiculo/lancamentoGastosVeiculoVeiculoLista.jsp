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
	<font size="5" face="Times, serif" color="black"><b><i>Gastos Veiculo</i></b></font>	
</div>

<br/>

<div align="center">
	<font size="4" face="Times, serif" color="black"><i>Pesquisa Veiculo</i></font>
</div>

<form id="lanGastoVeiculoSelecionaVeiculo" name="lanGastoVeiculoSelecionaVeiculo" method="post">

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
		<a href="#" onClick="document.lanGastoVeiculoSelecionaVeiculo.submit();">
			<img src="/safetycar/resources/imagens/pesquisa01.png" width="20" height="20" border="0" title="Pesquisar" alt="Pesquisar"
						 onMouseOver="this.src='/safetycar/resources/imagens/pesquisa02.png'"  
	                     onMouseOut="this.src='/safetycar/resources/imagens/pesquisa01.png'">
		</a>		
	</td>
</tr>

<tr>
	<td colspan="4">
	
	</td>
</tr>
</table>


<table align="center">
<tr>
	<td colspan="4"> 
		<t:paginacao id="tabela_AltCF" lista="${itensRelatorio}" semPaginacao="false">
			<jsp:attribute name="cabecalho">
				<th><b>Placa</b></th>
				<th><b>Marca</b></th>
				<th><b>Modelo</b></th>
				<th><b>Renavan</b></th>
				<th><b>Data Cadastro</b></th>
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
					${item.renavan}
				</td>
				
				<td align="center">
					<fmt:formatDate value="${item.dataCadastro.time}" pattern="dd/MM/yyyy"/>
				</td>
												
				<td align="center">
					<a href="gastosVeiculoSelecionadoVeiculo?idVeiculo=${item.id}">
						<img src="/safetycar/resources/imagens/selecionar01.png" width="20" height="20" border="0" title="Selecionar" alt="Selecionar"
							 onMouseOver="this.src='/safetycar/resources/imagens/selecionar02.png'"  
	                         onMouseOut="this.src='/safetycar/resources/imagens/selecionar01.png'">
					</a>
				</td>				
			</jsp:attribute>
			<jsp:attribute name="link">
				javascript: paginar('lanGastoVeiculoSelecionaVeiculo', '${tabela}', ${pagina});
			</jsp:attribute>
		</t:paginacao>
	</td>
</tr>
</table>

</form>

<br/>
<br/>

<!-- Layout -->		
<c:import url="../layout/rodape.jsp" />