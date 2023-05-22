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

void splitQS (LInt l, int x, LInt *mx, LInt *Mx){
    LInt aux = l;
    LInt antmx = NULL;
    LInt antMx = NULL;
    while(aux != NULL){
        if(aux->valor < x){
            if(antmx == NULL){
                *mx = aux;
            }
            else{
                antmx->prox = aux;
            }
            antmx = aux;
        }
        else{
            if(antMx == NULL){
                *Mx = aux;
            }
            else{
                antMx->prox = aux;
            }
            antMx = aux;
        }
        aux = aux->prox;
    }
    if(antmx != NULL) antmx->prox = NULL;
    if(antMx != NULL) antMx->prox = NULL;

}

int main(int argc, char const *argv[]) {
    LInt l = newLInt(1, newLInt(5, newLInt(15, newLInt(10, newLInt(3, newLInt(2, NULL))))));
    LInt mx = NULL;
    LInt Mx = NULL;
    splitQS(l, 10, &mx, &Mx);
    imprimeL(mx);
    printf("\n\n");
    imprimeL(Mx);
    return 0;
}