<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<html>

<!-- Scripts de  paginação e liberação de tags -->
<%@taglib tagdir="/WEB-INF/tags" prefix="t" %>
<script type="text/javascript" src="/safetycar/resources/javaScript/formulario.js"></script>
<script type="text/javascript" src="/safetycar/resources/javaScript/util.js"></script>

<!-- Hiddens de  paginação -->
<input type="hidden" id="relatorio" name="relatorio" value="0">
<input type="hidden" id="metodo" name="metodo" value="paginar" />
<input type="hidden" id="pagina" name="pagina" value="" />
<input type="hidden" id="tabela" name="tabela" value="" />

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
			Safety &#38; Car - Relat&oacute;rio Lucro Veiculo 
		</title>
	</head>
	
	
	<body>
	
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>		
		
		<div>
			<table align="center" valign="top" border="0" cellpadding="0" cellspacing="0">
				<thead>				
				<tr>
					<td align="left">
					<img align="left" width="200" height="50" src="/safetycar/resources/imagens/logo_Evolution.png"/>
					</td>
				</tr>
				<tr>
					<td align="center">
						<font size="5" face="Times, serif" color="black"><b><i>Relat&oacute;rio Lucro Veiculo</i></b></font>						
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
					
						<div align="center">
						<div>		
						
						<t:paginacao id="tabela_AltCF" lista="${itensRelatorio}" semPaginacao="true">
							<jsp:attribute name="cabecalho">
								<th nowrap><b>Placa</b></th>
								<th nowrap><b>Marca</b></th>
								<th nowrap><b>Modelo</b></th>
								<th nowrap><b>Versão</b></th>
								<th nowrap><b>Data Compra</b></th>			
								<th nowrap><b>Data Venda</b></th>
								<th nowrap><b>Compra</b></th>
								<th nowrap><b>Comiss&atilde;o</b></th>
								<th nowrap><b>Gastos</b></th>
								<th nowrap><b>Venda</b></th>
								<th nowrap><b>Lucro</b></th>			
							</jsp:attribute>	
							<jsp:attribute name="linha">				
								<td>
									${item.placa}
								</td>
								
								<td align="center">
									${item.marca}
								</td>
								
								<td align="center" nowrap>
									${item.modelo}
								</td>
								
								<td align="center" nowrap>
									${item.versao}
								</td>				
								
								<td align="center">
									<fmt:formatDate value="${item.dataCompra.time }" pattern="dd/MM/yyyy"/>
								</td>	
								
								<td align="center">
									<fmt:formatDate value="${item.dataVenda.time }" pattern="dd/MM/yyyy"/>
								</td>
								
								<td align="center" nowrap>
									<fmt:formatNumber value="${item.vlCompra }" type="currency"/>
								</td>			
								
								<td align="center" nowrap>
									<fmt:formatNumber value="${item.vlComissao }" type="currency"/>			
								</td>
								
								<td align="center" nowrap>	
									<fmt:formatNumber value="${item.vlGastos }" type="currency"/>
								</td>	
								
								<td align="center" nowrap>	
									<fmt:formatNumber value="${item.vlVenda }" type="currency"/>
								</td>
								
								<td align="center" nowrap>
									<c:if test="${item.vlLucro < 0 }">
										<font color="#FF0000"> 
											<fmt:formatNumber value="${item.vlLucro }" type="currency"/>
										</font>
									</c:if>
									<c:if test="${item.vlLucro >= 0 }">
										<fmt:formatNumber value="${item.vlLucro }" type="currency"/>
									</c:if>
									
								</td>	
								
							</jsp:attribute>
							<jsp:attribute name="link">
								javascript: paginar('relLucro', '${tabela}', ${pagina});
							</jsp:attribute>
						</t:paginacao>
						</div>
						</div>
					</td>
				</tr>
				</tbody>
			</table>
		</div>
	
	</body>
</html>