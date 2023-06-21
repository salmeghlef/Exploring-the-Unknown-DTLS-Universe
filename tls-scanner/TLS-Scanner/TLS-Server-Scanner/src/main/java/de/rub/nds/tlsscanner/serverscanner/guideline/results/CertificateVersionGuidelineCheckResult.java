/**
 * TLS-Server-Scanner - A TLS configuration and analysis tool based on TLS-Attacker
 *
 * Copyright 2017-2023 Ruhr University Bochum, Paderborn University, Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */

package de.rub.nds.tlsscanner.serverscanner.guideline.results;

import de.rub.nds.scanner.core.constants.TestResult;
import de.rub.nds.tlsscanner.core.guideline.GuidelineCheckResult;

public class CertificateVersionGuidelineCheckResult extends GuidelineCheckResult {

    private final int version;

    public CertificateVersionGuidelineCheckResult(TestResult result, int version) {
        super(result);
        this.version = version;
    }

    @Override
    public String display() {
        return "Certificate has Version " + version;
    }

    public int getVersion() {
        return version;
    }
}
