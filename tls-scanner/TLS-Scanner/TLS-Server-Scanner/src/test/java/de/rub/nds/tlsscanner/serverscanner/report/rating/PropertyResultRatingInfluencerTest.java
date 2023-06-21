/**
 * TLS-Server-Scanner - A TLS configuration and analysis tool based on TLS-Attacker
 *
 * Copyright 2017-2023 Ruhr University Bochum, Paderborn University, Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */

package de.rub.nds.tlsscanner.serverscanner.report.rating;

import static org.junit.Assert.assertEquals;

import de.rub.nds.scanner.core.constants.TestResults;
import de.rub.nds.scanner.core.report.rating.PropertyResultRatingInfluencer;
import org.junit.Test;

public class PropertyResultRatingInfluencerTest {

    public PropertyResultRatingInfluencerTest() {
    }

    /**
     * Test of compareTo method, of class PropertyResultRatingInfluencer.
     */
    @Test
    public void testCompareTo() {
        PropertyResultRatingInfluencer ri1 = new PropertyResultRatingInfluencer(TestResults.TRUE, 200);
        PropertyResultRatingInfluencer ri2 = new PropertyResultRatingInfluencer(TestResults.TRUE, -200);
        assertEquals(1, ri1.compareTo(ri2));
        assertEquals(-1, ri2.compareTo(ri1));

        ri2 = new PropertyResultRatingInfluencer(TestResults.TRUE, -200, 100);
        assertEquals(1, ri1.compareTo(ri2));

        ri2 = new PropertyResultRatingInfluencer(TestResults.TRUE, 200, 100);
        assertEquals(1, ri1.compareTo(ri2));

        ri2 = new PropertyResultRatingInfluencer(TestResults.TRUE, 300, 100);
        assertEquals(1, ri1.compareTo(ri2));

        ri1 = new PropertyResultRatingInfluencer(TestResults.TRUE, 200, 200);
        assertEquals(1, ri1.compareTo(ri2));

        ri1 = new PropertyResultRatingInfluencer(TestResults.TRUE, 300, 100);
        assertEquals(0, ri1.compareTo(ri2));
    }

}
