package br.com.alura.legenda;

import java.util.Scanner;

public class LegendaApplication {

    public static void main(String[] args) {
        System.out.println("Digite o tempo em milisegundos:");
        int tempo = new Scanner(System.in).nextInt();

        System.out.println("Digite o nome do arquivo srt:");
        String nomeArquivo = new Scanner(System.in).nextLine();

        try {
            LeitorLegenda leitor = new LeitorLegenda(nomeArquivo);
            EscritorLegenda escritor = new EscritorLegenda(nomeArquivo, tempo, leitor.getBlocos());
        } catch (Exception e) {
            System.out.println("Erro ao ajustar a legenda: " + e.getMessage());
        }
    }

}
