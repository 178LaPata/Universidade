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
    while(l != NULL){
        printf("%d\n", l->valor);
        l = l->prox;
    }
}

int main(int argc, char const *argv[]) {
    LInt l = newLInt(1, newLInt(2, newLInt(3, NULL)));
    imprimeL(l);
    return 0;
}
