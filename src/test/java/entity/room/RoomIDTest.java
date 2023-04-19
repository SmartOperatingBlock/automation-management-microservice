/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package entity.room;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


/**
 * Test for the {@link RoomID} value object.
 */
class RoomIDTest {
    private final RoomID roomID = new RoomID("id1");
    private final RoomID roomIDDifferentRef = new RoomID("id1");
    private final RoomID differentroomID = new RoomID("id2");

    @Test
    @DisplayName("Room ID must not be empty")
    void testRoomIDEmpty() {
        assertThrows(IllegalArgumentException.class, () -> new RoomID(""));
    }

    @Test
    @DisplayName("two RoomID must be equal on the base of their fields (being a value object),"
                 + "not on the base of their reference.")
    void testRoomIDEquals() {
        assertEquals(roomID, roomIDDifferentRef);
    }

    @Test
    @DisplayName("two equal RoomID must have the same hash code")
    void testRoomIDEqualHashCode() {
        assertEquals(roomID.hashCode(), roomIDDifferentRef.hashCode());
    }

    @Test
    @DisplayName("two RoomID with different ids must not be equal")
    void testRoomIDNotEqual() {
        assertNotEquals(roomID, differentroomID);
    }
}
