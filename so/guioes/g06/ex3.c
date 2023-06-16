#include <unistd.h> /* chamadas ao sistema: defs e decls essenciais */
#include <fcntl.h>
#include <stdio.h>
#include <stdlib.h>


int main(int argc, char *argv[]){
    
    int res;
    int buffer[100];
    pid_t pid;

    int fd_in = open("/etc/passwd", O_RDONLY);
    int fd_out = open("saida.txt", O_CREAT | O_TRUNC | O_WRONLY, 0666);
    int fd_err = open("erros.txt", O_CREAT | O_TRUNC | O_WRONLY, 0666);

    dup2(fd_in, 0);
    close(fd_in);

    dup2(fd_out, 1);
    close(fd_out);

    dup2(fd_err, 2);
    close(fd_err);

    execlp("wc", "wc", NULL);

    printf("Acabou\n");
    return 0;
}

