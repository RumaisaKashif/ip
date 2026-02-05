package pook;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class StorageTest {
    private static final String TEST_FILE_PATH = "data/temp.txt";
    
    @Test
    public void loadTask_validFilePath_loadsTaskCorrectly() {
        Storage storage = new Storage(TEST_FILE_PATH);
        List<Task> tasks = new ArrayList<>();
        storage.loadFile(tasks);
        Task task = tasks.get(0);
        assertTrue(task instanceof Deadline);
        assertEquals("X", task.getStatusIcon());
        assertEquals("[D][X] submit tut (by: Jan 12 2018, 1:00 am)", task.toString());
    }
}
