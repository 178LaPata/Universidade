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

int length (LInt l) {
    int r = 0;
    while(l != NULL){ 
        r++;
        l = l->prox;
    }
    return r;
}

int main(int argc, char const *argv[]) {
    LInt l = newLInt(1, newLInt(2, newLInt(3, newLInt(4, NULL))));
    printf("%d\n", length(l));
    return 0;
}
