package com.caffeine.artenon.Model;

public class CourseModel {

    private String level, price, category, popularity, reviews, sales, advanced, newest;

    public CourseModel() {
    }

    public CourseModel(String level, String price, String category, String popularity, String reviews, String sales, String advanced, String newest) {
        this.level = level;
        this.price = price;
        this.category = category;
        this.popularity = popularity;
        this.reviews = reviews;
        this.sales = sales;
        this.advanced = advanced;
        this.newest = newest;
    }

    public String getLevel() {
        return level;
    }

    public String getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }

    public String getPopularity() {
        return popularity;
    }

    public String getReviews() {
        return reviews;
    }

    public String getSales() {
        return sales;
    }

    public String getAdvanced() {
        return advanced;
    }

    public String getNewest() {
        return newest;
    }
}
