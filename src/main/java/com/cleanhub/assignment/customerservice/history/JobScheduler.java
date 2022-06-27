package com.cleanhub.assignment.customerservice.history;

import com.cleanhub.assignment.customerservice.manager.CustomerManager;
import com.cleanhub.assignment.customerservice.manager.OrderManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Slf4j
@Service
public class JobScheduler {

    private final CustomerManager customerManager;
    private final OrderManager orderManager;

    JobScheduler(CustomerManager customerManager, OrderManager orderManager) {
        this.customerManager = customerManager;
        this.orderManager = orderManager;
    }

    //The data is populated for the first time
    @PostConstruct
    public void onStartup() {
        fetchCustomers();
        fetchQuantityForOrders();
        fetchQuantityForOrders();
    }

    //History with current snapshot being stored.
    //cron to be taken from application properties file
    @Scheduled(cron = "* */10 * * * *")
    public void fetchQuantityForOrders() {
        log.info("Start : Fetching the quantities");
        orderManager.getOrders();
        log.info("End: Fetching order quantities done");
    }

    //not so frequent call
    @Scheduled(cron = "* */30 * * * *")
    public void fetchCustomers() {
        log.info("Fetching the new customers");
        customerManager.addCustomers();
        log.info("Fetching the new customers done");
    }


    //#TODO: add a cleanup job to remove older entries
}
