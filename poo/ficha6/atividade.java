import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class atividade {
    private String codigo;
    private String descricao;
    private LocalDate dataAtividade;
    private int minutos; // tempo em minutos que a atividade durou
    private double calorias; //calorias gastas na atividade

    public atividade() {
        this.codigo = "";
        this.descricao = "";
        this.dataAtividade = LocalDate.now(); //acho que nao e assim
        this.minutos = 0;
        this.calorias = 0;
    }

    public atividade(String codigo, String descricao, LocalDate dataAtividade, int minutos) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.dataAtividade = dataAtividade;
        this.minutos = minutos;
    }

    public atividade(atividade a) {
        this.codigo = a.getCodigo();
        this.descricao = a.getDescricao();
        this.dataAtividade = a.getDataAtividade();
        this.minutos = a.getMinutos();
        this.calorias = a.getCalorias();
    }

    public String getCodigo() {
        return this.codigo;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public LocalDate getDataAtividade() {
        return this.dataAtividade;
    }

    public int getMinutos() {
        return this.minutos;
    }

    public double getCalorias() {
        return this.calorias;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setDataAtividade(LocalDate dataAtividade) {
        this.dataAtividade = dataAtividade;
    }

    public void setMinutos(int minutos) {
        this.minutos = minutos;
    }

    public void setCalorias(double calorias) {
        this.calorias = calorias;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("==============  Atividade  ==============\n");
        sb.append("Código: ").append(this.codigo).append("\n");
        sb.append("Descrição: ").append(this.descricao).append("\n");
        sb.append("Data da atividade: ").append(this.dataAtividade).append("\n");
        sb.append("Minutos: ").append(this.minutos).append("\n");
        sb.append("Calorias: ").append(this.calorias).append("\n");
        return sb.toString();
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        atividade a = (atividade) o;
        return this.codigo.equals(a.getCodigo()) &&
                this.descricao.equals(a.getDescricao()) &&
                this.dataAtividade.equals(a.getDataAtividade()) &&
                this.minutos == a.getMinutos() &&
                this.calorias == a.getCalorias();
    }

    public atividade clone() {
        return new atividade(this);
    }
}
