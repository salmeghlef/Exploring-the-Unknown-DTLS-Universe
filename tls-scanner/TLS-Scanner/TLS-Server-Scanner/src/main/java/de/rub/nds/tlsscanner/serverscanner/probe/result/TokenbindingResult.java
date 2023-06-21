/**
 * TLS-Server-Scanner - A TLS configuration and analysis tool based on TLS-Attacker
 *
 * Copyright 2017-2023 Ruhr University Bochum, Paderborn University, Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */

package de.rub.nds.tlsscanner.serverscanner.probe.result;

import de.rub.nds.scanner.core.constants.TestResults;
import de.rub.nds.scanner.core.probe.result.ProbeResult;
import de.rub.nds.tlsattacker.core.constants.ExtensionType;
import de.rub.nds.tlsattacker.core.constants.TokenBindingKeyParameters;
import de.rub.nds.tlsattacker.core.constants.TokenBindingVersion;
import de.rub.nds.tlsscanner.core.constants.TlsAnalyzedProperty;
import de.rub.nds.tlsscanner.core.constants.TlsProbeType;
import de.rub.nds.tlsscanner.serverscanner.report.ServerReport;
import java.util.LinkedList;
import java.util.List;

public class TokenbindingResult extends ProbeResult<ServerReport> {

    private List<TokenBindingVersion> supportedTokenBindingVersion = null;
    private List<TokenBindingKeyParameters> supportedTokenBindingKeyParameters = null;

    public TokenbindingResult(List<TokenBindingVersion> supportedTokenBindingVersion,
        List<TokenBindingKeyParameters> supportedTokenBindingKeyParameters) {
        super(TlsProbeType.TOKENBINDING);
        this.supportedTokenBindingVersion = supportedTokenBindingVersion;
        this.supportedTokenBindingKeyParameters = supportedTokenBindingKeyParameters;
    }

    @Override
    public void mergeData(ServerReport report) {
        report.setSupportedTokenBindingKeyParameters(supportedTokenBindingKeyParameters);
        report.setSupportedTokenBindingVersion(supportedTokenBindingVersion);
        if (supportedTokenBindingVersion != null && !supportedTokenBindingVersion.isEmpty()) {
            report.putResult(TlsAnalyzedProperty.SUPPORTS_TOKENBINDING, TestResults.TRUE);
            if (report.getSupportedExtensions() == null) {
                report.setSupportedExtensions(new LinkedList<ExtensionType>());
            }
            report.getSupportedExtensions().add(ExtensionType.TOKEN_BINDING);
        } else {
            report.putResult(TlsAnalyzedProperty.SUPPORTS_TOKENBINDING, TestResults.FALSE);
        }
    }
}
