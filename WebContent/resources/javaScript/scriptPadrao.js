function validaData(campo){
	var expReg = /^(([0-2]\d|[3][0-1])\/([0]\d|[1][0-2])\/[1-2][0-9]\d{2})$/;
	var msgErro = 'Formato inválido de data.';
	if (campo.value.length > 0 ) {
		if ((campo.value.match(expReg)) && (campo.value!='')){
			var dia = campo.value.substring(0,2);
			var mes = campo.value.substring(3,5);
			var ano = campo.value.substring(6,10);
			if((mes==4 || mes==6 || mes==9 || mes==11) && dia > 30){
				alert("Dia incorreto !!! O mês especificado contém no máximo 30 dias.");
				campo.value="";
				return false;
				} else{
					if((ano%4!=0 && mes==2) && dia>28){
						alert("Data incorreta!! O mês especificado contém no máximo 28 dias.");
						campo.value="";
						return false;
						} else{
							if((ano%4==0 && mes==2) && dia>29){
								alert("Data incorreta!! O mês especificado contém no máximo 29 dias.");
								campo.value="";
								return false;
								} else{										
									return true;
								  }
						  }
				  }
	    } else {
			alert(msgErro);
			campo.focus();
			campo.value="";
			return false;
	      }
	}
}


function bloqueiaData(campo){  
	var digits="0123456789/"; 
	var campo_temp;
    for (var i=0;i<campo.value.length;i++){  
        campo_temp=campo.value.substring(i,i+1);   
        if (digits.indexOf(campo_temp)==-1){  
            campo.value = campo.value.substring(0,i);  
        }  
    }
}


function bloqueiaCpf(campo){  
	var digits="0123456789.-";
	var campo_temp;
    for (var i=0;i<campo.value.length;i++){  
        campo_temp=campo.value.substring(i,i+1);  
        if (digits.indexOf(campo_temp)==-1){  
            campo.value = campo.value.substring(0,i);  
        }  
    }  
}

function bloqueiaCep(campo){  
	var digits="0123456789-";
	var campo_temp;
    for (var i=0;i<campo.value.length;i++){  
        campo_temp=campo.value.substring(i,i+1);  
        if (digits.indexOf(campo_temp)==-1){  
            campo.value = campo.value.substring(0,i);  
        }  
    }  
}


function bloqueiaTelefone(campo){  
	var digits="0123456789-"; 
	var campo_temp;
    for (var i=0;i<campo.value.length;i++){  
        campo_temp=campo.value.substring(i,i+1);   
        if (digits.indexOf(campo_temp)==-1){  
            campo.value = campo.value.substring(0,i);  
        }  
    }  
}


function somenteLetras(campo) { 
	var string = campo.value; 
	/* 
	para não aceitar números use: 0-9
	para não aceitar letras use: a-z
	*/ 
	var string = string.replace(/([0-9])/g, ""); 
	campo.value=string; 
} 

function somenteNumeros(campo) { 
	var string = campo.value; 
	/* 
	para não aceitar números use: 0-9
	para não aceitar letras use: a-z
	*/ 
	var string = string.replace(/([a-z])/g, ""); 
	campo.value=string; 
} 


function somenteNumerosLetras(campo){  
	var digits="0123456789AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvXxWwYyZz";  
	var campo_temp;
    for (var i=0;i<campo.value.length;i++){  
        campo_temp=campo.value.substring(i,i+1);
        if (digits.indexOf(campo_temp)==-1){  
            campo.value = campo.value.substring(0,i);  
        }  
    }  
}

function somenteNumerosLetrasComPontoEArroba(campo){  
	var digits="0123456789AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvXxWwYyZz.@";  
	var campo_temp;
    for (var i=0;i<campo.value.length;i++){  
        campo_temp=campo.value.substring(i,i+1);
        if (digits.indexOf(campo_temp)==-1){  
            campo.value = campo.value.substring(0,i);  
        }  
    }  
}


