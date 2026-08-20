package com.yume24.rendevouz.user;

import java.time.Instant;

public record UserLocationDTO(String id,
                              long latitude,
                              long longitude,
                              Instant lastUpdated) {
}
