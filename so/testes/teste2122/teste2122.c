#include <stdio.h>
#include <stdlib.h>
#include <sys/wait.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <unistd.h>
#include <errno.h>
#include <string.h>

int mensagens(char* palavra, char* ficheiro){
    char buffer[20];
    int fd[2], fd2[2];
    pipe(fd);
    pipe(fd2);
    pid_t pid, pid2;
    
    if((pid=fork())==0){
        close(fd[0]); 
        dup2(fd[1], 1);
        close(fd[1]);

        execlp("grep", "grep", palavra, ficheiro, NULL);
        perror("Erro no grep");
        _exit(1);
    } else {
        close(fd[1]);
        wait(NULL);

        if((pid2=fork())==0){
            close(fd2[0]);
            dup2(fd[0], 0);
            close(fd[0]);
            dup2(fd2[1], 1);
            close(fd2[1]);

            execlp("wc", "wc", "-l", NULL);
            perror("Erro no wc");
            _exit(1);
        } else {
            close(fd[0]);
            close(fd2[1]);  
            wait(NULL);
            read(fd2[0], buffer, 20);
            close(fd2[0]);
        }
    }
    return atoi(buffer);
}

int autores_que_usaram(char* palavra, int n, char* autores[n]){
    int status, k=0, fd[2];
    pipe(fd);
    pid_t pid;

    for(int i = 0; i < n; i++){
        if((pid=fork())==0){
            if(mensagens(palavra, autores[i]) > 0) _exit(1);
            _exit(0);
        }
    }

    for(int i = 0; i < n; i++){
        wait(&status);
        if(WIFEXITED(status) && WEXITSTATUS(status)>0) k++;
    }
    return k;
}


int main(int argc, char const *argv[]){

    int n = mensagens("ola", "teste.txt");
    char* autores[2];
    autores[0] = "rozinha.txt";
    autores[1] = "titi.txt";

    printf("Numero de mensagens: %d\n", n);
    printf("Numero de autores: %d\n", autores_que_usaram("gay", 2, autores));

    return 0;
}
