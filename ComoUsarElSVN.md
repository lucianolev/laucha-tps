Bueno gente, ya tenemos un lugar donde subir nuestras cosas y no estar mandando mails como tarados :p

Primero que nada, tienen q instalar el programa svn para usar esto, se llama "subversion".

La idea es la siguiente:

Ustedes primero bajan la ultima versión del SVN con el siguiente comando en la consola:

$ svn checkout https://laucha-tps.googlecode.com/svn/trunk/ laucha-tps --username su-usuario

Les va a pedir una clave. Esto mismo esta explicado en la pestaña Source (en español debe ser Fuentes o algo asi) y ahi mismo pueden generar su clave.

Luego van a ver que se les creó una carpeta q se llama laucha-tps en donde ejecutaron el comando, ahi adentro está todo. Ustedes hacen los cambios que quieren y una vez q terminen con el siguiente comando suben los cambios (tienen que estar dentro de la carpeta laucha-tps obviamente)

$ svn commit -m "Acá ponen una descripción de los cambios que hicieron"

Si quieren sincronizar la version del servidor con la que tienen, van a la carpeta de laucha-tps y hacen

$ svn update

Hay mil comandos mas y cosas para hacer, igual yo soy re principiante en esto asi q no sé, hay pila de info en internet.

Acá tienen un manual completo y en español:

http://svnbook.red-bean.com/nightly/es/svn-ch-3-sect-5.html

Usen también la sección Wiki e Issues para documentar lo q hagamos y para reportar bugs o cosas por mejorar, respectivamente.