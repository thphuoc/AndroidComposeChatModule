package com.vintech.data.usecase

import com.vintech.data.form.Form

interface BaseUseCase<I: Form,O> {
    fun execute(from: I) : O
}