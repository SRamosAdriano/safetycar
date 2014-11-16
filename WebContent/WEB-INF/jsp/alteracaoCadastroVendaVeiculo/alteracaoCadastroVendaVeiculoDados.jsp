<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

 <!-- Layout -->
<c:import url="../layout/cabecalho.jsp" />

<!-- Scripts Padrões -->
<script type="text/javascript" src="/safetycar/resources/javaScript/scriptPadrao.js"></script>

<link rel="stylesheet" href="/safetycar/resources/jquery/css/jquery-ui-1.8.20.custom.css" />

<script type="text/javascript" src="/safetycar/resources/jquery/js/jquery-1.7.2.js"></script>
<script type="text/javascript" src="/safetycar/resources/jquery/js/jquery.mim.js"></script>
<script type="text/javascript" src="/safetycar/resources/jquery/js/jquery.ui.datepicker.js"></script>
<script type="text/javascript" src="/safetycar/resources/jquery/js/jquery.ui.core.js"></script>
<script type="text/javascript" src="/safetycar/resources/jquery/js/jquery.ui.datepicker-pt-BR.js"></script>


<script>
	$(function() {
		$( "#dataVenda" ).datepicker();
	});
</script>

<script type='text/javascript' src='/safetycar/dwr/engine.js'></script>
<script type='text/javascript' src='/safetycar/dwr/interface/alteraVendaVeiculoAjaxDwr.js'></script>
<script type='text/javascript' src='/safetycar/dwr/util.js'></script>

<script language="JavaScript" type="text/JavaScript">
	function direcionaTelaPesquisa(){
		
		var parametros = "";
		
		parametros += "?idVeiculo=" + document.getElementById("idVeiculo").value;
		parametros += "&valorVenda=" + document.getElementById("valorVenda").value;
		parametros += "&vendedor=" + document.getElementById("vendedor").value;
		parametros += "&dataVenda=" + document.getElementById("dataVenda").value;

		window.location="alteracaoVendaVeiculoPesquisaCliente" + parametros;
	}
	
	function adicionar(){		

		var idVeiculo = dwr.util.getValue("idVeiculo");
		var idCliente = dwr.util.getValue("idCliente");			
		var valorVenda = dwr.util.getValue("valorVenda");
		var vendedor = dwr.util.getValue("vendedor");
		
		/* Data calendar */
		var dataVenda = dwr.util.getValue("dataVenda");

		alteraVendaVeiculoAjaxDwr.altVendaVeiculo(idVeiculo, idCliente, valorVenda, vendedor, dataVenda, {
			callback : function(retorno) {	 			

				if(retorno[0] == 'ERRO'){
					var c = document.getElementById("divShow");
					var table = "<table border='0'align='center' >";
					
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
					window.location="alteracaoVendaVeiculo";					
				}	 					
			}
		});	
	    
		}  
</script>


<div align="center">
	<font size="5" face="Times, serif" color="black"><b><i>Altera&ccedil;&atilde;o Venda Veiculos</i></b></font>	
</div>

<form action="vendaVeiculo" method="post">

<input type="hidden" id="idVeiculo" name="idVeiculo"  value="${veiculo.id}">


<table width="98%" border="0" cellpadding="1" cellspacing="0" align="center">	
	<tr>
		<td>			
			<br />
				
