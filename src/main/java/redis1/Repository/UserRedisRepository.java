package redis1.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import redis1.Entitie.Redis.UserRedis;

@Repository
@EnableJpaRepositories
public interface UserRedisRepository extends JpaRepository<UserRedis, String> {
}
