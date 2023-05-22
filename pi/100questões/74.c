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

LInt somasAcL(LInt l) {
    LInt r = NULL;
    LInt aux = NULL;
    int sum = 0;
    
    while (l != NULL) {
        sum += l->valor;
        aux = newLInt(sum, NULL);
        
        if (r == NULL) { 
            r = aux; 
        } else {
            LInt temp = r;
            while (temp->prox != NULL) {
                temp = temp->prox;
            }
            temp->prox = aux;
        }
        
        l = l->prox;
    }
    
    return r;
}

int main(int argc, char const *argv[]) {
    LInt l = newLInt(1, newLInt(2, newLInt(3, newLInt(4, NULL))));
    printf("Lista Inicial: ");
    imprimeL(l);
    printf("Lista Final: ");
    imprimeL(somasAcL(l));
    return 0;
}