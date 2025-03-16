package com.softdesign.avaliacao_tecnica.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import com.softdesign.avaliacao_tecnica.dataProvider.PautaDataProvider;
import com.softdesign.avaliacao_tecnica.dataProvider.SessaoVotacaoDataProvider;
import com.softdesign.avaliacao_tecnica.domain.DominioTipoCampo;
import com.softdesign.avaliacao_tecnica.exception.PautaNaoEncontradaException;
import com.softdesign.avaliacao_tecnica.model.Pauta;
import com.softdesign.avaliacao_tecnica.model.SessaoVotacao;
import com.softdesign.avaliacao_tecnica.repository.PautaRepository;
import com.softdesign.avaliacao_tecnica.repository.SessaoVotacaoRepository;

@SpringBootTest
public class SessaoVotacaoServiceTest {

    @Mock
    private SessaoVotacaoRepository mockedSessaoVotacaoRepository;

    @Mock
    private PautaRepository mockedPautaRepository;

    @InjectMocks
    private SessaoVotacaoService mockedSessaoVotacaoService;

    @Test
    @DisplayName("Abrir sessao com sucesso")
    public void testAbrirSessao() {

        // Arrange
        final Pauta pauta = PautaDataProvider.makePauta();
        final SessaoVotacao sessao = SessaoVotacaoDataProvider.makSessaoVotacao();

        when(mockedPautaRepository.findById(1L)).thenReturn(Optional.of(pauta));
        when(mockedSessaoVotacaoRepository.save(any(SessaoVotacao.class))).thenReturn(sessao);

        // Act
        SessaoVotacao resultado = mockedSessaoVotacaoService.abrirSessao(1L, DominioTipoCampo.TEXTO, 1L, "Texto", null, null);

        // Assert
        assertNotNull(resultado);
        assertEquals("Tipo1", resultado.getTipo());

        verify(mockedPautaRepository, times(1)).findById(1L);
        verify(mockedSessaoVotacaoRepository, times(1)).save(any(SessaoVotacao.class));
    }

    @Test
    @DisplayName("Abrir sessao com pauta nãoo existente")
    public void testAbrirSessaoException() {
        // Arrange
        when(mockedPautaRepository.findById(1L)).thenReturn(Optional.empty());

        // Act
        Exception exception = assertThrows(PautaNaoEncontradaException.class, () -> {
            mockedSessaoVotacaoService.abrirSessao(1L, DominioTipoCampo.TEXTO, 30L, "Texto", null, null);
        });

        //Assert
        assertEquals("Pauta não encontrada com ID: 1", exception.getMessage());
        verify(mockedPautaRepository, times(1)).findById(1L);
        verify(mockedSessaoVotacaoRepository, never()).save(any());
    }

    @Test
    @DisplayName("Listar Votacao")
    public void testListarVotacao() {
        // Arrange
        final SessaoVotacao sessao = SessaoVotacaoDataProvider.makSessaoVotacao();
        List<SessaoVotacao> sessoesMock = Arrays.asList(sessao);
        when(mockedSessaoVotacaoRepository.findAll()).thenReturn(sessoesMock);

        // Act
        List<SessaoVotacao> resultado = mockedSessaoVotacaoService.listarVotacao();

        // Assert
        assertEquals(1, resultado.size());
        assertEquals("Titulo1", resultado.get(0).getTitulo());

        verify(mockedSessaoVotacaoRepository, times(1)).findAll();
    }
}
