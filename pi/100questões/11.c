#include <stdio.h>
#include <string.h>

void strrev(char s[]) {
    char aux;
    int len = strlen(s);
    int i = 0, j = len -1;

    for (; i < j; i++, j--) {
        aux = s[i];
        s[i] = s[j];
        s[j] = aux;
    }
}

int main(int argc, char const *argv[]){
    char str[] = "Hello, World!";
    printf("String original: %s\n", str);
    strrev(str);
    printf("String invertida: %s\n", str);
    return 0;
}

