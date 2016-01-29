<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="security" %>



<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitio	nal//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Registro de Productos</title>


<link rel="stylesheet" type="text/css" href="resources/css/bootstrap-3.3.6-dist/css/bootstrap.css">
</head>
<body>

<!-- <form action="/casadocodigo/produtos" method="post"> -->

<%-- <form action="${spring:mvcUrl("PC#save").build()}" method="post"> --%>
<%-- ${spring:mvcUrl("saveProduct").build()} --%>

<%-- <form action="${spring:mvcUrl("ProductsController#save").build()}" method="post"> --%>

<%-- <form:form action="${spring:mvcUrl('PC#save').build()}" method="post" commandName="product"> --%>

	<form:form action="${spring:mvcUrl('saveProduct').build()}" method="post" commandName="product" enctype="multipart/form-data">



<!-- INPUT DE SEGURIDAD AL TENER LA ANOTACION EN LA CLASE SECURITYCONFIGURATION ES OPCIONAL EL INPUT--> 
<%-- <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/> --%>

<%-- <security:csrfInput/> --%>

<div class="form-group">
<label for="title">Titulo</label>
<input type="text" name="title" id="title" class="form-control"/>
<form:errors path="title"/>
</div>

<div class="form-group">
<label for="description">Descricion</label>
<textarea  rows="10" cols="20" name="description"></textarea>
<form:errors path="description"/>
</div>

<div>
<label for="pages">Número de Páginas</label>

<input type="text" name="pages" id="pages">

<form:errors path="pages"/>
</div>

<div>
<label for="releaseDate">Data de lanzamiento</label>
<input type="date" name="releaseDate"/>
<form:errors path="releaseDate"/>
</div>

<div>
<label for="summary">Sumario do livro</label>
<input type="file" name="summary"/>
<form:errors path="summaryPath"/>
</div>



<c:forEach items="${types}" var="bookType" varStatus="status">
<div>
<label for="price_${bookType}">${bookType}</label>
<input type="text" name="prices[${status.index}].value"
id="price_${bookType}"/>
<input type="hidden"
name="prices[${status.index}].bookType"
value="${bookType}"/>
</div>
</c:forEach>

<div>
<input type="submit" value="Enviar" class="btn btn-primary">

</div>



</form:form>



<%-- <spring:hasBindErrors name="product"> --%>

<!-- <ul> -->
<%-- <c:forEach var="error" items="${errors.allErrors}"> --%>
<%-- <li>${error.code}</li> --%>

<%-- </c:forEach> --%>


<!-- </ul> -->

<%-- </spring:hasBindErrors> --%>


<spring:hasBindErrors name="product">
<ul>
<c:forEach var="error" items="${errors.allErrors}">
<li><spring:message code="${error.code}"
text="${error.defaultMessage}"/></li>
</c:forEach>
</ul>
</spring:hasBindErrors>







</body>
</html>