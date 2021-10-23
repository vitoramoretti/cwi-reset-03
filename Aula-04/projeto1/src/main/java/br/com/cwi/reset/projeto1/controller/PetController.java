package br.com.cwi.reset.projeto1.controller;

import br.com.cwi.reset.projeto1.domain.Pet;
import br.com.cwi.reset.projeto1.exception.PetJaExistenteException;
import br.com.cwi.reset.projeto1.exception.PetNaoExistenteException;
import br.com.cwi.reset.projeto1.service.PetService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/pet")
public class PetController {


    private PetService petService = new PetService();

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Pet savePet(@RequestBody Pet pet) throws PetJaExistenteException{
        return petService.save(pet);
    }

    @GetMapping
    public List<Pet> getPet() {
        return petService.findAll();
    }

    @GetMapping("/{name}")
    public Pet getByName(@PathVariable String name) throws PetNaoExistenteException {
        return petService.findByName(name);
    }

    @DeleteMapping("/{name}")
    public void deletePet(@PathVariable String name) throws PetNaoExistenteException{
        petService.delete(name);
    }

    @PutMapping
    public Pet updatePet(@RequestBody Pet pet) throws PetNaoExistenteException{
        return petService.update(pet);
    }

}
