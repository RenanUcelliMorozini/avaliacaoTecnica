package com.softdesign.avaliacao_tecnica.dataProvider;

import java.util.ArrayList;
import java.util.List;
import com.softdesign.avaliacao_tecnica.model.SessaoVotacao;
import com.softdesign.avaliacao_tecnica.model.Voto;

public class VotoDataProvider {

    public static Voto makeVoto() {
        return makeListVotos().get(0);
    }

    public static List<Voto> makeListVotos() {
        final List<Voto> lista = new ArrayList<>();
       
        final Voto voto = Voto.builder()
            .id(1L)
            .associadoId(1L)
            .sessao(new SessaoVotacao())
            .voto(Boolean.TRUE)
            .build();
        lista.add(voto);
        
        final Voto voto2 = Voto.builder()
            .id(2L)
            .associadoId(2L)
            .sessao(new SessaoVotacao())
            .voto(Boolean.FALSE)
            .build();
        lista.add(voto2);
        
        return lista;
    }
}
