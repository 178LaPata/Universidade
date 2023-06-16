#include <stdio.h>
#include <sys/types.h>
#include <unistd.h> /* chamadas ao sistema: defs e decls essenciais */
#include <fcntl.h> /* O_RDONLY, O_WRONLY, O_CREAT, O_* */
#include <stdlib.h>
#include <string.h>

ssize_t myreadln(int fd, char *line, size_t size){
    int read_bytes = 0;
    char buffer[1];

    while(read(fd,buffer,1)>0 && (read_bytes < size-1)){
        if(buffer[0] == '\n'){
            line[read_bytes] = buffer[0]; 
            read_bytes++;
            line[read_bytes] = '\0';
            return read_bytes;
        } else {
            line[read_bytes] = buffer[0];
            read_bytes++;
        }
    }
    line[read_bytes] = '\0';
    return read_bytes;
}


int mynl(char const *path){
    int fd = open(path, O_RDONLY, 0600);

    if(fd < 0){
        perror("Error opening file");
        return -1;
    }

    int read_bytes = 0;
    char buffer2[1024];
    char* buffer = malloc(sizeof(char) * 1024); // allocate memory for line    
    int linha = 1;
    char nr_str[4];

    while((read_bytes = myreadln(fd,buffer,1024))>0){
        snprintf(nr_str, 4, "%d", linha); // escreve o nr da linha no nr_str
        write(1, nr_str, strlen(nr_str)); // escreve o nr da linha
        write(1, " ", 1); // escreve o espaço
        write(1, buffer, read_bytes);
        linha++;
    }
    free(buffer);
    close(fd);
    return 0;
}

int main(int argc, char const *argv[])
{
    mynl("oi.txt");
    return 0;
}
