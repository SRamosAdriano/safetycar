<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


 <!-- Layout -->
<c:import url="../layout/cabecalho.jsp" />




<script type='text/javascript' src='/safetycar/dwr/engine.js'></script>
<script type='text/javascript' src='/safetycar/dwr/interface/usuarioAjax.js'></script>
<script type='text/javascript' src='/safetycar/dwr/util.js'></script>

<script type="text/javascript">
	function findUser() {
		var nome = document.getElementById("inputNome").value;
		usuarioAjax.find(nome, {
			callback : function(usuarioBean) {
				dwr.util.setValue('divShow', usuarioBean.id + " / "
						+ usuarioBean.nome + " - " + usuarioBean.endereco
						+ " - " + usuarioBean.telefone);
			}
		});
	}
</script>


<table>
	<tr>
		<td><label for="inputNome">Nome:</label></td>
		<td><input type="text" id="inputNome" maxlength="20" width="20px"></td>
		<td><input type="button" value="Enviar" onclick="findUser();"></td>
	</tr>
</table>



<h2>
	Janela Modal
</h2>

<ul>
	<li>
		<a href="#" onclick="$('html, body').animate({ scrollTop: $(document).height() },1500);">
			Link Normal
		</a>
	</li>
	<li>
		<a href="#dialog" name="modal" onclick="$('html, body').animate({ scrollTop: $(document).height() },1500);">
			Modal Simples
		</a>
	</li>
	<li>
		<a href="#dialog1" name="modal" onclick="$('html, body').animate({ scrollTop: 0 }, 'slow'); findUser();" >
			Modal com caixa de di�logo
		</a>
	</li>
	<li>
		<a href="#dialog2" name="modal" onclick="$('html, body').animate({ scrollTop: 0 }, 'slow');">
			Modal com Bloco de Nota
		</a>
	</li>
</ul>
<div id="boxes">

<div id="dialog" class="window">
	<a href="#" class="close">
		Fechar [X]
	</a>
	<br />
	Janela Modal Simples
	<br />  
	Aqui vai o conte�do da sua Janela Modal Simples.
</div>
  
<!-- Janela Modal com caixa de di�logo -->  
<div id="dialog1" class="window">
	<div class="RoundedCorner" style="width:460px;">
		<b class="b1"></b>
		<b class="b2"></b>
		<b class="b3"></b>
		<b class="b4"></b>
		<div class="RoundedCornerContent" >
			
			<a href="#" class="close" onclick="$('html, body').animate({ scrollTop: 0 }, 'slow');">
				Fechar [X]
			</a>
			&nbsp;
			
			<div id="divShow"></div>
		
		</div>
		<b class="b4"></b>
		<b class="b3"></b>
		<b class="b2"></b>
		<b class="b1"></b>
	</div>
							
				

</div>
<!-- Fim Janela Modal com caixa de di�logo -->  



<!-- Janela Modal com Bloco de Nota -->
<div id="dialog2" class="window">
	Ent�o?
	<br />
	Construir uma <b>Janela Modal Simples</b> com o formato que voc� quiser � f�cil!
	<br />
	Simples e totalmente personaliz�vel : ) 
	<br /><br />
	<input type="button" value="Fechar" class="close"/>
</div>
<!-- Fim Janela Modal com Bloco de Nota -->



<!-- M�scara para cobrir a tela -->
  <div id="mask"></div>

</div>

<br />
<br />




		
<BR/><BR/><BR/><BR/><BR/><BR/><BR/>aDRIANO<BR/><BR/><BR/><BR/><BR/><BR/><BR/>aDRIANO<BR/><BR/><BR/><BR/><BR/>
<BR/><BR/><BR/><BR/>aDRIANO<BR/><BR/><BR/><BR/><BR/><BR/>aDRIANO<BR/><BR/><BR/><BR/><BR/><BR/>aDRIANO
<BR/><BR/><BR/><BR/>
		





















<br>
<div id="divShow"></div>




<!-- Layout -->		
<c:import url="../layout/rodape.jsp" />