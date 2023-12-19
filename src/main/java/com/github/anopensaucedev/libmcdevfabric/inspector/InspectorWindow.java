package com.github.anopensaucedev.libmcdevfabric.inspector;

import javax.swing.*;
import java.awt.*;

public class InspectorWindow {

    JFrame inspector = new JFrame("Inspector");

    void SetupInspectorWindow(){
        inspector.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        inspector.getContentPane().add(new Label("haha"));
        inspector.pack();
        inspector.setVisible(true);
    }
}
