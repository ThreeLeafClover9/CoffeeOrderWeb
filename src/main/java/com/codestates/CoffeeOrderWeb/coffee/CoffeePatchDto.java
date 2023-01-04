package com.codestates.CoffeeOrderWeb.coffee;

import com.codestates.CoffeeOrderWeb.validator.NotSpace;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Pattern;
import java.util.Optional;

@Getter
@Setter
public class CoffeePatchDto {
    private long coffeeId;
    @NotSpace(message = "커피명(한글)은 공백이 아니어야 합니다.")
    private String korName;
    @Pattern(regexp = "^([A-Za-z])(\\s?[A-Za-z])*$",
            message = "커피명(영문)은 영문이어야 합니다.")
    private String engName;
    private Optional<@Range(min = 100, max = 50000) Integer> price = Optional.empty();
}
