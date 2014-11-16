<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


 <!-- Layout -->
<c:import url="../layout/cabecalho.jsp" />

<!-- Scripts Padrões -->
<script type="text/javascript" src="/safetycar/resources/javaScript/scriptPadrao.js"></script>


<script type='text/javascript' src='/safetycar/dwr/engine.js'></script>
<script type='text/javascript' src='/safetycar/dwr/interface/lancamentoVlComissaoEVendaVeiculoAjaxDwr.js'></script>
<script type='text/javascript' src='/safetycar/dwr/util.js'></script>


<script language="JavaScript"> 
	window.onload = function() {		
		calculaTotalVeiculo();
		calculaTotalLucro();
	};
</script>

<script language="javascript">  
	function calculaTotalVeiculo(){
		if (vlCompra.value == ""){ 
			var Parametro1 = 0; 
		}else{ 
			var Parametro1 = vlCompra.value;
			Parametro1 = Parametro1.replace("R$ ", "");
			Parametro1 = Parametro1.replace(".", "");
			Parametro1 = Parametro1.replace(",", ".");
		}
				
		if (totalGastos.value == ""){ 
			var Parametro2= 0; 
		}else{ 
			var Parametro2 = totalGastos.value;
			Parametro2 = Parametro2.replace("R$ ", "");
			Parametro2 = Parametro2.replace(".", "");
			Parametro2 = Parametro2.replace(",", "."); 
		}
				
		if (vlComissao.value == ""){
			var Parametro3 = 0; 
		}else{ 
			var Parametro3 = vlComissao.value;
			Parametro3 = Parametro3.replace("R$ ", "");
			Parametro3 = Parametro3.replace(".", "");
			Parametro3 = Parametro3.replace(",", "."); 
		}
				
		if (vlBonus.value == ""){ 
			var Parametro4 = 0; 
		}else{ 
			var Parametro4 = vlBonus.value;
			Parametro4 = Parametro4.replace("R$ ", "");
			Parametro4 = Parametro4.replace(".", "");
			Parametro4 = Parametro4.replace(",", "."); 
		}
				
		var total = (parseFloat(Parametro1)+ parseFloat(Parametro2) + parseFloat( Parametro3) + parseFloat( Parametro4)).toFixed(2);
		
        total=total.replace(/\D/g,"");  //permite digitar apenas números 
		total=total.replace(/[0-9]{12}/,"inválido");   //limita pra máximo 999.999.999,99 
		total=total.replace(/(\d{1})(\d{8})$/,"$1.$2");  //coloca ponto antes dos últimos 8 digitos 
		total=total.replace(/(\d{1})(\d{5})$/,"$1.$2");  //coloca ponto antes dos últimos 5 digitos 
		total=total.replace(/(\d{1})(\d{1,2})$/,"$1,$2");        //coloca virgula antes dos últimos 2 digitos 
		totveiculo.value= total; 	
	}
</script>

