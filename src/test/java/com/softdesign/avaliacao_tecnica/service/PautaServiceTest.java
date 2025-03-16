package com.softdesign.avaliacao_tecnica.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import com.softdesign.avaliacao_tecnica.dataProvider.PautaDataProvider;
import com.softdesign.avaliacao_tecnica.model.Pauta;
import com.softdesign.avaliacao_tecnica.repository.PautaRepository;

@SpringBootTest
public class PautaServiceTest {

    @Mock
    private PautaRepository mockedPautaRepository;

    @InjectMocks
    private PautaService mockedPautaService; 

    @Test
    @DisplayName("Criar Pauta")
    public void testCriarPauta() {
        // Arrange
        Pauta pauta = PautaDataProvider.makePauta();
        when(mockedPautaRepository.save(any(Pauta.class))).thenReturn(pauta);

        // Act
        Pauta resultado = mockedPautaService.criarPauta("Tipo", "Titulo");

        // Assert
        assertNotNull(resultado);
        assertEquals("Tipo", resultado.getTipo());
        assertEquals("Titulo", resultado.getTitulo());

        // Verifica se o método save foi chamado 1 vez
        verify(mockedPautaRepository, times(1)).save(any(Pauta.class));
    }

    @Test
    @DisplayName("Listar Pautas")
    public void testListarPautas() {
        // Arrange
        List<Pauta> pautasMock = PautaDataProvider.makeListaPauta();
        when(mockedPautaRepository.findAll()).thenReturn(pautasMock);

        // Act
        List<Pauta> resultado = mockedPautaService.listarPautas();

        // Assert
        assertEquals(2, resultado.size());
        assertEquals("Tipo1", resultado.get(0).getTipo());
        assertEquals("Tipo2", resultado.get(1).getTipo());

        // Verifica se o método findAll foi chamado 1 vez
        verify(mockedPautaRepository, times(1)).findAll();
    }
}
