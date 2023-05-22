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

void imprimeABin (ABin a){
    if (a != NULL){
        printf("%d ", a->valor);
        imprimeABin(a->esq);
        imprimeABin(a->dir);
    }
}

void inorder (ABin a, LInt * l) {
	if (a == NULL){
		return;
	}
	inorder(a->dir, l);
	LInt new = malloc(sizeof(struct lligada));
	new->valor = a->valor;
	new->prox = *l;
	*l = new;
	inorder(a->esq, l);
}

int main(int argc, char const *argv[]) {
	ABin a = newABin(1, newABin(2, newABin(3, NULL, NULL), newABin(4, NULL, NULL)), newABin(5, NULL, NULL));
	LInt l = NULL;
	inorder(a, &l);
	printf("[ ");
	while(l != NULL){
		printf("%d ", l->valor);
		l = l->prox;
	}
	printf("]\n");
	return 0; 
}