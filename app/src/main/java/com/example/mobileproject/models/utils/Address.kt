package com.example.mobileproject.models.api_responses.utils

class Address (
    private val address: String,
    private val number: String,
    private val location: String,
    private val description: String
) {
    override fun toString(): String {
        return "Address(address='$address', number='$number', location='$location', description='$description')"
    }
}