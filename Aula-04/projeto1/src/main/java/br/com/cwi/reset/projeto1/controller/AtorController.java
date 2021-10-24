package br.com.cwi.reset.projeto1.controller;

import br.com.cwi.reset.projeto1.domain.Ator;
import br.com.cwi.reset.projeto1.domain.Pet;
import br.com.cwi.reset.projeto1.exception.AtorDoesNotExistException;
import br.com.cwi.reset.projeto1.exception.AtorExistsException;
import br.com.cwi.reset.projeto1.exception.PetDoesNotExistException;
import br.com.cwi.reset.projeto1.service.AtorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ator")
public class AtorController {

    @Autowired
    private AtorService atorService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Ator createAtor(@RequestBody Ator ator) throws AtorExistsException {
        return atorService.save(ator);
    }

    @GetMapping
    public List<Ator> getAtor() {
        return atorService.findAll();
    }

    @GetMapping("/{name}")
    public Ator getByNome(@PathVariable String name) throws AtorDoesNotExistException {
        return atorService.findByNome(name);
    }

    @GetMapping("/{numeroOscars}")
    public List<Ator> getByNumeroOscars(@PathVariable Integer numeroOscars) throws AtorDoesNotExistException {
        return atorService.findByNumeroOscars(numeroOscars);
    }

    @DeleteMapping("/{name}")
    public void deleteAtor(@PathVariable String name) throws AtorDoesNotExistException {
        atorService.delete(name);
    }

    @PutMapping
    public Ator update(@RequestBody Ator ator) throws AtorDoesNotExistException {
        return atorService.update(ator);
    }


}

