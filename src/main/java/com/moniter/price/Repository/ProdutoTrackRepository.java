package com.moniter.price.Repository;

import com.moniter.price.domain.Entity.ProdutoTrack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoTrackRepository extends JpaRepository<ProdutoTrack, Long> {
}
