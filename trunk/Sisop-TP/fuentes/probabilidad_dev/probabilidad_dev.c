#include <linux/fs.h>
#include <linux/init.h>
#include <linux/miscdevice.h>
#include <linux/module.h>

#include <linux/proc_fs.h> /* For proc */

#include <asm/uaccess.h>

MODULE_LICENSE("GPL");

int acum_lecturas = 0;

static struct proc_dir_entry *proc_entry;

int cant_lecturas( char *page, char **start, off_t off, int count, int *eof, void *data ) {
	int len;
	len = sprintf(page, "%d\n", acum_lecturas);
	return len;
}

static ssize_t probabilidad_read(struct file * file, char * buf, size_t count, loff_t *ppos) {

	char *probabilidad_str = "ABC\n";
	int len = strlen(probabilidad_str); /* Don't include the null byte. */
	/*
	 * We only support reading the whole string at once.
	 */
	if (count < len)
		return -EINVAL;
	/*
	 * If file position is non-zero, then assume the string has
	 * been read and indicate there is no more data to be read.
	 */
	if (*ppos != 0)
		return 0;
	/*
	 * Besides copying the string to the user provided buffer,
	 * this function also checks that the user has permission to
	 * write to the buffer, that it is mapped, etc.
	 */
	if (copy_to_user(buf, probabilidad_str, len))
		return -EINVAL;
	/*
	 * Tell the user how much data we wrote.
	 */
	*ppos = len;

	acum_lecturas++;

	return len;
}

/*
 * The only file operation we care about is read.
 */

static const struct file_operations probabilidad_fops = {
	.owner = THIS_MODULE,
	.read = probabilidad_read,
};

static struct miscdevice probabilidad_dev = {
	/*
	 * We don't care what minor number we end up with, so tell the
	 * kernel to just pick one.
	 */
	MISC_DYNAMIC_MINOR,
	/*
	 * Name ourselves /dev/probabilidad.
	 */
	"probabilidad",
	/*
	 * What functions to call when a program performs file
	 * operations on the device.
	 */
	&probabilidad_fops
};

static int __init probabilidad_init(void) {
	int ret;

	/*
	 * Create the "hello" device in the /sys/class/misc directory.
	 * Udev will automatically create the /dev/hello device using
	 * the default rules.
	 */
	ret = misc_register(&probabilidad_dev);
	if (ret)
		printk(KERN_ERR "Unable to register \"probabilidad\" misc device\n");

	proc_entry = create_proc_entry("probabilidad", 0644, NULL);

	if(proc_entry == NULL) {
		printk(KERN_INFO "leds: No se pudo crear la entrada en /proc\n");
	} else {
		proc_entry->read_proc = cant_lecturas;
		proc_entry->owner = THIS_MODULE;
	}

	return ret;
}

module_init(probabilidad_init);

static void __exit probabilidad_exit(void) {
	remove_proc_entry("probabilidad", &proc_root);
	misc_deregister(&probabilidad_dev);
}

module_exit(probabilidad_exit);
