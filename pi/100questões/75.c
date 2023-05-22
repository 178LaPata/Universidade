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

void remreps (LInt l){
    LInt atual = l;
    while(atual != NULL && atual->prox != NULL){
        if(atual->prox->valor == atual->valor){
            LInt temp = atual->prox;
            atual->prox = temp->prox;
            free(temp);
        }
        else{
            atual = atual->prox;
        }
    }
}

int main(int argc, char const *argv[]) {
    LInt l = newLInt(1, newLInt(1, newLInt(2, newLInt(3, newLInt(3, NULL)))));
    printf("Lista Inicial: ");
    imprimeL(l);
    remreps(l);
    printf("Lista Final: ");
    imprimeL(l);
    return 0;
}