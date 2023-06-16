#include <stdio.h>

int maiorPrefixo (char s1 [], char s2 []) {
    int i;
    for (i = 0; s1[i] && s2[i] && s1[i] == s2[i]; i++);
    return i;
}

int main(int argc, char const *argv[])
{
    char s1[] = "ola";
    char s2[] = "ol";
    printf("%d\n", maiorPrefixo(s1, s2));
    return 0;
}
