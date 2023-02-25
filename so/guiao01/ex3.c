#include <sys/types.h>
#include <unistd.h> /* chamadas ao sistema: defs e decls essenciais */
#include <fcntl.h> /* O_RDONLY, O_WRONLY, O_CREAT, O_* */

// resolucao aula 
int readch(int fd, char *buf){
    return read(fd, buf, 1);
}

ssize_t myreadln(int fd, char *line, ssize_t size){
    int next_pos = 0;
    int read_bytes = 0;

    while (next_pos < size && readch(fd, line + next_pos) > 0){
        read_bytes++;
        if (line[next_pos] == '\n'){
            break;
        }
        next_pos++;
    }
    return read_bytes;
}

// minha resolucao
int myreadln1(int fd, char *line, size_t size) {

  int i = 0;
  char c;
  for(i=0;(read(fd, &c, 1)) == 1 && i < size && c != '\n' ;i++) {

    line[i] = c;
  }
  line[i] = '\n';
  
  return i;
}