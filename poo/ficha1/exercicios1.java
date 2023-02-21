import java.util.Scanner;
import java.time.LocalDateTime;

public class exercicios1 {
    public String diaDaSemana(int dia, int mes, int ano){
        int pilar = (ano - 1900);
        pilar = pilar*365;
        pilar = (ano-1900)/4;

        if((((ano % 4 == 0) && (ano % 100 != 0)) || (ano % 400 == 0)) && (ano == 1 || ano == 2)){ // whatttt??
            pilar--;
        }

        for(; mes>0; mes--){
            if (dia == 0){
                if(mes == 1 || mes == 3 || mes == 5 || mes == 7 || mes == 8 || mes == 10 || mes == 12){
                    dia += 31;
                }
                else if (mes == 4 || mes == 6 || mes == 9 || mes == 11){
                    dia += 30;
                }
                else if (mes == 2) dia += 28;
            }
            pilar += dia;
            dia = 0;
        }

        pilar = pilar % 7;
        String diaSemana = null;
        switch (pilar){
            case 0: diaSemana = "Domingo";
                break;
            case 1: diaSemana = "Segunda-feira";
                break;
            case 2: diaSemana = "Terça-feira";
                break;
            case 3: diaSemana = "Quarta-feira";
                break;
            case 4: diaSemana = "Quinta-feira";
                break;
            case 5: diaSemana = "Sexta-feira";
                break;
            case 6: diaSemana = "Sábado";
                break;
        }
    return diaSemana;
    }

    public String somaDuasDatas(int d, int h, int m, int s){
        LocalDateTime date1 = LocalDateTime.now();

        s += date1.getSecond();
        while(s >= 60){
            m++;
            s = s - 60;
        }
        m += date1.getMinute();
        while(m >= 60){
            h++;
            m = m - 60;
        }
        h += date1.getHour();
        while(h >= 24){
            d++;
            h = h - 24;
        }
        d += date1.getDayOfMonth();

        String out = d + "D " + h + "H " + m + "m " + s + "s";
        return out;
    }

    public String classificacoes(){
        Scanner oi = new Scanner(System.in);
        System.out.println("Para parar, prima 0");
        
        int zeroCinco = 0;
        int cincoDez = 0;
        int dezQuinze = 0;
        int quinzeVinte = 0;
        
        System.out.println("Nota");
        int nota = oi.nextInt();
        while(nota != 0){
            if(nota >= 0 && nota <= 5) zeroCinco++;
            else if(nota > 5 && nota <= 10) cincoDez++;
            else if(nota > 10 && nota <= 15) dezQuinze++;
            else if(nota > 15 && nota <= 20) quinzeVinte++;
            else System.out.println("Tas na maionese");
            nota = oi.nextInt();
        }

        String out = "[0,5[ -> " + zeroCinco;
        String out1 = "[5,10[ -> " + cincoDez;
        String out2 = "[10,15[ -> " + dezQuinze;
        String out3 = "[15,20] -> " + quinzeVinte;

        oi.close();
        return ("O número de notas em cada intervalo é: \n" + out + "\n" + out1 + "\n" + out2 + "\n" + out3);
    }

    public static int media (int[] arr)
    {
        int s = 0;
        for (int i=0 ; i<arr.length ; i++) s+= arr[i];

        return s/arr.length;
    }

    public static String mediaTemperaturas(){
        Scanner ola = new Scanner(System.in);
        System.out.println("Insina o número de temperaturas(pelo menos 2): ");
        int n = ola.nextInt();

        int[] temp = new int[n];
        int varMax = 0;
        int dia = 1;
        temp[0] = ola.nextInt();

        for(int i = 1; i < n; i++){
            temp[i] = ola.nextInt();
            int temp2 = temp[i] - temp[i-1];
            if(Math.abs(temp2) >= Math.abs(varMax)){
                varMax = temp2;
                dia = i;
            }
        }
        ola.close();
        
        String t = new String("A média das  temperaturas foi de " + media(temp) + " graus.\nA maior variação registou-se entre os dias " + dia + " e " + (dia+1) + ", tendo a temperatura " + ((varMax>0) ?"subido " :"descido ") + Math.abs(varMax) + " graus.");
        return t;
    }

    public void triangulos(){
        Scanner oi = new Scanner(System.in);
        float base = 1.0f, altura = 1.0f;
        while (base > 0.0f)
        {
            System.out.println("Insira a base e a altura do triângulo: ");
            base = oi.nextFloat();
            altura = oi.nextFloat();
            if (base<0 || altura<0)
            {
                System.out.println("Valores devem ser maiores que zero.");
                continue;
            }
            float area = (base*altura)/2;
            float perimetro = base + altura + (int)Math.sqrt(base*base + altura*altura);
            System.out.println("A área do triângulo é " + area + " e o perímetro é " + perimetro);
        }
        oi.close();
    }
    
    public void primos(int n){
        Scanner oi = new Scanner(System.in);
        while(n != 0){
            System.out.println("Insira um número: ");
            n = oi.nextInt();
            System.out.println("\nOs números primos até " + n + " são: ");
            for(int i = 2; i <= n; i++){
                boolean isPrime = true;
                for(int j = 2; j < i; j++){
                    if(i % j == 0){
                        isPrime = false;
                        break;
                    }
                }
                if(isPrime) {
                    System.out.println(i);
                };
            }
        }
        oi.close();
    }
    
    public long dataHoras(LocalDateTime data){
        long ano = data.getYear() - 1900;
        long dias = ano*365;
        dias += (ano/4);
        dias += data.getDayOfYear();
        long horas = dias*24 + data.getHour();
        return horas;
    }

    public void idadeHoras (){
        Scanner oi = new Scanner(System.in);
        System.out.println("Insira a data de nascimento: ");
        int dia = oi.nextInt();
        int mes = oi.nextInt();
        int ano = oi.nextInt();
        
        LocalDateTime nascimento = LocalDateTime.of(ano, mes, dia, 0, 0, 0);
        LocalDateTime hoje = LocalDateTime.now();

        long horasNascimento = dataHoras(nascimento);
        long horasHoje = dataHoras(hoje);

        System.out.println("A sua idade em horas é: " + (horasHoje-horasNascimento) + " horas.");
        System.out.println("O calculo foi feito em " + hoje.getDayOfMonth() + "-" + hoje.getMonthValue() + "-" + hoje.getYear() + " " + hoje.getHour() + ":" + hoje.getMinute() + ":" + hoje.getSecond() + ".");

        oi.close();
    }
}
