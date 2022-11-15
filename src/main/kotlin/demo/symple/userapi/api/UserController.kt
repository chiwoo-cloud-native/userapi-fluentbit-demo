package demo.symple.userapi.api

import demo.symple.userapi.entity.User
import demo.symple.userapi.service.UserService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder

@Controller
class UserController @Autowired constructor(val userService: UserService) {

    companion object {
        private val log = LoggerFactory.getLogger(UserController::class.java)
        const val BASE_URI = "/api/users"
    }

    @GetMapping("$BASE_URI/query")
    fun findAllByQuery(@RequestParam(name = "name", required = false) name: String?): ResponseEntity<List<User>> {
        return try {
            val users = userService.findAll(name)
            log.info("users.size: {}", users.size)
            ResponseEntity.ok(users)
        } catch (e: Throwable) {
            log.warn(e.message, e)
            ResponseEntity.ok(emptyList())
        }
    }

    @GetMapping("$BASE_URI/{name}")
    fun get(@PathVariable(name = "name") name: String): ResponseEntity<User> {
        return try {
            ResponseEntity.ok(userService.get(name))
        } catch (e: Throwable) {
            log.warn(e.message, e)
            ResponseEntity.notFound().build()
        }
    }

    @PostMapping(BASE_URI)
    fun add(@RequestBody user: User): ResponseEntity<String> {
        return try {
            log.info("added user: {}", userService.add(user))
            val location = UriComponentsBuilder.newInstance().path(BASE_URI).pathSegment(user.name).build().toUri()
            ResponseEntity.created(location).build<String>()
        } catch (e: Throwable) {
            log.error(e.message, e)
            ResponseEntity.badRequest().build()
        }
    }

    @PutMapping("$BASE_URI/{name}")
    fun modify(@PathVariable(name = "name") name: String, @RequestBody user: User): ResponseEntity<User>? {
        if (name.equals(user.name, ignoreCase = true)) {
            log.warn("Not matched user data.")
            return ResponseEntity.badRequest().build()
        }
        return try {
            ResponseEntity.ok(userService.modify(user))
        } catch (e: Throwable) {
            log.error(e.message, e)
            ResponseEntity.notFound().build()
        }
    }

    @DeleteMapping("$BASE_URI/{name}")
    fun delete(@PathVariable(name = "name") name: String): ResponseEntity<Any> {
        return try {
            log.info("delete user - name: {}", name)
            userService.remove(name)
            ResponseEntity.noContent().build()
        } catch (e: Throwable) {
            log.warn(e.message, e)
            ResponseEntity.notFound().build()
        }
    }

}
