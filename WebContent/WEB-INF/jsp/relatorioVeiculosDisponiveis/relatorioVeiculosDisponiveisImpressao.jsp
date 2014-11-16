<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<html>

	<style type="text/css">
		.folha {
		    page-break-after: always;
		}
		
		thead { display: table-header-group; }
   		tfoot { display: table-footer-group; }	
	</style>
	
	<script language="JavaScript"> 
		window.onload = function() { 
			window.print(); 
		};
	</script>


	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		
		<title>
			Safety &#38; Car - Relat&oacute;rio Veiculos Disponiveis para Venda 
		</title>
	</head>
	
	
	<body>
	
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>		
		
		<div>
			<table align="center" valign="top" border="0" cellpadding="0" cellspacing="0" width="960px">
				<thead>
				<tr>
					<td>						
						<table align="center" border="0" cellpadding="0" cellspacing="0" width="958px">
							<tr>
								<td>
									<img align="left" width="200" height="50" src="/safetycar/resources/imagens/logo_Evolution.png"/>
								<td>								
							</tr>			
						</table>							
					<td>
				<tr>
				<tr>
					<td align="center">
						<font size="5" face="Times, serif" color="black"><b><i>Relat&oacute;rio Veiculos Disponiveis para Venda</i></b></font>						
					</td>
				</tr>				
				</thead>
			
				
				<tbody>
				<tr>
					<td align="right">
						<a href='javascript:;' onClick='window.print();return false'>
							<img src="/safetycar/resources/imagens/imprimir01.png" width="20" height="20" border="0" title="Imprimir" alt="Imprimir"
										 onMouseOver="this.src='/safetycar/resources/imagens/imprimir02.png'"  
					                     onMouseOut="this.src='/safetycar/resources/imagens/imprimir01.png'">
						</a>
					</td>
				</tr>				
				<tr>
					<td>						
						<c:forEach items="${veiculosDispVenda}" var="veiculo" varStatus="id">	
							
							<c:if test="${id.count % 6 == 0}">								
								<div class="folha">																				
							</c:if>
							<c:if test="${id.count < 5 && id.count > 5}">
								<div>
							</c:if>
											
							<table align="center" valign="top" border=1 style="border-collapse: collapse" width="99%">
								<tr>
									<td nowrap width="50px">
										<font size="4" face="Times, serif" color="black">Placa:</font>
									</td>
									<td width="200px">
										<font size="3" face="Times, serif" color="black">
											${veiculo.placa }
										</font>
									</td>
									<td nowrap width="50px">
										<font size="4" face="Times, serif" color="black">Marca:</font>
									</td>
									<td width="200px">
										<font size="3" face="Times, serif" color="black">
											${veiculo.marca }
										</font>
									</td>
									<td nowrap width="50px">
										<font size="4" face="Times, serif" color="black">Cor:</font>
									</td>
									<td>
										<font size="3" face="Times, serif" color="black">
											${veiculo.cor }
										</font>
									</td>														
								</tr>
								<tr>
									<td nowrap>
										<font size="4" face="Times, serif" color="black">Ano Fab/Mod:</font>
									</td>
									<td>
										<font size="3" face="Times, serif" color="black">
											${veiculo.anoFab}/${veiculo.anoMod}
										</font>
									</td>
									<td nowrap>
										<font size="4" face="Times, serif" color="black">Combustivel:</font>
									</td>
									<td>
										<font size="3" face="Times, serif" color="black">
											${veiculo.combustivel }
										</font>
									</td>
									<td nowrap>
										<font size="4" face="Times, serif" color="black">Km:</font>
									</td>
									<td>
										<font size="3" face="Times, serif" color="black">
											${veiculo.km }
										</font>
									</td>
								</tr>
								<tr>
									<td nowrap>
										<font size="4" face="Times, serif" color="black">Modelo:</font>
									</td>
									<td colspan="7">
										<font size="3" face="Times, serif" color="black">
											${veiculo.modelo }
										</font>
									</td>																
								</tr>
								<tr>
									<td nowrap>
										<font size="4" face="Times, serif" color="black">Vers&atilde;o:</font>
									</td>
									<td colspan="7">
										<font size="3" face="Times, serif" color="black">
											${veiculo.versao }
										</font>
									</td>	
								</tr>
								<tr>
									<td nowrap valign="top">
										<font size="4" face="Times, serif" color="black">Opcionais:</font>
									</td>
									<td colspan="7">
										<font size="3" face="Times, serif" color="black">
											${veiculo.opcionais }
										</font>
									</td>							
								</tr>
								<tr>
									<td nowrap>
										<font size="4" face="Times, serif" color="black">Valor Venda:</font>
									</td>
									<td colspan="7">
										<font size="3" face="Times, serif" color="black">
											<strong>
												${veiculo.valorVenda }
											</strong>
										</font>
									</td>							
								</tr>
							</table>
							<br/>
							</div>
						</c:forEach>					
					</td>
				</tr>
				</tbody>
			</table>
		</div>
	
	</body>
</html>