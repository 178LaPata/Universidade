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

int iguaisAB (ABin a, ABin b) {
    int e, d;
    if(!a && !b) return 1; // as duas vazias
    if(!a || !b) return 0;
    
    if(a->valor == b->valor){
        e = iguaisAB(a->esq, b->esq);
        d = iguaisAB(a->dir, b->dir);
        return (e < d ? e : d);
    }
    return 0;
}

int main(int argc, char const *argv[]) {
	ABin a = newABin(1, newABin(2, newABin(3, NULL, NULL), newABin(4, NULL, NULL)), newABin(5, NULL, NULL));
	ABin b = newABin(1, newABin(2, newABin(3, NULL, NULL), newABin(4, NULL, NULL)), newABin(5, NULL, NULL));
	imprimeABin(a);
	printf("\n");
	imprimeABin(b);
	printf("\n");
	printf("%d\n", iguaisAB(a, b));
	return 0;
}