#include <stdio.h>
#include <stdlib.h>
#include <sys/wait.h>

int main() {
    int pfd[2];
    pid_t cpid;
    char buf[80];

    if(pipe(pfd)== -1) {
	    perror("pipe");
	    exit(EXIT_FAILURE);
    }


    cpid = fork();
    if (cpid == -1){
	    perror("fork");
	    exit(EXIT_FAILURE);
    }


    if (cpid == 0){
	    close(pfd[1]);
	    printf("Se ejecuta el hijo\n");
	    read(pfd[0], &buf, 1);
	    close(pfd[0]);
	    exit(EXIT_SUCCESS);

    }else {
	    close(pfd[0]);
	    printf("Se ejecuta el padre\n");
	    write(pfd[1], &buf, 1);
	    close(pfd[1]);
	    wait(NULL);
	    exit(EXIT_SUCCESS);
    }

}
