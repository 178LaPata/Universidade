#include <stdio.h>
/*
ex1

1.
int main(){
    int x, y;
    x = 3; y = x+1;
    x = x*y; y = x + y;
    printf("%d %d\n", x, y);
    return 0;
}

output: x=12 y=16

2.
int main(){
    int x, y;
    x = 0;
    printf ("%d %d\n", x, y);
    return 0;
}

output: x=0 y=0

3.
int main(){
    char a, b, c;
    a = 'A'; b = ' '; c = '0';
    printf ("%c %d\n", a, a);
    a = a+1; c = c+2; 
    printf ("%c %d %c %d\n", a, a, c, c);
    c = a + b;
    printf ("%c %d\n", c, c);
    return 0;
}

output: 
A 65
B 66 2 50
b 98

4.
int main(){
    int x, y;
    x = 200; y = 100;
    x = x+y; y = x-y; x = x-y;
    printf ("%d %d\n", x, y);
    return 0;    
}

output: x=100 y=200

ex2

a.
int main(){
    int x,y;
    x=3; y=5;
    if(x>y)
        y=6;
    printf("%d %d\n", x, y);
    return 0;
}

output: x=3 y=5

b.
int main(){
    int x, y;
    x = y = 0;
    while (x != 11) {
        x = x+1; y += x;
    }
    printf ("%d %d\n", x, y);
    return 0;
}

output: x=11 y=66

c.
int main(){
    int i;
    for (i=0; (i<20) ; i++)
        if (i%2 == 0) putchar ('_');
        else putchar ('#');
    return 0;
}

output: _#_#_#_#_#_#_#_#_#_#

d.
void f (int n) {
    while (n>0) {
        if (n%2 == 0) putchar ('0');
        else putchar ('1');
        n = n/2;
    }
    putchar ('\n');
}
int main () {
    int i;
    for (i=0;(i<16);i++)
        f (i);
    return 0;
}

output: 
1
01
11
001
101
011
111
0001
1001
0101
1101
0011
1011
0111
1111
*/

void quadrado (int n){
    for(int j=0; j<n; j++){
        for(int i=0; i<n;i++){
            printf("%c", '#');
        }
        printf("%c", '\n');
    }
}

void xadrez (int n){
    for(int j=0; j<n; j++){
        if(j%2==0){
            for(int i=0; i<n;i++){
                if(i%2==0){
                    printf("%c", '#');
                } else {
                    printf("%c", '_');
                }
            }
            printf("%c", '\n');
        } else {
            for(int i=0; i<n;i++){
                if(i%2==0){
                    printf("%c", '_');
                } else {
                    printf("%c", '#');
                }
            }
            printf("%c", '\n');
        }
    }
}

void trianguloH (int n){
    for(int j=0; j<n; j++){
        for(int i=0; i<j;i++){
            printf("%c", '#');
        }
        printf("%c", '\n');
    }
    for(int j=n; j>0; j--){
        for(int i=0; i<j;i++){
            printf("%c", '#');
        }
        printf("%c", '\n');
    }
}

void trianguloV (int n){
    for(int j=0; j<=n; j++){
        for (int i=0; i<n-j; i++){
            printf("%c", ' ');
        }
        for (int i=0; i<j; i++){
            printf("%c", '#');
        }
        for (int i=0; i<j-1; i++){
            printf("%c", '#');
        }
        for (int i=0; i<n-j; i++){
            printf("%c", ' ');
        }
        printf("%c", '\n');
    }
}

int circulo (int raio){
    int x,y;
    for(y=raio ; y>=-raio ; y--){
        for(x=-raio ; x<=raio ; x++) {
            if (x*x + y*y <= raio * raio) printf("%c", '#');
            else printf("%c", ' ');
        }
        printf("%c", '\n');

    }
    return 0;
}

int main()
{
    quadrado (5);
    printf("%c", '\n');
    xadrez (5);
    printf("%c", '\n');
    trianguloH (5);
    printf("%c", '\n');
    trianguloV (5);
    printf("%c", '\n');
    circulo (4);
    return 0;
}
