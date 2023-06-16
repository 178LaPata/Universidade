#include <sys/types.h>
#include <sys/stat.h>
#include <stdio.h>

int main(int argc, char const *argv[]){
    if(mkfifo("fifo", 0666) < 0){
        perror("erro a criar fifo");
        return -1;
    }
    return 0;
}
