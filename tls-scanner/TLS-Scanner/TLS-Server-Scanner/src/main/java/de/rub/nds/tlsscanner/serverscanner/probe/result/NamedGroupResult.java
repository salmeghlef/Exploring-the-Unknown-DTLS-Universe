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
import de.rub.nds.tlsattacker.core.constants.NamedGroup;
import de.rub.nds.tlsscanner.core.constants.TlsAnalyzedProperty;
import de.rub.nds.tlsscanner.core.constants.TlsProbeType;
import de.rub.nds.tlsscanner.serverscanner.probe.namedgroup.NamedGroupWitness;
import de.rub.nds.tlsscanner.serverscanner.report.ServerReport;
import java.util.LinkedList;
import java.util.Map;

public class NamedGroupResult extends ProbeResult<ServerReport> {

    private final Map<NamedGroup, NamedGroupWitness> namedGroupsMap;
    private final Map<NamedGroup, NamedGroupWitness> namedGroupsMapTls13;

    private final TestResult supportsExplicitPrime;
    private final TestResult supportsExplicitChar2;
    private final TestResult groupsDependOnCipherSuite;
    private final TestResult ignoresEcdsaGroupDisparity;

    public NamedGroupResult(Map<NamedGroup, NamedGroupWitness> namedGroupsMap,
        Map<NamedGroup, NamedGroupWitness> namedGroupsMapTls13, TestResult supportsExplicitPrime,
        TestResult supportsExplicitChar2, TestResult groupsDependOnCipherSuite, TestResult ignoresEcdsaGroupDisparity) {
        super(TlsProbeType.NAMED_GROUPS);
        this.namedGroupsMap = namedGroupsMap;
        this.namedGroupsMapTls13 = namedGroupsMapTls13;
        this.supportsExplicitPrime = supportsExplicitPrime;
        this.supportsExplicitChar2 = supportsExplicitChar2;
        this.groupsDependOnCipherSuite = groupsDependOnCipherSuite;
        this.ignoresEcdsaGroupDisparity = ignoresEcdsaGroupDisparity;
    }

    @Override
    public void mergeData(ServerReport report) {
        LinkedList<NamedGroup> allGroups = new LinkedList<>();
        if (namedGroupsMap != null) {
            allGroups.addAll(namedGroupsMap.keySet());
        }

        LinkedList<NamedGroup> tls13Groups = new LinkedList<>();
        if (namedGroupsMapTls13 != null) {
            tls13Groups.addAll(namedGroupsMapTls13.keySet());
        }

        report.setSupportedNamedGroups(allGroups);
        report.setSupportedTls13Groups(tls13Groups);
        report.setSupportedNamedGroupsWitnesses(namedGroupsMap);
        report.setSupportedNamedGroupsWitnessesTls13(namedGroupsMapTls13);
        report.putResult(TlsAnalyzedProperty.SUPPORTS_EXPLICIT_PRIME_CURVE, supportsExplicitPrime);
        report.putResult(TlsAnalyzedProperty.SUPPORTS_EXPLICIT_CHAR2_CURVE, supportsExplicitChar2);
        report.putResult(TlsAnalyzedProperty.GROUPS_DEPEND_ON_CIPHER, groupsDependOnCipherSuite);
        report.putResult(TlsAnalyzedProperty.IGNORES_ECDSA_GROUP_DISPARITY, ignoresEcdsaGroupDisparity);
    }

}
