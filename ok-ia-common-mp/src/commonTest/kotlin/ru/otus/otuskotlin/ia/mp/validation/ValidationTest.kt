package ru.otus.otuskotlin.ia.mp.validation

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class ValidationTest {

    @Test
    fun stringValidationTest() {
        val validator = ValidatorStringNotEmpty()
        val res = validator.validate("")
        "".validate(validator)

        assertEquals(false, res.isSuccess)
        assertTrue {
            res.errors.map {it.message}.first().contains("empty")
        }
    }

    @Test
    fun lengthValidationTest() {
        val validator = ValidatorMinLength("login",5)
        val res = validator.validate("test")
        "test".validate(validator)

        assertEquals(false, res.isSuccess)
        assertTrue {
            res.errors.map {it.message}.first().contains("longer")
        }
    }

    @Test
    fun validatorsListTest(sample: String) {
        val login = ""
        val validator = ValidatorChild()
        val res = validator.validate(login)

        assertEquals(false, res.isSuccess)
        assertTrue ("contains empty error") {
            res.errors.filter {it.message.contains("empty")}.isNotEmpty()
        }
        assertTrue ("contains longer error") {
            res.errors.filter {it.message.contains("longer")}.isNotEmpty()
        }
    }
}

class ValidatorChild {
    val validators: List<IValidator<String?>> = listOf(
        ValidatorMinLength("login", 5),
        ValidatorStringNotEmpty()
    )

    fun validate (login: String): ValidationResult = ValidationResult (
        validators[0].validate(login),
        validators[1].validate(login)
    )
}

interface IValidator<T> {
    fun validate (login: T): ValidationResult
}

private fun String.validate(validator: ValidatorMinLength) = validator.validate(this)
private fun String.validate(validator: ValidatorStringNotEmpty) = validator.validate(this)

class ValidatorMinLength(val field: String, val min: Int) : IValidator<String?> {
    override fun validate(sample: String?): ValidationResult = if (sample != null && sample.length >= min) {
        ValidationResult.SUCCESS
    } else {
        ValidationResult(
            errors = listOf(
                ValidationFieldError(
                    field = field,
                    message = "Field $field must be longer than $min characters"
                )
            )
        )
    }
}


class ValidatorStringNotEmpty : IValidator<String?> {
    override fun validate(sample: String?): ValidationResult = if (sample.isNullOrBlank()) {
        ValidationResult(
            errors = listOf(
                ValidationDefaultError(
                    message = "String \"sample\" must not be empty or null"
                )
            )
        )
    } else {
        ValidationResult.SUCCESS
    }
}




class ValidationResult (val errors: List<IValidationError>) {
    constructor(vararg errors: ValidationResult) : this(errors = errors.flatMap {it.errors}.toList())

    val isSuccess: Boolean
        get() = errors.isEmpty()

    companion object {
        val SUCCESS = ValidationResult(errors = emptyList())

    }

}

data class ValidationDefaultError (
    override val message: String
): IValidationError

data class ValidationFieldError (
    override val message: String, override val field: String
): IValidationFieldError

interface IValidationError {
    val message: String
}

interface IValidationFieldError: IValidationError {
    val field: String
}