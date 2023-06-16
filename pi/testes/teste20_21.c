#include <stdio.h>
#include <stdlib.h>

typedef struct lligada {
    int valor;
    struct lligada *prox;
} *LInt;

typedef struct nodo {
    int valor;
    struct nodo *pai, *esq, *dir;
} *ABin;

// 1
int sumhtpo (int n) {
    int r = 0, i;
    for(i=0; i<100; i++) {
        r += n;
        if (n%2 == 0) n = n/2; else n = 1+(3*n);
    }
    return r;
}

// 2
int moda(int v[], int N, int *m){
    int i, j=0, count=0, r=0, freq=0;
    for(i=0; i<N; i++){
        for(; j<N; j++){
            if(v[i] == v[j]) count++;
        }
        if(count>freq){
            freq = count;
            r = v[i];
        }
        count=0;
    }
    if(freq==1) return 0;
    *m=r;
    return freq;
}

// 3
int procura (LInt *l, int x){
    LInt aux = *l;
    LInt ant = NULL;

    while(aux != NULL){
        if(aux->valor == x){
            if(ant != NULL){
                ant->prox = aux->prox;
                aux->prox = *l;
                *l = aux;
            }
            return 1;
        }
        ant = aux;
        aux = aux->prox;
    }
    return 0;
}

// 4
int freeAB(ABin a){
    if(!a) return 0;
    int r = 1 + freeAB(a->esq) + freeAB(a->dir);
    free(a);
    return r;
}

// 5 - esta mal
void caminho(ABin a){
    if(!a) return;
    if(a->pai){
        if(a->pai->esq == a) printf("esq\n");
        else printf("dir\n");
    }
    caminho(a->pai);
}
