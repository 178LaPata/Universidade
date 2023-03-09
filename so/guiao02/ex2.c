#include <unistd.h>
#include <sys/wait.h>
#include <stdio.h>

int main(int argc, char *argv[]){
    pid_t pid;
    int status;

    if((pid = fork()) == 0){
        printf("child pid: %d\n", getpid());
        printf("child ppid: %d\n", getppid());
        _exit(0);
    }
    printf("parent pid: %d\n", getpid());
    printf("parent ppid: %d\n", getppid());

    if(wait(&status)>0){
        if (WIFEXITED(status)){
            printf("exit value %d\n" , WEXITSTATUS(status));
        }
        else {
            printf("bad exit\n");
        }
    }
    printf("parent is exiting...\n");
}
