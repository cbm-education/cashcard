package com.cassiomenezes.cashcard;

import org.springframework.data.annotation.Id;

/**
 * @author cassio.menezes
 * @since 2024-10-23
 */
public record CashCard(@Id Long id, Double amount, String owner) {
}
