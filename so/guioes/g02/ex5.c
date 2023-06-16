#include <unistd.h>
#include <stdio.h>
#include <sys/wait.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>

// da seg fault mas nao sei porque
int main(int argc, char *argv[]){

    int needle = atoi(argv[1]); 
    int row = 10;
    int collumn = 1000;
    int rand_max = 1000;
    int status;
    int **matrix;

    srand(time(NULL));  

    matrix = (int**) malloc(sizeof(int*)*row);
    for (int i = 0; i < row; i++){
        matrix[i] = (int*) malloc(sizeof(int)*collumn);
        for (int j = 0; j < collumn ; j++){
            matrix[i][j]= rand() % rand_max;
        }
    }

    for(int r=0; r<10; r++){
        pid_t pid = fork();
        if(pid == 0){
            printf("pid do filho %d - %d\n", r+1, getpid());
            for(int j=0; j<collumn; j++){
                if(needle==matrix[r][j]){
                    printf("encontrado na linha %d coluna %d\n", r+1, j+1);
                    _exit(r+1);
                }
            }
            _exit(-1); 
        }
    }

    for(int h=0; h<10; h++){
        pid_t pidFilho = wait(&status);

        if(WIFEXITED(status)) printf("filho %d encontou na linha %d\n", pidFilho, WEXITSTATUS(status));
    }
    return 0;
}

