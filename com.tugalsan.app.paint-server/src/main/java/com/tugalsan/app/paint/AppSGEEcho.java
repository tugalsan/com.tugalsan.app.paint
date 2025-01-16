package com.tugalsan.app.paint;

import com.tugalsan.api.log.server.*;
import com.tugalsan.api.tuple.client.*;
import com.tugalsan.api.servlet.gwt.client.*;
import com.tugalsan.api.servlet.gwt.server.*;
import com.tugalsan.app.paint.sg.AppSGFEcho;
import com.tugalsan.lib.boot.server.TS_LibBootUtils;
import com.tugalsan.lib.login.server.*;
import javax.servlet.http.*;

public class AppSGEEcho extends TS_SGWTExecutor {

    final private static TS_Log d = TS_Log.of(AppSGEEcho.class);

    @Override
    public TGS_Tuple2<Boolean, Object> validate(HttpServletRequest rq, TGS_SGWTFuncBase funcBase) {
        var f = (AppSGFEcho) funcBase;
        var u_loginCard = TS_LibLoginCardUtils.get(TS_LibBootUtils.killTrigger, rq, f);
        if (u_loginCard.isExcuse()) {
            var msg = "loginCard==" + u_loginCard.excuse().getMessage() + ":" + funcBase.getInput_url();
            f.setExceptionMessage(msg);
            d.ce("validate", msg);
            return new TGS_Tuple2(false, null);
        }
        var loginCard = u_loginCard.value();
        if (!loginCard.userAdmin) {
            var msg = "!loginCard.userAdmin:" + funcBase.getInput_url();
            f.setExceptionMessage(msg);
            d.ce("validate", msg);
            return new TGS_Tuple2(false, loginCard);
        }
        return new TGS_Tuple2(true, loginCard);
    }

    @Override
    public String name() {
        return AppSGFEcho.class.getSimpleName();
    }

    @Override
    public void run(HttpServletRequest rq, TGS_SGWTFuncBase funcBase, Object vldRtn) {
//        var loginCard = (TGS_LibLoginCard) vldRtn;
        var f = (AppSGFEcho) funcBase;
        f.setOutput_msg(f.getInput_msg());
    }
}
