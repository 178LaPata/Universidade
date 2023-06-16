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
int pesquisa (int a[], int N, int x){
    for(int i=0; i<N; i++){
        if(a[i] == x) return i;
    }
    return -1;
}

// pesquisa binária aka mais eficiente
int pesquisa(int a[], int N, int x) {
    int esquerda = 0;
    int direita = N - 1;

    while (esquerda <= direita) {
        int meio = (esquerda + direita) / 2;
        if (a[meio] == x) return meio; 
        else if (a[meio] < x) esquerda = meio + 1;
        else direita = meio - 1;
    }
    return -1;
}

// 2
void roda(LInt *l) {
    if (!(*l) || !(*l)->prox) return;
    LInt aux = *l; 
    LInt last = NULL;

    while (aux->prox) {last = aux; aux = aux->prox;} 
    aux->prox = *l; 
    *l = aux;
    last->prox = NULL;
}

// 3
int apaga (ABin a, int n){    
    if(!a) return 0;
    int count = 1 + apaga(a->esq, n) + apaga(a->dir, n);
    if(count <= n){
        free(a);
        return count;
    }
    return count;
}

// 4 nao sei se esta bem
void checksum(char s[]) {
    int len = strlen(s);
    int sum = 0;
    int doubleDigit = 0;

    for (int i = len - 1; i >= 0; i--) {
        int digit = s[i] - '0';

        if (doubleDigit) {
            digit *= 2;
            if (digit > 9) {
                digit = digit / 10 + digit % 10;
            }
        }

        sum += digit;
        doubleDigit = !doubleDigit;
    }

    int controlDigit = (10 - (sum % 10)) % 10;
    s[len] = controlDigit + '0';
    s[len + 1] = '\0';
}

// 5 nao sei se esta bem
int escolhe(int N, int valor[], int peso[], int C, int quant[]) {
    int i, j;
    int **dp = (int **)malloc((N + 1) * sizeof(int *));
    for (i = 0; i <= N; i++) {
        dp[i] = (int *)malloc((C + 1) * sizeof(int));
    }

    for (i = 0; i <= N; i++) {
        for (j = 0; j <= C; j++) {
            if (i == 0 || j == 0)
                dp[i][j] = 0;
            else if (peso[i - 1] <= j)
                dp[i][j] = max(valor[i - 1] + dp[i][j - peso[i - 1]], dp[i - 1][j]);
            else
                dp[i][j] = dp[i - 1][j];
        }
    }

    int res = dp[N][C];
    for (i = N, j = C; i > 0 && j > 0; i--) {
        if (dp[i][j] != dp[i - 1][j]) {
            quant[i - 1]++;
            j = j - peso[i - 1];
        }
    }

    for (i = 0; i <= N; i++) {
        free(dp[i]);
    }
    free(dp);

    return res;
}

