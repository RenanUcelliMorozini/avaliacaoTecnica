package com.softdesign.avaliacao_tecnica.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import com.softdesign.avaliacao_tecnica.dataProvider.SessaoVotacaoDataProvider;
import com.softdesign.avaliacao_tecnica.domain.DominioTipoCampo;
import com.softdesign.avaliacao_tecnica.exception.PautaNaoEncontradaException;
import com.softdesign.avaliacao_tecnica.model.SessaoVotacao;
import com.softdesign.avaliacao_tecnica.service.SessaoVotacaoService;

@SpringBootTest
public class SessaoVotacaoControllerTest {

    @Mock
    private SessaoVotacaoService mockedSessaoVotacaoService;

    @InjectMocks
    private SessaoVotacaoController mockedSessaoVotacaoController;

    @Test
    @DisplayName("Retornar lista com 2 itens")
    public void testListarVotacao() {
        
        // arrange
        when(mockedSessaoVotacaoService.listarVotacao()).thenReturn(SessaoVotacaoDataProvider.makListSessaoVotacao());

        //act
        List<SessaoVotacao> lista = mockedSessaoVotacaoService.listarVotacao();

        // assert
        assertEquals(2, lista.size());
        assertEquals("Titulo1", lista.get(0).getTitulo());
        assertEquals("Tipo1", lista.get(0).getTipo());
        verify(mockedSessaoVotacaoService, times(1)).listarVotacao();
    }

    @Test
    @DisplayName("Deve abrir sessao com sucesso")
    public void testAbrirSessao() {
        // arrange
        final SessaoVotacao sessao = SessaoVotacaoDataProvider.makSessaoVotacao();

        //act
        when(mockedSessaoVotacaoService.abrirSessao(10L, DominioTipoCampo.TEXTO, 1L, "Teste", null, null))
                .thenReturn(sessao);

        SessaoVotacao retorno = mockedSessaoVotacaoService.abrirSessao(10L, DominioTipoCampo.TEXTO, 1L, "Teste", null, null);

        // assert
        assertEquals("Titulo1", retorno.getTitulo());
        assertEquals("Tipo1", retorno.getTipo());
        verify(mockedSessaoVotacaoService, times(1)).abrirSessao(10L, DominioTipoCampo.TEXTO, 1L, "Teste", null, null);
    }

    @Test
    @DisplayName("Deve retornar erro quando pauta nao encontrada")
    public void testAbrirSessaoException() {
        when(mockedSessaoVotacaoService.abrirSessao(anyLong(), any(), anyLong(), any(), any(), any()))
                .thenThrow(new PautaNaoEncontradaException("Pauta não encontrada"));
    }

    @Test
    @DisplayName("Deve retornar erro interno para excecoes nao tratadas")
    public void testAbrirSessaoException2() {
        when(mockedSessaoVotacaoService.abrirSessao(anyLong(), any(), anyLong(), any(), any(), any()))
                .thenThrow(new RuntimeException("Erro inesperado"));
    }

}
