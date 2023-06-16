#include <unistd.h> /* chamadas ao sistema: defs e decls essenciais */
#include <sys/wait.h> /* chamadas wait*() e macros relacionadas */
#include <stdio.h>

int main(int argc, char const *argv[]){
    int fd[2];
    __pid_t pid;

    if(pipe(fd) == -1){
        perror("erro a abrir o pipe");
        return 1;
    }

    if((pid = fork()) == 0){
        close(fd[0]);
        int y = 178;
        write(fd[1], &y, sizeof(int));
        printf("O filho escreveu %d no pipe \n", y);

        int h = 140;
        write(fd[1], &h, sizeof(int));
        printf("O filho escreveu %d no pipe \n", h);
        close(fd[1]);
    }
    else{
        close(fd[1]);
        wait(NULL);
        int x;
        read(fd[0], &x, sizeof(int));
        printf("O pai leu %d do pipe \n", x);

        int z;
        read(fd[0], &z, sizeof(int));
        printf("O pai leu %d do pipe \n", z);

        close(fd[0]);
    }
    return 0;
}

