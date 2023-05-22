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

int maximo (LInt l){
    int max = l->valor;
    LInt aux = l;
    while(aux != NULL){
        if(aux->valor > max){
            max = aux->valor;
        }
        aux = aux->prox;
    }
    return max;
}

int main(int argc, char const *argv[]) {
    LInt l = newLInt(1, newLInt(2, newLInt(3, NULL)));
    printf("Lista Inicial: ");
    imprimeL(l);
    printf("Maior valor armazenado: %d\n", maximo(l));
    printf("Lista Final: ");
    imprimeL(l);
    return 0;
}