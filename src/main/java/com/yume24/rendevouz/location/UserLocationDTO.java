package com.yume24.rendevouz.location;

import java.time.Instant;

public record UserLocationDTO(
                              String id,
                              long latitude,
                              long longitude,
                              Instant timestamp
                              ) {}
