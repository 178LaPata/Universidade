#include <stdio.h>
#include <stdlib.h>

typedef struct LInt_nodo {
    int valor;
    struct LInt_nodo *prox;
} *LInt;


typedef struct nodo {
    int valor;
    struct nodo *pai, *esq, *dir;
} *ABin;

// 1
int nesimo(int a[], int N, int i){
    int j, k, aux;
    for(j=0; j<N; j++){
        for(k=j; k<N; k++){
            if(a[j] > a[k]){
                aux = a[j];
                a[j] = a[k];
                a[k] = aux;
            }
        }
    }
    return a[i-1];
}

// 2
LInt removeMaiores(LInt l, int x){
    LInt aux = l;
    if(!aux) return NULL;
    while(aux->prox){
        if(aux->prox->valor > x){
            LInt temp = aux->prox;
            aux->prox = aux->prox->prox;
            free(temp);
        }
        else aux = aux->prox;
    }
    return l;
}

// 3 
LInt caminho(ABin a, int x){
    if(!a) return NULL;
    if(a->valor == x){
        LInt aux = malloc(sizeof(LInt));
        aux->valor = x;
        aux->prox = NULL;
        return aux;
    }
    else if(a->valor > x){
        LInt aux = caminho(a->esq, x);
        if(!aux) return NULL;
        LInt temp = malloc(sizeof(LInt));
        temp->valor = a->valor;
        temp->prox = aux;
        return temp;
    }
    else{
        LInt aux = caminho(a->dir, x);
        if(!aux) return NULL;
        LInt temp = malloc(sizeof(LInt));
        temp->valor = a->valor;
        temp->prox = aux;
        return temp;
    }
}

// 4


// 5 acho que esta bem 
int sacos(int p[], int N, int C){
    int count=0, i, sacos=0;
    
    for(i=0;i<N;i++){
        count += p[i];
        if(count > C){
            sacos++;
            count -= C;
        }
    }
    
    if(count <= C) sacos++;
    return sacos;
}

// uma solucao melhor/mais correta ig
int sacos(int p[], int N, int C) {
    int sacos = 0;
    int cap = 0;

    for (int i = 0; i < N; i++) {
        if (cap + p[i] <= C) cap += p[i];
        else {sacos++; cap = p[i];}
    }

    if (cap > 0) sacos++;
    return sacos;
}

