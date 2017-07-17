package com.cibersys.firewall.autorization.Services.RequestHandlers.Ping;

import com.cibersys.firewall.commons.Ping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by Luis Maracara on 6/13/2017.
 */
@RestController
public class PingController {
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/ping")
    public Ping greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return new Ping(counter.incrementAndGet(),
                String.format(template, name));
    }
}
