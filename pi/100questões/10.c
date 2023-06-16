#include <stdio.h>
#include <string.h>


char *mystrstr (char s1[], char s2[]) {
	int i=0;
	int j=1;

	if(s2[0] == '\0') return s1;
	if(s1[0] == '\0') return NULL;

	for(; s1[i] != '\0'; i++){
		if(s1[i] == s2[0]){
			for(; s2[j] != '\0' && s1[i+j] == s2[j]; j++);
			if(s2[j] == '\0') return &s1[i];
		}
	}
    return NULL;
}


int main() {
    char s1[] = "Hello, world!";
    char s2[] = "world";
    
    char *result = strstr(s1, s2);

    if (result != NULL)
        printf("A string '%s' foi encontrada em '%s' na posição %ld.\n", s2, s1, result - s1);
    else
        printf("A string '%s' não foi encontrada em '%s'.\n", s2, s1);

    return 0;
}
