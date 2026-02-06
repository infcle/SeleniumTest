package proyecto.junit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.*;

public class PruebasJUnit {

    @BeforeAll
    static void setup() {
        System.out.print("Setup...");
    }

    @BeforeEach
    public void preconditions() {
        System.out.print("Precondiciones...");
    }

    @Test
    public void AdditionTest(){
        System.out.println("test 1");
    }

    @Test
    public void test2(){
        System.out.println("test 2");
    }

    @AfterEach
    public void postconditions() {
        System.out.println("postcondiciones...");
    }

    @AfterAll
    public static void teardown() {
        System.out.print("Teardown...");
    }
}
