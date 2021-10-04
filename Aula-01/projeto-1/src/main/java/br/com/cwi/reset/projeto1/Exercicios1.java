package br.com.cwi.reset.projeto1;

import java.util.Arrays;
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

        Integer[] nums = numeros.toArray(new Integer[numeros.size()]);
        int temp = 0;

        for (int i = 0; i < numeros.size() - 1; i++) {
            for (int j = i + 1; j < numeros.size(); j++) {
                if(nums[j] < nums[i]) {
                    temp = nums[j];
                    nums[j] = nums[i];
                    nums[i] = temp;
                }
            }
        }


        return Arrays.asList(nums);
    }
}

