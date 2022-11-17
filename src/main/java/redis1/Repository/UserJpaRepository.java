package redis1.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import redis1.Entitie.JPA.UserJpa;

@Repository
public interface UserJpaRepository extends JpaRepository<UserJpa, Long> {
}
