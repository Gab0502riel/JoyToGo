<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
  <link rel="stylesheet" href="<%=request.getContextPath()%>/risorse/css/styleaggiungicategoria.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="icon" type="image/png" href="<%=request.getContextPath()%>/risorse/res/LogoTakeAway - Busta.png">
<title>Aggiungi Categoria</title>
</head>
<body>
<form action="<%=request.getContextPath() %>/CategoriaLogica" method="POST">
	<h1>Aggiungi Categoria</h1>
        <label for="nome_categoria">Nome Categoria:</label>
        <input type="text" id="nome_categoria" name="nome_categoria">
        <input type="submit" value="Aggiungi">
	</form>
</body>
</html>