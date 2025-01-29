package com.tugalsan.app.paint;

import com.tugalsan.api.file.common.client.TGS_FileCommonFavIcon;
import com.tugalsan.api.log.server.TS_Log;
import com.tugalsan.api.log.server.TS_LogUtils;
import com.tugalsan.api.servlet.gwt.server.*;
import com.tugalsan.api.servlet.url.server.TS_SURLWebServlet;
import com.tugalsan.api.string.client.TGS_StringUtils;
import com.tugalsan.api.thread.server.sync.TS_ThreadSyncTrigger;
import com.tugalsan.api.tomcat.server.TS_TomcatPathUtils;
import com.tugalsan.app.paint.AppSGEEcho;
import com.tugalsan.lib.boot.server.TS_LibBootPathUtils;
import com.tugalsan.lib.boot.server.TS_LibBootUtils;
import java.time.Duration;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class AppServlet implements ServletContextListener {

    final private static TS_Log d = TS_Log.of(AppServlet.class);
    final public static TGS_FileCommonFavIcon favIconSpi = TGS_FileCommonFavIcon.ofTxt("🛢️", null, false);
    final public static TGS_FileCommonFavIcon favIconBug = TGS_FileCommonFavIcon.ofTxt("🐛", null, false);
    public static String APP_NAME;

    @Override
    public void contextInitialized(ServletContextEvent evt) {
        APP_NAME = TS_TomcatPathUtils.getWarNameLabel(evt);
        TS_LogUtils.MAP = txt -> TGS_StringUtils.cmn().concat("[", APP_NAME, "] ", txt);
        var contextKillTrigger = TS_SURLWebServlet.killTrigger = TS_ThreadSyncTrigger.of();
        var appName = TS_TomcatPathUtils.getWarNameLabel(evt);
        var pathServletConfig = TS_LibBootPathUtils.dirDat().value().resolve("cfg").resolve(appName);
        var u_sConfig = TS_SGWTConfig.of(pathServletConfig, appName);
        if (u_sConfig.isExcuse()){
            d.ce("contextInitialized", u_sConfig.excuse().getMessage());
            return;
        }
        TS_SGWTWebServlet.config = u_sConfig.value();
        TS_LibBootUtils.contextInitializedAsyncRun(contextKillTrigger,
                Duration.ofMinutes(10),
                evt, "autosqlweb", "ASW",
                favIconSpi, favIconBug,
                killTrigger -> {
                    TS_SGWTExecutorList.add(new AppSGEEcho()
                    );
                }
        );
    }

    @Override
    public void contextDestroyed(ServletContextEvent evt) {
        TS_LibBootUtils.contextDestroyed(evt);
    }
}
