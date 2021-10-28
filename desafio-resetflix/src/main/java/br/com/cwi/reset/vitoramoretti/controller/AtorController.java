package br.com.cwi.reset.vitoramoretti.controller;

import br.com.cwi.reset.vitoramoretti.FakeDatabase;
import br.com.cwi.reset.vitoramoretti.model.Ator;
import br.com.cwi.reset.vitoramoretti.request.AtorRequest;
import br.com.cwi.reset.vitoramoretti.response.AtorEmAtividade;
import br.com.cwi.reset.vitoramoretti.service.AtorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/atores")
public class AtorController {

    private AtorService atorService;

    @Autowired
    public AtorController(AtorService atorService) {
        this.atorService = atorService;
    }

    @GetMapping
    public List<Ator> consultarAtores() throws Exception {
        return this.atorService.consultarAtores();
    }

    @GetMapping("/{id}")
    public Ator consultarAtor(@PathVariable Integer id) throws Exception {
        return this.atorService.consultarAtor(id);
    }

    @GetMapping("/em_atividade")
    public List<AtorEmAtividade> listarAtoresEmAtividade (@RequestParam String filterNome) throws Exception {
        return this.atorService.listarAtoresEmAtividade(filterNome);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void criarAtor(@RequestBody AtorRequest atorRequest) throws Exception {
        this.atorService.criarAtor(atorRequest);
    }


}
