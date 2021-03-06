package io.mysocialapp.app.customfield.controllers

import io.mysocialapp.customfield.services.CustomFieldDataService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * Created by evoxmusic on 11/02/2018.
 */
@Component
@RestController
@RequestMapping("/test")
class TestController : TestMyController() {

    @Autowired
    val customFieldDataService: CustomFieldDataService? = null

    @GetMapping
    fun list() = customFieldDataService?.list(params.usageKey ?: "", params.parentType ?: "", params.parentId ?: -1L)

}