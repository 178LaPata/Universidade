#include <stdio.h>
#include <stdlib.h>

typedef struct nodo {
	int valor;
	struct nodo *esq, *dir;
} *ABin;

typedef struct lligada {
    int valor;
    struct lligada *prox;
} *LInt;

ABin newABin (int r, ABin e, ABin d){
	ABin new = (ABin) malloc (sizeof (struct nodo));

	if (new!=NULL){
		new->valor = r;
		new->esq   = e;
		new->dir   = d;
	}
	return new;
}

LInt newLInt (int v, LInt t) {
    LInt new = (LInt) malloc (sizeof (struct lligada));
    
    if (new!=NULL) {
        new->valor = v;
        new->prox  = t;
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

/* Defina uma função "LInt nivelL (ABin a, int n)" que, dada uma árvore
 binária, constrói uma lista com os valores dos elementos que estão armazenados
 na árvore ao nível "n" (assuma que a raiz da árvore está ao nível "1").  */
LInt nivelL (ABin a, int n) {
	if(!a)  return 0;
		
    return NULL;
}

int main(int argc, char const *argv[]){

}