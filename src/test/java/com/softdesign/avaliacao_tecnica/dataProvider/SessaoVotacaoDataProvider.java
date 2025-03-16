package com.softdesign.avaliacao_tecnica.dataProvider;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import com.softdesign.avaliacao_tecnica.model.Pauta;
import com.softdesign.avaliacao_tecnica.model.SessaoVotacao;

public class SessaoVotacaoDataProvider {
    public static SessaoVotacao makSessaoVotacao() {
        return makListSessaoVotacao().get(0);
    }

    public static List<SessaoVotacao> makListSessaoVotacao() {
        final List<SessaoVotacao> lista = new ArrayList<>();
        SessaoVotacao sessao1 = SessaoVotacao.builder()
            .id(1L)
            .dataAbertura(LocalDateTime.now())
            .tipo("Tipo1")
            .titulo("Titulo1")
            .pauta(new Pauta())
            .dataFechamento(LocalDateTime.now().plusMinutes(2L))
            .build();

        lista.add(sessao1);
        SessaoVotacao sessao2 = SessaoVotacao.builder()
            .id(1L)
            .dataAbertura(LocalDateTime.now())
            .tipo("Tipo2")
            .titulo("Titulo2")
            .pauta(new Pauta())
            .dataFechamento(LocalDateTime.now().plusMinutes(3L))
            .build();
        lista.add(sessao2);
        return lista;
    }
}
