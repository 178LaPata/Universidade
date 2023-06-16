#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>

typedef struct { 
    int x,y; 
} Ponto;

typedef struct LInt_nodo {
    int valor;
    struct LInt_nodo *prox;
} *LInt;


typedef struct nodo {
    int valor;
    struct nodo *pai, *esq, *dir;
} *ABin;

// 1
int perfeito(int x){
    int soma = 0;
    for (int i = 1; i <= x/2; i++){
        if (x % i == 0) soma += i;
    }
    return soma == x;
}

// 2
void ordena(Ponto pos[], int N){
    for (int i = 0; i < N; i++){
        for (int j = i; j < N; j++){
            if (pos[i].x > pos[j].x){
                Ponto temp = pos[i];
                pos[i] = pos[j];
                pos[j] = temp;
            }
            else if (pos[i].x == pos[j].x){
                if (pos[i].y > pos[j].y){
                    Ponto temp = pos[i];
                    pos[i] = pos[j];
                    pos[j] = temp;
                }
            }
        }
    }
}

// 3
int depth (ABin a, int x) {
    if(!a) return -1;
    if(a->valor == x) return 1; 
    else {
        if(depth(a->esq, x) == -1 && depth(a->dir, x) == -1) return -1;
        else if(depth(a->esq, x) == -1 && depth(a->dir, x) != -1) return 1 + depth(a->dir, x);
        else if(depth(a->esq, x) != -1 && depth(a->dir, x) == -1) return 1 + depth(a->esq, x);
        else if(depth(a->esq, x) != -1 && depth(a->dir, x) != -1){
            int e = depth(a->esq, x);
            int d = depth(a->dir, x);
            return 1 + (e < d ? e : d);
        }
    }
    return -1;
}

// 4
int wordle(char secreta[], char tentativa[]) {
    int acertos = 0;
    int i, j;
    int tamanho = strlen(secreta);

    for (i = 0; i < tamanho; i++) {
        if (secreta[i] == tentativa[i]) {
            acertos++;
            tentativa[i] = toupper(tentativa[i]);
        } else {
            int temCorrespondente = 0;
            for (j = 0; j < tamanho; j++) {
                if (secreta[j] == tentativa[i] && secreta[j] != tentativa[j]) {
                    temCorrespondente = 1;
                    break;
                }
            }
            if (!temCorrespondente) {
                tentativa[i] = '*';
            }
        }
    }

    return acertos;
}

// 5
LInt periodica(char s[]) {
}