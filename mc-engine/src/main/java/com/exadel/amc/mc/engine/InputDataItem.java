package com.exadel.amc.mc.engine;

public class InputDataItem  {

    private String id;
    private String type;
    private boolean disabled;

    public InputDataItem() {
    }

    public InputDataItem(String id, String type, boolean disabled) {
        super();
        this.id = id;
        this.type = type;
        this.disabled = disabled;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    public boolean isDisabled() {
        return disabled;
    }
    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

}
