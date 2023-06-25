package com.vintech.data.usecase

import com.vintech.data.form.MainForm
import io.reactivex.rxjava3.core.Single
import java.util.concurrent.TimeUnit
import kotlin.random.Random

class MainUseCase : BaseUseCase<MainForm, Single<List<String>>> {

    override fun execute(from: MainForm) : Single<List<String>> {
        return Single.timer(3, TimeUnit.SECONDS).map { listOf(
            "Item ${Random.nextInt()}",
            "Item ${Random.nextInt()}",
            "Item ${Random.nextInt()}"
        ) }
    }
}