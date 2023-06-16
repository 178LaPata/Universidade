#include <unistd.h> /* chamadas ao sistema: defs e decls essenciais */
#include <sys/wait.h> /* chamadas wait*() e macros relacionadas */

int main(int argc, char *argv[]){
    pid_t pid;
    int status;

    for(int i = 1; i < argc; i++){
        pid = fork();
        if(pid == 0){
            execlp(argv[i], argv[i], NULL);
            _exit(0);
        }
    }
    for(int i = 1; i < argc; i++){
        wait(&status);
    }
    return 0;
}
