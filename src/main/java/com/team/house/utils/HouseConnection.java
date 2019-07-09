package com.team.house.utils;

public class HouseConnection  extends Page{
    private String title;
    private Integer typeId=-1;
    private Integer districtId=-1;
    private Integer streetId=-1;

    private Integer minPrice;
    private Integer maxPrice;
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Integer getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Integer districtId) {
        this.districtId = districtId;
    }

    public Integer getStreetId() {
        return streetId;
    }

    public void setStreetId(Integer streetId) {
        this.streetId = streetId;
    }

    public Integer getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Integer minPrice) {
        this.minPrice = minPrice;
    }

    public Integer getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(Integer maxPrice) {
        this.maxPrice = maxPrice;
    }

    @Override
    public String toString() {
        return "HouseConnection{" +
                "title='" + title + '\'' +
                ", typeId=" + typeId +
                ", districtId=" + districtId +
                ", streetId=" + streetId +
                ", minPrice=" + minPrice +
                ", maxPrice=" + maxPrice +
                '}';
    }
}
