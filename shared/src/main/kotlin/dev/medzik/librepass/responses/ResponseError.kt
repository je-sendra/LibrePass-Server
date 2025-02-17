package dev.medzik.librepass.responses

/**
 * ResponseError represents the error responses from the API.
 *
 * @property statusCode The HTTP status code.
 */
enum class ResponseError(val statusCode: HttpStatus) {
    INVALID_BODY(HttpStatus.BAD_REQUEST),
    INVALID_CREDENTIALS(HttpStatus.UNAUTHORIZED),
    INVALID_2FA_CODE(HttpStatus.UNAUTHORIZED),
    RE_LOGIN_REQUIRED(HttpStatus.UNAUTHORIZED),
    EMAIL_NOT_VERIFIED(HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED),
    NOT_FOUND(HttpStatus.NOT_FOUND),
    TOO_MANY_REQUESTS(HttpStatus.TOO_MANY_REQUESTS),
    CIPHER_TOO_LARGE(HttpStatus.CONTENT_TOO_LARGE),

    // Database errors
    DATABASE_DUPLICATED_KEY(HttpStatus.CONFLICT),
    DATABASE_ERROR(HttpStatus.INTERNAL_SERVER_ERROR),

    UNEXPECTED_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR)
}

/**
 * HttpStatus represents the HTTP error status codes that can be returned by the API.
 *
 * @property code The HTTP status code.
 */
enum class HttpStatus(val code: Int) {
    BAD_REQUEST(400),
    UNAUTHORIZED(401),
    NOT_FOUND(404),
    CONFLICT(409),
    CONTENT_TOO_LARGE(413),
    TOO_MANY_REQUESTS(429),
    INTERNAL_SERVER_ERROR(500)
}
