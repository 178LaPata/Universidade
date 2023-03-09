#include <sys/types.h>
#include <unistd.h> /* chamadas ao sistema: defs e decls essenciais */
#include <fcntl.h> /* O_RDONLY, O_WRONLY, O_CREAT, O_* */
#include <stdio.h>


int mycp(char const * from_path, char const * to_path){
    int from_fd = open(from_path, O_RDONLY);
    if (from_fd < 0) {
        // imprimir erro e sair
        printf("Erro");
    }

    int to_fd = open(to_path, O_CREAT | O_TRUNC | O_WRONLY, 0640);
    // verificar to_fd < 0
    if (to_fd < 0){
        printf("Erro");
    }

    int read_bytes = 0;
    char buf[20];

    while((read_bytes = read(from_fd, buf, 20)) > 0){
        write(to_fd, buf, read_bytes);
    }

    close(from_fd);
    close(to_fd);

    return 0;
}

// ./mycp from to
int main (int argc, char* const * argv){
    return mycp(argv[1], argv[2]);
}

