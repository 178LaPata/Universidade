public class SmartDevice {
    public enum Estado{
        ON,
        OFF
    }
    private int id = 0;
    private Estado estado;

    public SmartDevice(){
        this.id++;
        this.estado = Estado.OFF;
    }

    public SmartDevice(Estado estado){
        this.id++;
        this.estado = estado;
    }

    public SmartDevice(SmartDevice sd){
        this.id = sd.getId();
        this.estado = sd.getEstado();
    }
    public int getId(){
        return this.id;
    }

    public Estado getEstado(){
        return this.estado;
    }

    public void setId(int id){
        this.id = id;
    }

    public void setEstado(Estado estado){
        this.estado = estado;
    }

    public boolean equals(Object o){
        if(this == o) return true;
        if((o == null) || (this.getClass() != o.getClass())) return false;
        SmartDevice sd = (SmartDevice) o;
        return (this.id == sd.getId() && this.estado == sd.getEstado());
    }

    public String toString() {
        final StringBuffer sb = new StringBuffer("SmartDevice {");
        sb.append("ID: ").append(id);
        sb.append(", Estado: ").append(estado);
        sb.append('}');
        return sb.toString();
    }

    public SmartDevice clone(){
        return new SmartDevice(this);
    }
}
