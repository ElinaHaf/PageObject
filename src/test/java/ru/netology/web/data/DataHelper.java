package ru.netology.web.data;

import lombok.Value;

public class DataHelper {
    private DataHelper() {}

    @Value
    public static class AuthInfo {
        private String login;
        private String password;
    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    public static AuthInfo getOtherAuthInfo(AuthInfo original) {
        return new AuthInfo("petya", "123qwerty");
    }

    @Value
    public static class VerificationCode {
        private String code;
    }

    public static VerificationCode getVerificationCodeFor(AuthInfo authInfo) {
        return new VerificationCode("12345");
    }
    @Value
    public static class CardId {
        private String id;
    }

    public static CardId getFirstCardId() {
        return new CardId("92df3f1c-a033-48e6-8390-206f6b1f56c0");
    }

    public static CardId getSecondCardId() {
        return new CardId("0f3f5c2a-249e-4c3d-8287-09f7a039391d");
    }

    @Value
    public static class TransferInfo {
        private int amount;
        private String numberCard;
    }

    public static TransferInfo getFirstCardTransferInfoPositive() {
        return new TransferInfo(5608, "5559000000000002");
    }

    public static TransferInfo getSecondCardTransferInfoPositive() {
        return new TransferInfo(3501, "5559000000000001");
    }

    public static TransferInfo getFirstCardTransferInfoNegative() {
        return new TransferInfo(-10000, "5559000000000002");
    }

    public static TransferInfo getSecondCardTransferInfoNegative() {
        return new TransferInfo(50000, "5559000000000001");
    }

}

