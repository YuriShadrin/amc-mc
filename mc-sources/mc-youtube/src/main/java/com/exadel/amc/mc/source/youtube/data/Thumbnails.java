package com.exadel.amc.mc.source.youtube.data;

import com.google.gson.annotations.SerializedName;

public class Thumbnails {

    @SerializedName("default")
    private Thumbnail defaul;
    private Thumbnail medium;
    private Thumbnail high;

    public Thumbnail getDefault() {
        return defaul;
    }

    public void setDefault(Thumbnail defaul) {
        this.defaul = defaul;
    }

    public Thumbnail getMedium() {
        return medium;
    }

    public void setMedium(Thumbnail medium) {
        this.medium = medium;
    }

    public Thumbnail getHigh() {
        return high;
    }

    public void setHigh(Thumbnail high) {
        this.high = high;
    }

    @Override
    public String toString() {
        return "Thumbnails [default=" + defaul + ", medium=" + medium + ", high=" + high + "]";
    }

}
