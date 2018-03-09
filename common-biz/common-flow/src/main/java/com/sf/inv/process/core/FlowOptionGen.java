package com.sf.inv.process.core;


import com.sf.inv.process.dto.FlowConstant;

/**
 * flow option util generator
 */
public class FlowOptionGen {

    private int option;

    public static int getNormalOption() {
        return 0;
    }

    public int get() {
        return option;
    }


    public FlowOptionGen reset() {
        this.option = 0;
        return this;
    }

    public int getOption() {
        return option;
    }

    public void setOption(int option) {
        this.option = option;
    }

    public static String fmtOption(int option) {
        StringBuilder sb = new StringBuilder();
        if (isEnableErrorCodeContinue(option)) {
            sb.append("ECodeON");
        } else {
            sb.append("ECodeOFF");
        }
        if (isEnableChangeNextFlowId(option)) {
            sb.append("|NxtFlowON");
        } else {
            sb.append("|NxtFlowOFF");
        }
        if (isEnableExceptionContinue(option)) {
            sb.append("|ExpON");
        } else {
            sb.append("|ExpOFF");
        }
        return sb.toString();
    }


    /**
     * ################ option checkers #####################
     */
    public static boolean isEnableErrorCodeContinue(int option) {
        return (option & FlowConstant.OP_ERROR_CODE_CONTINUE_ENABLE) == FlowConstant.OP_ERROR_CODE_CONTINUE_ENABLE;
    }

    public static boolean isEnableExceptionContinue(int option) {
        return (option & FlowConstant.OP_EXCEPTION_CONTINUE_ENABLE) == FlowConstant.OP_EXCEPTION_CONTINUE_ENABLE;
    }

    public static boolean isEnableChangeNextFlowId(int option) {
        return (option & FlowConstant.OP_CHNAGE_NEXT_FLOWID_ENABLE) == FlowConstant.OP_CHNAGE_NEXT_FLOWID_ENABLE;
    }

    /**
     * ################ option setters #####################
     *
     * @return
     */
    public FlowOptionGen enableErrorCodeContinue() {
        this.option = option | FlowConstant.OP_ERROR_CODE_CONTINUE_ENABLE;
        return this;
    }

    public FlowOptionGen enableExceptionContinue() {
        this.option = option | FlowConstant.OP_EXCEPTION_CONTINUE_ENABLE;
        return this;
    }

    public FlowOptionGen enableChangeNextFlowId() {
        this.option = option | FlowConstant.OP_CHNAGE_NEXT_FLOWID_ENABLE;
        return this;
    }


}
