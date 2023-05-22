import java.time.LocalDate;
import java.util.Scanner;

public class fitnessApp {
    fitness f = new fitness();
    output out = new output();
    input in = new input();
    Scanner sc = new Scanner(System.in);



    public void menuInicial() throws FitnessException {
        out.printMenus((new String[]{"Login/Registar", "Gravar para um Ficheiro", "Carregar de um Ficheiro"}),"      MENU PRINCIPAL",0);
        int op = in.lerInteiro(0,5);
        switch (op){
            case 1:
                menuLogin();
                break;
            case 2:
                break;
            case 3:
                break;
            case 0:
                System.exit(0);
                break;
        }

    }

    public void menuLogin() throws FitnessException {
        out.printMenus((new String[]{"Login", "Registar"}),"      MENU LOGIN",0);
        int op = in.lerInteiro(0,3);
        switch (op) {
            case 1 -> {
                System.out.println("================  Login  ================");
                System.out.println("Email: ");
                String email2 = sc.nextLine();
                System.out.println("Password: ");
                String password2 = sc.nextLine();
                if (f.existeUtilizador(email2)) {
                    menuUtilizador();
                } else {
                    System.out.println("Utilizador não existe");
                    menuLogin();
                }
            }
            case 2 -> {
                System.out.println("================  Registar  ================");
                System.out.println("Email: ");
                String email = sc.nextLine();
                System.out.println("Password: ");
                String password = sc.nextLine();
                System.out.println("Nome: ");
                String nome = sc.nextLine();
                System.out.println("Genero [F,M]: ");
                utilizador.Genero genero = utilizador.Genero.valueOf(sc.nextLine());
                System.out.println("Altura: ");
                double altura = sc.nextDouble();
                System.out.println("Peso: ");
                double peso = sc.nextDouble();
                System.out.println("Data de Nascimento: ");
                LocalDate dataNascimento = LocalDate.parse(sc.nextLine());
                System.out.println("Desporto Favorito: ");
                String desportoFav = sc.nextLine();
                utilizador u = new utilizador(email, password, nome, genero, altura, peso, dataNascimento, desportoFav);
                menuUtilizador();
            }
            case 0 -> menuInicial();
        }
    }

    private void menuUtilizador() {
        out.printMenus((new String[]{"Ver Perfil", "Ver Atividades", "Ver Amigos", "Ver Desafios", "Ver Estatisticas", "Ver Notificacoes", "Logout"}),"      MENU UTILIZADOR",0);
    }

}