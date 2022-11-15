package demo.symple.userapi.utils

class BadRequestException(override val message: String?, override val cause: Throwable?) : RuntimeException(message) {
    companion object {
        const val CODE: Int = 1001
    }

    val CODE: Int = BadRequestException.CODE

    constructor() : this(null, null)
    constructor(message: String?) : this(message, null)
}

class NotFoundException(override val message: String?, override val cause: Throwable?) : RuntimeException(message) {
    companion object {
        const val CODE: Int = 1021
    }

    constructor() : this(null, null)
    constructor(message: String?) : this(message, null)
}

