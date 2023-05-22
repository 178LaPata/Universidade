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

int altura (ABin a){
    if (a == NULL){ // arvore for nula
        return 0;
    }
    int e = altura(a->esq); // calcula a altura da esquerda
    int d = altura(a->dir); // calcula a altura da direita
    return 1 + (e > d ? e : d); // retorna a maior altura + 1 
}

int main(int argc, char const *argv[]) {
    ABin a = newABin(1, newABin(2, newABin(3, NULL, NULL), newABin(4, NULL, NULL)), newABin(5, NULL, NULL));
    imprimeABin(a);
    printf("\n");
    printf("%d\n", altura(a));
    return 0;
}