<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<html>

	<script language="JavaScript"> 
		window.onload = function() { 
			window.print(); 
		};
	</script>
	
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	
	<title>
		Safety &#38; Car - Check List Veiculo 
	</title>
</head>

<body>

<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

	

<table width="98%" border="0" cellpadding="1" cellspacing="0" align="center">	
	<tr>
		<td>			
				
<table border="0" cellpadding="3" cellspacing="1" align="center">

	<tr>
		<td colspan="4">						
			<table align="left" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td>
						<img align="left" width="200" height="50" src="/safetycar/resources/imagens/logo_Evolution.png"/>
					<td>								
				</tr>			
			</table>							
		<td>
	<tr>

	<tr>
		<td colspan="4" align="center">
			<b>Check List Veiculo</b>				
		</td>
	</tr>
	
	<tr>		
		<td colspan="4">
			<div align="right">
				<a href='javascript:;' onClick='window.print();return false'>
					<img src="/safetycar/resources/imagens/imprimir01.png" width="20" height="20" border="0" title="Imprimir" alt="Imprimir"
								 onMouseOver="this.src='/safetycar/resources/imagens/imprimir02.png'"  
			                     onMouseOut="this.src='/safetycar/resources/imagens/imprimir01.png'">
				</a> 				
			</div>
		</td>
	</tr>


	<tr>
		<td colspan="4" align="center">
			<table width="80%" border="0" cellpadding="1" cellspacing="0" align="center">
				<tr>
					<td nowrap>
						<b>Placa:</b>&nbsp;<i>${veiculo.placa }</i>&nbsp;
					</td>
					<td nowrap>
						<b>Cor:</b>&nbsp;<i>${veiculo.cor }</i>&nbsp;			
					</td>
					<td nowrap>		
						<b>Data Entrada:</b>&nbsp;<i> <fmt:formatDate value="${veiculo.dataCadastro.time }" pattern="dd/MM/yyyy"/></i>		
					</td>											
				</tr>
				<tr>					
					<td nowrap>
						<b>Marca:</b>&nbsp;<i>${veiculo.marca }</i>&nbsp;		
					</td>
					<td colspan="2" nowrap>		
						<b>Modelo:</b>&nbsp;<i>${veiculo.modelo }</i>&nbsp;		
					</td>													
				</tr>
				
				<tr>
					<td nowrap>
						<b>Renavan:</b>&nbsp;<i>${veiculo.renavan }</i>&nbsp;
					</td>
					<td colspan="2" nowrap>		
						<b>Chassi:</b>&nbsp;<i>${veiculo.chassi }</i>			
					</td>
				</tr>
			</table>
		
		</td>	
	</tr>	

