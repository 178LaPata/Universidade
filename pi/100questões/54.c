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

LInt reverseL (LInt l) {
    LInt antes = NULL;
    LInt atual = l;
    LInt prox = NULL;
        while(atual != NULL){
        prox = atual->prox;
        atual->prox = antes;
        antes = atual;
        atual = prox;
    }
    return antes;

}

int main(int argc, char const *argv[]) {
    LInt l = newLInt(1, newLInt(2, newLInt(3, NULL)));
    l = reverseL(l); 
    imprimeL(l);
    return 0;
}
