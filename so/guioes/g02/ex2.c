#include <stdio.h>
#include <unistd.h> /* chamadas ao sistema: defs e decls essenciais */
#include <sys/wait.h> /* chamadas wait*() e macros relacionadas */

int main(int argc, char const *argv[]){
    pid_t pid;
    int status;

    if((pid=fork()) == 0){
        printf("pid %d\n", getpid());
        printf("pid do pai %d\n", getppid());
        _exit(0);
    } else {
        printf("Pid %d\n", getpid());
        printf("Pid do pai %d\n", getppid());
        if(wait(&status)>0){ 
            if(WIFEXITED(status)) printf("valor de saida %d\n", WEXITSTATUS(status));
            else printf("erro a sair\n");
        }
        printf("a sair\n");
    }
    return 0;
}