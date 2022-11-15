package demo.symple.userapi.service

import demo.symple.userapi.entity.User
import demo.symple.userapi.repository.UserRepository
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class UserService @Autowired constructor(val userRepository: UserRepository) {

    companion object {
        private val log = LoggerFactory.getLogger(UserService::class.java)
    }

    fun get(name: String): User? {
        log.info("get user. name: {}", name)
        return userRepository.selectOne(name)
    }

    fun findAll(name: String?): List<User> {
        log.info("findAll")
        val list = userRepository.findAll()
        return list.filter { p -> name == null || p.name == name }
    }

    fun add(user: User): User {
        user.createdDts = LocalDateTime.now()
        return userRepository.add(user)
    }

    fun modify(user: User): User {
        log.info("modify user. name: {}", user.name)
        return userRepository.update(user)
    }

    fun remove(name: String) {
        log.info("remove user. name: {}", name)
        return userRepository.delete(name)
    }

}
