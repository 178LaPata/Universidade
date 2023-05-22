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

int listToArray (LInt l, int v[], int N){
    int i;
    for(i = 0; i < N && l != NULL; i++){ 
        v[i] = l->valor; // preenche o array com os valores da lista
        l = l->prox; // avança na lista
    }
    return i;

}

int main(int argc, char const *argv[]) {
    LInt l = newLInt(1, newLInt(2, newLInt(3, newLInt(4, NULL))));
    int v[4];
    int i = listToArray(l, v, 4);
    printf("%d\n", i);
    return 0;
}