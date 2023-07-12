package br.com.alura.legenda;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
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

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss,SSS");

            LocalTime horarioInicial = LocalTime.parse(minutagemInicial, formatter);
            horarioInicial = horarioInicial.plusNanos(tempo * 1_000_000);

            LocalTime horarioFinal = LocalTime.parse(minutagemFinal, formatter);
            horarioFinal = horarioFinal.plusNanos(tempo * 1_000_000);

            escritor.print(horarioInicial.format(formatter));
            escritor.print(" --> ");
            escritor.println(horarioFinal.format(formatter));

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

                horarioInicial = LocalTime.parse(minutagemInicial, formatter);
                horarioInicial = horarioInicial.plusNanos(tempo * 1_000_000);

                horarioFinal = LocalTime.parse(minutagemFinal, formatter);
                horarioFinal = horarioFinal.plusNanos(tempo * 1_000_000);

                escritor.print(horarioInicial.format(formatter));
                escritor.print(" --> ");
                escritor.println(horarioFinal.format(formatter));
            }
        } catch (Exception e) {
            System.out.println("Erro ao carregar o arquivo: " + e.getMessage());
        }
    }

}
