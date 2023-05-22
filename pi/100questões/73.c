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

LInt arrayToList (int v[], int N){
    LInt r = NULL;
    LInt *e = &r; // apontador para o endereço de r
    for(int i = 0; i < N; i++){ 
        *e = newLInt(v[i], NULL);
        e = &((*e)->prox); // e passa a apontar para o endereço do proximo elemento
    }
    return r;
}

int main(int argc, char const *argv[]){
    int v[] = {1,2,3,4,5};
    LInt l = arrayToList(v, 5);
    imprimeL(l);
    return 0;
}