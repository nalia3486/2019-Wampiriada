package pl.edu.wampa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.wampa.domain.Users;

public interface LoginRepo extends JpaRepository<Users, Long> {
    Users findByLogin(String login);
}
