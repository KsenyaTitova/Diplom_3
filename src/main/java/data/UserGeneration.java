package data;

import com.github.javafaker.Faker;
import io.qameta.allure.Step;

public class UserGeneration {

    private final Faker faker = new Faker();

    public UserGeneration() {
    }
    @Step ("Заполнение имени пользователя")
    public String getName() {
        return faker.name().name();
    }

    @Step ("Заполнение email пользователя")
    public String getEmail() {
        return faker.internet().emailAddress();
    }

    @Step ("Заполнение пароля пользователя")
    public String getPassword() {
        return faker.internet().password(6, 30);
    }

    @Step ("Заполнение невалидного пароля пользователя")
    public String getInvalidPassword() {
        return faker.internet().password(1, 6);
    }
}
