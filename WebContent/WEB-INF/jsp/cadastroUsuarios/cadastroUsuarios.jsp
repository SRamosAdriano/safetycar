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
<script type='text/javascript' src='/safetycar/dwr/interface/cadastroUsuariosAjaxDwr.js'></script>
<script type='text/javascript' src='/safetycar/dwr/util.js'></script>

<script type="text/javascript">
	function addItems() {
		  var ai = document.getElementById("availableItems");
		  var si = document.getElementById("selectedItems");
		  for (i=0;i<ai.options.length;i++) {
			if (ai.options[i].selected) {
			  var opt = ai.options[i];
			  si.options[si.options.length] = new Option(opt.innerHTML, opt.value);
			  ai.options[i] = null;
			  i = i - 1;
			}
		  }
		}
	   
		function addAll() {
		  var ai = document.getElementById("availableItems");
		  var si = document.getElementById("selectedItems");
		  for (i=0;i<ai.options.length;i++) {
			var opt = ai.options[i];
			si.options[si.options.length] = new Option(opt.innerHTML, opt.value);
		  }
		  ai.options.length = 0;
		}
		  
		function removeItems() {
		  var ai = document.getElementById("availableItems");
		  var si = document.getElementById("selectedItems");
		  for (i=0;i<si.options.length;i++) {
			if (si.options[i].selected) {
			  var opt = si.options[i];
			  ai.options[ai.options.length] = new Option(opt.innerHTML, opt.value);
			  si.options[i] = null;
			  i = i - 1;
			}
		  }
		  sortAvailable();
		}
		
		function removeAll() {
		  var ai = document.getElementById("availableItems");
		  var si = document.getElementById("selectedItems");
		  for (i=0;i<si.options.length;i++) {
			var opt = si.options[i];
			ai.options[ai.options.length] = new Option(opt.innerHTML, opt.value);
		  }
		  si.options.length = 0;
		  sortAvailable();
		}
	   
		function moveUp() {
		  var si = document.getElementById("selectedItems");
		  var sel = si.selectedIndex;
		  if (sel > 0) {
			   var optHTML = si.options[sel].innerHTML;
			   var optVal = si.options[sel].value;
			   var opt1HTML = si.options[sel-1].innerHTML;
			   var opt1Val = si.options[sel-1].value;
			   si.options[sel] = new Option(opt1HTML,opt1Val);
			   si.options[sel-1] = new Option(optHTML,optVal);
			   si.options.selectedIndex = sel -1;
		  }
		}
	   
		function moveDown() {
		  var si = document.getElementById("selectedItems");
		  var sel = si.selectedIndex;
		  if (sel < si.options.length -1) {
			   var optHTML = si.options[sel].innerHTML;
			   var optVal = si.options[sel].value;
			   var opt1HTML = si.options[sel+1].innerHTML;
			   var opt1Val = si.options[sel+1].value;
			   si.options[sel] = new Option(opt1HTML,opt1Val);
			   si.options[sel+1] = new Option(optHTML,optVal);
			   si.options.selectedIndex = sel +1;
		  }
		}

		function sortAvailable() {
		  var ai = document.getElementById("availableItems");
		  var tmp = "";
		  for (i=0;i<ai.options.length;i++) {
			if (tmp > "") tmp +=",";
			tmp += ai.options[i].innerHTML + "~" + ai.options[i].value;
		  }
		  var atmp = tmp.split(",")
		  atmp = atmp.sort();
		  ai.options.length = 0
		  for (i=0;i<atmp.length;i++) {
			var opt = atmp[i].split("~");
			ai.options[i] = new Option(opt[0],opt[1]);
		  }
		}

		function frmSubmit() {
		  var si = document.getElementById("selectedItems");
		  	  
		  var grupos = new Array();	  
		  for (i=0;i<si.options.length;i++) {
			si.options[i].selected = true;				
			grupos[i] = si.options[i].value;
		  }
		 
		  var usuario = {		
			             nome:dwr.util.getValue("nome"),	
			             login:dwr.util.getValue("login")
				        };

		  /* Data calendar */
		  var dataCadastro = dwr.util.getValue("dataCadastro");

	 	  cadastroUsuariosAjaxDwr.addUsuarios(usuario, dataCadastro, grupos, {
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
						table += "<td> <font size='4' face='Times, serif' color='black'>" + retorno[1] + "</font> </td>";						
						table += "</tr>";
						table += "<tr>";
						table += "<td>&nbsp;</td>";
						table += "<td> <font size='4' face='Times, serif' color='black'>" + retorno[2] + "</font> </td>";
						table += "<tr>";

						/* Reseta input */					
						document.getElementById("nome").value ="";
						document.getElementById("login").value ="";					
						document.getElementById("dataCadastro").value ="";

						/* Reseta Paginas Liberadas*/
						removeAll();				  	
					}
									
		 			table += "</table>";				
		 			c.innerHTML = table;			
				}
			});	  
		}	
</script>

<div align="center">
	<font size="5" face="Times, serif" color="black"><b><i>Cadastro de Usu&aacute;rio</i></b></font>	
