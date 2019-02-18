import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Main {
    Scanner in;
    public static void main(String[] args) {
        Main m = new Main();
        m.testDatabase();
    }

    private String insert(String n, String s, int a){
        return String.format("INSERT INTO users (name, sername, age) VALUES ('%s','%s','%d');", n, s, a);
    }

    private void testDatabase() {
        try {
            in = new Scanner(System.in);
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5432/Users";
            String login = "postgres";
            String password = "1111";
            Connection con = DriverManager.getConnection(url, login, password);
            try {
                Statement stmt = con.createStatement();
                while (true) {
                    System.out.println("Меню:");
                    System.out.println("1) Вывести всех пользователей");
                    System.out.println("2) Найти пользователя по имени");
                    System.out.println("3) Добавить пользователя");
                    System.out.println("4) Выход");
                    System.out.println("Введите цифру - пункт меню");

                    int command = in.nextInt();
                    if (command == 4){
                        break;
                    }
                    switch (command) {
                        case 1:
                            ResultSet rs = stmt.executeQuery("SELECT * FROM users");
                            while (rs.next()) {
                                String str = rs.getString(4) + ") " + rs.getString(1) + rs.getString(2) + rs.getString(3);
                                System.out.println(str);
                            }
                            rs.close();
                            break;
                        case 2:
                            System.out.println("Введите имя");
                            String name = in.next();
                            ResultSet res = stmt.executeQuery("SELECT * FROM users WHERE name=" + "\'" + name +"\'");
                            while (res.next()) {
                                String str = res.getString(4) + ") " +
                                        res.getString(1) + res.getString(2) + res.getString(3);
                                System.out.println(str);
                            }
                            res.close();
                            break;
                        case 3:
                            System.out.println("Введите Имя Фамилия и возраст");
                            String n = in.next(), s = in.next();
                            int a = in.nextInt();
                            stmt.execute(insert(n, s, a));
                            System.out.println("Пользователь добавлен");
                            break;
                    }
                }
                stmt.close();
            } finally {

                con.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}