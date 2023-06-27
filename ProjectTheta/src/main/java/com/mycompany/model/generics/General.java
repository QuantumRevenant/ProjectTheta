package com.mycompany.model.generics;

import com.mycompany.controller.ProgramController;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class General {
    public static String clock() {
        ProgramController pc=ProgramController.getProgramController();
        LocalDateTime datetime= LocalDateTime.now();
       DateTimeFormatter formatTime = DateTimeFormatter.ofPattern("HH:mm:ss");
       DateTimeFormatter formatDay = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        return ("Date: "+datetime.format(formatDay)+"  -  Time: "+datetime.format(formatTime));

    }
}
