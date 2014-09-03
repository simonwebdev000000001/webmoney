package diploma.webmoney;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import java.sql.*;

public class DataTable  {
	private JPanel jp;
	private Basa b;
	private JFrame jf;

	public DataTable()  {
		createMyPanel();
		 jf = new JFrame("Information about me");
		jf.setMinimumSize(new Dimension(450, 400));
		jf.setLocation(250, 100);
		jf.setContentPane(jp);
		jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		jf.pack();
		jf.setVisible(true);

	}

	public void createMyPanel()  {
		String[]f= new String[11];
		//-----connect with DataBase
//		Connection con = null;
//		ResultSet resulset = null;
//		PreparedStatement st = null;
//		try {
//			Class.forName("com.mysql.jdbc.Driver");
//		} catch (ClassNotFoundException e1) {
//			// TODO Auto-generated catch block
//			System.out.println("not found a driver");
//		}
//		 try {
//			con = DriverManager.getConnection(
//					"jdbc:mysql://localhost:3306/WebMoney", "root", "");
//		} catch (SQLException e1) {
//			// TODO Auto-generated catch block
//			System.out.println("failed in connection");
//		}
//		 try {
//			st = con.prepareStatement("select * from dataTable");
//		} catch (SQLException e1) {
//			// TODO Auto-generated catch block
//			System.out.println("cann`t take data from DB");
//		}
//		 try {
//			resulset = st.executeQuery();
//					while(resulset.next() ){
//						if(resulset.getString(1).equals("1")){
////							while(resulset.next() ){
//			f[0]=resulset.getString(11);
//			f[1]=resulset.getString(4);
//			f[2]=resulset.getString(3);
//			f[3]=resulset.getString(2);
//			f[4]=resulset.getString(6);
//			f[5]=resulset.getString(5);
//			f[6]=resulset.getString(8);
//			f[7]=resulset.getString(7);
//			f[8]=resulset.getString(9);
//			f[9]=resulset.getString(10);
//						}
////					}
//					}
//		} catch (SQLException e1) {
//			// TODO Auto-generated catch block
//			System.out.println("((((");
//		}
	Start.getData();
	//paint a window
		jp = new JPanel();
		jp.setBackground(Color.WHITE);
		JPanel jp1 = new JPanel();
		jp1.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));
		jp1.setLayout(new BorderLayout());
	
		JLabel jl1 = new JLabel(new ImageIcon("01.png"));
		
		jp1.add("West",jl1);
		JLabel jl2 = new JLabel("Information about me");
		JPanel jp3 = new JPanel();
		jp3.setLayout(new GridLayout(2,0));
		jl2.setFont(new Font("Times newRoman", Font.BOLD, 12));
		jp3.add(jl2);
		JLabel jl3 = new JLabel("WMID:" + Start.getData()[0] + ", Your personal level");
		jl3.setFont(new Font("Times newRoman", Font.ROMAN_BASELINE, 12));
		jp3.add(jl3);
		jp1.add("Center",jp3);

		JPanel jp2 = new JPanel();
		jp2.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.GRAY));
		jp2.setMinimumSize(new Dimension(400,60));
		JButton jb = new JButton("Close");
		jb.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				jf.removeAll();
				jf.setVisible(false);
			}
		});
		jp2.add( jb);
		
		JTabbedPane main= new JTabbedPane();
		JPanel jp5 = new JPanel();
		jp5.setBackground(Color.WHITE);
		jp5.setLayout(new BorderLayout() );
		JPanel jp6 = new JPanel();
		jp6.setLayout(new GridLayout(6,0) );

		JLabel jl4 = new JLabel("               WMID:  ");
		jp6.add(jl4);
		JLabel jl5 = new JLabel("                  F.I.O:  ");
		jp6.add(jl5);
		JLabel jl6 = new JLabel("            Adress:  ");
		jp6.add(jl6);
		JLabel jl7 = new JLabel("         Contacts:  ");
		jp6.add(jl7);
		JLabel jl8 = new JLabel("                Level:  ");
		jp6.add(jl8);
		JLabel jl9 = new JLabel("  Credit status:   ");
		jp6.add(jl9);
		
		JTextField jt = new JTextField(30);
		jt.setText(Start.getData()[0]);
		JPanel jp7 = new JPanel();
		jp7.setBackground(Color.WHITE);
		jp7.setLayout(new GridLayout(6,0) );
		jp7.add(jt);
		JTextField jt1 = new JTextField();
		jt1.setText(Start.getData()[2]+" "+Start.getData()[1]+" "+Start.getData()[3]);
		jp7.add(jt1);
		JTextField jt2 = new JTextField();
		jt2.setText(Start.getData()[4]+", "+Start.getData()[5]);
		jp7.add(jt2);
		JTextField jt3 = new JTextField();
		jt3.setText(Start.getData()[6]+", "+Start.getData()[7]);
		jp7.add(jt3);
		JTextField jt4 = new JTextField();
		jt4.setText(Start.getData()[8]);
		jp7.add(jt4);
		JTextField jt5 = new JTextField();
		jt5.setText(Start.getData()[9]);
		jp7.add(jt5);
		
		jp5.add("West",jp6);
		jp5.add("Center",jp7);
		
		JPanel jp8 = new JPanel();
		jp6.setBackground(Color.WHITE);
		String[] str={"Data","Credit service"};
		main.add(str[0],jp5);
		main.add(str[1],jp8);
		jp.setLayout(new BorderLayout());
		jp.add("North", jp1);
		jp.add("Center",main);
		jp.add("South", jp2);
		
	}

	public static void main(String[] args) throws Exception {
		new DataTable();
	}

}
