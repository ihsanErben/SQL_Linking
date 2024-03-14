
import java.sql.*;
import java.util.Scanner;

public class Link {

    String username = "root";
    String password = "";

    String host = "localhost";
    int port = 3306;
    String db_name = "basla";

    Connection connection = null;
    Statement statement = null;

    Scanner scn = new Scanner(System.in);

    public Link() {

        try {
            //    jdbc:mysql://localhost:3306/try
            String url = "jdbc:mysql://" + host + ":" + port + "/" + db_name;
            connection = DriverManager.getConnection(url, username, password);
            statement = connection.createStatement();
            System.out.println("- connection is avaliable -");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Link x = new Link();
        Scanner scn = new Scanner(System.in);
        int a = 1;

        while (a == 1) {
            System.out.println("-----------------------------------------------");
            System.out.println("1 - add");
            System.out.println("2 - delete");
            System.out.println("3 - update");
            System.out.println("4 - print");
            System.out.print("option: ");
            int option = scn.nextInt();

            switch (option) {
                case 0:
                    a = 0;
                    break;
                case 1:
                    x.add_employee();
                    break;
                case 2:
                    x.delete_employee();
                    break;
                case 3:
                    x.update_employee();
                    break;
                case 4:
                    x.print_employee();
                    break;
            }

        }
    }

    public void print_employee() {
        try {
            //    select * from students 
            String sorgu = "select * from employee";

            ResultSet rs = statement.executeQuery(sorgu);

            while (rs.next()) {

                int id = rs.getInt("id");
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                String email = rs.getString("email");

                System.out.println("id: " + id + ", name: " + name + ", surname: " + surname + ", email: " + email);
            }

        } catch (Exception e) {
            System.out.println("-- problem --");
        }
    }

    public void add_employee() {
        try {
            System.out.print("name: ");
            String name = scn.next();
            System.out.print("surname: ");
            String surname = scn.next();
            System.out.print("email: ");
            String email = scn.next();

            //   insert into employee (name,surname,email) values ('  '  ,  '  '   ,  '  '   ,   '  ')
            String sorgu = "insert into employee (name,surname,email) values ('" + name + "','" + surname + "','" + email + "')";

            statement.executeUpdate(sorgu);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update_employee() {
        try {
            System.out.print("emaili guncellenecek kisinin id: ");
            int guncelle = scn.nextInt();
            //     update employee set email = 'example@gmail.com'
            String sorgu = "update employee set email = 'example@gmail.com' where id = " + guncelle;

            int x = statement.executeUpdate(sorgu);
            System.out.println(x + " kisinin datasi guncellendi.");

        } catch (Exception e) {
            System.out.println("-- problem --");
        }
    }

    public void delete_employee() {
        try {
            System.out.print("silinecek kisinin id: ");
            int sil = scn.nextInt();
            //     delete from students where id = 72
            String sorgu = "delete from employee where id = " + sil;

            int x = statement.executeUpdate(sorgu);
            System.out.println("id : " + x + " listeden silindi.");
        } catch (Exception e) {
            System.out.println("-- problem --");
        }
    }

}
