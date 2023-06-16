#include <stdio.h>
#include <ctype.h>
#include <string.h>

char charMaisfreq (char s[]){
    int i, j, max=0, count;
    char c;
    for(i=0;i<strlen(s);i++){
        count = 0;
        for(j=0;j<strlen(s);j++){
            if(s[i] == s[j]) count++;
        }
        if(count > max){ max = count; c = s[i]; }
    }
    return c;
}

int main(int argc, char const *argv[])
{
    char s[50] = "Olllllllllaaaaaaaaaaaaa";
    printf("Caracter mais frequente: %c\n", charMaisfreq(s));
    return 0;
}
