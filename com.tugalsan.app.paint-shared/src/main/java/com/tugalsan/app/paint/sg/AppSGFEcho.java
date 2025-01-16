package com.tugalsan.app.paint.sg;

import com.google.gwt.user.client.rpc.*;
import com.tugalsan.api.servlet.gwt.client.*;

public class AppSGFEcho extends TGS_SGWTFuncBase implements IsSerializable {

    @Override
    public String getSuperClassName() {
        return AppSGFEcho.class.getSimpleName();
    }

    public AppSGFEcho() {
    }

    public AppSGFEcho(String msg) {
        this.input_msg = msg;
    }

    private String input_msg;
    private String output_msg;

    public String getInput_msg() {
        return input_msg;
    }

    public void setInput_msg(String input_msg) {
        this.input_msg = input_msg;
    }

    public String getOutput_msg() {
        return output_msg;
    }

    public void setOutput_msg(String output_msg) {
        this.output_msg = output_msg;
    }

    @Override
    public String toString() {
        return AppSGFEcho.class.getSimpleName() + "{" + "input_msg=" + input_msg + ", output_msg=" + output_msg + '}';
    }

}
