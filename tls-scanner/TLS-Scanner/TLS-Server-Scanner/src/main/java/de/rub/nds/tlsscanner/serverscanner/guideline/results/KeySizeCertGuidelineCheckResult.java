/**
 * TLS-Server-Scanner - A TLS configuration and analysis tool based on TLS-Attacker
 *
 * Copyright 2017-2023 Ruhr University Bochum, Paderborn University, Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */

package de.rub.nds.tlsscanner.serverscanner.guideline.results;

import de.rub.nds.scanner.core.constants.TestResults;
import de.rub.nds.tlsscanner.core.guideline.GuidelineCheckResult;
import java.util.ArrayList;
import java.util.List;

public class KeySizeCertGuidelineCheckResult extends GuidelineCheckResult {

    private final List<KeySizeData> keySizes = new ArrayList<>();

    public KeySizeCertGuidelineCheckResult() {
        super(TestResults.UNCERTAIN);
    }

    public void addKeySize(KeySizeData data) {
        this.keySizes.add(data);
    }

    public List<KeySizeData> getKeySizes() {
        return keySizes;
    }

    @Override
    public String display() {
        StringBuilder stringBuilder = new StringBuilder();
        for (KeySizeData data : this.keySizes) {
            stringBuilder.append(data.getAlgorithm()).append(" Key Size: ").append(data.getActualLength());
            if (data.getActualLength() < data.getMinimumLength()) {
                stringBuilder.append('<').append(data.getMinimumLength()).append('\n');
            } else {
                stringBuilder.append('≥').append(data.getMinimumLength()).append('\n');
            }
        }
        return stringBuilder.toString();
    }
}
