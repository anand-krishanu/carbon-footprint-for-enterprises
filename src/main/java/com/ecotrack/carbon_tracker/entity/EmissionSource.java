package com.ecotrack.carbon_tracker.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "emission_sources")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmissionSource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "source_name", nullable = false, unique = true)
    private String sourceName;

    @OneToMany(mappedBy = "emissionSource", cascade = CascadeType.ALL)
    private List<EmissionRecord> emissionRecords;
}
