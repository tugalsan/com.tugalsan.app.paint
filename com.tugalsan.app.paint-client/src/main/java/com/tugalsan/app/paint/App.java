package com.tugalsan.app.paint;

import com.google.gwt.core.client.EntryPoint;
import com.tugalsan.api.file.html.client.*;
import com.tugalsan.api.gui.client.browser.*;
import com.tugalsan.api.gui.client.click.*;
import com.tugalsan.api.gui.client.widget.*;
import com.tugalsan.api.icon.client.*;
import com.tugalsan.api.log.client.TGC_Log;
import com.tugalsan.api.time.client.TGS_Time;
import com.tugalsan.lib.boot.client.*;
import com.tugalsan.lib.domain.client.*;
import com.tugalsan.lib.login.client.*;
import com.tugalsan.lib.route.client.*;
import com.tugalsan.lib.scale.client.*;

// *!* Dont change my hardcoded classpath com.tugalsan.app.database.App *!*
public class App implements EntryPoint {

    final private static TGC_Log d = TGC_Log.of(App.class);
    public static final TGS_LibRoute route = TGC_LibRouteUtils.of();
    public static TGS_Time warVersion;
    public static TGS_LibDomainCard domainCard;
    public static TGS_LibLoginCard loginCard;

    public void onModuleLoad() {
        TGC_LibBootUtils.onModuleLoad(route, (warVersion, domainCard, runAcc2Url) -> {
            TGC_LibLoginCardUtils.async(loginCard -> {
                if (!loginCard.userAdmin) {
                    var err = new TGS_FileHtmlText().setBold(true).setHexcolor("FF0000")
                            .setText("HATA: Kullanıcı admin değil:<br>- Geri dönmek için basınız.");
                    var btn = TGC_ButtonUtils.createIcon(TGS_IconUtils.CLASS_BUG(), err.toString());
                    TGC_ClickUtils.add(btn, () -> TGC_BrowserWindowUtils.openSame(
                            route.setRouteApp(TGS_LibScaleMemUtils.cloud()).delMdl().toUrl())
                    );
                    TGC_LibBootGUIBodyStatic.setToCenter(btn);
                    d.ce("onModuleLoad", "loginCard", err);
                    return;
                }
                App.warVersion = warVersion;
                App.domainCard = domainCard;
                App.loginCard = loginCard;
                runAcc2Url.run();
            });
        },
                new AppModuleDefault(),
                new AppModuleCanvas()
        );
    }
}
