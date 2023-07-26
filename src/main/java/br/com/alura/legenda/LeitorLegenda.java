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
    private static final DateTimeFormatter FORMATO_HORARIO = DateTimeFormatter.ofPattern("HH:mm:ss,SSS");

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
            LocalTime horarioInicial = lerHora(linhaMinutagem.split(" --> ")[0]);
            LocalTime horarioFinal = lerHora(linhaMinutagem.split(" --> ")[1]);

            List<String> textos = lerTextos();
            BlocoFala bloco = new BlocoFala(id, horarioInicial, horarioFinal, textos);
            blocos.add(bloco);
        }
    }

    private LocalTime lerHora(String hora) {
        return LocalTime.parse(hora, FORMATO_HORARIO);
    }

    private List<String> lerTextos() {
        List<String> textos = new ArrayList<>();

        String texto = scanner.nextLine();
        while (!texto.isEmpty()) {
            textos.add(texto);
            if (!scanner.hasNextLine()) {
                break;
            }
            texto = scanner.nextLine();
        }

        return textos;
    }

    public List<BlocoFala> getBlocos() {
        return blocos;
    }
}
