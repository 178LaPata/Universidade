public class output {
    private void printLine(int size) {
        for(int i=0; i<size; i++)
            System.out.print("-");

        System.out.println("");
    }

    public void printMenus(String []menu, String message, int type){

        int size, length=message.length();

        for(String linha: menu)
            if(linha.length() + 4 > length)
                length = linha.length() + 4;

        if(length < 20)
            length = 20;

        printLine(length);
        System.out.println(message);
        printLine(length);

        size = menu.length;
        for(int i = 0;i < size;i++)
            System.out.println(i+1+" | "+menu[i]);
        if(type == 0)
            System.out.println("0 | Sair");
        else
            System.out.println("0 | Voltar atrás");
        printLine(length);
    }


    public void printMessage(String message) {
        System.out.println(message);
    }

    public void printDouble(Double dou){
        System.out.println(dou);
    }

    public void printInteiro(int inteiro){
        System.out.println(inteiro);
    }
}
