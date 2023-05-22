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

int drop (int n, LInt *l){
    LInt aux = *l;
    LInt ant = NULL;
    int i=0;
    
    for(i; i<n && aux!=NULL; i++){
        ant = aux;
        aux = aux->prox;
        free(ant); 
    }
    if(aux == NULL){ 
        *l = NULL;
        return i;
    }
    else{
        *l = aux; 
        return i;
    }
}

int main(){
    LInt l = newLInt(1, newLInt(2, newLInt(3, newLInt(4, newLInt(5, NULL)))));
    printf("Lista Inicial: ");
    imprimeL(l);
    printf("Número de elementos removidos: %d\n", drop(2, &l));
    printf("Lista Final: ");
    imprimeL(l);
    return 0;
}