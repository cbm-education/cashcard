package com.cassiomenezes.cashcard;

import org.springframework.data.annotation.Id;
import org.springframework.data.repository.CrudRepository;

record CashCard(@Id Long id, Double amount) {
}
