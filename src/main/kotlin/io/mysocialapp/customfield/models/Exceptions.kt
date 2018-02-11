package io.mysocialapp.customfield.models

/**
 * Created by evoxmusic on 11/02/2018.
 */
class MissingMandatoryFieldException(message: String?) : RuntimeException(message)

class FieldFormatException(message: String?) : RuntimeException(message)