package com.atjl.util.config;


import java.util.List;

public class BlackWhiteDto {

    private boolean gotWhite;
    private boolean gotBlack;

    private List<String> white;
    private List<String> black;

    public boolean isGotWhite() {
        return gotWhite;
    }

    public void setGotWhite(boolean gotWhite) {
        this.gotWhite = gotWhite;
    }

    public boolean isGotBlack() {
        return gotBlack;
    }

    public void setGotBlack(boolean gotBlack) {
        this.gotBlack = gotBlack;
    }

    public List<String> getWhite() {
        return white;
    }

    public void setWhite(List<String> white) {
        this.white = white;
    }

    public List<String> getBlack() {
        return black;
    }

    public void setBlack(List<String> black) {
        this.black = black;
    }
}
