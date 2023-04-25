import java.time.LocalDate;

public class fitnessTest {
    public static void main(String[] args){
        fitness f1 = new fitness();

        utilizador u1 = new utilizador("ola", "123", "pilar", "M", 1.80, 70, LocalDate.of(1999, 12, 12), LocalDate.of(2020, 12, 12), null);
        utilizador u2 = new utilizador("ola2", "123", "conas", "F", 1.80, 70, LocalDate.of(1999, 12, 12), LocalDate.of(2020, 12, 12), null);

        f1.adicionaUtilizador(u1);
        f1.adicionaUtilizador(u2);

        corrida c1 = new corrida("corrida", LocalDate.of(2020, 12, 12), 30, 10, 10, "olimpo");
        corrida c2 = new corrida("corrida ao ar livre", LocalDate.of(2020, 12, 12), 50, 400, 20, "deserto");
        abdominais a1 = new abdominais("abdominais", LocalDate.of(2020, 12, 12), 30, "costas", 10);
        abdominais a2 = new abdominais("abdominais 2", LocalDate.of(2020, 12, 12), 40, "frente", 15);
        canoagem ca1 = new canoagem("canoagem", LocalDate.of(2020, 12, 12), 30, "topg", 10, "norte", 500, 10);
        canoagem ca2 = new canoagem("canoagem 2", LocalDate.of(2020, 12, 12), 40, "pilar", 60, "sul", 50, 20);
        
        f1.adiciona("ola", c1);
        f1.adiciona("ola2", c2);
        f1.adiciona("ola", a1);
        f1.adiciona("ola2", a2);
        f1.adiciona("ola", ca1);
        f1.adiciona("ola2", ca2);

        
        //System.out.println("Existe o utilizador: " + f1.existeUtilizador("ola2"));

        //System.out.println(f1.quantos());
        //System.out.println(f1.quantos("corrida", "ola"));

        //System.out.println("\n" + f1.getUtilizador("ola2"));

        //System.out.println("\n" + f1.getAllActividades());

        f1.adiciona("ola", new abdominais("abdominais 5", LocalDate.of(2020, 12, 12), 30, "costas", 10));
        System.out.println("\n" + f1.getAllActividades());
    }
}
