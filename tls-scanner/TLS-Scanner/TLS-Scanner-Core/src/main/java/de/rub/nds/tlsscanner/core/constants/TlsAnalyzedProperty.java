/**
 * TLS-Scanner-Core - A TLS configuration and analysis tool based on TLS-Attacker
 *
 * Copyright 2017-2023 Ruhr University Bochum, Paderborn University, Hackmanit GmbH
 *
 * Licensed under Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.txt
 */

package de.rub.nds.tlsscanner.core.constants;

import de.rub.nds.scanner.core.constants.AnalyzedProperty;
import de.rub.nds.scanner.core.constants.AnalyzedPropertyCategory;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public enum TlsAnalyzedProperty implements AnalyzedProperty {

    SUPPORTS_ESNI(TlsAnalyzedPropertyCategory.ESNI),
    SUPPORTS_SSL_2(TlsAnalyzedPropertyCategory.VERSIONS),
    SUPPORTS_SSL_3(TlsAnalyzedPropertyCategory.VERSIONS),
    SUPPORTS_TLS_1_0(TlsAnalyzedPropertyCategory.VERSIONS),
    SUPPORTS_TLS_1_1(TlsAnalyzedPropertyCategory.VERSIONS),
    SUPPORTS_TLS_1_2(TlsAnalyzedPropertyCategory.VERSIONS),
    SUPPORTS_TLS_1_3(TlsAnalyzedPropertyCategory.VERSIONS),
    SUPPORTS_TLS_1_3_DRAFT(TlsAnalyzedPropertyCategory.VERSIONS),
    SUPPORTS_TLS_1_3_DRAFT_14(TlsAnalyzedPropertyCategory.VERSIONS),
    SUPPORTS_TLS_1_3_DRAFT_15(TlsAnalyzedPropertyCategory.VERSIONS),
    SUPPORTS_TLS_1_3_DRAFT_16(TlsAnalyzedPropertyCategory.VERSIONS),
    SUPPORTS_TLS_1_3_DRAFT_17(TlsAnalyzedPropertyCategory.VERSIONS),
    SUPPORTS_TLS_1_3_DRAFT_18(TlsAnalyzedPropertyCategory.VERSIONS),
    SUPPORTS_TLS_1_3_DRAFT_19(TlsAnalyzedPropertyCategory.VERSIONS),
    SUPPORTS_TLS_1_3_DRAFT_20(TlsAnalyzedPropertyCategory.VERSIONS),
    SUPPORTS_TLS_1_3_DRAFT_21(TlsAnalyzedPropertyCategory.VERSIONS),
    SUPPORTS_TLS_1_3_DRAFT_22(TlsAnalyzedPropertyCategory.VERSIONS),
    SUPPORTS_TLS_1_3_DRAFT_23(TlsAnalyzedPropertyCategory.VERSIONS),
    SUPPORTS_TLS_1_3_DRAFT_24(TlsAnalyzedPropertyCategory.VERSIONS),
    SUPPORTS_TLS_1_3_DRAFT_25(TlsAnalyzedPropertyCategory.VERSIONS),
    SUPPORTS_TLS_1_3_DRAFT_26(TlsAnalyzedPropertyCategory.VERSIONS),
    SUPPORTS_TLS_1_3_DRAFT_27(TlsAnalyzedPropertyCategory.VERSIONS),
    SUPPORTS_TLS_1_3_DRAFT_28(TlsAnalyzedPropertyCategory.VERSIONS),
    SUPPORTS_DTLS_1_0_DRAFT(TlsAnalyzedPropertyCategory.VERSIONS),
    SUPPORTS_DTLS_1_0(TlsAnalyzedPropertyCategory.VERSIONS),
    SUPPORTS_DTLS_1_2(TlsAnalyzedPropertyCategory.VERSIONS),
    SUPPORTS_DTLS_1_3(TlsAnalyzedPropertyCategory.VERSIONS),
    SUPPORTS_PFS(TlsAnalyzedPropertyCategory.CIPHER_SUITES),
    SUPPORTS_ONLY_PSK(TlsAnalyzedPropertyCategory.CIPHER_SUITES),
    SUPPORTS_NULL_CIPHERS(TlsAnalyzedPropertyCategory.CIPHER_SUITES),
    SUPPORTS_FORTEZZA(TlsAnalyzedPropertyCategory.CIPHER_SUITES),
    SUPPORTS_EXPORT(TlsAnalyzedPropertyCategory.CIPHER_SUITES),
    SUPPORTS_ANON(TlsAnalyzedPropertyCategory.CIPHER_SUITES),
    SUPPORTS_DES(TlsAnalyzedPropertyCategory.CIPHER_SUITES),
    SUPPORTS_3DES(TlsAnalyzedPropertyCategory.CIPHER_SUITES),
    SUPPORTS_SEED(TlsAnalyzedPropertyCategory.CIPHER_SUITES),
    SUPPORTS_IDEA(TlsAnalyzedPropertyCategory.CIPHER_SUITES),
    SUPPORTS_RC2(TlsAnalyzedPropertyCategory.CIPHER_SUITES),
    SUPPORTS_RC4(TlsAnalyzedPropertyCategory.CIPHER_SUITES),
    SUPPORTS_LEGACY_PRF(TlsAnalyzedPropertyCategory.CIPHER_SUITES),
    SUPPORTS_SHA256_PRF(TlsAnalyzedPropertyCategory.CIPHER_SUITES),
    SUPPORTS_SHA384_PRF(TlsAnalyzedPropertyCategory.CIPHER_SUITES),
    SUPPORTS_CBC(TlsAnalyzedPropertyCategory.CIPHER_SUITES),
    SUPPORTS_AEAD(TlsAnalyzedPropertyCategory.CIPHER_SUITES),
    SUPPORTS_POST_QUANTUM(TlsAnalyzedPropertyCategory.CIPHER_SUITES),
    SUPPORTS_ONLY_PFS(TlsAnalyzedPropertyCategory.CIPHER_SUITES),
    SUPPORTS_AES(TlsAnalyzedPropertyCategory.CIPHER_SUITES),
    SUPPORTS_CAMELLIA(TlsAnalyzedPropertyCategory.CIPHER_SUITES),
    SUPPORTS_ARIA(TlsAnalyzedPropertyCategory.CIPHER_SUITES),
    SUPPORTS_CHACHA(TlsAnalyzedPropertyCategory.CIPHER_SUITES),
    SUPPORTS_RSA(TlsAnalyzedPropertyCategory.CIPHER_SUITES),
    SUPPORTS_STATIC_DH(TlsAnalyzedPropertyCategory.CIPHER_SUITES),
    SUPPORTS_DHE(TlsAnalyzedPropertyCategory.CIPHER_SUITES),
    SUPPORTS_ECDHE(TlsAnalyzedPropertyCategory.CIPHER_SUITES),
    SUPPORTS_STATIC_ECDH(TlsAnalyzedPropertyCategory.CIPHER_SUITES),
    SUPPORTS_ECDSA(TlsAnalyzedPropertyCategory.CIPHER_SUITES),
    SUPPORTS_RSA_CERT(TlsAnalyzedPropertyCategory.CIPHER_SUITES),
    SUPPORTS_DSS(TlsAnalyzedPropertyCategory.CIPHER_SUITES),
    SUPPORTS_GOST(TlsAnalyzedPropertyCategory.CIPHER_SUITES),
    SUPPORTS_SRP(TlsAnalyzedPropertyCategory.CIPHER_SUITES),
    SUPPORTS_KERBEROS(TlsAnalyzedPropertyCategory.CIPHER_SUITES),
    SUPPORTS_PSK_PLAIN(TlsAnalyzedPropertyCategory.CIPHER_SUITES),
    SUPPORTS_PSK_RSA(TlsAnalyzedPropertyCategory.CIPHER_SUITES),
    SUPPORTS_PSK_DHE(TlsAnalyzedPropertyCategory.CIPHER_SUITES),
    SUPPORTS_PSK_ECDHE(TlsAnalyzedPropertyCategory.CIPHER_SUITES),
    SUPPORTS_NEWHOPE(TlsAnalyzedPropertyCategory.CIPHER_SUITES),
    SUPPORTS_ECMQV(TlsAnalyzedPropertyCategory.CIPHER_SUITES),
    SUPPORTS_STREAM_CIPHERS(TlsAnalyzedPropertyCategory.CIPHER_SUITES),
    SUPPORTS_BLOCK_CIPHERS(TlsAnalyzedPropertyCategory.CIPHER_SUITES),
    SUPPORTS_EXTENDED_MASTER_SECRET(TlsAnalyzedPropertyCategory.EXTENSIONS),
    SUPPORTS_ENCRYPT_THEN_MAC(TlsAnalyzedPropertyCategory.EXTENSIONS),
    SUPPORTS_TOKENBINDING(TlsAnalyzedPropertyCategory.EXTENSIONS),
    SUPPORTS_CERTIFICATE_STATUS_REQUEST(TlsAnalyzedPropertyCategory.EXTENSIONS),
    SUPPORTS_CERTIFICATE_STATUS_REQUEST_V2(TlsAnalyzedPropertyCategory.EXTENSIONS),
    SUPPORTS_CERTIFICATE_STATUS_REQUEST_TLS13(TlsAnalyzedPropertyCategory.EXTENSIONS),
    SUPPORTS_SCTS_PRECERTIFICATE(TlsAnalyzedPropertyCategory.CERTIFICATE_TRANSPARENCY),
    SUPPORTS_SCTS_HANDSHAKE(TlsAnalyzedPropertyCategory.CERTIFICATE_TRANSPARENCY),
    SUPPORTS_SCTS_OCSP(TlsAnalyzedPropertyCategory.CERTIFICATE_TRANSPARENCY),
    SUPPORTS_CHROME_CT_POLICY(TlsAnalyzedPropertyCategory.CERTIFICATE_TRANSPARENCY),
    SUPPORTS_MONTGOMERY_CURVES(TlsAnalyzedPropertyCategory.EC),
    SUPPORTS_SESSION_TICKETS(TlsAnalyzedPropertyCategory.SESSION_RESUMPTION),
    SUPPORTS_TLS13_SESSION_TICKETS(TlsAnalyzedPropertyCategory.SESSION_RESUMPTION),
    SUPPORTS_TLS13_PSK_DHE(TlsAnalyzedPropertyCategory.SESSION_RESUMPTION),
    SUPPORTS_TLS13_PSK(TlsAnalyzedPropertyCategory.SESSION_RESUMPTION),
    SUPPORTS_TLS13_PSK_EXCHANGE_MODES(TlsAnalyzedPropertyCategory.SESSION_RESUMPTION),
    SUPPORTS_TLS13_0_RTT(TlsAnalyzedPropertyCategory.SESSION_RESUMPTION),
    SUPPORTS_SESSION_ID_RESUMPTION(TlsAnalyzedPropertyCategory.SESSION_RESUMPTION),
    SUPPORTS_SESSION_TICKET_RESUMPTION(TlsAnalyzedPropertyCategory.SESSION_RESUMPTION),
    SUPPORTS_SESSION_TICKET_ROTATION_HINT(TlsAnalyzedPropertyCategory.SESSION_RESUMPTION),
    SUPPORTS_DTLS_COOKIE_EXCHANGE_IN_SESSION_ID_RESUMPTION(TlsAnalyzedPropertyCategory.SESSION_RESUMPTION),
    SUPPORTS_DTLS_COOKIE_EXCHANGE_IN_SESSION_TICKET_RESUMPTION(TlsAnalyzedPropertyCategory.SESSION_RESUMPTION),
    SUPPORTS_SECURE_RENEGOTIATION_EXTENSION(TlsAnalyzedPropertyCategory.RENEGOTIATION),
    SUPPORTS_CLIENT_SIDE_SECURE_RENEGOTIATION_CIPHERSUITE(TlsAnalyzedPropertyCategory.RENEGOTIATION),
    SUPPORTS_CLIENT_SIDE_SECURE_RENEGOTIATION_EXTENSION(TlsAnalyzedPropertyCategory.RENEGOTIATION),
    SUPPORTS_CLIENT_SIDE_INSECURE_RENEGOTIATION(TlsAnalyzedPropertyCategory.RENEGOTIATION),
    SUPPORTS_DTLS_COOKIE_EXCHANGE_IN_RENEGOTIATION(TlsAnalyzedPropertyCategory.RENEGOTIATION),
    SUPPORTS_TLS_FALLBACK_SCSV(TlsAnalyzedPropertyCategory.RENEGOTIATION),
    SUPPORTS_TLS_COMPRESSION(TlsAnalyzedPropertyCategory.COMPRESSION),
    SUPPORTS_COMMON_DH_PRIMES(TlsAnalyzedPropertyCategory.FFDHE),
    SUPPORTS_ONLY_PRIME_MODULI(TlsAnalyzedPropertyCategory.FFDHE),
    SUPPORTS_ONLY_SAFEPRIME_MODULI(TlsAnalyzedPropertyCategory.FFDHE),
    SUPPORTS_INSECURE_RENEGOTIATION(TlsAnalyzedPropertyCategory.RENEGOTIATION),
    SUPPORTS_RENEGOTIATION(TlsAnalyzedPropertyCategory.RENEGOTIATION),
    SUPPORTS_HTTPS(TlsAnalyzedPropertyCategory.HTTPS_HEADERS),
    SUPPORTS_HSTS(TlsAnalyzedPropertyCategory.HTTPS_HEADERS),
    SUPPORTS_HSTS_PRELOADING(TlsAnalyzedPropertyCategory.HTTPS_HEADERS),
    SUPPORTS_HPKP(TlsAnalyzedPropertyCategory.HTTPS_HEADERS),
    SUPPORTS_HPKP_REPORTING(TlsAnalyzedPropertyCategory.HTTPS_HEADERS),
    SUPPORTS_HTTP_COMPRESSION(TlsAnalyzedPropertyCategory.HTTPS_HEADERS),
    SUPPORTS_UNCOMPRESSED_POINT(TlsAnalyzedPropertyCategory.EC),
    SUPPORTS_ANSIX962_COMPRESSED_PRIME(TlsAnalyzedPropertyCategory.EC),
    SUPPORTS_ANSIX962_COMPRESSED_CHAR2(TlsAnalyzedPropertyCategory.EC),
    HANDSHAKES_WITH_UNDEFINED_POINT_FORMAT(TlsAnalyzedPropertyCategory.EC),
    SUPPORTS_TLS13_SECP_COMPRESSION(TlsAnalyzedPropertyCategory.EC),
    SUPPORTS_EXPLICIT_PRIME_CURVE(TlsAnalyzedPropertyCategory.EC),
    SUPPORTS_EXPLICIT_CHAR2_CURVE(TlsAnalyzedPropertyCategory.EC),
    GROUPS_DEPEND_ON_CIPHER(TlsAnalyzedPropertyCategory.EC),
    SUPPORTS_OCSP(TlsAnalyzedPropertyCategory.OCSP),
    PREFERS_PFS(TlsAnalyzedPropertyCategory.BEST_PRACTICES),
    ENFORCES_PFS(TlsAnalyzedPropertyCategory.BEST_PRACTICES),
    ENFORCES_CS_ORDERING(TlsAnalyzedPropertyCategory.BEST_PRACTICES),
    ENFORCES_NAMED_GROUP_ORDERING(TlsAnalyzedPropertyCategory.BEST_PRACTICES),
    ENFORCES_SIGNATURE_HASH_ALGORITHM_ORDERING(TlsAnalyzedPropertyCategory.BEST_PRACTICES),
    STRICT_SNI(TlsAnalyzedPropertyCategory.SNI),
    STRICT_ALPN(TlsAnalyzedPropertyCategory.EXTENSIONS),
    /**
     * does it handle unknown versions correctly?
     */
    HAS_VERSION_INTOLERANCE(TlsAnalyzedPropertyCategory.QUIRKS),
    /**
     * does it handle unknown cipher suites correctly?
     */
    HAS_CIPHER_SUITE_INTOLERANCE(TlsAnalyzedPropertyCategory.QUIRKS),
    /**
     * does it handle unknown extensions correctly?
     */
    HAS_EXTENSION_INTOLERANCE(TlsAnalyzedPropertyCategory.QUIRKS),
    /**
     * does it handle long cipher suite length values correctly?
     */
    HAS_CIPHER_SUITE_LENGTH_INTOLERANCE(TlsAnalyzedPropertyCategory.QUIRKS),
    /**
     * does it handle unknown compression algorithms correctly
     */
    HAS_COMPRESSION_INTOLERANCE(TlsAnalyzedPropertyCategory.QUIRKS),
    /**
     * does it handle unknown alpn strings correctly?
     */
    HAS_ALPN_INTOLERANCE(TlsAnalyzedPropertyCategory.QUIRKS),
    /**
     * 256 - 511 <-- ch should be bigger than this
     */
    HAS_CLIENT_HELLO_LENGTH_INTOLERANCE(TlsAnalyzedPropertyCategory.QUIRKS),
    /**
     * does it break on empty last extension
     */
    HAS_EMPTY_LAST_EXTENSION_INTOLERANCE(TlsAnalyzedPropertyCategory.QUIRKS),
    /**
     * does it handle signature and hash algorithms correctly
     */
    HAS_SIG_HASH_ALGORITHM_INTOLERANCE(TlsAnalyzedPropertyCategory.QUIRKS),
    /**
     * server does not like really big client hello messages
     */
    HAS_BIG_CLIENT_HELLO_INTOLERANCE(TlsAnalyzedPropertyCategory.QUIRKS),
    /**
     * does it handle unknown groups correctly
     */
    HAS_NAMED_GROUP_INTOLERANCE(TlsAnalyzedPropertyCategory.QUIRKS),
    /**
     * is only the second byte of the cipher suite evaluated
     */
    HAS_SECOND_CIPHER_SUITE_BYTE_BUG(TlsAnalyzedPropertyCategory.QUIRKS),
    /**
     * does it ignore the offered cipher suites
     */
    REFLECTS_OFFERED_CIPHER_SUITES(TlsAnalyzedPropertyCategory.QUIRKS),
    /**
     * does it ignore the offered cipher suites
     */
    IGNORES_OFFERED_CIPHER_SUITES(TlsAnalyzedPropertyCategory.QUIRKS),
    /**
     * does it ignore the offered named groups
     */
    IGNORES_OFFERED_NAMED_GROUPS(TlsAnalyzedPropertyCategory.QUIRKS),
    /**
     * does it ignore the sig hash algorithms
     */
    IGNORES_OFFERED_SIG_HASH_ALGOS(TlsAnalyzedPropertyCategory.QUIRKS),
    /**
     * does it accept that named groups for ecdsa are missing
     */
    IGNORES_ECDSA_GROUP_DISPARITY(TlsAnalyzedPropertyCategory.QUIRKS),
    /**
     * does it handle a http false start
     */
    SUPPORTS_HTTP_FALSE_START(TlsAnalyzedPropertyCategory.QUIRKS),
    SUPPORTS_RECORD_FRAGMENTATION(TlsAnalyzedPropertyCategory.QUIRKS),
    /**
     * does it have a grease value intolerance?
     */
    HAS_GREASE_CIPHER_SUITE_INTOLERANCE(TlsAnalyzedPropertyCategory.QUIRKS),
    HAS_GREASE_NAMED_GROUP_INTOLERANCE(TlsAnalyzedPropertyCategory.QUIRKS),
    HAS_GREASE_SIGNATURE_AND_HASH_ALGORITHM_INTOLERANCE(TlsAnalyzedPropertyCategory.QUIRKS),
    USES_UNIX_TIMESTAMPS_IN_RANDOM(TlsAnalyzedPropertyCategory.QUIRKS),
    SENDS_HELLO_RETRY_REQUEST(TlsAnalyzedPropertyCategory.BEST_PRACTICES),
    ISSUES_COOKIE_IN_HELLO_RETRY(TlsAnalyzedPropertyCategory.EXTENSIONS),
    VULNERABLE_TO_SESSION_TICKET_ZERO_KEY(TlsAnalyzedPropertyCategory.ATTACKS),
    VULNERABLE_TO_DIRECT_RACCOON(TlsAnalyzedPropertyCategory.ATTACKS),
    VULNERABLE_TO_BLEICHENBACHER(TlsAnalyzedPropertyCategory.ATTACKS),
    VULNERABLE_TO_PADDING_ORACLE(TlsAnalyzedPropertyCategory.ATTACKS),
    VULNERABLE_TO_INVALID_CURVE(TlsAnalyzedPropertyCategory.ATTACKS),
    VULNERABLE_TO_INVALID_CURVE_EPHEMERAL(TlsAnalyzedPropertyCategory.ATTACKS),
    VULNERABLE_TO_INVALID_CURVE_TWIST(TlsAnalyzedPropertyCategory.ATTACKS),
    VULNERABLE_TO_INVALID_CURVE_EPHEMERAL_EXPLOITABLE(TlsAnalyzedPropertyCategory.ATTACKS),
    VULNERABLE_TO_POODLE(TlsAnalyzedPropertyCategory.ATTACKS),
    VULNERABLE_TO_SWEET_32(TlsAnalyzedPropertyCategory.ATTACKS),
    VULNERABLE_TO_GENERAL_DROWN(TlsAnalyzedPropertyCategory.ATTACKS),
    VULNERABLE_TO_EXTRA_CLEAR_DROWN(TlsAnalyzedPropertyCategory.ATTACKS),
    VULNERABLE_TO_HEARTBLEED(TlsAnalyzedPropertyCategory.ATTACKS),
    VULNERABLE_TO_TICKETBLEED(TlsAnalyzedPropertyCategory.ATTACKS),
    VULNERABLE_TO_EARLY_CCS(TlsAnalyzedPropertyCategory.ATTACKS),
    VULNERABLE_TO_CRIME(TlsAnalyzedPropertyCategory.ATTACKS),
    VULNERABLE_TO_BREACH(TlsAnalyzedPropertyCategory.ATTACKS),
    VULNERABLE_TO_LOGJAM(TlsAnalyzedPropertyCategory.ATTACKS),
    VULNERABLE_TO_FREAK(TlsAnalyzedPropertyCategory.ATTACKS),
    VULNERABLE_TO_RENEGOTIATION_ATTACK_EXTENSION_V1(TlsAnalyzedPropertyCategory.ATTACKS),
    VULNERABLE_TO_RENEGOTIATION_ATTACK_EXTENSION_V2(TlsAnalyzedPropertyCategory.ATTACKS),
    VULNERABLE_TO_RENEGOTIATION_ATTACK_CIPHERSUITE_V1(TlsAnalyzedPropertyCategory.ATTACKS),
    VULNERABLE_TO_RENEGOTIATION_ATTACK_CIPHERSUITE_V2(TlsAnalyzedPropertyCategory.ATTACKS),
    VULNERABLE_TO_RACCOON_ATTACK(TlsAnalyzedPropertyCategory.ATTACKS),
    ALPACA_MITIGATED(TlsAnalyzedPropertyCategory.ATTACKS),
    MISSES_MAC_APPDATA_CHECKS(TlsAnalyzedPropertyCategory.COMPARISON_FAILURE),
    MISSES_MAC_FINISHED_CHECKS(TlsAnalyzedPropertyCategory.COMPARISON_FAILURE),
    MISSES_VERIFY_DATA_CHECKS(TlsAnalyzedPropertyCategory.COMPARISON_FAILURE),
    MISSES_GCM_CHECKS(TlsAnalyzedPropertyCategory.COMPARISON_FAILURE),
    HAS_CERTIFICATE_ISSUES(TlsAnalyzedPropertyCategory.CERTIFICATE),
    MUST_STAPLE(TlsAnalyzedPropertyCategory.OCSP),
    INCLUDES_CERTIFICATE_STATUS_MESSAGE(TlsAnalyzedPropertyCategory.OCSP),
    STAPLED_RESPONSE_EXPIRED(TlsAnalyzedPropertyCategory.OCSP),
    SUPPORTS_NONCE(TlsAnalyzedPropertyCategory.OCSP),
    SUPPORTS_STAPLED_NONCE(TlsAnalyzedPropertyCategory.OCSP),
    SUPPORTS_OCSP_STAPLING(TlsAnalyzedPropertyCategory.OCSP),
    NONCE_MISMATCH(TlsAnalyzedPropertyCategory.OCSP),
    STAPLING_UNRELIABLE(TlsAnalyzedPropertyCategory.OCSP),
    STAPLING_TLS13_MULTIPLE_CERTIFICATES(TlsAnalyzedPropertyCategory.OCSP),
    HAS_WEAK_RANDOMNESS(TlsAnalyzedPropertyCategory.FRESHNESS),
    REUSES_EC_PUBLICKEY(TlsAnalyzedPropertyCategory.FRESHNESS),
    REUSES_DH_PUBLICKEY(TlsAnalyzedPropertyCategory.FRESHNESS),
    REUSES_GCM_NONCES(TlsAnalyzedPropertyCategory.FRESHNESS),
    REQUIRES_SNI(TlsAnalyzedPropertyCategory.SNI),
    HAS_GNU_TLS_MAGIC_BYTES(TlsAnalyzedPropertyCategory.SESSION_TICKET),
    /**
     * CCA Properties
     */
    SUPPORTS_CCA(TlsAnalyzedPropertyCategory.CERTIFICATE),
    REQUIRES_CCA(TlsAnalyzedPropertyCategory.CERTIFICATE),
    VULNERABLE_TO_CCA_BYPASS(TlsAnalyzedPropertyCategory.ATTACKS),
    /**
     * DTLS
     */
    SUPPORTS_DTLS_FRAGMENTATION(TlsAnalyzedPropertyCategory.QUIRKS),
    DTLS_FRAGMENTATION_REQUIRES_EXTENSION(TlsAnalyzedPropertyCategory.QUIRKS),
    SUPPORTS_DTLS_FRAGMENTATION_WITH_INDIVIDUAL_PACKETS(TlsAnalyzedPropertyCategory.QUIRKS),
    DTLS_FRAGMENTATION_WITH_INDIVIDUAL_PACKETS_REQUIRES_EXTENSION(TlsAnalyzedPropertyCategory.QUIRKS),
    SUPPORTS_REORDERING(TlsAnalyzedPropertyCategory.QUIRKS),
    HAS_HVR_RETRANSMISSIONS(TlsAnalyzedPropertyCategory.HELLO_VERIFY_REQUEST),
    HAS_COOKIE_CHECKS(TlsAnalyzedPropertyCategory.HELLO_VERIFY_REQUEST),
    USES_IP_ADDRESS_FOR_COOKIE(TlsAnalyzedPropertyCategory.HELLO_VERIFY_REQUEST),
    USES_PORT_FOR_COOKIE(TlsAnalyzedPropertyCategory.HELLO_VERIFY_REQUEST),
    USES_VERSION_FOR_COOKIE(TlsAnalyzedPropertyCategory.HELLO_VERIFY_REQUEST),
    USES_RANDOM_FOR_COOKIE(TlsAnalyzedPropertyCategory.HELLO_VERIFY_REQUEST),
    USES_SESSION_ID_FOR_COOKIE(TlsAnalyzedPropertyCategory.HELLO_VERIFY_REQUEST),
    USES_CIPHERSUITES_FOR_COOKIE(TlsAnalyzedPropertyCategory.HELLO_VERIFY_REQUEST),
    USES_COMPRESSIONS_FOR_COOKIE(TlsAnalyzedPropertyCategory.HELLO_VERIFY_REQUEST),
    ACCEPTS_UNENCRYPTED_FINISHED(TlsAnalyzedPropertyCategory.QUIRKS),
    ACCEPTS_UNENCRYPTED_APP_DATA(TlsAnalyzedPropertyCategory.QUIRKS),
    HAS_EARLY_FINISHED_BUG(TlsAnalyzedPropertyCategory.QUIRKS),
    ACCEPTS_STARTED_WITH_INVALID_MESSAGE_SEQUENCE(TlsAnalyzedPropertyCategory.QUIRKS),
    ACCEPTS_SKIPPED_MESSAGE_SEQUENCES_ONCE(TlsAnalyzedPropertyCategory.QUIRKS),
    ACCEPTS_SKIPPED_MESSAGE_SEQUENCES_MULTIPLE(TlsAnalyzedPropertyCategory.QUIRKS),
    ACCEPTS_RANDOM_MESSAGE_SEQUENCES(TlsAnalyzedPropertyCategory.QUIRKS),
    MISSES_MESSAGE_SEQUENCE_CHECKS(TlsAnalyzedPropertyCategory.QUIRKS),
    SENDS_RETRANSMISSIONS(TlsAnalyzedPropertyCategory.QUIRKS),
    PROCESSES_RETRANSMISSIONS(TlsAnalyzedPropertyCategory.QUIRKS),
    CHANGES_PORT(TlsAnalyzedPropertyCategory.QUIRKS),
    CHANGES_PORT_TO_RANDOM_PORTS(TlsAnalyzedPropertyCategory.QUIRKS),
    // CLIENT SPECIFIC PROPERTIES
    TLS_1_3_DOWNGRADE_PROTECTION(TlsAnalyzedPropertyCategory.QUIRKS),
    FORCED_COMPRESSION(TlsAnalyzedPropertyCategory.QUIRKS);

    private final TlsAnalyzedPropertyCategory category;

    private TlsAnalyzedProperty(TlsAnalyzedPropertyCategory category) {
        this.category = category;
    }

    @Override
    public AnalyzedPropertyCategory getCategory() {
        return category;
    }

    @Override
    public String getName() {
        return name();
    }
}
