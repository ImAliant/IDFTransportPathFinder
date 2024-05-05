package fr.u_paris.gla.crazytrip.io;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class TestTimeFormat {


    @Test
    void testExampletime() {
        String time = "10:00";
        int result  =0;

   result = TimeFormat.convertToSeconds(time);

assert (result == 600);

    }

    @Test
    void testExampletime2() {
        String time = "11,160:00";
      
       assertThrows(Exception.class, () -> {
           TimeFormat.convertToSeconds(time);
       });
   
      
    }
    
}
