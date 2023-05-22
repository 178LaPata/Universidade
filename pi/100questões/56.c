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

int removeOneOrd (LInt *l, int x) {
    int r = 1;
    LInt aux = *l;
    LInt ant = NULL;
    while(aux != NULL && aux->valor != x){ // procura o x
        ant = aux;
        aux = aux->prox;
    }
    if(aux == NULL) r = 1; // nao existe o x
    else{
        if(ant == NULL){ // x esta na head
            *l = aux->prox; // a head passa a ser o proximo do x
            free(aux);
        }
        else{
            ant->prox = aux->prox;
            free(aux);
        }
        r = 0; // x existe
    }
    return r;
}

int main(int argc, char const *argv[]) {
    LInt l = newLInt(1, newLInt(5, newLInt(15, newLInt(10, NULL))));
    
    removeOneOrd(&l, 5);
    imprimeL(l);
    return 0;
}