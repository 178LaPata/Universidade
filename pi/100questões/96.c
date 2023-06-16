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

int depthOrd (ABin a, int x) {
    if(!a) return -1;
    if(a->valor == x) return 1;
    if(a->valor > x){
		int e = depthOrd(a->esq, x);
		if(e == -1) return -1; 
		return 1 + e; 
	} else {
		int d = depthOrd(a->dir, x);
		if(d == -1) return -1;
		return 1 + d;
	}
}

int main(int argc, char const *argv[]) {
	ABin a = newABin(80, newABin(40, newABin(20, newABin(10, NULL, NULL), newABin(30, NULL, NULL)), newABin(60, newABin(50, NULL, NULL), newABin(70, NULL, NULL))), newABin(120, newABin(100, newABin(90, NULL, NULL), newABin(110, NULL, NULL)), newABin(140, newABin(130, NULL, NULL), newABin(150, NULL, NULL))));
	imprimeABin(a);
	printf("\n");
	printf("%d\n", depthOrd(a, 5));
	return 0;
}