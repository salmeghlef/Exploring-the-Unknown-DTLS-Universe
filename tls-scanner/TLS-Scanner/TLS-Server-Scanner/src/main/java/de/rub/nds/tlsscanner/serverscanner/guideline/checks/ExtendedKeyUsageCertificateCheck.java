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
import de.rub.nds.tlsscanner.serverscanner.probe.certificate.CertificateChain;
import de.rub.nds.tlsscanner.serverscanner.probe.certificate.CertificateReport;
import java.util.Objects;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ExtendedKeyUsageCertificateCheck extends CertificateGuidelineCheck {

    private ExtendedKeyUsageCertificateCheck() {
        super(null, null);
    }

    public ExtendedKeyUsageCertificateCheck(String name, RequirementLevel requirementLevel) {
        super(name, requirementLevel);
    }

    public ExtendedKeyUsageCertificateCheck(String name, RequirementLevel requirementLevel,
        boolean onlyOneCertificate) {
        super(name, requirementLevel, onlyOneCertificate);
    }

    public ExtendedKeyUsageCertificateCheck(String name, RequirementLevel requirementLevel,
        GuidelineCheckCondition condition, boolean onlyOneCertificate) {
        super(name, requirementLevel, condition, onlyOneCertificate);
    }

    @Override
    public GuidelineCheckResult evaluateChain(CertificateChain chain) {
        CertificateReport report = chain.getCertificateReportList().get(0);
        return new GuidelineCheckResult(TestResults.of(Boolean.TRUE.equals(report.getExtendedKeyUsageServerAuth())
            && Boolean.FALSE.equals(report.getExtendedKeyUsagePresent()))) {
            @Override
            public String display() {
                return Objects.equals(TestResults.TRUE, getResult())
                    ? "Certificate has extended key usage for server auth."
                    : "Certificate is missing extended key usage for server auth.";
            }
        };
    }

    @Override
    public String getId() {
        return "ExtendedKeyUsage_" + getRequirementLevel();
    }

}
