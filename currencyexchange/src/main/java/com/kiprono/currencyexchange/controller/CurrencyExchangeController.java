package com.kiprono.currencyexchange.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonParser;
import com.kiprono.currencyexchange.Currencies;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URL;
import java.util.*;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.StringTokenizer;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

@Controller
public class CurrencyExchangeController {
    @RequestMapping("/home")
    public String getGreeting() {
        try {
            return "currencyexchange";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}
