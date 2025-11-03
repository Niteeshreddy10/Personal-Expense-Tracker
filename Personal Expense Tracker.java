import java.sql.*;
import java.util.Scanner;


public class ExpenseTracker {
static final String DB_URL = "jdbc:mysql://localhost:3306/expensedb";
static final String USER = "root";
static final String PASS = "password";


public static void main(String[] args) {
Scanner sc = new Scanner(System.in);
try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
while (true) {
System.out.println("---- Personal Expense Tracker ----");
System.out.println("1. Add Expense\n2. View Expenses\n3. Update Expense\n4. Delete Expense\n5. Exit");
System.out.print("Enter your choice: ");
int choice = sc.nextInt();
sc.nextLine(); // consume newline
switch (choice) {
case 1:
addExpense(conn, sc);
break;
case 2:
viewExpenses(conn);
break;
case 3:
updateExpense(conn, sc);
break;
case 4:
deleteExpense(conn, sc);
break;
case 5:
System.out.println("Exiting...");
return;
default:
System.out.println("Invalid choice.");
}
}
} catch (SQLException e) {
e.printStackTrace();
}
}


static void addExpense(Connection conn, Scanner sc) throws SQLException {
System.out.print("Enter Date (YYYY-MM-DD): ");
String date = sc.nextLine();
System.out.print("Enter Category: ");
String category = sc.nextLine();
System.out.print("Enter Description: ");
String desc = sc.nextLine();
System.out.print("Enter Amount: ");
double amount = sc.nextDouble();
sc.nextLine();


String sql = "INSERT INTO expenses(date, category, description, amount) VALUES (?, ?, ?, ?)";
try (PreparedStatement pst = conn.prepareStatement(sql)) {
pst.setString(1, date);
pst.setString(2, category);
pst.setString(3, desc);
pst.setDouble(4, amount);
pst.executeUpdate();
System.out.println("Expense added successfully!");
}
}


}