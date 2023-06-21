/**
 * TLS-Server-Scanner - A TLS configuration and analysis tool based on TLS-Attacker
 *
 * Copyright 2017-2023 Ruhr University Bochum, Paderborn University, Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */

package de.rub.nds.tlsscanner.serverscanner.selector;

import de.rub.nds.tlsattacker.core.config.Config;
import de.rub.nds.tlsattacker.core.constants.CipherSuite;
import de.rub.nds.tlsattacker.core.constants.NamedGroup;
import de.rub.nds.tlsattacker.core.constants.SignatureAndHashAlgorithm;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ConfigFilter {
    public static void applyFilterProfile(Config baseConfig, ConfigFilterType[] configFilterTypes) {
        for (ConfigFilterType filterType : configFilterTypes) {
            if (filterType.isCipherSuiteFilter()) {
                filterCipherSuites(baseConfig, filterType);
            } else if (filterType.isNamedGroupFilter()) {
                filterNamedGroups(baseConfig, filterType);
            } else if (filterType.isSignatureAlgorithmFilter()) {
                filterSignatureAlgorithms(baseConfig, filterType);
            } else {
                throw new IllegalArgumentException("No behavior defined for filter " + filterType);
            }
        }
    }

    private static void filterCipherSuites(Config baseConfig, ConfigFilterType filterType) {
        List<CipherSuite> reducedCipherSuites = baseConfig.getDefaultClientSupportedCipherSuites();
        switch (filterType) {
            case CIPHERSUITE_ANON:
                String anonEnumSubstring = filterType.name().replace("CIPHERSUITE_", "").toLowerCase();
                reducedCipherSuites =
                    reducedCipherSuites.stream().filter(cipherSuite -> !cipherSuite.name().contains(anonEnumSubstring))
                        .collect(Collectors.toList());
                break;
            case CIPHERSUITE_RABBIT:
            case CIPHERSUITE_CECPQ1:
            case CIPHERSUITE_IDEA:
            case CIPHERSUITE_SEED:
            case CIPHERSUITE_RC4:
            case CIPHERSUITE_SSL:
            case CIPHERSUITE_NULL:
            case CIPHERSUITE_ECCPWD:
            case CIPHERSUITE_EXPORT:
            case CIPHERSUITE_GREASE:
            case CIPHERSUITE_GOST:
            case CIPHERSUITE_KRB5:
            case CIPHERSUITE_PSK:
            case CIPHERSUITE_ARIA:
            case CIPHERSUITE_SRP:
            case CIPHERSUITE_CAMELLIA:
            case CIPHERSUITE_UNOFFICIAL:
                String filteredEnumSubstring = filterType.name().replace("CIPHERSUITE_", "");
                reducedCipherSuites = reducedCipherSuites.stream()
                    .filter(cipherSuite -> !cipherSuite.name().contains(filteredEnumSubstring))
                    .collect(Collectors.toList());
                break;
            case CIPHERSUITE_UNNEGOTIABLE:
                reducedCipherSuites = reducedCipherSuites.stream()
                    .filter(cipherSuite -> cipherSuite.isRealCipherSuite()).collect(Collectors.toList());
                break;
            case CIPHERSUITE_TLS13:
                reducedCipherSuites = reducedCipherSuites.stream().filter(Predicate.not(CipherSuite::isTLS13))
                    .collect(Collectors.toList());
                break;
            default:
                throw new IllegalArgumentException("No behavior defined for filter " + filterType);
        }
        baseConfig.setDefaultClientSupportedCipherSuites(reducedCipherSuites);
    }

    private static void filterNamedGroups(Config baseConfig, ConfigFilterType filterType) {
        List<NamedGroup> reducedNamedGroups = baseConfig.getDefaultClientNamedGroups();
        switch (filterType) {
            case NAMEDGROUP_FFDHE:
            case NAMEDGROUP_GREASE:
            case NAMEDGROUP_SECT:
            case NAMEDGROUP_BRAINPOOL:
                String filteredEnumSubstring = filterType.name().replace("NAMEDGROUP_", "");
                reducedNamedGroups = reducedNamedGroups.stream()
                    .filter(group -> !group.name().contains(filteredEnumSubstring)).collect(Collectors.toList());
                break;
            case NAMEDGROUP_DEPRECATED:
                reducedNamedGroups =
                    reducedNamedGroups.stream().filter(NamedGroup::isTls13).collect(Collectors.toList());
                break;
            default:
                throw new IllegalArgumentException("No behavior defined for filter " + filterType);
        }
        baseConfig.setDefaultClientNamedGroups(reducedNamedGroups);
    }

    private static void filterSignatureAlgorithms(Config baseConfig, ConfigFilterType filterType) {
        List<SignatureAndHashAlgorithm> reducedSignatureAlgorithms =
            baseConfig.getDefaultClientSupportedSignatureAndHashAlgorithms();
        switch (filterType) {
            case SIGNATUREALGORITHM_ANON:
            case SIGNATUREALGORITHM_DSA:
            case SIGNATUREALGORITHM_SHA1:
            case SIGNATUREALGORITHM_GREASE:
            case SIGNATUREALGORITHM_GOST:
            case SIGNATUREALGORITHM_MD5:
            case SIGNATUREALGORITHM_NONE:
                String filteredEnumSubstring = filterType.name().replace("SIGNATUREALGORITHM_", "");
                reducedSignatureAlgorithms = reducedSignatureAlgorithms.stream()
                    .filter(algo -> !algo.name().contains(filteredEnumSubstring)).collect(Collectors.toList());
                break;
            case SIGNATUREALGORITHM_TLS13:
                reducedSignatureAlgorithms = reducedSignatureAlgorithms.stream()
                    .filter(SignatureAndHashAlgorithm.getTls13SignatureAndHashAlgorithms()::contains)
                    .collect(Collectors.toList());
                break;
            default:
                throw new IllegalArgumentException("No behavior defined for filter " + filterType);
        }
        baseConfig.setDefaultClientSupportedSignatureAndHashAlgorithms(reducedSignatureAlgorithms);
    }
}
