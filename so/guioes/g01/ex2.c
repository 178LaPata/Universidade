#include <stdio.h>
#include <sys/types.h>
#include <unistd.h> /* chamadas ao sistema: defs e decls essenciais */
#include <fcntl.h> /* O_RDONLY, O_WRONLY, O_CREAT, O_* */

int mycat(){
    int read_bytes = 0; 
    char buffer[1024]; 

    while((read_bytes=read(0,buffer,1024))>0){ // read from stdin
        write(1,buffer,read_bytes); // write to stdout
    }
    return 0;
}

int main(int argc, char const *argv[]){
    mycat();
    return 0;
}
