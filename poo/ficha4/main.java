
import java.util.Scanner;

public class main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.println("Escolha um exercício");
        int in = sc.nextInt();
        
        switch(in){
            case 1:
                stackStrings s = new stackStrings();
                s.push("ola");
                s.push("tudo");
                s.push("bem");
                System.out.println(s.top());
                s.pop();
                System.out.println(s.top());
                System.out.println(s.length());
                System.out.println(s.empty());
                break;
            case 2:
                encEficiente enc = new encEficiente();
                enc.adicionaLinha(new linhaEncomenda("123", "teste", 1.0, 1, 0.23, 0.3));
                enc.adicionaLinha(new linhaEncomenda("456", "oi", 15.0, 5, 0.23, 0.5));
                System.out.println(enc.calculaValorTotal());
                System.out.println(enc.calculaValorDesconto());
                enc.removeProduto("123");
                System.out.println(enc.numeroTotalProdutos());
                System.out.println(enc.existeProdutoEncomenda("123"));
                break;
            case 3:
                casaInteligente casa = new casaInteligente();
                casa.addLampada(new lampada(lampada.Modo.ON, 0.2, 0.5, 0.4, 0.6));
                casa.addLampada(new lampada(lampada.Modo.OFF, 0.3, 0.6, 0.5, 0.7));
                casa.addLampada(new lampada(lampada.Modo.ECO, 0.4, 0.7, 0.6, 0.8));
                casa.addLampada(new lampada(lampada.Modo.ON, 0.5, 0.8, 0.7, 0.9));
                casa.addLampada(new lampada(lampada.Modo.OFF, 0.6, 0.9, 0.8, 1.0));
                casa.addLampada(new lampada(lampada.Modo.ON, 0.7, 1.0, 0.9, 1.1));
                //casa.removeLampada(1); 
                //casa.ligaLampadaECO(0);
                //casa.ligaLampadaECO(1);                
                //casa.ligaTodasEco();
                //System.out.println(casa.qtEmEco());    
                System.out.println(casa.consumoTotal());
                System.out.println(casa.maisGastadora());
                System.out.println(casa.lampadasEmModoEco());
                //casa.reset();
                break;

        }
        sc.close();
    }
}