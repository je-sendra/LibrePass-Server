package dev.medzik.librepass.server.components

import jakarta.servlet.http.HttpServletRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.MethodParameter
import org.springframework.stereotype.Component
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.method.support.ModelAndViewContainer

/** Annotation for getting request IP from request. */
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.VALUE_PARAMETER, AnnotationTarget.ANNOTATION_CLASS)
annotation class RequestIP

/** Implementation of the [RequestIP] annotation. */
@Component
class RequestIPArgumentResolver
    @Autowired
    constructor(
        @Value("\${http.ip.header}")
        private val ipHeader: String
    ) : HandlerMethodArgumentResolver {
        override fun supportsParameter(parameter: MethodParameter): Boolean {
            return parameter.hasParameterAnnotation(RequestIP::class.java)
        }

        override fun resolveArgument(
            parameter: MethodParameter,
            mavContainer: ModelAndViewContainer?,
            webRequest: NativeWebRequest,
            binderFactory: WebDataBinderFactory?
        ): String? {
            val request =
                webRequest.getNativeRequest(HttpServletRequest::class.java)
                    ?: return null

            return if (ipHeader == "remoteAddr")
                request.remoteAddr
            else
                request.getHeader(ipHeader)
                    ?: request.remoteAddr
        }
    }
