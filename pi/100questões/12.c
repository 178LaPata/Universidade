#include <stdio.h>
#include <string.h>

void strnoV (char t[]){
    int i=0, j=0;
    for(;i<strlen(t);i++){
        if(t[i] != 'a' && t[i] != 'e' && t[i] != 'i' && t[i] != 'o' && t[i] != 'u' &&
           t[i] != 'A' && t[i] != 'E' && t[i] != 'I' && t[i] != 'O' && t[i] != 'U'){
            t[j] = t[i];
            j++;
        }
    }
    t[j] = '\0';
}

int main(){
    char s[100];
    printf("Insira uma string: ");
    scanf("%s", s);
    strnoV(s);
    printf("String sem vogais: %s\n", s);
    return 0;
}