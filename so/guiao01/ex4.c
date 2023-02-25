#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <unistd.h>
#include <fcntl.h>
#include "ex3.h" 

int mynl(char *from_path){

    char line[1024];
    int contador = 0;

    int from_fd = open(from_path, O_RDONLY);

    for (int i = 0; (i = myreadln(from_fd, line, 1024)) != 0; contador++){

        char number[1024];
        snprintf(number, 1024, "%d", contador);

        write(1, number, 1);
        write(1, " ", 1);
        write(1, line, i);
        //write(1, "\n", 1);
    
    }
    close(from_fd);

    return 0;
}

int main (int argc, char* argv[]){

    int i = mynl(argv[1]);

    return 0;
}
/*
// resolucao aula stor
#define  MAX_BUFFER 4096

ssize_t readC(int fd, char *buf){
    return read(fd,buf,1);
}

ssize_t readln(int fd, char *line, size_t size){
    size_t next_pos = 0;
    size_t total_bytes_read = 0;

    while((total_bytes_read = readC(fd,line + next_pos)) > 0 && total_bytes_read < size ) {
        
        total_bytes_read++;

        if(line[next_pos] == '\n'){
             return total_bytes_read;;
        }

        next_pos++;
        
    }
    return total_bytes_read;
}

int main(int argc, char* argv[]){
    int line_counter = 0;
    char buffer[MAX_BUFFER];
    int bytes_read = 0;
    int newline = 1;

    while((bytes_read = readln(STDIN_FILENO, buffer, MAX_BUFFER)) >  0){
        char line_number[10] = "";
        // nl skips empty lines
        if(newline && buffer[0] != '\n'){
            snprintf(line_number, 10, "id: ", line_counter);
            write(STDOUT_FILENO, line_number, sizeof(line_number));
            line_counter++;
        }
        write(STDOUT_FILENO, buffer,bytes_read);

        // buffer was not big enough to hold the whole line, continue reading the line
        if(buffer[bytes_read - 1] != '\n' ){
            newline = 0;
        } else{
            newline = 1;
        }
    }
}
*/