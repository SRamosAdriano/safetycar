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


<link rel="stylesheet" href="/safetycar/resources/css/paginacao.css" />

<script type='text/javascript' src='/safetycar/dwr/engine.js'></script>
<script type='text/javascript' src='/safetycar/dwr/interface/cadastroLancamentoGastosVeiculoAjaxDwr.js'></script>
<script type='text/javascript' src='/safetycar/dwr/util.js'></script>

<script>
	$(function() {
		$( "#dtLancamento" ).datepicker();
	});
</script>

<script type="text/javascript">
	function removeGastoVeiculo(idGastoVeiculo, tipo){
		
		if(tipo == 'mensagem'){
			document.getElementById("idGastoVeiculo").value = idGastoVeiculo;
		}else if(tipo == 'excluir'){
			/*Caso seja confirmada a exclusão*/			
			window.location="gastosVeiculoRemoveGasto?idGastoVeiculo=" + document.getElementById("idGastoVeiculo").value + "&idVeiculo=" + document.getElementById("idVeiculo").value + "&idForDesp=" + document.getElementById("idForDesp").value;
		}		
	}

	function adicionar(){
		var gastoVeiculo = {				 
				descricao:dwr.util.getValue("descricao"),	
				NDocumento:dwr.util.getValue("nDocumento")				
		};		

		/* Data calendar */
		var dtLancamento = dwr.util.getValue("dtLancamento");		

		/* idVeiculo e idFornecedorDespesas*/
		var idVeiculo = dwr.util.getValue("idVeiculo");
		var idForDesp = dwr.util.getValue("idForDesp");

		/* Valor gasto */
		var valorGastoVeiculo = dwr.util.getValue("valor");

        cadastroLancamentoGastosVeiculoAjaxDwr.addGastoVeiculo(gastoVeiculo, dtLancamento,idVeiculo, idForDesp, valorGastoVeiculo, {
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
					/* Caso a alteração seja efetuada a busca novamente */
					window.location="gastosVeiculoSelecionadoForDesp?idVeiculo=" + idVeiculo + "&idForDesp=" + idForDesp;
										
				}	 					
			}
		});	
	}
</script>

<div align="center">
	<font size="5" face="Times, serif" color="black"><b><i>Gastos Veiculo</i></b></font>	
</div>
<form action="btnCadasForDesp">
<table width="98%" border="0" cellpadding="1" cellspacing="0" align="center">	
	<tr>
		<td>			
			<br />				
<table border="0" cellpadding="3" cellspacing="1" align="center">		
<tr>
	<td colspan="4">
	
