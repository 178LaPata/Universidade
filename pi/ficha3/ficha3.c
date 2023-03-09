#include <stdio.h>
/*
ex1

a.
int main () {
    int x [15] = {1, 2, 3, 4, 5,
                  6, 7, 8, 9,10,
                  11,12,13,14,15 };
    int *y, *z, i;
    y = x;
    z = x+3;
    for (i=0; i<5; i++) {
        printf ("%d %d %d\n",
            x[i], *y, *z);
        y = y+1; z = z+2;
    }
}
 
output:
1 1 4
2 2 6
3 3 8
4 4 10
5 5 12

b.
int main(){
    int i, j, *a, *b;

    i=3, j=5;
    a=b=42;
    a=&i; b=&j;
    i++;
    j = i + *b;
    b = a;
    j = j + *b;
    printf ("%d\n", j);

    return 0;
}

output: 13
*/

int printArray (int v[], int N){
    int i;
    for (i=0; i<N; i++) printf ("%d ", v[i]);
    putchar ('\n');
    return 0;
}

//ex2
void swapM (int *x, int *y){
    int tmp = *x;
    *x = *y;
    *y = tmp;
}

//ex3
void swap (int v[], int i, int j){
    int k =  v[i];
    v[i] = v[j];
    v[j] = k;
}

//ex4
int soma (int v[], int N){
    int i, s=0;
    for (i=0; i<N; i++)
        s += v[i];
    return s;
}

//ex5
void inverteArray1 (int v[], int N){
    int i, aux=N-1;
    for (i=0; i<N/2; i++){
        swap(v, i, aux-i);
    }
}

void inverteArray2 (int v[], int N){
    int i, aux=N-1;
    for (i=0; i<N/2; i++){
        swapM(&v[i], &v[aux-i]);
    }
}

//ex6
int maximum (int v[], int N, int *m){
    int i, max=v[0];
    for (i=1; i<N; i++){
        if (v[i]>max){
            max = v[i];
            *m = i;
        }
    }
    return max;
}

//ex7
void quadrados (int q[], int N){
    int i;
    for (i=0; i<N; i++){
        q[i] = i*i;
    }
}

//ex8a
//void pascal (int v[], int N){
//    int i, j, aux[N];
//    for (i=0; i<N; i++){
//        aux[i] = v[i];
//    }
//    for (i=0; i<N; i++){
//        if (i==0 || i==N-1) v[i] = 1;
//        else v[i] = aux[i-1] + aux[i];
//    }
//}

void pascal (int v[], int N) {
    if (N>1) {
        v[0] = v[N] = 1;
        int N1 = N-1, v1[N1];
        pascal(v1,N1);
        for (int i = N-1 ; i >= 1 ; i--) v[i] = v1[i] + v1[i-1];
    }
    else if (N==0) v[0] = 1;
    else if (N==1) {v[0] = 1; v[N] = 1;}
}

int main()
{  
    printf ("Testes\n");

    int v[10] = {10, 6, 2, 3, 5, 1, 5, 9, 8, 7};
    int x = 3, y = 5;

    printf ("Ex2\n");
    printf ("x = %d, y = %d\n", x, y);
    swapM (&x, &y);
    printf ("x = %d, y = %d\n", x, y);
    
    printf ("\nEx3\n");
    printf ("%d %d\n", v[0], v[9]);
    swap (v,0,9);
    printf ("%d %d\n", v[0], v[9]);
    
    printf ("\nEx4\n");
    x = soma (v,10);
    printf ("A soma dos elementos do array é %d.\n", x);
    
    printf ("\nEx5\n");
    printf("O array original é: \n");
    printArray (v,10);
    printf("O array invertido é: \n");
    inverteArray1 (v,10);
    printArray (v,10);
    inverteArray2 (v,10);
    printArray (v,10);

    printf ("\nEx6\n");
    x = maximum (v,10, &y);
    printf("O maior elemento do array é %d.\n", x);

    printf ("\nEx7\n");
    printf("Os quadrados são: \n");
    quadrados (v,10);
    printArray (v,10);

    printf("\nEx8\n");
    printf("A linha de Pascal é: \n");
    pascal (v,10);
    printArray (v,10);

    printf ("\nFim dos testes\n");

    return 0;
}