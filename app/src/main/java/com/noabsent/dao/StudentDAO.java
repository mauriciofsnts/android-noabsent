package com.noabsent.dao;

import com.noabsent.beans.Student;

public class StudentDAO {

    public boolean LoginUser(String rgm, String password){

        // Boolean para indicar se os dados conferem com a base
        boolean validado = false;



            // Declarando o array de estudantes
            Student[] studentArr;

            // Alocando espaço para 4 estudantes
            studentArr = new Student[4];

            studentArr[0] = new Student(1, "19611111", "pass123123", "Rick Sanchez", "sanchez@mail.com");
            studentArr[1] = new Student(2, "19", "123", "2Pac", "2pac@mail.com");
            studentArr[2] = new Student(3, "19622222", "oodogg", "Snoop Dogg", "doggdogg@mail.com");
            studentArr[3] = new Student(4, "19633333", "slimshady", "Eminem", "realSlim@mail.com");


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

