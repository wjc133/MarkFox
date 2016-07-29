package com.elite.tools.markfox.client.bootstrap;

import com.elite.tools.markfox.client.ui.MainView;

public class BootStrap {

    public static void main(String[] args) {
        Config.configAll();

        new MainView();
    }

}
