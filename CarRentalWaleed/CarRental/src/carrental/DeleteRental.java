
package carrental;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;

public class DeleteRental extends JFrame implements ActionListener {
    
    Choice carReg;
    JButton delete, back;
    
    DeleteRental() {
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
            String query = "select * from mainrents";
            ResultSet rs = c.s.executeQuery(query);
            while(rs.next()) {
                carReg.add(rs.getString("Reg"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        JLabel labelname = new JLabel("Model :");
        labelname.setForeground(Color.RED);
        labelname.setBounds(50, 100, 100, 30);
        add(labelname);
        
        JLabel lblname = new JLabel();
        lblname.setBounds(200, 100, 100, 30);
        add(lblname);
        
        JLabel labelphone = new JLabel("Brand :");
        labelphone.setForeground(Color.RED);
        labelphone.setBounds(50, 150, 100, 30);
        add(labelphone);
        
        JLabel lblphone = new JLabel();
        lblphone.setBounds(200, 150, 100, 30);
        add(lblphone);
        
        
        
        try {
            database c = new database();
            String query = "select * from mainrents where Reg = '"+carReg.getSelectedItem()+"'";
            ResultSet rs = c.s.executeQuery(query);
            while(rs.next()) {
                lblname.setText(rs.getString("model"));
                lblphone.setText(rs.getString("brand"));
//                lblemail.setText(rs.getString("email"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        carReg.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent ie) {
                try {
                    database c = new database();
                    String query = "select * from mainrents where Reg = '"+carReg.getSelectedItem()+"'";
                    ResultSet rs = c.s.executeQuery(query);
                    while(rs.next()) {
                        lblname.setText(rs.getString("model"));
                        lblphone.setText(rs.getString("brand"));
//                        lblemail.setText(rs.getString("email"));
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
                String query = "delete from mainrents where reg = '"+carReg.getSelectedItem()+"'";
                c.s.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "CarRent Info Deleted Sucessfully");
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
        new DeleteRental();
    }
}