package br.com.cwi.reset.vitoramoretti.service;

import br.com.cwi.reset.vitoramoretti.FakeDatabase;
import br.com.cwi.reset.vitoramoretti.exception.*;
import br.com.cwi.reset.vitoramoretti.model.Filme;
import br.com.cwi.reset.vitoramoretti.model.PersonagemAtor;
import br.com.cwi.reset.vitoramoretti.request.FilmeRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class FilmeService {

    private FakeDatabase fakeDatabase;
    private EstudioService estudioService;
    private DiretorService diretorService;
    private PersonagemAtorService personagemAtorService;

    public FilmeService(FakeDatabase fakeDatabase) {
        this.fakeDatabase = fakeDatabase;
        this.estudioService = new EstudioService(fakeDatabase);
        this.diretorService = new DiretorService(fakeDatabase);
        this.personagemAtorService = new PersonagemAtorService(fakeDatabase);
    }

    // Registers a new movie in the DB
    public void criarFilme(FilmeRequest filmeRequest) throws Exception {
        final List<Filme> savedFilmes = fakeDatabase.recuperaFilmes();
        final Integer generatedId = savedFilmes.size() + 1;

        final Filme filme = new Filme(
                generatedId,
                filmeRequest.getNome(),
                filmeRequest.getAnoLancamento(),
                filmeRequest.getCapaFilme(),
                filmeRequest.getGeneros(),
                diretorService.consultarDiretor(filmeRequest.getIdDiretor()),
                estudioService.consultarEstudio(filmeRequest.getIdEstudio()),
                personagemAtorService.createPersonagensFilme(filmeRequest.getPersonagens()),
                filmeRequest.getResumo()
        );

        if (filmeRequest.getNome() == null) {
            throw new NomeNaoInformadoException();
        }

        if (filmeRequest.getAnoLancamento() == null) {
            throw new AnoLancamentoNaoInformadoException();
        }

        if (filmeRequest.getCapaFilme() == null) {
            throw new CapaFilmeNaoInformadaException();
        }

        if (filmeRequest.getGeneros().isEmpty()) {
            throw new UniversalUseException("Deve ser informado pelo menos um gênero para o cadastro do filme.");
        }

        if (filmeRequest.getResumo() == null) {
            throw new ResumoNaoInformadoException();
        }

        // Implement here -> logic for not repeating movie category

        fakeDatabase.persisteFilme(filme);
    }

    // Lists all the movies in the DB
    public List<Filme> consultarFilmes(String nomeFilme, String nomeDiretor, String nomeAtor, String nomePersonagem) throws Exception {
        final List<Filme> savedFilmes = fakeDatabase.recuperaFilmes();

        if (savedFilmes.isEmpty()) {
            throw new ListaVaziaException(TipoDominioException.FILME.getSingular(), TipoDominioException.FILME.getPlural());
        }

        // Additional filters:
        final List<Filme> filterByNomePersonagem = filterByNomePersonagem(savedFilmes, nomePersonagem);
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