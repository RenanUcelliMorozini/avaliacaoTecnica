package com.softdesign.avaliacao_tecnica.dataProvider;

import java.util.ArrayList;
import java.util.List;
import com.softdesign.avaliacao_tecnica.model.Pauta;

public class PautaDataProvider {

    public static Pauta makePauta() {
        return makeListaPauta().get(0);
    }

    public static List<Pauta> makeListaPauta() {
        final List<Pauta> lista = new ArrayList<>();
        
        final Pauta pauta1 = Pauta.builder()
            .id(1L)
            .tipo("Tipo1")
            .titulo("Titulo1")
            .build();
        lista.add(pauta1);

        final Pauta pauta2 = Pauta.builder()
            .id(2L)
            .tipo("Tipo2")
            .titulo("Titulo2")
            .build();

        lista.add(pauta2);
        return lista;
    }
}
