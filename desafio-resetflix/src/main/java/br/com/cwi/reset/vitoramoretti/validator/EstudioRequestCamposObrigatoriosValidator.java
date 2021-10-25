package br.com.cwi.reset.vitoramoretti.validator;

import br.com.cwi.reset.vitoramoretti.exception.DataCriacaoNaoInformadaException;
import br.com.cwi.reset.vitoramoretti.exception.DescricaoNaoInformadaException;
import br.com.cwi.reset.vitoramoretti.exception.NomeNaoInformadoException;
import br.com.cwi.reset.vitoramoretti.exception.StatusAtividadeNaoInformadoException;
import br.com.cwi.reset.vitoramoretti.request.EstudioRequest;

public class EstudioRequestCamposObrigatoriosValidator {

    public void accept(EstudioRequest estudioRequest) throws Exception {
        if (estudioRequest.getNome() == null) {
            throw new NomeNaoInformadoException();
        }

        if (estudioRequest.getDataCriacao() == null) {
            throw new DataCriacaoNaoInformadaException();
        }

        if (estudioRequest.getDescricao() == null) {
            throw new DescricaoNaoInformadaException();
        }

        if (estudioRequest.getStatusAtividade() == null) {
            throw new StatusAtividadeNaoInformadoException();
        }
    }
}
