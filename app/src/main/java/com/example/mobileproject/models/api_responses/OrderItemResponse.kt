package com.example.mobileproject.models.api_responses

class OrderItemResponse (
    private val id: Int,
    private val status: String,
    private val restaurantId: Int,
    private val items: List<Int>,
    private val totalCost: Float,
    private val paidDate: String
){
    override fun toString(): String {
        return "OrderItemResponse(id=$id, status='$status', restaurantId=$restaurantId, items=$items, totalCost=$totalCost, paidDate='$paidDate')"
    }
}