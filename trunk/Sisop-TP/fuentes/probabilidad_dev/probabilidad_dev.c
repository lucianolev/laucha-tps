#include <linux/kernel.h>
#include <linux/fs.h>
#include <linux/init.h>
#include <linux/miscdevice.h>
#include <linux/module.h>
#include <linux/vmalloc.h>
#include <linux/time.h>
#include <linux/proc_fs.h> /* For proc */
#include <asm/uaccess.h>

MODULE_LICENSE("GPL");

int acum_lecturas = 0;

static struct proc_dir_entry *proc_entry;

unsigned long prev_random;

static char *input_semilla;

unsigned long get_random(unsigned long m_w, unsigned long m_z) {
    m_z = 36969 * (m_z & 65535) + (m_z >> 16);
    m_w = 18000 * (m_w & 65535) + (m_w >> 16);
    return (m_z << 16) + m_w;  /* 32-bit result */
}

int cant_lecturas( char *page, char **start, off_t off, int count, int *eof, void *data ) {
	int len;
	len = sprintf(page, "Lecturas realizadas: %d\n", acum_lecturas);
	return len;
}

ssize_t cambiar_semilla(struct file *filp, const char __user *buff, unsigned long len, void *data) {

	copy_from_user(&input_semilla[0], buff, len);

	prev_random = simple_strtoul(input_semilla, NULL, 10);
	
	printk(KERN_INFO "probabilidad: Se han cambiado la semilla\n");
	
	return len;
}

static ssize_t probabilidad_read(struct file * file, char * buf, size_t count, loff_t *ppos) {

	unsigned int random_pos;
	char *probabilidad_str;
	char random_letter;
	int len;

	prev_random = get_random(prev_random, prev_random);

	random_pos = (prev_random % 25);
	probabilidad_str = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	random_letter = probabilidad_str[random_pos];
	len = 1;

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
	if (copy_to_user(buf, &random_letter, len))
		return -EINVAL;
	/*
	 * Tell the user how much data we wrote.
	 */
	*ppos = len;

	acum_lecturas++;

	printk(KERN_ALERT "probabilidad: Se ha utilizado la semilla %lu\n", simple_strtoul(input_semilla, NULL, 10));

	return len;
}

static const struct file_operations probabilidad_fops = {
	.owner = THIS_MODULE,
	.read = probabilidad_read,
};

static struct miscdevice probabilidad_dev = {
	MISC_DYNAMIC_MINOR,
	"probabilidad",
	&probabilidad_fops
};

static int __init probabilidad_init(void) {
	int ret;

	/*
	 * Create the "probabilidad" device in the /sys/class/misc directory.
	 * Udev will automatically create the /dev/hello device using
	 * the default rules.
	 */
	ret = misc_register(&probabilidad_dev);
	if (ret) {
		printk(KERN_ERR "No se puede registrar el dispositivo 'probabilidad'\n");
	} else {

		struct timespec tv;
		getnstimeofday(&tv);
		prev_random = tv.tv_nsec;

		input_semilla = (char *)vmalloc(PAGE_SIZE);
		memset(input_semilla, 0, PAGE_SIZE);

		proc_entry = create_proc_entry("probabilidad", 0644, NULL);
	
		if(proc_entry == NULL) {
			printk(KERN_INFO "probabilidad: No se pudo crear la entrada en /proc\n");
		} else {
			proc_entry->read_proc = cant_lecturas;
			proc_entry->write_proc = cambiar_semilla;
			proc_entry->owner = THIS_MODULE;
		}
	}

	return ret;
}

static void __exit probabilidad_exit(void) {
	remove_proc_entry("probabilidad", &proc_root);
	vfree(input_semilla);
	misc_deregister(&probabilidad_dev);
}

module_init(probabilidad_init);
module_exit(probabilidad_exit);
