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
 * Servlet implementation class MyForm
 */
@WebServlet("/MyForm")
public class CreateForm extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		PrintWriter out=response.getWriter();
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
			response.setHeader("refresh", "4; URL= Create.html");
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
						out.println("The Entered Enrollment no already exists.Enter new one. ");
						response.setHeader("refresh", "4; URL=Create.html");
						flag=1;
						break;
					}
				}
				
				if(flag==0)
				{
					PreparedStatement ps = con.prepareStatement("insert into Form value(?,?,?,?,?,?,?)");
					ps.setInt(1, s);
					ps.setString(2, s2);
					ps.setString(3, s3);
					ps.setString(4, s4);
					ps.setString(5, s5);
					ps.setString(6, s6);
					ps.setString(7, s7);
					int i = ps.executeUpdate();
					out.println("Values Entered Successfully");
					
					con.close();
				}
			}
			catch(Exception e)
			{
				out.println(e);
			}
			response.setHeader("refresh", "4;URL=Index.html");
		}
	}

}
// adding comment on last line
