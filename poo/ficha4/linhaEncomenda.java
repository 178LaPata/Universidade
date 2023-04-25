
public class linhaEncomenda {
    private String referencia;
    private String descricao;
    private Double preco;
    private int quantidade;
    private double imposto;
    private double desconto;

    public linhaEncomenda() {
        this.referencia = "";
        this.descricao = "";
        this.preco = 0.0;
        this.quantidade = 0;
        this.imposto = 0.0;
        this.desconto = 0.0;
    }

    public linhaEncomenda(String ref, String des, Double pre, int quan, double imp, double desc) {
        this.referencia = ref;
        this.descricao = des;
        this.preco = pre;
        this.quantidade = quan;
        this.imposto = imp;
        this.desconto = desc;
    }

    public linhaEncomenda(linhaEncomenda l) {
        this.referencia = l.getReferencia();
        this.descricao = l.getDescricao();
        this.preco = l.getPreco();
        this.quantidade = l.getQuantidade();
        this.imposto = l.getImposto();
        this.desconto = l.getDesconto();
    }

    public String getReferencia() {
        return this.referencia;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public Double getPreco() {
        return this.preco;
    }

    public int getQuantidade() {
        return this.quantidade;
    }

    public double getImposto() {
        return this.imposto;
    }

    public double getDesconto() {
        return this.desconto;
    }

    public void setReferencia(String ref) {
        this.referencia = ref;
    }

    public void setDescricao(String des) {
        this.descricao = des;
    }

    public void setPreco(Double pre) {
        this.preco = pre;
    }

    public void setQuantidade(int quan) {
        this.quantidade = quan;
    }

    public void setImposto(Double imp) {
        this.imposto = imp;
    }

    public void setDesconto(Double desc) {
        this.desconto = desc;
    }

    public boolean equals(Object o){
        if (this == o) 
            return true;
        if ((o == null) || (this.getClass() != o.getClass())) 
            return false;
        linhaEncomenda l = (linhaEncomenda) o;
        return (this.referencia.equals(l.getReferencia()) && 
                this.descricao.equals(l.getDescricao()) && 
                this.preco == l.getPreco() && 
                this.quantidade == l.getQuantidade() && 
                this.imposto == l.getImposto() && 
                this.desconto == l.getDesconto());
    }

    public String toString(){
        return "Referencia: " + this.referencia + 
               " Descricao: " + this.descricao + 
               " Preco: " + this.preco + 
               " Quantidade: " + this.quantidade + 
               " Imposto: " + this.imposto + 
               " Desconto: " + this.desconto;
    }

    public linhaEncomenda clone() {
        return new linhaEncomenda(this);
    }

    public double calculaValorLinhaEnc(){
        double precoTotal = this.preco * this.quantidade;
        double desconto = precoTotal * this.desconto;
        double imposto = precoTotal * this.imposto;
        return precoTotal - desconto + imposto;  
    }

    public double calculaValorDesconto(){
        double precoTotal = this.preco * this.quantidade;
        double desconto = precoTotal * this.desconto;
        return desconto;
    }
}
