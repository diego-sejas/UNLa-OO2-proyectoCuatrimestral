package com.unla.app.models;

public class AdminSideBar {
    private int id;
    private String url;
    private String icon;
    private String text;


    public AdminSideBar(int id, String url, String icon, String text) {
        this.id = id;
        this.url = url;
        this.icon = icon;
        this.text = text;
    }


    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIcon() {
        return this.icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", url='" + getUrl() + "'" +
            ", icon='" + getIcon() + "'" +
            ", text='" + getText() + "'" +
            "}";
    }


}
