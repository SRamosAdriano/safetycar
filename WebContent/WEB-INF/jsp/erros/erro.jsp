<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

 <!-- Layout -->
<c:import url="../layout/cabecalho.jsp" />


<div align="center">	
	<font size="5" face="Times, serif" color="red"><b><i>Erro Inesperado!</i></b></font>	
</div>

<div>
	<table width="98%" border="0" cellpadding="1" cellspacing="0" align="center">		
		<tr>
			<td>			
				<br />	
		<table border="0" cellpadding="3" cellspacing="1" align="center" >
			<tr>
				<td rowspan="2">
					<img src='/safetycar/resources/imagens/erroInesperado.png' width='80' height='60' border='0'/>
				</td>
				<td align="center">
					<font size="3" face="Times, serif" color="black">
						Ocorreu um erro inesperado no sistema.
					</font>				
				</td>				
			</tr>
			
			<tr>
				<td align="center">
					<font size="3" face="Times, serif" color="black">						
						Caso o problema persista entre em contato com o Administrador do sistema.
					</font>				
				</td>				
			</tr>				
		</table>			
			</td>		
		</tr>
	</table>

</div>





<br />
<br />

<!-- Layout -->		
<c:import url="../layout/rodape.jsp" />