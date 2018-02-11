package io.mysocialapp.customfield.services

import io.mysocialapp.customfield.extensions.lazyLogger
import org.springframework.data.cassandra.core.WriteListener
import java.lang.Exception

/**
 * Created by evoxmusic on 12/06/17.
 */
open class MyWriteListener<T>(val onComplete: ((entities: T?) -> Unit)? = null) : WriteListener<T> {

    private val log by lazyLogger()

    override fun onWriteComplete(entities: MutableCollection<T>?) {
        onComplete?.invoke(entities?.firstOrNull())
    }

    override fun onException(x: Exception?) {
        log.error(x?.message)
        x?.printStackTrace()
    }

}