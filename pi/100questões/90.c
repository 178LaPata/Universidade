#include <stdio.h>
#include <stdlib.h>

typedef struct nodo {
	int valor;
	struct nodo *esq, *dir;
} *ABin;

ABin newABin (int r, ABin e, ABin d){
	ABin new = (ABin) malloc (sizeof (struct nodo));

	if (new!=NULL){
		new->valor = r;
		new->esq   = e;
		new->dir   = d;
	}
	return new;
}

void imprimeABin (ABin a){
    if (a != NULL){
        printf("%d ", a->valor);
        imprimeABin(a->esq);
        imprimeABin(a->dir);
    }
}

int dumpAbin (ABin a, int v[], int N) {
    if (a == NULL || N == 0) return 0; // lista vazia || array cheio
    else {
        int n = dumpAbin(a->esq, v, N);
        if (n < N) {
            v[n] = a->valor;
            return n + 1 + dumpAbin(a->dir, v + n + 1, N - n - 1);
        } else {
            return n;
        }
    }
}

int main(int argc, char const *argv[]) {
	ABin a = newABin(8, newABin(4, newABin(2, newABin(1, NULL, NULL), newABin(3, NULL, NULL)), newABin(6, newABin(5, NULL, NULL), newABin(7, NULL, NULL))), newABin(2, newABin(10, newABin(9, NULL, NULL), newABin(11, NULL, NULL)), newABin(14, newABin(13, NULL, NULL), newABin(15, NULL, NULL))));
	int v[20];
	int n = dumpAbin(a, v, 20);
	printf("[ ");
	for (int i = 0; i < n; i++) {
		printf("%d ", v[i]);
	}
	printf("]\n");
	return 0;
}