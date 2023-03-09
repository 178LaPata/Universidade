import java.util.Scanner;
import java.time.LocalDate;
import java.util.Arrays;


public class main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.println("Escolha um exercício");
        int in = sc.nextInt();

        switch(in){
            case 1:
                System.out.println("Insira o número de valores: ");
                int N = sc.nextInt();
                ex1 e1 = new ex1(N, sc);
                int min = e1.ex1a();
                System.out.println("O mínimo é " + min);

                System.out.println("Insira dois valores: ");
                int a = sc.nextInt();
                int b = sc.nextInt();

                System.out.println("O array entre " + a + " e " + b + " é: " + Arrays.toString(e1.ex1b(a, b)));

                int[] r = new int[N];
                System.out.println("Insira " + N + " valores: ");
                for(int i = 0; i < N; i++){
                    r[i] = sc.nextInt();
                }
                System.out.println("O array é: " + Arrays.toString(r));  
                break;          
            case 2:
                ex2 e2 = new ex2();
                LocalDate d1 = LocalDate.of(2002, 11, 7);
                LocalDate d2 = LocalDate.of(2022, 8, 30);
                LocalDate d3 = LocalDate.of(2012, 9, 2);
                LocalDate d4 = LocalDate.of(1999, 4, 22);
                LocalDate d5 = LocalDate.of(2000, 1, 1);

                e2.insereData(d1);
                e2.insereData(d2);
                e2.insereData(d3);
                e2.insereData(d4);
                e2.insereData(d5);
                
                System.out.println("A data mais proxima é: " + e2.dataMaisProxima(LocalDate.now()).toString());
                System.out.println("As datas são: " + e2.toString());
                break;          
            case 3:
                break;          
            case 4:
                break;
            case 5:
                ex5 e5 = new ex5();
                for(int i = 0; i < 5; i++){
                    for(int j = 0; j < 5; j++){
                        System.out.println("Insira a nota do aluno " + i + " na UC " + j + ": ");
                        e5.notasAlunos(i, j, sc.nextInt());
                        //System.out.println("\n");
                    }
                }
                System.out.println("Qual é o número da UC? (0-4)");
                int p = sc.nextInt();
                System.out.println("A soma das notas da UC " + p + " é: " + e5.somaNotasUC(p) + "\n");

                System.out.println("Qual é o número do aluno? (0-4)");
                int j = sc.nextInt();
                System.out.println("A média das notas do aluno " + j + " é: " + e5.mediaNotasAluno(j) + "\n");

                System.out.println("Qual é o número da UC? (0-4)");
                int k = sc.nextInt();
                System.out.println("A média das notas da UC " + k + " é: " + e5.mediaNotasUC(k) + "\n");

                System.out.println("A nota mais alta é: " + e5.notaMaisAlta() + "\n");
                System.out.println("A nota mais baixa é: " + e5.notaMaisBaixa() + "\n");

                System.out.println("Insira um valor: ");
                int l = sc.nextInt();
                System.out.println("As notas acima de " + l + " são: " + Arrays.toString(e5.notasAcima(l)) + "\n");

                System.out.println("A string com as notas todas é: ");
                System.out.print(e5.toString());

                System.out.println("A unidade curricular com a nota mais elevada é: " + e5.mediaMaisElevadaUC());
                break;
        }   
    }
}
