package com.noabsent.dao;

import com.noabsent.beans.Student;

public class StudentDAO {

    public boolean LoginUser(String rgm, String password){

        // Declarando o array de estudantes
        Student[] studentArr;

        // Alocando espaço para 4 estudantes
        studentArr = new Student[4];

        studentArr[0] = new Student(1, "19611111", "pass123123", "Rick Sanchez", "sanchez@mail.com");
        studentArr[1] = new Student(4, "19", "2pac", "2Pac", "2pac@mail.com");
        studentArr[2] = new Student(2, "19622222", "oodogg", "Snoop Dogg", "doggdogg@mail.com");
        studentArr[3] = new Student(3, "19633333", "slimshady", "Eminem", "realSlim@mail.com");


        // Boolean para indicar se os dados conferem com a base
        boolean validado = false;

        for (int i = 0; i < studentArr.length; i++) {

            String userRGM = studentArr[i].getRgm();
            String userPass = studentArr[i].getPassword();

            // Se o RGM e a senha informadas correspondem aos do usuários salvos
            boolean isTrue = (userRGM.equals(rgm)) && (userPass.equals(password));

            if(isTrue){
                validado = true;
                break;
            }

        }


        return validado;
    }


}

