#include <stdio.h>
#include <unistd.h>
#include <sys/wait.h>

int main(int argc, char *argv[]){
    printf("PID: %d\n", getpid());
    printf("PID do Pai: %d", getppid());

    sleep(10); // so para conseguir ver as merdas no terminal
    return 0;
}   