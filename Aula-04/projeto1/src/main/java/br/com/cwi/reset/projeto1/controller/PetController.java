package br.com.cwi.reset.projeto1.controller;

import br.com.cwi.reset.projeto1.domain.Pet;
import br.com.cwi.reset.projeto1.exception.PetExistsException;
import br.com.cwi.reset.projeto1.exception.PetDoesNotExistException;
import br.com.cwi.reset.projeto1.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pet")
public class PetController {

    @Autowired
    private PetService petService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Pet savePet(@RequestBody Pet pet) throws PetExistsException {
        return petService.save(pet);
    }

    @GetMapping
    public List<Pet> getPet() {
        return petService.findAll();
    }

    @GetMapping("/{name}")
    public Pet getByName(@PathVariable String name) throws PetDoesNotExistException {
        return petService.findByName(name);
    }

    @GetMapping("/especie/{nome}")
    public List<Pet> findByEspecie(@PathVariable String nome) {
        return petService.findByEspecie(nome);
    }

    @DeleteMapping("/{name}")
    public void deletePet(@PathVariable String name) throws PetDoesNotExistException {
        petService.delete(name);
    }

    @PutMapping
    public Pet updatePet(@RequestBody Pet pet) throws PetDoesNotExistException {
        return petService.update(pet);
    }

}
