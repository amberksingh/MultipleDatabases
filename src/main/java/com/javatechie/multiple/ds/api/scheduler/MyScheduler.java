package com.javatechie.multiple.ds.api.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MyScheduler {

    //@EnableScheduling
    //✅ Spring Boot automatically creates a task scheduler (a background thread pool) that looks for all methods
    // annotated with @Scheduled and runs them automatically — you never call them manually.
    //So your job (scheduled method) starts automatically when the application starts

    // 1️⃣ Runs every 5 seconds
    @Scheduled(fixedRate = 5000)
    public void task1() {
        System.out.println("Fixed rate task - every 5 seconds: " + System.currentTimeMillis());
    }

    // 2️⃣ Runs 10 seconds after the previous execution completes
    @Scheduled(fixedDelay = 10000)
    public void task2() {
        System.out.println("Fixed delay task - runs 10s after previous ends: " + System.currentTimeMillis());
    }

    @Scheduled(initialDelay = 10000, fixedDelay = 3000)
    public void task3() {
        System.out.println("initialDelay 10s and fixed delay task - runs 3s after previous ends: " + System.currentTimeMillis());
    }


    //Spring uses a 6-field cron expression (not 5 like Linux).
    //Field No	Field Name	Allowed Values	Example
    //1	Seconds	0–59	0
    //2	Minutes	0–59	*
    //3	Hours	0–23	*
    //4	Day of month	1–31	*
    //5	Month	1–12 or JAN–DEC	*
    //6	Day of week	0–6 or SUN–SAT	*

    //🧠 2️⃣ Meaning of 0 * * * * *
    //Field	        Value	    Meaning
    //Seconds	    0	    Start exactly at the 0th second
    //Minutes	    *	    Every minute
    //Hours	        *	    Every hour
    //Day of month	*	    Every day
    //Month	        *	    Every month
    //Day of week	*	    Every day of the week

    //🧩 3️⃣ Common variations
    //Expression	        Runs At	                            Description
    //0 * * * * *	        every minute	                once every minute
    //0 */5 * * * *	        every 5 minutes	                at 0s, 5m, 10m, 15m…
    //0 0 * * * *	        every hour	                    at the start of each hour
    //0 0 9 * * *	        9:00 AM daily	                once per day
    //0 0 9 * * MON-FRI	    9:00 AM weekdays	            Mon–Fri only
    //0 0 0 1 * *	        midnight on 1st day of month	monthly job


    // 3️⃣ Runs using cron expression (every minute)
    //Run at second 0 of every minute, every hour, every day, every month
    @Scheduled(cron = "0 * * * * *")
    public void task4() {
        System.out.println("Cron task - runs at the start of every minute");
    }
}