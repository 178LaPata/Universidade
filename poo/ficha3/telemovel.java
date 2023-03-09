public class telemovel {
    private String marca;
    private String modelo;
    private int displayX;
    private int displayY;
    private byte armMensagens;
    private byte armFotos;
    private byte armApps;
    private int armTotalFotosApps;
    private int armTotalOcupado;
    private int armMaximo;
    private String[] nomeApps;
    private String[] mensagens;
    private int nrFotos;
    private int nrApps;
    private int nrMensagens;

    public telemovel(){
        this.marca = "n/a";
        this.modelo = "n/a";
        this.displayX = 0;
        this.displayY = 0;
        this.armMensagens = 0;
        this.armFotos = 0;
        this.armApps = 0;
        this.armTotalFotosApps = 0;
        this.armTotalOcupado = 0;
        this.armMaximo = 0;
        this.nomeApps = new String[20];
        this.mensagens = new String[20];
        this.nrFotos = 0;
        this.nrApps = 0;
        this.nrMensagens = 0;
    }

    public telemovel(String marca, String modelo, int displayX, int displayY, byte armMensagens, byte armFotos, byte armApps, int armMaximo){
        this.marca = new String(marca);
        this.modelo = new String(modelo);
        this.displayX = displayX;
        this.displayY = displayY;
        this.armMensagens = armMensagens;
        this.armFotos = armFotos;
        this.armApps = armApps;
        this.armTotalFotosApps = (int) (0.8*armMaximo);
        this.armTotalOcupado = 0;
        this.armMaximo = armMaximo;
        this.nomeApps = new String[20];
        this.mensagens = new String[20];
        this.nrFotos = 0;
        this.nrApps = 0;
        this.nrMensagens = 0;
    }

    public telemovel(telemovel t){
        this.marca = t.getMarca();
        this.modelo = t.getModelo();
        this.displayX = t.getDisplayX();
        this.displayY = t.getDisplayY();
        this.armMensagens = t.getArmMensagens();
        this.armFotos = t.getArmFotos();
        this.armApps = t.getArmApps();
        this.armTotalFotosApps = t.getArmTotalFotosApps();
        this.armTotalOcupado = t.getArmTotalOcupado();
        this.armMaximo = t.getArmMaximo();
        this.nomeApps = t.getNomeApps();
        this.mensagens = t.getMensagens();
        this.nrFotos = t.getNrFotos();
        this.nrApps = t.getNrApps();
        this.nrMensagens = t.getNrMensagens();
    }

    public String getMarca(){
        return this.marca;
    }

    public String getModelo(){
        return this.modelo;
    }

    public int getDisplayX(){
        return this.displayX;
    }

    public int getDisplayY(){
        return this.displayY;
    }

    public byte getArmMensagens(){
        return this.armMensagens;
    }

    public byte getArmFotos(){
        return this.armFotos;
    }

    public byte getArmApps(){
        return this.armApps;
    }

    public int getArmTotalFotosApps(){
        return this.armTotalFotosApps;
    }

    public int getArmTotalOcupado(){
        return this.armTotalOcupado;
    }

    public int getArmMaximo(){
        return this.armMaximo;
    }

    public String[] getNomeApps(){
        String[] nomeApps = new String[20];
        for(int i = 0; i < this.nomeApps.length; i++){
            nomeApps[i] = this.nomeApps[i];
        }
        return nomeApps;
    }

    public String[] getMensagens(){
        String[] mensagens = new String[20];
        for(int i = 0; i < this.mensagens.length; i++){
            mensagens[i] = this.mensagens[i];
        }
        return mensagens;
    }

    public int getNrFotos(){
        return this.nrFotos;
    }

    public int getNrApps(){
        return this.nrApps;
    }

    public int getNrMensagens(){
        return this.nrMensagens;
    }

    public void setMarca(String marca){
        this.marca = marca;
    }

    public void setModelo(String modelo){
        this.modelo = modelo;
    }

    public void setDisplayX(int displayX){
        this.displayX = displayX;
    }

    public void setDisplayY(int displayY){
        this.displayY = displayY;
    }

    public void setArmMensagens(byte armMensagens){
        this.armMensagens = armMensagens;
    }

    public void setArmFotos(byte armFotos){
        this.armFotos = armFotos;
    }

    public void setArmApps(byte armApps){
        this.armApps = armApps;
    }

    public void setArmTotalFotosApps(int armTotalFotosApps){
        this.armTotalFotosApps = armTotalFotosApps;
    }

    public void setArmTotalOcupado(int armTotalOcupado){
        this.armTotalOcupado = armTotalOcupado;
    }

    public void setArmMaximo(int armMaximo){
        this.armMaximo = armMaximo;
    }

    public void setNomeApps(String[] nomeApps){
        this.nomeApps = nomeApps;
    }

    public void setMensagens(String[] mensagens){
        this.mensagens = mensagens;
    }

    public void setNrFotos(int nrFotos){
        this.nrFotos = nrFotos;
    }

    public void setNrApps(int nrApps){
        this.nrApps = nrApps;
    }

    public void setNrMensagens(int nrMensagens){
        this.nrMensagens = nrMensagens;
    }

    
    public boolean equals(Object o){
        if(this == o) 
            return true;
        if((o == null) || (this.getClass() != o.getClass())) 
            return false;
        telemovel t = (telemovel) o;
        return (this.marca.equals(t.getMarca()) && 
        this.modelo.equals(t.getModelo()) && 
        this.displayX == t.getDisplayX() && 
        this.displayY == t.getDisplayY() && 
        this.armMensagens == t.getArmMensagens() && 
        this.armFotos == t.getArmFotos() && 
        this.armApps == t.getArmApps() && 
        this.armTotalFotosApps == t.getArmTotalFotosApps() && 
        this.armTotalOcupado == t.getArmTotalOcupado() && 
        this.armMaximo == t.getArmMaximo() && 
        this.nomeApps.equals(t.getNomeApps()) && 
        this.mensagens.equals(t.getMensagens()) && 
        this.nrFotos == t.getNrFotos() && 
        this.nrApps == t.getNrApps() && 
        this.nrMensagens == t.getNrMensagens());
    }
    
    public String toString(){
        return "Marca: " + this.marca + 
        "\nModelo: " + this.modelo + 
        "\nDisplay X: " + this.displayX + 
        "\nDisplay Y: " + this.displayY + 
        "\nArmazenamento de mensagens: " + this.armMensagens + 
        "\nArmazenamento de fotos: " + this.armFotos + 
        "\nArmazenamento de apps: " + this.armApps + 
        "\nArmazenamento total de fotos e apps: " + this.armTotalFotosApps + 
        "\nArmazenamento total ocupado: " + this.armTotalOcupado + 
        "\nArmazenamento máximo: " + this.armMaximo + 
        "\nNome das apps: " + this.nomeApps + 
        "\nMensagens: " + this.mensagens + 
        "\nNúmero de fotos: " + this.nrFotos + 
        "\nNúmero de apps: " + this.nrApps + 
        "\nNúmero de mensagens: " + this.nrMensagens + "\n";
    }
    
    public telemovel clone(){
        return new telemovel(this);
    }

    public boolean existeEspaco(int numeroBytes){
        return (this.armTotalOcupado + numeroBytes) < this.armMaximo;

    }

    public void instalaApp(String nome, int tamanho){
        if(this.existeEspaco(tamanho)){
            this.nomeApps[this.nrApps] = nome;
            this.nrApps++;
            this.armTotalOcupado += tamanho;
        }
    }

    public void recebeMsg(String msg){
        if(this.existeEspaco(msg.length())){
            this.mensagens[this.nrMensagens] = msg;
            this.nrMensagens++;
            this.armTotalOcupado += msg.length();
        }
    }

    public double tamMedioApps(){
        double soma = 0;
        
        for(int i = 0; i < this.nrApps; i++){
            soma += this.armApps;
        }
        return soma / this.nrApps;
    }

    public String maiorMsg(){
        int maior = 0;
        for(int i = 0; i < this.nrMensagens; i++){
            if(this.mensagens[i].length() > this.mensagens[maior].length()){
                maior = i;
            }
        }
        return this.mensagens[maior];
    }

    public void removeApp(String nome, int tamanho){
        for(int i = 0; i < this.nrApps; i++){
            if(this.nomeApps[i].equals(nome)){
                this.nomeApps[i] = this.nomeApps[this.nrApps - 1];
                this.nomeApps[this.nrApps - 1] = null;
                this.nrApps--;
                this.armTotalOcupado -= tamanho;
            }
        }
    }
}
