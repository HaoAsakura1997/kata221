package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      userService.add(new User("Rubina", "Korabanova", "user1@mail.ru", new Car("Subaru",1 )));
      userService.add(new User("Alex", "Kokoulin", "user2@mail.ru", new Car("Mercedes", 9)));
      userService.add(new User("Madina", "Abisheva", "user3@mail.ru",new Car("Toyota",3)));
      userService.add(new User("Altyn", "Auezova", "user4@mail.ru", new Car("Lexus", 10)));

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car model = " + user.getCar().getModel());
         System.out.println("Car series = " + user.getCar().getSeries());
         System.out.println();
      }

      List<User> withThisCar = userService.usersWithThisCar("Subaru", 1);
      for (User u : withThisCar) {
         System.out.println(u.getId() + " " + u.getFirstName() + " " + u.getLastName());
      }

      context.close();
   }
}