<script language="javascript">  
	function calculaTotalLucro(){
		if (vlCompra.value == ""){ 
			var Parametro1 = 0; 
		}else{ 
			var Parametro1 = vlCompra.value;
			Parametro1 = Parametro1.replace("R$ ", "");
			Parametro1 = Parametro1.replace(".", "");
			Parametro1 = Parametro1.replace(",", ".");
		}
				
		if (totalGastos.value == ""){ 
			var Parametro2= 0; 
		}else{ 
			var Parametro2 = totalGastos.value;
			Parametro2 = Parametro2.replace("R$ ", "");
			Parametro2 = Parametro2.replace(".", "");
			Parametro2 = Parametro2.replace(",", "."); 
		}
				
		if (vlComissao.value == ""){
			var Parametro3 = 0; 
		}else{ 
			var Parametro3 = vlComissao.value;
			Parametro3 = Parametro3.replace("R$ ", "");
			Parametro3 = Parametro3.replace(".", "");
			Parametro3 = Parametro3.replace(",", "."); 
		}
				
		if (vlBonus.value == ""){ 
			var Parametro4 = 0; 
		}else{ 
			var Parametro4 = vlBonus.value;
			Parametro4 = Parametro4.replace("R$ ", "");
			Parametro4 = Parametro4.replace(".", "");
			Parametro4 = Parametro4.replace(",", "."); 
		}

		if (vlVenda.value == ""){ 
			var Parametro5 = 0; 
		}else{ 
			var Parametro5 = vlVenda.value;
			Parametro5 = Parametro5.replace("R$ ", "");
			Parametro5 = Parametro5.replace(".", "");
			Parametro5 = Parametro5.replace(",", "."); 
		}
		var totVeiculo = (parseFloat(Parametro1)+ parseFloat(Parametro2) + parseFloat( Parametro3) + parseFloat( Parametro4)).toFixed(2);

		var total =(Parametro5 - (parseFloat(Parametro1)+ parseFloat(Parametro2) + parseFloat( Parametro3) + parseFloat( Parametro4))).toFixed(2);
		
        total=total.replace(/\D/g,"");  //permite digitar apenas números 
		total=total.replace(/[0-9]{12}/,"inválido");   //limita pra máximo 999.999.999,99 
		total=total.replace(/(\d{1})(\d{8})$/,"$1.$2");  //coloca ponto antes dos últimos 8 digitos 
		total=total.replace(/(\d{1})(\d{5})$/,"$1.$2");  //coloca ponto antes dos últimos 5 digitos 
		total=total.replace(/(\d{1})(\d{1,2})$/,"$1,$2");        //coloca virgula antes dos últimos 2 digitos
		
		if 	(parseFloat(totVeiculo) < parseFloat(Parametro5)){			 
			lucro.value= total; 
		}else{
			if(parseFloat(totVeiculo) > parseFloat(Parametro5)){
				lucro.value= "-"+ total;
			}else{
				lucro.value= total;
			}				
		}	
	}
</script>

<script type="text/javascript">
	function adicionar(){
		var vlComissao = dwr.util.getValue("vlComissao");
		var vlBonus = dwr.util.getValue("vlBonus");
		var vlVenda = dwr.util.getValue("vlVenda");
		var idVeiculo = dwr.util.getValue("idVeiculo");
		
		lancamentoVlComissaoEVendaVeiculoAjaxDwr.addVlComissaoEVendaVeiculo(idVeiculo, vlVenda, vlComissao, vlBonus, {
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
					window.location="lancamentoVlComissaoEVendaVeiculo"; 	
				}			
			}
		});	
	    
		}  
</script>

<script>
	$(function() {
		$( "#dtLancamento" ).datepicker();
	});
</script>

<script>
	$(function() {
		$( "#dtVencimento" ).datepicker();
	});
</script>

<div align="center">
	<font size="5" face="Times, serif" color="black"><b><i>Valor Comissão e Venda do Veiculo</i></b></font>	
</div>

<br/>




<table width="98%" border="0" cellpadding="1" cellspacing="0" align="center">		
	<tr>
		<td>			
			<br />
<input type="hidden" id="idVeiculo" name="idVeiculo"  value="${veiculo.id}">	
				
<table border="0" cellpadding="3" cellspacing="1" align="center">	
<tr>
	<td colspan="4">
<form action="btnPesqVeiculoComissao">
		<table border="0" cellpadding="3" cellspacing="1" align="center">
			<tr>
				<td colspan="4" align="center">
					<font size="4" face="Times, serif" color="black"><i>Dados Veiculo</i></font>
				</td>
				<td>
					<a href="lancamentoVlComissaoEVendaVeiculo">
						<img src="/safetycar/resources/imagens/pesquisa01.png" width="20" height="20" border="0" title="Pesquisa Veiculo" alt="Pesquisa Veiculo"
									 onMouseOver="this.src='/safetycar/resources/imagens/pesquisa02.png'"  
				                     onMouseOut="this.src='/safetycar/resources/imagens/pesquisa01.png'">
					</a>
				</td>
			</tr>
			
			<tr>
				<td colspan="4">
					<table border="0" cellpadding="3" cellspacing="1" align="center">						
						<tr>
							<td>
								<font size="4" face="Times, serif" color="black">Placa:</font> 
							</td>
							<td colspan="1">
								<font size="3" face="Times, serif" color="black">${veiculo.placa }</font>					
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
								<font size="4" face="Times, serif" color="black">Modelo:</font>
							</td>
							<td>
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
					</table>	
				</td>
			</tr>				
		</table>
</form>	
	</td>
</tr>

