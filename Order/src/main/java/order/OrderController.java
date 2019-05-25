package main.java.order;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.util.SerializationUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import redis.clients.jedis.Jedis;

@RestController
// Catch everything starting with /orders
@RequestMapping("/orders")
public class OrderController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
    
    private Jedis jedis = new Jedis("redis", 6379);

    // @RequestMapping(method=GET) or POST etc. to narrow mapping. without method=.. it maps all HTTP ops.
    // Pathvariable => straight up
    // POST - create order for userId, return orderId
    @RequestMapping("/create/{userId}")
    public long orderCreate(@PathVariable String userId) {
        // create new order
        Order o = new Order(userId, counter.incrementAndGet());
        // save order
        jedis.set(String.valueOf(o.getorderId()), String.valueOf(o.getUserId()));
        // return new orderId
        return o.getorderId();
    }


    // @RequestMapping("/remove/{orderid}")
    // RequestParam => orders/remove?orderId={id}
    // DELETE - deletes order with orderId
    // Return success/failure ?
    @RequestMapping("/remove/{orderId}")
    public boolean orderRemove(@PathVariable String orderId) {
        // jedis.del() returns number of deleted items
        // long deleted = jedis.del(orderId);
        long deleted = 0;
        if (deleted>0){
            return true;
        }
        return false;
    }


    @RequestMapping("/find/{orderId}")
    // GET - retrieve info about orderId: pay status, items, userId
    public String orderFind(@PathVariable String orderId) {
        // retrieve order
        //Order o = new Order("test", Long.parseLong(orderId));
        return jedis.get(String.valueOf(orderId));
        // return order details
        //return new Order("def", counter.incrementAndGet());
    }

    @RequestMapping("/addItem/{orderid}/{itemid}")
    // POST - add item with itemId to orderId
    public Order orderAddItem(@PathVariable String orderId, @PathVariable String itemId) {
        // get order - orderId
        // add item:itemId to order
        return new Order("def", counter.incrementAndGet());
    }

    @RequestMapping("/removeItem/{orderid}/{itemid}")
    // DELETE/POST - remove itemId from orderId
    public Order orderRemoveItem(@PathVariable String orderId, @PathVariable String itemId) {
        // get order - orderId
        // remove item:itemId from order
        return new Order("def", counter.incrementAndGet());
    }

    // POST - make payment (call pay service), subtract stock(stock service)
    //          return status (success/fail)
    @RequestMapping("/checkout/{orderid}")
    public Order orderCheckout(@PathVariable String orderId) {
        // get order - orderId
        // checkout order
        return new Order("def", counter.incrementAndGet());
    }

    //
    @RequestMapping("/")
    public String orderEnd() {
        // get order - orderId
        // checkout order
        return "This is the order endpoint";
    }

}