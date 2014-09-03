package diploma.webmoney;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.RenderingHints;
import java.awt.SplashScreen;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayer;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.WindowConstants;

import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;

import javax.swing.*;
import javax.swing.plaf.LayerUI;

import org.logi.crypto.sign.MD5State;
import org.logi.crypto.sign.Fingerprint;

public class Start {
	private static String[] data = new String[20];
	private String password = "";
	private List<String> myListLogin = new ArrayList<String>();
	private List<Character> myListPassword = new ArrayList<Character>();
	private JFrame frame;
	private static String OK = "ok";
	private JButton jb1;
	private JPanel jp;
	private JPanel jp1;
	private JLabel jl4;
	private static Basa b;
	private static JPasswordField jpf;
	private static JTextField jt1;
	private static String nameOfTable = "gh";

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public static String getNameOfTable() {
		return nameOfTable;
	}

	public static void setNameOfTable(String nameOfTable) {
		Start.nameOfTable = nameOfTable;
	}

	final WaitLayerUI layerUI = new WaitLayerUI();

	public Start() throws Exception {
		// layerUI
		b = new Basa();
		JPanel panel = createMyPanel();
		JLayer<JPanel> jlayer = new JLayer<JPanel>(panel, layerUI);

		frame = new JFrame("Webmoney Keeper Classic >> Enter");
		frame.setLocation(300, 150);
		frame.setMinimumSize(new Dimension(400, 220));
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		try {
			frame.getContentPane().add(jlayer);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		frame.pack();
		frame.setVisible(true);
	}

	public JPanel createMyPanel() throws Exception {
		jp1 = new JPanel();
		jp = new JPanel();
		jp.setBackground(Color.WHITE);
		jp.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		JLabel jl1 = new JLabel(new ImageIcon("4.jpg"));
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 3;
		jp.add(jl1, c);
		JLabel jl2 = new JLabel("WMID");
		c.ipadx = 10;
		c.gridheight = 1;
		c.gridx = 1;
		c.gridy = 0;
		jp.add(jl2, c);
		jt1 = new JTextField(15);
		c.gridwidth = 2;
		c.ipadx = 0;
		c.gridx = 2;
		c.gridy = 0;
		jp.add(jt1, c);

		JLabel jl3 = new JLabel("Password");
		c.gridwidth = 1;
		c.ipadx = 10;
		c.gridx = 1;
		c.gridy = 1;
		jp.add(jl3, c);
		jpf = new JPasswordField(15);
		// setPas(jpf.getPassword());
		jpf.setActionCommand(OK);
		c.gridwidth = 2;
		c.ipadx = 0;
		c.gridx = 2;
		c.gridy = 1;
		jp.add(jpf, c);
		jl4 = new JLabel("Need help");

		jl4.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		c.gridwidth = 1;
		c.ipadx = 10;
		c.gridx = 1;
		c.gridy = 2;

		jp.add(jl4, c);
		jb1 = new JButton("Sing in");
		jb1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		// ------add the users
		b.setResulset(b.getSt().executeQuery());
		while (b.getResulset().next()) {
			myListLogin.add(b.getResulset().getString(8));
			myListLogin.add(b.getResulset().getString(11));
			myListLogin.add(b.getResulset().getString(12));

		}

		jb1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				final Timer stopper = new Timer(2000, new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent ae) {
						layerUI.stop();
					}
				});
				stopper.setRepeats(false);

