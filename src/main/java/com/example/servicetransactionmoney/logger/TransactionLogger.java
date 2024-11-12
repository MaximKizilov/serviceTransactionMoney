package com.example.servicetransactionmoney.logger;

import com.example.servicetransactionmoney.model.Transaction;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TransactionLogger {

    public enum LogType {
        PENDING,
        SUCCESS,
        FAILED,
        UNKNOWN
    }

    private final static String INCOME_TRANS = "Пришёл запрос на транзакцию, детали : ";
    private final static String COMP_TRANS = "Транзакция обработана : ";
    private final static String STAT_PENDING = "статус : в обратобтке.";
    private final static String STAT_SUCCESS = "статус : успепшно.";
    private final static String STAT_FAILED = "статус : отклонено.";
    private final static String CARD_UNKNOWN = "транзакция не обнаружена";
    //
    private final static String LOG_PATH = "src/main/resources/log.txt";
    private final static String LOG_DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";

    public static void logProcessedTrans(Transaction transaction, LogType logType) {
        StringBuilder logbuilder = new StringBuilder();
        if (logType.equals(LogType.PENDING)) {
            logbuilder.append(INCOME_TRANS);
        } else {
            logbuilder.append(COMP_TRANS);
        }
        logbuilder.append(transaction.toString());

        switch (logType) {
            case PENDING -> logbuilder.append(STAT_PENDING);
            case SUCCESS -> logbuilder.append(STAT_SUCCESS);
            case FAILED -> logbuilder.append(STAT_FAILED);
        }
        log(logbuilder.toString());
    }

    public static void log(String msg) {
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(LOG_DATE_PATTERN);
        String timestap = localDateTime.format(dateTimeFormatter);
        String logmsg = String.format("[%s] %s%n", timestap, msg);
        Path path = Path.of(LOG_PATH);
        try {
            if(!Files.exists(path)){
                Files.createFile(path);
            }
            Files.write(path, logmsg.getBytes(StandardCharsets.UTF_8), StandardOpenOption.APPEND);
        }catch (IOException e){
            System.err.println(e.getMessage());
        }
    }
}
