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

void merge (LInt *r, LInt a, LInt b) {
    LInt aux = NULL;
    if (*r == NULL) { // se a lista estiver vazia cria um nó com valor 0
        *r = newLInt(0, NULL);
        aux = *r;
    } else { // percorre a lista até ao ultimo nó
        aux = *r; 
        while (aux->prox != NULL)
            aux = aux->prox;
    }
    while(a != NULL && b != NULL){
        if(a->valor < b->valor){ 
            aux->prox = a; 
            a = a->prox; 
        }
        else{
            aux->prox = b;
            b = b->prox;
        }
        aux = aux->prox; // avança para o proximo nó
    }
    if(a == NULL) aux->prox = b; // se a lista a for a primeira a acabar
    else aux->prox = a; // se a lista b for a primeira a acabar
    aux = *r; // aponta para a head
    *r = (*r)->prox; // remove o nó com valor 0
    free(aux);
}

int main(int argc, char const *argv[]) {
    LInt l1 = newLInt(1, newLInt(3, newLInt(5, NULL)));
    LInt l2 = newLInt(2, newLInt(4, newLInt(6, NULL)));
    LInt r = NULL;
    merge(&r, l1, l2);
    imprimeL(r);
    return 0;
}