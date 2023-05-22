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

int addOrd (ABin *a, int x) {
	if(!(*a)) {
		*a = newABin(x, NULL, NULL);
		return 0;
	}
	if((*a)->valor == x) return 1;
	if((*a)->valor > x) return addOrd(&((*a)->esq), x); // se o valor for maior, vai para a esquerda
	return addOrd(&((*a)->dir), x); // se o valor for menor, vai para a direita
}


int main(int argc, char const *argv[]) {
	ABin a = NULL;
	addOrd(&a, 5);
	addOrd(&a, 3);
	addOrd(&a, 7);
	addOrd(&a, 1);
	addOrd(&a, 4);
	addOrd(&a, 6);
	addOrd(&a, 8);	
	imprimeABin(a);
	printf("\n");
	return 0;
}