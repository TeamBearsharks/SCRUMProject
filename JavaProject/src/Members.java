import java.sql.*;
import java.util.Scanner;

public class Members 
{
	private JDBCConnections jdbcConn = new JDBCConnections();
	
	public void showAll(String memberType) 
	{
		if(memberType.toLowerCase() == "employees")
		{
			System.out.println("Here is a list of all of the employees:\n");
			this.getEmployees();
		}
		else if(memberType.toLowerCase() == "stakeholders")
		{
			System.out.println("Here is a list of all the stakeholders:\n");
			this.getStakeholders();
		}
	}
	
	public void getEmployees() 
	{
		//SQL: select * from Employees
		try {
			jdbcConn.setStatment(jdbcConn.getConnection());
			ResultSet results = jdbcConn.getStatment().executeQuery("select * from Employees;");
			ResultSetMetaData rsmd = results.getMetaData();
			
			int numberCols = rsmd.getColumnCount();
			
			//print column names
			for (int i = 1; i <= numberCols; i++) {
				if (i == 2)
					System.out.print(rsmd.getColumnLabel(3) + "\t");
				else if (i == 3)
					System.out.print(rsmd.getColumnLabel(2) + "\t");
				else
					System.out.print(rsmd.getColumnLabel(i) + "\t");
			}
			System.out.println("\n----------------------------------------------");
			
			//print column contents
			while (results.next()) {
				String EmployeeID = results.getString(1);
				String LName = results.getString(3);
				String FName = results.getString(2);
				String RoleID = results.getString(4);
				String TeamID = results.getString(5);

				System.out.format("%8s %8s, %8s %8s %8s\n",EmployeeID,LName,FName,RoleID,TeamID);
			}
			System.out.println("\n");
			results.close();
			rsmd = null;
			jdbcConn.getStatment().close();
			
		}catch (SQLException sqlExcept) {
			sqlExcept.printStackTrace();
		}
	}

	public void getStakeholders() 
	{
		//SQL: select * from StakeHolders
		try {
			jdbcConn.setStatment(jdbcConn.getConnection());
			ResultSet results = jdbcConn.getStatment().executeQuery("select * from StakeHolders;");
			ResultSetMetaData rsmd = results.getMetaData();
			
			int numberCols = rsmd.getColumnCount();
			
			//print column names
			for (int i = 1; i <= numberCols; i++) {
				if (i == 2)
					System.out.print(rsmd.getColumnLabel(3) + "\t");
				else if (i == 3)
					System.out.print(rsmd.getColumnLabel(2) + "\t");
				else
					System.out.print(rsmd.getColumnLabel(i) + "\t");
			}
			System.out.println("\n--------------------------------------");
			
			while (results.next()) {
				String StakeHolderID = results.getString(1);
				String FName = results.getString(2);
				String LName = results.getString(3);
				String RoleID = results.getString(4);

				System.out.format("%8s %8s %8s %8s\n",StakeHolderID,FName,LName,RoleID);
			}
			System.out.println("\n");
			results.close();
			rsmd = null;
			jdbcConn.getStatment().close();
			
		}catch (SQLException sqlExcept) {
			sqlExcept.printStackTrace();
		}
	}

	public void showAssignedEmployees() 
	{
		//Need to select a particular project by id
		//sql select * from employees where proj id = ?
	}

	public void showScrumTeam() 
	{
		//Need to be able to select a particular team by id
		//sql select * from employees where team id= ?
	}

	public void addEmployee() 
	{
		//temporary scanner
		Scanner in = new Scanner(System.in);
		
		//gather new employee information
		System.out.print("\nLast name: ");
		String lName = in.next();
		System.out.print("First name: ");
		String fName = in.next();
		
		//gather new employee role
		System.out.println("\n1. Software Engineer");
		System.out.println("2. Owner");
		System.out.println("3. Salesperson");
		System.out.print("Employee role: ");
		int roleID = in.nextInt();
		if (roleID == 3)
			roleID = 4;
		
		//SQL: insert into Employees
		try {
			//gather new employee team
			jdbcConn.setStatment(jdbcConn.getConnection());
			ResultSet results = jdbcConn.getStatment().executeQuery("select * from Teams;");
			
			int i = 1;
			System.out.print("\n");
			while (results.next()) {
				String TeamName = results.getString(2);
				
				System.out.println(i + ". " + TeamName);
			}
			
			System.out.print("\nAdd to team: ");
			int teamID = in.nextInt();
			
			//output
			results = jdbcConn.getStatment().executeQuery("select count(*) from Employees");
			jdbcConn.getStatment().execute("insert into Employees values ('" + results + "','" +
					fName + "','" + lName + "','" + roleID + "','" + teamID + "');");
			
			System.out.println("\n");
			results.close();
			jdbcConn.getStatment().close();
			
		}catch (SQLException sqlExcept) {
			sqlExcept.printStackTrace();
		}
	}

	public void modifyEmployee() 
	{
		//Need to be able to select a particular employee by id
		//sql update employee set () values() where employee id = ?
	}

	public void addStakeholder() 
	{
		//sql insert into stakeholders
	}

	public void modifyStakeholder() 
	{
		//Need to be able to select a particular stakeholder by id
		//sql update stakeholders set () values() where stakeholder id = ?
	}

}
