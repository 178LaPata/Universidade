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

int freeAB (ABin a) {
    if(!a) return 0;

    int e = freeAB(a->esq);
    int d = freeAB(a->dir);
    free(a);

    return 1 + e + d;
}

int main(int argc, char const *argv[]) {
	ABin a = newABin(1, newABin(2, newABin(3, NULL, NULL), newABin(4, NULL, NULL)), newABin(5, NULL, NULL));
	imprimeABin(a);
	printf("\n");
	printf("%d\n", freeAB(a));
	return 0;
}
