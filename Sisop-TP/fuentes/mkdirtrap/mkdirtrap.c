#include <linux/module.h>
#include <linux/kernel.h>
#include <linux/unistd.h>
#include <linux/mm.h>
#include <asm/cacheflush.h>

#define SYS_CALL_TABLE_ADDRESS 0xc0318500

MODULE_LICENSE ("GPL");

struct page *pg;
pgprot_t prot;

void **sys_call_table = (void *)SYS_CALL_TABLE_ADDRESS;

asmlinkage int (*original_sys_call) (const char *pathname);

asmlinkage int fake_mkdir_function(const char __user *pathname) {
	printk(KERN_ALERT "No se permite la ejecucion de mkdir!\n");

	return 0;
}

static int my_init (void) {
						
	original_sys_call = sys_call_table[__NR_mkdir];

	pg = virt_to_page( SYS_CALL_TABLE_ADDRESS );
	prot.pgprot = VM_READ | VM_WRITE | VM_EXEC;
	change_page_attr(pg, 1, prot);
	
	sys_call_table[__NR_mkdir] = fake_mkdir_function;

	return 0;
}

static void my_exit (void) {
	sys_call_table[__NR_mkdir] = original_sys_call;
}

module_init(my_init);
module_exit(my_exit);
