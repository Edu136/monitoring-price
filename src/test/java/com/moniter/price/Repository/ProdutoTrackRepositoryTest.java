package com.moniter.price.Repository;

import com.moniter.price.domain.Entity.ProdutoTrack;
import com.moniter.price.domain.vo.Email;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;

import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ProdutoTrackRepositoryTest {

    @Autowired
    private ProdutoTrackRepository repository;

    @Test
    void shouldSaveAndFindProduct() {
        Email email = new Email("teste@local.com");
        ProdutoTrack newTrack = ProdutoTrack.create("http://kabum.com.br", 1000.0, email);

        ProdutoTrack savedTrack = repository.save(newTrack);

        assertThat(savedTrack.getId()).isNotNull();

        Optional<ProdutoTrack> found = repository.findById(savedTrack.getId());
        assertThat(found).isPresent();
        assertThat(found.get().getUserEmail().getAddress()).isEqualTo("teste@local.com");
    }
}