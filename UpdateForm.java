package Form;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UpdateForm
 */
@WebServlet("/UpdateForm")
public class UpdateForm extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	PrintWriter out = response.getWriter();
	int s;
	int flag=0;
	String s1 = request.getParameter("txt1");
	String s2 = request.getParameter("txt2");
	String s3 = request.getParameter("txt3");
	String s4 = request.getParameter("txt4");
	String s5 = request.getParameter("txt5");
	String s6 = request.getParameter("txt6");
	String s7 = request.getParameter("gender");
	
	if(s1=="" || s2=="" || s3=="" || s4=="" || s5=="" || s6=="" || s7=="")
	{
		out.println("Please enter all the details");
		response.setHeader("refresh", "4; URL= Update.html");
	}
	else
	{
		try
		{
			s = Integer.parseInt(s1);
			Class.forName("com.mysql.jdbc.Driver");
			
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Task1", "Prateek" , "Varsha1595#");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from Form");
			while(rs.next())
			{
				if(rs.getInt(1)==s)
				{
					PreparedStatement ps = con.prepareStatement("update Form set First_name=?,Last_name=?,Email=?,City=?,State=?,Gender=? where Enrollment_no=?");
					ps.setString(1, s2);
					ps.setString(2, s3);
					ps.setString(3, s4);
					ps.setString(4, s5);
					ps.setString(5, s6);
					ps.setString(6, s7);
					ps.setInt(7, s);
					int i = ps.executeUpdate();
					out.println("Record updated Successfully");
					flag=1;
					break;
				}
			}
			if(flag==0)
			{
				out.println("Specified Enrollment no. does not exists. Please enter correct Enrollment no");
				response.setHeader("refresh", "4; URL=Update.html");
			}
			con.close();
			
			
		}
		catch(Exception e)
		{
			out.println(e);
		}
		response.setHeader("refresh", "4;URL=Index.html");
	
	}
	}

}
