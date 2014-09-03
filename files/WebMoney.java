package diploma.webmoney;

import java.awt.AWTEvent;
import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.Point;
import java.awt.RadialGradientPaint;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayer;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import javax.swing.plaf.LayerUI;

import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;








import javax.swing.*;

public class WebMoney {
	// Where the GUI is created:
	private JMenuBar menuBar;
	private JMenu menu, submenu;
	private JMenuItem menuItem, rbMenuItem, menuItem1, menuItem2, menuItem3,
			menuItem4;
	private JCheckBoxMenuItem cbMenuItem;
	private JButton button;
	private JLabel jl;
	private static Object[][] data = new Object[14][4];
	private static JScrollPane scrollPane;
	private static int idColum;
	private JFrame f ;
	public WebMoney() {

		 f = new JFrame("Webmoney Keeper");
		f.setLocation(200, 150);
//		f.setMinimumSize(new Dimension(700, 420));
		f.setPreferredSize(new Dimension(700, 420));
		f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		MyPanel mp = new MyPanel();
		// f.getContentPane().add(yellowLabel, BorderLayout.CENTER);
		f.getContentPane().add(mp);
		f.pack();
		f.setVisible(true);
	}

	public static int getIdColum() {
		return idColum;
	}

	public static void setIdColum(int idColum) {
		WebMoney.idColum = idColum;
	}

	public static Object[][] getData() {
		return data;
	}

	public static void setData(Object[][] data) {
		WebMoney.data = data;
	}

	public static void fillData(Object[][] t, Object g) {
		for (int i = 0; i < t.length; i++) {
			for (int j = 0; j < t[i].length; j++) {
				if (t[i][j] == null) {
					t[i][j] = g;
					return;
				}
			}
		}
	}
	public static void fillElem(Object[][] t, Object t1, Double g) {
		Double h = 0d;
		for (int i = 0; i < t.length; i++) {
				if (t[i][2].equals(t1)  ){
					if(t[i][1] instanceof Double){
						 h += (Double)t[i][1];
					
					}else{
						 h +=Double.parseDouble((String) t[i][1]);
					}
					t[i][1] =g + h;
					return;
				}
		}
	}
private static int h=0;


	public static JTable createPurseP() {
		String[] columnNames = { "Purse", "Summa", "Number", "Date of creating" };

if(h==0){
		 Connection con= null;
		 PreparedStatement st = null;
		 ResultSet resulset = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		
		 con = DriverManager.getConnection("jdbc:mysql://localhost:3306/WebMoney", "root", "");
//		 System.out.println(Start.getNameOfTable());
		  st=con.prepareStatement("SELECT * FROM `"+Start.getNameOfTable()+"` LIMIT 0 , 30");
		 
		  resulset =  st.executeQuery();
//		  System.out.println("3");
		 
		  while (resulset.next()) {
			  
			  for(int i=2;i<6;i++){
			  fillDataIfExist(WebMoney.getData(), resulset.getString(i));
			  }
		  }
		
	
		}catch (SQLException e1) {
			e1.printStackTrace();
		}catch (Exception e1) {
			e1.printStackTrace();
		}
		h++;
}
		JTable jtb = new JTable(WebMoney.getData(), columnNames);
		
		scrollPane = new JScrollPane(jtb);
		jtb.setCellSelectionEnabled(true);
		jtb.setColumnSelectionAllowed(false);
//		jtb.getModel();
		jtb.setGridColor(Color.WHITE);
		jtb.setFillsViewportHeight(false);
		jtb.setDragEnabled(true);
		jtb.setDoubleBuffered(false);
		jtb.setEnabled(true);
		jtb.setAutoCreateColumnsFromModel(false);
		jtb.setAutoCreateRowSorter(true);
		
