package com.appchallenge.ml_app_challenge.utils;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;

import static org.junit.Assert.*;

/**
 * Created by nikhilthiruvengadam on 12/21/17.
 */
public class DateUtilsTest {
    @Mock
    DateUtils dateUtils;

    @Test
    public void formatDate() throws Exception {
        assertNotNull(dateUtils.formatDate(null)); //Null date

        assertNotNull(dateUtils.formatDate("")); //Empty date

        assertEquals(dateUtils.formatDate("2017-12-21"), "Thu, December 21, 2017");

        assertNotNull(dateUtils.formatDate("2017-12"), "Thu, December 21, 2017"); //Incorrect date

        assertNotEquals(dateUtils.formatDate("12-21-2017"), "Thu, December 21, 2017");

        assertNotEquals(dateUtils.formatDate("12-21-2017"), "T, December 21, 2017");
    }

    @Test
    public void isValidDate() throws Exception {
        assertNotNull(dateUtils.isValidDate(null)); //Null date

        assertNotNull(dateUtils.isValidDate("")); //Empty date

        assertFalse(dateUtils.isValidDate(null));

        assertFalse(dateUtils.isValidDate(""));

        assertTrue(dateUtils.isValidDate("21-12-2017")); //Correct Date

        assertTrue(dateUtils.isValidDate("2017-12-21")); //Correct Date

        assertFalse(dateUtils.isValidDate("2017-12-2017")); //InCorrect date

        assertFalse(dateUtils.isValidDate("2017-12-30")); //Future date

        assertTrue(dateUtils.isValidDate("2017-12-01")); //Past date
    }

}