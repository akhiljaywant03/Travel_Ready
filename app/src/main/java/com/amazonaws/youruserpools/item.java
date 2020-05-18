package com.amazonaws.youruserpools;

public class item {


    int background;
    String modeName;
    String modeDesc;

    public int getBackground() {
        return background;
    }

    public String getModeName() {
        return modeName;
    }

    public String getModeDesc() {
        return modeDesc;
    }



    public item(){

    }
    public item(int background, String modeName, String modeDesc) {
        this.background = background;
        this.modeName = modeName;
        this.modeDesc = modeDesc;
    }

    public void setBackground(int background) {
        this.background = background;
    }

    public void setModeName(String modeName) {
        this.modeName = modeName;
    }

    public void setModeDesc(String modeDesc) {
        this.modeDesc = modeDesc;
    }
}
