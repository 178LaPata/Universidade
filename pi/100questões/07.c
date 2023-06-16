#include <stdio.h>

char *mystrcat (char s1[], char s2[]){
    int i=0, j=0;

    for(;s1[i]!='\0';i++);
    for(; s2[j]!='\0';j++, i++){
        s1[i]=s2[j];
    }

    s1[i]='\0';
    return s1;
}


int main(){
    char s1[20] = "oi";
    char s2[] = "cringe";

    mystrcat(s1,s2);
    printf("%s\n",s1);

    return 0;
}