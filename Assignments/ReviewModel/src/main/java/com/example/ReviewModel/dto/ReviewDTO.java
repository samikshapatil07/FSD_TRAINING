package com.example.ReviewModel.dto;

public class ReviewDTO {

    private int id;
    private String comment;
    private int rating;
    private String customerName;
    private String productTitle;

    public ReviewDTO() {}

    public ReviewDTO(int id, String comment, int rating, String customerName, String productTitle) {
        this.id = id;
        this.comment = comment;
        this.rating = rating;
        this.customerName = customerName;
        this.productTitle = productTitle;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }
}
