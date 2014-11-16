<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

 <!-- Layout -->
<c:import url="../layout/cabecalho.jsp" />

<!-- Scripts Padr�es -->
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
<script type='text/javascript' src='/safetycar/dwr/interface/cadastroCliForAjaxDwr.js'></script>
<script type='text/javascript' src='/safetycar/dwr/util.js'></script>

<script type="text/javascript">
	function adicionar(){
		var clienteFornecedor = {		
				tipoCliFor:dwr.util.getValue("tipoCliFor"),	
				nomeCompleto:dwr.util.getValue("nomeCompleto"),
				rg:dwr.util.getValue("rg"),
				cpf:dwr.util.getValue("cpf"),
				endereco:dwr.util.getValue("endereco"),
				bairro:dwr.util.getValue("bairro"),
				cep:dwr.util.getValue("cep"),
				cidade:dwr.util.getValue("cidade"),
				estado:dwr.util.getValue("estado"),				
				telefone:dwr.util.getValue("telefone"),
				celular:dwr.util.getValue("celular"),
				radio:dwr.util.getValue("radio"),
				email:dwr.util.getValue("email"),					
				status:dwr.util.getValue("status")
			 };

		/* Data calendar */
		var dataCadastro = dwr.util.getValue("dataCadastro");

	 	cadastroCliForAjaxDwr.addClienteFornecedor(clienteFornecedor, dataCadastro, {
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
					table += "<td> <font size='4' face='Times, serif' color='black'> Dados cadastrados com sucesso</font> </td>";
					table += "</tr>";

					/* Reseta input */					
					document.getElementById("nomeCompleto").value ="";
					document.getElementById("rg").value ="";
					document.getElementById("cpf").value ="";
					document.getElementById("endereco").value ="";
					document.getElementById("bairro").value ="";
					document.getElementById("cep").value ="";
					document.getElementById("cidade").value ="";
					document.getElementById("telefone").value ="";
					document.getElementById("celular").value ="";
					document.getElementById("nomeCompleto").value ="";
					document.getElementById("celular").value ="";
					document.getElementById("radio").value ="";					
					document.getElementById("email").value ="";	
					document.getElementById("dataCadastro").value ="";					
					
					/* Reseta combo*/					
					var combo = document.getElementById("estado");
					for ( i =0; i < combo.length; i++){
						combo[i].selected = combo[i].value == 'Selecione' ? true : false;
                    } 	
                    
              		/* Reseta radios */
					document.getElementById('cliente/Fornecedor').checked=true;  	
					document.getElementById('ativo').checked=true;  	
				}
								
	 			table += "</table>";				
	 			c.innerHTML = table;			
			}
		});	
	    
		}  
</script>

<div align="center">
	<font size="5" face="Times, serif" color="black"><b><i>Cadastro Cliente / Fornecedor</i></b></font>	
</div>


<form action="adicionaCliFor" method="post">

<table width="98%" border="0" cellpadding="1" cellspacing="0" align="center">	
	<tr>
		<td>			
			<br />
				
