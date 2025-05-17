package com.fakestoreInteract.Ecommerce.Repositories;

import com.fakestoreInteract.Ecommerce.models.UserToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserTokenReporsitory extends JpaRepository<UserToken,Long> {
    Optional<UserToken> findByUserId(Long id);

    Optional<UserToken> findByTokenAndIsDeleted(String token, boolean value);

    Optional<UserToken> findByUserIdAndIsDeleted(Long id, boolean b);
}
