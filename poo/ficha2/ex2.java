import java.time.LocalDate;

public class ex2 {
    private LocalDate[] data;
    private int tamanho,posicao;

    public ex2(int tam){
        this.tamanho = tam;
        data = new LocalDate[tamanho];
        posicao = 0;
    }

    public ex2(){
        this.tamanho = 5;
        data = new LocalDate[tamanho];
        posicao = 0;
    }

    public void insereData(LocalDate data){
        if(posicao < tamanho){
            this.data[posicao++] = data;
        }
    }
    
    public long dataParaDia(LocalDate date){
        long dias = (date.getYear()*365)+(date.getYear()/4)+(date.getDayOfYear());
        return dias;
    }

    public LocalDate dataMaisProxima(LocalDate data) {
        LocalDate maisProxima = data;
        long diferencaMinima = Long.MAX_VALUE;
        for (int i = 0; i < posicao; i++) {
            long diferenca = Math.abs(dataParaDia(data) - dataParaDia(this.data[i]));
            if (diferenca < diferencaMinima) {
                diferencaMinima = diferenca;
                maisProxima = this.data[i];
            }
        }
        return maisProxima;
    }
    
    public String toString(){
        String da = "[";
        for(int i=0; i<tamanho-1;i++)
            da += data[i].toString() + ", ";
        return da + data[tamanho-1] + "]";
    }
}
