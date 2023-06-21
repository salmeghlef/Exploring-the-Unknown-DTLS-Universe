/**
 * TLS-Server-Scanner - A TLS configuration and analysis tool based on TLS-Attacker
 *
 * Copyright 2017-2023 Ruhr University Bochum, Paderborn University, Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */

package de.rub.nds.tlsscanner.serverscanner.guideline.checks;

import de.rub.nds.scanner.core.constants.TestResults;
import de.rub.nds.tlsattacker.core.constants.SignatureAndHashAlgorithm;
import de.rub.nds.tlsattacker.core.crypto.keys.CustomPublicKey;
import de.rub.nds.tlsscanner.core.guideline.GuidelineCheck;
import de.rub.nds.tlsscanner.core.guideline.GuidelineCheckCondition;
import de.rub.nds.tlsscanner.core.guideline.GuidelineCheckResult;
import de.rub.nds.tlsscanner.core.guideline.RequirementLevel;
import de.rub.nds.tlsscanner.serverscanner.guideline.results.CertificateAgilityGuidelineCheckResult;
import de.rub.nds.tlsscanner.serverscanner.probe.certificate.CertificateChain;
import de.rub.nds.tlsscanner.serverscanner.probe.certificate.CertificateReport;
import de.rub.nds.tlsscanner.serverscanner.report.ServerReport;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Checks if the server support the use of multiple server certificates with their associated private keys to support
 * algorithm and key size agility.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class CertificateAgilityGuidelineCheck extends GuidelineCheck<ServerReport> {

    private CertificateAgilityGuidelineCheck() {
        super(null, null);
    }

    public CertificateAgilityGuidelineCheck(String name, RequirementLevel requirementLevel) {
        super(name, requirementLevel);
    }

    public CertificateAgilityGuidelineCheck(String name, RequirementLevel requirementLevel,
        GuidelineCheckCondition condition) {
        super(name, requirementLevel, condition);
    }

    @Override
    public GuidelineCheckResult evaluate(ServerReport report) {
        List<CertificateChain> chains = report.getCertificateChainList();
        if (chains == null || chains.size() < 2) {
            return new CertificateAgilityGuidelineCheckResult(TestResults.FALSE);
        }
        CertificateReport firstReport = chains.get(0).getCertificateReportList().get(0);
        SignatureAndHashAlgorithm firstAlg = firstReport.getSignatureAndHashAlgorithm();
        Integer firstKey = null;
        if (firstReport.getPublicKey() instanceof CustomPublicKey) {
            firstKey = ((CustomPublicKey) firstReport.getPublicKey()).keySize();
        }
        for (int i = 1; i < chains.size(); i++) {
            CertificateChain chain = chains.get(i);
            CertificateReport certReport = chain.getCertificateReportList().get(0);
            if (!firstAlg.equals(certReport.getSignatureAndHashAlgorithm())) {
                return new CertificateAgilityGuidelineCheckResult(TestResults.TRUE);
            }
            if (firstKey != null && certReport.getPublicKey() instanceof CustomPublicKey) {
                if (firstKey != ((CustomPublicKey) certReport.getPublicKey()).keySize()) {
                    return new CertificateAgilityGuidelineCheckResult(TestResults.TRUE);
                }
            }
        }
        return new CertificateAgilityGuidelineCheckResult(TestResults.FALSE);
    }

    @Override
    public String getId() {
        return "CertificateAgility_" + getRequirementLevel();
    }

}
