package io.mysocialapp.app.customfield.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import javax.servlet.http.HttpServletRequest

/**
 * Created by evoxmusic on 21/08/2017.
 */
@Component
class TestMyController {

    @Autowired
    val req: HttpServletRequest? = null

    val params: TestQueryParameters
        get() = TestQueryParameters().apply {
            req?.getParameter("usage_key")?.let { usageKey = it }
            req?.getParameter("parent_type")?.let { parentType = it }
            req?.getParameter("parent_id")?.let { parentId = it.toLong() }
        }

}