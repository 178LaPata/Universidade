#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct celula {
    char *palavra;
    int ocorr;
    struct celula * prox;
} * Palavras;


void libertaLista (Palavras l){
    if(!l) return;
    Palavras aux = l;
    while(l->prox){
        aux = l->prox;
        free(l->palavra);
        free(l);
        l = aux;
    }
    free(l->palavra);
    free(l);
}

int quantasP (Palavras l){
    if(!l) return 0;
    Palavras aux = l;
    int count = 0;
    while(aux){
        count++;
        aux = aux->prox;
    }
    return count;
}

void listaPal (Palavras l){
    if(!l) return;
    int count = 1;
    Palavras aux = l;
    while(aux){
        printf("\n %d - Palavra: %s |   Ocorrencias: %d\n", count++, aux->palavra, aux->ocorr);
        aux = aux->prox;
    }
}

char * ultima (Palavras l){
    if(!l) return NULL;
    Palavras aux = l;
    while(aux->prox){
        aux = aux->prox;
    }
    return aux->palavra;
}

Palavras acrescentaInicio(Palavras l, char *p) {
    Palavras novo = malloc(sizeof(struct celula));
    novo->palavra = strdup(p);
    novo->ocorr = 1;
    novo->prox = l;
    return novo;
}

Palavras acrescentaFim (Palavras l, char *p){
    Palavras novo = malloc(sizeof(struct celula));
    novo->palavra = strdup(p);
    novo->ocorr = 1;
    novo->prox = NULL;
    return novo;
}

Palavras acrescenta (Palavras l, char *p){
    if (!l) {
        Palavras novo = malloc(sizeof(struct celula));
        novo->palavra = strdup(p);
        novo->ocorr = 1;
        novo->prox = NULL;
        return novo;
    }
    Palavras aux = l;
    while(aux){
        if(strcmp(aux->palavra, p) == 0){
            aux->ocorr++;
            return l;
        }
        aux = aux->prox;
    }
    Palavras novo = malloc(sizeof(struct celula));
    novo->palavra = strdup(p);
    novo->ocorr = 1;
    novo->prox = l;
    return novo;
}

struct celula * maisFreq (Palavras l){
    if(!l) return NULL;
    int max = 0;
    Palavras aux = l;
    Palavras freq = NULL;
    while(aux){
        if(aux->ocorr > max){
            max = aux->ocorr;
            freq = aux;
        }
        aux = aux->prox;
    }
    return freq;
}


int main () {
    Palavras dic = NULL;

    char * canto1 [44] = {"as", "armas", "e", "os", "baroes", "assinalados",
                          "que", "da", "ocidental", "praia", "lusitana", 
                          "por", "mares", "nunca", "de", "antes", "navegados",
                          "passaram", "ainda", "alem", "da", "taprobana",
                          "em", "perigos", "e", "guerras", "esforcados",
                          "mais", "do", "que", "prometia", "a", "forca", "humana",
                          "e", "entre", "gente", "remota", "edificaram", 
                          "novo", "reino", "que", "tanto", "sublimaram"};

    printf ("\n_____________ Testes _____________\n\n");

    int i; struct celula *p;
    for (i=0;i<44;i++)
        dic = acrescentaInicio (dic, canto1[i]);

    printf ("Foram inseridas %d palavras\n", quantasP (dic));
    printf ("\nPalavras existentes:\n");
    listaPal (dic);
    printf ("\nÚltima palavra inserida: %s\n", ultima (dic));

    libertaLista (dic);
    
    dic = NULL;
    
    srand(42);
    
    for (i=0; i<1000; i++)
        dic = acrescenta (dic, canto1 [rand() % 44]);
       
    printf ("\nForam inseridas %d palavras\n", quantasP (dic));
    printf ("\nPalavras existentes:\n");
    listaPal (dic);
    printf ("\nÚltima palavra inserida: %s\n", ultima (dic));
    
    p = maisFreq (dic);
    printf ("Palavra mais frequente: %s (%d)\n", p->palavra, p->ocorr);
    
    printf ("\n_________ Fim dos testes _________\n\n");

    return 0;
}

