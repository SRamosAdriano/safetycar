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
<script type='text/javascript' src='/safetycar/dwr/interface/resetSenhaLoginsAjaxDwr.js'></script>
<script type='text/javascript' src='/safetycar/dwr/util.js'></script>

<script type="text/javascript">
	function alterar(){
	
	  var usuario = {
			  		 id:dwr.util.getValue("id"),	
		             nome:dwr.util.getValue("nome"),	
		             login:dwr.util.getValue("login")
			        };

	  resetSenhaLoginsAjaxDwr.resetSenhaLogin(usuario, {
 		 callback : function(retorno) {
			var c = document.getElementById("divShow");
		
			var table = "<table border='0'align='center' >";

			if(retorno[0] == 'ERRO'){
				table += "<tr>";
				table += "<td colspan='2' align='center'> <font size='5' face='Times, serif' color='red'><b><i> Aten&ccedil;&atilde;o </i></b></font> </td>";					
				table += "</tr>";
				
				table += "<tr>";
				table += "<td> <img src='/safetycar/resources/imagens/atencao.png' width='60' height='60' border='0'/> </td>";
				table += "<td> <font size='4' face='Times, serif' color='black'>" + retorno[0] + "</font> </td>";
				table += "</tr>";
			}else{
				table += "<tr>";
				table += "<td> <img src='/safetycar/resources/imagens/aceitarSim.png' width='60' height='60' border='0'/> </td>";
				table += "<td> <font size='4' face='Times, serif' color='black'>" + retorno[0] + "</font> </td>";						
				table += "</tr>";
				table += "<tr>";
				table += "<td>&nbsp;</td>";
				table += "<td> <font size='4' face='Times, serif' color='black'>" + retorno[1] + "</font> </td>";
				table += "<tr>";								  	
			}
							
			table += "</table>";				
			c.innerHTML = table;
			}
		});
	}
		
</script>

<div align="center">
	<font size="5" face="Times, serif" color="black"><b><i> Reset de Senha</i></b></font>	
</div>


<form action="adicionaUsuario" method="post">

<input type="hidden" id="id" name="id" value="${usuario.id}">
<input type="hidden" id="nome" name="nome" value="${usuario.nome }">
<input type="hidden" id="login" name="login" value="${usuario.login }">

<table width="98%" border="0" cellpadding="1" cellspacing="0" align="center">	
	<tr>
		<td>			
			<br />
				
<table border="0" cellpadding="3" cellspacing="1" align="center">
	<tr>
		<td colspan="2">
			<table border="0" cellpadding="3" cellspacing="1" align="center">	
				<tr>
					<td nowrap width="10px">
						<font size="4" face="Times, serif" color="black">Nome:</font>
					</td>
					<td align="left">
						<font size="3" face="Times, serif" color="black"> ${usuario.nome } </font>
					</td>							
				</tr>	
				
				<tr>
					<td nowrap>
						<font size="4" face="Times, serif" color="black">Login:</font>
					</td>
					<td>
						<font size="3" face="Times, serif" color="black"> ${usuario.login } </font>						
					</td>
				</tr>
				
				<tr>
					<td nowrap>
						<font size="4" face="Times, serif" color="black">Data Dadastro:</font>
					</td>
					<td>
						<font size="3" face="Times, serif" color="black"> <fmt:formatDate value="${usuario.dataCadastro.time}" pattern="dd/MM/yyyy"/> </font>			
					</td>						
				</tr>	
				
				<tr>
					<td nowrap>
						<font size="4" face="Times, serif" color="black">Ultimo Acesso:</font>
					</td>
					<td>
						<font size="3" face="Times, serif" color="black"> <fmt:formatDate value="${usuario.dataUltimoLogin.time}" pattern="dd/MM/yyyy"/> </font>						
					</td>	
				</tr>			
			</table>
		</td>
	</tr>
		
	<tr>
		<td colspan="4" align="center">
			<font size="4" face="Times, serif" color="black"> <i>Grupos Liberados:</i></font>
		</td>
	</tr>
	<tr>
		<td colspan="2">										
		 	<c:forEach items="${usuario.grupos}" var="grupo">
		 		<font size="3" face="Times, serif" color="black">${grupo.nome }</font>				 		
		 		<br/>				 							 	
		 	</c:forEach>					
		</td>			
	</tr>
	
	<tr>
		<td colspan="4" align="center">
			<a href="#dialog1" name="modal" style="text-decoration:none" onclick="$('html, body').animate({ scrollTop: 0 }, 'slow'); alterar()" >
				<img src="/safetycar/resources/imagens/senha01.png" width="35" height="35" border="0" title="Alterar Senha" alt="Alterar Senha"
							 onMouseOver="this.src='/safetycar/resources/imagens/senha02.png'"  
	                         onMouseOut="this.src='/safetycar/resources/imagens/senha01.png'">
			</a>	
		</td>		
	</tr>

</table>
			
		</td>		
	</tr>
</table>

</form>

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

<br />
<br />



<!-- Layout -->		
<c:import url="../layout/rodape.jsp" />