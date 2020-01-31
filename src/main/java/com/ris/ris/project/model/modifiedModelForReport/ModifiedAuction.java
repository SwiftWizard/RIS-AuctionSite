package com.ris.ris.project.model.modifiedModelForReport;

public class ModifiedAuction{
    private String title;
    private String category;
    private String description;
    private Double maxBid;

    public ModifiedAuction(String title, String category, String description, Double maxBid) {
        this.title = title;
        this.category = category;
        this.description = description;
        this.maxBid = maxBid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getMaxBid() {
        return maxBid;
    }

    public void setMaxBid(Double maxBid) {
        this.maxBid = maxBid;
    }
}