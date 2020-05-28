package com.example.appdoencaspi;

public class Api {

    private static final String ROOT_URL = "http://192.168.0.11/DoencaApi/v1/Api.php?apicall=";

    public static final String URL_CREATE_DOENCAS = ROOT_URL + "createdoenca";
    public static final String URL_READ_DOENCAS = ROOT_URL + "getDoenca";
    public static final String URL_UPDATE_DOENCAS = ROOT_URL + "updatedoenca";
    public static final String URL_DELETE_DOENCAS = ROOT_URL + "deletedoenca&id=";
}
