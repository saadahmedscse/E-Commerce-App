package com.caffeine.artenon.Model;

public class CourseModel {

    private String name, price, category, popularity, reviews, sales, advanced, picture;

    public CourseModel() {
    }

    public CourseModel(String name, String price, String category, String popularity, String reviews, String sales, String advanced, String picture) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.popularity = popularity;
        this.reviews = reviews;
        this.sales = sales;
        this.advanced = advanced;
        this.picture = picture;
    }

    public String getName() {
        return name;
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

    public String getPicture() {
        return picture;
    }
}
