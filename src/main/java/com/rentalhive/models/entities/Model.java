package com.rentalhive.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Builder;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EntityListeners({AuditingEntityListener.class})
@Table(name = "models")
@Entity
public class Model {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is mandatory")
    private String name;

    @ManyToOne()
    @NotNull(message = "Family is mandatory")
    private Family family;

    @JsonIgnore
    @OneToMany(mappedBy = "model" , fetch = FetchType.LAZY)
    private List<Equipment> equipmentList;

    @JsonIgnore
    @CreatedDate
    private LocalDateTime createdAt;

    @JsonIgnore
    @LastModifiedDate
    private LocalDateTime modifiedAt;
}
