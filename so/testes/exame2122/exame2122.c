#include <stdio.h>
#include <stdlib.h>
#include <sys/wait.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <unistd.h>
#include <errno.h>
#include <string.h>

int comando(const char* filtro, const char* entrada, const char* saida){
    int fd_ent = open(entrada, O_RDONLY | O_CREAT, 0600);
    int fd_sai = open(saida, O_WRONLY | O_CREAT, 0600);
    int status;
    pid_t pid;

    if((pid=fork())==0){
        dup2(fd_ent, 0);
        dup2(fd_sai, 1);
        close(fd_ent);
        close(fd_sai);
        
        execlp(filtro, filtro, NULL);
        perror("Erro no exec");
        _exit(1);
    }

    close(fd_ent);
    close(fd_sai);
    wait(&status);

    if(!WIFEXITED(status) && WEXITSTATUS(status)<0) return 0;
    return 1;
}

