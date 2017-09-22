package com.atjl.util.common.domain;


import java.util.List;

public class BlackWhiteResp<T> {
    List<T> white;
    List<T> black;
    boolean useWhite;
    boolean useBlack;

    public List<T> getWhite() {
        return white;
    }

    public void setWhite(List<T> white) {
        this.white = white;
    }

    public List<T> getBlack() {
        return black;
    }

    public void setBlack(List<T> black) {
        this.black = black;
    }

    public boolean isUseWhite() {
        return useWhite;
    }

    public void setUseWhite(boolean useWhite) {
        this.useWhite = useWhite;
    }

    public boolean isUseBlack() {
        return useBlack;
    }

    public void setUseBlack(boolean useBlack) {
        this.useBlack = useBlack;
    }
}
