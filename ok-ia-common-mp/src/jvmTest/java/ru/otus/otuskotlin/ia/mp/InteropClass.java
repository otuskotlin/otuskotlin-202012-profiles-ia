package ru.otus.otuskotlin.ia.mp;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class InteropClass {

    @Test
    public void sharedModel() {
        SharedModel sm1 = new SharedModel();
        SharedModel sm2 = new SharedModel("id", "name");

        assertEquals("id", sm2.getId());
    }

    @Test
    public void propTest() {
        String myName = new SharedModel().getMyName();

        assertEquals("my name", myName);
    }
}
