package com.softdesign.avaliacao_tecnica.controller;

import com.softdesign.avaliacao_tecnica.dataProvider.PautaDataProvider;
import com.softdesign.avaliacao_tecnica.dto.PautaDTO;
import com.softdesign.avaliacao_tecnica.model.Pauta;
import com.softdesign.avaliacao_tecnica.service.PautaService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.List;

@SpringBootTest
public class PautaControllerTest {

    @Mock
    private PautaService mockedPautaService;

    @InjectMocks
    private PautaController mockedPautaController;

    @Test
    @DisplayName("Retornar lista de Pautas")
    public void testListarPautas() {
        // arrange
        final List<Pauta> pautas =PautaDataProvider.makeListaPauta();

        // ack
        when(mockedPautaService.listarPautas()).thenReturn(pautas);
        List<PautaDTO> resultado = mockedPautaController.listarPautas();

        // assert
        assertEquals(2, resultado.size());
        assertEquals("Titulo1", resultado.get(0).getTitulo());
        assertEquals("Tipo1", resultado.get(0).getTipo());

        verify(mockedPautaService, times(1)).listarPautas();
    }

    @Test
    @DisplayName("Criar Pauta")
    public void testCriarPauta() {
        // arrange
        final Pauta pauta = PautaDataProvider.makePauta();

        // ack
        when(mockedPautaService.criarPauta(any(), any())).thenReturn(pauta);
        Pauta resultado = mockedPautaService.criarPauta(any(), any());

        // assert
        assertNotNull(resultado);
        verify(mockedPautaService, times(1)).criarPauta(any(), any());
    }
}