<input type="hidden" id="idVeiculo" name="idVeiculo" value="${idVeiculo }">
<input type="hidden" id="idForDesp" name="idForDesp" value="${idForDesp }">
<input type="hidden" id="idGastoVeiculo" name="idGastoVeiculo" value="${idGastoVeiculo }">
						
		<table border="0" cellpadding="3" cellspacing="1" align="center">
			<tr>
				<td colspan="4" align="center">
					<font size="4" face="Times, serif" color="black"><i>Dados Veiculo</i></font>
				</td>
				<td>
					<a href="gastosVeiculo">
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
			
			<tr>
				<td colspan="4" align="center">
					<br/>
					<font size="4" face="Times, serif" color="black"><i>Dados Fornecedor</i></font>
				</td>
				<td align="right">
					<br/>
					<a href="gastosVeiculoSelecionaForDesp?idVeiculo=${idVeiculo}">
						<img src="/safetycar/resources/imagens/pesquisa01.png" width="20" height="20" border="0" title="Pesquisa Fornecedor" alt="Pesquisa Fornecedor"
									 onMouseOver="this.src='/safetycar/resources/imagens/pesquisa02.png'"  
				                     onMouseOut="this.src='/safetycar/resources/imagens/pesquisa01.png'">
					</a>
				</td>
			</tr>
			
			<tr>
				<td colspan="4">
					<table border="0" cellpadding="3" cellspacing="1" align="center">	
						<c:if test="${fornededorNaoSele eq true}">
							<tr>
								<td colspan="5" align="center">
									<font size="3" face="Times, serif" color="black">Nenhum fornecedor selecionado</font>
								</td>
							</tr>						
						</c:if>					
								
						<c:if test="${fornededorSele eq true}">	
							<tr>
								<td>
									<font size="4" face="Times, serif" color="black">Nome:</font>
								</td>
								<td colspan="3">
									<font size="3" face="Times, serif" color="black">${fornecedorDespesas.nome}</font>
								</td>							
							</tr>
									
							<tr>
								<td>
									<font size="4" face="Times, serif" color="black">Contato:</font>
								</td>
								<td>
									<font size="3" face="Times, serif" color="black">${fornecedorDespesas.contato }</font>
								</td>
								<c:if test="${ !empty fornecedorDespesas.telefone }">
									<td>
										<font size="4" face="Times, serif" color="black">Telefone:</font>
									</td>
									<td>
										<font size="3" face="Times, serif" color="black">${fornecedorDespesas.telefone }</font>
									</td>								
								</c:if>								
							</tr>
						</c:if>				
																
					</table>	
				</td>
			</tr>
			
			<c:if test="${fornededorSele eq true}">			
				<tr>
					<td colspan="4" align="center">
						<br/>
						<font size="4" face="Times, serif" color="black"><i>Dados do Lan&ccedil;amento</i></font>
					</td>				
				</tr>
				
				<tr>
					<td colspan="4">
						<table border="0" cellpadding="3" cellspacing="1" align="center">					
							<tr>
								<td>
									<font size="4" face="Times, serif" color="black">Descri&ccedil;&atilde;o:</font> <b><font face="Times, serif" color="#FF0000">*</font></b>
								</td>
								<td colspan="3">
									<input name="descricao" type="text" style="width:555px; height: 24px;" onKeyUp="formataTexto(this);" value="${despVeiculo.descricao }">
								</td>		
							</tr>	
							<tr>
								<td>
									<font size="4" face="Times, serif" color="black">N&ordm; Documento:</font>
								</td>
								<td>					
									<input id="nDocumento" name="nDocumento" type="text" style="width:200px; height: 24px;" maxlength="14" onKeyUp="converteMaiuscula(this);" value="${despVeiculo.nDocumento}">			
								</td>
								<td align="right">
									<font size="4" face="Times, serif" color="black">Valor:</font> <b><font face="Times, serif" color="#FF0000">*</font></b>					
								</td>
								<td align="right">
									<fmt:setLocale value="pt-BR" />
									<input name="valor" type="text" style="width:200px; height: 24px;" maxlength="14" onKeyUp="moeda(this);" value="<fmt:formatNumber value="${despVeiculo.valor}" type="currency"/>">
								</td>
							</tr>
							<tr>
								<td>
									<font size="4" face="Times, serif" color="black">Data Lan&ccedil;amento:</font> <b><font face="Times, serif" color="#FF0000">*</font></b>
								</td>
								<td>
									<input id="dtLancamento" name="dtLancamento" type="text" style="width:200px; height: 24px;" maxlength="10" onblur="validaData(this)" onKeyUp="bloqueiaData(this);return mascara(this, '##/##/####', event);"  
									       value="<fmt:formatDate value="${despVeiculo.dtLancamento.time}" pattern="dd/MM/yyyy"/>" />
								</td>
								<td colspan="2" align="right">
									<a href="#dialog2" name="modal" style="text-decoration:none" onclick="$('html, body').animate({ scrollTop: 0 }, 'slow'); adicionar();" >
										<img src="/safetycar/resources/imagens/salvar01.png" width="20" height="20" border="0" title="Adiciona Gasto Veiculo" alt="Adiciona Gasto Veiculo"
													 onMouseOver="this.src='/safetycar/resources/imagens/salvar02.png'"  
								                     onMouseOut="this.src='/safetycar/resources/imagens/salvar01.png'">
									</a>
								</td>																		
							</tr>											
						</table>	
					</td>
				</tr>			
			</c:if>						
		</table>		
	</td>
</tr>	

<tr>
	<th>&nbsp; &nbsp;</th>
	<th>&nbsp; &nbsp;</th>
	<th>&nbsp; &nbsp;</th>
	<th>&nbsp; &nbsp;</th>				
</tr>

<tr>
	<td align="center" colspan="4">
		<font size="4" face="Times, serif" color="black"><i>Lista Gastos Veiculos</i></font>
	</td>
</tr>