</div>


<form action="adicionaUsuario" method="post">

<table width="98%" border="0" cellpadding="1" cellspacing="0" align="center">	
	<tr>
		<td>			
			<br />
				
<table border="0" cellpadding="3" cellspacing="1" align="center">
	<tr>
		<td colspan="2">
			<table border="0" cellpadding="3" cellspacing="1" align="center">	
				<tr>
					<td nowrap width="10px">
						<font size="4" face="Times, serif" color="black">Nome:</font> <b><font face="Times, serif" color="#FF0000">*</font></b>
					</td>
					<td colspan="2" align="left">
						<input id="nome" name="nome" type="text" style="width:500px; height: 24px;" maxlength="149" onKeyUp="javascript:somenteLetras(this); formataTexto(this);" value="${usuario.nome }">&nbsp;
					</td>		
				</tr>	
				
				<tr>
					<td nowrap>
						<font size="4" face="Times, serif" color="black">Login:</font> <b><font face="Times, serif" color="#FF0000">*</font></b>
					</td>
					<td>
						<input id="login" name="login" type="text" style="width:200px; height: 24px;" maxlength="31" onkeyup="somenteNumerosLetrasComPontoEArroba(this); converteMinuscula(this);" value="${usuario.login }">&nbsp;
					</td>		
				</tr>
				
				
				<tr>
					<td nowrap>
						<font size="4" face="Times, serif" color="black">Data Dadastro:</font> <b> <font face="Times, serif" color="#FF0000">*</font> </b>
					</td>
					<td>			
						<input type="text" style="width:200px; height: 24px;" id="dataCadastro" name="dataCadastro" onblur="validaData(this)" onKeyUp="bloqueiaData(this);return mascara(this, '##/##/####', event);"
							maxlength="10" value="<fmt:formatDate value="${usuario.dataCadastro.time}" pattern="dd/MM/yyyy"/>" />			
					</td>		
				</tr>
			</table>
		</td>
	</tr>
		
	<tr>
		<td colspan="2">			
			<div style="width: 25%; float: left">
			</div>
			<div style="width: 400px; float: left">
				
				<div align="center">
					<font size="4" face="Times, serif" color="black"><i>Grupos Disponiveis</i></font>
				</div>				
				
				<select style="width: 390px; height: 180px;" id=availableItems multiple name=availableItems>				
				 	<c:forEach items="${gruposDisponiveis}" var="pagina">				 		
				 		<option id="${pagina.id}" value="${pagina.id}"> ${pagina.nome } </option>				 							 	
				 	</c:forEach>					 
				</select> 
			</div>
			<div style="width: 35px; float: left">				
				<!--
				<input class=btn onclick=addItems(); value=Add type=button>				 
				<input class=btn onclick=addAll(); value="Add All" type=button> 
				<input class=btn onclick=removeItems(); value=Remove type=button> 
				<input class=btn onclick=removeAll(); value="Remove All" type=button> 
				<input class=btn onclick=moveUp(); value="Move Up" type=button> 
				<input class=btn onclick=moveDown(); value="Move Down" type=button>
				<input class=btn onclick=frmSubmit(); value=Submit type=button>
				-->
				&nbsp;
				<a style="text-decoration:none" onclick="addItems();" >
					<img src="/safetycar/resources/imagens/avancar01.png" width="30" height="25" border="0" title="Adicionar" alt="Adicionar">
				</a>
				<a style="text-decoration:none" onclick="addAll();" >
					<img src="/safetycar/resources/imagens/avancar02.png" width="30" height="30" border="0" title="Adicionar Todos" alt="Adicionar Todos">
				</a>
				<a style="text-decoration:none" onclick="removeItems();" >
					<img src="/safetycar/resources/imagens/voltar01.png" width="30" height="25" border="0" title="Remover" alt="Remover">
				</a>				
				<a style="text-decoration:none" onclick="removeAll();" >
					<img src="/safetycar/resources/imagens/voltar02.png" width="30" height="30" border="0" title="Remover Todos" alt="Remover Todos">
				</a>
				<a style="text-decoration:none" onclick="moveUp();" >
					<img src="/safetycar/resources/imagens/cima01.png" width="25" height="25" border="0" title="Mover para Cima" alt="Mover para Cima">
				</a>
				<a style="text-decoration:none" onclick="moveDown();" >
					<img src="/safetycar/resources/imagens/baixo01.png" width="25" height="25" border="0" title="Mover para Baixo" alt="Mover para Baixo">
				</a>
			</div>
			<div style="width: 400px; float: left">
				
				<div align="center">
					<font size="4" face="Times, serif" color="black"><i>Grupos Liberados</i></font>
				</div>
			
				<select style="width: 390px; height: 180px;" id=selectedItems multiple size=10 name=selectedItems>
				</select> 
			</div>			
		</td>			
	</tr>
	
	<tr>
		<td colspan="4" align="right">
			<a href="#dialog1" name="modal" style="text-decoration:none" onclick="$('html, body').animate({ scrollTop: 0 }, 'slow'); frmSubmit();" >
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