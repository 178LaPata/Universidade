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

void concatL (LInt *a, LInt b){
    LInt aux = *a;
    if(*a == NULL){ // se for lista vazia a lista a passa a ser a lista b
        *a = b;
    } else{
        while(aux->prox != NULL){ // percorre a lista a até ao fim
        aux = aux->prox;
    }
    aux->prox = b; // adiciona a lista b no fim da lista a
    }
}

int main(int argc, char const *argv[]) {
    LInt l = newLInt(1, newLInt(2, newLInt(3, NULL)));
    LInt l2 = newLInt(4, newLInt(5, newLInt(6, NULL)));
    printf("Lista Inicial: ");
    imprimeL(l);
    printf("Lista a concatenar: ");
    imprimeL(l2);
    concatL(&l, l2);
    printf("Lista Final: ");
    imprimeL(l);
    return 0;
}