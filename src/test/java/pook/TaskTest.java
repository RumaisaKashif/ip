package pook;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TaskTest {

    static class TaskStub extends Task {
        TaskStub(String description) {
            super(description);
        }

        @Override
        public String toStorableString() {
            return "T | " + getStatusNumber() + " | " + description;
        }
    }

    @Test
    void getStatusIcon_newTask_isNotDoneByDefault() {
        Task task = new TaskStub("read book");
        assertEquals(" ", task.getStatusIcon());
    }
}

