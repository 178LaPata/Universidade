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

LInt rotateL (LInt l){
    LInt aux = l;
    if(l == NULL || l->prox == NULL){ // lista vazia ou apenas 1 elemento
        return l;
    }
    while(aux->prox != NULL){
        aux = aux->prox; 
    }
    aux->prox = l; // liga o último elemento ao primeiro
    l = l->prox; // o segundo elemento passa a ser o primeiro
    aux->prox->prox = NULL; // o primeiro elemento passa a ser o último
    return l;
}

int main(int argc, char const *argv[]) {
    LInt l = newLInt(1, newLInt(2, newLInt(3, newLInt(4, NULL))));
    printf("Lista Inicial: ");
    imprimeL(l);
    l = rotateL(l);
    printf("Lista Final: ");
    imprimeL(l);
    return 0;
}