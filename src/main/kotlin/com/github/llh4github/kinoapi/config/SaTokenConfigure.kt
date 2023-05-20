package com.github.llh4github.kinoapi.config

import cn.dev33.satoken.interceptor.SaInterceptor
import cn.dev33.satoken.router.SaHttpMethod
import cn.dev33.satoken.router.SaRouter
import cn.dev33.satoken.stp.StpUtil
import com.jihulab.llh4gitlab.kinoapi.service.inner.InsideUrlService
import org.apache.logging.log4j.kotlin.logger
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

/**
 *
 * Created At 2023/4/2 10:32
 * @author llh
 */
@Configuration
class SaTokenConfigure(
    private val insideUrlService: InsideUrlService
) : WebMvcConfigurer {

    @Value("#{'\${anonymous.urls:}'.empty ? '' : '\${anonymous.urls:}'.split(',')}")
    private lateinit var anonymousUrls: List<String>

    override fun addInterceptors(registry: InterceptorRegistry) {
        logger().debug("可匿名访问URL ： $anonymousUrls")
        registry.addInterceptor(SaInterceptor()).excludePathPatterns(anonymousUrls).addPathPatterns("/**")
        val urlAndPermission = insideUrlService.allUrlAndPermissionCode()
        registry.addInterceptor(SaInterceptor {
            SaRouter.match(SaHttpMethod.OPTIONS).free { _ -> logger().debug("OPTIONS预检请求，不做处理") }
//            SaRouter.match("/**")
//                .notMatch(anonymousUrls)
//                .check { _ -> StpUtil.checkLogin() }

            urlAndPermission.forEach { ele ->
                val codes = ele.permissions.map { it.code }.toList()
                SaRouter.match(ele.method.toSaHttpMethod())
                    .match(ele.url)
                    .check { _ ->
                        if (ele.permissionOrMode) {
                            StpUtil.checkPermissionOr(*codes.toTypedArray())
                        } else {
                            StpUtil.checkPermissionAnd(*codes.toTypedArray())
                        }
                    }
            }
        })
            .addPathPatterns("/**")
            .excludePathPatterns(anonymousUrls)
    }

    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/**")
            .allowedOriginPatterns("*")
            .allowedHeaders("*")
//            .allowCredentials(true)
//            .allowedMethods("GET", "POST", "DELETE", "PUT", "OPTIONS")
            .allowedMethods("*")
            .maxAge(3600)
    }
}