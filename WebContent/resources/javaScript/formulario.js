/**
 * Submete um formulário alterando o valor do elemento 'metodo'
 * para o valor do parâmetro "metodo".
 * Usado para submissões de formulário para chamar um método
 * específico no servidor. 
 */
function submeterFormularioComMetodo(idFormulario, metodo, ancora) {
	var elemento = getElemento('metodo');
	elemento.value = metodo;
	submeterFormularioComAncora(idFormulario, ancora);
}

/**
 * Faz a submissão de um formulário.
 */
function submeterFormulario(idFormulario) {
	getElemento(idFormulario).submit();
}

/**
 * Faz a submissão de um formulário usando uma ancora no action.
 *
 * @param idFormulario id do formulário que deve ser submetido.
 * @param ancora Ancora que deve ser usada no action.
 */
function submeterFormulario(idFormulario, ancora) {
	var formulario = getElemento(idFormulario);
	
	// Retira todos os parâmetros da requisição
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
 * Submete um formulário de paginação chamando o método excluir.
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