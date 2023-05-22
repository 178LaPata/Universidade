#include <stdio.h>
#include <stdlib.h>
#include <string.h>


typedef struct aluno {
    int numero;
    char nome[100];
    int miniT[6];
    float teste;
} Aluno;

//calcula a nota de um aluno
int nota (Aluno a){
    double notaFinal, notaMT, soma;
    for(int i=1; i<6; i++){
        soma += a.miniT[i];
    }
    notaMT = soma*0.2;
    if(notaMT > 0.25){
        return (int) (a.teste*0.8 + notaMT);
    } else
        return 0;
}

//procura um aluno pelo numero, retorna -1 se nao encontrar
int procuraNum (int num, Aluno t[], int N){
    int i;
    for(i=0; i<N; i++){
        if(t[i].numero == num){
            return i;
        }
    }
    return -1;
}

//ordena os alunos por ordem crescente
void ordenaPorNum (Aluno t[], int N){
    int i, j;
    Aluno tmp;
    for(i=0; i<N; i++){
        for(j=i+1; j<N; j++){
            if(t[i].numero > t[j].numero){
                tmp = t[i];
                t[i] = t[j];
                t[j] = tmp;
            }
        }
    }
}

//igual a procuraNum, mas procura num array de indices
//esta mal
int procuraNumInd (int num, int ind[], Aluno t[], int N){
    int i;
    for(i=0; i<N; i++){
        if(t[i].numero == num){
            return ind[i];
        }
    }
    return -1;
}

// igual a ordenaPorNum, mas ordena um array de indices
void criaIndPorNum (Aluno t[], int N, int ind[]){
    int i, j;
    Aluno tmp;
    for(i=0; i<N; i++){
        for(j=i+1; j<N; j++){
            if(t[i].numero > t[j].numero){
                tmp = t[i];
                t[i] = t[j];
                t[j] = tmp;
            }
        }
    }
    for(i=0; i<N; i++){
        ind[i] = t[i].numero;
    }
}

// igual a criaIndPorNum, mas ordena por nome
void criaIndPorNome (Aluno t [], int N, int ind[]){
    int i, j;
    Aluno tmp;
    for(i=0; i<N; i++){
        for(j=i+1; j<N; j++){
            if(strcmp(t[i].nome, t[j].nome) > 0){
                tmp = t[i];
                t[i] = t[j];
                t[j] = tmp;
            }
        }
    }
    for(i=0; i<N; i++){
        ind[i] = t[i].numero;
    }    
}

void imprimeTurma (int ind[], Aluno t[], int N){
    int i;
    for(i=0; i<N; i++){
        printf("%d %s %d %d %d %d %d %d %f %d\n", t[i].numero, t[i].nome, t[i].miniT[0], t[i].miniT[1], t[i].miniT[2], t[i].miniT[3], t[i].miniT[4], t[i].miniT[5], t[i].teste, nota(t[i]));
    }
}

void dumpV (int v[], int N){
    int i;
    for (i=0; i<N; i++) printf ("%d ", v[i]);
}

void imprimeAluno (Aluno *a){
    int i;
    printf ("%-5d %s (%d", a->numero, a->nome, a->miniT[0]);
    for(i=1; i<6; i++) printf (", %d", a->miniT[i]);
    printf (") %5.2f %d\n", a->teste, nota(*a));
}

int main() {
    Aluno Turma1 [7] = {{4444, "André", {2,1,0,2,2,2}, 10.5}
                       ,{3333, "Paulo", {0,0,2,2,2,1},  8.7}
                       ,{8888, "Carla", {2,1,2,1,0,1}, 14.5}
                       ,{2222, "Joana", {2,0,2,1,0,2},  3.5}
                       ,{7777, "Maria", {2,2,2,2,2,1},  5.5}
                       ,{6666, "Bruna", {2,2,2,1,0,0}, 12.5}
                       ,{5555, "Diogo", {2,2,1,1,1,0},  8.5}
                       } ;
    int i;
    int indNum [7];
    int indNome [7];
    
    printf ("\n-------------- Testes --------------\n");
    
    ordenaPorNum (Turma1, 7);
    
    printf ("Procura do numero 5555: %d \n", procuraNum (5555, Turma1, 7));
    printf ("Procura do numero 9999: %d \n", procuraNum (9999, Turma1, 7));

    //for (i=0; i<7; imprimeAluno (Turma1 + i++));
    
    printf("\nOrdenado por numero: \n");
    criaIndPorNum (Turma1, 7, indNum);
    imprimeTurma(indNum, Turma1, 7); 

    printf("\nOrdenado por nome: \n");
    criaIndPorNome (Turma1, 7, indNome);
    imprimeTurma (indNome, Turma1, 7);
    
    printf ("\nProcura do numero 5555: %d \n", procuraNumInd (5555, indNum, Turma1, 7));
    printf ("Procura do numero 9999: %d \n", procuraNumInd (9999, indNum, Turma1, 7));

    printf ("\n---------- Fim dos Testes ----------\n");
    
    return 0;
}
