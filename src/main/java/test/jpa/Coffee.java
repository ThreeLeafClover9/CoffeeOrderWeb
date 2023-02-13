package test.jpa;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@Entity
public class Coffee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long coffeeId;
    @Column(nullable = false, length = 50)
    private String korName;
    @Column(nullable = false, length = 50)
    private String engName;
    @Column(nullable = false)
    private int price;
    @Column(nullable = false, length = 3)
    private String coffeeCode;
    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
    @Column(nullable = false, name = "LAST_MODIFIED_AT")
    private LocalDateTime modifiedAt = LocalDateTime.now();
}
