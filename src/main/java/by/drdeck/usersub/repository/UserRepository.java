package by.drdeck.usersub.repository;

import by.drdeck.usersub.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
