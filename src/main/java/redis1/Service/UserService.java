package redis1.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis1.Entitie.JPA.UserJpa;
import redis1.Entitie.Redis.UserRedis;
import redis1.Repository.UserJpaRepository;
import redis1.Repository.UserRedisRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserJpaRepository userJpaRepository;

    @Autowired
    UserRedisRepository userRedisRepository;

    public List<UserJpa> get() {
        return userJpaRepository.findAll();
    }

    public void post(UserJpa userJPA) {
        userJpaRepository.saveAndFlush(userJPA);
    }

    public void update(String id, UserJpa userJPA) {
        userJPA.setId(id);
        Optional<UserRedis> userRedis = userRedisRepository.findById(id);
        if(userRedis.isPresent()){
            userRedisRepository.deleteById(id);
        }
    }

    public void delete(String id) {
        userJpaRepository.deleteById(id);
        userRedisRepository.deleteById(id);
    }
}
