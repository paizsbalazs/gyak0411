package hu.nive.ujratervezes.kepesitovizsga;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UpperCaseLettersTest {

    @Test
    void getNumberOfUpperCase() {
        Assertions.assertEquals(8, new UpperCaseLetters().getNumberOfUpperCase("characters.txt"));
    }
}