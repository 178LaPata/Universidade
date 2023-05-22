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

void insertOrd (LInt *l, int x){
    LInt aux = *l;  // aponta para a head
    LInt ant = NULL;
    LInt novo = newLInt(x, NULL); // nó a inserir

    if(*l == NULL){ 
        *l = novo; // se a lista estiver vazia, o novo nó é a head
        return;
    }
    while(aux != NULL && aux->valor < x){ 
        ant = aux; 
        aux = aux->prox; 
    }
    if(ant == NULL){ // inserir no inicio
        novo->prox = *l; // a head passa a ser o proximo do novo nó
        *l = novo; // o novo nó passa a ser a head
    }
    else{ // inserir no meio 
        ant->prox = novo; // o proximo do anterior passa a ser o novo nó
        novo->prox = aux; // o proximo do novo nó passa a ser o atual
    }
}

int main(int argc, char const *argv[]) {
    LInt l = NULL;
    insertOrd(&l, 5);
    insertOrd(&l, 3);
    insertOrd(&l, 7);
    insertOrd(&l, 1);
    insertOrd(&l, 9);
    imprimeL(l);
    return 0;
}
