package br.com.cwi.reset.vitoramoretti.service;

import br.com.cwi.reset.vitoramoretti.exception.*;
import br.com.cwi.reset.vitoramoretti.model.Estudio;
import br.com.cwi.reset.vitoramoretti.repository.EstudioRepository;
import br.com.cwi.reset.vitoramoretti.request.EstudioRequest;
import br.com.cwi.reset.vitoramoretti.validator.EstudioRequestCamposObrigatoriosValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


@Service
public class EstudioService {

    private EstudioRepository estudioRepository;

    @Autowired
    public EstudioService(final EstudioRepository estudioRepository) {
        this.estudioRepository = estudioRepository;
    }

    public void criarEstudio(EstudioRequest estudioRequest) throws Exception {
        new EstudioRequestCamposObrigatoriosValidator().accept(estudioRequest);

        final List<Estudio> estudiosCadastrados = estudioRepository.findAll();

        for (Estudio estudioCadastrado : estudiosCadastrados) {
            if (estudioCadastrado.getNome().equalsIgnoreCase(estudioRequest.getNome())) {
                throw new CadastroDuplicadoException(TipoDominioException.ESTUDIO.getSingular(), estudioRequest.getNome());
            }
        }
        if (LocalDate.now().isBefore(estudioRequest.getDataCriacao())) {
            throw new UniversalUseException("Não é possível cadastrar estúdios do futuro.");
        }


        final Estudio estudio = new Estudio(estudioRequest.getNome(), estudioRequest.getDescricao(), estudioRequest.getDataCriacao(), estudioRequest.getStatusAtividade());

        estudioRepository.save(estudio);
    }

    public List<Estudio> consultarEstudios(String filtroNome) throws Exception {
        final List<Estudio> estudiosCadastrados = estudioRepository.findAll();
        final List<Estudio> estudios = new ArrayList<>();

        if (estudiosCadastrados.isEmpty()) {
            throw new ListaVaziaException(TipoDominioException.ESTUDIO.getSingular(), TipoDominioException.ESTUDIO.getPlural());
        }

        if (filtroNome != null) {
            for (Estudio estudio : estudiosCadastrados) {
                if (estudio.getNome().toLowerCase(Locale.ROOT).contains(filtroNome.toLowerCase(Locale.ROOT))) {
                    estudios.add(estudio);
                }
            }
        } else {
            estudios.addAll(estudiosCadastrados);
        }

        if (estudios.isEmpty()) {
            throw new FiltroNomeNaoEncontrado("Estúdio", filtroNome);
        }
        return estudios;
    }

    public Estudio consultarEstudio(Integer id) throws Exception {

        if (id == null) {
            throw new IdNaoInformado();
        }

        final List<Estudio> estudios = estudioRepository.findAll();

        for (Estudio estudio : estudios) {
            if (estudio.getId().equals(id)) {
                return estudio;
            }
        }

        throw new ConsultaIdInvalidoException(TipoDominioException.ESTUDIO.getSingular(), id);
    }

//        List<Estudio> estudios = estudioRepository.findAll();
//
//        if (id != null) {
//            throw new IdNaoInformado();
//        }
//        return estudios.stream().filter(e -> e.getId().equals(id)).findFirst();
//        return fakeDatabase.recuperaEstudios().stream().filter(e -> e.getId().equals(id)).findFirst().orElseThrow(() -> new ConsultaIdInvalidoException(TipoDominioException.ESTUDIO.getSingular(), id));
//    }
}
