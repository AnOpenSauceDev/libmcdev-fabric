package com.github.anopensaucedev.libmcdevfabric.inspector;

import com.github.anopensaucedev.libmcdevfabric.Debug;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class InspectorWindow {

    public static List InspectableAWTList = new List();
    public static JFrame inspector = new JFrame("Inspector");
    public static java.util.List<Inspectable> Inspectables = new ArrayList<>();

    public static Inspectable selected;

    public static void SetupInspectorWindow(){
        try {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }catch (Exception e){
            e.printStackTrace();
        }


        InspectableAWTList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        Debug.InternalLogger.info("creating inspector window...");

        inspector.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        inspector.getContentPane().add(new Label("libMCdev Inspector"),BorderLayout.NORTH);
        inspector.getContentPane().add(InspectableAWTList,BorderLayout.NORTH);

        InspectableAWTList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InspectableAWTList.getSelectedItem().toString();
            }
        });


        Container VarInfo = new Container();
        inspector.getContentPane().add(VarInfo);
        //variable modifier stuff

        inspector.setSize(320,180);
        inspector.setLocationRelativeTo(null);
        inspector.setVisible(true);
    }

    public static void updateInspector(){
        JSlider slider = new JSlider();
        inspector.add(new Label(selected.toString()));
        if(isNumber(selected)){
            inspector.add(slider);
        }else { // probably a boolean

        }
    }

    public static boolean isNumber(Object object){
        if(object instanceof Number){
            return true;
        }else {
            return false;
        }
    }


    public void updateContent(){

    }

    public static void AddInspectable(Inspectable I){
        Inspectables.add(I);
        updateInspectables();
    }

    public static void updateInspectables(){
        InspectableAWTList.removeAll();
        for (int i = 0; i < Inspectables.size(); i++) {
            InspectableAWTList.add(Inspectables.get(i).toString());
        }
    }

}
