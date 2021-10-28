package br.com.cwi.reset.vitoramoretti.controller;

import br.com.cwi.reset.vitoramoretti.FakeDatabase;
import br.com.cwi.reset.vitoramoretti.model.Estudio;
import br.com.cwi.reset.vitoramoretti.request.EstudioRequest;
import br.com.cwi.reset.vitoramoretti.service.EstudioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estudios")
public class EstudioController {

    private EstudioService estudioService;

    @Autowired
    public EstudioController(EstudioService estudioService) {
        this.estudioService = estudioService;
    }

    @GetMapping
    public List<Estudio> consultarEstudios(@RequestParam String filterNome) throws Exception {
        return estudioService.consultarEstudios(filterNome);
    }

    @GetMapping("/{id}")
    public Estudio consultarEstudio(@PathVariable Integer id) throws Exception {
        return estudioService.consultarEstudio(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void criarEstudio(@RequestBody EstudioRequest estudioRequest) throws Exception {
        estudioService.criarEstudio(estudioRequest);
    }



}
