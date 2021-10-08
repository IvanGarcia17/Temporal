var tabla = [
	{ nombre: "root", password: 123456 },
	{ nombre: "usuario1", password: "usuario1" }
];

window.onload = cargarEventos;

function cargarEventos(){
	document.getElementById("mostrar-tabla").addEventListener("click", mostrarTabla, false);
	document.getElementById("nuevo-usuario").addEventListener("submit", nuevoUsuario, false);
}

function mostrarTabla(){
	var cuerpoTabla = document.getElementById("usuarios-tabla");
	var tablaLlena = "";

	for (var i = 0; i < tabla.length; i++) {
		tablaLlena += "<tr><td>" + tabla[i].nombre + "</td><td>" + tabla[i].password + "</td></tr>";
	}

	cuerpoTabla.innerHTML = tablaLlena;
}

function nuevoUsuario(event){
	event.preventDefault();

	var usuarioTecleado = document.getElementById("usuario").value;
	var passwordTecleada = document.getElementById("password").value;

	var nuevoUsuario = { nombre: usuarioTecleado, password: passwordTecleada };
	tabla.push(nuevoUsuario);
}