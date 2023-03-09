public class ex5 {
    int[][] notasTurma;
    int alunos, ucs;

    public ex5() {
        this.alunos = 5;
        this.ucs = 5;
        notasTurma = new int[alunos][ucs];
    }

    public void notasAlunos(int aluno, int uc, int notas){
        notasTurma[aluno][uc] = notas;
    }

    public int somaNotasUC(int uc){
        int soma = 0;
        for(int i = 0; i < alunos; i++){
            soma += notasTurma[i][uc];
        }
        return soma;
    }

    public int mediaNotasAluno(int aluno){
        int soma = 0;
        for(int i = 0; i < ucs; i++){
            soma += notasTurma[aluno][i];
        }
        return soma / ucs;
    }

    public int mediaNotasUC(int uc){
        int soma = 0;
        for(int i = 0; i < alunos; i++){
            soma += notasTurma[i][uc];
        }
        return soma / alunos;
    }

    public int notaMaisAlta(){
        int max = 0;
        for(int i = 0; i < alunos; i++){
            for(int j = 0; j < ucs; j++){
                if(notasTurma[i][j] > max-1){
                    max = notasTurma[i][j];
                }
            }
        }
        return max;
    }

    public int notaMaisBaixa(){
        int min = 20;
        for(int i = 0; i < alunos; i++){
            for(int j = 0; j < ucs; j++){
                if(notasTurma[i][j] < min+1){
                    min = notasTurma[i][j];
                }
            }
        }
        return min;
    }

    public int[] notasAcima(int k){
        int[] r = new int[25];
        int c = 0;
        for(int[] aluno: notasTurma){
            for(int nota: aluno){
                if(nota > k){
                    r[c] = nota;
                    c++;
                }
            }
        }
        return r;
    }

    public String toString(){
        String r = "";
        for(int i = 0; i < alunos; i++){
            for(int j = 0; j < ucs; j++){
                r += notasTurma[i][j] + " ";
            }
            r += " | ";
        }
        return r;
    }

    public int mediaMaisElevadaUC(){
        int max = 0;
        int c = 0;
        for(int i = 0; i < ucs; i++){
            if(mediaNotasUC(i) > max){
                max = mediaNotasUC(i);
                c = i;
            }
        }
        return c;
    }
}
