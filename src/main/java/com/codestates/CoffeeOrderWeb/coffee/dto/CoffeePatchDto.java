package com.codestates.CoffeeOrderWeb.coffee.dto;

import com.codestates.CoffeeOrderWeb.validator.NotSpace;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Pattern;

@Getter
@Setter
public class CoffeePatchDto {
    private long coffeeId;
    @NotSpace(message = "커피명(한글)은 공백이 아니어야 합니다.")
    private String korName;
    @Pattern(regexp = "^([A-Za-z])(\\s?[A-Za-z])*$",
            message = "커피명(영문)은 영문이어야 합니다.(단어 사이 공백 한 칸 포함)")
    private String engName;
    @Range(min = 100, max = 50000)
    private Integer price;
}
