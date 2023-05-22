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

void init (LInt *l){
    LInt aux = *l;
    if(*l == NULL || (*l)->prox == NULL){
        *l = NULL;
    } else{
        while(aux->prox->prox != NULL) {
            aux = aux->prox; 
        } // quando chega aqui, aux está no penúltimo elemento
        free(aux->prox);
        aux->prox = NULL; 
    }
}

int main(int argc, char const *argv[]){
    LInt l = newLInt(1, newLInt(2, newLInt(3, newLInt(4, NULL))));
    printf("Lista Inicial: ");
    imprimeL(l);
    init(&l);
    printf("Lista Final: ");
    imprimeL(l);
    return 0;
}