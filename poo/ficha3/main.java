import java.util.Scanner;
import java.time.LocalDate;

public class main {
    public static void main(String[] args){
        
        Scanner sc = new Scanner(System.in);
        System.out.println("Escolha um exercício");
        int in = sc.nextInt();
        
        switch(in){
            case 1:
                circulo c2 = new circulo(4.0, 4.0, 5.0);

                System.out.println("Introduza o novo valor do centro do circulo");
                Scanner sc2 = new Scanner(System.in);
                double novoX = sc2.nextDouble();
                double novoY = sc2.nextDouble();
                c2.alteraCentro(novoX, novoY);

                System.out.println("A area do circulo é " + c2.calculaArea());
                System.out.println("O perimetro do circulo é " + c2.calculaPerimetro());
                break;
            case 2:
                telemovel tel1 = new telemovel("iPhone", "12 Pro", 1920, 1080, (byte) 100, (byte) 100, (byte) 100, 64000);               
                tel1.instalaApp("instagram", 10);
                tel1.instalaApp("tiktok", 10);
                tel1.instalaApp("twitter", 20);
                tel1.instalaApp("youtube", 10);

                tel1.removeApp("instagram", 10);    

                tel1.recebeMsg("ola!");
                tel1.recebeMsg("o dlc é um chinelo!");

                System.out.println("A maior mensagem é " + tel1.maiorMsg());

                System.out.println("O tamanho médio das aplicações é " + tel1.tamMedioApps());

                System.out.println("O nome das aplicações são: ");
                String[] apps = tel1.getNomeApps();
                for (int i=0 ; i<tel1.getNrApps() ; i++) {
                    System.out.print(apps[i] + "\n");
                }
                break;    
            case 3:

                









            case 4:
            case 5:
            case 6:
            case 7:
                linhaEncomenda l1 = new linhaEncomenda("ref", "des", 100.0, 1, 23, 50);
                linhaEncomenda l2 = new linhaEncomenda("ref", "des", 10.0, 2, 15, 30);
                linhaEncomenda l3 = new linhaEncomenda("ref", "des", 5.0, 3, 10, 20);
                linhaEncomenda l4 = new linhaEncomenda("ref", "des", 22.0, 5, 6, 40);

                System.out.println("O valor da venda é " + l1.calculaValorLinhaEnc());
                System.out.println("O valor da venda é " + l2.calculaValorLinhaEnc());
                System.out.println("O valor da venda é " + l3.calculaValorLinhaEnc());
                System.out.println("O valor da venda é " + l4.calculaValorLinhaEnc());
                
                System.out.println("O valor do desconto é " + l1.calculaValorDesconto());
                System.out.println("O valor do desconto é " + l2.calculaValorDesconto());
                System.out.println("O valor do desconto é " + l3.calculaValorDesconto());
                System.out.println("O valor do desconto é " + l4.calculaValorDesconto());
                break;
            case 8:
                linhaEncomenda l5 = new linhaEncomenda("ref", "des", 100.0, 1, 23, 50);
                linhaEncomenda l6 = new linhaEncomenda("ref", "des", 10.0, 2, 15, 30);
                linhaEncomenda l7 = new linhaEncomenda("ref", "des", 5.0, 3, 10, 20);
                linhaEncomenda l8 = new linhaEncomenda("ref", "des", 22.0, 5, 6, 40);

                encomenda e2 = new encomenda("nome", 123, "morada", 1, LocalDate.now(), new linhaEncomenda[]{l5, l6, l7, l8});

                System.out.println("O valor total da encomenda é " + e2.calculaValorTotal());
                System.out.println("O valor total dos descontos é " + e2.calculaValorDesconto2());
                System.out.println("O número total de produtos é " + e2.numeroTotalProdutos());
                System.out.println("O produto vai ser encomendado? " + e2.existeProdutoEncomenda("das"));
                System.out.println("Adiciona uma linha de encomenda");
                
                linhaEncomenda l9 = new linhaEncomenda("das", "oi", 22.0, 8, 2, 35);
                e2.adicionaLinha(l9);
                
                Scanner ola = new Scanner(System.in);
                System.out.println("Qual a referencia do produto a remover? ");
                String removeRef = ola.nextLine();
                e2.removeProduto(removeRef);
                
                System.out.print(e2.toString());

                break;
            case 9:
                triangulo t1 = new triangulo();

                System.out.println(t1);

                System.out.println(t1.calculaAreaTriangulo());
                System.out.println(t1.calculaPerimetroTriangulo());
            
        }
    }
}
