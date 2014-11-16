<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

 <!-- Layout -->
<c:import url="../layout/cabecalho.jsp" />

<!-- Scripts Padrões -->
<script type="text/javascript" src="/safetycar/resources/javaScript/scriptPadrao.js"></script>


<link rel="stylesheet" href="/safetycar/resources/jquery/css/jquery-ui-1.8.20.custom.css" />

<script type="text/javascript" src="/safetycar/resources/jquery/js/jquery.mim.js"></script>
<script type="text/javascript" src="/safetycar/resources/jquery/js/jquery.ui.datepicker.js"></script>
<script type="text/javascript" src="/safetycar/resources/jquery/js/jquery.ui.core.js"></script>
<script type="text/javascript" src="/safetycar/resources/jquery/js/jquery.ui.datepicker-pt-BR.js"></script>


<script>
	$(function() {
		$( "#dataCadastro" ).datepicker();
	});
</script>


<script type='text/javascript' src='/safetycar/dwr/engine.js'></script>
<script type='text/javascript' src='/safetycar/dwr/interface/alteraCadastroVendedorAjaxDwr.js'></script>
<script type='text/javascript' src='/safetycar/dwr/util.js'></script>

<script type="text/javascript">
	function alterar(){
		var vendedor = {
				id:dwr.util.getValue("id"),		
				nome:dwr.util.getValue("nome"),	
				sobreNome:dwr.util.getValue("sobreNome")
			 };		
		alteraCadastroVendedorAjaxDwr.altVendedor(vendedor, {
			callback : function(retorno) {
	 			var c = document.getElementById("divShow");
			
	 			var table = "<table border='0'align='center' >";

				if(retorno[0] == 'ERRO'){
					table += "<tr>";
					table += "<td colspan='2' align='center'> <font size='5' face='Times, serif' color='red'><b><i> Aten&ccedil;&atilde;o </i></b></font> </td>";					
					table += "</tr>";
					
					table += "<tr>";
					table += "<td> <img src='/safetycar/resources/imagens/atencao.png' width='60' height='60' border='0'/> </td>";
					table += "<td> <font size='4' face='Times, serif' color='black'>" + retorno[1] + "</font> </td>";
					table += "</tr>";
					
		 			table += "</table>";				
		 			c.innerHTML = table;
				}else{
					/*Caso a alteração seja efetuada retorna para pagina de busca*/
					window.location="alteracaoVendedor";					  	
				}							
			}
		});	
	    
		}  
</script>

<form action="altCadastroVendedor" method="post">

<input type="hidden" id="id" name="id" value="${vendedor.id}">

<div align="center">
	<font size="5" face="Times, serif" color="black"><b><i>Altera&ccedil;&atilde;o Cadastro Vendedor</i></b></font>	
</div>


<table width="98%" border="0" cellpadding="1" cellspacing="0" align="center">		
	<tr>
		<td>			
			<br />
				

	<table border="0" cellpadding="3" cellspacing="1" align="center" >
		<tr>
			<td>
				<font size="4" face="Times, serif" color="black">Nome:</font> <b><font face="Times, serif" color="#FF0000">*</font></b>				
			</td>
			<td colspan="3">
				<input id="nome" name="nome" type="text" style="width:500px; height: 24px;" maxlength="99" onKeyUp="formataTexto(this);" value="${vendedor.nome }">
			</td>
		</tr>
		
		<tr>
			<td>
				<font size="4" face="Times, serif" color="black">Sobre Nome:</font>				
			</td>
			<td colspan="3">
				<input id="sobreNome" name="sobreNome" type="text" style="width:500px; height: 24px;" maxlength="99" onKeyUp="formataTexto(this);" value="${vendedor.sobreNome }">
			</td>
		</tr>		
		
		<tr>
			<td>
				<font size="4" face="Times, serif" color="black">Data Cadastro:</font>				
			</td>
			<td>
				<font size="3" face="Times, serif" color="black"> <fmt:formatDate value="${vendedor.dataCadastro.time}" pattern="dd/MM/yyyy"/> </font>
			</td>
			<td colspan="2" align="right">
				<a href="#dialog1" name="modal" style="text-decoration:none" onclick="$('html, body').animate({ scrollTop: 0 }, 'slow'); alterar();" >
					<img src="/safetycar/resources/imagens/salvar01.png" width="20" height="20" border="0" title="Salvar" alt="Salvar"
								 onMouseOver="this.src='/safetycar/resources/imagens/salvar02.png'"  
		                         onMouseOut="this.src='/safetycar/resources/imagens/salvar01.png'">
				</a>
			</td>
		</tr>		
	</table>			
		</td>		
	</tr>
</table>

</form>

<!-- Layout -->		
<c:import url="../layout/rodape.jsp" />

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
			
			<div id="divShow"></div>
			
			<table align="center">
				<tr>
					<td>
						<a href="#" class="close" onclick="$('html, body').animate({ scrollTop: 0 }, 'slow');">
							<img src="/safetycar/resources/imagens/aceitarSim.png" width="30" height="30" border="0" title="OK" alt="OK">
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

<!-- Máscara para cobrir a tela -->
  <div id="mask"></div>

</div>