<table border="0" cellpadding="3" cellspacing="1" align="center">	

	<tr>
		<td colspan="4">			
			<input type="radio" id="cliente/Fornecedor" name="tipoCliFor" value="cliente/Fornecedor" checked="checked"> <font size="4" face="Times, serif" color="black">Cliente / Fornecedor</font>
			&nbsp; &nbsp;
			<input type="radio" id="cliente" name="tipoCliFor" value="cliente"> <font size="4" face="Times, serif" color="black">Cliente</font>
			&nbsp; &nbsp;
			<input type="radio" id="fornecedor" name="tipoCliFor" value="fornecedor"> <font size="4" face="Times, serif" color="black">Fornecedor</font>
		</td>
	</tr>
	
	<tr>
		<td>
			<font size="4" face="Times, serif" color="black">Nome Completo:</font> <b><font face="Times, serif" color="#FF0000">*</font></b>
		</td>
		<td colspan="3" align="right">
			<input id="nomeCompleto" name="nomeCompleto" type="text" style="width:500px; height: 24px;" maxlength="149" onKeyUp="javascript:somenteLetras(this); formataTexto(this);" value="${cliFor.nomeCompleto }">&nbsp;
		</td>		
	</tr>	
	
	<tr>
		<td>
			<font size="4" face="Times, serif" color="black">RG:</font> <b><font face="Times, serif" color="#FF0000">*</font></b>
		</td>
		<td>
			<input id="rg" name="rg" type="text" style="width:200px; height: 24px;" maxlength="10" onkeyup="somenteNumerosLetras(this); converteMaiuscula(this);" value="${cliFor.rg }">&nbsp;
		</td>
		<td>
			<font size="4" face="Times, serif" color="black">CPF:</font> <b> <font face="Times, serif" color="#FF0000">*</font> </b>
		</td>
		<td align="right">
			<input id="cpf" name="cpf" type="text" style="width:200px; height: 24px;" maxlength="14" onKeyUp="javascript:bloqueiaCpf(this);return mascara(this, '###.###.###-##', event);" value="${cliFor.cpf }">&nbsp;
		</td>
	</tr>
	
		<tr>
		<td>
			<font size="4" face="Times, serif" color="black">Endere&ccedil;o:</font> <b> <font face="Times, serif" color="#FF0000">*</font> </b>
		</td>
		<td colspan="3" align="right">
			<input id="endereco" name="endereco" type="text" style="width:500px; height: 24px;" maxlength="149" onkeyup="formataTexto(this);" value="${cliFor.endereco }">&nbsp;
		</td>		
	</tr>
	
	<tr>
		<td>
			<font size="4" face="Times, serif" color="black">Bairro:</font> <b> <font face="Times, serif" color="#FF0000">*</font> </b>
		</td>
		<td colspan="3" align="right">
			<input id="bairro" name="bairro" type="text" style="width:500px; height: 24px;" maxlength="149" onKeyUp="javascript:somenteLetras(this); formataTexto(this);" value="${cliFor.bairro }">&nbsp;
		</td>	
	</tr>
	
	<tr>
		<td>
			<font size="4" face="Times, serif" color="black">Cidade:</font> <b> <font face="Times, serif" color="#FF0000">*</font> </b>
		</td>
		<td colspan="3" align="right">
			<input id="cidade" name="cidade" type="text" style="width:500px; height: 24px;" maxlength="149" onKeyUp="javascript:somenteLetras(this); formataTexto(this);" value="${cliFor.cidade }">&nbsp;
		</td>			
	</tr>
	<tr>
		<td>
			<font size="4" face="Times, serif" color="black">Estado:</font> <b> <font face="Times, serif" color="#FF0000">*</font> </b>
		</td>
		<td>
			<select size="1" id="estado" name="estado" style="width:200px; height: 24px;">
			 	<option value="Selecione">Selecione</option>
			 	<c:forEach items="${estados}" var="estados">
			 		<c:if test="${estados.sigla eq cliFor.estado}">
			 			<option value="${estados.sigla }" selected="selected"> ${estados.sigla } </option>
			 		</c:if>
			 		<c:if test="${estados.sigla != cliFor.estado}">
			 			<option value="${estados.sigla }"> ${estados.sigla } </option>
			 		</c:if>					 	
			 	</c:forEach>							
			</select>
		</td>
		<td>
			<font size="4" face="Times, serif" color="black">CEP:</font> <b> <font face="Times, serif" color="#FF0000">*</font> </b>
		</td>
		<td align="right">
			<input id="cep" name="cep" type="text" style="width:200px; height: 24px;" maxlength="9" onKeyUp="javascript:bloqueiaCep(this);return mascara(this, '#####-###', event);" value="${cliFor.cep }">&nbsp;
		</td>
	</tr>
	
	<tr>
		<td>
			<font size="4" face="Times, serif" color="black">Telefone (res):</font> <b> <font face="Times, serif" color="#FF0000">*</font> </b>
		</td>
		<td>
			<input id="telefone" name="telefone" type="text" style="width:200px; height: 24px;" maxlength="13" onkeyup="bloqueiaTelefone(this);return mascara(this, '##-####-#####', event);" value="${cliFor.telefone }">&nbsp;
		</td>
		<td>
			<font size="4" face="Times, serif" color="black">Celular:</font> <b> <font face="Times, serif" color="#FF0000">*</font> </b>
		</td>
		<td align="right">
			<input id="celular" name="celular" type="text" style="width:200px; height: 24px;" maxlength="13" onkeyup="bloqueiaTelefone(this);return mascara(this, '##-####-#####', event);" value="${cliFor.celular }">&nbsp;
		</td>	
	</tr>
	
	<tr>
		<td>
			<font size="4" face="Times, serif" color="black">Radio:</font>
		</td>
		<td>
			<input id="radio" name="radio" type="text" maxlength="13" style="width:200px; height: 24px;" onkeyup="converteMaiuscula(this);" value="${cliFor.radio }">&nbsp;
		</td>
		<td>
			<font size="4" face="Times, serif" color="black">Status:</font> <b> <font face="Times, serif" color="#FF0000">*</font> </b>
		</td>
		<td>			
			<input type="radio" id="ativo" name="status" value="ativo" checked="checked"> <font size="4" face="Times, serif" color="black">Ativo</font>
			&nbsp; &nbsp;
			<input type="radio" id="inativo" name="status" value="inativo"> <font size="4" face="Times, serif" color="black">Inativo</font>
		</td>			
	</tr>
	
	<tr>
		<td>
			<font size="4" face="Times, serif" color="black">E-Mail:</font>
		</td>
		<td colspan="3">
			<input id="email" name="email" type="text" maxlength="249" style="width:500px; height: 24px;" onkeyup="converteMinuscula(this);" value="${cliFor.email }">&nbsp;
		</td>		
	</tr>
	
	<tr>
		<td>
			<font size="4" face="Times, serif" color="black">Data Dadastro:</font> <b> <font face="Times, serif" color="#FF0000">*</font> </b>
		</td>
		<td>			
			<input type="text" style="width:200px; height: 24px;" id="dataCadastro" name="dataCadastro" onblur="validaData(this)" onKeyUp="bloqueiaData(this);return mascara(this, '##/##/####', event);"
				maxlength="10" value="<fmt:formatDate value="${cliFor.dataCadastro.time}" pattern="dd/MM/yyyy"/>" />			
		</td>		
	</tr>
	
	<tr>
		<td colspan="4" align="right">
			<a href="#dialog1" name="modal" style="text-decoration:none" onclick="$('html, body').animate({ scrollTop: 0 }, 'slow'); adicionar();" >
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

<!-- M�scara para cobrir a tela -->
  <div id="mask"></div>

</div>

<br />
<br />



<!-- Layout -->		
<c:import url="../layout/rodape.jsp" />