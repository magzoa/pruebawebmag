<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<title>Login Page</title>


<link rel="stylesheet" type="text/css" href="resources/css/bootstrap-3.3.6-dist/css/bootstrap.css">

<style type="text/css">

#formulario{

padding-top:30px;
width: 200px;
margin: 0 auto;

}

body {

	
	width:450px;
	height:300px;
	margin: 0 auto;
	margin-top: 30px;
}



</style>



</head>

<body class="panel panel-primary">


	<div class="panel-heading">
	Login with Username and Password
	</div>
	
<!-- 	action="<c:url value="/shopping"/>"  name='username'  name='password'-->
	
	<div id="formulario">
	<form:form servletRelativeAction="/login"  >
	
	<div class="form-group">
				<label>User:</label>
				<input type="text" name="username" class="form-control"/>
			</div>
			
			
			<div class="form-group">
			<label>Password:</label>
				
				<input type="password" name="password" class="form-control" />
				</div>
				
			<input name="submit" type="submit" value="Login" class="btn btn-primary" />
	</form:form>
</div>
</body>
</html>