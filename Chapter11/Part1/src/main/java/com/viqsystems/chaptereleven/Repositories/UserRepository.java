package com.viqsystems.chaptereleven.Repositories;

import com.viqsystems.chaptereleven.Entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

public interface UserRepository  extends JpaRepository<Users,String> {
        Optional<Users> findUsersByUsername(String username);
}
