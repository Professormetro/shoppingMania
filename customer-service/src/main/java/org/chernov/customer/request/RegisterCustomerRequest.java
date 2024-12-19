package org.chernov.customer.request;


public record RegisterCustomerRequest (
         String firstName,
         String lastName,
         String email
) {
}
