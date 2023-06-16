package teste05_2022;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

class Livro implements Comparable<Livro>, Serializable {
    public String codISBN; //código ISBN do livro
    private String nomeLivro;
    private String autor;
    private String editora;
    private List<Pagina> pagLidas; // páginas já lidas
    private List<Pagina> pagPorLer; //páginas ainda por ler.

    public Pagina devolvePag(int numPag) throws PagInexistenteException {
        Pagina res = null;
        int numLidas = this.pagLidas.size(); //número de páginas lidas
        int porLer = this.pagPorLer.size();
        if (numPag > numLidas+porLer)
            throw new PagInexistenteException(numLidas);
        if (numPag <= numLidas )
            res = this.pagLidas.get(numPag -1);
        else
            res = this.pagPorLer.get(numPag-numLidas -1);
        return res.clone();
    }
}


class Pagina implements Comparable<Pagina>, Serializable {
    private List<String> texto;
    public Pagina() {
        this.texto = new ArrayList<>();
    }
    
    public String reproduzPagina() {...}
}


class PaginaComAudio extends Pagina implements Comparable<PaginaComAudio>, Serializable {
    private String narrador;
    private List<Byte> som;

    public PaginaComAudio(List<String> texto, String narrador, List<Byte> audio) {
        super(texto);
        this.narrador = narrador;
        this.audio = new ArrayList<>(audio);
    }
    public String reproduzPagina() {...}
}


class Utilizador implements Serializable {
    private String numUser;
    private String nomeUser;
    private LocalDate dataAdesao; 
    private List<Livro> livrosAdquiridos;

    public Utilizador(String numUser, String nomeUser, Iterator<Livro> livros){
        this.numUser = numUser;
        this.nomeUser = nomeUser;
        this.dataAdesao = LocalDate.now();
        this.livrosAdquiridos = new ArrayList<>();
        while (livros.hasNext())
            this.livrosAdquiridos.add(livros.next().clone());
    }

    public void avancaPags(String codISBN, int n) throws PagInexistenteException, LivroInexistenteException {
        Livro l = null;
        for (Livro livro : this.livrosAdquiridos) { 
            if (livro.getCodISBN().equals(codISBN)) { 
                l = livro; 
                break;
            }
        }
        if (l == null) 
            throw new LivroInexistenteException(codISBN);
        int numLidas = l.getPagLidas().size(); 
        int numPorLer = l.getPagPorLer().size(); 
        if (numLidas + numPorLer < n) 
            throw new PagInexistenteException(numLidas + numPorLer); 
        if (numLidas < n) { 
            int pagPorLer = n - numLidas; 
            for (int i = 0; i < pagPorLer; i++) { 
                Pagina p = l.getPagPorLer().remove(0); //remove a primeira página da lista de páginas por ler
                l.getPagLidas().add(p.clone()); //adiciona a página à lista de páginas lidas
            }
        }
    }

    // Resolução TITI
    //public void avancaPags(String codISBN, int n) throws LivroLidoException, LivroNaoAdquiridoException{
    //    int i = 0;
    //    for(Livro l : this.livrosAdquiridos){
    //        if(l.getcodISBN().equals(codISBN)){
    //            if(l.getPaginasPorLer() == null){
    //                throw new LivroLidoException;
    //            }
    //            else{
    //                for (; i<n;i++){
    //                    Pagina p = l.getPaginasPorLer().get(0);
    //                    l.getPaginasLidas.add(p.clone);
    //                    l.getPaginasPorLer.remove(p);
    //                }
    //            }
    //        }
    //    }
    //    if(i==0){
    //        throw new LivroNaoAdquiridoException;
    //    }
    //}
}


class LivrosDigitais {
    List<Utilizador> utilizadores;
    
    public Livro livroMaisLido() {
        Livro livroMaisLido = null;
        int paginasLidasMaisLido = 0;

        for (Utilizador utilizador : utilizadores) {
            for (Livro livro : utilizador.getLivrosAdquiridos()) {
                int paginasLidas = livro.getPagLidas().size();
                if (paginasLidas > paginasLidasMaisLido || (paginasLidas == paginasLidasMaisLido 
                    && livroMaisLido != null && livro.getNomeLivro().compareTo(livroMaisLido.getNomeLivro()) > 0)) {
                    livroMaisLido = livro;
                    paginasLidasMaisLido = paginasLidas;
                }
            }
        }

        return livroMaisLido;
    }

    public Map<String,List<Livro>> livrosPorEditora(){
        Map<String,List<Livro>> res = new HashMap<>();
        for (Utilizador utilizador : utilizadores) {
            for (Livro livro : utilizador.getLivrosAdquiridos()) {
                String editora = livro.getEditora();
                if (!res.containsKey(editora)) {
                    res.put(editora, new ArrayList<>());
                }
                List<Livro> listaLivros = res.get(editora);
                if(!listaLivros.contains(livro)) {
                    listaLivros.add(livro.clone());
                }
            }
        }
        return res;
    }
}

