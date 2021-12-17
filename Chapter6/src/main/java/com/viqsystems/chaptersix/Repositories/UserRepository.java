package com.viqsystems.chaptersix.Repositories;

import com.viqsystems.chaptersix.Entities.UserDef;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserDef,Integer> {

    Optional<UserDef> findUserDefByUsername(String user);
}
