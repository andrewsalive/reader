package example.com.reader.domain.exception

class NetworkException : DomainException {

    companion object {
        val NETWORK_ERROR = -1
        val TIMEOUT_ERROR = -2
    }

    constructor(code: Int) : super(code)
    constructor(t: Throwable) : super(t)
    constructor(t: Throwable, code: Int) : super(t, code)
    constructor(t: Throwable, code: Int, message: String?) : super(t, code, message)
}