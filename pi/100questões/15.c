#include <string.h>
#include <stdio.h>

int iguaisConsecutivos (char s[]) {
    int i, j, max = 0, count = 1;
    for (i = 0; s[i] != '\0'; i++) {
        for (j = i + 1; s[j] != '\0' && s[j] == s[i]; j++) {
        count++;
        }
        if (count > max) max = count;
        count = 1;
    }
    return max;
}

int main(int argc, char const *argv[])
{
    char s[10] = "aabcccaaa";
    printf("Resultado: %d\n", iguaisConsecutivos(s));
    return 0;
}
