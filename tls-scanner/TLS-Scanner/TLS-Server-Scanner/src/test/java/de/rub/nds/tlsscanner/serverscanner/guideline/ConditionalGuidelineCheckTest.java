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
import de.rub.nds.tlsscanner.core.constants.TlsAnalyzedProperty;
import de.rub.nds.tlsscanner.core.guideline.GuidelineCheckCondition;
import de.rub.nds.tlsscanner.core.guideline.GuidelineCheckResult;
import de.rub.nds.tlsscanner.serverscanner.guideline.checks.AnalyzedPropertyGuidelineCheck;
import de.rub.nds.tlsscanner.serverscanner.report.ServerReport;
import org.junit.Assert;
import org.junit.Test;

public class ConditionalGuidelineCheckTest {

    @Test
    public void testPositive() {
        ServerReport report = new ServerReport("test", 443);
        report.putResult(TlsAnalyzedProperty.SUPPORTS_TLS13_PSK, false);
        report.putResult(TlsAnalyzedProperty.SUPPORTS_TLS_1_3, true);

        GuidelineCheckCondition condition =
            new GuidelineCheckCondition(TlsAnalyzedProperty.SUPPORTS_TLS_1_3, TestResults.TRUE);
        AnalyzedPropertyGuidelineCheck check = new AnalyzedPropertyGuidelineCheck(null, null, condition,
            TlsAnalyzedProperty.SUPPORTS_TLS13_PSK, TestResults.TRUE);

        GuidelineCheckResult result = null;

        if (check.passesCondition(report)) {
            result = check.evaluate(report);
        }

        Assert.assertNotNull(result);
        Assert.assertEquals(TestResults.FALSE, result.getResult());
    }

    @Test
    public void testNegative() {
        ServerReport report = new ServerReport("test", 443);
        report.putResult(TlsAnalyzedProperty.SUPPORTS_TLS13_PSK, false);
        report.putResult(TlsAnalyzedProperty.SUPPORTS_TLS_1_3, false);

        GuidelineCheckCondition condition =
            new GuidelineCheckCondition(TlsAnalyzedProperty.SUPPORTS_TLS_1_3, TestResults.TRUE);
        AnalyzedPropertyGuidelineCheck check = new AnalyzedPropertyGuidelineCheck(null, null, condition,
            TlsAnalyzedProperty.SUPPORTS_TLS13_PSK, TestResults.TRUE);

        GuidelineCheckResult result = null;

        if (check.passesCondition(report)) {
            result = check.evaluate(report);
        }

        Assert.assertNull(result);
    }
}
