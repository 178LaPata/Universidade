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
int paresImpares(int v[], int N){
    int i, j=0, count=0;
    int aux;
    for(i=0; i<N; i++){
        if(v[i]%2 == 0) count++;
    }

    for(i=0; i<N; i++){
        if(v[i]%2 == 0){
            aux = v[i];
            v[i] = v[j];
            v[j] = aux;
            j++;
        }
    }
    return count;
}

// 2 
void merge (LInt *r, LInt a, LInt b) {
    if(!a){*r = b; return;} 
    if(!b){*r = a; return;}

    LInt ant = NULL;

    while(a && b){
        LInt aux = malloc(sizeof(LInt));
        if(a->valor < b->valor){aux->valor = a->valor; a = a->prox;}
        else{aux->valor = b->valor; b = b->prox;}

        if(!ant) *r = aux;
        else ant->prox = aux;
        ant = aux;
    }
    if(!a) ant->prox = b;
    if(!b) ant->prox = a;
}

// 3
void latino (int N, int m[N][N]){
    int i, j;
    for(i=0; i<N; i++){
        for(j=0; j<N; j++){
            m[i][j] = (i+j)%N + 1; 
        }
    }
}

// 4
ABin next (ABin a){
    if(!a) return NULL;
    if(a->dir){
        a = a->dir;
        while(a->esq) a = a->esq; 
        return a;
    }
    while(a->pai && a->pai->dir == a) a = a->pai;
    if(!a->pai) return NULL;
    return a->pai;
}

// 5 - nao me apetece fazer