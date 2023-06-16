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

int main(int argc, char const *argv[]) {
	ABin a = newABin(8, newABin(4, newABin(2, newABin(1, NULL, NULL), newABin(3, NULL, NULL)), newABin(6, newABin(5, NULL, NULL), newABin(7, NULL, NULL))), newABin(2, newABin(10, newABin(9, NULL, NULL), newABin(11, NULL, NULL)), newABin(14, newABin(13, NULL, NULL), newABin(15, NULL, NULL))));
	imprimeABin(a);
	printf("\n");
	printf("%d\n", depth(a, 2));
	return 0;
}