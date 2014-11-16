/**
 * Submete um formul�rio alterando o valor do elemento 'metodo'
 * para o valor do par�metro "metodo".
 * Usado para submiss�es de formul�rio para chamar um m�todo
 * espec�fico no servidor. 
 */
function submeterFormularioComMetodo(idFormulario, metodo, ancora) {
	var elemento = getElemento('metodo');
	elemento.value = metodo;
	submeterFormularioComAncora(idFormulario, ancora);
}

/**
 * Faz a submiss�o de um formul�rio.
 */
function submeterFormulario(idFormulario) {
	getElemento(idFormulario).submit();
}

/**
 * Faz a submiss�o de um formul�rio usando uma ancora no action.
 *
 * @param idFormulario id do formul�rio que deve ser submetido.
 * @param ancora Ancora que deve ser usada no action.
 */
function submeterFormulario(idFormulario, ancora) {
	var formulario = getElemento(idFormulario);
	
	// Retira todos os par�metros da requisi��o
	var action = formulario.action;
	if (action.indexOf("?") >= 0) {
		action = action.substring(0, action.indexOf("?"));
	}
	if (isset(ancora)) {
		action += "#" + ancora;
	}
	formulario.action = action;
	formulario.submit();
}

function paginar(idFormulario, idTabela, pagina) {
	getElemento('tabela').value = idTabela;
	getElemento('pagina').value = pagina;

	var formulario = getElemento(idFormulario);
	if (isset(idTabela)) {
		formulario.action = formulario.action + "#" + idTabela;
	}
	formulario.submit();
}

function paginarFormulario(idFormulario, paginasAtuais, ancora) {
	setPaginasAtuais(paginasAtuais);
	submeterFormularioComAncora(idFormulario, ancora);
}

function setPaginasAtuais(paginasAtuais) {
	for (chave in paginasAtuais) {
		getElemento(chave).value = paginasAtuais[chave];
	}
}

function submeterFormularioComAncora(idFormulario, ancora) {
	var formulario = getElemento(idFormulario);
	if (isset(ancora)) {
		formulario.action = formulario.action + "#" + ancora;
	}
	formulario.submit();
}

/**
 * Submete um formul�rio de pagina��o chamando o m�todo excluir.
 */
function executarExcluir(id, pagina, msg) {
	var confirmado = window.confirm(msg);
	if (confirmado) {
		getElemento('id').value = id;
		getElemento('pagina').value = pagina;
		submeterFormularioComMetodo('formulario', 'remover');
	} else {
		return false;
	}
}