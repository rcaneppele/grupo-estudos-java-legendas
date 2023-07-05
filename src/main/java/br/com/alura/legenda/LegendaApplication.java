package br.com.alura.legenda;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class LegendaApplication {

    public static void main(String[] args) {
        System.out.println("Digite o tempo em milisegundos:");
        int tempo = new Scanner(System.in).nextInt();

        System.out.println("Digite o nome do arquivo srt:");
        String nomeArquivo = new Scanner(System.in).nextLine();

        try {
            InputStream inputStream = new FileInputStream(new File(nomeArquivo));
            Scanner leitor = new Scanner(inputStream);

            PrintStream escritor = new PrintStream(new File(nomeArquivo.replace(".srt", "") +"-novo.srt"));

            escritor.println(leitor.nextLine());
            String linhaMinutagem = leitor.nextLine();
            String minutagens[] = linhaMinutagem.split(" --> ");
            String minutagemInicial = minutagens[0];
            String minutagemFinal = minutagens[1];
            String minutagemInicialQuebrada[] = minutagemInicial.split(",");
            String minutagemFinalQuebrada[] = minutagemFinal.split(",");
            int novaMinutagemInicial = Integer.parseInt(minutagemInicialQuebrada[1]) + tempo;
            int novaMinutagemFinal = Integer.parseInt(minutagemFinalQuebrada[1]) + tempo;

            escritor.println(minutagemInicialQuebrada[0] +"," +novaMinutagemInicial + " --> " + minutagemFinalQuebrada[0] +"," +novaMinutagemFinal);

            while (leitor.hasNextLine()) {
                String proximaLinha = leitor.nextLine();
                if (!proximaLinha.isEmpty()) {
                    escritor.println(proximaLinha);
                    continue;
                }

                escritor.println("");
                escritor.println(leitor.nextLine());
                linhaMinutagem = leitor.nextLine();
                minutagens = linhaMinutagem.split(" --> ");
                minutagemInicial = minutagens[0];
                minutagemFinal = minutagens[1];

                minutagemInicialQuebrada = minutagemInicial.split(",");
                minutagemFinalQuebrada = minutagemFinal.split(",");
                novaMinutagemInicial = Integer.parseInt(minutagemInicialQuebrada[1]) + tempo;
                novaMinutagemFinal = Integer.parseInt(minutagemFinalQuebrada[1]) + tempo;

                escritor.println(minutagemInicialQuebrada[0] +"," +novaMinutagemInicial + " --> " + minutagemFinalQuebrada[0] +"," +novaMinutagemFinal);
            }
        } catch (Exception e) {
            System.out.println("Erro ao carregar o arquivo: " + e.getMessage());
        }
    }

}
