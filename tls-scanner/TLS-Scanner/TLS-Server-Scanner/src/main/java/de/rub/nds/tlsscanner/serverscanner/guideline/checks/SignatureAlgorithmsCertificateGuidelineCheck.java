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
import de.rub.nds.tlsattacker.core.constants.SignatureAlgorithm;
import de.rub.nds.tlsscanner.core.guideline.GuidelineCheckCondition;
import de.rub.nds.tlsscanner.core.guideline.GuidelineCheckResult;
import de.rub.nds.tlsscanner.core.guideline.RequirementLevel;
import de.rub.nds.tlsscanner.serverscanner.guideline.results.SignatureAlgorithmsGuidelineCheckResult;
import de.rub.nds.tlsscanner.serverscanner.probe.certificate.CertificateChain;
import de.rub.nds.tlsscanner.serverscanner.probe.certificate.CertificateReport;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class SignatureAlgorithmsCertificateGuidelineCheck extends CertificateGuidelineCheck {

    private List<SignatureAlgorithm> recommendedAlgorithms;

    private SignatureAlgorithmsCertificateGuidelineCheck() {
        super(null, null);
    }

    public SignatureAlgorithmsCertificateGuidelineCheck(String name, RequirementLevel requirementLevel,
        List<SignatureAlgorithm> recommendedAlgorithms) {
        super(name, requirementLevel);
        this.recommendedAlgorithms = recommendedAlgorithms;
    }

    public SignatureAlgorithmsCertificateGuidelineCheck(String name, RequirementLevel requirementLevel,
        boolean onlyOneCertificate, List<SignatureAlgorithm> recommendedAlgorithms) {
        super(name, requirementLevel, onlyOneCertificate);
        this.recommendedAlgorithms = recommendedAlgorithms;
    }

    public SignatureAlgorithmsCertificateGuidelineCheck(String name, RequirementLevel requirementLevel,
        GuidelineCheckCondition condition, boolean onlyOneCertificate, List<SignatureAlgorithm> recommendedAlgorithms) {
        super(name, requirementLevel, condition, onlyOneCertificate);
        this.recommendedAlgorithms = recommendedAlgorithms;
    }

    @Override
    public GuidelineCheckResult evaluateChain(CertificateChain chain) {
        CertificateReport report = chain.getCertificateReportList().get(0);
        if (report.getSignatureAndHashAlgorithm() == null) {
            return new SignatureAlgorithmsGuidelineCheckResult(TestResults.UNCERTAIN, null);
        }
        Set<SignatureAlgorithm> nonRecommended = new HashSet<>();
        if (!this.recommendedAlgorithms.contains(report.getSignatureAndHashAlgorithm().getSignatureAlgorithm())) {
            nonRecommended.add(report.getSignatureAndHashAlgorithm().getSignatureAlgorithm());
        }
        return new SignatureAlgorithmsGuidelineCheckResult(TestResults.of(nonRecommended.isEmpty()), nonRecommended);
    }

    @Override
    public String getId() {
        return "SignatureAlgorithmsCert_" + getRequirementLevel() + "_" + recommendedAlgorithms;
    }

    public List<SignatureAlgorithm> getRecommendedAlgorithms() {
        return recommendedAlgorithms;
    }
}
