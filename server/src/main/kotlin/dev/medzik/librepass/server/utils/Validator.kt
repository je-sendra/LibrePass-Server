package dev.medzik.librepass.server.utils

import dev.medzik.libcrypto.X25519
import dev.medzik.librepass.server.controllers.api.ServerPrivateKey
import dev.medzik.librepass.server.database.UserTable
import dev.medzik.librepass.utils.fromHexString
import org.apache.commons.validator.routines.EmailValidator
import java.util.regex.Pattern

object Validator {
    private val REGEX_PATTERN = Pattern.compile("^\\p{XDigit}+$")

    fun emailValidator(email: String) = EmailValidator.getInstance().isValid(email)

    fun hexValidator(hex: String) = REGEX_PATTERN.matcher(hex).matches()

    fun hexValidator(
        hex: String,
        length: Int
    ) = REGEX_PATTERN.matcher(hex).matches() && hex.length == length * 2

    fun validateSharedKey(
        user: UserTable,
        sharedKey: String
    ) = validateSharedKey(
        publicKey = user.publicKey,
        sharedKey
    )

    fun validateSharedKey(
        publicKey: String,
        sharedKey: String
    ): Boolean {
        val oldSharedKey = X25519.computeSharedSecret(ServerPrivateKey, publicKey.fromHexString())
        return sharedKey.fromHexString().contentEquals(oldSharedKey)
    }
}
