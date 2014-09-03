package diploma.webmoney;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.swing.*;

public class CreatePurse {
	private JFrame jf;
	private JTextArea tt;
	JList list;
	private Basa b;
	private static int count = 0;

	public static int getCount() {
		return count;
	}

	public static void setCount(int count) {
		CreatePurse.count += count;
	}

	public CreatePurse() {
		jf = new JFrame("Create purse");
		jf.setLocation(250, 150);
		jf.setMinimumSize(new Dimension(440, 320));
		jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		jf.getContentPane().add(createMyPan());
		jf.pack();
		jf.setVisible(true);
		jf.setResizable(false);
		b = new Basa();
	}

	public JPanel createMyPan() {
		JPanel jp = new JPanel();
		jp.setLayout(new BorderLayout());
		JPanel j1 = new JPanel();
		j1.setBackground(Color.WHITE);
		j1.setLayout(new BorderLayout());
		JLabel jl1 = new JLabel(new ImageIcon("11.jpg"));
		j1.add("West", jl1);
		JPanel j2 = new JPanel();
		j2.setBackground(Color.WHITE);
		j2.setLayout(new GridLayout(2, 0));
		j2.add(new JLabel("Creation of a personal purse"));
		JLabel jl3 = new JLabel("Select the type of purse shown below");
		jl3.setFont(new Font("Verdana", Font.ITALIC, 10));
		j2.add(jl3);
		j1.add("Center", j2);
		j1.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));

		JPanel j3 = new JPanel();
		j3.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.GRAY));

		JButton jb = new JButton("Cancel");
		jb.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				jf.removeAll();
				jf.setVisible(false);
			}
		});
		jb.setContentAreaFilled(false);
		jb.setFocusable(false);
		jb.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		JButton jb1 = new JButton("Next");

		jb1.setContentAreaFilled(false);
		jb1.setFocusable(false);
		jb1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		j3.add(jb);
		j3.add(jb1);

		JPanel j4 = new JPanel();
		j4.setLayout(new BorderLayout());
		JLabel jl4 = new JLabel("Type personal currency");
		jl4.setFont(new Font("Verdana", Font.PLAIN, 12));
		j4.add("North", jl4);

		JPanel j5 = new JPanel();

		j5.setLayout(new GridLayout(2, 0));
		JLabel jl5 = new JLabel("Name:");
		j5.setMaximumSize(new Dimension(350, 120));
		jl5.setFont(new Font("Verdana", Font.PLAIN, 12));
		j5.add(jl5);
		tt = new JTextArea();
		tt.setMargin(new Insets(10, 0, 0, 0));

		j5.add(tt);

		String[] data = { "WMU-equivalent UAN ", "WMZ-equivalent USD",
				"WME-equivalent EUR", "WMR-equivalent RUB",
				"WMD-equivalent USD(for sales on credit)", };
		list = new JList(data); // data has type Object[]
		list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		// list.setPreferredSize(new Dimension(300, 50));
		list.setAutoscrolls(true);
		list.setLayoutOrientation(JList.VERTICAL);
		list.setVisibleRowCount(-1);
		j4.add("Center", list);
		j4.add("South", j5);

		jp.add("South", j3);
		jp.add("North", j1);
		jp.add("Center", j4);

		jb1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				if (tt.getText() == null || tt.getText().equals("")
						|| tt.getText().equals(" ")) {
					JOptionPane.showConfirmDialog(null,
							"Please, select the name", "Atention!!!",
							JOptionPane.ERROR_MESSAGE);
				} else {
					SimpleDateFormat s = new SimpleDateFormat(
							"dd/MMM/yyyy HH:mm");
					Date data = new Date();
					// setCount(1);
					// System.out.println(count);
					String date = s.format(data);
					// System.out.println(date);
					String sql = "INSERT INTO `WebMoney`.`"
							+ Start.getNameOfTable()
							+ "` (`id`, `name`, `summa`, `number`, `date`) VALUES (NULL, '"
							+ tt.getText() + "', '0.00', '" + chooseVal()
							+ "', '" + date + "');";

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

					WebMoney.fillData(WebMoney.getData(), tt.getText());

					WebMoney.fillData(WebMoney.getData(), "0.00");
					WebMoney.fillData(WebMoney.getData(), chooseVal());

					WebMoney.fillData(WebMoney.getData(), date);
					jf.removeAll();
					jf.setVisible(false);

					WebMoney.createPurseP().repaint();

				}
			}
		});
		return jp;
	}

	public long myR() {
		Random r = new Random();
		long f = 0;
		while (f < 100000000) {
			f += r.nextInt(999999999);
		}
		return f;
	}

	public String chooseVal() {
		String s = "";
		if (list.getSelectedIndex() == 0) {
			s = "U" + myR();
		} else if (list.getSelectedIndex() == 1) {
			s = "Z" + myR();
		} else if (list.getSelectedIndex() == 2) {
			s = "E" + myR();
		} else if (list.getSelectedIndex() == 3) {
			s = "R" + myR();
		} else {
			s = "NONE" + myR();
		}
		return s;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new CreatePurse();
	}

}
