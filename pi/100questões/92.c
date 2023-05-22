#include <stdio.h>
#include <stdlib.h>

typedef struct nodo {
	int valor;
	struct nodo *esq, *dir;
} *ABin;

ABin newABin (int r, ABin e, ABin d){
	ABin new = (ABin) malloc (sizeof (struct nodo));

	if (new!=NULL){
		new->valor = r;
		new->esq   = e;
		new->dir   = d;
	}
	return new;
}

void imprimeABin (ABin a){
    if (a != NULL){
        printf("%d ", a->valor);
        imprimeABin(a->esq);
        imprimeABin(a->dir);
    }
}


int contaFolhas (ABin a) {
	if(!a) return 0;

	int e = contaFolhas(a->esq); 
	int d = contaFolhas(a->dir);

	if(!a->esq && !a->dir) return 1 + e + d; // se nao tiver filhos devolve a soma dos filhos + 1
	else return e + d; // se tiver filhos devolve a soma dos filhos
}


int main(int argc, char const *argv[]) {
	ABin a = newABin(8, newABin(4, newABin(2, newABin(1, NULL, NULL), newABin(3, NULL, NULL)), newABin(6, newABin(5, NULL, NULL), newABin(7, NULL, NULL))), newABin(2, newABin(10, newABin(9, NULL, NULL), newABin(11, NULL, NULL)), newABin(14, newABin(13, NULL, NULL), newABin(15, NULL, NULL))));
	imprimeABin(a);
	printf("\n");
	printf("%d\n", contaFolhas(a));
	return 0;
}