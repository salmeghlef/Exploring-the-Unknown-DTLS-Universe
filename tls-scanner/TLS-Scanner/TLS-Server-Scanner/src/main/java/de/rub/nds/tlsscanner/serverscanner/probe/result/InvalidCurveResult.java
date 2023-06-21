/**
 * TLS-Server-Scanner - A TLS configuration and analysis tool based on TLS-Attacker
 *
 * Copyright 2017-2023 Ruhr University Bochum, Paderborn University, Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */

package de.rub.nds.tlsscanner.serverscanner.probe.result;

import de.rub.nds.scanner.core.constants.TestResult;
import de.rub.nds.scanner.core.probe.result.ProbeResult;
import de.rub.nds.tlsscanner.core.constants.TlsAnalyzedProperty;
import de.rub.nds.tlsscanner.core.constants.TlsProbeType;
import de.rub.nds.tlsscanner.serverscanner.probe.invalidcurve.InvalidCurveResponse;
import de.rub.nds.tlsscanner.serverscanner.report.ServerReport;
import java.util.List;

public class InvalidCurveResult extends ProbeResult<ServerReport> {

    private final TestResult vulnerableClassic;
    private final TestResult vulnerableEphemeral;
    private final TestResult vulnerableTwist;
    private final List<InvalidCurveResponse> responses;

    public InvalidCurveResult(TestResult vulnerableClassic, TestResult vulnerableEphemeral, TestResult vulnerableTwist,
        List<InvalidCurveResponse> responses) {
        super(TlsProbeType.INVALID_CURVE);
        this.vulnerableClassic = vulnerableClassic;
        this.vulnerableEphemeral = vulnerableEphemeral;
        this.vulnerableTwist = vulnerableTwist;
        this.responses = responses;
    }

    @Override
    public void mergeData(ServerReport report) {
        report.putResult(TlsAnalyzedProperty.VULNERABLE_TO_INVALID_CURVE, vulnerableClassic);
        report.putResult(TlsAnalyzedProperty.VULNERABLE_TO_INVALID_CURVE_EPHEMERAL, vulnerableEphemeral);
        report.putResult(TlsAnalyzedProperty.VULNERABLE_TO_INVALID_CURVE_TWIST, vulnerableTwist);
        report.setInvalidCurveResultList(responses);
    }

}
