<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Compila Piano di Studi</title>
<link rel="stylesheet" type="text/css" href="style.css" />
<link rel="stylesheet" type="text/css" href="form.css" />
</head>
<body>
<%@ include file="header.html" %>
	<div id="container">
	<h2><%= request.getSession().getAttribute("tipoErrore")!=null ? request.getSession().getAttribute("msgerroreId") : ""  %></h2>
	<p>Inserisci nome, cognome o matricola</p>
	<form method="post" action="trovaPianoDiStudiStudenteCompila.do">
	<div class="box">
		<label>
			<span>Studente: </span>
			<input class="input_text" type="text" name="studente" />
			<% if(request.getAttribute("msgerroreId") != null) out.print("<p class='pError'>"+request.getAttribute("msgerroreId")+"</p>"); %>
		</label>
		<input class="button" type="submit" value="Trova" />
	</div>
	</form>
	</div>
</body>
</html>