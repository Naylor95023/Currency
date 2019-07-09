package com.hw.currency;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CurrencyExchanger {
    enum CURRENCY{
        NTD,
        JPY,
        USD
    }

    private Map<String, CurrencyRate> cRateMap = new HashMap<>();
    public CurrencyExchanger(){
        updateRate();
    }

    private boolean iSgetting = true;
    private static final String URL ="https://rate.bot.com.tw/xrt?Lang=zh-TW";
    private void updateRate(){
        Runnable runnable = new Runnable(){
            @Override
            public void run() {
                try {
                    Document doc = Jsoup.connect(URL).get();
                    int i = 0;
                    for(Element title : doc.select("div.hidden-phone")){
                        CurrencyRate cRate = new CurrencyRate();
                        cRate.setCurrency(title.text().replaceAll("[^A-Za-z]", ""));
                        if (i < doc.select("td.rate-content-cash").size()){
                            cRate.setCashBuyRate(doc.select("td.rate-content-cash").eq(i).text());
                            cRate.setCashSoldRate(doc.select("td.rate-content-cash").eq(i+1).text());
                            i += 2;
                        }
                        cRateMap.put(cRate.getCurrency(), cRate);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
                handler.sendEmptyMessage(0);
            }
        };
        new Thread(runnable).start();
    }

    @SuppressLint("HandlerLeak")
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            iSgetting = false;
        }
    };

    public float getExRate(CURRENCY c){

        if(iSgetting){
            return -1f;
        }else{
            return Float.parseFloat(cRateMap.get(c.toString()).getCashBuyRate());
        }
    }
}
