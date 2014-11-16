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
	<font size="4" face="Times, serif" color="black"><i>Pesquisa Fornecedor</i></font>
</div>

<form id="lanGastoVeiculoSelecionaFornecedor" name="lanGastoVeiculoSelecionaFornecedor" method="post">

<!-- Hiddens de  paginação -->
<input type="hidden" id="relatorio" name="relatorio" value="0">
<input type="hidden" id="metodo" name="metodo" value="paginar" />
<input type="hidden" id="pagina" name="pagina" value="" />
<input type="hidden" id="tabela" name="tabela" value="" />

<input type="hidden" id="idVeiculo" name="idVeiculo" value="${idVeiculo }" />

<table align="center">
<tr>
	<td colspan="4">
		<table border="0" cellpadding="3" cellspacing="1" align="left">
				<tr>
					<td>
						<font size="4" face="Times, serif" color="black">Nome Fornecedor:</font>
					</td>
					<td colspan="3">
						<input id="pesqNome" name="pesqNome" type="text" style="width:500px; height: 24px;" maxlength="99" onkeyup="somenteLetras(this); formataTexto(this);" value="${pesqNome }">
					</td>		
				</tr>
				
				<tr>
					<td>
						<font size="4" face="Times, serif" color="black">Contato:</font>
					</td>
					<td>						
						<input id="pesqContato" name="pesqContato" type="text" style="width:200px; height: 24px;" maxlength="99" onkeyup="somenteLetras(this); formataTexto(this);" value="${pesqContato }">
					</td>
					<td align="right">
						<font size="4" face="Times, serif" color="black">Telefone:</font>
					</td>
					<td align="right">
						<input id="pesqTelefone" name="pesqTelefone" type="text" style="width:200px; height: 24px;" max="13" onkeyup="bloqueiaTelefone(this);return mascara(this, '##-####-#####', event);" value="${pesqTelefone }">
					</td>				
				</tr>
		</table>
	</td>
</tr>

<tr>
	<td colspan="4" align="right">
		<a href="#" onClick="document.lanGastoVeiculoSelecionaFornecedor.submit();">
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
				<th><b>Nome Fornecedor</b></th>
				<th><b>Contato</b></th>
				<th><b>Telefone</b></th>
				<th><b>Data Cadastro</b></th>				
				<th>&nbsp; &nbsp;</th>				
			</jsp:attribute>
			<jsp:attribute name="linha">	
				<td>
					${item.nome}
				</td>
				
				<td align="center">
					${item.contato}
				</td>
				
				<td align="center">
					${item.telefone}
				</td>				
				
				<td align="center">
					<fmt:formatDate value="${item.dtCadastro.time}" pattern="dd/MM/yyyy"/>
				</td>
												
				<td align="center">
					<a href="gastosVeiculoSelecionadoForDesp?idForDesp=${item.id}&idVeiculo=${idVeiculo}">
						<img src="/safetycar/resources/imagens/selecionar01.png" width="20" height="20" border="0" title="Selecionar" alt="Selecionar"
							 onMouseOver="this.src='/safetycar/resources/imagens/selecionar02.png'"  
	                         onMouseOut="this.src='/safetycar/resources/imagens/selecionar01.png'">
					</a>
				</td>				
			</jsp:attribute>
			<jsp:attribute name="link">
				javascript: paginar('lanGastoVeiculoSelecionaFornecedor', '${tabela}', ${pagina});
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