<tr>
	<td colspan="4">
<form action="btnCadasVeiculoComissao">
		<table border="0" cellpadding="3" cellspacing="1" align="center">
			<tr>
				<td colspan="4" align="center">
					<font size="4" face="Times, serif" color="black"><i>Lan&ccedil;amento Valor Venda e Comiss&atilde;o</i></font>
				</td>
			</tr>		
			
			<tr>
				<td>
					<font size="4" face="Times, serif" color="black">Valor Compra:</font>
				</td>
				<td>
					<font size="3" face="Times, serif" color="black"><fmt:formatNumber value="${vlCompra}" type="currency"/></font>
					<input type="hidden" id="vlCompra" name="vlCompra"  onKeyUp="calculaTotalVeiculo();calculaTotalLucro();" value="<fmt:formatNumber value="${vlCompra}" type="currency"/>">
				</td>
				<td>
					<font size="4" face="Times, serif" color="black">Total Gastos:</font>
				</td>
				<td>
					<font size="3" face="Times, serif" color="black"><fmt:formatNumber value="${totalGastos}" type="currency"/></font>
					<input type="hidden" id="totalGastos" name="totalGastos"  onKeyUp="calculaTotalVeiculo();calculaTotalLucro();" value="<fmt:formatNumber value="${totalGastos}" type="currency"/>">
				</td>
			</tr>
			
			<tr>
				<td>
					<font size="4" face="Times, serif" color="black">Valor Comiss&atilde;o:</font>
				</td>
				<td>					
					<fmt:setLocale value="pt-BR" />
					<input id="vlComissao" name="vlComissao" type="text" style="width:170px;" maxlength="10" onKeyUp="moeda(this);calculaTotalVeiculo();calculaTotalLucro();" value="<fmt:formatNumber value="${bonusComissaoVenda.valorComissao}" type="currency"/>">
				</td>
				<td>
					<font size="4" face="Times, serif" color="black">Bonus:</font> 
				</td>
				<td>
					<fmt:setLocale value="pt-BR" />
					<input id="vlBonus" name="vlBonus" type="text" style="width:170px;" maxlength="10" onKeyUp="moeda(this);calculaTotalVeiculo();calculaTotalLucro();" value="<fmt:formatNumber value="${bonusComissaoVenda.valorBonus}" type="currency"/>">
				</td>				
			</tr>
			
			<tr>
				<td>
					<font size="4" face="Times, serif" color="black">Valor Venda:</font> <b><font face="Times, serif" color="#FF0000">*</font></b>
				</td>
				<td>
					<fmt:setLocale value="pt-BR" />
					<input id="vlVenda" name="vlVenda" type="text" style="width:170px;" maxlength="10" onblur="validaValorVenda();" onKeyUp="moeda(this);calculaTotalLucro();" value="<fmt:formatNumber value="${bonusComissaoVenda.valorVenda}" type="currency"/>">
				</td>
				<td colspan="2" align="right">					
					<a href="#dialog1" name="modal" style="text-decoration:none" onclick="$('html, body').animate({ scrollTop: 0 }, 'slow'); adicionar();" >
						<img src="/safetycar/resources/imagens/salvar01.png" width="20" height="20" border="0" title="Salvar" alt="Salvar"
									 onMouseOver="this.src='/safetycar/resources/imagens/salvar02.png'"  
			                         onMouseOut="this.src='/safetycar/resources/imagens/salvar01.png'">
					</a>		
				</td>				
			</tr>
			
			<tr>
				<td align="center" colspan="2">
					<font size="4" face="Times, serif" color="black"><b>Total Veiculo:  R$</b></font> <input style="border-width: 0; font-size:16px; font-weight:bold;" readonly="readonly" id="totveiculo" name="totveiculo" value=" ${vlVeiculo}" >
				</td>
				<td align="center" colspan="2">
					<font size="4" face="Times, serif" color="black"><b>Lucro: R$</b></font> <input style="border-width: 0; font-size:16px; font-weight:bold;" readonly="readonly" id="lucro" name="lucro" value="${vlLucro }" >
				</td>
			</tr>			
		</table>
</form>	
	</td>
</tr>


</table>


		</td>
	</tr>
</table>

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


<!-- Layout -->		
<c:import url="../layout/rodape.jsp" />