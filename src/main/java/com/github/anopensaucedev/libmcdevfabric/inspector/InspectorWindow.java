package com.github.anopensaucedev.libmcdevfabric.inspector;

import com.github.anopensaucedev.libmcdevfabric.Debug;

import javax.swing.*;
import java.awt.*;

public class InspectorWindow {



    public static void SetupInspectorWindow(){
        Debug.InternalLogger.info("creating inspector window...");
        JFrame inspector = new JFrame("Inspector");
        inspector.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        inspector.getContentPane().add(new Label("haha"),BorderLayout.CENTER);
        inspector.setSize(320,180);
        inspector.setLocationRelativeTo(null);
        inspector.setVisible(true);
    }
}
