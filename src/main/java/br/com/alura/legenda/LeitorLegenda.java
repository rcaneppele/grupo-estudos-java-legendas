package br.com.alura.legenda;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LeitorLegenda {

    private Scanner scanner;
    private List<BlocoFala> blocos = new ArrayList<>();
    private static final DateTimeFormatter FORMATO_HORARIO = DateTimeFormatter.ofPattern("HH:mm:ss,SSS");;

    public LeitorLegenda(String legenda) {
        try {
            InputStream inputStream = new FileInputStream(new File(legenda));
            scanner = new Scanner(inputStream);
            lerBlocos();
        } catch (IOException e) {
            throw new RuntimeException("Erro ao ler o arquivo!", e);
        }
    }

    private void lerBlocos() {
        while (scanner.hasNextLine()) {
            Integer id = Integer.parseInt(scanner.nextLine());

            String linhaMinutagem = scanner.nextLine();
            String minutagens[] = linhaMinutagem.split(" --> ");
            String minutagemInicial = minutagens[0];
            String minutagemFinal = minutagens[1];

            LocalTime horarioInicial = LocalTime.parse(minutagemInicial, FORMATO_HORARIO);
            horarioInicial = horarioInicial.plusNanos(tempo * 1_000_000);

            LocalTime horarioFinal = LocalTime.parse(minutagemFinal, FORMATO_HORARIO);
            horarioFinal = horarioFinal.plusNanos(tempo * 1_000_000);

            BlocoFala bloco = new BlocoFala(id, inicio, fim, texto);
        }
    }

    public String lerProximaLinha() {
        return scanner.nextLine();
    }

    public boolean temLinha() {
        return scanner.hasNextLine();
    }
}
