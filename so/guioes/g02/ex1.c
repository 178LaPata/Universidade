#include <stdio.h>
#include <unistd.h> /* chamadas ao sistema: defs e decls essenciais */
#include <sys/wait.h> /* chamadas wait*() e macros relacionadas */

int main(int argc, char const *argv[])
{
    printf("pid %d\n", getpid());
    printf("pid do pai %d\n", getppid());
    return 0;
}
