package Validator;
import Exceptions.ValidatorException;
import Domain.Student;

import java.util.regex.Pattern;

public class StudentValidator implements IValidator<Student> {

    public void validate(Student s) throws ValidatorException {
        String regex="^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(regex);

        String errors="";
        if(s.getId().equals("") || s.getId() == null){
            //throw new ValidatorException("Id invalid\n");
            errors+="Id invalid\n";
        }
        if(s.getNume().equals("") || s.getNume()==null){
            //throw new ValidatorException("Nume invalid\n");
            errors+="Nume invalid\n";
        }
        if(s.getIndrumator().equals("") || s.getIndrumator()==null){
            //throw new ValidatorException("Nume invalid\n");
            errors+="Nume invalid\n";
        }
        if(s.getGrupa()<=0){
            //throw new ValidatorException("Grupa invalida\n");
            errors+="Grupa invalid\n";
        }
        if(s.getEmail().equals("") || s.getEmail()==null || !pattern.matcher(s.getEmail()).matches()){
            //throw new ValidatorException("Email invalid\n");
            errors+="Email invalid\n";
        }
        if (errors.length()!=0){
            throw  new ValidatorException(errors);
        }
    }
}