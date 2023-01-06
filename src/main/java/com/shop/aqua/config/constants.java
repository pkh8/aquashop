package com.shop.aqua.config;

public final class constants {

    //Resoutce대상
//    public static final String[] resourceArray = new String[] { "/css/**", "/fonts/**", "/images/**", "/js/**",
//            "/modules/**", "/h2-console/**", "/swagger-ui/**" };

    //권한제외대상
    public static final String[] permitAllArray = new String[] { "/login.do", "/logout.do", "/swagger-ui/**",
            "/swagger-ui" };


    //인터셉터 제외대상
    public static final String[] interceptorArray = new String[] { "/css/**", "/fonts/**", "/images/**", "/js/**",
            "/modules/**", "/login.do", "/logout.do", "/upload.do" };

}