<table border="0" cellpadding="3" cellspacing="1" align="center">	
<tr>
	<td colspan="4">
		<table border="0" cellpadding="3" cellspacing="1" align="center">
			<tr>
				<td>
					<font size="4" face="Times, serif" color="black">Placa:</font>
				</td>
				<td colspan="3">
					<font size="3" face="Times, serif" color="black">${veiculo.placa }</font>										
				</td>					
			</tr>
			
			<tr>
				<td>
					<font size="4" face="Times, serif" color="black">Cidade:</font>
				</td>
				<td>
					<font size="3" face="Times, serif" color="black">${veiculo.cidade }</font>					 					
				</td>
				<td>
					<font size="4" face="Times, serif" color="black">Estado:</font>
				</td>
				<td align="left">
					<font size="3" face="Times, serif" color="black">${veiculo.estado }</font>
				</td>	
			</tr>	
		
			<tr>
				<td>
					<font size="4" face="Times, serif" color="black">Marca:</font>
				</td>
				<td>
					<font size="3" face="Times, serif" color="black">${veiculo.marca }</font> 					
				</td>
				<td>
					<font size="4" face="Times, serif" color="black">Modelo	:</font>
				</td>
				<td align="left">
					<font size="3" face="Times, serif" color="black">${veiculo.modelo }</font>					
				</td>	
			</tr>
			
			<tr>
				<td>
					<font size="4" face="Times, serif" color="black">Vers&atilde;o:</font>
				</td>
				<td colspan="3">
					<font size="3" face="Times, serif" color="black">${veiculo.versao }</font>					
				</td>		
			</tr>
			
			<tr>
				<td>
					<font size="4" face="Times, serif" color="black">Ano Fabrica&ccedil;&atilde;o:</font>
				</td>
				<td>
					<font size="3" face="Times, serif" color="black">${veiculo.anoFab }</font>								
				</td>
				<td>
					<font size="4" face="Times, serif" color="black">Ano Modelo:</font>
				</td>
				<td align="left">
					<font size="3" face="Times, serif" color="black">${veiculo.anoMod }</font>					
				</td>	
			</tr>
			
			<tr>
				<td>
					<font size="4" face="Times, serif" color="black">Renavan:</font>
				</td>
				<td>
					<font size="3" face="Times, serif" color="black">${veiculo.renavan }</font>								
				</td>
				<td>
					<font size="4" face="Times, serif" color="black">Chassi:</font>
				</td>
				<td align="left">
					<font size="3" face="Times, serif" color="black">${veiculo.chassi }</font>					
				</td>
			</tr>
			
			<tr>
				<td>
					<font size="4" face="Times, serif" color="black">Km:</font>
				</td>
				<td>
					<font size="3" face="Times, serif" color="black">${veiculo.km }</font>								
				</td>
				<td>
					<font size="4" face="Times, serif" color="black">Combustivel:</font>
				</td>
				<td align="left">
					<font size="3" face="Times, serif" color="black">${veiculo.combustivel }</font>										
				</td>		
			</tr>
			
			<tr>
				<td>
					<font size="4" face="Times, serif" color="black">Cor:</font>
				</td>
				<td>
					<font size="3" face="Times, serif" color="black">${veiculo.cor }</font>					
				</td>
				<td>
					<font size="4" face="Times, serif" color="black">Data Entrada:</font> 
				</td>
				<td align="left">
					<font size="3" face="Times, serif" color="black"><fmt:formatDate value="${veiculo.dataCadastro.time}" pattern="dd/MM/yyyy"/></font>
				</td>
			</tr>
			
			<tr>
				<td>
					<font size="4" face="Times, serif" color="black">Opcionais:</font>
				</td>
				<td colspan="3" width="100px">
					<font size="3" face="Times, serif" color="black">${veiculoOpcionais }</font>
				</td>
			</tr>
			
			<tr>
				<td nowrap>
					<font size="4" face="Times, serif" color="black">Valor Venda:</font> <b><font face="Times, serif" color="#FF0000">*</font></b>
				</td>
				<td>					
					<input id="valorVenda" name="valorVenda" type="text" style="width:200px; height: 24px;" maxlength="12" onKeyUp="moeda(this);" value="<fmt:formatNumber value="${valorVenda}" type="currency"/>">			
				</td>
				<td>
					<font size="4" face="Times, serif" color="black">Vendedor:</font> <b><font face="Times, serif" color="#FF0000">*</font></b>
				</td>
				<td>
					<select style="width:200px; height: 24px;" id="vendedor" name="vendedor">	
						<option value="Selecione">Selecione</option>
						<c:forEach items="${vendedores}" var="vendedores">
							<c:if test="${vendedor eq vendedores.id}">
								<option selected="selected" value = "${vendedores.id}"> ${vendedores.nome } </option>
							</c:if>
							<c:if test="${vendedor != vendedores.id}">
								<option value="${vendedores.id}"> ${vendedores.nome } </option>
							</c:if>				
						</c:forEach>
					 </select>
				</td>
			</tr>
			<tr>
				<td>
					<font size="4" face="Times, serif" color="black">Data Venda:</font> <b><font face="Times, serif" color="#FF0000">*</font></b>
				</td>
				<td>
					<input id="dataVenda" name="dataVenda" type="text" style="width:200px; height: 24px;" maxlength="10" onblur="validaData(this)" onKeyUp="bloqueiaData(this);return mascara(this, '##/##/####', event);"  
									       value="${dataVenda}" />
				</td>
			</tr>
		</table>
	</td>
</tr>

<tr>
	<td colspan="4" align="center">
		<br/>
		<font size="4" face="Times, serif" color="black"><i>Pesquisa Cliente</i></font>
	</td>
	<td align="right">
		<br/>
		<a href="#" onclick="direcionaTelaPesquisa();">
			<img src="/safetycar/resources/imagens/pesquisa01.png" width="20" height="20" border="0" title="Pesquisar Cliente" alt="Pesquisar Cliente"
						 onMouseOver="this.src='/safetycar/resources/imagens/pesquisa02.png'"  
	                     onMouseOut="this.src='/safetycar/resources/imagens/pesquisa01.png'">	        
		</a>
	</td>
