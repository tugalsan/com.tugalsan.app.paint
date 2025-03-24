package com.tugalsan.app.paint;

import com.google.gwt.user.client.ui.*;
import com.tugalsan.api.file.html.client.*;
import com.tugalsan.api.function.client.maythrow.uncheckedexceptions.TGS_FuncMTU;
import com.tugalsan.api.gui.client.click.*;
import com.tugalsan.api.gui.client.focus.*;
import com.tugalsan.api.gui.client.key.*;
import com.tugalsan.api.gui.client.panel.*;
import com.tugalsan.api.gui.client.widget.*;
import com.tugalsan.api.icon.client.*;
import com.tugalsan.api.log.client.*;
import com.tugalsan.api.servlet.gwt.client.TGC_SGWTCalller;
import com.tugalsan.lib.boot.client.*;
import com.tugalsan.app.paint.sg.*;

public class AppModuleCanvas extends TGC_LibBootModulePanel {

    final private static TGC_Log d = TGC_Log.of(AppModuleCanvas.class);

    @Override
    public String getBrowserTitle() {
        return TGC_LibBootGUITitleUtils.browserTitle_domain_txt_user(
                App.domainCard, App.loginCard, App.route,
                AppStrings.TITLE_AppModuleEcho(),
                null
        );
    }

    @Override
    public String getBodyTitle() {
        return new TGS_FileHtmlText().setText(
                TGC_LibBootGUITitleUtils.bodyTitle_cloud_app_sub_user(
                        App.loginCard, App.route,
                        AppStrings.TITLE_AppModuleDefault(),
                        AppStrings.TITLE_AppModuleEcho(),
                        null
                )
        ).setBold(true).toString();
    }

    @Override
    public void run() {
        loadParams();
        createWidgets();
        createPops();
        configInit();
        configActions();
        configLayout();
        configFocus();
        TGC_FocusUtils.setFocusAfterGUIUpdate(tbEchoInput);
    }
    private TextBox tbEchoInput;
    private PushButton btnEcho;
    private Label lblEchoOutput;

    @Override
    public void loadParams() {
    }

    @Override
    public void createWidgets() {
        tbEchoInput = new TextBox();
        btnEcho = TGC_ButtonUtils.createIcon(TGS_IconUtils.CLASS_CHECKMARK(), "Gönder");
        lblEchoOutput = new Label("");
    }

    @Override
    public void createPops() {
    }

    @Override
    public void configInit() {
    }

    final TGS_FuncMTU onEcho = () -> {
        TGC_SGWTCalller.async(new AppSGFEcho(tbEchoInput.getText()), reply -> {
            d.cr("onEcho", "repy", reply.getOutput_msg());
        });
    };

    @Override
    public void configActions() {
        TGC_ClickUtils.add(btnEcho, onEcho);
        TGC_KeyUtils.add(tbEchoInput, onEcho, null);
    }

    @Override
    public void configFocus() {
        TGC_FocusUtils.addKeyDown(btnEcho, nativeKeyCode -> {
            var filterFocusSides = new TGS_FocusSides4(tbEchoInput, null, null, null);
            TGC_FocusUtils.focusSide(btnEcho, filterFocusSides, nativeKeyCode);
        });
    }

    @Override
    public void configLayout() {
        Integer[] columnPercent = {25, 25, 25};
        
        
        Widget[] widgets = {
            tbEchoInput, btnEcho, lblEchoOutput
        };
        widget = TGC_PanelLayoutUtils.createGrid(TGC_PanelLayoutUtils.MAX_GRID_WIDTH(), columnPercent, widgets, true);
    }
    private Widget widget;

    @Override
    public Widget getWidget() {
        return widget;
    }
}
