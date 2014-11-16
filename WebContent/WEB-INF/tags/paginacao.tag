<%@ tag pageEncoding="UTF-8" %>
<%@ tag body-content="scriptless" %>
<link rel="stylesheet" href="/safetycar/resources/css/paginacao.css" />
<%@ attribute name="id" required="true" type="java.lang.String" %>
<%@ attribute name="lista" required="true" type="br.com.safetycar.paginacao.ListaPaginacao" %>
<%@ attribute name="estiloTabela" type="java.lang.String" %>
<%@ attribute name="semPaginacao" type="java.lang.Boolean" %>

<%@ attribute name="cabecalho" fragment="true" %>
<%@ attribute name="subcabecalho" fragment="true" %>
<%@ attribute name="linha" fragment="true" %>
<%@ attribute name="link" fragment="true" %>

<%@ variable name-given="item" %>
<%@ variable name-given="indice" %>
<%@ variable name-given="tabela" %>
<%@ variable name-given="pagina" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:set var="cssTabela" value="paginationTable" />
<c:set var="cssPar" value="odd" />
<c:set var="cssImpar" value="even" />
<c:set var="cssRodape" value="pagebanner" />
<c:set var="cssLinks" value="pagelinks" />

<c:set var="tabela" value="${id}" />
<c:set var="pagina" value="${lista.numeroPagina}" />

<c:choose>
	<c:when test="${lista.vazia}"> <%-- Quando a lista está vazia, seta o atributo cols para um valor grande e cria uma célula com esse valor de colspan, assim não precisamos saber quantas colunas tem na tabela --%>
		<table id="${tabela}" class="${cssTabela}" cols="50" style="${estiloTabela}">
			<thead>
				<jsp:invoke fragment="cabecalho" />
			</thead>
			<tbody>
				<tr>
					<td class="${cssPar}" colspan="50" style="text-align: center;">
						Nenhum registro encontrado
					</td>
				</tr>
			</tbody>
		</table>
	</c:when>
	<c:otherwise>
		<table id="${tabela}" class="${cssTabela}" style="${estiloTabela}">
			<thead>
				<tr>
					<jsp:invoke fragment="cabecalho" />
				</tr>
				<tr>
					<jsp:invoke fragment="subcabecalho" />
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${lista.itens}" var="item" varStatus="status">
					<c:set var="css" value="${cssImpar}" />
					<c:if test="${status.index % 2 == 0}">
						<c:set var="css" value="${cssPar}" />
					</c:if>
					<tr class="${css}">
						<c:set var="indice" value="${status.index}" />
						<jsp:invoke fragment="linha" />
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</c:otherwise>
</c:choose>

<c:if test="${!semPaginacao}">
<span class="${cssRodape}">
	<c:choose>
		<c:when test="${lista.vazia}">
			Nenhum registro encontrado
		</c:when>
		<c:when test="${lista.total == 1}">
			Um registro encontrado
		</c:when>
		<c:when test="${lista.total <= lista.totalPorPagina}">
			${lista.total} registros encontrados, exibindo todos os registros
		</c:when>
		<c:otherwise>
			${lista.total} registros encontrados, exibindo registros ${lista.posicaoInicial} a ${lista.posicaoFinal}
		</c:otherwise>
	</c:choose>
</span>

<c:if test="${lista.total > lista.totalPorPagina}">
<span class="${cssLinks}">
<%-- Primeira página --%>
<c:choose>
	<c:when test="${lista.paginaAnteriorValida}">
		<c:set var="pagina" value="1" />
		<a href="<jsp:invoke fragment="link" />" title="Ir para a página ${pagina}">&lt;&lt;</a>
	</c:when>
	<c:otherwise>
		&lt;&lt;
	</c:otherwise>
</c:choose>
&nbsp;
<%-- Página anterior --%>
<c:choose>
	<c:when test="${lista.paginaAnteriorValida}">
		<c:set var="pagina" value="${lista.numeroPagina - 1}" />
		<a href="<jsp:invoke fragment="link" />" title="Ir para a página ${pagina}">&lt;</a>
	</c:when>
	<c:otherwise>
		&lt;
	</c:otherwise>
</c:choose>
&nbsp;

<%-- Números --%>
<c:forEach begin="${lista.numeroPaginaInicial}"
		   end="${lista.numeroPaginaFinal}"
		   varStatus="status">
	<c:set var="pagina" value="${status.index}" />
	<c:choose>
		<c:when test="${lista.numeroPagina == pagina}">
			<strong>${pagina}</strong>
		</c:when>
		<c:otherwise>
			<a href="<jsp:invoke fragment="link" />" title="Ir para a página ${pagina}">${pagina}</a>
		</c:otherwise>
	</c:choose>
	&nbsp;
</c:forEach>

<%-- Próxima página --%>
<c:choose>
	<c:when test="${lista.proximaPaginaValida}">
		<c:set var="pagina" value="${lista.numeroPagina + 1}" />
		<a href="<jsp:invoke fragment="link" />" title="Ir para a página ${pagina}">&gt;</a>
	</c:when>
	<c:otherwise>
		&gt;
	</c:otherwise>
</c:choose>
&nbsp;
<%-- Última página --%>
<c:choose>
	<c:when test="${lista.proximaPaginaValida}">
		<c:set var="pagina" value="${lista.totalPaginas}" />
		<a href="<jsp:invoke fragment="link" />" title="Ir para a página ${pagina}">&gt;&gt;</a>
	</c:when>
	<c:otherwise>
		&gt;&gt;
	</c:otherwise>
</c:choose>
</span>
</c:if>
</c:if>