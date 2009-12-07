#include <linux/module.h>
#include <linux/init.h>
#include <linux/kernel.h> /* KERNEL_INFO, KERNEL_ALERT */
#include <linux/kd.h>		/* For KDSETLED */
#include <linux/vt.h>
#include <linux/vt_kern.h> /* For fg_console */
#include <linux/console_struct.h>	/* For vc_cons */

#include <linux/proc_fs.h> /* For proc */
#include <linux/string.h>
#include <linux/vmalloc.h> /* For vmalloc, vfree */
#include <asm/uaccess.h> /* For copy_from_user */

#define LED_ALLOFF  0x00
#define LED_SCROLL  0x01
#define LED_NUMLOCK  0x02
#define LED_CAPSLOCK  0x04

MODULE_LICENSE("GPL");

struct tty_driver *leds_driver;
static struct proc_dir_entry *proc_entry;
static char *input_leds;

ssize_t control_leds(struct file *filp, const char __user *buff, unsigned long len, void *data) {

	int leds_code = LED_ALLOFF;
	
	copy_from_user(&input_leds[0], buff, len);
	
	if(input_leds[0] == '1') {
		leds_code += LED_NUMLOCK;
	}
	if(input_leds[2] == '1') {
		leds_code += LED_CAPSLOCK;
	}
	if(input_leds[4] == '1') {
		leds_code += LED_SCROLL;
	}
	
	(leds_driver->ioctl) (vc_cons[fg_console].d->vc_tty, NULL, KDSETLED, leds_code);
	
	printk(KERN_INFO "leds: Se han cambiado el estado de los LEDs\n");
	
	return len;
}

int iniciar_modulo(void) {

	input_leds = (char *)vmalloc(PAGE_SIZE);
	
	if (!input_leds) {
		printk(KERN_ALERT "leds: No se pudo crear la entrada en /proc\n");
		return 1;
	} else {
		memset(input_leds, 0, PAGE_SIZE);
		
		proc_entry = create_proc_entry("leds", 0644, NULL);

		if(proc_entry == NULL) {
			vfree(input_leds);
			printk(KERN_INFO "leds: No se pudo crear la entrada en /proc\n");
		} else {
			proc_entry->write_proc = control_leds;
			proc_entry->owner = THIS_MODULE;
		}
		
		leds_driver = vc_cons[fg_console].d->vc_tty->driver;
		
		printk(KERN_INFO "leds: El modulo ha sido cargado.\n");
	
		return 0;
	}
	
}

void detener_modulo(void) {
	
	remove_proc_entry("leds", &proc_root);
	vfree(input_leds);
	printk(KERN_INFO "leds: Modulo ha sido descargado.\n");
	
}

module_init(iniciar_modulo);
module_exit(detener_modulo);
