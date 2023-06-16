#include <stdio.h>
#include <sys/types.h>
#include <unistd.h> /* chamadas ao sistema: defs e decls essenciais */
#include <fcntl.h> /* O_RDONLY, O_WRONLY, O_CREAT, O_* */

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

int main(int argc, char const *argv[]){
    int fd;
    char buffer[1024];
    int read_bytes;

    if((fd = open("oi.txt", O_RDONLY)) < 0){ perror("Error opening file"); return -1; }
    read_bytes = myreadln(fd, buffer, 1024);    
    printf("%s\n", buffer);

    close(fd);
    return 0;
}