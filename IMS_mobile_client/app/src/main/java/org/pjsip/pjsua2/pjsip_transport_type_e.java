/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 4.0.2
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package org.pjsip.pjsua2;

public final class pjsip_transport_type_e {
  public final static int PJSIP_TRANSPORT_UNSPECIFIED = 0;
  public final static int PJSIP_TRANSPORT_UDP = PJSIP_TRANSPORT_UNSPECIFIED + 1;
  public final static int PJSIP_TRANSPORT_TCP = PJSIP_TRANSPORT_UDP + 1;
  public final static int PJSIP_TRANSPORT_TLS = PJSIP_TRANSPORT_TCP + 1;
  public final static int PJSIP_TRANSPORT_DTLS = PJSIP_TRANSPORT_TLS + 1;
  public final static int PJSIP_TRANSPORT_SCTP = PJSIP_TRANSPORT_DTLS + 1;
  public final static int PJSIP_TRANSPORT_LOOP = PJSIP_TRANSPORT_SCTP + 1;
  public final static int PJSIP_TRANSPORT_LOOP_DGRAM = PJSIP_TRANSPORT_LOOP + 1;
  public final static int PJSIP_TRANSPORT_START_OTHER = PJSIP_TRANSPORT_LOOP_DGRAM + 1;
  public final static int PJSIP_TRANSPORT_IPV6 = 128;
  public final static int PJSIP_TRANSPORT_UDP6 = PJSIP_TRANSPORT_UDP+PJSIP_TRANSPORT_IPV6;
  public final static int PJSIP_TRANSPORT_TCP6 = PJSIP_TRANSPORT_TCP+PJSIP_TRANSPORT_IPV6;
  public final static int PJSIP_TRANSPORT_TLS6 = PJSIP_TRANSPORT_TLS+PJSIP_TRANSPORT_IPV6;
  public final static int PJSIP_TRANSPORT_DTLS6 = PJSIP_TRANSPORT_DTLS+PJSIP_TRANSPORT_IPV6;
}

