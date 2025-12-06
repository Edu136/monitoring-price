package com.moniter.price.Entity;

import com.moniter.price.domain.Entity.ProdutoTrack;
import com.moniter.price.domain.vo.Email;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ProductTrackTest {

    @Test
    void shouldCreateActiveProductWithValidData() {
        Email email = new Email("usuario@teste.com");

        ProdutoTrack produto = ProdutoTrack.create("http://url.com", 2500.00, email);

        assertNotNull(produto);
        assertNull(produto.getId());

        assertEquals(2500.00, produto.getTargetPrice());

        assertEquals("usuario@teste.com", produto.getUserEmail().getAddress());

        assertTrue(produto.getActive(), "O produto deve nascer Ativo por padrão");
        assertNull(produto.getLastCheckedPrice(), "Não deve ter preço verificado ao nascer");
    }

    @Test
    void shouldThrowExceptionWhenEmailIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            ProdutoTrack.create("http://url.com", 2500.00, null);
        });
    }

    @Test
    void shouldUpdatePriceAndDate() {
        Email email = new Email("usuario@teste.com");
        ProdutoTrack produto = ProdutoTrack.create("http://url.com", 2500.00, email);

        produto.updateLastPrice(2400.00);

        assertEquals(2400.00, produto.getLastCheckedPrice());
        assertNotNull(produto.getLastCheckedDate(), "A data de verificação deve ser atualizada");
    }
}