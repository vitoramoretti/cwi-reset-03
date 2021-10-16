package br.com.cwi.reset.primeiroprojetospring.controller;


import br.com.cwi.reset.primeiroprojetospring.domain.Diretor;
import br.com.cwi.reset.primeiroprojetospring.domain.Filme;
import br.com.cwi.reset.primeiroprojetospring.domain.Genero;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/filme")
public class FilmeController {

    @GetMapping
    public Filme getFilme() {
        LocalDate dataNascimento = LocalDate.of(1960, 4,23);
        Diretor seanpean = new Diretor("Sean Penn", dataNascimento, 45, Genero.MASCULINO);
        Filme intoTheWild = new Filme("Into The Wild", "Um otimo filme", 120, 2012, 5.0, seanpean);

        return intoTheWild;
    }
}
