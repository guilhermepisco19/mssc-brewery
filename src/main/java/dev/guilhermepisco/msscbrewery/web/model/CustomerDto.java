package dev.guilhermepisco.msscbrewery.web.model;

import java.util.UUID;

public record CustomerDto(UUID customerId, String name) {
}
