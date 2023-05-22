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

void preorder (ABin a, LInt *l){
    if(a == NULL){
        return;
    }
    
    LInt novo = newLInt(a->valor, NULL); // Cria um novo nó com o valor do nodo atual
    if(*l == NULL){ // se a lista estiver vazia, o novo nó é o primeiro elemento
        *l = novo;
    }
    else{
        LInt atual = *l; 
        while (atual->prox != NULL){ // percorre a lista até ao último elemento
            atual = atual->prox; 
        }
        atual->prox = novo;
    }
    preorder(a->esq, l); 
    preorder(a->dir, l);
}



int main(int argc, char const *argv[]) {
	ABin a = newABin(8, newABin(4, newABin(2, newABin(1, NULL, NULL), newABin(3, NULL, NULL)), newABin(6, newABin(5, NULL, NULL), newABin(7, NULL, NULL))), newABin(12, newABin(10, newABin(9, NULL, NULL), newABin(11, NULL, NULL)), newABin(14, newABin(13, NULL, NULL), newABin(15, NULL, NULL))));
	LInt l = NULL;
	preorder(a, &l);
	printf("[ ");
	while(l != NULL){
		printf("%d ", l->valor);
		l = l->prox;
	}
	printf("]\n");
	return 0; 
}