package tests;

import com.codeborne.selenide.Condition;
import io.appium.java_client.MobileBy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Selectors.byClassName;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static io.qameta.allure.Allure.step;

public class AndroidTests extends TestBase {

    @Test
    @DisplayName("Поиск в Wikipedia, с урока")
    void searchTest() {
        step("Кликаем в поле поиска Wikipedia", () -> {
            $(MobileBy.AccessibilityId("Search Wikipedia")).click();
        });

        step("Вводим BrowserStack в качестве поискового запроса", () -> {
            $(MobileBy.id("org.wikipedia.alpha:id/search_src_text")).sendKeys("BrowserStack");
        });

        step("Проверяем что есть результаты поиска", () -> {
            $$(byClassName("android.widget.TextView")).shouldHave(sizeGreaterThan(0));
        });
    }

    @Test
    @DisplayName("Смена языка на русский")
    void changeLanguage() {
        step("Открываем меню Settings", () -> {
            $(MobileBy.id("org.wikipedia.alpha:id/menu_overflow_button")).click();
            $(MobileBy.id("org.wikipedia.alpha:id/explore_overflow_settings")).click();
        });

        step("Меняем язык поиска на Русский", () -> {
            $(MobileBy.id("android:id/title")).click();
            $(MobileBy.id("org.wikipedia.alpha:id/preference_languages_filter")).sendKeys("Russian");
            $(MobileBy.id("org.wikipedia.alpha:id/language_subtitle")).click();
        });

        step("Возвращаемся на экран со строкой поиска", () -> {
            $(byClassName("android.widget.ImageButton")).click();
        });

        step("Открываем строку поиска", () -> {
            $(MobileBy.AccessibilityId("Search Wikipedia")).click();
        });

        step("Проверяем что язык переключен на русский", () -> {
            $(MobileBy.id("org.wikipedia.alpha:id/search_lang_button")).shouldHave(Condition.text("RU"));
        });

    }

}
