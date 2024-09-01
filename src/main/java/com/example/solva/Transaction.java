package com.example.solva;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Getter
@Setter
@Entity
@Table(name = "transactions")
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "account_from_id", nullable = false)
    private User accountFrom;

    @ManyToOne
    @JoinColumn(name = "account_to_id", nullable = false)
    private User accountTo;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal sum;

    @Column(nullable = false, length = 3)
    private String currency_shortname;

    @Column(nullable = false)
    private ZonedDateTime dateTime;
}
