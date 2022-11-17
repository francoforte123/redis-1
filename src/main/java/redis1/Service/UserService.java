package redis1.Service;

import org.springframework.beans.BeanUtils;
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

    public UserRedis convertData(UserJpa userJpa){
        UserRedis playerRedis = new UserRedis();
        BeanUtils.copyProperties(userJpa, playerRedis);
        return playerRedis;
    }

    public UserJpa convertData(UserRedis userRedis){
        UserJpa playerRedis = new UserJpa();
        BeanUtils.copyProperties(userRedis, playerRedis);
        return playerRedis;
    }

    public UserJpa create(UserJpa userJpa) {
        if(userJpa == null) return null;
        userJpa.setId(userJpa.getId());
        return userJpaRepository.save(userJpa);
    }

    public List<UserJpa> readAll() {
        return userJpaRepository.findAll();
    }

    public UserJpa update(Long id, UserJpa player) {
        player.setId(id);
        Optional<UserRedis> userRedis = userRedisRepository.findById(id);
        if(userRedis.isPresent()) {
            userRedisRepository.deleteById(id);
        }

        return player;
    }

    public UserJpa readOne(Long id) {
        Optional<UserRedis> playerRedis = userRedisRepository.findById(id);
        if(playerRedis.isPresent()){
            return convertData(playerRedis.get());
        }else {
            UserJpa userFromDB = userJpaRepository.getById(id);
            if (userFromDB == null ) return null;
            userRedisRepository.save(convertData(userFromDB));
            return userFromDB;
        }
    }

    public void delete(Long id) {
        userJpaRepository.deleteById(id);
        userRedisRepository.deleteById(id);
    }
}
