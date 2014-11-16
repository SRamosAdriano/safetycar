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
		$( "#pesqDataCadastro" ).datepicker();
	});
</script>

<div align="center">
	<font size="5" face="Times, serif" color="black"><b><i>Reset de Senha</i></b></font>	
</div>

<br/>

<div align="center">
	<font size="4" face="Times, serif" color="black"><i>Pesquisa Usu&aacute;rio</i></font>
</div>

<form id="resetSenha" name="resetSenha" method="post">

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
						<font size="4" face="Times, serif" color="black">Nome Usu&aacute;rio:</font>
					</td>
					<td colspan="3">
						<input id="pesqNome" name="pesqNome" type="text" style="width:500px; height: 24px;" maxlength="149" onKeyUp="javascript:somenteLetras(this); formataTexto(this);" value="${pesqNome }">&nbsp;						
					</td>		
				</tr>
				
				<tr>
					<td>
						<font size="4" face="Times, serif" color="black">Login Usu&aacute;rio:</font>
					</td>
					<td colspan="3">						
						<input id="pesqLogin" name="pesqLogin" type="text" style="width:200px; height: 24px;" maxlength="31" onkeyup="formataTexto(this); converteMinuscula(this);" value="${pesqLogin }">&nbsp;						
					</td>		
				</tr>
				
				<tr>
					<td>
						<font size="4" face="Times, serif" color="black">Data Cadastro:</font>
					</td>
					<td>						
						<input type="text" style="width:200px; height: 24px;" id="pesqDataCadastro" name="pesqDataCadastro" onblur="validaData(this)" onKeyUp="bloqueiaData(this);return mascara(this, '##/##/####', event);"
							maxlength="10" value="${pesqDataCadastro}" />
					</td>									
				</tr>
		</table>
	</td>
</tr>

<tr>
	<td colspan="4" align="right">
		<a href="#" onClick="document.resetSenha.submit();">
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
				<th><b>Nome Usuario</b></th>
				<th><b>Login</b></th>
				<th><b>Data Cadastro</b></th>
				<th><b>Data Ultimo Login</b></th>
				<th>&nbsp; &nbsp;</th>
			</jsp:attribute>
			<jsp:attribute name="linha">	
				<td>
					${item.nome}
				</td>
				
				<td>
					${item.login}
				</td>
				
				<td align="center">
					<fmt:formatDate value="${item.dataCadastro.time}" pattern="dd/MM/yyyy"/>
				</td>
				
				<td align="center">
					<fmt:formatDate value="${item.dataUltimoLogin.time}" pattern="dd/MM/yyyy"/>
				</td>	
												
				<td align="center">
					<a href="resetSenhaLoginsSelecionadoUsuario?id=${item.id}">
						<img src="/safetycar/resources/imagens/selecionar01.png" width="20" height="20" border="0" title="Selecione" alt="Selecione"
							 onMouseOver="this.src='/safetycar/resources/imagens/selecionar02.png'"  
	                         onMouseOut="this.src='/safetycar/resources/imagens/selecionar01.png'">
					</a>
				</td>				
			</jsp:attribute>
			<jsp:attribute name="link">
				javascript: paginar('resetSenha', '${tabela}', ${pagina});
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