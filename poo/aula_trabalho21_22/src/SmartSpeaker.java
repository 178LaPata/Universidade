public class SmartSpeaker extends SmartDevice {
    private int volume;
    private double canal;

    public SmartSpeaker(){
        super();
        this.volume = 0;
        this.canal = 0.0;
    }

    public SmartSpeaker(int volume, double canal, Estado estado){
        super(estado);
        this.volume = volume;
        this.canal = canal;
    }

    public SmartSpeaker(SmartSpeaker ss){
        super(ss);
        this.volume = ss.getVolume();
        this.canal = ss.getCanal();
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public double getCanal() {
        return canal;
    }

    public void setCanal(double canal) {
        this.canal = canal;
    }
    public boolean equals(Object o){
        if(this == o) return true;
        if((o == null) || (this.getClass() != o.getClass())) return false;
        SmartDevice sd = (SmartSpeaker) o;
        return (super.equals(sd) && this.volume == ((SmartSpeaker) o).getVolume() && this.canal == ((SmartSpeaker) o).getCanal());
    }

    public String toString() {
        final StringBuffer sb = new StringBuffer("SmartSpeaker {");
        sb.append("Volume: ").append(volume);
        sb.append(", Canal: ").append(canal);
        sb.append(", ").append(super.toString());
        sb.append('}');
        return sb.toString();
    }

    public SmartSpeaker clone(){
        return new SmartSpeaker(this);
    }
}
