<html>
<head></head>
<body>
	<h1>Clientes</h1>
	
	<%=servlets.Util.print(request, "msg") %>
	
	<form action="listado.do">
		Buscar
		<input type="text" name="buscar">
		<input type="submit" value="Buscar">
	</form>

	<br/>	<br/>	<br/>
	
	<a href="listado.do?modo=all">Listado</a>
	<a href="insertar.do">Nuevo cliente</a>

	
	
</body>
</html>