<!-- 	
	<tr>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
	</tr>
 -->
 	
	<tr>
		<td>
			Combustivel:
		</td>
		<td colspan="3">
			<img align="top" width="20" height="20" src="/safetycar/resources/imagens/checkBoxFalse.png"/> 
			Reserva
			&nbsp; &nbsp;
			<img align="top" width="20" height="20" src="/safetycar/resources/imagens/checkBoxFalse.png"/> 
			1/4
			&nbsp; &nbsp;
			<img align="top" width="20" height="20" src="/safetycar/resources/imagens/checkBoxFalse.png"/> 
			1/2
			&nbsp; &nbsp;	
			<img align="top" width="20" height="20" src="/safetycar/resources/imagens/checkBoxFalse.png"/> 
			3/4
			&nbsp; &nbsp;
			<img align="top" width="20" height="20" src="/safetycar/resources/imagens/checkBoxFalse.png"/> 
			1/1			
		</td>
	</tr>
	
	<tr>
		<td>
			Step: 
		</td> 
		<td colspan="3">
			<img align="top" width="20" height="20" src="/safetycar/resources/imagens/checkBoxFalse.png"/> 
			Conforme
			&nbsp; 
			<img align="top" width="20" height="20" src="/safetycar/resources/imagens/checkBoxFalse.png"/> 
			Não Conforme:
			&nbsp; <img align="middle" src="/safetycar/resources/imagens/tracoCheckList.png"/>		
		</td>
	</tr>
	
	<tr>
		<td>
			Macaco: 
		</td> 
		<td colspan="3">
			<img align="top" width="20" height="20" src="/safetycar/resources/imagens/checkBoxFalse.png"/> 
			Conforme
			&nbsp; 
			<img align="top" width="20" height="20" src="/safetycar/resources/imagens/checkBoxFalse.png"/> 
			Não Conforme:
			&nbsp; <img align="middle" src="/safetycar/resources/imagens/tracoCheckList.png"/>		
		</td>
	</tr>
	
	<tr>
		<td>
			Chave de rodas: 
		</td> 
		<td colspan="3">
			<img align="top" width="20" height="20" src="/safetycar/resources/imagens/checkBoxFalse.png"/> 
			Conforme
			&nbsp; 
			<img align="top" width="20" height="20" src="/safetycar/resources/imagens/checkBoxFalse.png"/> 
			Não Conforme:
			&nbsp; <img align="middle" src="/safetycar/resources/imagens/tracoCheckList.png"/>	
		</td>
	</tr>	
	
	<tr>
		<td>
			Triângulo: 
		</td> 
		<td colspan="3">
			<img align="top" width="20" height="20" src="/safetycar/resources/imagens/checkBoxFalse.png"/> 
			Conforme
			&nbsp; 
			<img align="top" width="20" height="20" src="/safetycar/resources/imagens/checkBoxFalse.png"/> 
			Não Conforme:
			&nbsp; <img align="middle" src="/safetycar/resources/imagens/tracoCheckList.png"/>	
		</td>
	</tr>		
	
	<tr>
		<td>
			Pneus: 
		</td> 
		<td colspan="3">
			<img align="top" width="20" height="20" src="/safetycar/resources/imagens/checkBoxFalse.png"/> 
			Conforme
			&nbsp; 
			<img align="top" width="20" height="20" src="/safetycar/resources/imagens/checkBoxFalse.png"/> 
			Não Conforme:
			&nbsp; <img align="middle" src="/safetycar/resources/imagens/tracoCheckList.png"/>	
		</td>
	</tr>
	
	<tr>
		<td>
			Freio: 
		</td> 
		<td colspan="3">
			<img align="top" width="20" height="20" src="/safetycar/resources/imagens/checkBoxFalse.png"/> 
			Conforme
			&nbsp; 
			<img align="top" width="20" height="20" src="/safetycar/resources/imagens/checkBoxFalse.png"/> 
			Não Conforme:
			&nbsp; <img align="middle" src="/safetycar/resources/imagens/tracoCheckList.png"/>		
		</td>
	</tr>
	
	<tr>
		<td>
			Freio de Mão: 
		</td> 
		<td colspan="3">
			<img align="top" width="20" height="20" src="/safetycar/resources/imagens/checkBoxFalse.png"/> 
			Conforme
			&nbsp; 
			<img align="top" width="20" height="20" src="/safetycar/resources/imagens/checkBoxFalse.png"/> 
			Não Conforme:
			&nbsp; <img align="middle" src="/safetycar/resources/imagens/tracoCheckList.png"/>
		</td>
	</tr>
	
	<tr>
		<td>
			Maçanetas: 
		</td> 
		<td colspan="3">
			<img align="top" width="20" height="20" src="/safetycar/resources/imagens/checkBoxFalse.png"/> 
			Conforme
			&nbsp; 
			<img align="top" width="20" height="20" src="/safetycar/resources/imagens/checkBoxFalse.png"/> 
			Não Conforme:
			&nbsp; <img align="middle" src="/safetycar/resources/imagens/tracoCheckList.png"/>	
		</td>
	</tr>
	
	<tr>
		<td>
			Chaves:
		</td> 
		<td colspan="3">
			<img align="top" width="20" height="20" src="/safetycar/resources/imagens/checkBoxFalse.png"/> 
			Conforme
			&nbsp; 
			<img align="top" width="20" height="20" src="/safetycar/resources/imagens/checkBoxFalse.png"/> 
			Não Conforme:
			&nbsp; <img align="middle" src="/safetycar/resources/imagens/tracoCheckList.png"/>
		</td>
	</tr>
	
	<tr>
		<td>
			Buzina:
		</td> 
		<td colspan="3">
			<img align="top" width="20" height="20" src="/safetycar/resources/imagens/checkBoxFalse.png"/> 
			Conforme
			&nbsp; 
			<img align="top" width="20" height="20" src="/safetycar/resources/imagens/checkBoxFalse.png"/> 
			Não Conforme:
			&nbsp; <img align="middle" src="/safetycar/resources/imagens/tracoCheckList.png"/>
		</td>
	</tr>	
	
	<tr>
		<td>
			Faróis:
		</td> 
		<td colspan="3">
			<img align="top" width="20" height="20" src="/safetycar/resources/imagens/checkBoxFalse.png"/> 
			Conforme
			&nbsp; 
			<img align="top" width="20" height="20" src="/safetycar/resources/imagens/checkBoxFalse.png"/> 
			Não Conforme:
			&nbsp; <img align="middle" src="/safetycar/resources/imagens/tracoCheckList.png"/>
		</td>
	</tr>	
	
	<tr>
		<td>
			Luz de Ré:
		</td> 
		<td colspan="3">
			<img align="top" width="20" height="20" src="/safetycar/resources/imagens/checkBoxFalse.png"/> 
			Conforme
			&nbsp; 
			<img align="top" width="20" height="20" src="/safetycar/resources/imagens/checkBoxFalse.png"/> 
			Não Conforme:
			&nbsp; <img align="middle" src="/safetycar/resources/imagens/tracoCheckList.png"/>
		</td>
	</tr>
	
	<tr>
		<td>
			Luz de Freio:
		</td> 
		<td colspan="3">
			<img align="top" width="20" height="20" src="/safetycar/resources/imagens/checkBoxFalse.png"/> 
			Conforme
			&nbsp; 
			<img align="top" width="20" height="20" src="/safetycar/resources/imagens/checkBoxFalse.png"/> 
			Não Conforme:
			&nbsp; <img align="middle" src="/safetycar/resources/imagens/tracoCheckList.png"/>
		</td>
	</tr>
	
	<tr>
		<td>
			Setas: 
		</td> 
		<td colspan="3">
			<img align="top" width="20" height="20" src="/safetycar/resources/imagens/checkBoxFalse.png"/> 
			Conforme
			&nbsp; 
			<img align="top" width="20" height="20" src="/safetycar/resources/imagens/checkBoxFalse.png"/> 
			Não Conforme:
			&nbsp; <img align="middle" src="/safetycar/resources/imagens/tracoCheckList.png"/>
		</td>
	</tr>
	
	<tr>
		<td>
			Tapetes:
		</td> 
		<td colspan="3">
			<img align="top" width="20" height="20" src="/safetycar/resources/imagens/checkBoxFalse.png"/> 
			Conforme
			&nbsp; 
			<img align="top" width="20" height="20" src="/safetycar/resources/imagens/checkBoxFalse.png"/> 
			Não Conforme:
			&nbsp; <img align="middle" src="/safetycar/resources/imagens/tracoCheckList.png"/>
		</td>
	</tr>
	
	<tr>
		<td>
			Bancos: 
		</td> 
		<td colspan="3">
			<img align="top" width="20" height="20" src="/safetycar/resources/imagens/checkBoxFalse.png"/> 
			Conforme
			&nbsp; 
			<img align="top" width="20" height="20" src="/safetycar/resources/imagens/checkBoxFalse.png"/> 
			Não Conforme:
			&nbsp; <img align="middle" src="/safetycar/resources/imagens/tracoCheckList.png"/>
		</td>
	</tr>
	
	<tr>
		<td>
			Suspensão: 
		</td> 
		<td colspan="3">
			<img align="top" width="20" height="20" src="/safetycar/resources/imagens/checkBoxFalse.png"/> 
			Conforme
			&nbsp; 
			<img align="top" width="20" height="20" src="/safetycar/resources/imagens/checkBoxFalse.png"/> 
			Não Conforme:
			&nbsp; <img align="middle" src="/safetycar/resources/imagens/tracoCheckList.png"/>
		</td>
	</tr>	
	
	<tr>
		<td>
			Câmbio:
		</td> 
		<td colspan="3">
			<img align="top" width="20" height="20" src="/safetycar/resources/imagens/checkBoxFalse.png"/> 
			Conforme
			&nbsp; 
			<img align="top" width="20" height="20" src="/safetycar/resources/imagens/checkBoxFalse.png"/> 
			Não Conforme:
			&nbsp; <img align="middle" src="/safetycar/resources/imagens/tracoCheckList.png"/>
		</td>
	</tr>
	
	<tr>
		<td>
			Vidros:
		</td> 
		<td colspan="3">
			<img align="top" width="20" height="20" src="/safetycar/resources/imagens/checkBoxFalse.png"/> 
			Conforme
			&nbsp; 
			<img align="top" width="20" height="20" src="/safetycar/resources/imagens/checkBoxFalse.png"/> 
			Não Conforme:
			&nbsp; <img align="middle" src="/safetycar/resources/imagens/tracoCheckList.png"/>
		</td>
	</tr>	
	
	<tr>
		<td>
			Travas:
		</td> 
		<td colspan="3">
			<img align="top" width="20" height="20" src="/safetycar/resources/imagens/checkBoxFalse.png"/> 
			Conforme
			&nbsp; 
			<img align="top" width="20" height="20" src="/safetycar/resources/imagens/checkBoxFalse.png"/> 
			Não Conforme:
			&nbsp; <img align="middle" src="/safetycar/resources/imagens/tracoCheckList.png"/>
		</td>
	</tr>	
	
	<tr>
		<td>
			Marc Gasolina:
		</td> 
		<td colspan="3">
			<img align="top" width="20" height="20" src="/safetycar/resources/imagens/checkBoxFalse.png"/> 
			Conforme
			&nbsp; 
			<img align="top" width="20" height="20" src="/safetycar/resources/imagens/checkBoxFalse.png"/> 
			Não Conforme:
			&nbsp; <img align="middle" src="/safetycar/resources/imagens/tracoCheckList.png"/>
		</td>
	</tr>		
	
	<tr>
		<td>
			Marc Velocidade:
		</td> 
		<td colspan="3">
			<img align="top" width="20" height="20" src="/safetycar/resources/imagens/checkBoxFalse.png"/> 
			Conforme
			&nbsp; 
			<img align="top" width="20" height="20" src="/safetycar/resources/imagens/checkBoxFalse.png"/> 
			Não Conforme:
			&nbsp; <img align="middle" src="/safetycar/resources/imagens/tracoCheckList.png"/>
		</td>
	</tr>
	
	<tr>
		<td>
			Luz Painel:
		</td> 
		<td colspan="3">
			<img align="top" width="20" height="20" src="/safetycar/resources/imagens/checkBoxFalse.png"/> 
			Conforme
			&nbsp; 
			<img align="top" width="20" height="20" src="/safetycar/resources/imagens/checkBoxFalse.png"/> 
			Não Conforme:
			&nbsp; <img align="middle" src="/safetycar/resources/imagens/tracoCheckList.png"/>
		</td>
	</tr>
	
	<tr>
		<td>
			Óleo Freio:
		</td> 
		<td colspan="3">
			<img align="top" width="20" height="20" src="/safetycar/resources/imagens/checkBoxFalse.png"/> 
			Conforme
			&nbsp; 
			<img align="top" width="20" height="20" src="/safetycar/resources/imagens/checkBoxFalse.png"/> 
			Não Conforme:
			&nbsp; <img align="middle" src="/safetycar/resources/imagens/tracoCheckList.png"/>
		</td>
	</tr>		
	
	<tr>
		<td>
			Óleo Câmbio: 
		</td> 
		<td colspan="3">
			<img align="top" width="20" height="20" src="/safetycar/resources/imagens/checkBoxFalse.png"/> 
			Conforme
			&nbsp; 
			<img align="top" width="20" height="20" src="/safetycar/resources/imagens/checkBoxFalse.png"/> 
			Não Conforme:
			&nbsp; <img align="middle" src="/safetycar/resources/imagens/tracoCheckList.png"/>
		</td>
	</tr>				
	
	<tr>
		<td>
			Óleo Motor: 
		</td> 
		<td colspan="3">
			<img align="top" width="20" height="20" src="/safetycar/resources/imagens/checkBoxFalse.png"/> 
			Conforme
			&nbsp; 
			<img align="top" width="20" height="20" src="/safetycar/resources/imagens/checkBoxFalse.png"/> 
			Não Conforme:
			&nbsp; <img align="middle" src="/safetycar/resources/imagens/tracoCheckList.png"/>
		</td>
	</tr>
	
	<tr>
		<td>
			Água Radiador:
		</td> 
		<td colspan="3">
			<img align="top" width="20" height="20" src="/safetycar/resources/imagens/checkBoxFalse.png"/> 
			Conforme
			&nbsp; 
			<img align="top" width="20" height="20" src="/safetycar/resources/imagens/checkBoxFalse.png"/>
			Não Conforme:
			&nbsp; <img align="middle" src="/safetycar/resources/imagens/tracoCheckList.png"/>
		</td>
	</tr>
	
	<tr>
		<td>
			Tampa Combustivel: 
		</td> 
		<td colspan="3">
			<img align="top" width="20" height="20" src="/safetycar/resources/imagens/checkBoxFalse.png"/> 
			Conforme
			&nbsp; 
			<img align="top" width="20" height="20" src="/safetycar/resources/imagens/checkBoxFalse.png"/> 
			Não Conforme:
			&nbsp; <img align="middle" src="/safetycar/resources/imagens/tracoCheckList.png"/>
		</td>
	</tr>	
	
	<tr>
		<td>
			Bateria: &nbsp;
		</td> 
		<td colspan="3">
			<img align="top" width="20" height="20" src="/safetycar/resources/imagens/checkBoxFalse.png"/> 
			Conforme
			&nbsp; 
			<img align="top" width="20" height="20" src="/safetycar/resources/imagens/checkBoxFalse.png"/> 
			Não Conforme:
			&nbsp; <img align="middle" src="/safetycar/resources/imagens/tracoCheckList.png"/>
		</td>
	</tr>	
	
	<tr>
		<td>
			Filtro de Ar:
		</td> 
		<td colspan="3">
			<img align="top" width="20" height="20" src="/safetycar/resources/imagens/checkBoxFalse.png"/> 
			Conforme
			&nbsp; 
			<img align="top" width="20" height="20" src="/safetycar/resources/imagens/checkBoxFalse.png"/> 
			Não Conforme:
			&nbsp; <img align="middle" src="/safetycar/resources/imagens/tracoCheckList.png"/>
		</td>
	</tr>
	
	<tr>
		<td>
			Amortecedor:
		</td> 
		<td colspan="3">
			<img align="top" width="20" height="20" src="/safetycar/resources/imagens/checkBoxFalse.png"/> 
			Conforme
			&nbsp; 
			<img align="top" width="20" height="20" src="/safetycar/resources/imagens/checkBoxFalse.png"/> 
			Não Conforme:
			&nbsp; <img align="middle" src="/safetycar/resources/imagens/tracoCheckList.png"/>
		</td>
	</tr>	
	
	<tr>
		<td>
			Extintor: &nbsp;
		</td> 
		<td colspan="3">
			<img align="top" width="20" height="20" src="/safetycar/resources/imagens/checkBoxFalse.png"/> 
			Conforme
			&nbsp; 
			<img align="top" width="20" height="20" src="/safetycar/resources/imagens/checkBoxFalse.png"/> 
			Não Conforme:
			&nbsp; <img align="middle" src="/safetycar/resources/imagens/tracoCheckList.png"/>
		</td>
	</tr>
	
	<tr>
		<td colspan="4" align="left">
			Locais Amassados: &nbsp;
		</td>		
	</tr>
	
	<tr>
		<td colspan="4" align="left">			
			<img align="middle" src="/safetycar/resources/imagens/tracoCheckListGrande.png"/>&nbsp;
			<br/>
			<img align="middle" src="/safetycar/resources/imagens/tracoCheckListGrande.png"/>&nbsp;
			<br/>
			<img align="middle" src="/safetycar/resources/imagens/tracoCheckListGrande.png"/>&nbsp;
			<br/>
			<img align="middle" src="/safetycar/resources/imagens/tracoCheckListGrande.png"/>&nbsp;
			<br/>
			<img align="middle" src="/safetycar/resources/imagens/tracoCheckListGrande.png"/>&nbsp;
			<br/>
			<img align="middle" src="/safetycar/resources/imagens/tracoCheckListGrande.png"/>&nbsp;
			<br/>
			<img align="middle" src="/safetycar/resources/imagens/tracoCheckListGrande.png"/>&nbsp;
			<br/>
		</td>		
	</tr>	
	
	<tr>
		<td colspan="4" align="left">
			Locais com Arranhões: &nbsp;
		</td>		
	</tr>
	
	<tr>
		<td colspan="4" align="left">
			<img align="middle" src="/safetycar/resources/imagens/tracoCheckListGrande.png"/>&nbsp;
			<br/>
			<img align="middle" src="/safetycar/resources/imagens/tracoCheckListGrande.png"/>&nbsp;
			<br/>
			<img align="middle" src="/safetycar/resources/imagens/tracoCheckListGrande.png"/>&nbsp;
			<br/>
			<img align="middle" src="/safetycar/resources/imagens/tracoCheckListGrande.png"/>&nbsp;
			<br/>
			<img align="middle" src="/safetycar/resources/imagens/tracoCheckListGrande.png"/>&nbsp;
			<br/>
			<img align="middle" src="/safetycar/resources/imagens/tracoCheckListGrande.png"/>&nbsp;
			<br/>
			<img align="middle" src="/safetycar/resources/imagens/tracoCheckListGrande.png"/>&nbsp;
			<br/>
		</td>		
	</tr>
	
	<tr>
		<td colspan="4" align="left">
			Observações Gerais: &nbsp;
		</td>		
	</tr>
	
	<tr>
		<td colspan="4" align="left">
			<img align="middle" src="/safetycar/resources/imagens/tracoCheckListGrande.png"/>&nbsp;
			<br/>
			<img align="middle" src="/safetycar/resources/imagens/tracoCheckListGrande.png"/>&nbsp;
			<br/>
			<img align="middle" src="/safetycar/resources/imagens/tracoCheckListGrande.png"/>&nbsp;
			<br/>
			<img align="middle" src="/safetycar/resources/imagens/tracoCheckListGrande.png"/>&nbsp;
			<br/>
			<img align="middle" src="/safetycar/resources/imagens/tracoCheckListGrande.png"/>&nbsp;
			<br/>
			<img align="middle" src="/safetycar/resources/imagens/tracoCheckListGrande.png"/>&nbsp;
			<br/>
			<img align="middle" src="/safetycar/resources/imagens/tracoCheckListGrande.png"/>&nbsp;
			<br/>
		</td>		
	</tr>
	
	<tr>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
	</tr>	
	
	<tr>
		<td align="left" colspan="4">
			<img align="middle" src="/safetycar/resources/imagens/tracoCheckList.png"/>&nbsp;
		</td>		
	</tr>
	<tr>
		<td align="left" colspan="4">
			Revisado por:
		</td>		
	</tr>
	<tr>
		<td align="left" colspan="4">
			Data:
		</td>		
	</tr>
</table>
			
		<br/>
		<br/>
		<br/>
		</td>		
	</tr>
</table>

</body>
</html>