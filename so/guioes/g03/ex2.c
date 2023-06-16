#include <unistd.h> /* chamadas ao sistema: defs e decls essenciais */
#include <sys/wait.h> /* chamadas wait*() e macros relacionadas */

int main(int argc, char const *argv[]){
    if(fork() == 0) {
        execlp("ls", "ls", "-l", NULL);
        _exit(0);
    }
    else wait(NULL);
    return 0;
}
