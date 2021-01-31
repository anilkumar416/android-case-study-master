package com.target.targetcasestudy

import com.google.common.truth.Truth.assertThat
import com.target.targetcasestudy.data.validateCreditCard
import org.junit.Assert
import org.junit.Test

/**
 * Feel free to make modifications to these unit tests! Remember, you have full technical control
 * over the project, so you can use any libraries and testing strategies that see fit.
 */
class CreditCardValidatorTest {

    @Test
    fun `is credit card number null check`() {
        val result = validateCreditCard("")
        assertThat(result).isFalse()
    }

    @Test
    fun `is credit card number valid`() {
        val result = validateCreditCard("4539976741512043")
        assertThat(result).isTrue()
    }

    @Test
    fun `is credit card number less than 13 invalid`() {
        val result = validateCreditCard("79927398713")
        assertThat(result).isFalse()
    }

    @Test
    fun `is credit card number greater than 19 invalid`() {
        val result = validateCreditCard("6011833524457658931JCB")
        assertThat(result).isFalse()
    }

    @Test
    fun `is credit card number invalid`() {
        val result = validateCreditCard("55555555555555555")
        assertThat(result).isFalse()
    }
}
