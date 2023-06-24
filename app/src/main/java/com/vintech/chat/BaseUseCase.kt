package com.vintech.chat

import com.vintech.chat.ui.Form

interface BaseUseCase<I: Form,O> {
    fun execute(from: I) : O
}