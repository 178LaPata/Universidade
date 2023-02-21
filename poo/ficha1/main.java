import java.util.Scanner;
import java.math.BigInteger;
import java.util.Date;

public class main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        exercicios1 e1 = new exercicios1();
        exercicios2 e2 = new exercicios2();

        System.out.println("Escolha um exercício");
        int in = sc.nextInt();

        if (in == 1){
            System.out.println("1 - Dia da semana");
            System.out.println("2 - Soma duas datas");
            System.out.println("3 - Classificações");
            System.out.println("4 - Temperaturas");
            System.out.println("5 - Triângulos");
            System.out.println("6 - Números primos");
            System.out.println("7 - Idade em horas");

            int inp = sc.nextInt();

            switch(inp){
                case 1:
                    System.out.println("Insira um dia, mês e ano");
                    System.out.println("Dia: ");
                    int dia = sc.nextInt();
                    System.out.println("Mês: ");
                    int mes = sc.nextInt();
                    System.out.println("Ano: ");
                    int ano = sc.nextInt();
                    String diaSemana = e1.diaDaSemana(dia, mes, ano);
                    System.out.println("O dia da semana é " + diaSemana);
                    break;
                case 2:
                    System.out.println("Dia: ");
                    int d = sc.nextInt();
                    System.out.println("Hora: ");
                    int h = sc.nextInt();
                    System.out.println("Minuto: ");
                    int m = sc.nextInt();
                    System.out.println("Segundo: ");
                    int s = sc.nextInt(); 

                    String soma = e1.somaDuasDatas(d, h, m, s);
                    System.out.println("A soma é " + soma);
                case 3:
                    String classificacoes = e1.classificacoes();
                    System.out.println(classificacoes);
                    break;
                case 4:
                    String temperaturas = exercicios1.mediaTemperaturas();
                    System.out.println(temperaturas);
                    //exercicios1.temperaturas();
                case 5:
                    e1.triangulos();
                case 6:
                    e1.primos(in);
                case 7:
                    e1.idadeHoras();
                break;
                }
        } else {
            System.out.println("1 - Celsius para Farenheit");
            System.out.println("2 - Máximo entre dois valores");
            System.out.println("3 - Descrição da conta");
            System.out.println("4 - Euros para Libras");
            System.out.println("5 - Ordem decrescente e média");
            System.out.println("6 - Fatorial de um valor");
            System.out.println("7 - Tempo Gasto");
    
            int input = sc.nextInt();
    
            switch(input){
                case 1:
                    System.out.println("Insira um valor de temperatura em C");
                    double celsius = sc.nextDouble();
                    double farenheit = e2.celsiusParaFarenheit(celsius);
                    System.out.println("O valor é da temperatura em Farenheit é " + farenheit);
                    break;
                case 2:
                    System.out.println("Insira dois valores");
                    int a = sc.nextInt();
                    int b = sc.nextInt();
                    int maior = e2.maximoNumeros(a, b);
                    System.out.println("O maior valor entre " + a + " e " + b + " é " + maior);
                    break;
                case 3:
                    System.out.println("Introduza o nome (String) e o saldo (decimal)");
                    sc.nextLine();
                    String nome = sc.nextLine();
                    double saldo = sc.nextDouble();
                    String descricao = e2.criaDescricaoConta(nome, saldo);
                    System.out.println(descricao);
                    break;
                case 4:
                    System.out.println("Insira um valor em euros e uma taxa de conversão");
                    sc.nextLine();
                    double valor = sc.nextDouble();
                    double taxaConversao = sc.nextDouble();
                    double libras = e2.eurosParaLibras(valor, taxaConversao);
                    System.out.println(libras);
                    break;
                case 5:
                    System.out.println("Insira dois valores");
                    sc.nextLine();
                    int val1 = sc.nextInt();
                    int val2 = sc.nextInt();
                    String media = e2.mediaDoisInt(val1, val2);
                    System.out.println("Ordem decrescente: " + Math.max(val1, val2) + "," + " " + Math.min(val1, val2) + ". " + media);
                    break;
                case 6:
                    System.out.println("Insira um valor");
                    sc.nextLine();
                    int num = sc.nextInt();
                    BigInteger fact = e2.factorial(num);
                    System.out.println("O factorial do número é " + fact);
                    break;
                case 7:
                    System.out.println("Data e hora do sistema: " + new Date());
                    long startTime = System.currentTimeMillis();
                    System.out.println("Fatorial de 5000: " + e2.factorial(5000));
                    System.out.println("Data e hora após o cálculo: " + new Date());
                    System.out.println("Tempo gasto em milissegundos: " + e2.tempoGasto(startTime));
                    break;
            }
        }
    sc.close();
    }
}