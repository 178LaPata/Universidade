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

int pruneAB (ABin *a, int l) {
	int e,d;
	if(!*a) return 0;
	if(l==0){
		e = pruneAB(&(*a)->esq, l);
		d = pruneAB(&(*a)->dir, l);
		*a = NULL;
		free(*a);
		return 1 + e + d;
	} else {
		e = pruneAB(&(*a)->esq, l-1);
		d = pruneAB(&(*a)->dir, l-1);
		return e + d;
	}
	return 0;
}

int main(int argc, char const *argv[]) {
	ABin a = newABin(8, newABin(4, newABin(2, newABin(1, NULL, NULL), newABin(3, NULL, NULL)), newABin(6, newABin(5, NULL, NULL), newABin(7, NULL, NULL))), newABin(12, newABin(10, newABin(9, NULL, NULL), newABin(11, NULL, NULL)), newABin(14, newABin(13, NULL, NULL), newABin(15, NULL, NULL))));
	imprimeABin(a);
	printf("\n");
	printf("%d\n", pruneAB(&a, 2));
	imprimeABin(a);
	printf("\n");
	return 0;
}