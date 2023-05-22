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

ABin cloneMirror (ABin a) {
    if(!a) return NULL;
    ABin new = (ABin) malloc(sizeof(struct nodo));
    if(a){
        ABin aux = a->esq;
        a->esq = a->dir;
        a->dir = aux;
        
        new->valor = a->valor;
        new->esq = cloneMirror(a->esq);
        new->dir = cloneMirror(a->dir);
    }
    return new;
}

int main(int argc, char const *argv[]) {
	ABin a = newABin(1, newABin(2, newABin(3, NULL, NULL), newABin(4, NULL, NULL)), newABin(5, NULL, NULL));
	imprimeABin(a);
	printf("\n");
	ABin b = cloneMirror(a);
	imprimeABin(b);
	printf("\n");
	return 0;
}