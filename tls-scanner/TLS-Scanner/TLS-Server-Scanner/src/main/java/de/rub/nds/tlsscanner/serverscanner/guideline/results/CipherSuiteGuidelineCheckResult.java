/**
 * TLS-Server-Scanner - A TLS configuration and analysis tool based on TLS-Attacker
 *
 * Copyright 2017-2023 Ruhr University Bochum, Paderborn University, Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */

package de.rub.nds.tlsscanner.serverscanner.guideline.results;

import com.google.common.base.Joiner;
import de.rub.nds.scanner.core.constants.TestResult;
import de.rub.nds.tlsattacker.core.constants.CipherSuite;
import de.rub.nds.tlsscanner.core.guideline.GuidelineCheckResult;
import java.util.List;

public class CipherSuiteGuidelineCheckResult extends GuidelineCheckResult {

    private final List<CipherSuite> notRecommendedSuites;

    public CipherSuiteGuidelineCheckResult(TestResult result, List<CipherSuite> notRecommendedSuites) {
        super(result);
        this.notRecommendedSuites = notRecommendedSuites;
    }

    @Override
    public String display() {
        if (notRecommendedSuites.isEmpty()) {
            return "Only listed Cipher Suites are supported.";
        } else {
            return "The following Cipher Suites were supported but not recommended:\n"
                + Joiner.on('\n').join(notRecommendedSuites);
        }
    }

    public List<CipherSuite> getNotRecommendedSuites() {
        return notRecommendedSuites;
    }
}
