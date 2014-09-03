package diploma.webmoney;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.NumberFormat;

import javax.imageio.ImageIO;
import javax.swing.*;

public class Replenish {
	private JLabel label;
	private JFrame f;
	final JDialog dialog;
	private int count = 0;
	private int countId = 0;
	int hfd;
	private Double g = 0.00d;

	public JLabel getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = new JLabel(label);
	}

	public Replenish() {
		f = new JFrame();
		// f.setLocation(200, 150);
		// f.setMinimumSize(new Dimension(350, 270));
		// f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		dialog = new JDialog(f, "Replenish", true);
		f.getContentPane().add(createMyP());
		f.pack();
		f.setVisible(false);

	}

	public JPanel createMyP() {
		JPanel optionPane = new JPanel();
		optionPane.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0,
				Color.GRAY));
		optionPane.setBackground(Color.WHITE);
		optionPane.setLayout(new BorderLayout());
		JPanel jp1 = new JPanel();
		jp1.setLayout(new BorderLayout());
		jp1.add("West", new JLabel(new ImageIcon("12.jpg")));

		JPanel jp2 = new JPanel();
		jp2.setBackground(Color.WHITE);
		jp2.setLayout(new GridLayout(2, 0));
		JLabel jl3 = new JLabel("Replenish your purse");
		jl3.setFont(new Font("Verdana", Font.BOLD, 12));
		jp2.add(jl3);
		JLabel jl2 = new JLabel("Choise your purse and way of replenish");
		jl2.setFont(new Font("Verdana", Font.ITALIC, 10));
		jp2.add(jl2);

		jp1.add("Center", jp2);
		optionPane.add(jp1);

		JPanel optionPane1 = new JPanel();
		optionPane1.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0,
				Color.GRAY));
		// optionPane1.setLayout(new BorderLayout());
		optionPane1.setPreferredSize(new Dimension(10, 40));
		JButton jb = new JButton("Back");
		jb.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

			}
		});
		jb.setContentAreaFilled(false);
		jb.setFocusable(false);
		jb.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		JButton jb1 = new JButton("Next");

		jb1.setContentAreaFilled(false);
		jb1.setFocusable(false);
		jb1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		optionPane1.add(jb);
		optionPane1.add(jb1);

		JPanel optionPane3 = new JPanel();
		optionPane3.setLayout(new BorderLayout());

		JPanel jl = new JPanel();
		jl.setLayout(new GridLayout(4, 0));
		JLabel jl4 = new JLabel("  Choise purse:");
		jl4.setFont(new Font("Verdana", Font.ROMAN_BASELINE, 10));
		jl.add(jl4);
		JLabel jl7 = new JLabel("  the way of replenish:  ");
		jl7.setFont(new Font("Verdana", Font.ROMAN_BASELINE, 10));
		jl.add(jl7);
		JLabel jl5 = new JLabel("  on summ:");
		jl5.setFont(new Font("Verdana", Font.ROMAN_BASELINE, 10));
		jl.add(jl5);
		JLabel jl6 = new JLabel("  some of the code:");
		jl6.setFont(new Font("Verdana", Font.ROMAN_BASELINE, 10));
		jl.add(jl6);

		JPanel jl1 = new JPanel();
		jl1.setLayout(new GridLayout(4, 0));
		String[] list = { "Bank transfer", "other web-cash" };

		countTheG();

		String[] list1 = new String[sizeOfList()];

		for (int i = 0; i < WebMoney.getData().length; i++) {
			fillList(list1, i);
		}

		final JComboBox petList = new JComboBox(list1);
		petList.setBackground(Color.WHITE);
		jl1.add(petList);
		jl1.add(new JComboBox(list));
		NumberFormat nf = NumberFormat.getNumberInstance();
		final JFormattedTextField txt = new JFormattedTextField(nf);
		jl1.add(txt);
		final JTextField txt1 = new JTextField();
		jl1.add(txt1);

		jb1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				if (!txt.getText().equals("") || !txt1.getText().equals("")) {
					hfd = petList.getSelectedIndex() + 1;
					try {
						g = Double.parseDouble(txt.getText()) + countTheG();
					} catch (NumberFormatException e5) {
						JOptionPane.showConfirmDialog(null,
								"sorry, we cann`t make this transaction",
								"Warning!!!", JOptionPane.WARNING_MESSAGE);
					}
					Double g1 = 0.0d;
					g1 += Double.parseDouble(txt.getText());
					if (petList.getSelectedIndex() == 0) {
						WebMoney.fillElem(WebMoney.getData(),
								WebMoney.getData()[0][2], g1);
					} else if (petList.getSelectedIndex() == 1) {
						WebMoney.fillElem(WebMoney.getData(),
								WebMoney.getData()[1][2], g1);
					} else if (petList.getSelectedIndex() == 2) {
						WebMoney.fillElem(WebMoney.getData(),
								WebMoney.getData()[2][2], g1);
					} else if (petList.getSelectedIndex() == 3) {
						WebMoney.fillElem(WebMoney.getData(),
								WebMoney.getData()[3][2], g1);
					} else if (petList.getSelectedIndex() == 4) {
						WebMoney.fillElem(WebMoney.getData(),
								WebMoney.getData()[4][2], g1);
					} else {
						JOptionPane.showConfirmDialog(null,
								"sorry, not develop", "Warning!!!",
								JOptionPane.INFORMATION_MESSAGE);
					}
					dialog.setVisible(false);
					WebMoney.createPurseP().repaint();

				} else {
					JOptionPane.showConfirmDialog(null,
							"please, select summ or select code", "Warning!!!",
							JOptionPane.INFORMATION_MESSAGE);
				}

				String sql = "UPDATE `WebMoney`.`" + Start.getNameOfTable()
						+ "` SET `summa` = '" + g + "' WHERE `"
						+ Start.getNameOfTable() + "`.`id` =" + hfd
						+ " LIMIT 1 ;";

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

				} catch (Exception e1) {

				}
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
			dialog.setIconImage(ImageIO.read(new File("13.png")
					.getAbsoluteFile()));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		dialog.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				setLabel("Thwarted user attempt to close window.");
			}
		});

		dialog.pack();
		dialog.setVisible(true);
		return optionPane;
	}

	public Double countTheG() {
		Connection con = null;
		PreparedStatement st = null;
		ResultSet resulset = null;
		Double g = 0.0d;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/WebMoney", "root", "");
			st = con.prepareStatement("select * from " + Start.getNameOfTable());
			resulset = st.executeQuery();
			String str = hfd + "";
			try {
				while (resulset.next()) {
					if (resulset.getString(1).equals(str)) {
						g += Double.parseDouble(resulset.getString(3));
						break;
					}
				}
				while (resulset.next()) {
					count = Integer.parseInt(resulset.getString(1));
				}
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		} catch (SQLException e1) {

		} catch (Exception e1) {

		}
		return g;
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

	public void fillList(String[] list1, int i) {
		for (int j = count; j < list1.length; j++) {
			if (WebMoney.getData()[i][2] != null) {
				list1[j] = (String) WebMoney.getData()[i][2];

			}
			count++;
			return;
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Replenish();
	}

}