				layerUI.start();
				if (!stopper.isRunning()) {
					stopper.start();
				}
				String pass = "";
				for (int i = 0; i < jpf.getPassword().length; i++) {
					pass += jpf.getPassword()[i];
				}
				setPassword(pass);
				for (int i = 0; i < myListLogin.size(); i++) {
					if (myListLogin.get(i).equals(jt1.getText())) {
						// String cmd = e.getActionCommand();
						if (isPasswordCorrect(pass)) {
							frame.removeAll();
							frame.setVisible(false);
							giveData();
							setNameOfTable(data[15]);
							new WebMoney();
							break;
						} else {

							JOptionPane.showConfirmDialog(null,
									"Invalid password. Try again.",
									"Error Message", JOptionPane.ERROR_MESSAGE);
							break;
						}

					} else if (i == myListLogin.size() - 1
							&& !myListLogin.get(i).equals(jt1.getText())) {
						JOptionPane.showConfirmDialog(null,
								"Please, try again enable your login correct",
								"Wrong User!!!", JOptionPane.PLAIN_MESSAGE);
						break;
					}
				}
			}

		});
		c.gridwidth = 1;
		c.ipadx = 0;
		c.gridx = 2;
		c.gridy = 2;
		jp.add(jb1, c);
		JButton jb2 = new JButton("Cancel");
		jb2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		jb2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				frame.removeAll();
				frame.setVisible(false);

			}

		});
		c.gridwidth = 1;
		c.ipadx = 0;
		c.gridx = 3;
		c.gridy = 2;
		jp.add(jb2, c);

		JPanel jp2 = new JPanel();
		JPanel jp3 = new JPanel();
		jp3.setLayout(new GridLayout(2, 1));

		jp2.setLayout(new BorderLayout());

		JLabel jl5 = new JLabel(new ImageIcon("5.jpg"));

		jp2.add("West", jl5);
		JLabel jt2 = new JLabel("Welcome to Webmoney Kepper!");
		jt2.setFont(new Font("Times newRoman", Font.BOLD, 12));

		jp3.add(jt2);
		JLabel jt3 = new JLabel(
				"Choose the place for save the keys and forword instructions");
		jt3.setFont(new Font("Times newRoman", Font.ITALIC, 9));

		jp3.add(jt3);
		jp2.add("Center", jp3);

		jp2.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));
		jp1.setLayout(new BorderLayout());
		jp1.add("Center", jp);
		jp1.add("North", jp2);
		return jp1;

	}

	private void giveData() {
		try {
			b.setResulset(b.getSt().executeQuery());
			while (b.getResulset().next()) {
				if (b.getResulset().getString(8).equals(jt1.getText())
						|| b.getResulset().getString(11).equals(jt1.getText())
						|| b.getResulset().getString(12).equals(jt1.getText())) {
					data[0] = b.getResulset().getString(11);
					data[1] = b.getResulset().getString(4);
					data[2] = b.getResulset().getString(3);
					data[3] = b.getResulset().getString(2);
					data[4] = b.getResulset().getString(6);
					data[5] = b.getResulset().getString(5);
					data[6] = b.getResulset().getString(8);
					data[7] = b.getResulset().getString(7);
					data[8] = b.getResulset().getString(9);
					data[9] = b.getResulset().getString(10);
					data[10] = b.getResulset().getString(11);
					data[11] = b.getResulset().getString(12);
					data[12] = b.getResulset().getString(13);
					data[13] = b.getResulset().getString(14);
					data[14] = b.getResulset().getString(15);
					data[15] = b.getResulset().getString(16);
					data[16] = b.getResulset().getString(17);
					data[17] = b.getResulset().getString(18);
					data[18] = b.getResulset().getString(19);
					data[19] = b.getResulset().getString(20);

					break;
				}

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static String[] getData() {
		return data;
	}

	private static boolean isPasswordCorrect(String name) {
		boolean isCorrect = true;

		try {
			b.setResulset(b.getSt().executeQuery());
			while (b.getResulset().next()) {
				if (((b.getResulset().getString(8).equals(jt1.getText()) || b
						.getResulset().getString(11).equals(jt1.getText())) || b
						.getResulset().getString(12).equals(jt1.getText()))
						&& b.getResulset().getString(13).equals(encryptString(name, b.getResulset().getString(15)))) {
					isCorrect = true;
					break;
				} else {
					isCorrect = false;

				}
			}
		} catch (SQLException e1) {
		}

		return isCorrect;
	}

	private static String encryptString(String sourseString, String salt) {
		MD5State digest = new MD5State();
		digest.update(sourseString.getBytes());
		Fingerprint hash = digest.calculate();
		String encryptedString = hash.toString();

		encryptedString = encryptedString.substring(
				encryptedString.indexOf(",") + 1, encryptedString.length() - 1);
		
		MD5State digest1 = new MD5State();
		digest1.update((encryptedString+salt).getBytes());
		Fingerprint hash1 = digest1.calculate();
		String encryptedString1 = hash1.toString();

		encryptedString1 = encryptedString1.substring(
				encryptedString1.indexOf(",") + 1, encryptedString1.length() - 1);
		return encryptedString1;
	}

	class WaitLayerUI extends LayerUI<JPanel> implements ActionListener {
		private boolean mIsRunning;
		private boolean mIsFadingOut;
		private Timer mTimer;

		private int mAngle;
		private int mFadeCount;
		private int mFadeLimit = 15;

		@Override
		public void paint(Graphics g, JComponent c) {
			int w = c.getWidth();
			int h = c.getHeight();

			// Paint the view.
			super.paint(g, c);

			if (!mIsRunning) {
				return;
			}

			Graphics2D g2 = (Graphics2D) g.create();

			float fade = (float) mFadeCount / (float) mFadeLimit;
			// Gray it out.
			Composite urComposite = g2.getComposite();
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
					.5f * fade));
			g2.fillRect(0, 0, w, h);
			g2.setComposite(urComposite);

			// Paint the wait indicator.
			int s = Math.min(w, h) / 5;
			int cx = w / 2;
			int cy = h / 2;
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
					RenderingHints.VALUE_ANTIALIAS_ON);
			g2.setStroke(new BasicStroke(s / 4, BasicStroke.CAP_ROUND,
					BasicStroke.JOIN_ROUND));
			g2.setPaint(Color.white);
			g2.rotate(Math.PI * mAngle / 180, cx, cy);
			for (int i = 0; i < 12; i++) {
				float scale = (11.0f - (float) i) / 11.0f;
				g2.drawLine(cx + s, cy, cx + s * 2, cy);
				g2.rotate(-Math.PI / 6, cx, cy);
				g2.setComposite(AlphaComposite.getInstance(
						AlphaComposite.SRC_OVER, scale * fade));
			}

			g2.dispose();
		}

		public void actionPerformed(ActionEvent e) {
			if (mIsRunning) {
				firePropertyChange("tick", 0, 1);
				mAngle += 3;
				if (mAngle >= 360) {
					mAngle = 0;
				}
				if (mIsFadingOut) {
					if (--mFadeCount == 0) {
						mIsRunning = false;
						mTimer.stop();
					}
				} else if (mFadeCount < mFadeLimit) {
					mFadeCount++;
				}
			}
		}

		public void start() {
			if (mIsRunning) {
				return;
			}

			// Run a thread for animation.
			mIsRunning = true;
			mIsFadingOut = false;
			mFadeCount = 0;
			int fps = 24;
			int tick = 1000 / fps;
			mTimer = new Timer(tick, this);
			mTimer.start();

		}

		public void stop() {
			mIsFadingOut = true;
		}

		@Override
		public void applyPropertyChange(PropertyChangeEvent pce, JLayer l) {
			if ("tick".equals(pce.getPropertyName())) {
				l.repaint();
			}
		}
	}

	public static void main(String[] args) throws Exception {
		SplashScreen splashScreen= SplashScreen.getSplashScreen();
        Thread.sleep(2000);
        new Start();
	}
}
