// Autor: Pedro de Souza Almeida
// Data: 04/07/2025

import java.util.HashMap;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Sistema {
    public static void main(String[] args){
        // Cria o HashMap com os elementos e suas características
        HashMap<String, ElementoQuimico> tabela = new HashMap<>();

        // Cria o Scanner que será usado para ler o arquivo
        Scanner scanner;
        Scanner teclado = new Scanner(System.in);

        // Se não acontecer nenhum erro, executa esse bloco
        try{
            // Leitura do arquivo
            scanner = new Scanner(new File("bdtabperiodica.txt"));

            // Enquanto houver próxima linha para ler, executa o código
            while(scanner.hasNextLine()){
                String linha = scanner.nextLine();

                // Separa os dados em cada linha
                String[] dados = linha.split(",");

                // Cria um novo objeto com os dados presentes em cada linha
                ElementoQuimico elemento = new ElementoQuimico(dados[0], dados[1], Integer.parseInt(dados[2]), Double.parseDouble(dados[3]), Integer.parseInt(dados[4]));

                // Adiciona o elemento no HashMap
                tabela.put(dados[1], elemento);
            }

            // Mecanismo de consulta
            System.out.print("Digite a fórmula de uma substância ('sair' para fechar): ");
            String consulta = teclado.nextLine();
            while(!consulta.equalsIgnoreCase("sair")){
                // Divide os elementos quando aparece um ".", colocando-os numa lista
                String[] formula = consulta.split("\\.");
                Double massaTotal = 0.0;
                
                System.out.println("=========================================");
                System.out.println(consulta.replace(".","") + ":");

                // Passa por todos os elementos presentes na lista formada
                for(String s : formula){
                    // Pega todos os caracteres que forem letras, se não for substitui por vazio
                    String simbolo = s.replaceAll("[^A-Za-z]", "");

                    // Pega todos os caracteres que forem número, se não for substitui por vazio
                    String quantidadeStr = s.replaceAll("[^0-9]", "");

                    // Se estiver vazio, entende como 1
                    int quantidade = quantidadeStr.isEmpty() ? 1 : Integer.parseInt(quantidadeStr);

                    // Pega os dados do elemento atual
                    ElementoQuimico atual = tabela.get(simbolo);

                    // Pega a massa molar do elemento e multiplica pelo quantidade que ele aparece na substância
                    Double massa = atual.massa_atom * quantidade;
                    
                    // Soma a massa do elemento à massa total da substância
                    massaTotal += massa;

                    System.out.println(tabela.get(simbolo));
                }
                System.out.println(String.format("Massa molar da substância +/-= %.3f g/mol", massaTotal));
                System.out.println("=========================================");
                
                System.out.print("Digite a fórmula de uma substância ('sair' para fechar): ");
                consulta = teclado.nextLine();
            }
            scanner.close();
        }
        // Se houver erro, retorna mensagem de erro
        catch(FileNotFoundException e){
            e.printStackTrace();
        }
        teclado.close();
    }
}