package com.vamos.jcommercekt.security

import com.vamos.jcommercekt.encoders.CustomPasswordEncoder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.security.crypto.password.PasswordEncoder

@Configuration
class SecurityConfig : WebSecurityConfigurerAdapter(){

    fun getPasswordEncoder():PasswordEncoder?{
        return PasswordEncoderFactories.createDelegatingPasswordEncoder()
    }

    override fun configure(http: HttpSecurity?) {
//        http?.authorizeRequests()?.anyRequest()?.permitAll()?.and()?.authorizeRequests()?.anyRequest()?.fullyAuthenticated()?.and()?.formLogin()
        http?.authorizeRequests()?.anyRequest()?.permitAll()
        http?.csrf()?.disable()
    }

    override fun configure(auth: AuthenticationManagerBuilder?) {
        auth?.inMemoryAuthentication()?.withUser("admin")?.password(getPasswordEncoder()?.encode("admin"))?.roles("ADMIN")
    }


}