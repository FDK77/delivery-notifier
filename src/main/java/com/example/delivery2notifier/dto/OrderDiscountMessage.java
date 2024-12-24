package com.example.delivery2notifier.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.UUID;

public class OrderDiscountMessage {
    @JsonProperty("orderId")
    private UUID orderId;
    @JsonProperty("date")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime date;
    @JsonProperty("oldPrice")
    private Double oldPrice;
    @JsonProperty("newPrice")
    private Double newPrice;
    @JsonProperty("discount")
    private Double discount;

    public OrderDiscountMessage(UUID orderId, LocalDateTime date, Double oldPrice, Double newPrice, Double discount) {
        this.orderId = orderId;
        this.date = date;
        this.oldPrice = oldPrice;
        this.newPrice = newPrice;
        this.discount = discount;
    }

    public UUID getOrderId() {
        return orderId;
    }

    public void setOrderId(UUID orderId) {
        this.orderId = orderId;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Double getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(Double oldPrice) {
        this.oldPrice = oldPrice;
    }

    public Double getNewPrice() {
        return newPrice;
    }

    public void setNewPrice(Double newPrice) {
        this.newPrice = newPrice;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }
}
