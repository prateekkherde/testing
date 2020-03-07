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
 * Servlet implementation class ReadForm
 */
@WebServlet("/ReadForm")
public class ReadForm extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		int flag=0;
		String s1 = request.getParameter("txt1");
		if(s1=="")
		{
			out.println("Please Fill the Specified Field. ");
			response.setHeader("refresh", "4;URL=Read.html");
		}
		else
		{
		try
		{
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Task1", "Prateek" , "Varsha1595#");
			Statement st = con.createStatement();
			
			out.println("<html><body><table border='1'>");
			out.println("<tr><th>Enrollment no</th><th>First Name</th><th>Last Name</th><th>Email</th><th>City</th><th>State</th><th>Gender</th></tr>");

			if(s1.equals("all") || s1.equals("All"))
			{
				ResultSet rs = st.executeQuery("select * from Form");
				while(rs.next())
				{
					int a1=rs.getInt(1);
					String a2=rs.getString(2);
					String a3=rs.getString(3);
					String a4=rs.getString(4);
					String a5=rs.getString(5);
					String a6=rs.getString(6);
					String a7=rs.getString(7);
					
						out.println("<tr>");
						out.println("<td>" +a1+ "</td>");
						out.println("<td>" +a2+ "</td>");
						out.println("<td>" +a3+ "</td>");
						out.println("<td>" +a4+ "</td>");
						out.println("<td>" +a5+ "</td>");
						out.println("<td>" +a6+ "</td>");
						out.println("<td>" +a7+ "</td>");
						out.println("</tr>");
				}
				flag=2;
			}
			else
			{
				int s = Integer.parseInt(s1);
				//PreparedStatement ps = con.prepareStatement("select * from Form where Enrollment_no=?");
				//ps.setInt(1, s);
				ResultSet rs = st.executeQuery("select * from Form where Enrollment_no="+s);
				while(rs.next())
				{
					out.println("<tr>");
					out.println("<td>"+rs.getInt(1)+"</td>");
					out.println("<td>"+rs.getString(2)+"</td>");
					out.println("<td>"+rs.getString(3)+"</td>");
					out.println("<td>"+rs.getString(4)+"</td>");
					out.println("<td>"+rs.getString(5)+"</td>");
					out.println("<td>"+rs.getString(6)+"</td>");
					out.println("<td>"+rs.getString(7)+"</td>");
					out.println("</tr>");
					out.println("</table></body></html>");
					flag=1;
				}
						
				
				
			}
			if(flag==0)
			{
				out.println("The Entered Enrollment no. does not Exists.");
				response.setHeader("refresh", "4;URL=Read.html");
			}
	
		}
			catch(Exception e)
			{
				out.println(e);
			}
			
		}
	}

}