		jtb.setFocusable(false);
		jtb.setFocusCycleRoot(false);
		jtb.setRequestFocusEnabled(false);
		jtb.setShowHorizontalLines(true);
		jtb.setShowVerticalLines(false);
		jtb.setVerifyInputWhenFocusTarget(false);
		jtb.setUpdateSelectionOnSort(false);
		return jtb;

	}
	
	public static void fillDataIfExist(Object[][]t,Object g){
		for(int i =0; i<t.length;i++){
			for(int j =0; j<t[i].length;j++){
				if(t[i][j] == null){
					t[i][j] = g;
					return;
				}
			}
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new WebMoney();
	}

	public class MyPanel extends JPanel implements ItemListener {
		JTabbedPane main;
		final static String Corresp = "Correspondents";
		final static String Purse = "Purse";
		final static String Message = "Message";
		final static String News = "News";
		final static int extraWindowWidth = 100;
		JPanel jp1;
		JPanel jp;
		private int messageid;

		public MyPanel() {// create famous panel
			// Create the menu bar.
			setLayout(new BorderLayout());
			createMenu();
			createInterface();
			add("Center", jp);
			add("South", createSouthPanel());
		}

		public JPanel createMenu() {
			jp1 = new JPanel();
			jp1.setLayout(new BorderLayout());

			menuBar = new JMenuBar();

			add("North", jp1);
			// Build the first menu.
			menu = new JMenu("Menu");
			menu.setMnemonic(KeyEvent.VK_A);
			menu.getAccessibleContext().setAccessibleDescription(
					"The only menu in this program that has menu items");
			menuBar.add(menu);

			// a group of JMenuItems
			menuItem = new JMenuItem("About Me", KeyEvent.VK_T);
			menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1,
					ActionEvent.ALT_MASK));
			menuItem.getAccessibleContext().setAccessibleDescription(
					"This doesn't really do anything");
			menuItem.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					new DataTable();
				}
			});
			menu.add(menuItem);

			menuItem = new JMenuItem("Give the money", new ImageIcon("1.jpg"));
			menuItem.setMnemonic(KeyEvent.VK_B);
			menu.add(menuItem);

			menuItem = new JMenuItem("Replenish", new ImageIcon("2.jpg"));
			menuItem.setMnemonic(KeyEvent.VK_D);
			menuItem.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					if(getData()[0][0] !=null){
						new Replenish();
						}else{
							JOptionPane.showConfirmDialog(null,
									"Please, create some purse", "Atention!!!",
									JOptionPane.INFORMATION_MESSAGE);
						}
				}
			});
		
			menu.add(menuItem);

			// a submenu
			menu.addSeparator();
			submenu = new JMenu("Message");
			submenu.setMnemonic(KeyEvent.VK_S);

			menuItem = new JMenuItem("Send....");
			menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2,
					ActionEvent.ALT_MASK));
			submenu.add(menuItem);

			menuItem = new JMenuItem("Look`s all");
			submenu.add(menuItem);
			menu.add(submenu);

			menuItem = new JMenuItem("Exit", KeyEvent.VK_T);
			menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4,
					ActionEvent.ALT_MASK));
			menuItem.getAccessibleContext().setAccessibleDescription(
					"This doesn't really do anything");
			menu.add(menuItem);

			// Build second menu in the menu bar.
			menu = new JMenu("Instruments");
			menu.setMnemonic(KeyEvent.VK_N);
			menu.getAccessibleContext().setAccessibleDescription(
					"This menu does nothing");

			menuItem = new JMenuItem("Parametris of programm...");
			menu.add(menuItem);
			menuBar.add(menu);

			// Build third menu in the menu bar.
			menu = new JMenu("Help");
			menu.setMnemonic(KeyEvent.VK_2);
			menu.getAccessibleContext().setAccessibleDescription(
					"This menu does nothing");

			menuItem = new JMenuItem("Help...");
			menu.add(menuItem);

			menuItem = new JMenuItem("Question and answer");
			menu.add(menuItem);

			menu.addSeparator();
			menuItem = new JMenuItem("About Webmoney Keeper");
			menu.add(menuItem);
			menuBar.add(menu);

			// jp1.add(menuBar);
			jp1.add("North", menuBar);
			return jp1;
		}

		public JPanel createInterface() {
			jp = new JPanel();
			jp.setBackground(Color.BLUE);
			jp.setLayout(new BorderLayout());
			JPanel jp2 = new JPanel();
			jp2.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0,
					Color.GRAY));
			jp2.setLayout(new GridBagLayout());
			GridBagConstraints c = new GridBagConstraints();
			c.ipadx = 25;
			c.gridwidth = 1;
			c.gridheight = 2;
			c.fill = GridBagConstraints.HORIZONTAL;
			c.gridx = 0;
			c.gridy = 0;
			jl = new JLabel(new ImageIcon("3.jpg"));
			jp2.add(jl, c);
			c.weightx = 1;
			c.gridheight = 1;
			c.gridy = 0;
			c.gridx = 1;
			jl = new JLabel(Start.getData()[2] + " " + Start.getData()[1] + " "
					+ Start.getData()[3]);
			jl.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			jl.setFont(new Font("Verdana", Font.BOLD, 14));
			jp2.add(jl, c);
			c.gridheight = 1;
			c.gridx = 1;
			c.gridy = 1;
			jl = new JLabel("your accounts: " + "UAN = " + Start.getData()[16]
					+ " USD = " + Start.getData()[17] + " EURO = "
					+ Start.getData()[18] + " RUB= " + Start.getData()[19]);
			jl.setFont(new Font("Verdana", Font.BOLD, 10));
			jp2.add(jl, c);
			jp2.setBackground(Color.WHITE);

			jp.add("North", jp2);
			// add CardLayout

			JPanel main1 = new JPanel() {
				// Make the panel wider than it really needs, so
				// the window's wide enough for the tabs to stay
				// in one row.
				public Dimension getPreferredSize() {
					Dimension size = super.getPreferredSize();
					size.width += extraWindowWidth;
					return size;
				}
			};
			main1.setBackground(new Color(250, 250, 250));
			JPanel hg = new JPanel();
			hg.setLayout(new GridLayout());
			JButton g = new JButton("Create", new ImageIcon("10.jpg"));
			g.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					new CreatePurse();
				}
			});
			g.setBackground(Color.WHITE);
			g.setBorderPainted(false);
			g.setSize(50, 20);
			g.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			hg.add(g);
			JButton g1 = new JButton("Replenish", new ImageIcon("2.jpg"));
			g1.setBackground(Color.WHITE);
			g1.setBorderPainted(false);
			g1.setMinimumSize(new Dimension(30, 20));
			g1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			g1.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
