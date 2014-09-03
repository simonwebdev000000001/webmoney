package diploma.webmoney;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Arc2D;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.*;
import java.text.NumberFormat;

/**
 * Created by Админ on 12.07.2014.
 */
public class Transmit {
    private JLabel label;
    private JFrame f;
    final JDialog dialog;
    private int count = 0;
    private int countId = 0;
    int hfd;
    private Connection con = null;
    private PreparedStatement st = null;
    private ResultSet resulset = null;
    private JComboBox petList;
    private JComboBox petList1;
    private Double currentFounds;
    private JFormattedTextField txt;
    private JTextField txt1;
    private JTextField txt2;
    private String nameGivePurse;

    public Transmit() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/WebMoney", "root", "");
            st = con.prepareStatement("select * from " + Start.getNameOfTable());
            resulset = st.executeQuery();
        } catch (SQLException e1) {
            e1.getStackTrace();
        } catch (Exception e1) {
            e1.getStackTrace();
        }

        f = new JFrame();
        dialog = new JDialog(f, "Transmit", true);
        f.getContentPane().add(createMyP());
        f.pack();
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.setVisible(false);
    }

    public static void main(String[] args) {
        new Transmit();
    }

    public JPanel createMyP() {
        JPanel optionPane = new JPanel();

        optionPane.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0,
                Color.GRAY));
        optionPane.setBackground(Color.WHITE);
        optionPane.setLayout(new BorderLayout());
        JPanel jp1 = new JPanel();
        jp1.setBackground(Color.WHITE);
        jp1.setLayout(new BorderLayout());
        jp1.add("West", new JLabel(new ImageIcon("15.png")));

        JPanel jp2 = new JPanel();
        jp2.setBackground(Color.WHITE);
        jp2.setLayout(new GridLayout(2, 0));
        JLabel jl3 = new JLabel("Transmit your money");
        jl3.setFont(new Font("Verdana", Font.BOLD, 12));
        jp2.add(jl3);
        JLabel jl2 = new JLabel(
                "Choise the purse in wich you want transmit money");
        jl2.setFont(new Font("Verdana", Font.ITALIC, 10));
        jp2.add(jl2);

        jp1.add("Center", jp2);
        optionPane.add(jp1);

        JPanel optionPane1 = new JPanel();
        optionPane1.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0,
                Color.GRAY));
        // optionPane1.setLayout(new BorderLayout());
        optionPane1.setPreferredSize(new Dimension(10, 40));

        JButton jb1 = new JButton("Next");

        jb1.setContentAreaFilled(false);
        jb1.setFocusable(false);
        jb1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        optionPane1.add(jb1);

        JPanel optionPane3 = new JPanel();
        optionPane3.setLayout(new BorderLayout());

        JPanel jl = new JPanel();
        jl.setLayout(new GridLayout(4, 0));
        JLabel jl4 = new JLabel("  a purse with wich withdraw funds:");
        jl4.setFont(new Font("Verdana", Font.ROMAN_BASELINE, 10));
        jl.add(jl4);
        JLabel jl7 = new JLabel("     a purse  wich will recieve funds:  ");
        jl7.setFont(new Font("Verdana", Font.ROMAN_BASELINE, 10));
        jl.add(jl7);
        JLabel jl5 = new JLabel("                                 on summ:");
        jl5.setFont(new Font("Verdana", Font.ROMAN_BASELINE, 10));
        jl.add(jl5);
        JLabel jl6 = new JLabel("               name of the operation:");
        jl6.setFont(new Font("Verdana", Font.ROMAN_BASELINE, 10));
        jl.add(jl6);

        JPanel jl1 = new JPanel();
        jl1.setLayout(new GridLayout(4, 0));
        String[] list = countTheG();

        petList = new JComboBox(list);
        petList.setBackground(Color.WHITE);
        jl1.add(petList);
        txt2 = new JTextField();
        txt2 = new JFormattedTextField();
        jl1.add(txt2);
        NumberFormat nf = NumberFormat.getNumberInstance();
        txt = new JFormattedTextField(nf);
        jl1.add(txt);
        txt1 = new JTextField();
        jl1.add(txt1);
        jb1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                setDataToDataBase();
                updateData();
                WebMoney.createPurseP().repaint();

            }
        });
        optionPane3.add(jl1);
        optionPane3.add("West", jl);

        dialog.setPreferredSize(new Dimension(430, 250));
        dialog.setAutoRequestFocus(true);
        dialog.setResizable(false);
        dialog.setLayout(new BorderLayout());
        dialog.add("Center", optionPane3);
        dialog.add("North", optionPane);
        dialog.add("South", optionPane1);
        dialog.setLocation(250, 120);
        try {
            dialog.setIconImage(ImageIO.read(new File("17.png")
                    .getAbsoluteFile()));
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        dialog.pack();
        dialog.setVisible(true);
        return optionPane;

    }

    public int sizeOfList() {
        int count1 = 0;
        Connection con = null;
        PreparedStatement st = null;
        ResultSet resulset = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/WebMoney", "root", "");
            st = con.prepareStatement("select * from " + Start.getNameOfTable());
            resulset = st.executeQuery();

            try {

                while (resulset.next()) {
                    count1 = Integer.parseInt(resulset.getString(1));
                    // setCount1(count1);
                }
            } catch (Exception e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }

        } catch (SQLException e1) {

        } catch (Exception e1) {

        }
        return count1;
    }

    private int j = 0;

    public String[] countTheG() {
        int gf = 0;

        // Replenish.setCount1(3);
        String[] str = new String[sizeOfList()];

        try {
            while (resulset.next()) {

                fillList(str, resulset.getString(4));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return str;
    }

    public void fillList(String[] s, String g) {
        for (int i = j; i < s.length; i++) {
            s[i] = g;
            j++;
            return;
        }
    }
private int numberOfPurse;
    private boolean checkThePurse(String purse, String nameOfTable) {

        try {
            Basa b = new Basa(nameOfTable);
            b.setResulset(b.getSt().executeQuery());
            while (b.getResulset().next()) {
                nameGivePurse = b.getResulset().getString(4);
                if (nameGivePurse.equals(purse)) {
                    numberOfPurse = Integer.parseInt(b.getResulset().getString(1));
                    return true;
                }
                // setCount1(count1);
            }
        } catch (SQLException e1) {
            e1.getStackTrace();

        } catch (Exception e1) {
            e1.getStackTrace();
        }

        return false;
    }

    public Double countExchange() {

        Double g = 0.0d;
            String t = (1 + petList.getSelectedIndex()) + "";
            try {
                Basa b = new Basa(Start.getNameOfTable());
                b.setResulset(b.getSt().executeQuery());


            while (b.getResulset().next()) {
                if (b.getResulset().getString(1).equals(t)) {
                    g = Double.parseDouble(b.getResulset().getString(3));
                    break;

                }
            }
                System.out.println(t+"  "+g);
        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        return g;
    }
   private String v;
    public Double countExchange(String name) {

        Double g = 0.0d;
        try {
            Basa b = new Basa(name);
            b.setResulset(b.getSt().executeQuery());

            while (b.getResulset().next()) {
                g=Double.parseDouble(b.getResulset().getString(3));
                if (b.getResulset().getString(4).equals(txt.getText())) {
                    break;

                }

            }
            System.out.println("  "+g);
        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        return g;
    }
    private Double fdd;
    private void changePreviousPurse() {
        String sql = null;
        double f = fdd - Double.parseDouble(txt.getText())-Double.parseDouble(txt.getText())*0.8;
        sql = "UPDATE `WebMoney`.`"
                + Start.getNameOfTable() + "` SET `summa` = '"
                + f + "' WHERE `" + Start.getNameOfTable()
                + "`.`id` =" + (petList.getSelectedIndex() + 1)
                + " LIMIT 1 ;";
        Connection con = null;
        Statement st = null;
        ResultSet resulset = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/WebMoney", "root",
                    "");
            st = (Statement) con.createStatement();
            st.executeUpdate(sql);
        } catch (SQLException e1) {
            e1.getStackTrace();

        } catch (Exception e1) {
            e1.getStackTrace();
        }
    }

    private void checkTakingPurse() {
        String sql = null;
        double f = countExchange(v) + Double.parseDouble(txt.getText());
        sql = "UPDATE `WebMoney`.`"
                + v + "` SET `summa` = '"
                + f + "' WHERE `" + v
                + "`.`id` =" + numberOfPurse
                + " LIMIT 1 ;";
        Connection con = null;
        Statement st = null;
        ResultSet resulset = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/WebMoney", "root",
                    "");
            st = (Statement) con.createStatement();
            st.executeUpdate(sql);
        } catch (SQLException e1) {
            e1.getStackTrace();

        } catch (Exception e1) {
            e1.getStackTrace();
        }
    }



    public void setDataToDataBase() {
        Connection con = null;
        Statement st = null;
        ResultSet resulset = null;
        fdd = countExchange();
        try {
            Basa b = new Basa();
            b.setResulset(b.getSt().executeQuery());
            int jh=0;
            while (b.getResulset().next()) {
                v = b.getResulset().getString(16);
               if (checkThePurse(txt2.getText(), v)) {
                   if(txt2.getText().substring(0, 1).equals(petList.getSelectedItem().toString().substring(0, 1))){
                        if(fdd>0.0 && fdd> Double.parseDouble(txt.getText())){
                    changePreviousPurse();
                    checkTakingPurse();
                            dialog.setVisible(false);
                            WebMoney.createPurseP().repaint();
                    break;
                        }
                        else{
                            JOptionPane.showConfirmDialog(null,
                                    "you cann`t transmit more than you have",
                                    "Warning!!!", JOptionPane.WARNING_MESSAGE);
                            return;
                        }
                    }else{
                        JOptionPane.showConfirmDialog(null,
                                "you cann`t transmit in other type of purse",
                                "Warning!!!", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                }

            else{JOptionPane.showConfirmDialog(null,
                    "we didn`t find the purse. Please check again right name of purse-giver",
                    "Warning!!!", JOptionPane.WARNING_MESSAGE);
            return;}}


        } catch (SQLException e1) {
            e1.getStackTrace();

        } catch (Exception e1) {
            e1.getStackTrace();
        }


    }

    private int countg = 0;

    public void updateData() {

        Connection con = null;
        PreparedStatement st = null;
        ResultSet resulset = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");

            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/WebMoney", "root", "");
//			 System.out.println(Start.getNameOfTable());
            st = con.prepareStatement("SELECT * FROM `" + Start.getNameOfTable() + "` LIMIT 0 , 30");

            resulset = st.executeQuery();
//			  System.out.println("3");

            while (resulset.next() && countg <= sizeOfList()) {

                if (Integer.parseInt(resulset.getString(1)) < sizeOfList()) {
//				  fillDataIfExist(WebMoney.getData(), resulset.getString(3));
                    WebMoney.getData()[countg][1] = resulset.getString(3);
                    countg++;
                }
            }


        } catch (SQLException e1) {
            e1.printStackTrace();
        } catch (Exception e1) {
            e1.printStackTrace();
        }


    }

}
