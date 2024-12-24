package com.example.delivery2notifier.dto;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DeliveryResult {
    @JsonProperty("orderId")
    private UUID orderId;
    @JsonProperty("distance")
    private double distance;
    @JsonProperty("estimatedTimeMinutes")
    private int estimatedTimeMinutes;
    @JsonProperty("estimatedTimeSeconds")
    private int estimatedTimeSeconds;

    public DeliveryResult() {}

    public DeliveryResult(UUID orderId, double distance, int estimatedTimeMinutes, int estimatedTimeSeconds) {
        this.orderId = orderId;
        this.distance = distance;
        this.estimatedTimeMinutes = estimatedTimeMinutes;
        this.estimatedTimeSeconds = estimatedTimeSeconds;
    }

    public UUID getOrderId() {
        return orderId;
    }

    public void setOrderId(UUID orderId) {
        this.orderId = orderId;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public int getEstimatedTimeMinutes() {
        return estimatedTimeMinutes;
    }

    public void setEstimatedTimeMinutes(int estimatedTimeMinutes) {
        this.estimatedTimeMinutes = estimatedTimeMinutes;
    }

    public int getEstimatedTimeSeconds() {
        return estimatedTimeSeconds;
    }

    public void setEstimatedTimeSeconds(int estimatedTimeSeconds) {
        this.estimatedTimeSeconds = estimatedTimeSeconds;
    }
}
