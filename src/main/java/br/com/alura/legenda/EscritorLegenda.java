package br.com.alura.legenda;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class EscritorLegenda {

    private static final DateTimeFormatter FORMATO = DateTimeFormatter.ofPattern("HH:mm:ss,SSS");

    private final PrintStream printStream;

    public EscritorLegenda(String nomeArquivo, int tempo, List<BlocoFala> blocos) {
        try {
            this.printStream = new PrintStream(new File(nomeArquivo.replace(".srt", "") +"-novo.srt"));
            escreverLegenda(tempo, blocos);
        } catch (IOException e) {
            throw new RuntimeException("Erro ao escrever o arquivo!", e);
        }
    }

    private void escreverLegenda(int tempo, List<BlocoFala> blocos) {
        for (BlocoFala bloco : blocos) {
            printStream.println(bloco.id());

            LocalTime inicioAjustado = bloco.inicio().plusNanos(tempo * 1_000_000);
            LocalTime fimAjustado = bloco.fim().plusNanos(tempo * 1_000_000);

            printStream.print(inicioAjustado.format(FORMATO));
            printStream.print(" --> ");
            printStream.println(fimAjustado.format(FORMATO));

            bloco.texto().forEach(printStream::println);

            printStream.println("");
        }
    }

}
