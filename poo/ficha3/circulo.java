import java.lang.Math;

public class circulo {
    private double x;
    private double y;
    private double raio;

    public circulo(){
        this.x = 0;
        this.y = 0;
        this.raio = 0;
    }
    
    public circulo(double x, double y, double raio){
        this.x = x;
        this.y = y;
        this.raio = raio;
    }

    public circulo(circulo c1){
        this.x = c1.getX();
        this.y = c1.getY();
        this.raio = c1.getRaio();
    }

    public double getX(){
        return this.x;
    }
    
    public double getY(){
        return this.y;
    }

    public double getRaio(){
        return this.raio;
    }

    public void setX(double x){
        this.x = x;
    }

    public void setY(double y){
        this.y = y;
    }

    public void setRaio(double raio){
        this.raio = raio;
    }

    public boolean equals(Object o){
        if (this == o) return true;
        if ((o == null) || (this.getClass() != o.getClass())) return false;
        circulo c = (circulo) o;
        return (this.x == c.getX() && this.y == c.getY() && this.raio == c.getRaio());
    }
    
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Circulo de centro (").append(this.x).append(",").append(this.y).append(") e raio ").append(this.raio);
        return sb.toString();
    }

    public circulo clone(){
        return new circulo(this);
    }

    public void alteraCentro(double x, double y){
        this.x = x;
        this.y = y;
    }

    public double calculaArea(){
        double area;
        area = Math.PI * Math.pow(this.raio, 2);
        return area;
    }

    public double calculaPerimetro(){
        double per;
        per = 2 * Math.PI * this.raio;
        return per;
    }
}
