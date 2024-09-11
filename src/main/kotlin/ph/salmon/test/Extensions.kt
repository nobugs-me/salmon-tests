package ph.salmon.test

import io.qameta.allure.Allure
import io.qameta.allure.Step

@Step
fun <T> GIVEN(stepTitle: String, action: () -> T) {
    Allure.step(stepTitle)
    action.invoke()
}

@Step
fun <T> WHEN(stepTitle: String, action: () -> T) {
    Allure.step(stepTitle)
    action.invoke()
}

@Step
fun <T> THEN(stepTitle: String, action: () -> T) {
    Allure.step(stepTitle)
    action.invoke()
}