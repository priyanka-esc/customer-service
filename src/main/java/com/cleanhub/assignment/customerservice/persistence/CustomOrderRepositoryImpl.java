package com.cleanhub.assignment.customerservice.persistence;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Repository
@Transactional
public class CustomOrderRepositoryImpl implements CustomOrderRepository{

    private JdbcTemplate jdbcTemplate;

    @Autowired
    CustomOrderRepositoryImpl(JdbcTemplate jdbcTemplate)
    {
        this.jdbcTemplate = jdbcTemplate;
    }

    final private String GET_TOP_CUSTOMERS_SQL_STMT="With minimum as (select distinct oh_order_id, min(oh_quantity) as min from testdb.order_history \n" +
            "group by oh_order_id),\n" +
            "maximum as (select distinct oh_order_id, max(oh_quantity) as max from testdb.order_history \n" +
            "group by oh_order_id) select maximum.oh_order_id, (maximum.max-minimum.min) AS diff FROM maximum,minimum WHERE maximum.oh_order_id=minimum.oh_order_id \n" +
            "ORDER BY DIFF DESC limit ?";


    @Override
    public List<String> getTopCustomers(int limit, String interval) {
        List<String> customersWithHighOrder = new ArrayList<>();
        jdbcTemplate.query(GET_TOP_CUSTOMERS_SQL_STMT, getPreparedStatementSetter(limit), rs -> {
            customersWithHighOrder.add(String.valueOf(rs.getLong("oh_order_id")));
        });
        log.info("top customers retrieved, {}",customersWithHighOrder.size());
        //get customer name from customer and order table mappings.
        return customersWithHighOrder;
    }

    private PreparedStatementSetter getPreparedStatementSetter(int limit) {
        return ps -> {
            ps.setInt(1, limit);
        };
    }
}
