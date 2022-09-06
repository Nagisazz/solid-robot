package com.nagisazz.fund.service;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;

@Slf4j
@Service
public class FundService {

    public static void main(String[] args) {
        Process proc;
        try {
            String[] param = new String[]{"python",
                    "D:\\project\\fund-invest\\src\\main\\resources\\investment-plan\\DecisionMain.py", "-c 161725", "-d 20190821", "-f 7", "-i 3000", "-b 7813", "-w 0.9738", "-y 0.053", "-m 250", "-v 5", "-t 2"};
            proc = Runtime.getRuntime().exec(param);
            BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream(), StandardCharsets.UTF_8));
            String line = null;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
            in.close();
            BufferedReader err = new BufferedReader(new InputStreamReader(proc.getErrorStream(), StandardCharsets.UTF_8));
            String line2 = null;
            while ((line2 = err.readLine()) != null) {
                System.out.println(line2);
            }
            err.close();
            proc.waitFor();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String calculate() {
        Process proc;
        String res = null;
        String resErr = null;
        try {
            String absolutePath = ResourceUtils.getFile("classpath:investment-plan/DecisionMain.py").getAbsolutePath();
            System.out.println(absolutePath);
            String[] param = new String[]{"python",
                    absolutePath, "-c 161725", "-d 20190821", "-f 7", "-i 3000", "-b 7813", "-w 0.9738", "-y 0.053", "-m 250", "-v 5", "-t 2"};
            proc = Runtime.getRuntime().exec(param);
            BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream(), StandardCharsets.UTF_8));
            while ((res = in.readLine()) != null) {
                System.out.println(res);
            }
            in.close();
            BufferedReader err = new BufferedReader(new InputStreamReader(proc.getErrorStream(), StandardCharsets.UTF_8));
            while ((resErr = err.readLine()) != null) {
                System.out.println(resErr);
            }
            err.close();
            proc.waitFor();
        } catch (Exception e) {
            log.error("计算失败", e);
            e.printStackTrace();
        }
        return res;
    }
}