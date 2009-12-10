#include <stdio.h>
#define IDGRUPO 1010

int main() {
	int i, c;

	while (1) {
		c = 48 + i;
		printf("%d",c);
		i++;
		i = i % IDGRUPO;
	}
	return 0;
}
