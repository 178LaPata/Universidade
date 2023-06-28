#include <stdio.h>
#include <stdlib.h>
#include <sys/wait.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <unistd.h>
#include <errno.h>
#include <string.h>

int mail(char* ficheiro){
    int fd_ent = open(ficheiro, O_RDONLY, 0600);
    int processos=0;
    pid_t pid = fork();
    char linha[10];

    while(read(fd_ent, linha, 10)>0){
        processos++;    
        if(processos>=10){
            wait(NULL);
            processos--;
        }
        if(pid==0){
            char nrAluno[7];
            char email[24];
            strcpy(nrAluno, linha);
            nrAluno[7]='\0';
            sprintf(email, "%s@alunos.uminho.pt", nrAluno);

            execlp("mail", "mail", "-s", "Sistemas_Operativos", email, linha, NULL);
            perror("Erro no exec");
            _exit(1);
        }
    }
    close(fd_ent);
    while(processos>0){
        wait(NULL);
        processos--;
    }
    return 0;
}
