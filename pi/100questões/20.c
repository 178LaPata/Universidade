#include <stdio.h>

int contaPal (char s[]){
    int i, count = 0;
    for(i = 0;s[i];i++){
        if (((s[i-1] == ' ') || (!s[i-1])) && ((s[i] != ' ') && (s[i] != '\n'))) count++;
        }
    return count;
}

int main(int argc, char const *argv[])
{
    char s[] = "\n \n";
    printf("%d\n", contaPal(s));
    return 0;
}
