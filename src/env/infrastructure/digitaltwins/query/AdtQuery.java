/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package infrastructure.digitaltwins.query;

/**
 * Helper class to construct query for Azure Digital Twins service.
 */
public class AdtQuery {
    private final String query;

    /**
     * Default constructor.
     */
    public AdtQuery() {
        this.query = "";
    }

    /**
     * Private constructor used to create the query.
     * @param query the part of the query to pre-pend.
     */
    private AdtQuery(final String query) {
        this.query = query;
    }

    /**
     * Select query considering returned elements.
     * @param elements the elements to return from the query
     * @return the updated query
     */
    public AdtQuery select(final String... elements) {
        return new AdtQuery("SELECT " + String.join(", ", elements));
    }

    /**
     * Select query but return only numberOfElements top elements.
     * @param numberOfElementsToSelect the number of top elements to return
     * @param elements the elements to return from the query
     * @return the updated query
     */
    public AdtQuery selectTop(final int numberOfElementsToSelect, final String... elements) {
        return new AdtQuery("SELECT TOP(" + numberOfElementsToSelect + ") " + String.join(", ", elements));
    }

    /**
     * Specify the FROM digital twins clause with its alias. This alias can be used later to refer to it.
     * @param alias the alias for the digital twin
     * @return the updated query
     */
    public AdtQuery fromDigitalTwins(final String alias) {
        return new AdtQuery(this.query + " FROM DIGITALTWINS " + alias);
    }

    /**
     * Join the query with a relationship, specified by its relationshipName of the srcAlias digital twin.
     * Specify also the dstAlias used to describe the destination digital twin.
     * @param dstAlias the alias of the target of the relationship
     * @param srcAlias the alias of the source of the relationship
     * @param relationshipName the name of the relationship to join
     * @return the updated query
     */
    public AdtQuery joinRelationship(final String dstAlias, final String srcAlias, final String relationshipName) {
        return new AdtQuery(this.query + " JOIN " + dstAlias + " RELATED " + srcAlias + "." + relationshipName);
    }

    /**
     * Where clause to include in the query.
     * @param whereClause the where clause
     * @return the updated query
     */
    public AdtQuery where(final String whereClause) {
        return new AdtQuery(this.query + " WHERE " + whereClause);
    }

    /**
     * Chain in and another where clause.
     * @param whereClause the where clause to chain
     * @return the updated query
     */
    public AdtQuery and(final String whereClause) {
        return new AdtQuery(this.query + " AND " + whereClause);
    }

    /**
     * Method used to export the final query.
     * @return the query
     */
    public String toQuery() {
        return this.query;
    }
}
