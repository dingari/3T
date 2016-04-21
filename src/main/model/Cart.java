package main.model;

/**
 * Created by Party on 20.4.2016.
 */
public class Cart {

    private String cartType, cartFrom, cartTo;
    private int cartPrice;

    public Cart(String cartType, String cartFrom, String cartTo, int cartPrice){
        this.cartType = cartType;
        this.cartFrom = cartFrom;
        this.cartTo = cartTo;
        this.cartPrice = cartPrice;
    }

    public String getCartType() {
        return cartType;
    }

    public void setCartType(String cartType) {
        this.cartType = cartType;
    }

    public String getCartFrom() {
        return cartFrom;
    }

    public void setCartFrom(String cartFrom) {
        this.cartFrom = cartFrom;
    }

    public String getCartTo() {
        return cartTo;
    }

    public void setCartTo(String cartTo) {
        this.cartTo = cartTo;
    }

    public int getCartPrice() {
        return cartPrice;
    }

    public void setCartPrice(int cartPrice) {
        this.cartPrice = cartPrice;
    }
}
