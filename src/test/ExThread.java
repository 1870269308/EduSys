package test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Random;
import java.util.Vector;

import javax.swing.JLabel;

import com.demo.utils.JdbcUtils;

//这里继承线程
public class ExThread extends Thread{
	//显示标签  
	private JLabel rs;
	boolean isSotp;
	int n;
	String a="";
	//ArrayList<String> array=new ArrayList<String>();
	//Vector 类可以实现可增长的对象数组。 与数组一样，它包含可以使用整数索引进行访问的组件。
	Vector vector=new Vector();
	int getn;
	/*ExThread(JLabel rs,int n,boolean a){
	this.rs = rs;
	this.n = n;
	isSotp=a;
	}*/
	void setbool(boolean a){isSotp=a;};
	void setint(int a){n=a;};
	
	//将rs显示标签传入线程
	ExThread(JLabel rs) throws IOException{
		this.rs = rs;
		v(vector);
	}
	
	//Vector 可以假装是个数组
	public void v(Vector a){
		a=vector;
		try{
			//建立连接
			Connection cn = JdbcUtils.getConnection();
//			Class.forName("com.mysql.jdbc.Driver");//加载 MySQL 驱动程序
//			//建立与数据库的连接
//			String url = "jdbc:mysql://118.31.59.108:3306/edusql";
//			
//			Connection cn = DriverManager.getConnection(url,"root","root");
			Statement cmd = cn.createStatement();//创建 Statement 对象
			System.out.println("123");
			String sql="select * from user";
			ResultSet r=cmd.executeQuery(sql);
			
			while(r.next()){
				String n1=r.getString(1);//获取数据库 第一行第一列
				String n2=r.getString(2); //获取数据库 第一行第二列
			//	String n3=n1+" "+n2; //连接学号和姓名
				String n3 = n2;
				a.add(n3);
			}
				getn=a.size();
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
		
	@Override
	public void run() {
		while(isSotp){
		try {
			Thread.sleep(50);
			//这里标签显示 
			rs.setText((String) vector.get(getnumber(getn)));
			
		//	System.out.println("1232");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			   e.printStackTrace();
			}
		}
	}
	
	//获取随机
	public static int getnumber(int n){ //根据你输入的总人数返回随机在人数范围内的一个学号
		Random r = new Random();
		int r1 = r.nextInt(n);
		return r1;
	}
}
