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

LInt newLInt (int v, LInt t) {
    LInt new = (LInt) malloc (sizeof (struct lligada));
    
    if (new!=NULL) {
        new->valor = v;
        new->prox  = t;
    }
    return new;
}

ABin newABin (int r, ABin e, ABin d){
	ABin new = (ABin) malloc (sizeof (struct nodo));

	if (new!=NULL){
		new->valor = r;
		new->esq   = e;
		new->dir   = d;
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

void imprimeABin (ABin a){
    if (a != NULL){
        printf("%d ", a->valor);
        imprimeABin(a->esq);
        imprimeABin(a->dir);
    }
}

// 1
int sumhtpo (int n) {
    int r = 0, i = 0;
    for(; i<100; i++){
        r += n;
        if(n%2 == 0) n = n/2; else n = 1+(3*n);
    }
    return r;
}

// 2
int conta(int v[], int N, int x){
    int r = 0;
    for(int i=0; i<N; i++){
        if(v[i] == x) r++;
    }
    return r;
}

int moda(int v[], int N, int *m){
    if(N == 0) return 0;
    int r = 0, max = 0;
    while(r<N){
        int aux = conta(v, N, v[r]);
        if(aux > max){
            max = aux;
            *m = v[r];
        }
        r++;
    }
    return max;
}

// 3 
int procura (LInt *l, int x){
    LInt aux = *l;
    LInt ant = NULL;
    while(aux != NULL){
        if(aux->valor == x){
            if(ant != NULL){
                ant->prox = aux->prox; // o proximo do anterior passa a ser o proximo do x
                aux->prox = *l; // o proximo do x passa a ser a head
                *l = aux; // a head passa a ser o x
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
    if(a == NULL) return 0;
    int e = freeAB(a->esq);
    int d = freeAB(a->dir);
    free(a);
    return e + d + 1;
}

// 5 -> nao faco ideia


int main(int argc, char const *argv[]) {
    printf("---------- Questão 1 ----------\n");
    printf("%d\n", sumhtpo(100));

    printf("---------- Questão 2 ----------\n");
    int v[] = {1, 2, 3, 4, 5, 6, 7, 8, 2, 2, 2};
    int m;
    printf("%d\n", moda(v, 11, &m));    

    printf("---------- Questão 3 ----------\n");
    LInt l = newLInt(1, newLInt(2, newLInt(3, NULL)));

    printf("%d\n", procura(&l, 2));
    printf("Lista Final: ");
    imprimeL(l);

    printf("---------- Questão 4 ----------\n");
    ABin a = newABin(1, newABin(2, newABin(3, NULL, NULL), newABin(4, NULL, NULL)), newABin(5, NULL, NULL));
    printf("%d\n", freeAB(a));

    printf("---------- Questão 5 ----------\n");
    

    return 0;
}
