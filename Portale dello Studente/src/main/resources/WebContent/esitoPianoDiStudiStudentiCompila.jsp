<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Scegli studente</title>
<link rel="stylesheet" type="text/css" href="style.css" />
</head>
<body>
<%@ include file="header.html" %>
	<div id="container">
	<form method="post" action="sceltaStudentePianoDiStudi.do">
	<% 
		if(request.getSession().getAttribute("tipoEsito")!=null)
		{
			out.print("<table align='center'><tr><th>Matricola</th><th>Nome</th><th>Cognome</th></tr>");
			out.print(request.getSession().getAttribute("tipoEsito"));
			request.getSession().removeAttribute("tipoEsito");
		}
		else
			out.print("Errore Sconosciuto");  
	%>
	</form>
	</div>
</body>
</html>