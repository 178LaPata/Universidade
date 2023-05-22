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

void mirror (ABin *a) {
	if (*a != NULL){
		ABin aux = (*a)->esq; // guarda o endereço do filho esquerdo
		(*a)->esq = (*a)->dir; // o filho esquerdo passa a ser o filho direito
		(*a)->dir = aux; // o filho direito passa a ser o filho esquerdo
		mirror(&((*a)->esq)); 
		mirror(&((*a)->dir));
	}
}

int main(int argc, char const *argv[]) {
	ABin a = newABin(1, newABin(2, newABin(3, NULL, NULL), newABin(4, NULL, NULL)), newABin(5, NULL, NULL));
	imprimeABin(a);
	printf("\n");
	mirror(&a);
	imprimeABin(a);
	printf("\n");
	return 0;
}