import java.time.LocalDate;

public class atividade {
    private static int nextID = 1;
    private int id;
    private String descricao;
    private LocalDate dataAtividade;
    private int minutos; // tempo em minutos que a atividade durou

    public atividade(){
        this.id = nextID++;
        this.descricao = "";
        this.dataAtividade = LocalDate.now();
        this.minutos = 0;
    }

    public atividade(String descricao, LocalDate dataAtividade, int minutos){
        this.id = nextID++;
        this.descricao = descricao;
        this.dataAtividade = dataAtividade;
        this.minutos = minutos;
    }

    public atividade(atividade a){
        this.id = a.getId();
        this.descricao = a.getDescricao();
        this.dataAtividade = a.getDataAtividade();
        this.minutos = a.getMinutos();
    }

    public int getId(){
        return this.id;
    }

    public String getDescricao(){
        return this.descricao;
    }

    public LocalDate getDataAtividade(){
        return this.dataAtividade;
    }

    public int getMinutos(){
        return this.minutos;
    }

    public void setDescricao(String descricao){
        this.descricao = descricao;
    }

    public void setDataAtividade(LocalDate dataAtividade){
        this.dataAtividade = dataAtividade;
    }

    public void setMinutos(int minutos){
        this.minutos = minutos;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("ID: ").append(this.id).append("\n");
        sb.append("Descrição: ").append(this.descricao).append("\n");
        sb.append("Data da atividade: ").append(this.dataAtividade).append("\n");
        sb.append("Minutos: ").append(this.minutos).append("\n");
        return sb.toString();
    }

    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        atividade a = (atividade) o;
        return this.id == a.getId() &&
                this.descricao.equals(a.getDescricao()) &&
                this.dataAtividade.equals(a.getDataAtividade()) &&
                this.minutos == a.getMinutos();
    }

    public atividade clone(){
        return new atividade(this);
    }

}
