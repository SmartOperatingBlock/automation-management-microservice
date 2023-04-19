/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package infrastructure.digitaltwins.query;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test for {@link AdtQuery}.
 */
class AdtQueryTest {
    @Test
    @DisplayName("It should be possible to specify a simple query to obtain all digital twins")
    void testSimpleQuery() {
        final String query = new AdtQuery()
                .select("*")
                .fromDigitalTwins("T")
                .toQuery();
        final String expectedQuery = "SELECT * FROM DIGITALTWINS T";
        assertEquals(query, expectedQuery);
    }

    @Test
    @DisplayName("It should be possible to specify a complete query")
    void testComplexQuery() {
        final String query = new AdtQuery()
                .selectTop(1, "T.prop1")
                .fromDigitalTwins("T")
                .joinRelationship("CT", "T", "relationship1")
                .where("IS_OF_MODEL(T, 'model1')")
                .and("CT.$dtId = 'id1'")
                .toQuery();
        final String expectedQuery = "SELECT TOP(1) T.prop1"
                + " FROM DIGITALTWINS T"
                + " JOIN CT RELATED T.relationship1"
                + " WHERE IS_OF_MODEL(T, 'model1')"
                + " AND CT.$dtId = 'id1'";
        assertEquals(query, expectedQuery);
    }
}
