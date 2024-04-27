
package carrental;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;

public class EditCustomer extends JFrame implements ActionListener {
    
    Choice carReg;
    JButton Edit, back;
    
    
    JTextField modelNo;
    JTextField brandName;
    JTextField phoneField;
    
    EditCustomer() {
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
        JLabel labelempId = new JLabel("Customer Id :");
        labelempId.setForeground(Color.RED);
        labelempId.setBounds(50, 50, 100, 30);
        add(labelempId);
        
        carReg = new Choice();
        carReg.setBounds(200, 50, 150, 30);
        add(carReg);
        
        try {
            database c = new database();
            String query = "select * from customer";
            ResultSet rs = c.s.executeQuery(query);
            while(rs.next()) {
                carReg.add(rs.getString("cid"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        JLabel labelname = new JLabel("Customer Name :");
        labelname.setForeground(Color.RED);
        labelname.setBounds(50, 100, 100, 30);
        add(labelname);
        
        modelNo = new JTextField();
        modelNo.setBounds(200, 100, 100, 30);
        add(modelNo);
        
        
        JLabel labelphone = new JLabel("CustomerAddress :");
        labelphone.setForeground(Color.RED);
        labelphone.setBounds(50, 150, 100, 30);
        add(labelphone);
        
        brandName = new JTextField();
        brandName.setBounds(200, 150, 100, 30);
        add(brandName);
        
         JLabel phone = new JLabel("Phone :");
        phone.setForeground(Color.RED);
        phone.setBounds(50, 200, 100, 30);
        add(phone);
        
        phoneField = new JTextField();
        phoneField.setBounds(200, 200, 100, 30);
        add(phoneField);
        
        
        
        try {
            database c = new database();
            String query = "select * from customer where cid = '"+carReg.getSelectedItem()+"'";
            ResultSet rs = c.s.executeQuery(query);
            while(rs.next()) {
                modelNo.setText(rs.getString("cname"));
                brandName.setText(rs.getString("caddress"));
                phoneField.setText(rs.getString("cphone"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        carReg.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent ie) {
                try {
                    database c = new database();
                    String query = "select * from customer where cid = '"+carReg.getSelectedItem()+"'";
                    ResultSet rs = c.s.executeQuery(query);
                    while(rs.next()) {
                        modelNo.setText(rs.getString("cname"));
                        brandName.setText(rs.getString("caddress"));
                        phoneField.setText(rs.geetString("cphone"));
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
                
                database c = new database();
//                String query = "update addcar  set brand = '"+brandName+"' ,  model = '"+modelNo+"' where registrationnum = '"+carReg.getSelectedItem()+"'";
                String query = "UPDATE customer SET cname = '"+brandName.getText()+"', caddress = '"+modelNo.getText()+"' , cphone = '"+phoneField.getText()+"' WHERE cid = '"+carReg.getSelectedItem()+"';   ";
                c.s.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Customer Info updated Sucessfully");
                setVisible(false);
                new Main();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            setVisible(false);
            new Cars();
        }
    }

    public static void main(String[] args) {
        new Ediit();
    }
}