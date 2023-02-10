package test.rest_client;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class WorldTime {
    private String datetime;
    private String timezone;
    private int day_of_week;
}
