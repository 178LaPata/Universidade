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


// nao esta a funcionar direito, nao sei porque
LInt parteAmeio(LInt *l) {
    LInt aux = *l;
    LInt ant = NULL;
    LInt oi;
    int len = 0;

    if (aux == NULL || aux->prox == NULL) { // lista vazia ou com 1 elemento
        return NULL;
    }
    while(aux != NULL) {
        len++;
        aux = aux->prox;
    }
    aux = *l;

    while(len/2>0) {
        LInt novo = malloc(sizeof(LInt));
        novo->valor = aux->valor;
        if(!ant) {
            oi = novo;
        } else {
            ant->prox = novo;
        }
        ant = novo;
        aux = aux->prox;
        len--;
    }
    return oi;
}


int main(int argc, char const *argv[]) {
    LInt l = newLInt(11, newLInt(12, NULL));
    LInt y = parteAmeio(&l);
    imprimeL(l);
    printf("\n");
    imprimeL(y);
    return 0;
}