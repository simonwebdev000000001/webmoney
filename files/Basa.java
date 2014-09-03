package diploma.webmoney;

import java.sql.*;

import javax.swing.JOptionPane;

public class Basa {
	private String[] data = new String[15];
	private Connection con = null;
	private ResultSet resulset = null;
	private PreparedStatement st = null;

	public Connection getCon() {
		return con;
	}

	public void setCon(Connection con) {
		this.con = con;
	}

	public ResultSet getResulset() {
		return resulset;
	}

	public void setResulset(ResultSet resulset) {
		this.resulset = resulset;
	}

	public PreparedStatement getSt() {
		return st;
	}

	public void setSt(PreparedStatement st) {
		this.st = st;
	}

	public Basa() {
		// -----connect with DataBase

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			System.out.println("not found a driver");
		}
		try {
			setCon(DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/WebMoney", "root", ""));
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			JOptionPane.showConfirmDialog(null,
					"Please, check your acces to internet", "failed in connection!!!",
					JOptionPane.INFORMATION_MESSAGE);
		}
		try {
			setSt(con.prepareStatement("select * from datatable"));
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			System.out.println("cann`t take data from DB");
		}
		try {
			setResulset(st.executeQuery());
//			giveData();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			System.out.println("((((");
		}}
        public Basa(String nameOf) {
            // -----connect with DataBase

            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e1) {
                // TODO Auto-generated catch block
                System.out.println("not found a driver");
            }
            try {
                setCon(DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/WebMoney", "root", ""));
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                JOptionPane.showConfirmDialog(null,
                        "Please, check your acces to internet", "failed in connection!!!",
                        JOptionPane.INFORMATION_MESSAGE);
            }
            try {
                setSt(con.prepareStatement("select * from "+nameOf));
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                System.out.println("cann`t take data from DB");
            }
            try {
                setResulset(st.executeQuery());
//			giveData();
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                System.out.println("((((");
            }
	}



}
