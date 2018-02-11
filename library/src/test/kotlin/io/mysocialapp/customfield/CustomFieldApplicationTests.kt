package io.mysocialapp.customfield

import io.mysocialapp.customfield.models.InputNumberField
import io.mysocialapp.customfield.models.InputTextField
import io.mysocialapp.customfield.models.Language
import io.mysocialapp.customfield.services.CustomFieldService
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
class CustomFieldApplicationTests {

    @Autowired
    private val customFieldService: CustomFieldService? = null

    @Test
    fun contextLoads() {
    }

    @Test
    fun createInputText() {
        val inputText: InputTextField = InputTextField.Builder("test")
                .addLabel(Language.FR, "prénom")
                .addLabel(Language.EN, "first name")
                .addDescription(Language.FR, "Mettez votre prénom")
                .addDescription(Language.EN, "Set your first name")
                .addPlaceholder(Language.FR, "Romaric Philogène")
                .addPlaceholder(Language.EN, "Georges Clooney")
                .build()

        assert(inputText.names.size == 2)
        assert(inputText.descriptions.size == 2)
        assert(inputText.placeholders.size == 2)
    }

    @Test
    fun saveInputText() {
        val inputText = InputTextField.Builder("test")
                .addLabel(Language.FR, "prénom")
                .addLabel(Language.EN, "first name")
                .addDescription(Language.FR, "Mettez votre prénom")
                .addDescription(Language.EN, "Set your first name")
                .addPlaceholder(Language.FR, "Romaric Philogène")
                .addPlaceholder(Language.EN, "Georges Clooney")
                .build()

        customFieldService?.create(inputText)
    }

    @Test
    fun createInputNumber() {
        val inputNumber: InputNumberField = InputNumberField.Builder("test")
                .addLabel(Language.FR, "âge")
                .addLabel(Language.EN, "age")
                .addDescription(Language.FR, "Quel est votre âge?")
                .addDescription(Language.EN, "What's your age?")
                .addPlaceholder(Language.FR, "18")
                .addPlaceholder(Language.EN, "18")
                .build()

        assert(inputNumber.names.size == 2)
        assert(inputNumber.descriptions.size == 2)
        assert(inputNumber.placeholders.size == 2)
    }

    @Test
    fun listExistingFields() {
        assert(customFieldService?.list("test")?.toList()?.isNotEmpty() ?: false)
    }

    @Test
    fun listNoFields() {
        assert(customFieldService?.list("xxxxxxxxx")?.toList()?.isEmpty() ?: false)
    }

}
