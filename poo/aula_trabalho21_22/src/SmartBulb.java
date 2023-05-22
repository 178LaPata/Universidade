public class SmartBulb extends SmartDevice {
    public enum Tonalidade{
        WARM,
        NEUTRAL,
        COLD
    }

    private Tonalidade tonalidade;

    public SmartBulb(){
        super();
        this.tonalidade = Tonalidade.NEUTRAL;
    }

    public SmartBulb(Tonalidade tonalidade, Estado estado){
        super(estado);
        this.tonalidade = tonalidade;
    }

    public SmartBulb(SmartBulb sb){
        super(sb);
        this.tonalidade = sb.getTonalidade();
    }

    public Tonalidade getTonalidade() {
        return tonalidade;
    }

    public void setTonalidade(Tonalidade tonalidade) {
        this.tonalidade = tonalidade;
    }

    public boolean equals(Object o){
        if(this == o) return true;
        if((o == null) || (this.getClass() != o.getClass())) return false;
        SmartDevice sd = (SmartBulb) o;
        return ((super.equals(o)) && this.tonalidade == ((SmartBulb) o).getTonalidade());
    }

    public String toString() {
        final StringBuffer sb = new StringBuffer("SmartBulb {");
        sb.append("Tonalidade: ").append(tonalidade);
        sb.append(", ").append(super.toString());
        sb.append('}');
        return sb.toString();
    }

    public SmartBulb clone(){
        return new SmartBulb(this);
    }
}
