#include <stdio.h>

void dumpV (int v[], int N){
    int i;
    putchar ('{');
    for (i=0; i<N; i++) printf ("%2d ", v[i]);
    putchar ('}');
}
void simNao (int x){
    if (!x) printf (" não");
}

int ordenado (int a[], int N){
    int i;
    for (i=0; i<N-1; i++)
        if (a[i] > a[i+1]) return 0;
    return 1;
}

void merge (int a[], int na, int b[], int nb, int r[]){
    int i, j, k;
    for (i=0, j=0, k=0; i<na && j<nb; k++)
        if (a[i] < b[j]){
            r[k] = a[i]; i++;
        } else {
            r[k] = b[j]; j++;
        }
    while (i<na){ 
        r[k] = a[i]; i++; k++; 
    }
    while (j<nb){
        r[k] = b[j]; j++; k++; 
    }
}

//funcao que ordena o array
void ordenaArray(int v[], int N){
    int i, j, temp;
    for (i=0; i<N-1; i++)
        for (j=i+1; j<N; j++)
            if (v[i] > v[j]){
                temp = v[i]; 
                v[i] = v[j]; 
                v[j] = temp;
            }
}
// nao esta a acabada ig
int partition (int v[], int N, int x){  
    int i, j, temp;
    for (i=0; i<N-1; i++)
        for (j=i+1; j<N; j++)
            if (v[i] > v[j]){
                temp = v[i]; 
                v[i] = v[j]; 
                v[j] = temp;
            }
    for (i=0; i<N; i++)
        if (v[i] > x) return i;
    return N;
}

int main() {  
    int a [15] = {10,3,45,56,8,23,13,42,77,31,18,88,24,45,1},
        b [10] = {4,12,34,45,48,52,61,73,84,87}, 
        c [10] = {1,3,8,22,33,35,38,41,44,49}, 
        d [50];
    int x,y;
    
    printf ("\n-------------- Testes --------------\n");

    printf("O array ");
    dumpV(a,15);
    simNao(ordenado (a,15)); 
    printf (" está ordenado\n");
    printf("O array "); 
    dumpV(b,10);
    simNao(ordenado (b,10)); 
    printf (" está ordenado\n");
    
    printf("\nArray 1 -> "); 
    dumpV (b,10);
    printf("\nArray 2 -> "); 
    dumpV (c,10);
    merge (b, 10, c, 10, d);
    printf("\nArray resultante -> "); 
    dumpV (d,20);

    printf ("\n\nArray -> "); 
    dumpV (a,15);
    printf ("\nQual o valor a utilizar na partição?\n");
    scanf ("%d", &y); 
    ordenaArray (a,15);
    x = partition (a,15,y); 
    
    printf ("\nNumero de elementos do array resultante -> %d\n", x);

    printf ("\n---------- Fim dos Testes ----------\n");
    return 0;
}