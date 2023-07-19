package br.com.alura.legenda;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class EscritorArquivo {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss,SSS");

    private final PrintStream printStream;

    public EscritorArquivo(String nomeArquivo) {
        try {
            this.printStream = new PrintStream(new File(nomeArquivo.replace(".srt", "") +"-novo.srt"));
        } catch (IOException e) {
            throw new RuntimeException("Erro ao escrever o arquivo!", e);
        }
    }

    public void escreverLinha(String linha) {
        printStream.println(linha);
    }

    public void escreverLinhaMinutagem(LocalTime horarioInicial, LocalTime horarioFinal) {
        printStream.print(horarioInicial.format(formatter));
        printStream.print(" --> ");
        printStream.println(horarioFinal.format(formatter));
    }


}
