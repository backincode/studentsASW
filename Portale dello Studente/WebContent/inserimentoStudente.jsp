<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="model.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Inserimento Studente</title>
<link rel="stylesheet" type="text/css" href="style.css" />
<link rel="stylesheet" type="text/css" href="form.css" />
</head>
<body>
<%@ include file="header.html" %>
	<div id="container">
	<h2>Inserimento studente nel Portale</h2>
	<form action="inserimentoStudente.do" method="POST">
	<div class="box">
		<label>
		<span>Nome</span>
		<input class="input_text"type="text" name="nome" value="<%= (request.getSession().getAttribute("nomeSessione") != null) ? request.getSession().getAttribute("nomeSessione") : ""  %>" />
		<% if(request.getAttribute("msgerroreNome") != null) out.print("<p class='pError'>"+request.getAttribute("msgerroreNome")+"</span>"); %>
		</label>
		<label>
		<span>Cognome</span>
		<input class="input_text"type="text" name="cognome" value="<%= (request.getSession().getAttribute("cognomeSessione") != null) ? request.getSession().getAttribute("cognomeSessione") : ""  %>" />
		<% if(request.getAttribute("msgerroreCognome") != null) out.print("<p class='pError'>"+request.getAttribute("msgerroreCognome")+"</span>"); %>
		</label>
		<label>
		<span>Matricola</span>
		<input class="input_text"type="text" name="matricola" value="<%= (request.getSession().getAttribute("matricolaSessione") != null) ? request.getSession().getAttribute("matricolaSessione") : ""  %>" />
		<% if(request.getAttribute("msgerroreMatricola") != null) out.print("<p class='pError'>"+request.getAttribute("msgerroreMatricola")+"</span>"); %>
		</label>
		<p><input class="button"type="submit" value="Invia" /></p>
	</div>	
	</form>
	</div>
</body>
</html>