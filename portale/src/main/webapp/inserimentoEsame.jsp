<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="model.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Inserimento Esame</title>
<link rel="stylesheet" type="text/css" href="style.css" />
<link rel="stylesheet" type="text/css" href="form.css" />
</head>
<body>
<%@ include file="header.html" %>
	<div id="container">
	<h2>Inserimento esame nel Portale</h2>
	<form action="inserimentoEsame.do" method="POST">
	<div class="box">
		<label>
		<span>Nome</span>
		<input class="input_text"type="text" name="nome" value="<%= (request.getSession().getAttribute("nomeEsameSessione") != null) ? request.getSession().getAttribute("nomeEsameSessione") : ""  %>" />
		<% if(request.getAttribute("msgerroreNome") != null) out.print("<p class='pError'>"+request.getAttribute("msgerroreNome")+"</p>"); %>
		</label>
		<label>
		<span>Descrizione</span>
		<input class="input_text"type="text" name="descrizione" value="<%= (request.getSession().getAttribute("descrizioneSessione") != null) ? request.getSession().getAttribute("descrizioneSessione") : ""  %>" />
		<% if(request.getAttribute("msgerroreDescrizione") != null) out.print("<p class='pError'>"+request.getAttribute("msgerroreDescrizione")+"</p>"); %>
		</label>
		<label>
		<span>CFU</span>
		<input class="input_text"type="text" name="cfu" value="<%= (request.getSession().getAttribute("cfuSessione") != null) ? request.getSession().getAttribute("cfuSessione") : ""  %>" />
		<% if(request.getAttribute("msgerroreCfu") != null) out.print("<p class='pError'>"+request.getAttribute("msgerroreCfu")+"</p>"); %>
		</label>
		<p><input class="button"type="submit" value="Invia" /></p>
	</div>	
	</form>
	</div>
</body>
</html>