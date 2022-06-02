package hr.tvz.smolcic.hardwareapp.DTOs;

import hr.tvz.smolcic.hardwareapp.enums.Rating;

public class ReviewDTO {

    String title;
    String text;
    Rating rating;

    public ReviewDTO(String text, Rating rating) {
        this.text = text;
        this.rating = rating;
    }

    public ReviewDTO(String title, String text, Rating rating) {
        this.title = title;
        this.text = text;
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }
}
