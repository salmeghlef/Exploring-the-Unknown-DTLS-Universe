/**
 * TLS-Server-Scanner - A TLS configuration and analysis tool based on TLS-Attacker
 *
 * Copyright 2017-2023 Ruhr University Bochum, Paderborn University, Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */

package de.rub.nds.tlsscanner.serverscanner.guideline;

import de.rub.nds.scanner.core.constants.TestResults;
import de.rub.nds.tlsscanner.core.guideline.GuidelineCheckResult;
import de.rub.nds.tlsscanner.serverscanner.guideline.checks.CertificateAgilityGuidelineCheck;
import de.rub.nds.tlsscanner.serverscanner.report.ServerReport;
import org.junit.Assert;
import org.junit.Test;

public class CertificateAgilityGuidelineCheckTest {

    @Test
    public void testNegative() {
        ServerReport report = new ServerReport("test", 443);

        CertificateAgilityGuidelineCheck check = new CertificateAgilityGuidelineCheck(null, null);

        GuidelineCheckResult result = check.evaluate(report);

        Assert.assertEquals(TestResults.FALSE, result.getResult());
    }
}
