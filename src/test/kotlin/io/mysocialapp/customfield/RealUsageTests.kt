package io.mysocialapp.customfield

import io.mysocialapp.customfield.models.FieldData
import io.mysocialapp.customfield.models.InputTextField
import io.mysocialapp.customfield.models.Language
import io.mysocialapp.customfield.services.CustomFieldDataService
import io.mysocialapp.customfield.services.CustomFieldService
import io.mysocialapp.customfield.utils.UUID
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

/**
 * Created by evoxmusic on 11/02/2018.
 */
@RunWith(SpringRunner::class)
@SpringBootTest
class RealUsageTests {

    @Autowired
    private val customFieldService: CustomFieldService? = null

    @Autowired
    private val customFieldDataService: CustomFieldDataService? = null

    @Test
    fun createRandomCustomField() {
        // simulate random field created by user

        val usageKey = "profile"

        val firstNameInputText = InputTextField.Builder(usageKey)
                .addName(Language.FR, "prénom")
                .addName(Language.EN, "first name")
                .addDescription(Language.FR, "Mettez votre prénom")
                .addDescription(Language.EN, "Set your first name")
                .addPlaceholder(Language.FR, "Romaric")
                .addPlaceholder(Language.EN, "Georges")
                .build()

        val lastNameInputText = InputTextField.Builder(usageKey)
                .addName(Language.FR, "nom")
                .addName(Language.EN, "last name")
                .addDescription(Language.FR, "Mettez votre nom")
                .addDescription(Language.EN, "Set your last name")
                .addPlaceholder(Language.FR, "Philogène")
                .addPlaceholder(Language.EN, "Clooney")
                .build()


        val fields = customFieldService?.create(firstNameInputText, lastNameInputText)
        assert(fields?.size == 2)

        val parentType = "User"
        val parentId = UUID.generateLongId()
        val datas = customFieldDataService?.create(usageKey, parentType, parentId,
                FieldData(fieldId = firstNameInputText.id, value = "Romaric"),
                FieldData(fieldId = lastNameInputText.id, value = "Philogène"))

        assert(datas?.size == 2)


        val listData = customFieldDataService?.list(usageKey, parentType, parentId)
        assert(listData?.size == 2)
    }

}