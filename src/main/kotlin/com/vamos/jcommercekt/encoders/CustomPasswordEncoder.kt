package com.vamos.jcommercekt.encoders

import org.springframework.context.annotation.Bean
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component

@Component
interface CustomPasswordEncoder: PasswordEncoder