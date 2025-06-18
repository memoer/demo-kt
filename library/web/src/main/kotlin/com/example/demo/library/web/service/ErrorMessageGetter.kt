package com.example.demo.library.web.service

import com.example.demo.library.web.annotation.ErrorMessageSource
import com.example.demo.support.holder.CountryCodeHolder
import org.springframework.context.MessageSource
import org.springframework.stereotype.Service
import java.util.Locale

@Service
class ErrorMessageGetter(@ErrorMessageSource private val messageSource: MessageSource) {
    fun get(key: String, vararg parameter: String?): String {
        val countryCode = CountryCodeHolder.get()
        val locale = locale(countryCode.name)
        return messageSource.getMessage(key, parameter, locale)
    }

    private fun locale(code: String) = Locale.Builder().setLanguage(code.lowercase()).setRegion(code.uppercase()).build()
}
