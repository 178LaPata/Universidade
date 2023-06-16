#include <stdio.h>
#include <sys/types.h>
#include <unistd.h> /* chamadas ao sistema: defs e decls essenciais */
#include <fcntl.h> /* O_RDONLY, O_WRONLY, O_CREAT, O_* */

#define MAX_BUFFER_SIZE 1024

void mycp(char const *from_path, char const *to_path){

    char buffer[MAX_BUFFER_SIZE];
    int fd = open(from_path, O_RDONLY, 0600);
    int bytes = read(fd, buffer, MAX_BUFFER_SIZE);

    int fd2 = open(to_path, O_WRONLY, 0600); 
    int bytes2 = write(fd2, buffer, bytes);

    close(fd);
    close(fd2);
}

int main(int argc, char const *argv[]){
    mycp("oi.txt","ola.txt");
    return 0;
}
