package com.ripple.trustline.dto;

/**
 * @author vreddy
 */
public class TransferResponse {

    public String messsge;

    public TransferStatus transferStatus;

    public TransferResponse() {

    }

    public TransferResponse(String messsge, TransferStatus transferStatus) {
        this.messsge = messsge;
        this.transferStatus = transferStatus;
    }

    public String getMesssge() {
        return messsge;
    }

    public void setMesssge(String messsge) {
        this.messsge = messsge;
    }

    public TransferStatus getTransferStatus() {
        return transferStatus;
    }

    public void setTransferStatus(TransferStatus transferStatus) {
        this.transferStatus = transferStatus;
    }
}

