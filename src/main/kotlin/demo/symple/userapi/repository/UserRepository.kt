package demo.symple.userapi.repository

import demo.symple.userapi.entity.User
import demo.symple.userapi.utils.NotFoundException
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Repository
import java.time.LocalDateTime
import java.util.*

@Repository
class UserRepository {

    companion object {
        private val log = LoggerFactory.getLogger(UserRepository::class.java)
    }

    val lists = LinkedList<User>()
    // val cache: Cache<String, Object)

    fun add(user: User): User {
        if (user.email == null || user.age == null) {
            log.error("user is null")
            throw NullPointerException("user is null")
        }
        lists.add(user)
        return user
    }

    fun selectOne(name: String): User? {
        lists.firstOrNull() {
            it.name.equals(name, ignoreCase = true)
        }?.let {
            return it
        } ?: throw RuntimeException("user not found.")

    }

    fun findAll(): LinkedList<User> {
        return this.lists
    }

    private fun indexOf(name: String): Int? {
        val index: Int = lists.indexOfFirst {
            it.name.equals(name, ignoreCase = true)
        }
        if (index == -1) {
            return null
        }
        return index
    }

    fun update(user: User): User {
        if (user.email == null || user.age == null) {
            log.error("user is null")
            throw NullPointerException("user is null")
        }
        indexOf(user.name)?.let {
            LocalDateTime.now().also { user.createdDts = it }
            lists[it] = user
            return user
        } ?: throw NotFoundException("user not found.")
    }

    fun delete(name: String) {
        indexOf(name)?.let {
            lists.removeAt(it)
        } ?: throw NotFoundException("user not found.")
    }

}
