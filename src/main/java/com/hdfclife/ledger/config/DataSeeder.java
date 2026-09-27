package com.hdfclife.ledger.config;

import org.springframework.context.annotation.Configuration;

/**
 * Reference and sample ledger data are provisioned by Flyway migrations
 * ({@code V2__seed_reference_riders.sql}, {@code V3__seed_policy_ledger.sql}).
 * This configuration class is retained as the extension point if runtime seeding
 * is introduced later.
 */
@Configuration
public class DataSeeder {
}
