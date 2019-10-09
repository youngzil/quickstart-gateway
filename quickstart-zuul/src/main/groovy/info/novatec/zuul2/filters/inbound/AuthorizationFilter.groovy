package info.novatec.zuul2.filters.inbound

import com.netflix.zuul.filters.http.HttpInboundSyncFilter
import com.netflix.zuul.message.http.HttpRequestMessage
import info.novatec.zuul2.filters.endpoint.BadRequestEndpoint
import io.netty.handler.codec.http.cookie.Cookie
import org.apache.http.HttpHeaders

/**
 * HttpInboundSyncFilter that extracts the 'customer-Id' cookie and adds its value to the
 * request's Authorization header.
 */
class AuthorizationFilter extends HttpInboundSyncFilter {

    @Override
    HttpRequestMessage apply(HttpRequestMessage request) {
        Cookie cookie = request.parseCookies().getFirst("customer-Id")
        if (!cookie?.value()?.trim()) {
            request.getContext().setEndpoint(BadRequestEndpoint.class.getCanonicalName())
        } else {
            request.getHeaders().add(HttpHeaders.AUTHORIZATION, cookie.value())
            return request
        }
    }

    @Override
    int filterOrder() {
        return 1
    }

    @Override
    boolean shouldFilter(HttpRequestMessage request) {
        return true
    }

}
