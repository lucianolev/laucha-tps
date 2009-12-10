/* hello.c
 *
 * "Hello, world" usando modulos de kernel
 *
 */

/* Headers para modulos de kernel */
#include <linux/module.h>
#include <linux/kernel.h>
#include <linux/init.h>

/* Prototipos de las funciones de inicializacion y destruccion */
static int __init hello_init(void);
static void __exit hello_exit(void);

/* Informamos al kernel que inicialice el modulo usando hello_init
 * y que antes de quitarlo use hello_exit
 */
module_init(hello_init);
module_exit(hello_exit);

/* Inicializacion */
static int __init hello_init() {
	printk(KERN_ALERT "Hola kernel!\n");
	/* Si devolvemos un valor distinto de cero significa que
	 * hello_init fallo y el modulo no puede ser cargado.
	 */
	return 0;
}

/* Destruccion */
static void __exit hello_exit() {
	printk(KERN_ALERT "Chau, kernel.\n");
}

/* Declaramos que este codigo tiene licencia GPL. De esta manera
 * no estamos "manchando" el kernel con codigo propietario.
 */

MODULE_LICENSE("GPL");
