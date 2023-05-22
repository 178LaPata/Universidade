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

int take (int n, LInt *l){
    LInt aux = *l;
    LInt ant = NULL;
    int i;
    
    for(i = 0; i < n && aux != NULL; i++){ // percorre a lista até ao nó n
        ant = aux;
        aux = aux->prox;
    }
    if(aux == NULL){
        return i;
    }
    while(aux != NULL){ // liberta os nós a partir do nó n
        LInt next = aux->prox;
        free(aux);
        aux = next;
    }
    if (ant != NULL){ 
        ant->prox = NULL; // o ant passa a ser o último nó da lista
    }
    else{ 
        *l = NULL; // lista vazia
    }
    return i;
}

int main(int argc, char const *argv[]){
    LInt l = newLInt(61, newLInt(62, newLInt(63, newLInt(64, newLInt(65, newLInt(66, NULL))))));
    printf("Lista Inicial: ");
    imprimeL(l);
    int n = take(5, &l);
    printf("%d\n", n);
    printf("Lista Final: ");
    imprimeL(l);
    return 0;
}