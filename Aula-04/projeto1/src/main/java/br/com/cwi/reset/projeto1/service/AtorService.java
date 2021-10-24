package br.com.cwi.reset.projeto1.service;

import br.com.cwi.reset.projeto1.domain.Ator;
import br.com.cwi.reset.projeto1.domain.Pet;
import br.com.cwi.reset.projeto1.exception.AtorDoesNotExistException;
import br.com.cwi.reset.projeto1.exception.AtorExistsException;
import br.com.cwi.reset.projeto1.exception.PetDoesNotExistException;
import br.com.cwi.reset.projeto1.repository.AtorRepositoryDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AtorService {

    @Autowired
    private AtorRepositoryDb atorRepository;

    public Ator save(Ator ator) throws AtorExistsException {
        Ator savedAtor = atorRepository.findByNome(ator.getNome());

        if (savedAtor != null) {
            throw new AtorExistsException("Ator com o nome " + ator.getNome() + " já existe");
        }
        return atorRepository.save(ator);
    }

    public Ator findByNome(String nome) throws AtorDoesNotExistException {
        Ator ator = atorRepository.findByNome(nome);

        if (ator == null) {
            throw new AtorDoesNotExistException("Ator com o nome " + nome + " não existe");
        }
        return ator;
    }

    public void delete(String nome) throws AtorDoesNotExistException {
        Ator ator = atorRepository.findByNome(nome);
        if (ator == null) {
            throw new AtorDoesNotExistException("Ator com o nome " + nome + " não existe");
        }
        atorRepository.delete(ator);
    }

    public Ator update(Ator ator) throws AtorDoesNotExistException {
        Ator savedAtor = atorRepository.findByNome(ator.getNome());
        if (savedAtor == null) {
            throw new AtorDoesNotExistException("Ator com o nome " + ator.getNome() + " não existe");
        }
        return atorRepository.save(ator);
    }

    public List<Ator> findByNumeroOscars (Integer numeroOscars) {
        return atorRepository.findByNumeroOscars(numeroOscars);
    }

    public List<Ator> findAll() {
        return atorRepository.findAll();
    }
}
