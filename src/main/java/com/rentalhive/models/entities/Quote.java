package com.rentalhive.models.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.rentalhive.models.enums.QuoteStatus;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Quote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double equipment_discount;
    private String description;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private Demand demand;
    private QuoteStatus status;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime modifiedAt;
}
