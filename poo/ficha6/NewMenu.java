/*********************************************************************************/
/** DISCLAIMER: Este cÃ³digo foi criado e alterado durante as aulas prÃ¡ticas      */
/** de POO. Representa uma soluÃ§Ã£o em construÃ§Ã£o, com base na matÃ©ria leccionada */
/** atÃ© ao momento da sua elaboraÃ§Ã£o, e resulta da discussÃ£o e experimentaÃ§Ã£o    */
/** durante as aulas. Como tal, nÃ£o deverÃ¡ ser visto como uma soluÃ§Ã£o canÃ³nica,  */
/** ou mesmo acabada. Ã‰ disponibilizado para auxiliar o processo de estudo.      */
/** Os alunos sÃ£o encorajados a testar adequadamente o cÃ³digo fornecido e a      */
/** procurar soluÃ§Ãµes alternativas, Ã  medida que forem adquirindo mais           */
/** conhecimentos de POO.                                                        */
/*********************************************************************************/



import java.util.*;

/**
 * Esta classe implementa um NewMenu em modo texto.
 *
 * @author JosÃ© Creissac Campos
 * @version v3.2 (20201215)
 * @version v3.3 (20230502)
 */
public class NewMenu {

    // Interfaces auxiliares

    /** Functional interface para handlers. */
    public interface Handler {  // mÃ©todo de tratamento
        public void execute();
    }

    /** Functional interface para prÃ©-condiÃ§Ãµes. */
    /** Podia ser utilizado Predicate<T> */
    public interface PreCondition {
        public boolean validate();
    }

    // VarÃ­Ã¡vel de classe para suportar leitura

    private static Scanner is = new Scanner(System.in);

    // VariÃ¡veis de instÃ¢ncia

    private List<String> opcoes;            // Lista de opÃ§Ãµes
    private List<PreCondition> disponivel;  // Lista de prÃ©-condiÃ§Ãµes
    private List<Handler> handlers;         // Lista de handlers

    // Construtor

    /**
     * Constructor for objects of class NewMenu
     */
    public NewMenu(String[] opcoes) {
        this.opcoes = Arrays.asList(opcoes);
        this.disponivel = new ArrayList<>();
        this.handlers = new ArrayList<>();
        this.opcoes.forEach(s-> {
            this.disponivel.add(()->true);
            this.handlers.add(()->System.out.println("\nATENÃ‡ÃƒO: OpÃ§Ã£o nÃ£o implementada!"));
        });
    }

    // MÃ©todos de instÃ¢ncia

    /**
     * Correr o NewMenu.
     *
     * Termina com a opÃ§Ã£o 0 (zero).
     */
    public void run() {
        int op;
        do {
            show();
            op = readOption();
            // testar prÃ©-condiÃ§Ã£o
            if (op>0 && !this.disponivel.get(op-1).validate()) {
                System.out.println("OpÃ§Ã£o indisponÃ­vel! Tente novamente.");
            } else if (op>0) {
                // executar handler
                this.handlers.get(op-1).execute();
            }
        } while (op != 0);
    }

    /**
     * MÃ©todo que regista uma uma prÃ©-condiÃ§Ã£o numa opÃ§Ã£o do NewMenu.
     *
     * @param i Ã­ndice da opÃ§Ã£o (comeÃ§a em 1)
     * @param b prÃ©-condiÃ§Ã£o a registar
     */
    public void setPreCondition(int i, PreCondition b) {
        this.disponivel.set(i-1,b);
    }

    /**
     * MÃ©todo para registar um handler numa opÃ§Ã£o do NewMenu.
     *
     * @param i indice da opÃ§Ã£o  (comeÃ§a em 1)
     * @param h handlers a registar
     */
    public void setHandler(int i, Handler h) {
        this.handlers.set(i-1, h);
    }

    // MÃ©todos auxiliares

    /** Apresentar o NewMenu */
    private void show() {
        System.out.println("\n *** NewMenu *** ");
        for (int i=0; i<this.opcoes.size(); i++) {
            System.out.print(i+1);
            System.out.print(" - ");
            System.out.println(this.disponivel.get(i).validate()?this.opcoes.get(i):"---");
        }
        System.out.println("0 - Sair");
    }

    /** Ler uma opÃ§Ã£o vÃ¡lida */
    private int readOption() {
        int op;
        //Scanner is = new Scanner(System.in);

        System.out.print("OpÃ§Ã£o: ");
        try {
            String line = is.nextLine();
            op = Integer.parseInt(line);
        }
        catch (NumberFormatException e) { // NÃ£o foi escrito um int
            op = -1;
        }
        if (op<0 || op>this.opcoes.size()) {
            System.out.println("OpÃ§Ã£o InvÃ¡lida!!!");
            op = -1;
        }
        return op;
    }
    /*********************************************************************************/

    public class Menu {
        // variÃ¡veis de instÃ¢ncia
        private List<String> opcoes;
        private int op;

        /**
         * Constructor for objects of class Menu
         */
        public Menu(String[] opcoes) {
            this.opcoes = Arrays.asList(opcoes);
            this.op = 0;
        }

        /**
         * MÃ©todo para apresentar o menu e ler uma opÃ§Ã£o.
         *
         */
        public void executa() {
            do {
                showMenu();
                this.op = lerOpcao();
            } while (this.op == -1);
        }

        /** Apresentar o menu */
        private void showMenu() {
            System.out.println("\n *** Menu *** ");
            for (int i=0; i<this.opcoes.size(); i++) {
                System.out.print(i+1);
                System.out.print(" - ");
                System.out.println(this.opcoes.get(i));
            }
            System.out.println("0 - Sair");
        }

        /** Ler uma opÃ§Ã£o vÃ¡lida */
        private int lerOpcao() {
            int op;
            Scanner is = new Scanner(System.in);

            System.out.print("OpÃ§Ã£o: ");
            try {
                op = is.nextInt();
            }
            catch (InputMismatchException e) { // NÃ£o foi escrito um int
                op = -1;
            }
            if (op<0 || op>this.opcoes.size()) {
                System.out.println("OpÃ§Ã£o InvÃ¡lida!!!");
                op = -1;
            }
            return op;
        }

        /**
         * MÃ©todo para obter a Ãºltima opÃ§Ã£o lida
         */
        public int getOpcao() {
            return this.op;
        }
    }
}

