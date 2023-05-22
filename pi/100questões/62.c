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

int removeMaiorL (LInt *l){
    int maior = (*l)->valor;
    LInt aux = *l;
    while(aux != NULL){
        if(aux->valor > maior){ 
            maior = aux->valor; 
        }
        aux = aux->prox;
    }
    aux = *l;
    if(aux->valor == maior){
        *l = aux->prox;
        free(aux);
        return maior;
    }
    while(aux->prox != NULL){
        if(aux->prox->valor == maior){ 
            LInt temp = aux->prox;
            aux->prox = temp->prox; 
            free(temp);
            return maior;
        }
        aux = aux->prox;
    }
    return maior;
}

int main(int argc, char const *argv[]) {
    LInt l = newLInt(1, newLInt(2, newLInt(3, NULL)));
    printf("Lista Inicial: ");
    imprimeL(l);
    printf("Maior: %d\n", removeMaiorL(&l));
    printf("Lista Final: ");
    imprimeL(l);
    return 0;
}