package com.tuniu.ord.vo;

import java.util.List;

import com.tuniu.ord.domain.ManualTourist;
import com.tuniu.ord.domain.ManualTouristCertificate;

public class ManualTouristVo extends ManualTourist {

    /**
     * 
     */
    private static final long serialVersionUID = -400952038835428391L;

    private List<ManualTouristCertificate> certificates;

    /**
     * @return the certificates
     */
    public List<ManualTouristCertificate> getCertificates() {
        return certificates;
    }

    /**
     * @param certificates the certificates to set
     */
    public void setCertificates(List<ManualTouristCertificate> certificates) {
        this.certificates = certificates;
    }
}
