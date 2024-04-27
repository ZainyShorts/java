
package carrental;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;

public class Ediit extends JFrame implements ActionListener {
    
    Choice carReg;
    JButton Edit, back;
    
    
    JTextField modelNo;
    JTextField brandName;
    
    Ediit() {
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
        JLabel labelempId = new JLabel("Registration no :");
        labelempId.setForeground(Color.RED);
        labelempId.setBounds(50, 50, 100, 30);
        add(labelempId);
        
        carReg = new Choice();
        carReg.setBounds(200, 50, 150, 30);
        add(carReg);
        
        try {
            database c = new database();
            String query = "select * from addcar";
            ResultSet rs = c.s.executeQuery(query);
            while(rs.next()) {
                carReg.add(rs.getString("registrationnum"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        JLabel labelname = new JLabel("Model :");
        labelname.setForeground(Color.RED);
        labelname.setBounds(50, 100, 100, 30);
        add(labelname);
        
        modelNo = new JTextField();
        modelNo.setBounds(200, 100, 100, 30);
        add(modelNo);
        
        
        JLabel labelphone = new JLabel("Brand :");
        labelphone.setForeground(Color.RED);
        labelphone.setBounds(50, 150, 100, 30);
        add(labelphone);
        
        brandName = new JTextField();
        brandName.setBounds(200, 150, 100, 30);
        add(brandName);
        
        
        
        
        
        try {
            database c = new database();
            String query = "select * from addcar where registrationnum = '"+carReg.getSelectedItem()+"'";
            ResultSet rs = c.s.executeQuery(query);
            while(rs.next()) {
                modelNo.setText(rs.getString("model"));
                brandName.setText(rs.getString("brand"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        carReg.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent ie) {
                try {
                    database c = new database();
                    String query = "select * from addcar where registrationnum = '"+carReg.getSelectedItem()+"'";
                    ResultSet rs = c.s.executeQuery(query);
                    while(rs.next()) {
                        modelNo.setText(rs.getString("model"));
                        brandName.setText(rs.getString("brand"));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        
        Edit = new JButton("Edit");
        Edit.setBounds(80, 300, 100,30);
        Edit.setBackground(Color.WHITE);
        Edit.setForeground(Color.RED);
        Edit.addActionListener(this);
        add(Edit);
        
        back = new JButton("Back");
        back.setBounds(220, 300, 100,30);
        back.setBackground(Color.WHITE);
        back.setForeground(Color.RED);
        back.addActionListener(this);
        add(back);
        
        
        setSize(500, 400);
        setLocation(300, 150);
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == Edit) {
            try {
                System.out.print(brandName.getText());
                System.out.print(modelNo.getText());
                System.out.print(carReg.getSelectedItem());
                
                database c = new database();
//                String query = "update addcar  set brand = '"+brandName+"' ,  model = '"+modelNo+"' where registrationnum = '"+carReg.getSelectedItem()+"'";
                String query = "UPDATE addcar SET brand = '"+brandName.getText()+"', model = '"+modelNo.getText()+"' WHERE registrationnum = '"+carReg.getSelectedItem()+"';   ";
                c.s.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Car Info updated Sucessfully");
                setVisible(false);
                new Main();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            setVisible(false);
            new Main();
        }
    }

    public static void main(String[] args) {
        new Ediit();
    }
}