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
	<font size="5" face="Times, serif" color="black"><b><i>Pesquisa Cliente</i></b></font>	
</div>

<br/>
		
<form id="pesquisaCliente" name="pesquisaCliente" method="get">	

<!-- Hiddens de  paginação -->
<input type="hidden" id="relatorio" name="relatorio" value="0">
<input type="hidden" id="metodo" name="metodo" value="paginar" />
<input type="hidden" id="pagina" name="pagina" value="" />
<input type="hidden" id="tabela" name="tabela" value="" />

<!-- Hiddens para manter dados -->
<input type="hidden" id="idVeiculo" name="idVeiculo"  value="${idVeiculo}">
<input type="hidden" id="valorVenda" name="valorVenda" value="${valorVenda}" />
<input type="hidden" id="dataVenda" name="dataVenda" value="${dataVenda}" />
<input type="hidden" id="vendedor" name="vendedor" value="${vendedor}" />

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
		<a href="#" onClick="document.pesquisaCliente.submit();">
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
					<a href="vendaVeiculoClienteSelecionado?idCliente=${item.id}&idVeiculo=${idVeiculo}&valorVenda=${valorVenda}&vendedor=${vendedor}&dataVenda=${dataVenda}">
						<img src="/safetycar/resources/imagens/selecionar01.png" width="20" height="20" border="0" title="Selecionar" alt="Selecionar"
							 onMouseOver="this.src='/safetycar/resources/imagens/selecionar02.png'"  
		                        onMouseOut="this.src='/safetycar/resources/imagens/selecionar01.png'">
					</a>
				</td>										
			</jsp:attribute>
			<jsp:attribute name="link">
				javascript: paginar('pesquisaCliente', '${tabela}', ${pagina});
			</jsp:attribute>
			</t:paginacao>
	</td>
</tr>
</table>

</form>

<!-- Layout -->		
<c:import url="../layout/rodape.jsp" />