#!/usr/bin/awk -f

BEGIN { 
	print "-> Procesando lista de usuarios..."
	pwdfile = "/etc/passwd"
	#El separador de fields es : en pwdfield
	FS = ":"
	#Guardo en un array 'users' los usuarios de passwd indexados por el userid
	while( (getline < pwdfile) > 0 ) {
		userid = $3
		usuario = $1
		users[userid] = usuario
	}
	close(pwdfile)
	#Seteo el separador de fields en espacio para la lectura del archivo de entrada
	FS = " "
}
{
	useridF = $1
	usuarioF = $3
	#Si el id existia en el pwdfile
	#y si ese id corresponde con el nombre de usuario
	#entonces debo editar el usuario, sino agregarlo
	if( users[useridF] && (users[useridF] == usuarioF) ) {
		action = "mod"
		print "Modificando informacion del usuario " usuarioF "..."
	} else {
		action = "add"
		print "Agregando al usuario " usuarioF "..."
	}

	#Si hay 5 parametros es porque se especifico un grupo
	if( NF == 5 ) {
		grupo = $4
		clave = $5
		encClave = "`perl -e \"print crypt('" clave "', 'Q9')\"`"
		thecommand = "user" action " -p " encClave " -g " grupo " " usuarioF
		print thecommand
		#system(thecommand)
	#Si hay 4 parametros es porque no hay grupo
	} else if( NF == 4 ) {
		clave = $4
		encClave = "`perl -e \"print crypt('" clave "', 'Q9')\"`"
		thecommand = "user" action " -p " encClave " " usuarioF
		print thecommand
		#system(thecommand)
	}
}
END {
	print "-> Se han realizado las modificaciones en el sistema"
}
