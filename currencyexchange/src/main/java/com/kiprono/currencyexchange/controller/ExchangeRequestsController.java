package com.kiprono.currencyexchange.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

@RestController
public class ExchangeRequestsController {
    @GetMapping("/getCurrencyExchange")
    public JsonObject getCurrencyExchange(@RequestParam("currency") String currency) {
        JsonObject jsonObject = new JsonObject();
        try {

            String data = getExchangeRateData(currency);
            data = data.replaceAll("^\"|\"$", "");
            System.out.println("TTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTT\n"+data);
            StringTokenizer jsonTokenizer = new StringTokenizer(data, ",");
            System.out.println("TTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTT\n"+jsonTokenizer);
            String internalData[];
            String expectedExchangeRateOutput = null;
            ArrayList otherCurrencies = new ArrayList();

            if (currency.equalsIgnoreCase("INR")) {
                otherCurrencies.add("GBP");
                otherCurrencies.add("EUR");
            }

            if (currency.equalsIgnoreCase("GBP")) {
                otherCurrencies.add("INR");
                otherCurrencies.add("EUR");
            }

            if (currency.equalsIgnoreCase("EUR")) {
                otherCurrencies.add("INR");
                otherCurrencies.add("GBP");
            }
            while (jsonTokenizer.hasMoreTokens()) {
                expectedExchangeRateOutput = jsonTokenizer.nextToken();
                System.out.println("---------------------------\n"+expectedExchangeRateOutput);
                internalData = StringUtils.split(expectedExchangeRateOutput, ":");
                System.out.println("---------------------------\n"+internalData);
                System.out.println(internalData[0] + internalData[1]);
                jsonObject.addProperty(currency, internalData[1]);
                if (internalData[0].substring(2,internalData[0].length()-1).equalsIgnoreCase(currency)) {
                    jsonObject.addProperty(currency, internalData[1]);
                }
                if (internalData[0].substring(1,internalData[0].length()-1).equalsIgnoreCase(otherCurrencies.get(0).toString())) {
                    jsonObject.addProperty(otherCurrencies.get(0).toString(), internalData[1]);
                }
                if (internalData[0].substring(1,internalData[0].length()-1).equalsIgnoreCase(otherCurrencies.get(1).toString())) {
                    jsonObject.addProperty(otherCurrencies.get(1).toString(), internalData[1]);
                }
            }
            return jsonObject;
        }catch (Exception exception){
            System.out.println("/////////////////////////////////\n"+exception.getMessage().toString());
            return jsonObject;
        }





    }

    private String getExchangeRateData(String currency) throws IOException {
        String data = null;
        List<String> gfdsdfg = new ArrayList<>();
        StringBuilder responseData = new StringBuilder();
        JsonObject jsonObject = null;
        URL url = null;
        url = new URL("https://api.exchangerate-api.com/v4/latest/" + currency);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + url);
        // System.out.println("Response Code : " + responseCode);
        try (BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()))) {
            String line;
            while ((line = in.readLine()) != null) {
                responseData.append(line);
            }
            jsonObject = new Gson().fromJson(responseData.toString(), JsonObject.class);

            data = jsonObject.get("rates").toString();
            System.out.println("PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP\n"+data);

        }
        // System.out.println(data);
        return data;
    }
}
