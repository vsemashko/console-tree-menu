package com.workfusion.menu;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.google.common.collect.Lists.newArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConsoleMenuTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    private ConsoleMenu consoleMenu;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        consoleMenu = new ConsoleMenu();
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void shouldDrawSameItemIfNoNestedElements() {
        // given
        List<String> items = newArrayList("Overview");

        // when
        consoleMenu.draw(items);

        // then
        verifyOutput("Overview");
    }

    @Test
    public void shouldDrawNestedMenuItems() {
        // given
        List<String> items = newArrayList("menu_Overview");

        // when
        consoleMenu.draw(items);

        // then
        verifyOutput("/menu\n" +
                "\tOverview");
    }

    @Test
    public void shouldCombineMenuItems() {
        // given
        List<String> items = newArrayList("electronics_accessoires_overview", "electronics_accessoires_phones");

        // when
        consoleMenu.draw(items);

        // then
        verifyOutput("/electronics\n" +
                "\t/accessoires\n" +
                "\t\toverview\n" +
                "\t\tphones"
        );
    }

    @Test
    public void shouldCombineNodesAndLeafsOnSameLevel() {
        // given
        List<String> items = newArrayList("electronics_phones", "electronics_accessoires_overview");

        // when
        consoleMenu.draw(items);

        // then
        verifyOutput("/electronics\n" +
                "\tphones\n" +
                "\t/accessoires\n" +
                "\t\toverview"
        );
    }

    @Test
    public void shouldDisplayElementsInCorrectOrder() {
        // given
        List<String> items = newArrayList("electronics_accessoires_overview", "books_detectives", "overview");

        // when
        consoleMenu.draw(items);

        // then
        verifyOutput("overview\n" +
                "/books\n" +
                "\tdetectives\n" +
                "/electronics\n" +
                "\t/accessoires\n" +
                "\t\toverview"
        );
    }

    @Test
    public void shouldCorrectlyProcessNodesAndLeafsWithSameNames() {
        // given
        List<String> items = newArrayList("electronics_phones", "electronics_phones_overview");

        // when
        consoleMenu.draw(items);

        // then
        verifyOutput("/electronics\n" +
                "\tphones\n" +
                "\t/phones\n" +
                "\t\toverview"
        );
    }

    @Test
    public void shouldPrintSortedMenuItems() {
        // given
        List<String> items = newArrayList(
                "electronics_accessoires_overview",
                "books_detectives",
                "books_professional_it_2020_overview",
                "overview",
                "electronics_accessoires_phones"
        );

        // when
        consoleMenu.draw(items);

        // then
        verifyOutput("overview\n" +
                "/books\n" +
                "\tdetectives\n" +
                "\t/professional\n" +
                "\t\t/it\n" +
                "\t\t\t/2020\n" +
                "\t\t\t\toverview\n" +
                "/electronics\n" +
                "\t/accessoires\n" +
                "\t\toverview\n" +
                "\t\tphones"
        );
    }

    private void verifyOutput(String expected) {
        assertEquals(expected, outContent.toString().trim());
    }

}
