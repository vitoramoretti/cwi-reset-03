package br.com.cwi.reset.vitoramoretti.service;

import br.com.cwi.reset.vitoramoretti.exception.*;
import br.com.cwi.reset.vitoramoretti.model.Diretor;
import br.com.cwi.reset.vitoramoretti.repository.DiretorRepository;
import br.com.cwi.reset.vitoramoretti.request.DiretorRequest;
import br.com.cwi.reset.vitoramoretti.validator.BasicInfoRequiredValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class DiretorService {

    private DiretorRepository diretorRepository;

    @Autowired
    public DiretorService(DiretorRepository diretorRepository) {
        this.diretorRepository = diretorRepository;
    }

    public void cadastrarDiretor(final DiretorRequest diretorRequest) throws Exception {
        new BasicInfoRequiredValidator().accept(diretorRequest.getNome(), diretorRequest.getDataNascimento(), diretorRequest.getAnoInicioAtividade(), TipoDominioException.DIRETOR);

        final List<Diretor> diretoresCadastrados = diretorRepository.findAll();

        for (Diretor diretorCadastrado : diretoresCadastrados) {
            if (diretorCadastrado.getNome().equalsIgnoreCase(diretorRequest.getNome())) {
                throw new CadastroDuplicadoException(TipoDominioException.DIRETOR.getSingular(), diretorRequest.getNome());
            }
        }


        final Diretor diretor = new Diretor(diretorRequest.getNome(), diretorRequest.getDataNascimento(), diretorRequest.getAnoInicioAtividade());

        diretorRepository.save(diretor);
    }

    public List<Diretor> listarDiretores(final String filterNome) throws Exception {
        final List<Diretor> diretoresCadastrados = diretorRepository.findAll();

        if (diretoresCadastrados.isEmpty()) {
            throw new ListaVaziaException(TipoDominioException.DIRETOR.getSingular(), TipoDominioException.DIRETOR.getPlural());
        }

        final List<Diretor> retorno = new ArrayList<>();

        if (filterNome != null) {
            for (Diretor diretor : diretoresCadastrados) {
                final boolean containsFilter = diretor.getNome().toLowerCase(Locale.ROOT).contains(filterNome.toLowerCase(Locale.ROOT));
                if (containsFilter) {
                    retorno.add(diretor);
                }
            }
        } else {
            retorno.addAll(diretoresCadastrados);
        }

        if (retorno.isEmpty()) {
            throw new FiltroNomeNaoEncontrado("Diretor", filterNome);
        }

        return retorno;
    }

    public Diretor consultarDiretor(final Integer id) throws Exception {
        if (id == null) {
            throw new IdNaoInformado();
        }

        final List<Diretor> diretores = diretorRepository.findAll();

        for (Diretor diretor : diretores) {
            if (diretor.getId().equals(id)) {
                return diretor;
            }
        }

        throw new ConsultaIdInvalidoException(TipoDominioException.DIRETOR.getSingular(), id);
    }

}