package com.softdesign.avaliacao_tecnica.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import com.softdesign.avaliacao_tecnica.dataProvider.SessaoVotacaoDataProvider;
import com.softdesign.avaliacao_tecnica.dataProvider.VotoDataProvider;
import com.softdesign.avaliacao_tecnica.exception.AssociadoRealizouVotoException;
import com.softdesign.avaliacao_tecnica.exception.SessaoVotacaoInvalidaException;
import com.softdesign.avaliacao_tecnica.model.SessaoVotacao;
import com.softdesign.avaliacao_tecnica.model.Voto;
import com.softdesign.avaliacao_tecnica.repository.SessaoVotacaoRepository;
import com.softdesign.avaliacao_tecnica.repository.VotoRepository;

@SpringBootTest
public class VotoServiceTest {

    @Mock
    private VotoRepository mockedVotoRepository;

    @Mock
    private SessaoVotacaoRepository mockedSessaoVotacaoRepository;

    @InjectMocks
    private VotoService mockedVotoService;

    @Test
    @DisplayName("Listar Votos")
    public void testListarVotos() {
        // arrange
        final List<Voto> votos = VotoDataProvider.makeListVotos();

        when(mockedVotoRepository.findAll()).thenReturn(votos);

        // ack
        List<Voto> retorno = mockedVotoService.listarVotos();

        // assert
        assertNotNull(retorno);
        assertEquals(2, retorno.size());
        verify(mockedVotoRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Registrar Voto")
    public void testRegistrarVoto() {
        // arrange
        final SessaoVotacao sessao = SessaoVotacaoDataProvider.makSessaoVotacao();
        final Voto voto = VotoDataProvider.makeVoto();

        when(mockedSessaoVotacaoRepository.findById(any())).thenReturn(Optional.of(sessao));
        when(mockedVotoRepository.existsByAssociadoIdAndSessaoId(any(), any())).thenReturn(false);
        when(mockedVotoRepository.save(any(Voto.class))).thenReturn(voto);

        // ack
        Voto votoSalvo = mockedVotoService.registrarVoto(1L, 1L, Boolean.TRUE);

        // assert
        assertNotNull(votoSalvo);
        assertEquals(1L, votoSalvo.getAssociadoId());
        assertTrue(votoSalvo.getVoto());
        verify(mockedVotoRepository, times(1)).save(any(Voto.class));
    }

    @Test
    @DisplayName("Registrar Voto com sessão encerrada")
    public void testRegistrarVotoException() {
        // Arrange
        SessaoVotacao sessao = SessaoVotacaoDataProvider.makSessaoVotacao();
        sessao.setDataFechamento(LocalDateTime.now().minusMinutes(10)); // Sessão já fechada
        when(mockedSessaoVotacaoRepository.findById(any())).thenReturn(Optional.of(sessao));

        // Act
        SessaoVotacaoInvalidaException exception = assertThrows(SessaoVotacaoInvalidaException.class,
            () -> mockedVotoService.registrarVoto(1L, 1L, Boolean.TRUE)
        );

        //Assert
        assertEquals("Sessão inválida ou encerrada", exception.getMessage());
        verify(mockedVotoRepository, never()).save(any(Voto.class));
    }

    @Test
    @DisplayName("Registrar Voto quando o Associado já votou")
    public void testRegistrarVotoException2() {
        // Arrange
        SessaoVotacao sessao = SessaoVotacaoDataProvider.makSessaoVotacao();
        when(mockedSessaoVotacaoRepository.findById(1L)).thenReturn(Optional.of(sessao));
        when(mockedVotoRepository.existsByAssociadoIdAndSessaoId(any(), any())).thenReturn(true);

        // Act
        AssociadoRealizouVotoException exception = assertThrows(AssociadoRealizouVotoException.class,
            () -> mockedVotoService.registrarVoto(1L, 1L, Boolean.TRUE)
        );

        //Assert
        assertEquals("O associado já votou nesta sessão.", exception.getMessage());
        verify(mockedVotoRepository, never()).save(any(Voto.class));
    }
}
