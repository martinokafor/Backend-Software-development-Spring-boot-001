package customer.com.profile.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.MappedSuperclass;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;

import java.time.Instant;
import java.time.LocalDateTime;
@Getter
@Setter
@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
public abstract class AbstractEntity {
    @CreatedBy
    @Column(name = "created_on", updatable = false)
    public Instant createdOn = Instant.now();
    @Column(name = "updated_on")
    public Instant updatedOn = Instant.now();

}
