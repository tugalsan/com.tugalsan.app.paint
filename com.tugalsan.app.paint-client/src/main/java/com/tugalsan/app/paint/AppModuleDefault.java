package com.tugalsan.app.paint;

import com.google.gwt.user.client.ui.*;
import com.tugalsan.api.file.html.client.*;
import com.tugalsan.api.gui.client.browser.*;
import com.tugalsan.api.gui.client.card.*;
import com.tugalsan.api.gui.client.widget.*;
import com.tugalsan.api.log.client.*;
import com.tugalsan.lib.boot.client.*;
import com.tugalsan.lib.resource.client.*;
import com.tugalsan.lib.scale.client.*;

public class AppModuleDefault extends TGC_LibBootModulePanel {

    final private static TGC_Log d = TGC_Log.of(AppModuleDefault.class);

    @Override
    public String getBrowserTitle() {
        return TGC_LibBootGUITitleUtils.browserTitle_domain_txt_user(
                App.domainCard, App.loginCard, App.route,
                AppStrings.TITLE_AppModuleDefault(),
                null
        );
    }

    @Override
    public String getBodyTitle() {
        return new TGS_FileHtmlText().setText(
                TGC_LibBootGUITitleUtils.bodyTitle_cloudlink_sub_user(
                        App.loginCard, App.route,
                        AppStrings.TITLE_AppModuleDefault(),
                        null
                )
        ).setBold(true).toString();
    }

    @Override
    public void run() {
        d.ci("run", "#0");
        loadParams();
        d.ci("run", "#1");
        createWidgets();
        d.ci("run", "#2");
        createPops();
        d.ci("run", "#3");
        configInit();
        d.ci("run", "#4");
        configActions();
        d.ci("run", "#5");
        configFocus();
        d.ci("run", "#6");
        configLayout();
        d.ci("run", "#7");
    }

    @Override
    public void loadParams() {
    }

    @Override
    public void createWidgets() {
        var search_png = TGS_LibResourceUtils.common.res.image.search.icon_png().toString();
        scroll = new ScrollPanel();
        var pack = TGC_CardUtils.create(scroll, null, search_png, cards -> {
            cards.add(new TGC_Card(
                    TGS_LibResourceUtils.png(TGS_LibScaleMemUtils.database().app + "-col-add"), null,
                    "Echo",
                    () -> TGC_BrowserWindowUtils.openNew(
                            App.route.setMdl(AppModuleCanvas.class.getSimpleName()).toUrl())
            ));
        });
        tbInput = pack.value0;
        pnlCards = pack.value1;
    }
    private TextBox tbInput;
    private FlowPanel pnlCards;
    private ScrollPanel scroll;

    @Override
    public void createPops() {
    }

    @Override
    public void configInit() {
    }

    @Override
    public void configActions() {
    }

    @Override
    public void configFocus() {
    }

    @Override
    public void configLayout() {
        TGC_LibBootGUIBody.buttonHolderCenter.add(tbInput);

        scroll.setWidget(pnlCards);
        TGC_ScrollUtils.addScrollToTop(scroll);
        widget = scroll;
    }
    public Widget widget;

    @Override
    public Widget getWidget() {
        return widget;
    }
}