</tr>

<c:if test="${exibeCliente eq true}">
	<tr id="dadosCliente">
		<td colspan="4">
			<input type="hidden" id="idCliente" name="idCliente" value="${cliFor.id }">
		
			<table border="0" cellpadding="3" cellspacing="1" align="center">
				<tr>
					<td>
						<font size="4" face="Times, serif" color="black">Nome Completo:</font>
					</td>
					<td colspan="3">
						<font size="4" face="Times, serif" color="black"><i> ${cliFor.nomeCompleto } </i></font>
					</td>		
				</tr>
				
				<tr>
					<td>
						<font size="4" face="Times, serif" color="black">RG:</font>
					</td>
					<td>
						<font size="4" face="Times, serif" color="black"><i> ${cliFor.rg } </i></font>
					</td>
					<td>
						<font size="4" face="Times, serif" color="black">CPF:</font> 
					</td>
					<td>
						<font size="4" face="Times, serif" color="black"><i> ${cliFor.cpf } </i></font>
					</td>
				</tr>
				
				<tr>
					<td>
						<font size="4" face="Times, serif" color="black">Endere&ccedil;o:</font> 
					</td>
					<td colspan="3">
						<font size="4" face="Times, serif" color="black"><i> ${cliFor.endereco } </i></font>
					</td>		
				</tr>
				
				<tr>
					<td>
						<font size="4" face="Times, serif" color="black">Bairro:</font> 
					</td>
					<td colspan="3">
						<font size="4" face="Times, serif" color="black"><i> ${cliFor.bairro } </i></font>
					</td>	
				</tr>
				
				<tr>
					<td>
						<font size="4" face="Times, serif" color="black">Cidade:</font> 
					</td>
					<td colspan="3">
						<font size="4" face="Times, serif" color="black"><i> ${cliFor.cidade } </i></font>
					</td>	
				</tr>
				
				<tr>
					<td>
						<font size="4" face="Times, serif" color="black">Estado:</font> 
					</td>
					<td>
						<font size="4" face="Times, serif" color="black"><i> ${cliFor.estado } </i></font>
					</td>
					<td>
						<font size="4" face="Times, serif" color="black">Cep:</font> 
					</td>
					<td>
						<font size="4" face="Times, serif" color="black"><i> ${cliFor.cep } </i></font>
					</td>
				</tr>
				
				<tr>
					<td>
						<font size="4" face="Times, serif" color="black">Telefone (res):</font> 
					</td>
					<td>
						<font size="4" face="Times, serif" color="black"><i> ${cliFor.telefone } </i></font>
					</td>
					<td>
						<font size="4" face="Times, serif" color="black">Celular:</font> 
					</td>
					<td>
						<font size="4" face="Times, serif" color="black"><i> ${cliFor.celular } </i></font>
					</td>	
				</tr>
				
				<tr>
					<c:if test="${!empty cliFor.radio}">
						<td>
							<font size="4" face="Times, serif" color="black">R&aacute;dio:</font>
						</td>
						<td>
							<font size="4" face="Times, serif" color="black"><i> ${cliFor.radio } </i></font>
						</td>
					</c:if>					
					<td>
						<font size="4" face="Times, serif" color="black">Status:</font> 
					</td>
					<td>
						<font size="4" face="Times, serif" color="black"><i> ${cliFor.status} </i></font>								
					</td>
				</tr>
				
				<c:if test="${!empty cliFor.email}">
					<tr>
						<td>
							<font size="4" face="Times, serif" color="black">E-mail:</font>
						</td>
						<td colspan="3">
							<font size="4" face="Times, serif" color="black"><i> ${cliFor.email } </i></font>
						</td>	
					</tr>
				</c:if>				
				
				<tr>
					<td>
						<font size="4" face="Times, serif" color="black">Data Cadastro:</font> 
					</td>
					<td>
			 			<font size="4" face="Times, serif" color="black"><i> <fmt:formatDate value="${cliFor.dataCadastro.time }" pattern="dd/MM/yyyy" /> </i></font>
					</td>	
				</tr>
			</table>
		</td>
	</tr>
	
	<tr id="btnSalvar">
		<td colspan="4" align="right">
			<a href="#dialog1" name="modal" style="text-decoration:none" onclick="$('html, body').animate({ scrollTop: 0 }, 'slow'); adicionar();" >
				<img src="/safetycar/resources/imagens/salvar01.png" width="20" height="20" border="0" title="Salvar" alt="Salvar"
							 onMouseOver="this.src='/safetycar/resources/imagens/salvar02.png'"  
	                         onMouseOut="this.src='/safetycar/resources/imagens/salvar01.png'">
			</a>
		</td>
	</tr>
	
</c:if>	

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