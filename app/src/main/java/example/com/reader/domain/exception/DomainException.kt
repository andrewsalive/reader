package example.com.reader.domain.exception

open class DomainException : Exception {

    private val UNKNOWN_ERROR = 0
    val code: Int

    constructor() : super() {
        code = UNKNOWN_ERROR
    }

    constructor(code: Int) : super() {
        this.code = code
    }

    constructor(t: Throwable) : super(t) {
        code = UNKNOWN_ERROR
    }

    constructor(t: Throwable, code: Int) : super(t) {
        this.code = code
    }

    constructor(t: Throwable, code: Int, message: String?) : super(message, t) {
        this.code = code
    }

}