package proyecto.junit;


import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PruebasJUnitTest {

    @BeforeAll
    static void setup() {
        System.out.print("Setup...");
    }

    @BeforeEach
    public void preconditions() {
        System.out.print("Precondiciones...");
    }

    @Test
    public void AdditionTest() {
        System.out.println("test 1");
    }

    @Test
    public void test2() {
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