//					setIdColum(createPurseP().getSelectedRowCount());
//					System.out.println(getIdColum());
					if(getData()[0][0] !=null){
					new Replenish();
					}else{
						JOptionPane.showConfirmDialog(null,
								"Please, create some purse", "Atention!!!",
								JOptionPane.INFORMATION_MESSAGE);
					}
					
					
				}
			});
			
			hg.add(g1);			
			
			
			
			JButton g2 = new JButton("Transmit", new ImageIcon("1.jpg"));
			g2.setBackground(Color.WHITE);
			g2.setBorderPainted(false);
			g2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			hg.add(g2);
            g2.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    // TODO Auto-generated method stub
                    new Transmit();
                }
            });
			
			
			JButton g3 = new JButton("Exchange", new ImageIcon("14.jpg"));
			g3.setBackground(Color.WHITE);
			g3.setBorderPainted(false);
			g3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			hg.add(g3);
			g3.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					new Exchange();
				}
			});
			

			JPanel jp3 = new JPanel();
			jp3.setBackground(Color.WHITE);
			jp3.setAutoscrolls(true);
			jp3.setLayout(new BorderLayout());
			createPurseP();
			jp3.add("Center", scrollPane);

			JPanel main2 = new JPanel();
			main2.setLayout(new BorderLayout());
			main2.setBackground(new Color(245, 245, 245));
			main2.add("North", hg);
			main2.add("Center", jp3);

			JPanel main3 = new JPanel();
			main3.setBackground(new Color(240, 240, 240));

			JPanel main4 = new JPanel();
			main4.setBackground(new Color(235, 235, 235));
			LayerUI<JPanel> layerUI = new SpotlightLayerUI();
			JPanel panel = createMyPanel();
			JLayer<JPanel> jlayer = new JLayer<JPanel>(panel, layerUI);

			main4.add(jlayer);

			main = new JTabbedPane();
			main.addTab(Corresp, main1);
			main.addTab(Purse, main2);
			main.addTab(Message, main3);
			main.addTab(News, main4);
			main.setBackground(new Color(230, 230, 230));

			jp.add("Center", main);

			return jp;
		}

		public JPanel createSouthPanel() {
			JPanel jp5 = new JPanel(new GridLayout(0, 6));
			jp5.add(new JLabel(new ImageIcon("7.jpg")));
			jp5.add(new JLabel("Online"));
			jp5.add(new JLabel(new ImageIcon("8.jpg")));
			jp5.add(new JLabel(Start.getData()[11]));
			jp5.add(new JLabel(new ImageIcon("9.jpg")));
			jp5.add(new JLabel("[ " + messageid + " ]"));

			return jp5;

		}

		private JPanel createMyPanel() {
			JPanel p = new JPanel();
			ButtonGroup entreeGroup = new ButtonGroup();
			JRadioButton radioButton;
			p.add(radioButton = new JRadioButton("Mini", true));
			entreeGroup.add(radioButton);
			p.add(radioButton = new JRadioButton("Keeper"));
			entreeGroup.add(radioButton);
			p.add(radioButton = new JRadioButton("Light"));
			entreeGroup.add(radioButton);
			p.add(new JCheckBox("Teaching"));
			p.add(new JCheckBox("Help"));
			p.add(new JLabel("Special requests:"));
			p.add(new JTextField(20));
			JButton orderButton = new JButton("Searching");
			p.add(orderButton);
			p.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			return p;
		}

		@Override
		public void itemStateChanged(ItemEvent e) {
			// TODO Auto-generated method stub
			CardLayout cl = (CardLayout) (main.getLayout());
			cl.show(main, (String) e.getItem());
		}
	}

	class SpotlightLayerUI extends LayerUI<JPanel> {
		private boolean mActive;
		private int mX, mY;

		@Override
		public void installUI(JComponent c) {
			super.installUI(c);
			JLayer jlayer = (JLayer) c;
			jlayer.setLayerEventMask(AWTEvent.MOUSE_EVENT_MASK
					| AWTEvent.MOUSE_MOTION_EVENT_MASK);
		}

		@Override
		public void uninstallUI(JComponent c) {
			JLayer jlayer = (JLayer) c;
			jlayer.setLayerEventMask(0);
			super.uninstallUI(c);
		}

		@Override
		public void paint(Graphics g, JComponent c) {
			Graphics2D g2 = (Graphics2D) g.create();

			// Paint the view.
			super.paint(g2, c);

			if (mActive) {
				// Create a radial gradient, transparent in the middle.
				java.awt.geom.Point2D center = new java.awt.geom.Point2D.Float(
						mX, mY);
				float radius = 72;
				float[] dist = { 0.0f, 1.0f };
				Color[] colors = { new Color(0.0f, 0.0f, 0.0f, 0.0f),
						Color.BLACK };
				RadialGradientPaint p = new RadialGradientPaint(center, radius,
						dist, colors);
				g2.setPaint(p);
				g2.setComposite(AlphaComposite.getInstance(
						AlphaComposite.SRC_OVER, .6f));
				g2.fillRect(0, 0, c.getWidth(), c.getHeight());
			}

			g2.dispose();
		}

		@Override
		protected void processMouseEvent(MouseEvent e, JLayer l) {
			if (e.getID() == MouseEvent.MOUSE_ENTERED)
				mActive = true;
			if (e.getID() == MouseEvent.MOUSE_EXITED)
				mActive = false;
			l.repaint();
		}

		@Override
		protected void processMouseMotionEvent(MouseEvent e, JLayer l) {
			Point p = SwingUtilities.convertPoint(e.getComponent(),
					e.getPoint(), l);
			mX = p.x;
			mY = p.y;
			l.repaint();
		}
	}
}
