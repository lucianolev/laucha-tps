#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>

#define BSIZE 10

typedef struct {
  char         buf[BSIZE];
  pthread_mutex_t        lock;
  pthread_cond_t        less;
  pthread_cond_t        more;
  int         nextin;
  int         nextout;
  int         occupied;
} buffer_t;

void consumer(buffer_t * b);
void producer(buffer_t * b);
char consume(buffer_t * b);
void produce(buffer_t * b, char item);

int main(int argc, char* argv[]) {
	pthread_t threads[2];
	buffer_t buffer;
	pthread_mutexattr_t mutex_attr;
	pthread_condattr_t cond_attr;
	buffer.occupied = 0;
	buffer.nextin = 0;
	buffer.nextout = 0;

	int i;
	for(i = 0; i < BSIZE; i++) {
		buffer.buf[i] = 0;
	}
	
	pthread_mutexattr_init(&mutex_attr);
	pthread_mutexattr_setpshared(&mutex_attr,PTHREAD_PROCESS_SHARED);
	pthread_mutex_init(&buffer.lock, &mutex_attr);
	pthread_condattr_init(&cond_attr);
	pthread_condattr_setpshared(&cond_attr, PTHREAD_PROCESS_SHARED);
	pthread_cond_init(&buffer.less, &cond_attr);
	pthread_cond_init(&buffer.more, &cond_attr);

	int rc;
	rc =  pthread_create(&threads[0], NULL, producer, (void*)&buffer);
	if (rc) {
		printf("Error al crear el thread 0");
		exit(1);
	}

	rc =  pthread_create(&threads[1], NULL, consumer, (void*)&buffer);
	if (rc) {
		printf("Error al crear el thread 1");
		exit(1);
	}
	pthread_exit(0);
}

void consumer(buffer_t * b){
	char item;
// 	printf("Consumidor - %d\n", getpid());
	FILE* salida = fopen("salida.txt","w");
	while (1) { 
		if(salida == NULL) {
			exit(0);
		}
		item = consume(b);
		if (item == '\0')
			break;
		fputc((int)item,salida);
	}
	fclose(salida);
}

void producer(buffer_t * b) {
      char item[20];
//       printf("Productor - %d\n", getpid());
      while (1) {
		printf("Introduzca item: ");
		scanf("%s", item);

		if (item[0] == 'q') {
			produce(b, '\0');
			break;
		} else
			produce(b, (char) item[0]);
		}
}

char consume(buffer_t * b){
	char         item;
	pthread_mutex_lock(&b->lock);
	
	while (b->occupied == 0)
		pthread_cond_wait(&b->more, &b->lock);

	printf("Consume\n");
	item = b->buf[b->nextout];

	if(b->nextout != 0)
		b->nextout--;

	b->nextin--;
	b->occupied--;
	
	pthread_cond_signal(&b->less);
	pthread_mutex_unlock(&b->lock);
	return (item);
}

void produce(buffer_t * b, char item) {
	pthread_mutex_lock(&b->lock);
	
	while (b->occupied == BSIZE)
		pthread_cond_wait(&b->less, &b->lock);

	printf("Produce\n");
	b->buf[b->nextin] = item;
	
	b->nextin++;
	if(b->nextin > 1)
		b->nextout++;

	b->occupied++;
	pthread_cond_signal(&b->more);
	pthread_mutex_unlock(&b->lock);
}