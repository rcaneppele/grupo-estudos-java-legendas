package br.com.alura.legenda;

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
            LeitorLegenda leitor = new LeitorLegenda(nomeArquivo);
            EscritorArquivo escritor = new EscritorArquivo(nomeArquivo);

            escritor.escreverLinha(leitor.lerProximaLinha());
            String linhaMinutagem = leitor.lerProximaLinha();
            String minutagens[] = linhaMinutagem.split(" --> ");
            String minutagemInicial = minutagens[0];
            String minutagemFinal = minutagens[1];

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss,SSS");

            LocalTime horarioInicial = LocalTime.parse(minutagemInicial, formatter);
            horarioInicial = horarioInicial.plusNanos(tempo * 1_000_000);

            LocalTime horarioFinal = LocalTime.parse(minutagemFinal, formatter);
            horarioFinal = horarioFinal.plusNanos(tempo * 1_000_000);

            escritor.escreverLinhaMinutagem(horarioInicial, horarioFinal);

            while (leitor.temLinha()) {
                String proximaLinha = leitor.lerProximaLinha();
                if (!proximaLinha.isEmpty()) {
                    escritor.escreverLinha(proximaLinha);
                    continue;
                }

                escritor.escreverLinha("");
                escritor.escreverLinha(leitor.lerProximaLinha());
                linhaMinutagem = leitor.lerProximaLinha();
                minutagens = linhaMinutagem.split(" --> ");
                minutagemInicial = minutagens[0];
                minutagemFinal = minutagens[1];

                horarioInicial = LocalTime.parse(minutagemInicial, formatter);
                horarioInicial = horarioInicial.plusNanos(tempo * 1_000_000);

                horarioFinal = LocalTime.parse(minutagemFinal, formatter);
                horarioFinal = horarioFinal.plusNanos(tempo * 1_000_000);

                escritor.escreverLinhaMinutagem(horarioInicial, horarioFinal);
            }
        } catch (Exception e) {
            System.out.println("Erro ao carregar o arquivo: " + e.getMessage());
        }
    }

}
