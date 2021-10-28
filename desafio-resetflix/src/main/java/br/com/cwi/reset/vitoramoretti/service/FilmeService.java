package br.com.cwi.reset.vitoramoretti.service;

import br.com.cwi.reset.vitoramoretti.FakeDatabase;
import br.com.cwi.reset.vitoramoretti.exception.*;
import br.com.cwi.reset.vitoramoretti.model.Filme;
import br.com.cwi.reset.vitoramoretti.model.Genero;
import br.com.cwi.reset.vitoramoretti.model.PersonagemAtor;
import br.com.cwi.reset.vitoramoretti.repository.FilmeRepository;
import br.com.cwi.reset.vitoramoretti.repository.PersonagemAtorRepository;
import br.com.cwi.reset.vitoramoretti.request.FilmeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.*;

@Service
public class FilmeService {

    private FilmeRepository filmeRepository;

    private EstudioService estudioService;
    private DiretorService diretorService;
    private PersonagemAtorService personagemAtorService;

    @Autowired
    public FilmeService(FilmeRepository filmeRepository) {
        this.filmeRepository = filmeRepository;
        this.estudioService = estudioService;
        this.diretorService = diretorService;
        this.personagemAtorService = personagemAtorService;
    }

    // Registers a new movie in the DB
    public void criarFilme(FilmeRequest filmeRequest) throws Exception {
        final List<Filme> filmesCadastrados = filmeRepository.findAll();

        if (filmeRequest.getNome() == null) {
            throw new NomeNaoInformadoException();
        }

        if (filmeRequest.getAnoLancamento() == null) {
            throw new AnoLancamentoNaoInformadoException();
        }

        if (filmeRequest.getCapaFilme() == null) {
            throw new CapaFilmeNaoInformadaException();
        }

        if (filmeRequest.getGeneros() == null) {
            throw new GeneroNaoInformadoException();
            // ("Deve ser informado pelo menos um gênero para o cadastro do filme.")
        }

        if (filmeRequest.getResumo() == null) {
            throw new ResumoNaoInformadoException();
        }

        final Set<Genero> generoSet = new HashSet<>();

        for (Genero genero : filmeRequest.getGeneros()) {
            if (generoSet.contains(genero)) {
                throw new UniversalUseException("Não é permitido informar o mesmo gênero mais de uma vez para o mesmo filme.");
            } else {
                generoSet.add(genero);
            }
        }
        final Integer idGerado = filmesCadastrados.size() + 1;

        final Filme filme = new Filme(
                filmeRequest.getNome(),
                filmeRequest.getAnoLancamento(),
                filmeRequest.getCapaFilme(),
                filmeRequest.getGeneros(),
                estudioService.consultarEstudio(filmeRequest.getIdEstudio()),
                diretorService.consultarDiretor(filmeRequest.getIdDiretor()),
                personagemAtorService.cadastrarPersonagensFilme(filmeRequest.getPersonagens()),
                filmeRequest.getResumo());

        filmeRepository.save(filme);
    }


    // Lists all the movies in the DB
    public List<Filme> consultarFilmes(String nomeFilme, String nomeDiretor, String nomeAtor, String nomePersonagem) throws Exception {
        final List<Filme> filmesCadastrados = filmeRepository.findAll();

        if (filmesCadastrados.isEmpty()) {
            throw new ListaVaziaException(TipoDominioException.FILME.getSingular(), TipoDominioException.FILME.getPlural());
        }

        // Additional filters:
        final List<Filme> filterByNomePersonagem = filterByNomePersonagem(filmesCadastrados, nomePersonagem);
        final List<Filme> filterByNomeAtor = filterByNomeAtor(filterByNomePersonagem, nomeAtor);
        final List<Filme> filterByNomeDiretor = filterByNomeDiretor(filterByNomeAtor, nomeDiretor);
        final List<Filme> finalFilter = filterByNomeFilme(filterByNomeDiretor, nomeFilme);

        if (finalFilter.isEmpty()) {
            throw new UniversalUseException(
                    String.format(
                            "Filme não encontrado com os filtros nomeFilme=%s, nomeDiretor=%s, nomePersonagem=%s, nomeAtor=%s, favor informar outros filtros.", nomeFilme, nomeDiretor, nomePersonagem, nomeAtor)
            );
        }

        return finalFilter;
    }

    private List<Filme> filterByNomeFilme(final List<Filme> listaOriginal, final String nome) {
        if (nome == null) {
            return listaOriginal;
        }

        final List<Filme> filmeFiltrado = new ArrayList<>();

        for (Filme filme : listaOriginal) {
            if (filme.getNome().toLowerCase(Locale.ROOT).equalsIgnoreCase(nome.toLowerCase(Locale.ROOT))) {
                filmeFiltrado.add(filme);
            }
        }

        return filmeFiltrado;
    }

    private List<Filme> filterByNomeDiretor(final List<Filme> listaOriginal, final String nome) {
        if (nome == null) {
            return listaOriginal;
        }

        final List<Filme> filmeFiltrado = new ArrayList<>();

        for (Filme filme : listaOriginal) {
            if (filme.getDiretor().getNome().toLowerCase(Locale.ROOT).equalsIgnoreCase(nome.toLowerCase(Locale.ROOT))) {
                filmeFiltrado.add(filme);
            }
        }

        return filmeFiltrado;
    }

    private List<Filme> filterByNomeAtor(final List<Filme> listaOriginal, final String nome) {
        if (nome == null) {
            return listaOriginal;
        }

        final List<Filme> filmeFiltrado = new ArrayList<>();

        for (Filme filme : listaOriginal) {
            for (PersonagemAtor personagens : filme.getPersonagens()) {
                if (personagens.getAtor().getNome().toLowerCase(Locale.ROOT).equalsIgnoreCase(nome.toLowerCase(Locale.ROOT))) {
                    filmeFiltrado.add(filme);
                    break;
                }
            }
        }

        return filmeFiltrado;
    }

    private List<Filme> filterByNomePersonagem(final List<Filme> listaOriginal, final String nome) {
        if (nome == null) {
            return listaOriginal;
        }

        final List<Filme> filmeFiltrado = new ArrayList<>();

        for (Filme filme : listaOriginal) {
            for (PersonagemAtor personagens : filme.getPersonagens()) {
                if (personagens.getNomePersonagem().toLowerCase(Locale.ROOT).equalsIgnoreCase(nome.toLowerCase(Locale.ROOT))) {
                    filmeFiltrado.add(filme);
                    break;
                }
            }
        }

        return filmeFiltrado;
    }
}