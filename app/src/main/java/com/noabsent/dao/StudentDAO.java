package com.noabsent.dao;

import com.noabsent.beans.Student;

public class StudentDAO {

    public boolean LoginUser(String rgm, String password){

        // Boolean para indicar se os dados conferem com a base
        boolean validado = false;

            // Declarando o array de estudantes
            Student[] studentArr;

            // Alocando espaço para 4 estudantes
            studentArr = new Student[6];

            studentArr[0] = new Student(1, "19479654", "123", "Luiz Eduardo Ribeiro dos Santos");
            studentArr[1] = new Student(2, "19618492", "123", "Mauricio Ferraz dos Santos");
            studentArr[2] = new Student(3, "20167067", "123", "Rodrigo Costa Servilha Reina");
            studentArr[3] = new Student(4, "19463448", "123", "Savyo Yuri de Melo Pereira");
            studentArr[4] = new Student(5, "19572646", "123", "Victor Oliveira Hotts");
            studentArr[5] = new Student(5, "19641869", "123", "Vinicius de Lima Dutra");



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

