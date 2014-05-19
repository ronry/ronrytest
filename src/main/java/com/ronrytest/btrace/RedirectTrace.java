package com.ronrytest.btrace;

// import static com.sun.btrace.BTraceUtils.println;
// import static com.sun.btrace.BTraceUtils.str;
// import static com.sun.btrace.BTraceUtils.strcat;
//
// import com.sun.btrace.annotations.BTrace;
// import com.sun.btrace.annotations.Kind;
// import com.sun.btrace.annotations.Location;
// import com.sun.btrace.annotations.OnMethod;
// import com.sun.btrace.annotations.Return;
//
// @BTrace
// public class RedirectTrace {
//
// @OnMethod(clazz = "com.alibaba.exodus2.web.common.process.result.RedirectResultHanlder", method = "getFinalLocation",
// location = @Location(Kind.RETURN))
// public static void onInsertOfferWithSeq(@Return String url) {
// println(strcat("call IBatisOfferDAO.insertOfferWithSeq(),redirect to", str(url)));
// }
//
// }
