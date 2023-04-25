
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDateTime;

public class main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.println("Escolha um exercício");
        int in = sc.nextInt();
        
        switch(in){
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4: 
                gestaoEncomendas gest = new gestaoEncomendas();
                List<linhaEncomenda> le = new ArrayList<>();
                gest.addEncomenda(new encomenda("nome", 123, "morada", 1, LocalDateTime.now(), le));
                gest.addEncomenda(new encomenda("ola", 456, "deserto", 2, LocalDateTime.now(), le));

                System.out.println(gest.todosCodigosEnc());
                System.out.println(gest.getEncomenda(1));
                System.out.println(gest.getEncomenda(2));
                //gest.removeEncomenda(1);
                //System.out.println(gest.todosCodigosEnc());
                // nao consigo testar
                System.out.println(gest.encomendaComMaisProdutos());
                System.out.println(gest.encomendasComProduto(""));
                break;

        }
        sc.close();
    }
}