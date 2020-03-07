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
 * Servlet implementation class DeleteForm
 */
@WebServlet("/DeleteForm")
public class DeleteForm extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		PrintWriter out = response.getWriter();
		int s;
		int flag=0;
		String s1 = request.getParameter("txt1");
		
		if(s1=="")
		{
			out.println("Please enter Enrollment no.");
			out.println("You will be redirected to Previous page.");
			response.setHeader("refresh", "4; URL= Delete.html");
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
						PreparedStatement ps = con.prepareStatement("delete from Form where Enrollment_no = ?");
						ps.setInt(1, s);
						int i = ps.executeUpdate();
						out.println("Values deleted Successfully");
						flag=1;
						break;
					}
				}
				if(flag==0)
				{
					out.println("The Entered Enrollment no does not exists. ");
					response.setHeader("refresh", "4; URL=Delete.html");
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
