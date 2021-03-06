package com.inderbagga.fox.util

import android.util.Patterns
import androidx.core.widget.doOnTextChanged
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

private fun <T> T.verify(validator: Validator<T>): Boolean = validator.validate(this)

interface Validator<in T> {
    fun validate(any: T?): Boolean
}

private class EmailValidator : Validator<CharSequence> {
    override fun validate(any: CharSequence?): Boolean =
        !any.isNullOrBlank() && Patterns.EMAIL_ADDRESS.matcher(any).matches()
}

val EMAIL_VALIDATOR: Validator<CharSequence> = EmailValidator()

private class PasswordValidator : Validator<CharSequence> {
    override fun validate(any: CharSequence?): Boolean = !any.isNullOrBlank() && any.length >= 8
}

val PASSWORD_VALIDATOR: Validator<CharSequence> = PasswordValidator()

fun TextInputEditText.validate(validator:Validator<CharSequence>, container: TextInputLayout, message:String) {
    this.doOnTextChanged { text, _, _, _ ->
        text?.let {
            val isValid=it.verify(validator)
                container.error = if (text.isNotEmpty() && !isValid) message else null
        }
    }
}