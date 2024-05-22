package org.example;

import org.example.config.ConfigurationFile;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
//        String name="krishna";
//        int roll=12;
//        String email="krishna@gamil.com";

        ApplicationContext context= new AnnotationConfigApplicationContext(ConfigurationFile.class);
//       JdbcTemplate jdbcTemplate= context.getBean(JdbcTemplate.class);

//       String sql="Insert into student values(?,?,?)";
//       int count=jdbcTemplate.update(sql,name,roll,email);
//       if(count>0){
//           System.out.println("insertion successful ");
//       }
//       else System.out.println("insertion failed ");
//       List<Student> studentList = jdbcTemplate.query("Select * from student", new StudentMapper());
//       for(Student std: studentList){
//           System.out.println(std.getName());
//           System.out.println(std.getRoll());
//           System.out.println(std.getEmail());
//       }

        NamedParameterJdbcTemplate template=context.getBean(NamedParameterJdbcTemplate.class);
        Map<String,Object> map= new HashMap<>();
        map.put("name","krishna");
        map.put("roll",122);
        map.put("email","kpchaulagain1999@gmail.com");
      int count= template.update("Insert into student values(:name,:roll,:email)",map);
      if(count>0){
          System.out.println("insertion successful");
      }
      else System.out.println("insertion failed");

    }
}