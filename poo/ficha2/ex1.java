import java.util.Scanner;

public class ex1{

    int[] val;
    Scanner ola;

    public ex1(int N, Scanner pilar){
        val = new int[N];
        ola = pilar;
    }
    
    public int ex1a(){
        System.out.println("Insira " + val.length + " valores: ");

        int min = Integer.MAX_VALUE;
        for(int i = 0; i < val.length; i++){
            val[i] = ola.nextInt();
            if(val[i] < min) min = val[i];
        }
        return min;
    }

    public int[] ex1b(int a, int b){
        int len = Math.abs(Math.max(a, b)-Math.min(a, b)) + 1;

        int[] r = new int[len];
        System.arraycopy(val, Math.min(a,b), r, 0, len);
        return r;
    }

    // nao esta a funcionar direito -> esta a dar output ao array inteiro em vez dos comuns
    public int[] ex1c(int[] c){
        int[] r = new int[Math.min(val.length, c.length)];
        int k = 0;
        for (int value1 : val) {
            for (int value2 : c) {
                if (value1 == value2) {
                    r[k++] = value1;
                    break;
                }
            }
        }
        int[] result = new int[k];
        System.arraycopy(r, 0, result, 0, k);
        return result;
    }
}


