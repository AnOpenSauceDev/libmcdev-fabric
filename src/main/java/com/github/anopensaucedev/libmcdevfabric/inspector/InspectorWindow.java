package com.github.anopensaucedev.libmcdevfabric.inspector;

import com.github.anopensaucedev.libmcdevfabric.Debug;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InspectorWindow {



    public static void SetupInspectorWindow(){
        try {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }catch (Exception e){
            e.printStackTrace();
        }

        List InspectableList = new List();
        InspectableList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
            }
        });

        Debug.InternalLogger.info("creating inspector window...");
        JFrame inspector = new JFrame("Inspector");
        inspector.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        inspector.getContentPane().add(new Label("libMCdev Inspector"),BorderLayout.NORTH);
        inspector.getContentPane().add(InspectableList,BorderLayout.NORTH);
        inspector.setSize(320,180);
        inspector.setLocationRelativeTo(null);
        inspector.setVisible(true);
    }

    public void getListElements(){

    }
}
