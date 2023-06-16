#include <string.h>
#include <stdio.h>

// da erro na codeboard 
int difConsecutivos(char s[]) {
    int maxLen = 0, currentLen = 1, i;

    for(i=1; s[i] != '\0'; i++) {
        if (s[i] != s[i - 1]) currentLen++;
        else {
            if (currentLen > maxLen) maxLen = currentLen;
            currentLen = 1;
        }
    }
    if (currentLen > maxLen) maxLen = currentLen;
    return maxLen;
}

int main(int argc, char const *argv[])
{
    char s[30] = "aabcccaaa";
    printf("Resultado: %d\n", difConsecutivos(s)); 
    return 0;
}
