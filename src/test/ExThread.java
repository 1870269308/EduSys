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

//����̳��߳�
public class ExThread extends Thread{
	//��ʾ��ǩ  
	private JLabel rs;
	boolean isSotp;
	int n;
	String a="";
	//ArrayList<String> array=new ArrayList<String>();
	//Vector �����ʵ�ֿ������Ķ������顣 ������һ��������������ʹ�������������з��ʵ������
	Vector vector=new Vector();
	int getn;
	/*ExThread(JLabel rs,int n,boolean a){
	this.rs = rs;
	this.n = n;
	isSotp=a;
	}*/
	void setbool(boolean a){isSotp=a;};
	void setint(int a){n=a;};
	
	//��rs��ʾ��ǩ�����߳�
	ExThread(JLabel rs) throws IOException{
		this.rs = rs;
		v(vector);
	}
	
	//Vector ���Լ�װ�Ǹ�����
	public void v(Vector a){
		a=vector;
		try{
			//��������
			Connection cn = JdbcUtils.getConnection();
//			Class.forName("com.mysql.jdbc.Driver");//���� MySQL ��������
//			//���������ݿ������
//			String url = "jdbc:mysql://118.31.59.108:3306/edusql";
//			
//			Connection cn = DriverManager.getConnection(url,"root","root");
			Statement cmd = cn.createStatement();//���� Statement ����
			System.out.println("123");
			String sql="select * from user";
			ResultSet r=cmd.executeQuery(sql);
			
			while(r.next()){
				String n1=r.getString(1);//��ȡ���ݿ� ��һ�е�һ��
				String n2=r.getString(2); //��ȡ���ݿ� ��һ�еڶ���
			//	String n3=n1+" "+n2; //����ѧ�ź�����
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
			//�����ǩ��ʾ 
			rs.setText((String) vector.get(getnumber(getn)));
			
		//	System.out.println("1232");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			   e.printStackTrace();
			}
		}
	}
	
	//��ȡ���
	public static int getnumber(int n){ //��������������������������������Χ�ڵ�һ��ѧ��
		Random r = new Random();
		int r1 = r.nextInt(n);
		return r1;
	}
}
