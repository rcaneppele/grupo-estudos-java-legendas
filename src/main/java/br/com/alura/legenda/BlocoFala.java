package br.com.alura.legenda;

import java.time.LocalTime;
import java.util.List;

public record BlocoFala(Integer id, LocalTime inicio, LocalTime fim, List<String> texto) {}
