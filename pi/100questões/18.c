#include <stdio.h>
#include <string.h>

int maiorSufixo (char s1 [], char s2 []) {
    int len1 = strlen(s1);
    int len2 = strlen(s2);
    int i = len1 - 1, j = len2 - 1, maxLen = 0;
    for (; i >= 0 && j >= 0 && s1[i] == s2[j]; i--, j--);
    return len1 - i - 1;
}

int main(int argc, char const *argv[])
{
    char s1[] = "batota";
    char s2[] = "totalidade";
    printf("%d\n", maiorSufixo(s1, s2));
    return 0;
}
