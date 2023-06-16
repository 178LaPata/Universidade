#include <unistd.h> /* chamadas ao sistema: defs e decls essenciais */
#include <fcntl.h>
#include <stdio.h>
#include <stdlib.h>
#include <sys/wait.h>

int main(int argc, char const *argv[]){
    
    int res;
    int buffer[1024];
    pid_t pid;

    int fd[2];
    pipe(fd);

    if((pid=fork())==0){
        close(fd[1]);
        dup2(fd[0], 0);
        execlp("wc", "wc", NULL);
        close(fd[0]);
    } else { 
        close(fd[0]);
        while((res = read(0, buffer, 1024)) > 0){
            write(fd[1], buffer, res);
        }
        close(fd[1]);
        wait(NULL);
    }
    printf("Acabou\n");
    return 0;
}