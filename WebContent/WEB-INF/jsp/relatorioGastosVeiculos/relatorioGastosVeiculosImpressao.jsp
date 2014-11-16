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
			Safety &#38; Car - Relat&oacute;rio Gastos Veiculos 
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
						<font size="5" face="Times, serif" color="black"><b><i>Relat&oacute;rio Gastos Veiculos</i></b></font>						
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
						<div style="width: 930px" >		
						
						<t:paginacao id="tabela_AltCF" lista="${itensRelatorio}" semPaginacao="true">
							<jsp:attribute name="cabecalho">
								<th nowrap><b>Placa</b></th>
								<th nowrap><b>Marca</b></th>
								<th nowrap><b>Modelo</b></th>
								<th nowrap><b>N&ordm; Documento</b></th>
								<th nowrap><b>Data Gasto</b></th>			
								<th nowrap><b>Nome Fornecedor</b></th>
								<th nowrap><b>Descri&ccedil;&atilde;o</b></th>
								<th nowrap><b>Valor</b></th>					
							</jsp:attribute>	
							<jsp:attribute name="linha">				
								<td>
									${item.placa}
								</td>
								
								<td align="center">
									${item.marca}
								</td>
								
								<td align="center">
									${item.modelo}
								</td>
								
								<td align="center">
									${item.nDocumento}
								</td>				
								
								<td align="center">
									<fmt:formatDate value="${item.dtGasto.time }" pattern="dd/MM/yyyy"/>
								</td>	
								
								<td align="center">
									${item.nomeFornecedor}
								</td>
								
								<td align="center">
									${item.descricaoGasto}
								</td>			
										
								
								<td align="center" nowrap>	
									${item.valorGasto }
								</td>		
								
							</jsp:attribute>
							<jsp:attribute name="link">
								javascript: paginar('gastosVeiculos', '${tabela}', ${pagina});
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