package demo.symple.userapi.api

import demo.symple.userapi.entity.Response
import demo.symple.userapi.entity.User
import demo.symple.userapi.service.UserService
import demo.symple.userapi.utils.BadRequestException
import demo.symple.userapi.utils.NotFoundException
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder

@Controller
@RequestMapping("${UserController.BASE_URI}")
class UserController @Autowired constructor(val userService: UserService) {

    companion object {
        private val log = LoggerFactory.getLogger(UserController::class.java)
        const val BASE_URI = "/api/v1/users"
    }

    @GetMapping("/query")
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

    @GetMapping("/{name}")
    fun get(@PathVariable(name = "name") name: String): ResponseEntity<User> {
        return try {
            ResponseEntity.ok(userService.get(name))
        } catch (e: NotFoundException) {
            log.warn(e.message, e)
            ResponseEntity.notFound().build()
        }
    }

    @PostMapping
    fun add(@RequestBody user: User): ResponseEntity<Any?> {
        return try {
            log.info("added user: {}", userService.add(user))
            val location = UriComponentsBuilder.newInstance().path(BASE_URI).pathSegment(user.name).build().toUri()
            ResponseEntity.created(location).build()
        } catch (ne: NullPointerException) {
            log.error(ne.message, ne)
            val be = BadRequestException(ne.message, ne)
            ResponseEntity.badRequest().body(Response(be.CODE, be.message!!))
        }
    }

    @PutMapping("/{name}")
    fun modify(@PathVariable(name = "name") name: String, @RequestBody user: User): ResponseEntity<Any>? {
        if (!name.equals(user.name, ignoreCase = true)) {
            val errMsg: String = "Not matched user data. name: %s, user.name: %s".format(name, user.name)
            log.warn(errMsg)
            return ResponseEntity.badRequest().body(Response(1001, errMsg))
        }
        return try {
            ResponseEntity.ok(userService.modify(user))
        } catch (ne: NotFoundException) {
            log.warn(ne.message, ne)
            ResponseEntity.notFound().build()
        } catch (e: Throwable) {
            log.error(e.message, e)
            ResponseEntity.badRequest().body(e.message?.let { Response(1021, "%s".format(e.message)) })
        }
    }

    @DeleteMapping("/{name}")
    fun delete(@PathVariable(name = "name") name: String): ResponseEntity<Any> {
        return try {
            log.info("delete user - name: {}", name)
            userService.remove(name)
            ResponseEntity.noContent().build()
        } catch (ne: NotFoundException) {
            log.warn(ne.message, ne)
            ResponseEntity.notFound().build()
        } catch (e: Throwable) {
            log.error(e.message, e)
            ResponseEntity.badRequest().body(e.message?.let { Response(1021, it) })
        }
    }

}
