package br.com.cwi.reset.vitoramoretti.validator;

import br.com.cwi.reset.vitoramoretti.exception.DescricaoPersonagemNaoInformadaException;
import br.com.cwi.reset.vitoramoretti.exception.IdAtorNaoInformadoException;
import br.com.cwi.reset.vitoramoretti.exception.NomeNaoInformadoException;
import br.com.cwi.reset.vitoramoretti.exception.TipoAtuacaoNaoInformadoException;
import br.com.cwi.reset.vitoramoretti.request.PersonagemRequest;

public class PersonagemRequestCamposObrigatoriosValidator {

    public void accept(final PersonagemRequest personagemRequest) throws Exception {
        if (personagemRequest.getNomePersonagem() == null) {
            throw new NomeNaoInformadoException();
        }

        if (personagemRequest.getDescricaoPersonagem() == null) {
            throw new DescricaoPersonagemNaoInformadaException();
        }

        if (personagemRequest.getIdAtor() == null) {
            throw new IdAtorNaoInformadoException();
        }

        if (personagemRequest.getTipoAtuacao() == null) {
            throw new TipoAtuacaoNaoInformadoException();
        }
    }
}