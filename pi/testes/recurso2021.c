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
int paresImpares(int v[], int N){
    int i, j, aux;
    for(i = 0; i < N; i++){
        if(v[i] % 2 == 0){
            for(j = i; j > 0 && v[j-1] % 2 != 0; j--){
                aux = v[j];
                v[j] = v[j-1];
                v[j-1] = aux;
            }
        }
    }
    return i;
    
}

// 2
void merge (LInt *r, LInt a, LInt b){
    LInt aux = NULL;
    if(*r==NULL){
        *r = newLInt(0,NULL); 
        aux = *r;
    } else {
        aux = *r;
        for(; aux->prox!=NULL; aux = aux->prox);
    }
    for(; a != NULL && b != NULL; aux = aux->prox){
        if(a->valor > b->valor){
            aux->prox = a;
            a = aux->prox;
        } else {
            aux->prox = b;
            b = aux->prox;
        }
    }
    if(!a) aux->prox = b;
    else aux->prox = a;
    aux = *r;
    *r = (*r)->prox;
    free(aux);
}

int main(int argc, char const *argv[]) {
    printf("---------- Questão 1 ----------\n");
    int v[] = {1,2,3,4,5,6,7,8,9,10};
    printf("%d\n", paresImpares(v, 10));
    printf("[ ");
    for(int i = 0; i < 10; i++){
        printf("%d ", v[i]);
    }
    printf("]\n");

    printf("---------- Questão 2 ----------\n");
    LInt l1 = newLInt(1, newLInt(3, newLInt(5, NULL)));
    LInt l2 = newLInt(2, newLInt(4, newLInt(6, NULL)));
    LInt r = NULL;
    merge(&r, l1, l2);
    imprimeL(r);

    printf("---------- Questão 3 ----------\n");

    printf("---------- Questão 4 ----------\n");

    printf("---------- Questão 5 ----------\n");
    

    return 0;
}
