package com.ronrytest.web.servlert.performance;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GWMockServlet extends HttpServlet {

    private static final long serialVersionUID = 4493980394425127885L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String t = req.getParameter("t");
        try {
            Thread.sleep(t == null ? 10 : Integer.parseInt(t));
        } catch (InterruptedException e) {
        }
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 100000; i++) {
            sb.append("a");
        }
        try {
            Thread.sleep(t == null ? 10 : Integer.parseInt(t));
        } catch (InterruptedException e) {
        }
        for (int i = 0; i < 100000; i++) {
            sb.append("a");
        }
        try {
            Thread.sleep(t == null ? 10 : Integer.parseInt(t));
            resp.getWriter().write("进入TaobaoLoginProcessor, req:UnifyLoginReq[loginId=顾小意119,appId=TAOBAO,appKey=21380790,loginType=taobao,validateTpye=withpwd,scene=<null>,ssoToken=<null>,signData=<null>,checkCodeId=<null>,checkCode=<null>,apdid=eYOHkfHyFEhn6U0ruRiCnor3b19p5k24l9yYhoNo,utdid=Ut8gZYQ2J/UDAE7eCgiQzZeW,tid=<null>,ttid=<null>,productId=<null>,productVersion=4.8.1,umidToken=Mwy6OhqDVK6aNaf7ohZ1jB30tsNr2Zpv,IMSI=<null>,IMEI=<null>,channel=<null>,clientType=<null>,userAgent=<null>,screenWidth=320,screenHigh=480,mobileBrand=Apple,mobileModel=vf nl,accessPoint=<null>,clientPostion={\"direction\":-1,\"altitude\":64.1246337890625,\"speed\":-1,\"longitude\":117.8453874625378,\"latitude\":36.79962438674299,\"accuracy\":65,\"wifiInfos\":[{\"ssid\":\"Tenda_5EB910\",\"mac\":\"c8:3a:35:5e:b9:10\",\"rssi\":0}]},systemType=IOS,systemVersion=6.1,wifiMac=c8:3a:35:5e:b9:10,wifiNodeName=Tenda_5EB910,lacId=<null>,cellId=<null>,isPrisonBreak=1,token=<null>,deviceId=Al4Cy4XHeazeum3cn1hpLhAFnA6PAorUBCKODxH1Xifs,externParams=<null>,fieldNames=[loginPwd, alipayEnvJson, taobaoEnvJson, loginPwd, alipayEnvJson, taobaoEnvJson]]");
            resp.getWriter().flush();
        } catch (InterruptedException e) {
        }
    }

}
