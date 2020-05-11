package Digitact.Backend.Storage;

import Digitact.Backend.Model.Admin;
import Digitact.Backend.Model.User;
import junit.framework.TestCase;
import org.junit.BeforeClass;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.*;

@SpringBootTest
public class StorageTests {
    User u1;
    Admin a1;

    // assigning the values
    protected void setUp() {
        new Storage();
        a1 = new Admin("firstee", "lastee");
        u1 = new Admin("firstU", "lastU");
    }

    // test method to add user to DB
    @Test
    public void testAddUserToDB() {
        /*
        setUp();
        Storage.addUserToDB(u1);
        assertThat(1).isEqualTo(Storage.getDB().size());
        assertTrue(Storage.getDB().contains(u1));
        assertFalse(Storage.getDB().contains(a1));
        Storage.addUserToDB(a1);
        assertThat(2).isEqualTo(Storage.getDB().size());
        assertTrue(Storage.getDB().contains(a1));
        for (int i = 0; i <10 ; i++) {
            Storage.addUserToDB(u1);
            assertThat(2).isEqualTo(Storage.getDB().size());
        }

         */
    }
}
