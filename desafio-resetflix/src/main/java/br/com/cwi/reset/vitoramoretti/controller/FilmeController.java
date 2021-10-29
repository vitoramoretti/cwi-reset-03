package br.com.cwi.reset.vitoramoretti.controller;


import br.com.cwi.reset.vitoramoretti.FakeDatabase;
import br.com.cwi.reset.vitoramoretti.model.Filme;
import br.com.cwi.reset.vitoramoretti.request.FilmeRequest;
import br.com.cwi.reset.vitoramoretti.service.FilmeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/filmes")
public class FilmeController {

    private FilmeService filmeService;

    @Autowired
    public FilmeController(FilmeService filmeService) {
        this.filmeService = filmeService;
    }

    @GetMapping
    public List<Filme> consultarFilmes(
            @RequestParam(required = false) String nomeFilme,
            @RequestParam(required = false) String nomeDiretor,
            @RequestParam(required = false) String nomeAtor,
            @RequestParam(required = false) String nomePersonagem) throws Exception {
        return this.filmeService.consultarFilmes(nomeFilme, nomeDiretor, nomeAtor, nomePersonagem);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void criarFilme(@RequestBody FilmeRequest filmeRequest) throws Exception {
        filmeService.criarFilme(filmeRequest);
    }
}
