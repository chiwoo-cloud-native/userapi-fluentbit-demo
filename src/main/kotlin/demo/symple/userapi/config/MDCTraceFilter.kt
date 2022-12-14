package demo.symple.userapi.config

import jakarta.servlet.FilterChain
import jakarta.servlet.ServletException
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import org.slf4j.MDC
import org.springframework.stereotype.Component
import org.springframework.web.filter.GenericFilterBean
import java.io.IOException
import java.util.*

@Component
class MDCTraceFilter : GenericFilterBean() {
    @Throws(IOException::class, ServletException::class)
    override fun doFilter(request: ServletRequest, response: ServletResponse, filterChain: FilterChain) {
        try {
            val txId = String.format("%012x", UUID.randomUUID().mostSignificantBits)
            MDC.put(MDCKey.TX_ID.key, txId)
            // authService.getUser().ifPresent(x -> MDC.put("user_id", x.getId().toString()))
            filterChain.doFilter(request, response)
        } finally {
            MDC.remove(MDCKey.TX_ID.key)
            // MDC.clear();
        }
    }
}
