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

LInt cloneRev (LInt l){
    LInt clone = NULL;
    while(l != NULL){
        clone = newLInt(l->valor, clone);
        l = l->prox;
    }
    return clone;
}

int main(int argc, char const *argv[]) {
    LInt l = newLInt(1, newLInt(2, newLInt(3, NULL)));
    printf("Lista Inicial: ");
    imprimeL(l);
    LInt clone = cloneRev(l);
    printf("Lista Final: ");
    imprimeL(clone);
    return 0;
}