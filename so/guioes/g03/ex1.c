#include <unistd.h> /* chamadas ao sistema: defs e decls essenciais */

int main(int argc, char const *argv[]){
    
    execlp("ls", "ls", "-l", NULL);
    return 0;
}