<c:if test="${ naoExisteGastoVeiculo eq true}">
	<tr>
		<td align="center" colspan="4">
			<font size="3" face="Times, serif" color="black">Nenhum gasto lançado para este veiculo</font>
		</td>
	</tr>
	<tr>
		<td align="center" colspan="4">
			<font size="4" face="Times, serif" color="black">Valor Veiculo: <fmt:formatNumber value="${valorVeiculo}" type="currency"/> </font>
		</td>
	</tr>
</c:if>

<c:if test="${ existeGastoVeiculo eq true}">
	<tr>
		<td colspan="4">
			<div align="center">
				<div style="overflow: auto;width: 930px" >				
					<table border="0" align="center" class="paginationTable">									
						<tr>						
							<th nowrap bgcolor="#EEEEEE"><b> Descrição </b></th>
							<th nowrap bgcolor="#EEEEEE"><b> Valor </b></th>
							<th nowrap bgcolor="#EEEEEE"><b> N&ordm; Documento </b></th>						
							<th nowrap bgcolor="#EEEEEE"><b> Nome Fornecedor </b></th>							
							<th nowrap><b> Data Lançamento </b></th>										
							<th nowrap><b> &nbsp; </b></th>
						</tr>
						
						<c:forEach items="${tabelaGastosVeiculo}" var="gastosVeiculo" varStatus="status">							
							<tr bgcolor="#${status.count % 2 == 0 ? 'E3E6EF': 'FFFFFF' }">								
								<td nowrap align="left">
									${gastosVeiculo.descricao }
								</td>
								
								<td nowrap align="left">
									<fmt:setLocale value="pt-BR" />
									<fmt:formatNumber value="${gastosVeiculo.valor}" type="currency"/>								
								</td>										
								
								<td nowrap align="left"> 
									${gastosVeiculo.NDocumento}
								</td>					
															
								
								<td nowrap align="left">								
								<c:set var="setado" value="false"/>
								<c:forEach items="${tabelaGastosVeiculoListaNomeFor}" var="nomeFor">								
									<c:if test="${nomeFor.id eq gastosVeiculo.fornecedordespesas_id }">	
										<c:if test="${setado eq false }">	
											${nomeFor.nome }
											<c:set var="setado" value="true"/>
										</c:if>								
							 		</c:if>
								</c:forEach>
								</td>									
								
								<td nowrap  align="center">
									<fmt:formatDate value="${gastosVeiculo.dtLancamento.time}" pattern="dd/MM/yyyy"/>									
								</td>
																
								<td align="center">
									<a href="#dialog1" name="modal" style="text-decoration:none" onclick="$('html, body').animate({ scrollTop: 0 }, 'slow'); removeGastoVeiculo(${gastosVeiculo.id}, 'mensagem');" >									
										<img src="/safetycar/resources/imagens/remove01.png" width="25" height="25" border="0" title="Excluir" alt="Excluir"
											 onMouseOver="this.src='/safetycar/resources/imagens/remove02.png'"  
					                         onMouseOut="this.src='/safetycar/resources/imagens/remove01.png'">
									</a>
								</td>								
							</tr>
						</c:forEach>												
					</table>
				</div>			
				
				<font size="4" face="Times, serif" color="black">Total Gastos(A): <fmt:formatNumber value="${totalGastos}" type="currency"/> </font>
				<br/>
				<fmt:setLocale value="pt-BR" />
				<font size="4" face="Times, serif" color="black">Valor Veiculo(B): <fmt:formatNumber value="${valorVeiculo}" type="currency"/> </font>			
				<br/>
				<b> <font size="4" face="Times, serif" color="black">Total Veiculo(A+B):</font> <font size="4" face="Times, serif" color="#FF0000"> 
																									<fmt:formatNumber value="${totalGastos + valorVeiculo}" type="currency"/> 
									   															</font> 
				</b>
				
			</div>
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
							Tem certeza que deseja excluir o Gasto do Veiculo?
						</font>
					</td>
				</tr>
			</table>
			
			<table align="center">
				<tr>
					<td>
						<a href="#" onclick="removeGastoVeiculo(0, 'excluir');">
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
							<img src="/safetycar/resources/imagens/aceitarNao.png" width="30" height="30" border="0" title="Não" alt="Não">
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
<div id="dialog2" class="window">
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