package com.apigateway.rating.entity;

import java.util.UUID;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(schema = "hotel_service", name = "rating")
public class Rating {

    @Id
    private String ratingId;

    private String userId;
    private String hotelId;
    private int rating;
    private String feedback;

    @PrePersist
    private void ensureId() {
        if (this.ratingId == null || this.ratingId.isEmpty()) {
            this.ratingId = UUID.randomUUID().toString();
        }
    }
}
