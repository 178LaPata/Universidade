#include <stdio.h>
#include <unistd.h> /* chamadas ao sistema: defs e decls essenciais */
#include <sys/wait.h> /* chamadas wait*() e macros relacionadas */

int main(int argc, char const *argv[]){
    
    pid_t pid;
    int status;

    for(int i=0;i<=10;i++){
        if((pid=fork())==0){
            printf("pid %d\n", getpid());
            printf("pid pai %d\n", getppid());
            _exit(i);
        }
    }
    for(int j=0;j<=10;j++){
        if(wait(&status)>0){
            if(WIFEXITED(status)) printf("valor de saida %d\n", WEXITSTATUS(status));
            else printf("erro a sair\n");
        }
    }
    return 0;
}
