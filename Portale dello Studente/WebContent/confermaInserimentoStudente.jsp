<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Conferma Inserimento Studente</title>
<link rel="stylesheet" type="text/css" href="style.css" />
</head>
	<body>
	<%@ include file="header.html" %>
	<div id="container">
	<form method="post" action="confermaInserimentoStudente.do">
	<div class="campo"><%= "Nome: " +request.getSession().getAttribute("nomeSessione") %></div>
	<div class="campo"><%= "Cognome: " +request.getSession().getAttribute("cognomeSessione") %></div>
	<div class="campo"><%= "Matricola: " +request.getSession().getAttribute("matricolaSessione") %></div>
	<input class="blueButton" type="submit" name="risposta" value="si" />
	<input class="blueButton" type="submit" name="risposta" value="no" />
	</form>
	</div>
</body>
</html>