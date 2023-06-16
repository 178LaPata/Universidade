#include <stdio.h>

int mystrcmp(char s1[], char s2[]){
    int i=0, j=0;
    for(;s1[i]!='\0' || s2[i]!='\0';i++){
        if (s1[i]>s2[i]){
            return 1;
        } else if (s1[i]<s2[i]){
            return -1;
        }
    }
     return 0;
}

int main(int argc, char const *argv[]){
    char s1[] = "ola";
    char s2[] = "ola";

    printf("%d\n", mystrcmp(s1,s2));

    return 0;
}