import java.lang.Math;
import java.math.BigInteger;

//import javax.lang.model.util.ElementScanner14;

public class exercicios2 {
    public double celsiusParaFarenheit(double graus){
        return graus*1.8 + 32;
    }

    public int maximoNumeros(int a, int b){
        return Math.max(a, b);        
    }

    public String criaDescricaoConta(String nome, double saldo){
        String r = new String(nome + " tem " + saldo + "€ na conta!");
        return r;

    }
    public double eurosParaLibras(double valor, double taxaConversao){
        return valor*taxaConversao;
    }
    public String mediaDoisInt(int a, int b){
        float med = (a+b)/2;
        String media = new String("A média entre " + a + " e " + b + " é " + med);
        return media;
    }
    public BigInteger factorial(int num){
        BigInteger resultado = BigInteger.valueOf(1);
        if (num == 0){
            return BigInteger.valueOf(0);
        }
        for (int i = 1; i <= num; i++) {
            resultado = resultado.multiply(BigInteger.valueOf(i));
        }
        return resultado;
    }
    public long tempoGasto(long start){
        start = System.currentTimeMillis();
        factorial(5000);
        long end = System.currentTimeMillis();
        long time = end - start;
        return time;
    }

}


