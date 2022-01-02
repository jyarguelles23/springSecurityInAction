package com.viqsystems.chaptertenparttwo.Repository;

import com.viqsystems.chaptertenparttwo.Entities.Token;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaTokenRepository  extends JpaRepository<Token,Integer> {
    Optional<Token> findTokenByIdentifier(String identifier);
}
