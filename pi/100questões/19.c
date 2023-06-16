#include <stdio.h>

int sufPref (char s1[], char s2[]) {
    int i = 0, j = 0, maxLen = 0;
    while (s1[i]) {
        if (s1[i] == s2[j]) {
            maxLen++;
            j++;
        } else {
            maxLen = 0;
            j = 0;
        }
        i++;
    }
    return maxLen;
}


int main(int argc, char const *argv[])
{
    char s1[] = "batota";
    char s2[] = "totalidade";
    printf("%d\n", sufPref(s1, s2));
    return 0;
}
