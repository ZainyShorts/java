
package carrental;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;

public class DeleteCustomer extends JFrame implements ActionListener {
    
    Choice carReg;
    JButton delete, back;
    
    DeleteCustomer() {
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
        JLabel labelempId = new JLabel("Customer Id no :");
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
        
        JLabel lblname = new JLabel();
        lblname.setBounds(200, 100, 100, 30);
        add(lblname);
        
        JLabel labelphone = new JLabel("Customer Address :");
        labelphone.setForeground(Color.RED);
        labelphone.setBounds(50, 150, 100, 30);
        add(labelphone);
        
        JLabel lblphone = new JLabel();
        lblphone.setBounds(200, 150, 100, 30);
        add(lblphone);
        
        JLabel pp = new JLabel("Customer Address :");
        pp.setForeground(Color.RED);
        pp.setBounds(50, 200, 100, 30);
        add(labelphone);
        
        JLabel ppval = new JLabel();
        ppval.setBounds(200, 200, 100, 30);
        add(ppval);
        
        
        
        try {
            database c = new database();
            String query = "select * from customer where cid = '"+carReg.getSelectedItem()+"'";
            ResultSet rs = c.s.executeQuery(query);
            while(rs.next()) {
                lblname.setText(rs.getString("cname"));
                lblphone.setText(rs.getString("caddress"));
                ppval.setText(rs.getString("cphone"));
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
                        lblname.setText(rs.getString("cname"));
                        lblphone.setText(rs.getString("caddress"));
                        ppval.setText(rs.getString("cphone"));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        
        delete = new JButton("Delete");
        delete.setBounds(80, 300, 100,30);
        delete.setBackground(Color.WHITE);
        delete.setForeground(Color.RED);
        delete.addActionListener(this);
        add(delete);
        
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
        if (ae.getSource() == delete) {
            try {
                database c = new database();
                String query = "delete from customer where cid = '"+carReg.getSelectedItem()+"'";
                c.s.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Customer Info Deleted Sucessfully");
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
        new DeleteCustomer();
    }
}