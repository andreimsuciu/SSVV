package ssvv.dreamteam;

import Domain.Student;
import Exceptions.ValidatorException;
import Repository.XMLFileRepository.StudentXMLRepo;
import Service.XMLFileService.StudentXMLService;
import Validator.StudentValidator;
import org.junit.Test;

import static org.junit.Assert.*;

public class MainTest {
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    @Test
    public void testAddStudentGroup(){
        StudentValidator vs=new StudentValidator();
        StudentXMLRepo strepo=new StudentXMLRepo(vs,"StudentiXML.xml");
        StudentXMLService studentXMLService = new StudentXMLService(strepo);
        String[] params={"1","nume","grupa","email","prof"};
        try{
            studentXMLService.add(params);
        }catch (ValidatorException ex){
            System.out.println(ex.getMessage());
        }
        assertNull(studentXMLService.findOne("1"));

        String[] params2={"1","nume","2","email","prof"};
        try{
            studentXMLService.add(params2);
        }catch (ValidatorException ex){
            System.out.println(ex.getMessage());
        }
        assertNotNull(studentXMLService.findOne("1"));
        studentXMLService.remove("1");
    }

    @Test
    public void testAddStudentId(){
        StudentValidator vs=new StudentValidator();
        StudentXMLRepo strepo=new StudentXMLRepo(vs,"StudentiXML.xml");
        StudentXMLService studentXMLService = new StudentXMLService(strepo);
        String[] params={"1","nume","2","email","prof"};
        try{
            studentXMLService.add(params);
        }catch (ValidatorException ex){
            System.out.println(ex.getMessage());
        }

        String[] params2={"1","nume2","2","email2","prof2"};
        try{
            studentXMLService.add(params);
        }catch (ValidatorException ex){
            System.out.println(ex.getMessage());
        }

        assertEquals(1, studentXMLService.getSize());
        Student student = studentXMLService.findOne("1");
        assertEquals("1",student.getId());
        assertEquals("nume",student.getNume());
        assertEquals("email",student.getEmail());
        assertEquals("prof",student.getIndrumator());
    }
}
