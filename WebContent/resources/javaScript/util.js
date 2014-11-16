function toggleDisplay(id) {
	var elemento = document.getElementById(id);
	if (elemento == null) {
		alert("Nao foi possivel encontrar elemento de id: " + id);
	}
	if (elemento.style.display == 'none') {
		elemento.style.display = '';
	} else {
		elemento.style.display = 'none';
	}
}

function toggleDisplayReposicionando(id, idPai) {
	reposicionar(id, idPai);
	toggleDisplay(id);
}

function getPontoCentral() {
	return {x: document.width / 2, y: document.height / 2};
}

function getElemento(id) {
	var elemento = document.getElementById(id);
	return elemento;
}

function isset(variavel) {
	return typeof(variavel) != "undefined";
}

function alterarImagem(id, nova) {
	var elemento = getElemento(id);
	if (elemento != null) {
		elemento.src = nova.src;
	}
}

function adicionarEventListener(evento, funcao, tipo) {
	if (document.attachEvent) {
		document.attachEvent('on' + evento, funcao, tipo);
	} else {
		document.addEventListener(evento, funcao, tipo);
	}
}

// Removes leading whitespaces
function LTrim(value) {
	var re = /\s*((\S+\s*)*)/;
	return value.replace(re, "$1");
}

// Removes ending whitespaces
function RTrim(value) {
	var re = /((\s*\S+)*)\s*/;
	return value.replace(re, "$1");
}

// Removes leading and ending whitespaces
function trim(value) {
	return LTrim(RTrim(value));
}

/*==================================================
  Cookie functions
  ==================================================*/
function setCookie(name, value, expires, path, domain, secure) {
    document.cookie= name + "=" + escape(value) +
        ((expires) ? "; expires=" + expires.toGMTString() : "") +
        ((path) ? "; path=" + path : "") +
        ((domain) ? "; domain=" + domain : "") +
        ((secure) ? "; secure" : "");
}

function getCookie(name) {
    var dc = document.cookie;
    var prefix = name + "=";
    var begin = dc.indexOf("; " + prefix);
    if (begin == -1) {
        begin = dc.indexOf(prefix);
        if (begin != 0) return null;
    } else {
        begin += 2;
    }
    var end = document.cookie.indexOf(";", begin);
    if (end == -1) {
        end = dc.length;
    }
    return unescape(dc.substring(begin + prefix.length, end));
}

function deleteCookie(name, path, domain) {
    if (getCookie(name)) {
        document.cookie = name + "=" +
            ((path) ? "; path=" + path : "") +
            ((domain) ? "; domain=" + domain : "") +
            "; expires=Thu, 01-Jan-70 00:00:01 GMT";
    }
}

function safeNumeric(id, defaultValue) {
	
	var elemento = getElemento(id);
	
	if (isNaN(elemento.value)) {
		elemento.value = defaultValue;
	}
}

function checkDiaMes(id){
	
	var elemento = getElemento(id);

	if (elemento.value == '09') {
		elemento.value = '9';
	} else if (elemento.value == '08') {
		elemento.value = '8';
	} else {
		elemento.value = parseInt(elemento.value);
	}
/*	alert(elemento.value);

	if (elemento.value == '09') {
		elemento.value = '9';
	} else {
		if (elemento.value == '08') {
			elemento.value = '8';
		}
	}*/
}

function checkAno(id){
	
	var elemento = getElemento(id);
	
	if (elemento.value == '0') {
		elemento.value = '';
	}
}