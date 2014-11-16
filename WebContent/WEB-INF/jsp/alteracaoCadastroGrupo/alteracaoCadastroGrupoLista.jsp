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
		$( "#pesqDataCadastro" ).datepicker();
	});
</script>

<script type="text/javascript">
	function removeGrupo(idGrupo, tipo){
		
		if(tipo == 'mensagem'){
			document.getElementById("idGrupo").value = idGrupo;
		}else if(tipo == 'excluir'){
			/*Caso seja confirmada a exclus�o*/
			window.location="alteracaoCadastroGrupoExcluirGrupo?idGrupo=" + document.getElementById("idGrupo").value;
		}		
	}
</script>

<div align="center">
	<font size="5" face="Times, serif" color="black"><b><i>Altera&ccedil;&atilde;o Cadastro Grupo</i></b></font>	
</div>

<br/>

<div align="center">
	<font size="4" face="Times, serif" color="black"><i>Pesquisa Grupo</i></font>
</div>

<form id="alteracaoGrupo" name="alteracaoGrupo" method="post">

<!-- Hiddens de  pagina��o -->
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
						<font size="4" face="Times, serif" color="black">Nome Grupo:</font>
					</td>
					<td colspan="3">
						<input id="pesqNome" name="pesqNome" type="text" style="width:500px; height: 24px;" maxlength="149" onKeyUp="javascript:somenteLetras(this); formataTexto(this);" value="${pesqNome }">&nbsp;						
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
		<a href="#" onClick="document.alteracaoGrupo.submit();">
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
				<th><b>Nome Grupo</b></th>
				<th><b>Data Cadastro</b></th>
				<th>&nbsp; &nbsp;</th>
				<th>&nbsp; &nbsp;</th>
			</jsp:attribute>
			<jsp:attribute name="linha">	
				<td>
					${item.nome}
				</td>
				
				<td align="center">
					<fmt:formatDate value="${item.dataCadastro.time}" pattern="dd/MM/yyyy"/>
				</td>				
												
				<td align="center">
					<a href="alteracaoCadastroGrupoSelecionadoGrupo?id=${item.id}">
						<img src="/safetycar/resources/imagens/editar01.png" width="20" height="20" border="0" title="Editar" alt="Editar"
							 onMouseOver="this.src='/safetycar/resources/imagens/editar02.png'"  
	                         onMouseOut="this.src='/safetycar/resources/imagens/editar01.png'">
					</a>
				</td>
				<td colspan="4" align="right">					 
					<a href="#dialog1" name="modal" style="text-decoration:none" onclick="$('html, body').animate({ scrollTop: 0 }, 'slow'); removeGrupo(${item.id}, 'mensagem');" >
						<img src="/safetycar/resources/imagens/remove01.png" width="20" height="20" border="0" title="Excluir Grupo" alt="Excluir Grupo"
									 onMouseOver="this.src='/safetycar/resources/imagens/remove02.png'"  
			                         onMouseOut="this.src='/safetycar/resources/imagens/remove01.png'">			            
					</a>
				</td>				
			</jsp:attribute>
			<jsp:attribute name="link">
				javascript: paginar('alteracaoGrupo', '${tabela}', ${pagina});
			</jsp:attribute>
		</t:paginacao>
	</td>
</tr>
</table>

</form>

<input type="hidden" id="idGrupo" name="idGrupo" value="" />

<div id="boxes">
  
<!-- Janela Modal -->  
<div id="dialog1" class="window">
	<div class="RoundedCorner" style="width:460px;">
		<b class="b1"></b>
		<b class="b2"></b>
		<b class="b3"></b>
		<b class="b4"></b>
		<div class="RoundedCornerContent" >
			
			<a href="#" class="close" onclick="$('html, body').animate({ scrollTop: 0 }, 'slow');">
				<img src="/safetycar/resources/imagens/fechar01.png" width="20" height="20" border="0" title="Fechar" alt="Fechar"
							 onMouseOver="this.src='/safetycar/resources/imagens/fechar02.png'"  
	                         onMouseOut="this.src='/safetycar/resources/imagens/fechar01.png'">
			</a>
			&nbsp;
			
			<table border='0' align='center' >
				<tr>
					<td colspan='2' align='center'> 
						<font size='5' face='Times, serif' color='red'>
							<b><i> Aten&ccedil;&atilde;o </i></b>
						</font>
					</td>
				</tr>
				
				<tr>
					<td> 
						<img src='/safetycar/resources/imagens/atencao.png' width='60' height='60' border='0'/> 
					</td>
					<td> 
						<font size='4' face='Times, serif' color='black'>
							Tem certeza que deseja excluir o Grupo?
						</font>
					</td>
				</tr>
			</table>
			
			<table align="center">
				<tr>
					<td>
						<a href="#" onclick="removeGrupo(0, 'excluir');">
							<img src="/safetycar/resources/imagens/aceitarSim.png" width="30" height="30" border="0" title="Sim" alt="Sim">
						</a>					
					</td>
					<td>
						&nbsp;					
					</td>
					<td>
						&nbsp;					
					</td>
					<td>
						&nbsp;					
					</td>
					<td>
						<a href="#" class="close" onclick="$('html, body').animate({ scrollTop: 0 }, 'slow');">
							<img src="/safetycar/resources/imagens/aceitarNao.png" width="30" height="30" border="0" title="N�o" alt="N�o">
						</a>					
					</td>
				</tr>			
			</table>			
		
		</div>
		<b class="b4"></b>
		<b class="b3"></b>
		<b class="b2"></b>
		<b class="b1"></b>
	</div>
</div>
<!-- Fim Janela Modal -->

<!-- M�scara para cobrir a tela -->
  <div id="mask"></div>

</div>

<br/>
<br/>

<!-- Layout -->		
<c:import url="../layout/rodape.jsp" />