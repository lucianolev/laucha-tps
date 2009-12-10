#!/usr/bin/awk -f

BEGIN { 
	print "-> Procesando lista de usuarios..."
	pwdfile = "/etc/passwd"
	#El separador de fields es : en pwdfield
	FS = ":"
	#Guardo en un array 'users' los usuarios de passwd indexados por el userid
	while( (getline < pwdfile) > 0 ) {
		userid = $3
		login = $1
		users[userid] = login
	}
	close(pwdfile)
	#Seteo el separador de fields en espacio para la lectura del archivo de entrada
	FS = " "
}
{
	useridF = $1
	#Si el id existia en el pwdfile
	#entonces debo editar la info de ese id, sino agregarlo
	if( users[useridF] ) {
		#El login es el obtenido de /etc/passwd que se corresponde
		#con el userid del archivo de entrada
		login = users[useridF]

		#Puede ser U o G, U cambiar login, G cambiar grupo
		tipoCambioF = $2
		if( tipoCambioF == "U" ) {
			loginF = $3
			paramsCommand = "-l " loginF
		} else if( tipoCambioF == "G" ) {
			grupoF = $3
			paramsCommand = "-g " grupoF
		}

		if( NF == 4) { #Si hay 4 parametros es porque tambien hay que cambiar clave
			claveF = $4
			encClave = "`perl -e \"print crypt('" claveF "', 'Q9')\"`"
			paramsCommand = paramsCommand " -p " encClave
		}

		print "Modificando informacion del usuario con id " useridF "..."
		thecommand = "usermod " paramsCommand " " login
		#system(thecommand)
		print thecommand
	} else {
		#El login es el especificado en el archivo de entrada
		login = $3

		#Si hay 4 parametros es que no pasaron el grupo,
		#si hay 5 es porque hay q asignar grupo
		if( NF == 4 ) { 
			claveF = $4
			paramsCommand = ""
		} else if ( NF == 5 ) { #Si hay 5 es porque hay q asignar grupo
			grupoF = $4
			claveF = $5
			paramsCommand = "-g " grupoF
		}

		encClave = "`perl -e \"print crypt('" claveF "', 'Q9')\"`"
		paramsCommand = paramsCommand " -p " encClave " -u " useridF

		print "Agregando al usuario con id " useridF "..."
		thecommand = "useradd " paramsCommand " " login
		#system(thecommand)
		print thecommand
	}
}
END {
	print "-> Se han realizado las modificaciones en el sistema"
}