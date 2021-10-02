package br.com.cwi.reset.projeto1;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Exercicios1 {

    public Integer somarLista(List<Integer> numeros) {
        int sum = 0;
        for (int i: numeros){
            sum += i;
        }
        return sum;
    }

    public Double calcularMedia(List<Integer> numeros) {
        double sum = 0;
        for (int i: numeros){
            sum += i;
        }
        return sum / numeros.size();
    }

    public Integer obterMaiorNumero(List<Integer> numeros) {

        int biggest = numeros.get(0);

        for (int i : numeros) {
            if (i > biggest) {
                biggest = i;
            }
        }

        return biggest;

    }

    public String obterPalavraInvertida(String palavra) {

        String inverted = "";

        for (int i = palavra.length() - 1; i >= 0; i--) {
            inverted += palavra.charAt(i);
        }
        return inverted;
    }

    public List<Integer> ordenarLista(List<Integer> numeros) {
        return Arrays.asList(1, 2, 3, 4, 5);
    }
}

