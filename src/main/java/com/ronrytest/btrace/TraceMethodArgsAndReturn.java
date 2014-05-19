// package com.ronrytest.btrace;
//
// import static com.sun.btrace.BTraceUtils.classOf;
// import static com.sun.btrace.BTraceUtils.field;
// import static com.sun.btrace.BTraceUtils.get;
// import static com.sun.btrace.BTraceUtils.print;
// import static com.sun.btrace.BTraceUtils.println;
// import static com.sun.btrace.BTraceUtils.str;
// import static com.sun.btrace.BTraceUtils.strcat;
//
// import com.sun.btrace.annotations.BTrace;
// import com.sun.btrace.annotations.Kind;
// import com.sun.btrace.annotations.Location;
// import com.sun.btrace.annotations.OnMethod;
// import com.sun.btrace.annotations.Return;
// import com.sun.btrace.annotations.Self;
//
// @BTrace
// public class TraceMethodArgsAndReturn {
//
// @OnMethod(clazz = "org.apache.activemq.transport.tcp.TcpTransport", location = @Location(Kind.RETURN))
// public static void onTransportCommandExit(@Self Object message, @Return Object command) { // 捕获调用对象和返回值
//
// println(strcat("message:  ", str(message)));
// Object content = get(field((classOf(message)), "errors", false), message);// 捕获消息内容byte[]
// println(strcat("warns:  ", str(content)));
// print(content);
// content = get(field((classOf(message)), "infos", false), message);
// print(content);
// content = get(field((classOf(message)), "warns", false), message);
// print(content);
// }
//
// @OnMethod(clazz = "com.ronrytest.btrace.CaseObject", method = "execute", location = @Location(Kind.RETURN))
// public static void traceExecute(@Self CaseObject instance, int sleepTime, @Return boolean result) {
// println("call CaseObject.execute");
// println(strcat("sleepTime is:", str(sleepTime)));
// println(strcat("sleepTotalTime is:", str(get(field("CaseObject", "sleepTotalTime"), instance))));
// println(strcat("return value is:", str(result)));
// }
//
// @OnMethod(clazz = "com.alibaba.china.biz.dal.rdbms.IBatisOfferDAO", method = "insertOfferWithSeq", location =
// @Location(Kind.RETURN))
// public static void onInsertOfferWithSeq(@Return long offerId) {
// println(strcat("call IBatisOfferDAO.insertOfferWithSeq(),insert offer", str(offerId)));
// }
// }
