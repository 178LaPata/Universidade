import java.time.DateTimeException;
import java.time.LocalDate;
import java.io.*;
import java.util.*;
public class input implements Serializable{
    Scanner sc = new Scanner(System.in);
    public boolean lerSN(output a, String message){
        Scanner s = new Scanner(System.in);
        String line;

        do{
            a.printMessage(message);
            line = s.nextLine();
        } while (!line.toUpperCase().equals("S") && !line.toUpperCase().equals("N"));

        return line.toUpperCase().equals("S");
    }

    public double lerDouble(output a, String message,int min,int max){
        Scanner s = new Scanner(System.in);
        double n = -1;

        do{
            a.printMessage(message);
            try {
                String line = s.nextLine();
                n = Double.parseDouble(line);
            } catch (NumberFormatException nfe) {
                a.printMessage(nfe.getMessage());
                n = -1;
            }
        } while (n < min || n > max);

        return n;
    }

    public int lerInt(output a, String message, int min, int max) {
        Scanner s = new Scanner(System.in);
        int n = -1;

        do {
            a.printMessage(message);
            try {
                String line = s.nextLine();
                n = Integer.parseInt(line);
            } catch (NumberFormatException nfe) {
                a.printMessage(nfe.getMessage());
                n = -1;
            }
        } while (n < min || n > max);

        return n;
    }

    public float lerFloat(output a, String message, float min, float max) {
        Scanner s = new Scanner(System.in);
        float n = Float.NaN;

        do {
            a.printMessage(message);
            try {
                String line = s.nextLine();
                n = Float.parseFloat(line);
            } catch (NumberFormatException nfe) {
                a.printMessage(nfe.getMessage());
                n = Float.NaN;
            }
        } while (n < min || n > max);

        return n;
    }

    public String lerString(output a, String message){
        Scanner s = new Scanner(System.in);
        String line;

        a.printMessage(message);
        line = s.nextLine();

        return line;
    }

    public LocalDate lerData(output a, String message) {
        Scanner s = new Scanner(System.in);
        boolean val = true;
        LocalDate data = null;
        String[] date;

        do {
            a.printMessage(message);
            try {
                date = s.nextLine().split("-",3);
                data = LocalDate.of(Integer.parseInt(date[2]), Integer.parseInt(date[1]), Integer.parseInt(date[0]));
                val = false;
            } catch (DateTimeException dte) {
                a.printMessage("Data inválida");
            } catch (NumberFormatException ignored) {
            }
        } while (val);

        return data;
    }
    public int lerInteiro(int minVal, int maxVal){
        int op = -1;

        do{
            try{
                op = this.sc.nextInt();
                this.sc.nextLine();
                //Foi inserido um inteiro mas não é válido
                if(op < minVal || op > maxVal){
                    System.out.println("Por favor insira um inteiro válido");
                    this.sc.nextLine();
                    op = -1;
                }
            }catch (InputMismatchException e){
                System.out.println("Por favor insira um inteiro válido");
                this.sc.nextLine();
            }
        }while(op == -1);
        return op;
    }
}

