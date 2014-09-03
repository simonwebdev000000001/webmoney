package diploma.webmoney;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.*;
import java.text.NumberFormat;

import javax.imageio.ImageIO;
import javax.swing.*;

public class Exchange {
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

	public Exchange() {

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
		dialog = new JDialog(f, "Exchange", true);
		f.getContentPane().add(createMyP());
		f.pack();
		f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		f.setVisible(false);

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
		jp1.add("West", new JLabel(new ImageIcon("14.png")));

		JPanel jp2 = new JPanel();
		jp2.setBackground(Color.WHITE);
		jp2.setLayout(new GridLayout(2, 0));
		JLabel jl3 = new JLabel("Ecxchange your money");
		jl3.setFont(new Font("Verdana", Font.BOLD, 12));
		jp2.add(jl3);
		JLabel jl2 = new JLabel(
				"Choise the purse in wich you want outwit an ecxchange");
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
		JLabel jl7 = new JLabel("     a purse wich will recieve funds:  ");
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
		petList1 = new JComboBox(list);
		jl1.add(petList1);
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
			dialog.setIconImage(ImageIO.read(new File("16.png")
					.getAbsoluteFile()));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		dialog.pack();
		dialog.setVisible(true);
		return optionPane;

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

	public Double countExchange() {
		Connection con = null;
		PreparedStatement st = null;
		ResultSet resulset = null;
		Double g = 0.0d;
		try {
			String t = (1 + petList.getSelectedIndex()) + "";
			try {

				Class.forName("com.mysql.jdbc.Driver");
				con = DriverManager.getConnection(
						"jdbc:mysql://localhost:3306/WebMoney", "root", "");
				st = con.prepareStatement("select * from "
						+ Start.getNameOfTable());
				resulset = st.executeQuery();
			} catch (SQLException e1) {
				e1.getStackTrace();
			} catch (Exception e1) {
				e1.getStackTrace();
			}
			while (resulset.next()) {
				if (resulset.getString(1).equals(t)) {
					g = Double.parseDouble(resulset.getString(3));
					break;

				}
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return g;
	}

	public Double countExchangeFrom2() {
		Connection con = null;
		PreparedStatement st = null;
		ResultSet resulset = null;
		Double g = 0.0d;
		try {
			String t = (1 + petList1.getSelectedIndex()) + "";
			try {

				Class.forName("com.mysql.jdbc.Driver");
				con = DriverManager.getConnection(
						"jdbc:mysql://localhost:3306/WebMoney", "root", "");
				st = con.prepareStatement("select * from "
						+ Start.getNameOfTable());
				resulset = st.executeQuery();
			} catch (SQLException e1) {
				e1.getStackTrace();
			} catch (Exception e1) {
				e1.getStackTrace();
			}
			while (resulset.next()) {
				if (resulset.getString(1).equals(t)) {
					g = Double.parseDouble(resulset.getString(3));
					break;

				}
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return g;
	}

	public void setDataToDataBase() {
		if (petList1.getSelectedIndex() != petList.getSelectedIndex()) {
			if (!txt1.getText().equals("") && !txt.getText().equals("")) {
				String s = (String) petList.getSelectedItem();
				String s1 = (String) petList1.getSelectedItem();
				Double currentHaveOnFirstPurse = countExchange();
				// System.out.println(currentHaveOnFirstPurse);
				// Double currentFundsOnsecondPurse = countExchangeFrom2();
				if (currentHaveOnFirstPurse > Double.parseDouble(txt.getText())) {
					String sql = null;
					Double give = 0.0d;
					Double take = 0.0d;
					take = new BigDecimal(currentHaveOnFirstPurse
							- Double.parseDouble(txt.getText())).setScale(2,
							RoundingMode.UP).doubleValue();
					if (s.substring(0, 1).equals("U")) {
						if (s1.substring(0, 1).equals("Z")) {
							give = countExchangeFrom2()
									+ new BigDecimal(Double.parseDouble(txt
											.getText()) / 10.9).setScale(2,
											RoundingMode.UP).doubleValue();
						} else if (s1.substring(0, 1).equals("E")) {
							give = countExchangeFrom2()
									+ new BigDecimal(Double.parseDouble(txt
											.getText()) / 14.7).setScale(2,
											RoundingMode.UP).doubleValue();
						} else if (s1.substring(0, 1).equals("R")) {
							give = countExchangeFrom2()
									+ new BigDecimal(Double.parseDouble(txt
											.getText()) * 2.37).setScale(2,
											RoundingMode.UP).doubleValue();
						} else {
							JOptionPane.showConfirmDialog(null,
									"you cann`t catch exchange in same purse",
									"Warning!!!", JOptionPane.WARNING_MESSAGE);
							return;
						}

					} else if (s.substring(0, 1).equals("Z")) {
						if (s1.substring(0, 1).equals("U")) {
							give = countExchangeFrom2()
									+ new BigDecimal(Double.parseDouble(txt
											.getText()) * 10.3).setScale(2,
											RoundingMode.UP).doubleValue();
						} else if (s1.substring(0, 1).equals("E")) {
							give = countExchangeFrom2()
									+ new BigDecimal(Double.parseDouble(txt
											.getText()) * 0.73).setScale(2,
											RoundingMode.UP).doubleValue();
						} else if (s1.substring(0, 1).equals("R")) {
							give = countExchangeFrom2()
									+ new BigDecimal(Double.parseDouble(txt
											.getText()) * 34.12).setScale(2,
											RoundingMode.UP).doubleValue();
						} else {
							JOptionPane.showConfirmDialog(null,
									"you cann`t catch exchange in same purse",
									"Warning!!!", JOptionPane.WARNING_MESSAGE);
							return;
						}

					} else if (s.substring(0, 1).equals("E")) {
						if (s1.substring(0, 1).equals("U")) {
							give = countExchangeFrom2()
									+ new BigDecimal(Double.parseDouble(txt
											.getText()) * 13.5).setScale(2,
											RoundingMode.UP).doubleValue();
						} else if (s1.substring(0, 1).equals("Z")) {
							give = countExchangeFrom2()
									+ new BigDecimal(Double.parseDouble(txt
											.getText()) * 1.36).setScale(2,
											RoundingMode.UP).doubleValue();
						} else if (s1.substring(0, 1).equals("R")) {
							give = countExchangeFrom2()
									+ new BigDecimal(Double.parseDouble(txt
											.getText()) * 38.12).setScale(2,
											RoundingMode.UP).doubleValue();
						} else {
							JOptionPane.showConfirmDialog(null,
									"you cann`t catch exchange in same purse",
									"Warning!!!", JOptionPane.WARNING_MESSAGE);
							return;
						}
					} else if (s.substring(0, 1).equals("R")) {
						if (s1.substring(0, 1).equals("U")) {
							give = countExchangeFrom2()
									+ new BigDecimal(Double.parseDouble(txt
											.getText()) * 0.75).setScale(2,
											RoundingMode.UP).doubleValue();
						} else if (s1.substring(0, 1).equals("Z")) {
							give = countExchangeFrom2()
									+ new BigDecimal(Double.parseDouble(txt
											.getText()) * 0.028).setScale(2,
											RoundingMode.UP).doubleValue();
						} else if (s1.substring(0, 1).equals("E")) {
							give = countExchangeFrom2()
									+ new BigDecimal(Double.parseDouble(txt
											.getText()) * 0.021).setScale(2,
											RoundingMode.UP).doubleValue();
						} else {
							JOptionPane.showConfirmDialog(null,
									"you cann`t catch exchange in same purse",
									"Warning!!!", JOptionPane.WARNING_MESSAGE);
							return;
						}

					}

					sql = "UPDATE `WebMoney`.`" + Start.getNameOfTable()
							+ "` SET `summa` = '" + give + "' WHERE `"
							+ Start.getNameOfTable() + "`.`id` ="
							+ (petList1.getSelectedIndex() + 1) + " LIMIT 1 ;";
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
						dialog.setVisible(false);
						WebMoney.createPurseP().repaint();

					} catch (SQLException e1) {
						e1.getStackTrace();

					} catch (Exception e1) {
						e1.getStackTrace();
					}

					String sql1 = "UPDATE `WebMoney`.`"
							+ Start.getNameOfTable() + "` SET `summa` = '"
							+ take + "' WHERE `" + Start.getNameOfTable()
							+ "`.`id` =" + (petList.getSelectedIndex() + 1)
							+ " LIMIT 1 ;";
					Connection con1 = null;
					Statement st1 = null;
					ResultSet resulset1 = null;

					try {
						Class.forName("com.mysql.jdbc.Driver");
						con1 = DriverManager.getConnection(
								"jdbc:mysql://localhost:3306/WebMoney", "root",
								"");
						st1 = (Statement) con.createStatement();
						st1.executeUpdate(sql1);

						dialog.setVisible(false);
						WebMoney.createPurseP().repaint();

					} catch (SQLException e1) {
						e1.getStackTrace();

					} catch (Exception e1) {
						e1.getStackTrace();
					}
				} else {
					JOptionPane.showConfirmDialog(null,
							"you don`t have such founds", "Warning!!!",
							JOptionPane.WARNING_MESSAGE);

				}

			} else {
				JOptionPane.showConfirmDialog(null,
						"please, choise the summ or select a check-code  ",
						"Warning!!!", JOptionPane.WARNING_MESSAGE);
			}

		} else {
			JOptionPane.showConfirmDialog(null,
					"you try exchange an funds in the same purse",
					"Warning!!!", JOptionPane.ERROR_MESSAGE);
		}

		String sql = "UPDATE `WebMoney`.`" + Start.getNameOfTable()
				+ "` SET `summa` = '" + 10 + "' WHERE `"
				+ Start.getNameOfTable() + "`.`id` =" + hfd + " LIMIT 1 ;";

		Connection con = null;
		Statement st = null;
		ResultSet resulset = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/WebMoney", "root", "");
			st = (Statement) con.createStatement();
			st.executeUpdate(sql);

		} catch (SQLException e1) {
			e1.getStackTrace();
		} catch (Exception e1) {
			e1.getStackTrace();
		}
	}
	private int countg=0;
	public void updateData(){
		
			 Connection con= null;
			 PreparedStatement st = null;
			 ResultSet resulset = null;
			
			try {
				Class.forName("com.mysql.jdbc.Driver");
			
			 con = DriverManager.getConnection("jdbc:mysql://localhost:3306/WebMoney", "root", "");
//			 System.out.println(Start.getNameOfTable());
			  st=con.prepareStatement("SELECT * FROM `"+Start.getNameOfTable()+"` LIMIT 0 , 30");
			 
			  resulset =  st.executeQuery();
//			  System.out.println("3");
			 
			  while (resulset.next() && countg <=sizeOfList() ) {
				  
				 if(Integer.parseInt(resulset.getString(1)) < sizeOfList()){
//				  fillDataIfExist(WebMoney.getData(), resulset.getString(3));
					 WebMoney.getData()[countg][1]=resulset.getString(3); 
				  countg++;
				  }
			  }
			
		
			}catch (SQLException e1) {
				e1.printStackTrace();
			}catch (Exception e1) {
				e1.printStackTrace();
			}
		
	
	}
// public void fillDataIfExist(Object[][]t,Object g){
//	
//		t[countg][2]=g;
//	 
// }
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
