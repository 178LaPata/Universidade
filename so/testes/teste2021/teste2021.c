#include <stdio.h>
#include <stdlib.h>
#include <sys/wait.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <unistd.h>
#include <errno.h>
#include <string.h>

int vacinados(char* regiao, int idade){
    char buffer[20];
    int fd[2], fd2[2];
    pipe(fd);
    pipe(fd2);
    pid_t pid, pid2;
    char idades[20];

    if((pid=fork())==0){
        close(fd[0]);
        dup2(fd[1], 1);
        close(fd[1]);

        sprintf(idades, "%d", idade);
        execlp("grep", "grep", idades, strcat(regiao, ".txt"), NULL);
        perror("Erro no grep");
        _exit(1);
    } else {
        close(fd[1]);
        wait(NULL);

        if((pid2=fork())==0){
            close(fd2[0]);
            dup2(fd[0], 0);
            close(fd[0]);
            dup2(fd2[1], 1);
            close(fd2[1]);
            execlp("wc", "wc", "-l", NULL);
            perror("Erro no wc");
            _exit(1);
        } else {
            close(fd[0]);
            close(fd2[1]);
            wait(NULL);
            read(fd2[0], buffer, 20);
            close(fd2[0]);
        }
    }
    return atoi(buffer);
}

bool vacinado(char* cidadao){
    int status;
    pid_t pid[10];
    char ficheiro[10];


    for(int i=0; i<10; i++){
        if((pid[i]=fork())==0){
            sprintf(ficheiro, "%d.txt", i);

            execlp("grep", "grep", cidadao, ficheiro, NULL);
            perror("erro no grep");
            _exit(0);
        }
    }

    for(int j=0; j<10; j++){
        waitpid(pid[j], &status, 0);
        if(WIFEXITED(status) && WEXITSTATUS(status)==0) return true;
    }
    return false;
}   
