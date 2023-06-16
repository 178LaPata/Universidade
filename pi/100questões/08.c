#include <stdio.h>

char *mystrcpy (char *dest, char source[]){
    int i=0;
    for(; source[i] != 0; i++){
        dest[i] = source[i]; 
    }
    dest[i]='\0';
    return dest;
}

int main(int argc, char const *argv[]) {
    char source[] = "És gordo!";
    char dest[20];
    
    mystrcpy(dest, source);
    printf("%s\n", dest);

    return 0;
}