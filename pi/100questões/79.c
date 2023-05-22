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

ABin cloneAB(ABin a){
    if (a == NULL){
        return NULL;
    }
    ABin new = (ABin) malloc(sizeof(struct nodo));
    new->valor = a->valor;
    new->esq = cloneAB(a->esq);
    new->dir = cloneAB(a->dir);
    return new;
}

int main(int argc, char const *argv[]) {
    ABin a = newABin(1, newABin(2, newABin(3, NULL, NULL), newABin(4, NULL, NULL)), newABin(5, NULL, NULL));
    imprimeABin(a);
    printf("\n");
    ABin b = cloneAB(a);
    imprimeABin(b);
    printf("\n");
    return 0;
}