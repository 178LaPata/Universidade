#include <stdio.h>
#include <stdlib.h>
#include <sys/wait.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <unistd.h>
#include <errno.h>
#include <string.h>

void defeitos(char* imagens[n], int n, int max){
    pid_t pid;
    int processos=0;

    for(int i=0; i<n; i++){
        if(processos>=max){
            wait(NULL);
            processos--;
        }
        if((pid=fork())==0){
            execl("./detectAnom", "./detectAnom", imagens[i], NULL);
            perror("Erro no exec");
            _exit(1);
        }
        processos++;
    }
    for(int i=0; i<processos; i++){
        wait(NULL);
    }
}

void conta(char* imagens[n], int n){
    int fd[2], status;
    pipe(fd);
    pid_t pid, pid2;
    if((pid=fork())==0){
        close(fd[0]);
        dup2(fd[1], 1);
        close(fd[1]);

        defeitos(imagens, n, n);
        _exit(0);
    } else { 
        wait(NULL);
        close(fd[1]);
        if((pid2=fork())==0){
            dup2(fd[0], 0);
            close(fd[0]);

            execlp("wc", "wc", "-l", NULL);
            perror("Erro no wc");
            _exit(1);
        }
        close(fd[0]);
        wait(NULL);
    }
}