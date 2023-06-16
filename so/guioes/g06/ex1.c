#include <unistd.h> /* chamadas ao sistema: defs e decls essenciais */
#include <fcntl.h>
#include <stdio.h>
#include <stdlib.h>


int main(int argc, char const *argv[]){
    
    int res;
    int buffer[100];

    int fdinput = dup(0);
    int fdoutput = dup(1);
    int fderro = dup(2);

    int fd_in = open("/etc/passwd", O_RDONLY);
    int fd_out = open("saida.txt", O_CREAT | O_TRUNC | O_WRONLY, 0666);
    int fd_err = open("erros.txt", O_CREAT | O_TRUNC | O_WRONLY, 0666);

    dup2(fd_in, 0);
    close(fd_in);

    dup2(fd_out, 1);
    close(fd_out);

    dup2(fd_err, 2);
    close(fd_err);

    while((res = read(0, buffer, 100)) > 0){
        write(1, buffer, res);
        write(2, buffer, res);
        fflush(stdout);
    }

    dup2(fdinput, 0);
    close(fdinput);

    dup2(fdoutput, 1);
    close(fdoutput);

    dup2(fderro, 2);
    close(fderro);

    printf("Acabou\n");
    return 0;
}
