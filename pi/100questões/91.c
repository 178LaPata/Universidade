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

ABin somasAcA (ABin a) {
    if(!a) return NULL;
	ABin new = malloc(sizeof(ABin));

	new->valor = a->valor; // nodo atual
	new->esq = somasAcA(a->esq); 
	new->dir = somasAcA(a->dir); 

	if(new->esq) new->valor += new->esq->valor; // soma do atual + esquerda
	if(new->dir) new->valor += new->dir->valor; // soma do atual + direita
	return new;
}


int main(int argc, char const *argv[]) {
	ABin a = newABin(8, newABin(4, newABin(2, newABin(1, NULL, NULL), newABin(3, NULL, NULL)), newABin(6, newABin(5, NULL, NULL), newABin(7, NULL, NULL))), newABin(2, newABin(10, newABin(9, NULL, NULL), newABin(11, NULL, NULL)), newABin(14, newABin(13, NULL, NULL), newABin(15, NULL, NULL))));
	imprimeABin(a);
	printf("\n");
	ABin b = somasAcA(a);
	imprimeABin(b);
	printf("\n");
	return 0;
}