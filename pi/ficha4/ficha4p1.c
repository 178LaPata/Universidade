#include <stdio.h>
#include <string.h>

int contaVogais (char *s){
    int count = 0;
    for (int i = 0; s[i] != '\0'; i++){
        if (s[i] == 'a' || s[i] == 'e' || s[i] == 'i' || s[i] == 'o' || s[i] == 'u' || s[i] == 'A' || s[i] == 'E' || s[i] == 'I' || s[i] == 'O' || s[i] == 'U'){
            count++;
        }
    }
    return count;
}

int retiraVogaisRep(char *s){
    char aux[strlen(s)];
    int pos = 0;
    int count = 0;
    int i;

    for(i=0; s[i]!='\0'; i++){
        if(s[i] == 'a' || s[i] == 'e' || s[i] == 'i' || s[i] == 'o' || s[i] == 'u' || s[i] == 'A' || s[i] == 'E' || s[i] == 'I' || s[i] == 'O' || s[i] == 'U'){
            while(s[i] == s[i+1]){
                i++;
                count++;
            }
        }
        aux[pos]=s[i];
        pos++;
    }
    for(i=0; i<pos; i++){
        s[i]=aux[i];
    } 
    s[i]='\0';
    return count;    
}

int duplicaVogais(char *s){
    char aux[300];
    int pos = 0;
    int count = 0;
    int i;

    for(i=0; s[i]!='\0'; i++){
        if(s[i] == 'a' || s[i] == 'e' || s[i] == 'i' || s[i] == 'o' || s[i] == 'u' || s[i] == 'A' || s[i] == 'E' || s[i] == 'I' || s[i] == 'O' || s[i] == 'U'){
            aux[pos]=s[i];
            pos++;
            count++;
        }
        aux[pos]=s[i];
        pos++;
    }
    for(i=0; i<pos; i++){
        s[i]=aux[i];
    } 
    s[i]='\0';
    return count;
}

int main(){
    printf ("\n-------------- Testes --------------\n");

    char s1 [100] = "Estaa e umaa string coom duuuplicadoos";
    int x;
    
    printf ("A string \"%s\" tem %d vogais\n", s1, contaVogais (s1));
    
    x = retiraVogaisRep(s1);
    printf ("Foram retiradas %d vogais, resultando em \"%s\"\n", x, s1);
    
    x = duplicaVogais(s1);
    printf ("Foram acrescentadas %d vogais, resultando em \"%s\"\n", x, s1);

    printf ("\n---------- Fim dos Testes ----------\n");

    return 0;
}