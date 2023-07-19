package br.com.alura.legenda;

import java.time.LocalTime;

public record BlocoFala(Integer id, LocalTime inicio, LocalTime fim, String texto) {}
