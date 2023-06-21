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
import de.rub.nds.tlsscanner.core.guideline.GuidelineCheckCondition;
import de.rub.nds.tlsscanner.core.guideline.GuidelineCheckResult;
import de.rub.nds.tlsscanner.core.guideline.RequirementLevel;
import de.rub.nds.tlsscanner.serverscanner.guideline.results.CertificateVersionGuidelineCheckResult;
import de.rub.nds.tlsscanner.serverscanner.probe.certificate.CertificateChain;
import de.rub.nds.tlsscanner.serverscanner.probe.certificate.CertificateReport;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class CertificateVersionGuidelineCheck extends CertificateGuidelineCheck {

    private int version;

    private CertificateVersionGuidelineCheck() {
        super(null, null);
    }

    public CertificateVersionGuidelineCheck(String name, RequirementLevel requirementLevel, int version) {
        super(name, requirementLevel);
        this.version = version;
    }

    public CertificateVersionGuidelineCheck(String name, RequirementLevel requirementLevel, boolean onlyOneCertificate,
        int version) {
        super(name, requirementLevel, onlyOneCertificate);
        this.version = version;
    }

    public CertificateVersionGuidelineCheck(String name, RequirementLevel requirementLevel,
        GuidelineCheckCondition condition, boolean onlyOneCertificate, int version) {
        super(name, requirementLevel, condition, onlyOneCertificate);
        this.version = version;
    }

    @Override
    public GuidelineCheckResult evaluateChain(CertificateChain chain) {
        CertificateReport report = chain.getCertificateReportList().get(0);
        return new CertificateVersionGuidelineCheckResult(
            TestResults.of(this.version == report.getCertificate().getVersionNumber()),
            report.getCertificate().getVersionNumber());
    }

    @Override
    public String getId() {
        return "CertificateVersion_" + getRequirementLevel() + "_" + version;
    }

    public int getVersion() {
        return version;
    }

}
