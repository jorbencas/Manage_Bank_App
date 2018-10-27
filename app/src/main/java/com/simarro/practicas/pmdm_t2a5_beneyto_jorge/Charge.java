package com.simarro.practicas.pmdm_t2a5_beneyto_jorge;

public class Charge {
    private String number_account;
    private String account;
    private char divise;
    private boolean jusfityer;

    public Charge() {
        this.number_account = "";
        this.account = "";
        this.divise = ' ';
        this.jusfityer = false;
    }

    public String getNumber_account() {
        return number_account;
    }

    public void setNumber_account(String number_account) {
        this.number_account = number_account;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public char getDivise() {
        return divise;
    }

    public void setDivise(char divise) {
        this.divise = divise;
    }

    public boolean isJusfityer() {
        return jusfityer;
    }

    public void setJusfityer(boolean jusfityer) {
        this.jusfityer = jusfityer;
    }

    @Override
    public String toString() {
        return "Charge{" +
                "number_account='" + number_account + '\'' +
                ", account='" + account + '\'' +
                ", divise=" + divise +
                ", jusfityer=" + jusfityer +
                '}';
    }
}
