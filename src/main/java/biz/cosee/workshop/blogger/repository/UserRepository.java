package biz.cosee.workshop.blogger.repository;

import biz.cosee.workshop.blogger.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
