package com.softdesign.avaliacao_tecnica.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import com.softdesign.avaliacao_tecnica.dataProvider.VotoDataProvider;
import com.softdesign.avaliacao_tecnica.exception.SessaoVotacaoInvalidaException;
import com.softdesign.avaliacao_tecnica.model.Voto;
import com.softdesign.avaliacao_tecnica.service.VotoService;

@SpringBootTest
public class VotoControllerTest {

    @Mock
    private VotoService mockedVotoService;

    @InjectMocks
    private VotoController mockedVotoController;
    
    @Test
    @DisplayName("Listar Votos")
    public void testListarVotos() {
        // arrange
        List<Voto> votos = VotoDataProvider.makeListVotos();

        // ack
        when(mockedVotoService.listarVotos()).thenReturn(votos);
        List<Voto> retorno = mockedVotoService.listarVotos();

        // assert
        assertEquals(2, retorno.size());
        assertEquals(1L, retorno.get(0).getId());
        assertEquals(Boolean.TRUE, retorno.get(0).getVoto());

        verify(mockedVotoService, times(1)).listarVotos();
    }

    @Test
    @DisplayName("Registrar Voto")
    public void testRegistrarVoto() {
        // arrange
        Voto voto = VotoDataProvider.makeVoto();
        when(mockedVotoService.registrarVoto(1L, 1L, true)).thenReturn(voto);
       
        // ack
        Voto retorno = mockedVotoService.registrarVoto(1L, 1L, Boolean.TRUE);

        // assert
        assertEquals(1L, retorno.getId());
        assertEquals(Boolean.TRUE, retorno.getVoto());
        verify(mockedVotoService, times(1)).registrarVoto(1L, 1L, Boolean.TRUE);
    }

    @Test
    @DisplayName("Registrar Voto quando sessao for inválida")
    public void testRegistrarVotoException() {
        when(mockedVotoService.registrarVoto(any(), any(), anyBoolean()))
            .thenThrow(new SessaoVotacaoInvalidaException("Sessão inválida!"));

        verify(mockedVotoService, times(0)).registrarVoto(any(), any(), anyBoolean());
    }

    @Test
    @DisplayName("Registrar Voto quando erro inesperado")
    public void testRegistrarVotoException2() {
        when(mockedVotoService.registrarVoto(any(), any(), anyBoolean()))
            .thenThrow(new RuntimeException("Erro inesperado"));

        verify(mockedVotoService, times(0)).registrarVoto(any(), any(), anyBoolean());
    }
}
