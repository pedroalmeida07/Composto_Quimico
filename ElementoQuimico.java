// Autor: Pedro de Souza Almeida
// Data: 04/07/2025

public class ElementoQuimico {
    String nome;
    String simbolo;
    int num_atomico;
    double massa_atom;
    int num_massa;

    public ElementoQuimico(String nome, String simbolo, int num_atomico, double massa_atom, int num_massa){
        this.nome = nome;
        this.simbolo = simbolo;
        this.num_atomico = num_atomico;
        this.massa_atom = massa_atom;
        this.num_massa = num_massa;
    }

    public String toString(){
        String s = String.format("%s, %s, %s, %.3f u, %s", nome, simbolo, num_atomico, massa_atom, num_massa);
        return s;
    }
}
