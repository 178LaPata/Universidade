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

LInt NForward(LInt l, int N) {
    LInt aux = l;
    int i=0;
    if(l == NULL) {
        return NULL;
    }
    while(i<N){
        aux = aux->prox;
        i++;
    }
    return aux;
}

int main(int argc, char const *argv[]) {
    LInt l = newLInt(1, newLInt(2, newLInt(3, newLInt(4, NULL))));
    printf("Lista Inicial: ");
    imprimeL(l);
    int n = 3;
    printf("Avançando %d: ", n);
    imprimeL(NForward(l, n));
    return 0;
}