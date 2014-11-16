<html>
	<head>
		<!-- CSS DO MENU -->
		<style>
			body{
				background: #eee url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAUAAAAFCAMAAAC6sdbXAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAAZQTFRF3d3d////riJKgAAAAAJ0Uk5T/wDltzBKAAAAFUlEQVR42mJgBAEGGMmAxAYCgAADAAGGABmnk/7aAAAAAElFTkSuQmCC);
				
			}
			
			h2, p {
				text-align: center;
				color: #444;
				text-shadow: 0 1px 0 #fff;	
			}
		</style>
	
		<!-- CSS PARA AREDONDAR A DIV -->
		<style>
			.RoundedCorner {
				bbackground: ttransparent;

				font-family:Verdana, Arial, Helvetica, sans-serif;
				color:#666666;
				font-size:12px;
			}

			.RoundedCorner .b1, .RoundedCorner .b2, .RoundedCorner .b3, .RoundedCorner .b4,.RoundedCorner .b2Top, .RoundedCorner .b3Top, .RoundedCorner .b4Top,
			.RoundedCorner .b1b, .RoundedCorner .b2b, .RoundedCorner .b3b, .RoundedCorner .b4b {
				display:block;
				overflow:hidden;
				font-size:1px;
			}

			.RoundedCorner .b1, .RoundedCorner .b2, .RoundedCorner .b3,
			.RoundedCorner .b2Top, .RoundedCorner .b3Top,
			.RoundedCorner .b1b, .RoundedCorner .b2b, .RoundedCorner .b3b {
				height:1px;
			}

			.RoundedCorner .b2, .RoundedCorner .b3, .RoundedCorner .b4 {
				background:#FFFFFF;
				border-left:1px solid #999999;
				border-right:1px solid #999999;
			}

			.RoundedCorner .b1 {
				margin:0 5px; background:#999999;
			}

			.RoundedCorner .b2,.RoundedCorner .b2Top {
				margin:0 3px;
				border-width:0 2px;
			}

			.RoundedCorner .b3,.RoundedCorner .b3Top {
				margin:0 2px;
			}

			.RoundedCorner .b4,.RoundedCorner .b4Top {
				height:2px;
				margin:0 1px;
			}

			.RoundedCorner .RoundedCornerTitle{
				padding-left:5px;
				padding-right:5px;
				padding-top:0;
				padding-bottom:3;
				display:block;
				background:#FFFFFF;
				border-left:1px solid #999999;
				border-bottom:1px solid #999999;
				border-right:1px solid #999999;
				background:#D4E6FC;
				font-family:Verdana, Arial, Helvetica, sans-serif;
				font-size:14px;
				color:#224499;
				font-weight:bold;
			}

			.RoundedCorner .RoundedCornerContent {
				display:block;
				background:#FFFFFF;
				border-left:1px solid #999999;
				border-right:1px solid #999999;
			}

			.RoundedCorner .b2Top, .RoundedCorner .b3Top, .RoundedCorner .b4Top {
				background:#D4E6FC;
				border-left:1px solid #999999;
				border-right:1px solid #999999;
			}
		</style>
		
		<!-- EFEITOS DO INPUT TYPE TEXT E PASSWORD -->
		<style type="text/css">
			input[type=text]{ 
				border-radius:4px;
				-moz-border-radius:4px;
				-webkit-border-radius:4px;
				box-shadow: 1px 1px 2px #333333;
				-moz-box-shadow: 1px 1px 2px #333333;
				-webkit-box-shadow: 1px 1px 2px #333333;
				background: -moz-linear-gradient(center top , #FFFFFF, #EEEEEE 1px, #FFFFFF 25px) repeat scroll 0 0 transparent;
				-moz-box-shadow: 0 0 8px rgba(0, 0, 0, 0.1);
				
				height: 24px;
				border:1px solid #000000;
				
			}
			
			input[type=password]{ 
				border-radius:4px;
				-moz-border-radius:4px;
				-webkit-border-radius:4px;
				box-shadow: 1px 1px 2px #333333;
				-moz-box-shadow: 1px 1px 2px #333333;
				-webkit-box-shadow: 1px 1px 2px #333333;
				background: -moz-linear-gradient(center top , #FFFFFF, #EEEEEE 1px, #FFFFFF 25px) repeat scroll 0 0 transparent;
				-moz-box-shadow: 0 0 8px rgba(0, 0, 0, 0.1);
				
				height: 24px;
				border:1px solid #000000;
				
			}			

			input[type=text]:hover, input[type=password]:hover{ 
					 background: #ffffff; border:1px solid #000000;
			}

		</style>		

		<title>
			Safety &#38; Car
		</title>
		
	</head>		
	
	<body onLoad="document.forms[0].elements[0].focus();">
	<div>
		<br/>
		<!-- LOGOS -->
				
		
		<!-- ESPACO ENTRE O LOGO E O MENU -->
		<font size="1" color="black">
			&nbsp;
		</font>		
	
		<div align="center">
			<font size="5" face="Times, serif" color="black"><b><i>Safety &#38; Car</i></b></font>	
		</div>
		
		<br/>
		
		<!-- CONTEUDO DO SISTEMA -->
		<div>
			<table align="center" valign="top" border="0" cellpadding="0" cellspacing="0" >
				<tr>
					<td>
						<div class="RoundedCorner"  style="width:360px;">
							<b class="b1"></b>
							<b class="b2"></b>
							<b class="b3"></b>
							<b class="b4"></b>
							<div class="RoundedCornerContent" >
								<br/><br/>
								<!-- CONTEUDO DO SISTEMA -->
								
<!-- ################################################################################################################################################## -->	

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

								
<form action="efetuaLogin" method="get">

				
<table border="0" cellpadding="3" cellspacing="1" align="center">

	<c:if test="${erroLoginSenha eq true}">
		<tr>
			<td colspan="3" align="center">
				<blink> <font size="3" face="Times, serif" color="red"><i>Login e&frasl;ou Senha inv&aacute;lidos</i></font> </blink>
			</td>				
		</tr>
	</c:if>
	
	<c:if test="${blqLogin eq true}">
		<tr>
			<td colspan="3" align="center">
				<blink> <font size="3" face="Times, serif" color="red"><i>Login Bloqueado. Contate o Administrador</i></font> </blink>
			</td>			
		</tr>
	</c:if>	
	
	<c:if test="${erroSenhasDiferentes eq true}">
		<tr>
			<td colspan="3" align="center">
				<blink> <font size="3" face="Times, serif" color="red"><i>As senhas novas devem ser iguais</i></font> </blink>
			</td>			
		</tr>
	</c:if>	
	
	<c:if test="${erroSenhaPequena eq true}">
		<tr>
			<td colspan="3" align="center">
				<blink> <font size="3" face="Times, serif" color="red"><i>A senha deve ser conter 4 caracteres ou mais</i></font> </blink>
			</td>			
		</tr>
	</c:if>

	<tr>
		<td>
			<font size="4" face="Times, serif" color="black">Login:</font>
		</td>
		<td>
			<input id="login" name="login" type="text" style="width:200px; height: 24px;" maxlength="24" value="${login}">
		</td>
		<td width="20">
			&nbsp;
		</td>
	</tr>	
	
	<c:if test="${alteraSenha eq false || empty alteraSenha }">
		<tr>
			<td>
				<font size="4" face="Times, serif" color="black">Senha:</font>
			</td>
			<td>
				<input id="senha" name="senha" type="password" style="width:200px;" maxlength="80" value="">
			</td>
			<td width="20">
				&nbsp;
			</td>	
		</tr>
	</c:if>
	
	<c:if test="${alteraSenha eq true}">
		<tr>
			<td>
				<font size="4" face="Times, serif" color="black">Senha Atual:</font>
			</td>
			<td>
				<input id="senha" name="senha" type="password" style="width:200px;" maxlength="80" value="">
			</td>
			<td width="20">
				&nbsp;
			</td>	
		</tr>
		
		<tr>
			<td>
				<font size="4" face="Times, serif" color="black">Senha Nova:</font>
			</td>
			<td>
				<input id="senhaNova" name="senhaNova" type="password" style="width:200px;" maxlength="80" value="">
			</td>
			<td width="20">
				&nbsp;
			</td>	
		</tr>
		
		<tr>
			<td>
				<font size="4" face="Times, serif" color="black">Confirme Senha Nova:</font>
			</td>
			<td>
				<input id="senhaNovaConf" name="senhaNovaConf" type="password" style="width:200px;" maxlength="80" value="">
			</td>
			<td width="20">
				&nbsp;
			</td>	
		</tr>
	</c:if>
	
	
	<tr>		
		<td colspan="2" align="right">
			<input type="image" src="/safetycar/resources/imagens/login01.png" width="25" height="25" border="0" title="Logar no Sistema" alt="Logar no Sistema"
									onMouseOver="this.src='/safetycar/resources/imagens/login02.png'"  
									onMouseOut="this.src='/safetycar/resources/imagens/login01.png'">
		</td>
		<td width="20">
			&nbsp;
		</td>
	</tr>
</table>
			
	


</form>
		
<!-- ################################################################################################################################################## -->			
								
								<!-- FINAL CONTEUDO DO SISTEMA -->
									<br/>
							</div>
							<b class="b4"></b>
							<b class="b3"></b>
							<b class="b2"></b>
							<b class="b1"></b>
						</div>
					<td>
				<tr>
			</table>
		</div>
		
	</div>
	</body>
</html>

