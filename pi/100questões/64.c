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

void appendL (LInt *l, int x){
    LInt aux = *l;
    LInt novo = newLInt(x, NULL);
    if(*l == NULL){ // lista vazia
        *l = novo;
        return;
    }
    while(aux->prox != NULL){ 
        aux = aux->prox;
    }
    aux->prox = novo;
}

int main(int argc, char const *argv[]) {
    LInt l = newLInt(1, newLInt(2, newLInt(3, NULL)));
    printf("Lista Inicial: ");
    imprimeL(l);
    appendL(&l, 4);
    printf("Lista Final: ");
    imprimeL(l);
    return 0;
}