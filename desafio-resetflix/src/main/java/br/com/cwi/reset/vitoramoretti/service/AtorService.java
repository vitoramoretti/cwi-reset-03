package br.com.cwi.reset.vitoramoretti.service;

import br.com.cwi.reset.vitoramoretti.FakeDatabase;
import br.com.cwi.reset.vitoramoretti.exception.*;
import br.com.cwi.reset.vitoramoretti.model.Ator;
import br.com.cwi.reset.vitoramoretti.model.StatusCarreira;
import br.com.cwi.reset.vitoramoretti.repository.AtorRepository;
import br.com.cwi.reset.vitoramoretti.request.AtorRequest;
import br.com.cwi.reset.vitoramoretti.response.AtorEmAtividade;
import br.com.cwi.reset.vitoramoretti.validator.BasicInfoRequiredValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class AtorService {

    private AtorRepository atorRepository;

    @Autowired
    public AtorService(AtorRepository atorRepository) {
        this.atorRepository = atorRepository;
    }

    public void criarAtor(AtorRequest atorRequest) throws Exception {
        // Validação básica
        new BasicInfoRequiredValidator().accept(atorRequest.getNome(), atorRequest.getDataNascimento(), atorRequest.getAnoInicioAtividade(), TipoDominioException.ATOR);

        // Verifica se Status Carreira foi infomado
        if (atorRequest.getStatusCarreira() == null) {
            throw new StatusCarreiraNaoInformadoException();
        }

        // Lista de todos os Atores cadastrados no Banco de Dados
        final List<Ator> atoresCadastrados = atorRepository.findAll();

        // Verifica se o Ator já foi cadastrado no Banco de Dados
        for (Ator atorCadastrado : atoresCadastrados) {
            if (atorCadastrado.getNome().equalsIgnoreCase(atorRequest.getNome())) {
                throw new CadastroDuplicadoException(TipoDominioException.ATOR.getSingular(), atorRequest.getNome());
            }
        }

        final Ator ator = new Ator(
                atorRequest.getNome(),
                atorRequest.getDataNascimento(),
                atorRequest.getStatusCarreira(),
                atorRequest.getAnoInicioAtividade());

        atorRepository.save(ator);
    }

    public List<AtorEmAtividade> listarAtoresEmAtividade(String filtroNome) throws Exception {
        final List<Ator> atoresCadastrados = atorRepository.findAll();

        if (atoresCadastrados.isEmpty()) {
            throw new ListaVaziaException(TipoDominioException.ATOR.getSingular(), TipoDominioException.ATOR.getPlural());
        }

        final List<AtorEmAtividade> retorno = new ArrayList<>();

        if (filtroNome != null) {
            for (Ator ator : atoresCadastrados) {
                final boolean containsFilter = ator.getNome().toLowerCase(Locale.ROOT).contains(filtroNome.toLowerCase(Locale.ROOT));
                final boolean emAtividade = StatusCarreira.EM_ATIVIDADE.equals(ator.getStatusCarreira());
                if (containsFilter && emAtividade) {
                    retorno.add(new AtorEmAtividade(ator.getId(), ator.getNome(), ator.getDataNascimento()));
                }
            }
        } else {
            for (Ator ator : atoresCadastrados) {
                final boolean emAtividade = StatusCarreira.EM_ATIVIDADE.equals(ator.getStatusCarreira());
                if (emAtividade) {
                    retorno.add(new AtorEmAtividade(ator.getId(), ator.getNome(), ator.getDataNascimento()));
                }
            }
        }

        if (retorno.isEmpty()) {
            throw new FiltroNomeNaoEncontrado("Ator", filtroNome);
        }

        return retorno;
    }

    public Ator consultarAtor(Integer id) throws Exception {
        if (id == null) {
            throw new IdNaoInformado();
        }

        final List<Ator> atores = atorRepository.findAll();

        for (Ator ator : atores) {
            if (ator.getId().equals(id)) {
                return ator;
            }
        }

        throw new ConsultaIdInvalidoException(TipoDominioException.ATOR.getSingular(), id);
    }

    public List<Ator> consultarAtores() throws Exception {
        final List<Ator> atores = atorRepository.findAll();

        if (atores.isEmpty()) {
            throw new ListaVaziaException(TipoDominioException.ATOR.getSingular(), TipoDominioException.ATOR.getPlural());
        }

        return atores;
    }
}