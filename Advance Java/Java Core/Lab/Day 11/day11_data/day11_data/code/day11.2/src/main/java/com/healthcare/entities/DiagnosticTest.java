
package com.healthcare.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Entity
@Table(name = "diagnostic_tests")
//lombok annotations
@NoArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)

public class DiagnosticTest extends BaseEntity{   

    @Column(length = 100, nullable = false, unique = true)
    private String name; // e.g., "Blood Test", "X-Ray", "MRI"

    @Column(length = 500)
    private String description;    
    private int price;
   
}
