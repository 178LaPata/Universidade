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

LInt cloneL (LInt l){
    LInt aux = l;
    LInt ant = NULL;
    LInt oi;
    while(aux != NULL) {
        LInt novo = malloc(sizeof(LInt)); // aloca espaço para o novo elemento
        novo->valor = aux->valor; 
        if(!ant) { // se for o primeiro elemento
            oi = novo; 
        } else {
            ant->prox = novo;
        }
        ant = novo; 
        aux = aux->prox; 
    }
    return oi;
}

int main(int argc, char const *argv[]) {
    LInt l = newLInt(1, newLInt(2, newLInt(3, NULL)));
    LInt l2 = cloneL(l);
    imprimeL(l);
    imprimeL(l2);
    return 0;
}