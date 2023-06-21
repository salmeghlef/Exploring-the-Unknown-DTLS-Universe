/**
 * TLS-Server-Scanner - A TLS configuration and analysis tool based on TLS-Attacker
 *
 * Copyright 2017-2023 Ruhr University Bochum, Paderborn University, Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */

package de.rub.nds.tlsscanner.serverscanner.probe.result;

import de.rub.nds.scanner.core.probe.result.ProbeResult;
import de.rub.nds.tlsattacker.core.constants.SignatureAndHashAlgorithm;
import de.rub.nds.tlsscanner.core.constants.TlsProbeType;
import de.rub.nds.tlsscanner.serverscanner.report.ServerReport;
import java.util.List;

public class SignatureAndHashAlgorithmResult extends ProbeResult<ServerReport> {

    private final List<SignatureAndHashAlgorithm> signatureAndHashAlgorithmListSke;
    private final List<SignatureAndHashAlgorithm> signatureAndHashAlgorithmListTls13;

    public SignatureAndHashAlgorithmResult(List<SignatureAndHashAlgorithm> signatureAndHashAlgorithmListSke,
        List<SignatureAndHashAlgorithm> signatureAndHashAlgorithmListTls13) {
        super(TlsProbeType.SIGNATURE_AND_HASH);
        this.signatureAndHashAlgorithmListSke = signatureAndHashAlgorithmListSke;
        this.signatureAndHashAlgorithmListTls13 = signatureAndHashAlgorithmListTls13;
    }

    @Override
    public void mergeData(ServerReport report) {
        report.setSupportedSignatureAndHashAlgorithmsSke(signatureAndHashAlgorithmListSke);
        report.setSupportedSignatureAndHashAlgorithmsTls13(signatureAndHashAlgorithmListTls13);
    }

}
