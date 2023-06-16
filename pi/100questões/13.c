#include <stdio.h>
#include <ctype.h>
#include <string.h>

void truncW (char t[], int n){
    int i=0, j=0, k=0;
    for(;i<strlen(t);i++){
        if(t[i] == ' '){
            j=0;
            t[k] = t[i];
            k++;
        }
        else if(j<n){
            t[k] = t[i];
            k++;
            j++;
        }
    }
    t[k] = '\0';
}


int main(){
    char s[] = "liberdade, igualdade e fraternidade";
    truncW(s, 6);
    printf("String truncada: %s\n", s);
    return 0;
}


