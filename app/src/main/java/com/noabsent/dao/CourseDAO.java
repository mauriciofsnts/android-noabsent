package com.noabsent.dao;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.noabsent.beans.Course;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CourseDAO {

    public Course[] getCourses() {

        Course[] courseArr;

        courseArr = new Course[4];

        courseArr[0] = new Course(1, "FUNDAMENTOS DE INTELIGÊNCIA ARTIFICIAL", "19:10", "21:50", "Segunda-Feira");
        courseArr[1] = new Course(2, "TRABALHO DE GRADUAÇÃO INTERDISCIPLINAR I", "19:10", "21:50", "Terça-Feira");
        courseArr[2] = new Course(3, "LINGUAGENS FORMAIS E AUTÔMATOS", "21:50", "23:00", "Quarta-Feira");
        courseArr[3] = new Course(4, "PROGRAMAÇÃO PARA DISPOSITIVOS MÓVEIS", "19:10", "23:00", "Quinta-Feira");


        return courseArr;
    }

    public Course getCourseByName(String name) {

        Course[] courseArr;

        courseArr = new Course[4];

        courseArr[0] = new Course(1, "FUNDAMENTOS DE INTELIGÊNCIA ARTIFICIAL", "19:10", "21:50", "Segunda-Feira");
        courseArr[1] = new Course(2, "TRABALHO DE GRADUAÇÃO INTERDISCIPLINAR I", "19:10", "21:50", "Terça-Feira");
        courseArr[2] = new Course(3, "LINGUAGENS FORMAIS E AUTÔMATOS", "21:50", "23:00", "Quarta-Feira");
        courseArr[3] = new Course(4, "PROGRAMAÇÃO PARA DISPOSITIVOS MÓVEIS", "19:10", "21:50", "Quinta-Feira");

        int position = 0;

        for (int i = 0; i < courseArr.length; i++) {

            if (courseArr[i].getName().equals(name)) {
                position = i;
            }

        }

        return courseArr[position];
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    public boolean checkCourse(Course c)  {

        boolean isValid = false;

        String dia = diaSemana();


        if (dia.equals(c.getDayOfWeek())) {

            DateTimeFormatter formatter = DateTimeFormat.forPattern("HH:mm");
            DateTime deHoras = formatter.parseDateTime(c.getStartTime());
            DateTime ateHoras = formatter.parseDateTime(c.getEndTime());

            Calendar rightNow = Calendar.getInstance();
            String getHoraAgra = rightNow.get(Calendar.HOUR_OF_DAY) + ":" + rightNow.get(Calendar.MINUTE);
            DateTime horaAgra = formatter.parseDateTime(getHoraAgra);

            boolean inicio = deHoras.isAfter(horaAgra);
            boolean fim = ateHoras.isBefore(horaAgra);

            if (!inicio && !fim) {
                isValid = true;
            }

        }

        return isValid;
    }

    private String diaSemana() {
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        String dia = null;
        switch (day) {
            case Calendar.SUNDAY:
                dia = "Domingo";
                break;
            case Calendar.MONDAY:
                dia = "Segunda-Feira";
                break;
            case Calendar.TUESDAY:
                dia = "Terça-Feira";
                break;
            case Calendar.WEDNESDAY:
                dia = "Quarta-Feira";
                break;
            case Calendar.THURSDAY:
                dia = "Quinta-Feira";
                break;
            case Calendar.FRIDAY:
                dia = "Sexta-Feira";
                break;
            case Calendar.SATURDAY:
                dia = "Sábado";
                break;
        }
        return dia;
    }

}
