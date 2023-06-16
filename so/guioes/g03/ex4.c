#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <unistd.h>
#include <string.h>

int mysystem(char *command){

    pid_t pid;
    int status;
    int i = 0;
    char *pal = strdup(command); 
    char *argumentos[10];
    char *key;

    while((key = strsep(&pal, " ")) != NULL && i < 9){
        argumentos[i] = strdup(key);
        i++;
    }

    argumentos[i] = NULL;
    int k;

    if((pid = fork()) == 0){
        k = execvp(argumentos[0], argumentos);
        _exit(k);
     }

     wait(&status); 

     for(i=0; argumentos[i] != NULL; i++){
        free(argumentos[i]);
     }
     free(pal);
     return 0;
}

int main(int argc, char const *argv[]){
    char command1[] = "ls -l";
    char command2[] = "ps";
    char command3[] = "pwd";

    printf("Executando o comando: %s\n", command1);
    mysystem(command1);
    printf("\nExecutando o comando: %s\n", command2);
    mysystem(command2);
    printf("\nExecutando o comando: %s\n", command3);
    mysystem(command3);
}
