package org.example.controller;
import junit.framework.TestCase;
import org.example.model.food;
import org.example.view.errorAlert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.io.ByteArrayOutputStream;

@DisplayName("Testing addToOrders method in foodController")
public class foodControllerTest extends TestCase {
    private  foodController controller;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    @Before
    public void setUp() {
        controller = new foodController();
    }
    @Test
    @DisplayName("Add Item To Order")
    public void testAddFoodToOrder() {
        food selectedFood = new food("Nasi goreng", 15000);
        controller.addToOrders(1, selectedFood, "Sedang");
        assertEquals(1, controller.getOrders().size());
    }

    @Test
    @DisplayName("Test order with zero Quantity ")
    public void testQuantityWithZero(){
        food selectedFood = new food("Nasi goreng", 15000);
        controller.addToOrders(0, selectedFood, "Sedang");
        assertEquals(0, controller.getOrders().size());
    }

}