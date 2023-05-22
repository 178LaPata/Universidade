#include <stdio.h>
#include <stdlib.h>

typedef struct lligada {
    int valor;
    struct lligada *prox;
} *LInt;

LInt newLInt (int v, LInt t) {
    LInt new = (LInt) malloc (sizeof (struct lligada));
    
    if (new!=NULL) {
        new->valor = v;
        new->prox  = t;
    }
    return new;
}

void imprimeL (LInt l) {
    printf("[ ");
    while(l != NULL){
        printf("%d ", l->valor);
        l = l->prox;
    }
    printf("]\n");
}

LInt parte (LInt l){
    LInt r = l->prox; 
    LInt aux = r;
    if(l == NULL || l->prox == NULL){
        return NULL;
    }
    l->prox = r->prox; 
    l = l->prox;
    while(l != NULL && l->prox != NULL){
        aux->prox = l->prox; // adiciona o elemento a lista dos pares
        aux = aux->prox; 
        l->prox = aux->prox; // remove o elemento da lista dos impares
        l = l->prox;
    }
    aux->prox = NULL;
    return r;
}

int main(int argc, char const *argv[]){
    LInt l = newLInt(1, newLInt(2, newLInt(3, newLInt(4, newLInt(5, newLInt(6, NULL))))));
    printf("Lista Inicial: ");
    imprimeL(l);
    LInt r = parte(l);
    printf("Listas Finais:\n");
    printf("Impares: ");
    imprimeL(l);
    printf("Pares: ");
    imprimeL(r);
    return 0;
}

