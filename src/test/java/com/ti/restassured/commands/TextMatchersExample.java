package com.ti.restassured.commands;

import com.google.common.collect.Lists;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TextMatchersExample {

    //https://codingnconcepts.com/java/unit-test-with-hamcrest-assertthat/#text-matchers-for-string
    // https://www.swtestacademy.com/hamcrest-matchers/
    // https://github.com/leveluplunch/levelup-java-examples/blob/master/src/test/java/com/levelup/java/hamcrest/TextMatchers.java
    @Test
    public void string_is_empty () {

        String favoriteCereal = "";
        assertThat(favoriteCereal, emptyString());
    }

    @Test
    public void string_is_empty_or_null () {
        String favoriteCereal = null;
        assertThat(favoriteCereal, nullValue());
    }

    @Test
    public void string_equal_to () {

        String favoriteCereal = "cinnamon life";
        assertThat(favoriteCereal, equalTo("cinnamon life"));
    }

    @Test
    public void string_equal_to_ignoring_case () {

        String favoriteCereal = "CINNAMON LIFE";
        assertThat(favoriteCereal, equalToIgnoringCase("cinnamon life"));
    }

    @Test
    public void string_equal_to_ignoring_whitespace () {

        String favoriteCereal = "CINNAMON LIFE          ";
        assertThat(favoriteCereal.toLowerCase(), Matchers.equalToCompressingWhiteSpace("cinnamon life"));
    }

    @Test
    public void string_contains () {

        String cereal = "mini wheats";
        assertThat(cereal, containsString("mini"));
    }

    @Test
    public void string_ends_with () {

        String cereal = "corn flakes";
        assertThat(cereal, endsWith("s"));
    }

    @Test
    public void string_starts_with () {

        String cereal = "honey smacks";
        assertThat(cereal, startsWith("honey"));
    }

    @Test
    public void string_has_order () {

        String cereal = "apple jacks";
        assertThat(cereal, stringContainsInOrder(Lists.newArrayList("apple", "jacks")));
    }
}
