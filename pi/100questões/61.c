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

int removeDups(LInt *l) {
    int r = 0;
    LInt atual = *l;

    while (atual != NULL) {
        LInt aux = atual;
        
        while (aux->prox != NULL) {
            if (aux->prox->valor == atual->valor) { // se o valor do proximo for igual ao atual
                LInt tmp = aux->prox; // tmp = proximo
                aux->prox = tmp->prox; // proximo = proximo->prox
                free(tmp); 
                r++; 
            } else {
                aux = aux->prox; 
            }
        }
        atual = atual->prox; 
    }
    
    return r;
}


int main(int argc, char const *argv[]){
    LInt l = newLInt(2, newLInt(2, newLInt(21, newLInt(25, newLInt(21, newLInt(25, newLInt(28, newLInt(21, newLInt(30, newLInt(3, NULL))))))))));
    printf("Lista Inicial: ");
    imprimeL(l);
    printf("Removidos: %d\n", removeDups(&l));
    printf("Lista Final: ");
    imprimeL(l);
    return 0;
}