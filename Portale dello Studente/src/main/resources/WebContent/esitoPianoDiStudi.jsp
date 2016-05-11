<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Report</title>
<link rel="stylesheet" type="text/css" href="style.css" />
</head>
<body>
<%@ include file="header.html" %>
	<div id="container">
	<h2>
	<% 
		if(request.getSession().getAttribute("tipoEsito")!=null)
		{
			out.print(request.getSession().getAttribute("tipoEsito"));
			request.getSession().removeAttribute("tipoEsito");
		}
		else
			out.print("Errore Sconosciuto");  
	%>
	</h2>
	<p><input class="blueButton" type="submit" onClick="location.href='esitoPianoDiStudiEsami.jsp'" value="continua" /></p>
	</div>
</body>
</html>