package com.workintech.s18d2.dto;

public record ApiResponse<T>(String message, T data) {

}
