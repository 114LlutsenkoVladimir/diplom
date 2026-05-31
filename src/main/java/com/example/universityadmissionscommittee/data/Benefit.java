package com.example.universityadmissionscommittee.data;

import com.example.universityadmissionscommittee.data.enums.ApplicantStatus;
import com.example.universityadmissionscommittee.data.enums.QuotaType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.Objects;

@Entity
@Table(
        name = "benefit",
        uniqueConstraints = {
                @UniqueConstraint(name = "uq_benefit_name", columnNames = "name")
        }
)
public class Benefit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 255)
    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @Min(0)
    @Column(name = "priority_points", nullable = true)
    private int priorityPoints;

    @Enumerated(EnumType.STRING)
    @Column(name = "quota_type", nullable = true, length = 32)
    private QuotaType type = QuotaType.NONE;

    protected Benefit() {}

    public Benefit(String name, int priorityPoints) {
        this.name = Objects.requireNonNull(name, "name");
        this.priorityPoints = priorityPoints;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = Objects.requireNonNull(name, "name");
    }

    public int getPriorityPoints() {
        return priorityPoints;
    }

    public void setPriorityPoints(int additionalPoints) {
        this.priorityPoints = additionalPoints;
    }

    public QuotaType getType() {
        return type;
    }

    public void setType(QuotaType type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Benefit other)) return false;
        return id != null && id.equals(other.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "Benefit{id=" + id +
                ", name='" + name + '\'' +
                ", additionalPoints=" + priorityPoints +
                '}';
    }
}

