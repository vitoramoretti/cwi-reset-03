package br.com.cwi.reset.vitoramoretti.controller;


import br.com.cwi.reset.vitoramoretti.FakeDatabase;
import br.com.cwi.reset.vitoramoretti.model.Filme;
import br.com.cwi.reset.vitoramoretti.request.FilmeRequest;
import br.com.cwi.reset.vitoramoretti.service.FilmeService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/filmes")
public class FilmeController {

    private FilmeService filmeService;

    public FilmeController() {
        this.filmeService = new FilmeService(FakeDatabase.getInstance());
    }

    @GetMapping
    public List<Filme> consultarFilmes(
            @RequestParam String nomeFilme,
            @RequestParam String nomeDiretor,
            @RequestParam String nomeAtor,
            @RequestParam String nomePersonagem) throws Exception {
        return filmeService.consultarFilmes(nomeFilme, nomeDiretor, nomeAtor, nomePersonagem);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void criarFilme(@RequestBody FilmeRequest filmeRequest) throws Exception {
        filmeService.criarFilme(filmeRequest);
    }
}
