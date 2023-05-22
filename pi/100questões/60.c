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

int removeAll (LInt *l, int x) {
    LInt aux = *l;
    LInt ant = NULL;
    int r = 0;
    while(aux != NULL){
        if(aux->valor == x){
            if(ant == NULL){
                *l = aux->prox; // a head passa a ser o proximo do x
                free(aux);
                aux = *l;
            }
            else{
                ant->prox = aux->prox; // o proximo do anterior passa a ser o proximo do x
                free(aux);
                aux = ant->prox;
            }
            r++;
        }
        else{
            ant = aux; 
            aux = aux->prox; 
        }
    }
    return r;
}

int main(int argc, char const *argv[]) {
    LInt l = newLInt(1, newLInt(2, newLInt(3, newLInt(2, newLInt(4, newLInt(2, newLInt(5, NULL)))))));
    
    printf("Lista Inicial: ");
    imprimeL(l);
    printf("Removidos: %d\n", removeAll(&l, 2));
    printf("Lista Final: ");
    imprimeL(l);

    return 0;
}