function mascara(objeto, sMask, evtKeyPress) {
    var i, nCount, sValue, fldLen, mskLen,bolMask, sCod, nTecla;


	if(document.all) { // Internet Explorer
	    nTecla = evtKeyPress.keyCode;
	} else if(document.layers) { // Nestcape
	    nTecla = evtKeyPress.which;
	} else {
	    nTecla = evtKeyPress.which;
	    if (nTecla == 8) {
	        return true;
	    }
	}

    sValue = objeto.value;

    // Limpa todos os caracteres de formatação que
    // já estiverem no campo.
    sValue = sValue.toString().replace( "-", "" );
    sValue = sValue.toString().replace( "-", "" );
    sValue = sValue.toString().replace( ".", "" );
    sValue = sValue.toString().replace( ".", "" );
    sValue = sValue.toString().replace( "/", "" );
    sValue = sValue.toString().replace( "/", "" );
    sValue = sValue.toString().replace( ":", "" );
    sValue = sValue.toString().replace( ":", "" );
    sValue = sValue.toString().replace( "(", "" );
    sValue = sValue.toString().replace( "(", "" );
    sValue = sValue.toString().replace( ")", "" );
    sValue = sValue.toString().replace( ")", "" );
    sValue = sValue.toString().replace( " ", "" );
    sValue = sValue.toString().replace( " ", "" );
    fldLen = sValue.length;
    mskLen = sMask.length;

    i = 0;
    nCount = 0;
    sCod = "";
    mskLen = fldLen;

    while (i <= mskLen) {
      bolMask = ((sMask.charAt(i) == "-") || (sMask.charAt(i) == ".") || (sMask.charAt(i) == "/") || (sMask.charAt(i) == ":"))
      bolMask = bolMask || ((sMask.charAt(i) == "(") || (sMask.charAt(i) == ")") || (sMask.charAt(i) == " "))

      if (bolMask) {
        sCod += sMask.charAt(i);
        mskLen++; }
      else {
        sCod += sValue.charAt(nCount);
        nCount++;
      }

      i++;
    }

    objeto.value = sCod;

    if (nTecla != 8) { // backspace
      if (sMask.charAt(i-1) == "9") { // apenas números...
        return ((nTecla > 47) && (nTecla < 58)); }
      else { // qualquer caracter...
        return true;
      }
    }
    else {
      return true;
    }
}


function formataTexto(objeto){
	var index;
	var tmpStr;
	var tmpChar;
	var preString;
	var postString;
	var strlen;
	tmpStr = objeto.value.toLowerCase();
	strLen = tmpStr.length;
	    if (strLen > 0){
		    for (index = 0; index < strLen; index++){
		        if (index == 0){
		            tmpChar = tmpStr.substring(0,1).toUpperCase();
		            postString = tmpStr.substring(1,strLen);
		            tmpStr = tmpChar + postString;
		        }else {
		            tmpChar = tmpStr.substring(index, index+1);
	                if (tmpChar == " " && index < (strLen-1)){
		                tmpChar = tmpStr.substring(index+1, index+2).toUpperCase();
		                preString = tmpStr.substring(0, index+1);
		                postString = tmpStr.substring(index+2,strLen);
		                tmpStr = preString + tmpChar + postString;
	                }
		        }
		    }
	    }
	objeto.value = tmpStr;
}


function converteMaiuscula(lstr){ 
	var str=lstr.value; 
	lstr.value=str.toUpperCase(); 
}


function converteMinuscula(lstr){ 
	var str=lstr.value; 
	lstr.value=str.toLowerCase(); 
}


function abrirNovaJanela(url,nome,atributos){
	window.open(url,nome,atributos);
}


function validaPlaca(placa){
	var lets = "AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvXxWwYyZz";
	var nums = "-0123456789";
	var valor2;   
	var valor;
	   
	if (placa.value.length <= 3) {
		for (var i=0;i<placa.value.length;i++) {
			valor=placa.value.substring(i,i+1);
			if (lets.indexOf(valor) == -1) {
					placa.value = placa.value.substring(0,i);
					alert("Padrão de placa ABC-1234");
					break;
			}
	    }
	}else if (placa.value.length > 3) {
		for (var i=3;i<placa.value.length;i++) {
			if(i>=8){
		    	alert("Padrão de placa ABC-1234");
			    break;
		    }		    
			valor2=placa.value.substring(i,i+1);
			if (nums.indexOf(valor2) == -1) {
				placa.value = placa.value.substring(0,i);
				alert("Padrão de placa: ABC-1234");
				break;
			}
	    }
	}
}


function moeda(z){   
	v = z.value; 
    v=v.replace(/\D/g,"");  //permite digitar apenas números 
    v=v.replace(/[0-9]{12}/,"inválido");   //limita pra máximo 999.999.999,99 
    v=v.replace(/(\d{1})(\d{8})$/,"$1.$2");  //coloca ponto antes dos últimos 8 digitos 
    v=v.replace(/(\d{1})(\d{5})$/,"$1.$2");  //coloca ponto antes dos últimos 5 digitos 
    v=v.replace(/(\d{1})(\d{1,2})$/,"$1,$2");        //coloca virgula antes dos últimos 2 digitos 
    z.value = v; 
} 
