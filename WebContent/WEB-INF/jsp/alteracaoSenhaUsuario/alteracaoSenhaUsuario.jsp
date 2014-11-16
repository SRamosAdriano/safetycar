<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

 <!-- Layout -->
<c:import url="../layout/cabecalho.jsp" />

<!-- Scripts Padrões -->
<script type="text/javascript" src="/safetycar/resources/javaScript/scriptPadrao.js"></script>


<script type='text/javascript' src='/safetycar/dwr/engine.js'></script>
<script type='text/javascript' src='/safetycar/dwr/interface/alteraSenhaUsuarioAjaxDwr.js'></script>
<script type='text/javascript' src='/safetycar/dwr/util.js'></script>

<script type="text/javascript">
	function alterar(){
			
		var senha = dwr.util.getValue("senha");
		var senhaNova = dwr.util.getValue("senhaNova");
		var senhaNovaConf = dwr.util.getValue("senhaNovaConf");

	 	alteraSenhaUsuarioAjaxDwr.altSenhaUsuario(senha, senhaNova, senhaNovaConf, {
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
				}else{
					table += "<tr>";
					table += "<td> <img src='/safetycar/resources/imagens/aceitarSim.png' width='60' height='60' border='0'/> </td>";
					table += "<td> <font size='4' face='Times, serif' color='black'>" + retorno[1] + "</font> </td>";
					table += "</tr>";

					/* Reseta input */					
					document.getElementById("senha").value ="";
					document.getElementById("senhaNova").value ="";
					document.getElementById("senhaNovaConf").value ="";					  	
				}
								
	 			table += "</table>";				
	 			c.innerHTML = table;			
			}
		});	
	    
		}  
</script>

<div align="center">
	<font size="5" face="Times, serif" color="black"><b><i>Altera&ccedil;&atilde;o Senha Usu&aacute;rio</i></b></font>	
</div>


<form action="alteraSenhaUsuario" method="post">

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
				
				<tr>
					<td colspan="2" align="center">
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
			</table>
		</td>
	</tr>	
	
	<tr>
		<td colspan="2" align="center">										
		 	<font size="4" face="Times, serif" color="black"><i>Senhas</i></font>					
		</td>
	</tr>
	
	<tr>
		<td>										
		 	<font size="4" face="Times, serif" color="black">Senha Atual:</font>					
		</td>
		<td>										
		 	<input id="senha" name="senha" type="password" style="width:200px;" maxlength="80" value="">					
		</td>
	</tr>
	
	<tr>
		<td>
			<font size="4" face="Times, serif" color="black">Senha Nova:</font>
		</td>
		<td>
			<input id="senhaNova" name="senhaNova" type="password" style="width:200px;" maxlength="80" value="">
		</td>	
	</tr>
	
	<tr>
		<td>
			<font size="4" face="Times, serif" color="black">Confirme Senha Nova:</font>
		</td>
		<td>
			<input id="senhaNovaConf" name="senhaNovaConf" type="password" style="width:200px;" maxlength="80" value="">
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