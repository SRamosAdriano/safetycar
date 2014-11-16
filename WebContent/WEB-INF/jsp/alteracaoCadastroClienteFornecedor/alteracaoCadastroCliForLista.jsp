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
	<font size="5" face="Times, serif" color="black"><b><i>Alteracao Cadastro Cliente / Fornecedor</i></b></font>	
</div>

<br/>

<div align="center">
	<font size="4" face="Times, serif" color="black"><i>Pesquisa Cliente / Fornecedor</i></font>
</div>

<form id="pesquisaCliFor" name="pesquisaCliFor" method="post">

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
						<font size="4" face="Times, serif" color="black">Nome:</font>
					</td>
					<td colspan="3">
						<input id="pesqNome" name="pesqNome" type="text" style="width:500px; height: 24px;" maxlength="150" onKeyUp="javascript:somenteLetras(this); formataTexto(this);" value="${pesqNome }">
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
					<td align="right">
						<input id="pesqRg" name="pesqRg" type="text" style="width:200px; height: 24px;" maxlength="10" onkeyup="somenteNumerosLetras(this); converteMaiuscula(this);" value="${pesqRg }">
					</td>				
				</tr>
		</table>
	</td>
</tr>

<tr>
	<td colspan="4" align="right">
		<a href="#" onClick="document.pesquisaCliFor.submit();">
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
				<th><b>Nome Completo</b></th>
				<th><b>RG</b></th>
				<th><b>CPF</b></th>
				<th><b>Tipo</b></th>
				<th><b>Data Cadastro</b></th>				
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
					${item.tipoCliFor}
				</td>
				
				<td align="center">
					<fmt:formatDate value="${item.dataCadastro.time}" pattern="dd/MM/yyyy"/>
				</td>
				
				<td align="center">
					<a href="cliForSelecionado?id=${item.id}">
						<img src="/safetycar/resources/imagens/editar01.png" width="20" height="20" border="0" title="Editar" alt="Editar"
							 onMouseOver="this.src='/safetycar/resources/imagens/editar02.png'"  
	                         onMouseOut="this.src='/safetycar/resources/imagens/editar01.png'">
					</a>
				</td>										
			</jsp:attribute>
			<jsp:attribute name="link">
				javascript: paginar('pesquisaCliFor', '${tabela}', ${pagina});
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