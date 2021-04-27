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
        String[] params={"1","nume","grupa","nume@gmail.com","prof"};
        try{
            studentXMLService.add(params);
        }catch (ValidatorException ex){
            System.out.println(ex.getMessage());
        }
        assertNull(studentXMLService.findOne("1"));

        String[] params2={"1","nume","2","nume@gmail.com","prof"};
        try{
            studentXMLService.add(params2);
        }catch (ValidatorException ex){
            System.out.println(ex.getMessage());
        }
        assertNotNull(studentXMLService.findOne("1"));
        studentXMLService.remove("1");

        String[] params3={"2","nume","-1","nume@gmail.com","prof"};
        try{
            studentXMLService.add(params3);
        }catch (ValidatorException ex){
            System.out.println(ex.getMessage());
        }
        assertNull(studentXMLService.findOne("1"));
        studentXMLService.remove("1");
    }

    @Test
    public void testAddStudentId(){
        StudentValidator vs=new StudentValidator();
        StudentXMLRepo strepo=new StudentXMLRepo(vs,"StudentiXML.xml");
        StudentXMLService studentXMLService = new StudentXMLService(strepo);
        String[] params={"1","nume","2","nume@gmail.com","prof"};
        try{
            studentXMLService.add(params);
        }catch (ValidatorException ex){
            System.out.println(ex.getMessage());
        }

        String[] params2={"1","nume2","2","nume@gmail.com","prof2"};
        try{
            studentXMLService.add(params2);
        }catch (ValidatorException ex){
            System.out.println(ex.getMessage());
        }

        assertEquals(1, studentXMLService.getSize());
        Student student = studentXMLService.findOne("1");
        assertEquals("1",student.getId());
        assertEquals("nume",student.getNume());
        assertEquals("nume@gmail.com",student.getEmail());
        assertEquals("prof",student.getIndrumator());

        boolean fails=false;
        String[] params3={"","nume2","2","nume@gmail.com","prof2"};
        try{
            studentXMLService.add(params3);
        }catch (ValidatorException ex){
            System.out.println(ex.getMessage());
            fails=true;
        }
        assertTrue(fails);

        fails=false;
        String[] params4={null,"nume2","2","nume@gmail.com","prof2"};
        try{
            studentXMLService.add(params4);
        }catch (ValidatorException | NullPointerException ex){
            System.out.println(ex.getMessage());
            fails=true;
        }
        assertTrue(fails);
    }

    @Test
    public void testAddStudentName(){
        StudentValidator vs=new StudentValidator();
        StudentXMLRepo strepo=new StudentXMLRepo(vs,"StudentiXML.xml");
        StudentXMLService studentXMLService = new StudentXMLService(strepo);

        boolean fails=false;
        String[] params={"1","nume","2","nume@gmail.com","prof"};
        try{
            studentXMLService.add(params);
        }catch (ValidatorException ex){
            System.out.println(ex.getMessage());
            fails=true;
        }
        assertFalse(fails);

        fails=false;
        String[] params2={"2","","2","nume@gmail.com","prof2"};
        try{
            studentXMLService.add(params2);
        }catch (ValidatorException ex){
            System.out.println(ex.getMessage());
            fails=true;
        }
        assertTrue(fails);

        fails=false;
        String[] params3={"2",null,"2","nume@gmail.com","prof2"};
        try{
            studentXMLService.add(params3);
        }catch (NullPointerException | ValidatorException ex){
            System.out.println(ex.getMessage());
            fails=true;
        }
        assertTrue(fails);
    }

    @Test
    public void testAddStudentProf(){
        StudentValidator vs=new StudentValidator();
        StudentXMLRepo strepo=new StudentXMLRepo(vs,"StudentiXML.xml");
        StudentXMLService studentXMLService = new StudentXMLService(strepo);

        boolean fails=false;
        String[] params={"1","nume","2","nume@gmail.com","prof"};
        try{
            studentXMLService.add(params);
        }catch (ValidatorException ex){
            System.out.println(ex.getMessage());
            fails=true;
        }
        assertFalse(fails);

        fails=false;
        String[] params2={"2","nume","2","nume@gmail.com",""};
        try{
            studentXMLService.add(params2);
        }catch (ValidatorException ex){
            System.out.println(ex.getMessage());
            fails=true;
        }
        assertTrue(fails);

        fails=false;
        String[] params3={"2","nume","2","nume@gmail.com",null};
        try{
            studentXMLService.add(params3);
        }catch (ValidatorException | NullPointerException ex){
            System.out.println(ex.getMessage());
            fails=true;
        }
        assertTrue(fails);
    }

    @Test
    public void testAddStudentEmail(){
        StudentValidator vs=new StudentValidator();
        StudentXMLRepo strepo=new StudentXMLRepo(vs,"StudentiXML.xml");
        StudentXMLService studentXMLService = new StudentXMLService(strepo);

        boolean fails=false;
        String[] params={"1","nume","2","nume@gmail.com","prof"};
        try{
            studentXMLService.add(params);
        }catch (ValidatorException ex){
            System.out.println(ex.getMessage());
            fails=true;
        }
        assertFalse(fails);

        fails=false;
        String[] params2={"2","nume","2","email","prof"};
        try{
            studentXMLService.add(params2);
        }catch (ValidatorException ex){
            System.out.println(ex.getMessage());
            fails=true;
        }
        assertTrue(fails);

        fails=false;
        String[] params3={"2","nume","2","","prof"};
        try{
            studentXMLService.add(params3);
        }catch (ValidatorException ex){
            System.out.println(ex.getMessage());
            fails=true;
        }
        assertTrue(fails);

        fails=false;
        String[] params4={"2","nume","2",null,"prof"};
        try{
            studentXMLService.add(params4);
        }catch (ValidatorException | NullPointerException ex){
            System.out.println(ex.getMessage());
            fails=true;
        }
        assertTrue(fails);
    }
}
